$(document).ready(function () {
    $('.activeList a').click(function () {
        $('.activeList a').removeClass('active');
        $(this).addClass('active');
    });
});
