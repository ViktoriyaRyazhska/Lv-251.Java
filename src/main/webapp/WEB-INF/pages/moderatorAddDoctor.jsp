<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div>
        <div class="container" style="width: 30%; float: left">
            <div class="row row-content">
                <div class="list-group doc-menu">
                    <a href="<c:url value="/moderator/cabinet"/>" class="list-group-item ">
                        <spring:message code="messages.clinic"/>
                    </a>
                    <a href="<c:url value="/moderator/cabinet/doctors"/>" class=" list-group-item ">
                        <spring:message code="messages.doctors"/><span class="badge">${doctors.size()}</span>
                    </a>
                    <a href="<c:url value="/moderator/cabinet/add/doctor"/>" class="list-group-item navbar-inverse">
                        <spring:message code="messages.addDoctors"/>
                    </a>
                    <a href="<c:url value="/moderator/cabinet/make/doctor"/>" class="list-group-item ">
                        <spring:message code="messages.makeDoctor" />
                    </a>

                </div>
            </div>
        </div>
    </div>
    <div>
        <div class="container" style="width: 70%; float: right">
            <div class="row row-content "style="border: #58d9b9 solid 1px ">

                <div class="col-md-3 col-md-offset-1">
                    <div class="text-center">
                    </div>
                </div>


                <div  class="col-md-7 col-md-offset-4" >
                    <form:form action="/moderator/add/doctor" method="post" modelAttribute="doctorForm"
                               enctype="multipart/form-data"  onsubmit="return Validate(this);">
                        <div class="form-group">
                            <label class="col-lg-3 control-label"><spring:message
                                    code="messages.userFirstname"/>:</label>
                            <div class="col-lg-7">
                                <form:errors path="firstName"  cssClass="text-danger"/>
                                <div style="color: #bb1219 ;display: none" id="errorName">Cant be empty</div>
                                <form:input id="firstname" name="firstName" type="text" class="form-control" path="firstName" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label"> <spring:message
                                    code="messages.userLastname"/>:</label>
                            <div class="col-lg-7">
                                <form:errors path="lastName"  cssClass="text-danger"/>
                                <div style="color: #bb1219 ;display: none" id="errorLastName">Can"t be empty</div>
                                <form:input id="lastname" type="text" class="form-control" path="lastName"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label"><spring:message code="messages.userEmail"/>:</label>
                            <div class="col-lg-7">
                                <form:errors path="email"  cssClass="text-danger"/>
                                <div style="color: #bb1219 ;display: none" id="errorEmail">Invalid email</div>
                                <form:input id="docEmail" type="text" class="form-control" path="email" disabled="false"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label"><spring:message code="messages.password"/>:</label>
                            <div class="col-lg-7">
                                <form:errors path="password"  cssClass="text-danger"/>
                                <div style="color: #bb1219 ;display: none" id="errorPassword">Cant be empty and be less then 8 symbols</div>
                                <form:input type="password" id="pass" class="form-control d" path="password"/>
                            </div>
                        </div>

                            <div class="form-group" id="wrapper">
                            <label class="col-lg-3 control-label"><spring:message
                                    code="messages.confirmPassword"/>:</label>
                            <div class="col-lg-7">
                                <form:errors path="matchingPassword"  cssClass="text-danger"/>
                                <div style="color: #bb1219 ;display: none" id="errorMatching">Password dont match</div>
                                <form:input type="password" id="passMatch" class="form-control" path="matchingPassword"/>
                                <div id="signDiv">
                                    <div style="z-index: 0;" id="sign">
                                        <i id="signMatch"></i>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-lg-3 control-label"><spring:message
                                    code="messages.specialization"/>:</label>
                            <div class="col-lg-7">
                                <form:errors path="specialization"  cssClass="text-danger"/>
                                <div style="color: #bb1219 ;display: none" id="errorSpec">Cant be empty</div>
                                <form:input  type="text" class="form-control" id="autocomplete-spec"
                                            path="specialization"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label"><spring:message code="messages.clinicName"/>:</label>
                            <div class="col-lg-7">
                                <form:errors path="clinic"/>
                                <div style="color: #bb1219 ;display: none" id="errorClinicName">Can"t be empty</div>
                                <form:input id="clinicName" type="text" value="${moderator.clinic.clinic_name}" class="form-control"
                                            path="clinic"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label"><spring:message code="messages.Description"/>:</label>
                            <div class="col-lg-7">
                                <form:errors path="description"/>
                                <div style="color: #bb1219 ;display: none" id="errorDescription">Can"t be empty</div>
                                <form:input type="text" class="form-control" id="description" path="description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-3 control-label"><spring:message code="messages.photo"/>:</label>
                            <div class="col-lg-7">
                                <form:errors path="multipartFile"/>
                                <div style="color: #bb1219 ;display: none" id="errorFile"> More then 100Kb or Invalid Format</div>
                                <form:input type="file" class="form-control" id="file" onchange="validPhoto()" path="multipartFile"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"></label>
                            <div class="col-md-7">
                                <form:button class="btn btn-clinic" id ="btnLoadFile"><spring:message
                                        code="messages.saveChanges" /></form:button>
                                <span></span>
                                <input type="reset" class="btn btn-github"
                                       value="<spring:message code="messages.cancel"/>"/>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>