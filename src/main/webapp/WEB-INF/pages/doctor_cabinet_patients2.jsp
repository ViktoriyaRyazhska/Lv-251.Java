<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<script src="<c:url value="/resources/js/doctorsPatients.js"/>"></script>

<body  onLoad="init()">


<div class="container">

    <div>
        <div class="container" style="width: 30%; float: left">
            <div class="row row-content">
                <div class="list-group doc-menu">
                    <a href="<c:url value="/doctor/сabinet"/>" class="list-group-item">
                        <spring:message code="messages.schedule"/>
                    </a>
                    <a href="#" class="list-group-item navbar-inverse ">
                        <spring:message code="messages.patients"/>
                    </a>
                </div>
            </div>
        </div>

        <div class="container" style="width: 70%; float: right">
            <div class="row row-content">

                <div class="container-fluid">
                    <div class="row content">
                        <br>
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
                <%--<button class="btn btn-info" style="margin-top: 15px"--%>
                <%--data-toggle="modal" data-target="#modal_\${id}">--%>
                <%--<spring:message code="messages.respond"/>--%>
                <%--</button>--%>
                </div>

            <div class="medical-card">
                <div class="media">
                    <div class="media-left">
                        <img class="media-object img-circle profile-img" src="<c:url value="/resources/img/User_Default.png"/>">
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
                                                    <h3 class="form-heading"><spring:message code="messages.respond"/></h3>
                                                </div>
                                                <div class="col-lg-9">
                                                    <button class="close" type="button" data-dismiss="modal">
                                                        <i class="fa fa-close"></i>
                                                    </button>
                                                </div>
                                            </div>

                                            <form action="${pageContext.request.contextPath}/user/addRespond" method="post">
                                                <div class="form-group ${error != null ? 'has-error' : ''}">
                                                        <%--<span>${message}</span>--%>
                                                    <div class="modal-body">
                                                        <div class="form-group">
                                                            <label class="control-label" for="email">
                                                                <spring:message code="messages.Description"/>
                                                            </label>
                                                            <input id="email" name="description" type="text" class="form-control"
                                                                   placeholder="<spring:message code="messages.Description"/>"
                                                                   autofocus=""/>
                                                            <label class="control-label" for="raiting">
                                                                <spring:message code="messages.raiting"/>
                                                            </label>
                                                            <input id="raiting" name="raiting" type="number" class="form-control"
                                                                   placeholder="<spring:message code="messages.raiting"/>"
                                                                   autofocus=""/>
                                                            <input name="doctorId" value="${doctor.id}" style="display: none">
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

</body>
