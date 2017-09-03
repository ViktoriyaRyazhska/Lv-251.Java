/**
 * Added by Pavlo Kuchereshko.
 */
$(function () {
    showAppointmentHistory();
});

var listPastAppointmentsLength = "${listPastAppointmentsLength}";
var listPendingAppointmentsLength = "${listPendingAppointmentsLength}";

var showAppointmentHistory = function () {
    $(".appointmentsHistory").show();
    $(".pendingAppointments").hide();
    $("#appointmentsHistoryLink").addClass("navbar-inverse");
    $("#pendingAppointmentsLink").removeClass("navbar-inverse");
    if (listPastAppointmentsLength == 0) {
        $("#appointmentsHistoryListIsEmpty").show();
        $("#pendingAppointmentsListIsEmpty").hide();
        $("#shown_if_not_empty").hide();
    } else {
        $("#appointmentsHistoryListIsEmpty").hide();
        $("#pendingAppointmentsListIsEmpty").hide();
        $("#shown_if_not_empty").show();
    }
};

var showPendingAppointments = function () {
    $(".appointmentsHistory").hide();
    $(".pendingAppointments").show();
    $("#pendingAppointmentsLink").addClass("navbar-inverse");
    $("#appointmentsHistoryLink").removeClass("navbar-inverse");
    if (listPendingAppointmentsLength == 0) {
        $("#pendingAppointmentsListIsEmpty").show();
        $("#appointmentsHistoryListIsEmpty").hide();
        $("#shown_if_not_empty").hide();
    } else {
        $("#appointmentsHistoryListIsEmpty").hide();
        $("#pendingAppointmentsListIsEmpty").hide();
        $("#shown_if_not_empty").show();
    }
};

$("#appointmentsHistoryLink").click(showAppointmentHistory);
$("#pendingAppointmentsLink").click(showPendingAppointments);

$(".appointmentsHistory").click(function() {
    $(this).modal();
});
