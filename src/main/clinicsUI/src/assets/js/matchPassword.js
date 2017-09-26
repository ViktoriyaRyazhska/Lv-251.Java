/**
 *
 * Created by Vitaliy Kovalevskyy  on 11.08.2017.
 *
 */

$( document ).ready(function() {

    $('#passMatch').on('propertychange change keyup paste input', function() {

        var passMatchVal = $('#passMatch').val();
        var pass = $('#pass').val();
        var signMatch =  '#signMatch';

        if((passMatchVal.length > 0)){

            if(passMatchVal == pass){
                $(signMatch).removeClass("fa-close");
                $(signMatch).addClass("fa fa-check").css("color", '#00b300');
            }else {
                $(signMatch).removeClass("fa-check");
                $(signMatch).addClass("fa fa-close").css("color", 'red');
            }
            $(signMatch).show();
        }
        else{
            $(signMatch).hide();
        }
    })
});