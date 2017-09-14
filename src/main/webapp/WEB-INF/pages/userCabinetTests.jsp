<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div>
        <div class="container" style="width: 30%; float: left">
            <div class="row row-content">
                <div class="list-group doc-menu">
                    <a href="<c:url value="/user/cabinet"/>" class="list-group-item">
                        <spring:message code="messages.profile"/>
                    </a>
                    <a href="<c:url value="/user/doctors"/>" class="list-group-item">
                        <spring:message code="messages.doctors"/>
                    </a>
                    <a href="<c:url value="/user/medicalcard"/>" class="list-group-item">
                        <spring:message code="messages.medicalCard"/>
                    </a>
                    <a href="<c:url value="/user/tests"/>" class="navbar-inverse list-group-item">
                        <spring:message code="messages.tests"/>
                    </a>
                </div>
            </div>
        </div>

        <div class="container" style="width: 70%; float: right">
            <div class="row row-content">

                <div class="container-fluid">
                    <div class="row content">
                        <br>
                        <div id="menu_mcard" style="cursor: pointer">
                            <ul>
                                <li><a id="appointmentsHistoryLink" class="navbar-inverse list-group-item"><spring:message code="messages.appointmentsHistory"/></a></li>
                                <li><a id="pendingAppointmentsLink" class="list-group-item"><spring:message code="messages.pendingAppointments"/></a></li>
                            </ul>
                        </div>
                        <div class="col-sm-9" style="width: 100%">
                            <div id="shown_if_not_empty" class="well mcard_content">
                                <c:set var="listPastAppointmentsLength" value="0"/>
                                <c:set var="listPendingAppointmentsLength" value="0"/>
                                <c:forEach items="${tests}" var="test" varStatus="loop">
                                    <fmt:formatDate var="aDate" pattern = 'dd-MM-yyyy HH:mm' value='${test.startDdate}'/>
                                    <c:choose>
                                        <c:when test="${test.endDdate.time lt date}">
                                            <c:set var="showAppointmentClass" value="appointmentsHistory"/>
                                            <c:set var="listPastAppointmentsLength" value="${listPastAppointmentsLength + 1}"/>
                                        </c:when>
                                        <c:when test="${test.endDdate.time ge date}">
                                            <c:set var="showAppointmentClass" value="pendingAppointments"/>
                                            <c:set var="listPendingAppointmentsLength" value="${listPendingAppointmentsLength + 1}"/>
                                        </c:when>
                                    </c:choose>



                                    <div class="${showAppointmentClass}">
                                        <div class="col-sm-6 appointmentWrapper">
                                            <div class="appointmentFloatContainer">
                                            </div>

                                            <div class="medical-card">
                                                <div class="media">
                                                    <div class="media-body">
                                                        <div>
                                                            <div >
                                                                <h3 class="media-heading"><c:out value="${test.test}"/></h3>
                                                            </div>
                                                        </div>

                                                        <div class="specialization"><c:out value="${test.description}"/></div>
                                                        <div class="diagnosis"><c:out value="${test.description}"/></div>
                                                        <div class="diagnosis"><c:out value="${aDate}"/></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div id="appointmentsHistoryListIsEmpty">
                                <div class="well mcard_content">
                                    <div class="col-sm-6" style="width: 100%; padding-left: 0; padding-right: 0;">
                                        <div class="medical-card alert alert-info">
                                            <div class="media">
                                                <div class="media-body">
                                                    <h3 class="media-heading" style="text-align: center"><spring:message code="messages.noPastAppointments"/></h3>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="pendingAppointmentsListIsEmpty">
                                <div class="well mcard_content">
                                    <div class="col-sm-6" style="width: 100%; padding-left: 0; padding-right: 0;">
                                        <div class="medical-card alert alert-info">
                                            <div class="media">
                                                <div class="media-body">
                                                    <h3 class="media-heading" style="text-align: center"><spring:message code="messages.noPendingAppointments"/></h3>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <script>
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

                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
