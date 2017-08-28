<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <div >
        <div class="container" style="width: 30%; float: left">
            <div class="row row-content">
                <div class="list-group doc-menu">
                    <a href="/moderator/cabinet" class="list-group-item ">
                        <spring:message code="messages.clinic" />
                    </a>
                    <a href="/moderator/cabinet/doctors" class=" list-group-item navbar-inverse">
                        <spring:message code="messages.doctors" /><span class="badge">${doctors.size()}</span>
                    </a>
                    <a href="/moderator/cabinet/add/doctor" class="list-group-item">
                        <spring:message code="messages.addDoctors" />
                    </a>
                    <a href="/moderator/cabinet/make/doctor" class="list-group-item ">
                        <spring:message code="messages.makeDoctor" />
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div>
    <div>
        <div class="container" style="width: 70%; float: right">
            <div class="row row-content">
                <h3 class="text-center">${moderator.clinic.clinic_name}</h3>
                <hr>
                <div class="row">
                    <c:choose>
                        <c:when test="${doctors.size()>0}">
                            <c:forEach items="${doctors}" var="doctor">
                                <div class="col-xs-6 col-sm-6 col-md-10 col-lg-10 col-md-offset-1 ">
                                    <div class="row">
                                        <div class="col-xs-6 col-md-3 col-lg-2">
                                            <img src="data:image/jpeg;base64,${doctor.photo}" width="100" class="avatar img-circle" alt="avatar">
                                        </div>
                                        <div class="col-md-1 col-lg-1"></div>
                                        <div class="col-xs-6 col-md-8 col-lg-9">
                                            <div class="row">
                                                <div> ${doctor.firstname}  ${doctor.lastname}</div>
                                                <p><spring:message code="messages.specialization"/>: ${doctor.specialization.name}</p>
                                            </div>
                                            <!-- Large modal -->
                                            <div class="modal fade bs-example-modal-lg" id="modal_${doctor.id}"  tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
                                                <div class="modal-dialog modal-lg" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" id="myModalLabel">Are you sure you want Delete?</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div> ${doctor.firstname}  ${doctor.lastname}</div>
                                                            <p><spring:message code="messages.specialization"/>: ${doctor.specialization.name}</p>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <a href="/moderator/cabinet/doctors"> <button type="button" class="btn btn-default" >No</button></a>
                                                            <a href="/moderator/cabinet/doctors/delete/${doctor.id}"><button  class="btn btn-github" ><spring:message code="messages.delete"/></button ></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <button data-toggle="modal"  data-target="#modal_${doctor.id}" class="btn btn-github" ><spring:message code="messages.delete"/></button >
                                            </div>
                                            <hr>
                                        </div>
                                    </div>


                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p></p>
                        </c:otherwise>
                    </c:choose>
                </div>

            </div>

        </div>
    </div>
</div>
</div>