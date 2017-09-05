<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div class="container">
    <div>
        <div class="container" style="width: 30%; float: left">
            <div class="row row-content">
                <div class="list-group doc-menu">
                    <a href="<c:url value="/user/cabinet"/>" class="list-group-item">
                        <spring:message code="messages.profile"/>
                    </a>
                    <a href="<c:url value="/user/doctors"/>" class="list-group-item navbar-inverse ">
                        <spring:message code="messages.doctors"/>
                    </a>
                    <a href="<c:url value="/user/medicalcard"/>" class="list-group-item">
                        <spring:message code="messages.medicalCard"/>
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
                                <c:set var="listPastAppointmentsLength" value="0"/>
                                <c:set var="listPendingAppointmentsLength" value="0"/>
                                <c:forEach items="${doctors}" var="doctor" varStatus="loop">
                                    <c:set var="listPastAppointmentsLength" value="${listPastAppointmentsLength + 1}"/>
                                    <c:set var="cssClass" value="label label-warning"/>
                                    <spring:message code="messages.appointmentIsNotApproved" var="appointmentIsNotApproved"/>
                                    <c:set var="appointmentSatatus" value="${appointmentIsNotApproved}"/>
                                    <div>
                                        <div class="col-sm-6 appointmentWrapper">
                                            <c:if test="${!doctor.responded}">
                                                <div class="appointmentFloatContainer">
                                                   <button class="btn btn-info" style="margin-top: 15px"
                                                           data-toggle="modal" data-target="#modal_${doctor.id}">
                                                       <spring:message code="messages.respond"/>
                                                   </button>
                                                </div>
                                            </c:if>
                                            <div class="medical-card">
                                                <div class="media">
                                                    <div class="media-left">
                                                        <img class="media-object img-circle profile-img" src="data:image/jpeg;base64,${doctor.photo}">
                                                    </div>
                                                    <div class="media-body">
                                                        <div>
                                                            <div >
                                                                <h3 class="media-heading">
                                                                    <c:out value="${doctor.firstname} ${doctor.lastname}"/>
                                                                </h3>
                                                            </div>
                                                        </div>

                                                        <div class="specialization"><c:out value="${doctor.specialization}"/></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                <div id="modal_${doctor.id}" class="modal fade" role="dialog">


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
                                                    <div class="modal-body">
                                                        <div class="form-group">
                                                            <label class="control-label" for="email">
                                                                <spring:message code="messages.Description"/>
                                                            </label>
                                                            <textarea id="email" name="description" type="text" class="form-control"
                                                                   placeholder="<spring:message code="messages.Description"/>"
                                                                      autofocus="" rows="5"></textarea>
                                                            <br>
                                                            <label class="control-label" for="raiting">
                                                                <spring:message code="messages.raiting"/>
                                                            </label>
                                                            <select id="raiting" name="raiting" class="form-control">
                                                                <option value="1">1</option>
                                                                <option value="2">2</option>
                                                                <option value="3">3</option>
                                                                <option value="4">4</option>
                                                                <option value="5">5</option>
                                                            </select>
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
                                </c:forEach>
                            </div>
                            <div id="appointmentsHistoryListIsEmpty">
                                <div class="well mcard_content">
                                    <div class="col-sm-6" style="width: 100%; padding-left: 0; padding-right: 0;">
                                        <div class="medical-card alert alert-info">
                                            <div class="media">
                                                <div class="media-body">
                                                    <h3 class="media-heading" style="text-align: center"><spring:message code="messages.noUserDoctors"/></h3>
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

                                var showAppointmentHistory = function () {
                                    $(".appointmentsHistory").show();
                                    if (listPastAppointmentsLength == 0) {
                                        $("#appointmentsHistoryListIsEmpty").show();
                                        $("#shown_if_not_empty").hide();
                                    } else {
                                        $("#appointmentsHistoryListIsEmpty").hide();
                                        $("#shown_if_not_empty").show();
                                    }
                                };

                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
