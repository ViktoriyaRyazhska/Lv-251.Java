<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<link href="<c:url value="/resources/css/profile.css"/>" rel="stylesheet">

<div class="container">
    <div>
        <div class="container" style="width: 30%; float: left">
            <div class="row row-content">
                <div class="list-group doc-menu">
                    <a href="<c:url value="/user/cabinet"/>" class="navbar-inverse list-group-item">
                        <spring:message code="messages.profile"/>
                    </a>
                    <a href="#" class="list-group-item">
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
                <hr>
                <div class="row">
                    <!-- left column -->
                    <form:form method="POST" modelAttribute="personalInfoDTO" enctype="multipart/form-data" action="/user/cabinet">
                    <div class="col-md-3 col-md-offset-1">
                        <div class="text-center">
                            <img src="data:image/jpeg;base64,${photo}" class="avatar img-circle" alt="avatar"
                                 width="100"
                                 height="100">
                            <h6>Upload a different photo...</h6>
                            <div class="fileupload fileupload-new" data-provides="fileupload">
                                <span class="btn btn-primary btn-file"><span class="fileupload-new">Select file</span>
                                <span class="fileupload-exists">Change</span>         <form:input  path="photo" type="file" /></span>
                                <span class="fileupload-preview"></span>
                                <a href="#" class="close fileupload-exists" data-dismiss="fileupload" style="float: none">?</a>
                            </div>
                        </div>
                    </div>


                    <!-- edit form column -->
                    <div class="col-md-7 personal-info">
                        <div class="alert alert-info alert-dismissable">
                            <a class="panel-close close" data-dismiss="alert">×</a>
                            <i class="fa fa-coffee"></i>
                            This is an <strong>.alert</strong>. Use this to show important messages to the user.
                        </div>
                        <h3><spring:message code="messages.personalInfo"/></h3>

                            <div class="form-group">
                                <label class="col-lg-3 control-label"><spring:message
                                        code="messages.userFirstname"/>:</label>
                                <div class="col-lg-7">
                                    <form:input type="text" class="form-control" path="firstname"/>
                                    <form:errors path="firstname" cssClass="text-danger"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"> <spring:message
                                        code="messages.userLastname"/>:</label>
                                <div class="col-lg-7">
                                    <form:input type="text" class="form-control" path="lastname"/>
                                    <form:errors path="lastname" cssClass="text-danger"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"><spring:message
                                        code="messages.userEmail"/>:</label>
                                <div class="col-lg-7">
                                    <form:input type="text" class="form-control" path="email" disabled="false"
                                    />
                                    <form:errors path="email" cssClass="text-danger"/>
                                </div>
                            </div>

                            <h3><spring:message code="messages.addressInfo"/></h3>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"><spring:message
                                        code="messages.userAddress"/>:</label>
                                <div class="col-lg-7">
                                    <form:input type="text" class="form-control d" path="address"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"><spring:message
                                        code="messages.userCity"/>:</label>
                                <div class="col-lg-7">
                                    <form:input type="text" class="form-control" path="city"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"><spring:message
                                        code="messages.userZipcode"/>:</label>
                                <div class="col-lg-7">
                                    <form:input type="text" class="form-control" path="zipCode"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"><spring:message
                                        code="messages.userFirstPhone"/>:</label>
                                <div class="col-lg-7">
                                    <form:input type="text" class="form-control" path="firstPhone"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"><spring:message
                                        code="messages.userSecondPhone"/>:</label>
                                <div class="col-lg-7">
                                    <form:input type="text" class="form-control" path="secondPhone"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"><spring:message
                                        code="messages.userThirdPhone"/>:</label>
                                <div class="col-lg-7">
                                    <form:input type="text" class="form-control" path="thirdPhone"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label"></label>
                                <div class="col-md-7">
                                    <input type="submit" class="btn btn-primary"
                                           value="<spring:message code="messages.saveChanges"/>">
                                    <span></span>
                                    <input type="reset" class="btn btn-default"
                                           value="<spring:message code="messages.cancel"/>">
                                </div>
                            </div>

                        <hr>
                        <h3>Change Password</h3>
                        <div class="form-group">
                            <label class="col-md-3 control-label"><spring:message
                                    code="messages.userPassword"/>:</label>
                            <div class="col-md-7">
                                <input class="form-control" type="password" value="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"><spring:message
                                    code="messages.userConfirmPassword"/>:</label>
                            <div class="col-md-7">
                                <input class="form-control" type="password" value="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"></label>
                            <div class="col-md-7">
                                <%--<input type="submit" class="btn btn-primary"--%>
                                       <%--value="<spring:message code="messages.saveChanges"/>"/>--%>
                                <span></span>
                                <input type="reset" class="btn btn-default"
                                       value="<spring:message code="messages.cancel"/>"/>
                            </div>
                        </div>
                    </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>


