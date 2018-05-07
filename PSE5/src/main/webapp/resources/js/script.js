/* global PF */

function handleLoginRequest(xhr, status, args) {
    if(args.validationFailed || !args.loggedIn) {
        PF('login').jq.effect("shake", {times:5}, 100);
    }
    else {
        PF('login').hide();
    }
}

function handleRegistroRequest(xhr, status, args) {
    if(args.validationFailed || !args.loggedIn) {
        PF('registro').jq.effect("shake", {times:5}, 100);
    }
    else {
        PF('registro').hide();
    }
}

function closeDialog(){
    PF('dlg').hide();
}


