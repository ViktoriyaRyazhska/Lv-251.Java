/**
 * Added by Pavlo Kuchereshko.
 */
$( document ).ready(function() {
    $('#passwordReg').on('propertychange change keyup paste input', function() {
        var password = $(this).val();
        var passwordScore = zxcvbn(password)['score'];

        var updateMeter = function(width, background, text) {
            $('.password-background').css({"width": width, "background-color": background});
            $('#strengthPswId').text(text).css('color', background);
        };

        var updatePasswordLengthWarn = function(text) {
            $('#lengthWarn').text(text).css('color', "#ff5b5a");
        };

        if (passwordScore === 0) {
            if (password.length === 0) {
                updateMeter("0%", "#ff383f", "");
            } else {
                updateMeter("20%", "#ff383f", document.getElementById("errorStrengthVeryWeak").innerHTML);
            }
        }
        if (passwordScore === 1) updateMeter("40%", "#ff6d52", document.getElementById("errorStrengthWeak").innerHTML);
        if (passwordScore === 2) updateMeter("60%", "#d59832", document.getElementById("errorStrengthVeryWeak").innerHTML);
        if (passwordScore === 3) updateMeter("80%", "#8dc64e", document.getElementById("errorStrengthMedium").innerHTML);
        if (passwordScore === 4) updateMeter("100%", "#3ec91f", document.getElementById("errorStrengthStrong").innerHTML); // Color needs changing
        if (document.getElementById("passwordReg").value.length < 8
            && document.getElementById("passwordReg").value.length > 0) {
            updatePasswordLengthWarn(document.getElementById("errorWordLength").innerHTML);
        } else updatePasswordLengthWarn("");
    });

    function show() {
        var p = document.getElementById('passwordReg');
        p.setAttribute('type', 'text');
        document.getElementById("eyeSymbol").className = "fa fa-eye-slash";
    }

    function hide() {
        var p = document.getElementById('passwordReg');
        p.setAttribute('type', 'password');
        document.getElementById("eyeSymbol").className = "fa fa-eye";
    }

    var pwShown = 0;

    var eye = document.getElementById("eye");
    if (eye !== null) {

        eye.addEventListener("click", function () {
            if (pwShown === 0) {
                pwShown = 1;
                show();
            } else {
                pwShown = 0;
                hide();
            }
        }, false);
    }

});

$( document ).ready(function() {
    $('#passwordConfirmReg').on('propertychange change keyup paste input', function() {

        var updateMatch = function(text) {
            $('#matchPsw').text(text).css('color', "#ff383f");
        };

        if (document.getElementById("passwordConfirmReg").innerHTML.trim().length === 0
            && document.getElementById("passwordReg").value !== document.getElementById("passwordConfirmReg").value) {
            updateMatch(document.getElementById("errorPasswordMatch").innerHTML);
        }
        else updateMatch("");
    });
});

$('#registration').submit(function() {

    var mustReturn = false;

    var updateFirstNameWarn = function(text) {
        $('#firstNameWarn').text(text).css('color', "#ff383f");
    };

    var updateLastNameWarn = function(text) {
        $('#lastNameWarn').text(text).css('color', "#ff383f");
    };

    var updateEmailWarn = function(text) {
        $('#emailWarn').text(text).css('color', "#ff383f");
    };

    var isReturn = function (goReturn) {
        if (goReturn) mustReturn = true;
        return mustReturn;
    };

    if (document.getElementById("firstName").value.trim().length === 0) {
        updateFirstNameWarn(document.getElementById("errorWordNotEmpty").innerHTML);
        isReturn(true);
    } else updateFirstNameWarn("");

    if (document.getElementById("lastName").value.trim().length === 0) {
        updateLastNameWarn(document.getElementById("errorWordNotEmpty").innerHTML);
        isReturn(true);
    } else updateLastNameWarn("");

    if (document.getElementById("emailReg").value.trim().length === 0) {
        updateEmailWarn(document.getElementById("errorWordNotEmpty").innerHTML);
        isReturn(true);
    } else updateEmailWarn("");

    if (document.getElementById("passwordConfirmReg").value.trim().length !== 0
        && document.getElementById("passwordReg").value.trim() !== document.getElementById("passwordConfirmReg").value.trim()) {
        isReturn(true);
    }

    return !mustReturn;
});
