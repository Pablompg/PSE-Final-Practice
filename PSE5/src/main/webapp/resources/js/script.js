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