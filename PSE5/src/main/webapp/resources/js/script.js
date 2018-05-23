/* global PF */

function handleLoginRequest(xhr, status, args) {
    if(!args.validationFailed && args.loggedIn) {
        PF('dialogWidget').show();
    }
}

function handleRegisterRequest(xhr, status, args) {
    if(!args.validationFailed && args.register) {
        PF('dialogWidget').show();
    }
}


$(document).ready(function() {     
    $('#descargarInforme').hover(function(){     
        $('#descargarInforme').addClass('ui-state-hover');    
    },     
    function(){    
        $('#descargarInforme').removeClass('ui-state-hover');     
    });
});   

function generarInforme(){
    setTimeout(function() {
        document.getElementById("descargarInforme").style.removeProperty("visibility");
    }, 500);
}