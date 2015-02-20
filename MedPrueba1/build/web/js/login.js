$(document).ready(function () {

    /* On load focus username input login */
    $('#username').focus();

    /* Keys pressed event handlers */
    $(document).keydown(function (key) {
        // "Enter" = 13
        if (key.keyCode == '13') {
            if ($('#form-signin').is(":visible")) {
                $('#btnLogOn').click();
            } else if ($('#form-register').is(":visible")) {
                $('#btnRegisterSend').click();
            } else if ($('#form-forgot').is(":visible")) {
                $('#btnForgotSend').click();
            }
        } else if (key.keyCode == '27') {
            if ($('#form-register').is(":visible")) {
                $('#btnRegisterCancel').click();
            } else if ($('#form-forgot').is(":visible")) {
                $('#btnForgotCancel').click();
            }
        }
    });

});