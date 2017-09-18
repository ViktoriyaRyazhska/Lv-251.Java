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
                    <a href="<c:url value="/doctor/сabinet"/>" class="list-group-item">
                        <spring:message code="messages.schedule"/>
                    </a>
                    <a href="<c:url value="/doctor/patients"/>" class="list-group-item navbar-inverse ">
                        <spring:message code="messages.patients"/>
                    </a>
                </div>
            </div>
        </div>

        <div class="container" style="width: 70%; float: right">
            <div class="row row-content">

                <div class="container-fluid">
                    <c:choose>
                        <c:when test="${success == 'success'}">
                            <div class="alert alert-success">
                                <strong><spring:message code="messages.testEdited"/></strong>
                            </div>
                        </c:when>
                        <c:when test="${success == 'failed'}">
                            <div class="alert alert-danger">
                                <strong><spring:message code="messages.testEditFailed"/></strong>
                            </div>
                        </c:when>
                    </c:choose>
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
                                    <fmt:formatDate var="startDate" pattern = 'dd-MM-yyyy' value='${test.startDdate}'/>
                                    <fmt:formatDate var="endDate" pattern = 'dd-MM-yyyy' value='${test.endDdate}'/>
                                    <c:choose>
                                        <c:when test="${test.endDdate.after(date)}">
                                            <c:set var="showTestsClass" value="pastTests"/>
                                            <c:set var="listActiveTestsLength" value="${listActiveTestsLength + 1}"/>
                                        </c:when>
                                        <c:when test="${test.endDdate.before(date)}">
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
                                                                <div class="col-xs-10">
                                                                    <h3 class="media-heading">
                                                                        <c:out value="${test.test}"/>
                                                                    </h3>
                                                                    <p>
                                                                        <c:out value="${test.description}"/>
                                                                    </p>
                                                                </div>
                                                                <div class="col-xs-2">
                                                                    <div class="row">
                                                                        <h5><c:out value="${startDate}"/></h5>
                                                                    </div>
                                                                    <div class="row">
                                                                        <h5><c:out value="${endDate}"/></h5>
                                                                    </div>
                                                                    <div class="row">
                                                                        <button class="btn btn-primary"
                                                                                style="margin-top: 15px;
                                                                                margin-right: 15px;
                                                                                float: right"
                                                                                data-toggle="modal"
                                                                                data-target="#modal_${test.id}">
                                                                            <spring:message code="messages.editTest"/>
                                                                        </button>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>


                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                        <%--------------------------------------------------------------------------------%>


                                    <div id="modal_${test.id}" class="modal fade" role="dialog">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <div class="col-lg-9">
                                                        <h3 class="form-heading">
                                                            <spring:message code="messages.addTest"/>
                                                        </h3>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <button class="close" type="button" data-dismiss="modal">
                                                            <i class="fa fa-close"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                                <form action="${pageContext.request.contextPath}/doctor/patient/${userId}" method="post">
                                                    <div class="form-group ${error != null ? 'has-error' : ''}">
                                                        <div class="modal-body">
                                                            <div class="form-group">
                                                                <label class="control-label" for="description-${test.id}">
                                                                    <spring:message code="messages.Description"/>
                                                                </label>
                                                                <textarea id="description-area-${test.id}"
                                                                          name="description"
                                                                          class="form-control"
                                                                          rows="5"
                                                                          placeholder="<spring:message code="messages.Description"/>"
                                                                >${test.description}</textarea>
                                                                <label class="control-label" for="test-${test.id}">
                                                                    <spring:message code="messages.testType"/>
                                                                </label>
                                                                <select id="test-${test.id}" name="test" class="form-control select">
                                                                    <c:forEach items="${testsNames}" var="testName">
                                                                        <option value="${testName}">${testName}</option>
                                                                    </c:forEach>
                                                                </select>
                                                                <label class="control-label" for="startDate-${test.id}">
                                                                    <spring:message code="messages.startDate"/>
                                                                </label>
                                                                <fmt:formatDate
                                                                        var="startEditDate"
                                                                        pattern = 'yyyy-MM-dd'
                                                                        value='${test.startDdate}'/>
                                                                <input
                                                                        id="startDate-${test.id}"
                                                                        name="startDate"
                                                                        type="date"
                                                                        class="form-control"
                                                                        value="<c:out value="${startEditDate}"/>">
                                                                <label class="control-label" for="endDate-${test.id}">
                                                                    <spring:message code="messages.endDate"/>
                                                                </label>
                                                                <fmt:formatDate
                                                                        var="endEditDate"
                                                                        pattern = 'yyyy-MM-dd'
                                                                        value='${test.endDdate}'/>
                                                                <input
                                                                        id="endDate-${test.id}"
                                                                        name="endDate"
                                                                        type="date"
                                                                        class="form-control"
                                                                        value="<c:out value="${endEditDate}"/>">

                                                                <input name="testId" value="${test.id}" style="display: none">
                                                                <input name="userId" value="${userId}" style="display: none">
                                                            </div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button class="btn btn-lg btn-primary btn-block">
                                                                <spring:message code="messages.send"/>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                        <%--------------------------------------------------------------------------------%>
                                </c:forEach>
                            </div>
                            <div id="activeTestsListIsEmpty">
                                <div class="well mcard_content">
                                    <div class="col-sm-6" style="width: 100%; padding-left: 0; padding-right: 0;">
                                        <div class="medical-card alert alert-info">
                                            <div class="media">
                                                <div class="media-body">
                                                    <h3 class="media-heading" style="text-align: center">
                                                        <spring:message code="messages.noActiveTestsForPatient"/>
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
                                                        <spring:message code="messages.noPastTestsForPatient"/>
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
