<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<script src="<c:url value="/resources/js/doctorsPatients.js"/>"></script>

<body onLoad="init()">



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
                                <strong><spring:message code="messages.testAdded"/></strong>
                            </div>
                        </c:when>
                        <c:when test="${success == 'failed'}">
                            <div class="alert alert-danger">
                                <strong><spring:message code="messages.testAddFailed"/></strong>
                            </div>
                        </c:when>
                    </c:choose>

                    <div class="row content">
                        <div class="col-sm-9" style="width: 100%">
                            <div class="well mcard_content" id="dynamic-list">

                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script id="template" type="text/x-jquery-tmpl">
    <div>
        <div class="col-sm-6 appointmentWrapper">
            <div class="appointmentFloatContainer">
                <div class=row>
                    <button class="btn btn-info" style="margin-top: 15px" data-toggle="modal" data-target="#modal_\${id}">
                        <spring:message code="messages.addTest"/>
                    </button>
                </div>
                <div class=row>
                    <button class="btn btn-info" style="margin-top: 15px">
                        <a href="/doctor/patient/\${id}" class="button">
                            <spring:message code="messages.checkTests"/>
                        </a>
                    </button>
                </div>
            </div>

            <div class="medical-card">
                <div class="media">
                    <div class="media-left">
                        <img class="media-object img-circle profile-img"
                            src="<c:url value="/resources/img/User_Default.png"/>">
                        </div>
                    <div class="media-body">
                        <div>
                            <div >
                                <h3 class="media-heading" id="patientName">
                                 \${Name}
                                 </h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="modal_\${id}" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="col-lg-3">
                        <h3 class="form-heading">
                            <spring:message code="messages.addTest"/>
                        </h3>
                    </div>
                    <div class="col-lg-9">
                        <button class="close" type="button" data-dismiss="modal">
                            <i class="fa fa-close"></i>
                        </button>
                    </div>
                </div>
                <form action="${pageContext.request.contextPath}/doctor/patients" method="post">
                    <div class="form-group ${error != null ? 'has-error' : ''}">
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="control-label" for="email">
                                    <spring:message code="messages.Description"/>
                                </label>
                                <textarea id="description" name="description" type="text" class="form-control" rows="5"
                                    placeholder="<spring:message code="messages.Description"/>"></textarea>
                                <label class="control-label" for="test">
                                    <spring:message code="messages.testType"/>
                                </label>
                                <select id="test-\${id}" name="test" class="form-control select">
                                </select>
                                <label class="control-label" for="startDate">
                                    <spring:message code="messages.startDate"/>
                                </label>
                                <input id="startDate" name="startDate" type="date" class="form-control">
                                <label class="control-label" for="endDate">
                                    <spring:message code="messages.endDate"/>
                                </label>
                                <input id="endDate" name="endDate" type="date" class="form-control">

                                <input name="userId" value="\${id}" style="display: none">
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



</script>

<script id="option-template" type="text/x-jquery-tmpl">

<option id=option-\${id} value="\${Name}">\${Name}</option>

</script>
</body>
