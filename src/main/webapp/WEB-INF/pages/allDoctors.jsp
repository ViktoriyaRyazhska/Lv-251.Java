<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<link href="<c:url value="/resources/css/search.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css"/>" rel="stylesheet">

<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap-datetimepicker.js"/>" charset="UTF-8"
        type="text/javascript"></script>
<div class="container">
    <p id="clinic" style="display: none"><spring:message code="messages.searchClinics"/></p>
    <p id="doctor" style="display: none"><spring:message code="messages.searchDoctors"/></p>
    <p id="docByspec" style="display: none"><spring:message code="messages.doctorsSearchBySpec"/></p>
    <div class="container">
        <div class="row" style="margin-top: 50px">
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">

                    <div class="navbar-form " style="width: 95%">
                        <div class="form-group" style="width: 90%">
                            <select class="selectpicker form-control" style="width:18% " id="selectDocOrClinic">
                                <option value="3"><spring:message code="messages.chooseSearch"/></option>
                                <option value="1"><spring:message code="messages.doctorsSearch"/></option>
                                <option value="2"><spring:message code="messages.doctorsSearchBySpec"/></option>
                            </select>

                            <div class="input-group "  style="width: 60%">
                                <span class="input-group-addon" id="sizing-addon1"  style="width: 8%"><i class="fa fa-ambulance change"
                                                                                      aria-hidden="true"
                                                                                      style="color: #226ed9"></i></span>
                                <input type="text" class="form-control " id="autocomplete"
                                       placeholder='' aria-describedby="sizing-addon1">
                            </div>
                            <input id="autocomplete-districts" type="text" class="form-control " style="width: 20%"
                                   placeholder='<spring:message code="messages.district"/>'>
                        </div>
                        <a href="${current}">
                            <button id="main-search-btn" class="btn btn-facebook"><spring:message
                                    code="messages.search"/></button>
                        </a>
                    </div>
                </div>
            </nav>
        </div>
    </div>
</div>
<div id="content" class="content">

    <script>

    </script>

    <%--Pagination--%>
    <%------------------------------------------------%>
    <div class="text-center">
        <ul class="pagination">
            <c:if test="${current>1}">
                <li id="previous" class="page-item"><a class="page-link" href="${current-1}">&laquo;</a></li>
            </c:if>
            <c:forEach begin="1" end="${numberChain}" varStatus="loop">
                <c:if test="${numberChain>1}">
                    <li id="current" class="${current == loop.index ? 'page-item active': 'page-item'}"><a
                            class="page-link"
                            href="/allDoctors/${loop.index}">${loop.index}</a>
                    </li>
                </c:if>
            </c:forEach>
            <c:if test="${current < numberChain}">
                <li id="next" class="page-item"><a class="page-link" href="${current+1}">&raquo;</a></li>
            </c:if>
        </ul>
    </div>
    <%------------------------------------------------%>

    <c:choose>
        <c:when test="${getDoctors.size()>0}">
            <div class="container">
                <c:forEach items="${getDoctors}" var="doctor">
                    <a href="doctors/${doctor.id}">
                        <div class="row row-content">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-xs-6 col-md-3">
                                        <a href="#" class="thumbnail">
                                            <img width="200" height="200" src="data:image/jpeg;base64,${doctor.photo}"
                                                 alt="...">
                                        </a>
                                    </div>

                                    <a href="/doctors/${doctor.id}" class="btn-link">

                                        <span class="doc-name">${doctor.firstname} ${doctor.lastname} ${doctor.middlename}</span>
                                    </a>

                                    <p><spring:message
                                            code="messages.specialization"/>:${doctor.specialization.name}</p>
                                    <hr>
                                    <p><spring:message code="messages.clinicName"/>:${doctor.clinic.clinic_name}</p>
                                    <button class="btn btn-facebook" style="margin-top: 10%;margin-left: 55%"
                                            data-toggle="modal" data-target="#modal_${doctor.id}">

                                        <spring:message code="messages.addAppointment"/>
                                    </button>
                                </div>

                            </div>
                        </div>
                    </a>

                    <!--Modal-->
                    <div id="modal_${doctor.id}" class="modal fade" role="dialog">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <div class="row">
                                        <div class="col-xs-6 col-md-9 col-lg-9">
                                            <div class="row">
                                                <div class="col-xs-6 col-md-3 col-lg-2">
                                                    <img width="200" height="200"
                                                         src="data:image/jpeg;base64,${doctor.photo}"
                                                         alt="...">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-6 col-md-3 col-lg-3">
                                            <div class="row">
                                                <h4 class="form-heading"><spring:message
                                                        code="messages.appointmentTo"/></h4>
                                            </div>
                                            <div class="row">
                                                <span class="doc-name">${doctor.lastname}</span>
                                            </div>
                                            <div class="row">
                                                <span class="doc-name">${doctor.firstname} ${doctor.middlename}</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-content">
                                <form action="${pageContext.request.contextPath}/user/addAppointment" method="post">

                                    <div class="form-group"
                                         style="text-align: center;  margin-bottom: 10pt; margin-top: 10pt">
                                        <div class="input-append date form_datetime" id="date-div-${doctor.id}">
                                            <label for="first-date" style="margin-left: 5pt">
                                                <spring:message code="messages.date"/>
                                            </label>

                                            <input type="text" value="" readonly id="first-date" name="datetime">
                                            <span class="add-on"><i class="icon-remove fa fa-times"></i></span>
                                            <span class="add-on"><i class="icon-calendar fa fa-calendar"></i></span>
                                        </div>


                                        <script type="text/javascript" charset="UTF-8">

                                            $("#date-div-${doctor.id}").datetimepicker({
                                                language: '${pageContext.response.locale}',
                                                format: "dd/mm/yyyy - hh:ii",
                                                autoclose: true,
                                                minuteStep: 15,
                                                startDate: new Date(),
                                                daysOfWeekDisabled: [0, 6],
                                                hoursDisabled: [1, 2, 3, 4, 5, 6, 22, 23, 0],
                                                onRenderMinute: function (date) {

                                                    var dates = [];
                                                    <c:choose>
                                                        <c:when test="${docApps.size()>0}">
                                                            <c:forEach items="${docApps}" var="apointments">
                                                                <c:if test="${apointments.doctor == doctor.id}">
                                                                    dd = new Date("${apointments.appointmentDate}");
                                                                    dates.push(dd);
                                                                </c:if>
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

                                        </script>

                                    </div>
                                    <input name="doctorId" value="${doctor.id}" style="display: none">
                                    <input name="current" value="${current}" style="display: none">
                                    <div style="text-align: center">
                                        <button class="btn btn-primary" style="width: 50%; margin: 5pt auto;">
                                            <spring:message code="messages.approve"/>
                                        </button>
                                    </div>
                                    <h5 style="color: red; text-align: center" id="wrong-date"></h5>
                                </form>
                            </div>
                        </div>
                    </div>

                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="container">
                <div class="row row-content">
                    <div class="container-fluid">
                        There are no doctor in database
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

<%--Pagination--%>
<%------------------------------------------------%>
<div class="text-center">
    <ul class="pagination">
        <c:if test="${current>1}">
            <li id="previous" class="page-item"><a class="page-link" href="${current-1}">&laquo;</a></li>
        </c:if>
        <c:forEach begin="1" end="${numberChain}" varStatus="loop">
            <c:if test="${numberChain>1}">
                <li id="current" class="${current == loop.index ? 'page-item active': 'page-item'}"><a
                        class="page-link"
                        href="/allDoctors/${loop.index}">${loop.index}</a>
                </li>
            </c:if>
        </c:forEach>
        <c:if test="${current < numberChain}">
            <li id="next" class="page-item"><a class="page-link" href="${current+1}">&raquo;</a></li>
        </c:if>
    </ul>
</div>
<%------------------------------------------------%>


<c:if test="${flag}">
    <c:set var="docId" value="${doc}"/>
    <script>
        document.getElementById('wrong-date').innerHTML = '<spring:message code="messages.invalidDate"/>';
        jQuery(window).load(function () {
            jQuery('#modal_${docId}').modal('show')
        });
    </script>
</c:if>