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
                                <li><a id="activeTestsLink" class="navbar-inverse list-group-item">
                                    <spring:message code="messages.activeTests"/>
                                </a></li>
                                <li><a id="unactiveTestsLink" class="list-group-item">
                                    <spring:message code="messages.unactiveTests"/>
                                </a></li>
                            </ul>
                        </div>
                        <div class="col-sm-9" style="width: 100%">
                            <div id="shown_if_not_empty" class="well mcard_content">
                                <c:set var="listActiveTestsLength" value="0"/>
                                <c:set var="listPastTestsLength" value="0"/>
                                <c:forEach items="${tests}" var="test" varStatus="loop">
                                    <fmt:formatDate var="startDate" pattern = 'dd-MM-yyyy HH:mm' value='${test.startDdate}'/>
                                    <fmt:formatDate var="endDate" pattern = 'dd-MM-yyyy HH:mm' value='${test.endDdate}'/>
                                    <c:choose>
                                        <c:when test="${test.endDdate.time lt date}">
                                            <c:set var="showTestsClass" value="pastTests"/>
                                            <c:set var="listActiveTestsLength" value="${listActiveTestsLength + 1}"/>
                                        </c:when>
                                        <c:when test="${test.endDdate.time ge date}">
                                            <c:set var="showTestsClass" value="activeTests"/>
                                            <c:set var="listPastTestsLength" value="${listPastTestsLength + 1}"/>
                                        </c:when>
                                    </c:choose>



                                    <div class="${showTestsClass}">
                                        <div class="col-sm-6 testWrapper">
                                            <div class="testFloatContainer">
                                            </div>

                                            <div class="medical-card">
                                                <div class="media">
                                                    <div class="media-body">
                                                        <div>
                                                            <div class="row">
                                                                <div class="col-xs-9">
                                                                    <h3 class="media-heading">
                                                                        <c:out value="${test.test}"/>
                                                                    </h3>
                                                                </div>
                                                                <div class="col-xs-3">
                                                                    <div class="row">
                                                                        <div class="test-description">
                                                                            <c:out value="${startDate}"/>
                                                                        </div>
                                                                    </div>
                                                                    <div class="row">
                                                                        <div class="test-description">
                                                                            <c:out value="${endDate}"/>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="test-description">
                                                            <c:out value="${test.description}"/>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div id="activeTestsListIsEmpty">
                                <div class="well mcard_content">
                                    <div class="col-sm-6" style="width: 100%; padding-left: 0; padding-right: 0;">
                                        <div class="medical-card alert alert-info">
                                            <div class="media">
                                                <div class="media-body">
                                                    <h3 class="media-heading" style="text-align: center">
                                                        <spring:message code="messages.noPastAppointments"/>
                                                    </h3>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="pastTestsListIsEmpty">
                                <div class="well mcard_content">
                                    <div class="col-sm-6" style="width: 100%; padding-left: 0; padding-right: 0;">
                                        <div class="medical-card alert alert-info">
                                            <div class="media">
                                                <div class="media-body">
                                                    <h3 class="media-heading" style="text-align: center">
                                                        <spring:message code="messages.noPendingAppointments"/>
                                                    </h3>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <script>
                                $(function () {
                                    showActiveTests();
                                });

                                var listActiveTestsLength = "${listActiveTestsLength}";
                                var listPastTestsLength = "${listPastTestsLength}";

                                var showActiveTests = function () {
                                    $(".pastTests").show();
                                    $(".activeTests").hide();
                                    $("#activeTestsLink").addClass("navbar-inverse");
                                    $("#unactiveTestsLink").removeClass("navbar-inverse");
                                    if (listActiveTestsLength == 0) {
                                        $("#activeTestsListIsEmpty").show();
                                        $("#pastTestsListIsEmpty").hide();
                                        $("#shown_if_not_empty").hide();
                                    } else {
                                        $("#activeTestsListIsEmpty").hide();
                                        $("#pastTestsListIsEmpty").hide();
                                        $("#shown_if_not_empty").show();
                                    }
                                };

                                var showTestsHistory = function () {
                                    $(".pastTests").hide();
                                    $(".activeTests").show();
                                    $("#unactiveTestsLink").addClass("navbar-inverse");
                                    $("#activeTestsLink").removeClass("navbar-inverse");
                                    if (listPastTestsLength == 0) {
                                        $("#pastTestsListIsEmpty").show();
                                        $("#activeTestsListIsEmpty").hide();
                                        $("#shown_if_not_empty").hide();
                                    } else {
                                        $("#activeTestsListIsEmpty").hide();
                                        $("#pastTestsListIsEmpty").hide();
                                        $("#shown_if_not_empty").show();
                                    }
                                };

                                $("#activeTestsLink").click(showActiveTests);
                                $("#unactiveTestsLink").click(showTestsHistory);

                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
