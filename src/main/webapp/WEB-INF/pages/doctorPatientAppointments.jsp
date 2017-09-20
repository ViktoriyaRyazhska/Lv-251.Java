<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
                    <div class="row content">
                        <br>
                        <div class="col-sm-9" style="width: 100%">
                            <div id="shown_if_not_empty" class="well mcard_content">

                                <c:choose>
                                    <c:when test="${listAppointments.size()>0}">
                                        <c:forEach items="${listAppointments}" var="appointment" varStatus="loop">
                                            <fmt:formatDate var="aDate" pattern='dd-MM-yyyy HH:mm'
                                                            value='${appointment.appointmentDate}'/>


                                            <div>
                                                <div class="col-sm-6 appointmentWrapper">
                                                    <c:out value="${date.time}"/>
                                                    <br>
                                                    <c:out value="${datePlus.time}"/>
                                                    <br>
                                                    <c:out value="${appointment.appointmentDate.time}"/>
                                                    <br>

                                                    <c:out value="${appointment.appointmentDate.before(date)}"/>
                                                    <br>
                                                    <c:out value="${appointment.appointmentDate.after(datePlus)}"/>

                                                    <c:if test="${appointment.doctor.email == pageContext.request.userPrincipal.name
                                                    && appointment.appointmentDate.before(date)
                                                     && datePlus.before(appointment.appointmentDate)}">
                                                        <div class="appointmentFloatContainer">
                                                            <div class=row>
                                                                <button class="btn btn-info"
                                                                        style="margin-top: 15px;
                                                                        margin-right: 15px;"
                                                                        data-toggle="modal"
                                                                        data-target="#modal_${appointment.id}">
                                                                    <spring:message code="messages.editAppointment"/>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <div class="medical-card">
                                                        <div class="media">
                                                            <div class="media-left">
                                                                <img class="media-object img-circle profile-img"
                                                                     src="data:image/jpeg;base64,${appointment.doctor.photo}">
                                                            </div>
                                                            <div class="media-body">
                                                                <div>
                                                                    <div>
                                                                        <h3 class="media-heading">
                                                                            <c:out value="${appointment.doctor.firstname}
                                                                                            ${appointment.doctor.lastname}"/>
                                                                        </h3>
                                                                    </div>
                                                                </div>

                                                                <div class="specialization"><c:out
                                                                        value="${appointment.doctor.specialization.name}"/></div>
                                                                <div class="diagnosis"><c:out
                                                                        value="${appointment.description}"/></div>
                                                                <div class="diagnosis"><c:out value="${aDate}"/></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div id="modal_${appointment.id}" class="modal fade" role="dialog">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <div class="col-lg-9">
                                                                <h3 class="form-heading">
                                                                    <spring:message code="messages.editTest"/>
                                                                </h3>
                                                            </div>
                                                            <div class="col-lg-3">
                                                                <button class="close" type="button" data-dismiss="modal">
                                                                    <i class="fa fa-close"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                        <form action="${pageContext.request.contextPath}/doctor/patient/appointment/${userId}" method="post">
                                                            <div class="form-group">
                                                                <div class="modal-body">
                                                                    <div class="form-group">
                                                                        <label class="control-label" for="description-${appointment.id}">
                                                                            <spring:message code="messages.Description"/>
                                                                        </label>
                                                                        <textarea id="description-area-${test.id}"
                                                                                  name="description"
                                                                                  class="form-control"
                                                                                  rows="5"
                                                                                  placeholder="<spring:message code="messages.Description"/>"
                                                                        >${appointment.description}</textarea>


                                                                        <input name="appointmentId" value="${appointment.id}" style="display: none">
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
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="well mcard_content">
                                            <div class="col-sm-6" style="width: 100%; padding-left: 0; padding-right: 0;">
                                                <div class="medical-card alert alert-info">
                                                    <div class="media">
                                                        <div class="media-body">
                                                            <h3 class="media-heading" style="text-align: center"><spring:message code="messages.noUserAppointments"/></h3>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
