<%--
  Created by IntelliJ IDEA.
  User: Rostyk
  Date: 02.08.2017
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Schedule</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/font-awesome.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-social.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/search.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/passwordStrength.css"/>" rel="stylesheet">
    <link href='<c:url value="/resources/calendarResourses/fullcalendar.min.css"/>' rel='stylesheet'/>
    <link href='<c:url value="/resources/calendarResourses/fullcalendar.print.css"/>' rel='stylesheet' media='print'/>
    <link href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/calendarResourses/bootstrap-dialog.css"/>" rel="stylesheet">
    <script src='<c:url value="/resources/calendarResourses/moment.min.js"/>'></script>
    <script src='<c:url value="/resources/calendarResourses/jquery.min.js"/>'></script>
    <script src='<c:url value="/resources/calendarResourses/fullcalendar.min.js"/>'></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.autocomplete.min.js"/>"></script>
    <script src="<c:url value="/resources/js/zxcvbn.min.js"/>"></script>
    <script src="<c:url value="/resources/js/passwordStrength.js"/>"></script>
    <script src="<c:url value="/resources/js/search.js"/>"></script>
    <script src="<c:url value="/resources/js/mainSearch.js"/>"></script>
    <script src="<c:url value="/resources/calendarResourses/bootstrap-confirmation.min.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap-datetimepicker.js"/>" charset="UTF-8"
            type="text/javascript"></script>
    <script src="<c:url value="/resources/calendarResourses/bootstrap-dialog.js"/>" ></script>
    <script src="<c:url value="/resources/calendarResourses/locale-all.js"/>" ></script>

    <script>

        $(document).ready(function () {

            var DangerDialogWindow = new BootstrapDialog();
            DangerDialogWindow.setTitle('Wrong input!');
            DangerDialogWindow.setMessage('Wrong input!');
            DangerDialogWindow.setType(BootstrapDialog.TYPE_DANGER);

            // Using chain callings
            var SuccessDialogWindow = new BootstrapDialog()
                .setTitle('Success!')
                .setMessage('Event added')
                .setType(BootstrapDialog.TYPE_SUCCESS);

            $('#calendar').fullCalendar({
                weekend: false, // disable events
                editable: false, // allow to add
                eventLimit: true, // allow "more" link when too many events
                draggable: false,
                locale : '${locale}',
                events: {
                    url: '/doctor/cabinet/getApp',
                    type: 'POST',
                    contentType: 'application/json'
                },
                eventClick: function (event) {
                    if (event.status === "PASSIVE") {
                        $(this).confirmation({
                            title: "Do you want to confirm?",
                            container: 'body',
                            onConfirm: function () {
                                $.ajax({
                                    url: '/doctor/cabinet/setApp/' + event.id,
                                    method: 'GET',
                                    contentType: 'application/json',
                                    success: function (result) {
                                        $('#calendar').fullCalendar({
                                            events: {
                                                url: '/doctor/cabinet/getApp',
                                                type: 'POST',
                                                contentType: 'application/json'
                                            }
                                        });
                                        $('#calendar').fullCalendar('rerenderEvents');
                                        $('#calendar').fullCalendar('refetchEvents');
                                        $('#calendar').fullCalendar('refresh')
                                    }
                                });
                            },
                            placement: 'top'
                        }).appendTo('calendar');
                        $(this).confirmation('show');
                    }
                }
            });
            $("#date-div").datetimepicker({
                language: '${pageContext.response.locale}',
                format: "dd/mm/yyyy - hh:ii",
                autoclose: true,
                minuteStep: 15,
                startDate: new Date(),
                daysOfWeekDisabled: [0, 6],
                hoursDisabled: [1, 2, 3, 4, 5, 6, 22, 23, 0],
                onRenderMinute: function (date) {

                    var dates = [];
                    var dd;
                    <c:choose>
                    <c:when test="${docApps.size()>0}">
                    <c:forEach items="${docApps}" var="apointments">
                    appDate = new Date("${apointments.appointmentDate}");
                    dates.push(appDate);
                    </c:forEach>
                    </c:when>
                    </c:choose>

                    for (var i = 0; i < dates.length; i++) {
                        if (date.getTime() === dates[i].getTime()) {
                            return ['disabled'];
                        }
                    }
                }
            });


            $("#autocom").autocomplete({
                serviceUrl: '/user/search',
                paramName: "name",
                transformResult: function (response) {
                    return {
                        suggestions: $.map($.parseJSON(response), function (item) {
                            var i = item.firstname + " " + item.lastname;
                            return {value: i, data: item.id};
                        })
                    };
                },
                onSelect: function (suggestion) {
                    $("#successinfo").html(suggestion.data);
                }

            });



            $( "#control-button" ).click(function() {
                if ($("#first-date").val() !== "" && $("#successinfo").html() !== "") {
                    $.ajax({
                        url: '/user/addApp/',
                        method: 'POST',
                        data: {
                            "datatime": $("#first-date").val(),
                            "input": $("#successinfo").html()
                        },
                        success: function (result) {
                            $('#calendar').fullCalendar('rerenderEvents');
                            $('#calendar').fullCalendar('refetchEvents');
                            $('#calendar').fullCalendar('refresh');
                            $("#first-date").val("");
                            $("#successinfo").html("");
                            $("#autocom").val("");
                            SuccessDialogWindow.open();
                        }
                    });
                }
                else
                {
                    DangerDialogWindow.open();
                }

            });
        });


    </script>

    <style>

        .popover.confirmation {

        }

        body {
            margin: 40px 10px;
            padding: 0;
            font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
            font-size: 14px;
        }

        #calendar {
            max-width: 900px;
            margin: 0 auto;
        }

        #calendar .fc-today {
            background: #B2EBF2;
            border: 3px #fff81c;
        }

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand hidden-md hidden-sm" href="${pageContext.request.contextPath}/">
                <img src="${pageContext.request.contextPath}/resources/img/heartbeat2.png" height=35 width=100>
            </a>

            <div class="hidden-lg hidden-md hidden-sm col-xs-6 pull-right">
                <li class="nav navbar-nav flags pull-right">
                    <a class="" href="?lang=en" style="padding: 20px 0 ; float: left">
                        <img src="${pageContext.request.contextPath}/resources/img/flag-gb.png" class="flag flag-gb"
                             alt="Great Britain"/>
                    </a>
                    <a class="" href="?lang=uk"  style="padding: 20px 3px ; float: left ">
                        <img src="${pageContext.request.contextPath}/resources/img/flag-ua.png" class="flag flag-ua"
                             alt="Ukraine"/>
                    </a>
                    <span>&nbsp;&nbsp;</span>
                </li>
            </div>
        </div>


        <div id="navbar" class="navbar-collapse collapse">
            <ul  class="nav navbar-nav">
                <li>
                    <a href="${pageContext.request.contextPath}/">
                        <span class="glyphicon glyphicon-home"></span>
                        <spring:message code="messages.home" />
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/clinics/1">
                        <span class="glyphicon glyphicon-plus-sign"></span><spring:message code="messages.clinics" />
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/allDoctors/1">
                        <span class="glyphicon glyphicon-user"></span><spring:message code="messages.doctors" />
                    </a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/contact"><i class="fa fa-envelope-o"></i> <spring:message code="messages.contact" />
                    </a>
                </li>
                <li>
                    <sec:authorize access="hasAuthority('ROLE_USER')">
                        <a href="${pageContext.request.contextPath}/user/cabinet">
                            <i class="fa fa-id-card"></i>
                            <spring:message code="messages.userCabinet" />
                        </a>
                    </sec:authorize>
                </li>
                <li class='active'>
                    <sec:authorize access="hasAuthority('ROLE_DOCTOR')">
                        <a href="${pageContext.request.contextPath}/doctor/сabinet"><i class="fa fa-tasks"></i> <spring:message code="messages.doctorCabinet" />
                        </a>
                    </sec:authorize>
                </li>
                <li class="${current=='moderator/cabinet'? 'active':''}">
                    <sec:authorize access="hasAuthority('ROLE_MODERATOR')">
                        <a href="${pageContext.request.contextPath}/moderator/cabinet"><i class="fa fa-cogs"></i> <spring:message code="messages.moderatorCabinet" />
                        </a>
                    </sec:authorize>
                </li>
                <li>
                    <a class="navbar-brand pull-right hidden-xs hidden-md hidden-sm" href="${pageContext.request.contextPath}">
                        <img src="${pageContext.request.contextPath}/resources/img/heartbeat2.png" height=35 width=100>
                    </a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <sec:authorize access="isAuthenticated()">
                        <a type="text">
                            <%= session.getAttribute("username") %>
                        </a>
                    </sec:authorize>
                </li>
                <li class="nav navbar-nav flags hidden-xs">
                    <a class="" href="?lang=en" style="padding: 20px 0 ; float: left">
                        <img src="${pageContext.request.contextPath}/resources/img/flag-gb.png" class="flag flag-gb"
                             alt="Great Britain"/>
                    </a>
                    <a class="" href="?lang=uk"  style="padding: 20px 3px ; float: left ">
                        <img src="${pageContext.request.contextPath}/resources/img/flag-ua.png" class="flag flag-ua"
                             alt="Ukraine"/>
                    </a>
                    <span>&nbsp;&nbsp;</span>
                </li>

                <sec:authorize access="!isAuthenticated()">
                    <li><a type="button" onclick="hideText()" id="loginBtn" data-toggle="modal" data-target="#loginModal" style="cursor:pointer;">
                        <span class="glyphicon glyphicon-log-in"></span> <spring:message code="messages.login"/> </a>
                    </li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a type="button" id="logoutBtn" style="cursor:pointer;" href="${pageContext.request.contextPath}/logout">
                            <span class="glyphicon glyphicon-log-out"></span> <spring:message code="messages.logout"/>
                        </a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>
<sec:authorize access="!isAuthenticated()">
    <!--LOGIN MODAL________________________________________________________________________________________________________________________________________-->
    <div id="loginModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="col-lg-3">
                        <h3 class="form-heading"><spring:message code="messages.login"/></h3>
                    </div>
                    <div class="col-lg-9">
                        <button class="close" type="button" data-dismiss="modal">
                            <i class="fa fa-close"></i>
                        </button>
                    </div>
                </div>

                <form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
                    <div class="form-group ${error != null ? 'has-error' : ''}">
                            <%--<span>${message}</span>--%>
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="control-label" for="email">
                                    <spring:message code="messages.email"/>
                                </label>
                                <input id="email" name="j_username" type="email" class="form-control"
                                       placeholder="<spring:message code="messages.email"/>"
                                       autofocus=""/>
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="password">
                                    <spring:message code="messages.password"/>
                                </label>
                                <input id="password" name="j_password" type="password" class="form-control"
                                       placeholder="<spring:message code="messages.password"/>"/>
                            </div>
                                <%--<span>${error}</span>--%>
                                <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>

                            <div class="form-group">
                                <input id="remember" type="checkbox" name="remember-me" value="true">
                                <label class="control-label" for="remember">
                                    <spring:message code="messages.rememberMe"/>
                                </label>
                            </div>

                            <p id="wrong" style="color: red"></p>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-lg btn-primary btn-block">
                                <spring:message code="messages.login"/>
                            </button>
                            <h4 class="text-center">
                                <a href="<c:url value="/registration"/>"><spring:message
                                        code="messages.createAccount"/></a>
                            </h4>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</sec:authorize>


<script>
    function hideText() {
        document.getElementById('wrong').innerHTML = '';
    }
</script>


<c:if test="${flag}">
    <script>
        document.getElementById('wrong').innerHTML = '<spring:message code="messages.invalidLoginOrPassword"/>';
        jQuery(window).load(function () {
            jQuery('#loginModal').modal('show')
        });
        <c:set var="flag" value="false"/>
    </script>
</c:if>


<div class="container">
    <div class="container" style="width: 30%; float: left">
        <div class="row row-content">
            <div class="list-group doc-menu">
                <a href="#" class='list-group-item active'>
                    <spring:message code="messages.schedule"/>
                </a>
                <a href=<c:url value="/doctor/patients"/> class="list-group-item">
                    <spring:message code="messages.patients"/>
                </a>
            </div>
        </div>
        <div class="row row-content">
            <ul style="font-size: 14px">
                <li style="color: #4CAF50;"><span style="font-family: " Lucida Grande", Helvetica, Arial, Verdana,
                    sans-serif;
                    "> <spring:message code="messages.dotActive"/> </span></li>
                <li style="color: #E53935;"><span style="font-family: " Lucida Grande", Helvetica, Arial, Verdana,
                    sans-serif;
                    "> <spring:message code="messages.dotPassive"/> </span></li>
                <li style="color: #424242;"><span style="font-family: " Lucida Grande", Helvetica, Arial, Verdana,
                    sans-serif;
                    "> <spring:message code="messages.dotPActive"/> </span></li>
                <li style="color: #546E7A;"><span style="font-family: " Lucida Grande", Helvetica, Arial, Verdana,
                    sans-serif;
                    "> <spring:message code="messages.dotPPassive"/> </span></li>
            </ul>
        </div>
        <div class="row row-content">
            <input type="text" class="form-control " id="autocom" style="width: 80%; margin: 2% 10% 2% 10%"
                   placeholder='<spring:message code="messages.UserFirstName"/>' aria-describedby="sizing-addon1">
            <div>
                <div class="form-group" style="text-align: center;  margin-bottom: 10pt; margin-top: 10pt">
                    <div class="input-append date form_datetime" id="date-div">
                        <label for="first-date" style="margin-left: 5pt">
                            <spring:message code="messages.date"/>
                        </label>
                        <input type="text" value="" readonly id="first-date" name="datetime">
                        <span class="add-on"><i class="icon-remove fa fa-times"></i></span>
                        <span class="add-on"><i class="icon-calendar fa fa-calendar"></i></span>
                    </div>
                </div>
                <div style="text-align: center">
                    <button id = "control-button" class="btn btn-primary" style="width: 50%; margin: 5pt auto;">
                        <spring:message code="messages.approve"/>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="container" style="width: 70%; float: right; margin-top: 1.5%" id='calendar'></div>
</div>

<div class="row row-footer">
    <div class="col-xs-5 col-offset-1 col-sm-2 col-sm-offset-1">
        <h5><spring:message code="messages.links"/></h5>
        <ul class="list-unstyled">
            <li><a href=<c:url value="/"/>><spring:message code="messages.home"/></a></li>
            <li><a href=<c:url value="/clinics/1"/>><spring:message code="messages.clinic"/></a></li>
            <li><a href=<c:url value="/allDoctors/1"/>><spring:message code="messages.doctors"/> </a></li>
            <li><a href="#"><spring:message code="messages.contact"/></a></li>
        </ul>
    </div>
    <div class="col-xs-6 col-sm-5">
        <h5><spring:message code="messages.ourAdress"/></h5>
        <address>
            <spring:message code="messages.Fedkovucha"/><br>
            <spring:message code="messages.Lviv"/><br>
            <spring:message code="messages.Ukraine"/><br>
            <i class="fa fa-phone"></i>: +382 1234 5678<br>
            <i class="fa fa-fax"></i>: +382 8765 4321<br>
            <i class="fa fa-envelope"></i>: <a href="mailto:lv251clinics@gmail.com">lv251clinics@gmail.com</a>
        </address>
    </div>
    <div class="col-xs-12 col-sm-4">
        <div class="nav navbar-nav" style="padding: 40px 10px;">
            <a class="btn btn-social-icon btn-facebook" href="http://www.facebook.com/profile.php?id="><i
                    class="fa fa-facebook"></i></a>
            <a class="btn btn-social-icon btn-linkedin" href="http://www.linkedin.com/in/"><i
                    class="fa fa-linkedin"></i></a>
            <a class="btn btn-social-icon btn-twitter" href="http://twitter.com/"><i class="fa fa-twitter"></i></a>
            <a class="btn btn-social-icon btn-youtube" href="http://youtube.com/"><i class="fa fa-youtube"></i></a>
        </div>
    </div>
    <div class="col-xs-12">
        <p style="padding:10px;"></p>
        <p align="center">© Copyright 2017 SoftServe Inc.</p>
    </div>
</div>


<p style="display: none" id = "successinfo" name="allInfo"></p>
</body>
</html>

