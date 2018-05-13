//To disable on keypress actions with primefaces
$('form').off('keypress.disableAutoSubmitOnEnter').on('keypress.disableAutoSubmitOnEnter', function(event) {
    if (event.which === $.ui.keyCode.ENTER && $(event.target).is(':input:not(textarea,:button,:submit,:reset)')) {
        event.preventDefault();
    }
});

var wsUri = 'ws://' + document.location.host
        + document.location.pathname.substr(0,
                document.location.pathname.indexOf("/faces")) + '/websocket';
console.log(wsUri);
var websocket = new WebSocket(wsUri);//Inicializa el websocket
var textField = document.getElementById("texto");
var users = document.getElementById("users");
var chatlog = document.getElementById("chatlog");
var output = document.getElementById("output");
var username =textField.value;
function join() {
    websocket.send(username + " joined");
    $("#texto").val('');
    document.getElementById("unirse").style.setProperty("visibility", "hidden");
    document.getElementById("enviar").style.removeProperty("visibility");
    document.getElementById("desconectar").style.removeProperty("visibility");
    document.getElementById("texto").style.removeProperty("visibility");
}
function send_message() {
    websocket.send(username + ": " + textField.value);
}
function disconnect() {
    websocket.close();
    document.getElementById("unirse").style.setProperty("visibility", "hidden");
    document.getElementById("enviar").style.setProperty("visibility", "hidden");
    document.getElementById("desconectar").style.setProperty("visibility", "hidden");
    window.location.href = "http://localhost:8080/PSE5/faces/index.xhtml";
}
websocket.onopen = function () {
    writeToScreen("WEB SOCKET CONNECTION ESTABLISHED");
};
websocket.onclose = function () {
    writeToScreen("WEB SOCKET CONNECTION CLOSED");
};
websocket.onmessage = function (evt) {
    writeToScreen("RECEIVED: " + evt.data);
    if (evt.data.indexOf("joined") !== -1) {
        users.innerHTML += evt.data.substring(0, evt.data.indexOf("joined")) + "\n";
    } else {
        chatlog.innerHTML += evt.data + "\n";
    }
};
websocket.onerror = function (evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
};
function writeToScreen(message) {
    //Muestro por pantalla un mensaje con la situación del websocket
    output.innerHTML = "";
    var pre = document.createElement("p");
    pre.style.wordWrap = "break-word";
    pre.innerHTML = message;
    output.appendChild(pre);
}
