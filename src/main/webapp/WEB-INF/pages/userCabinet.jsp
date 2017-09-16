<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <a href="<c:url value="/user/doctors"/>" class="list-group-item">
                        <spring:message code="messages.doctors"/>
                    </a>
                    <a href="<c:url value="/user/medicalcard"/>" class="list-group-item">
                        <spring:message code="messages.medicalCard"/>
                    </a>
                    <a href="<c:url value="/user/tests"/>" class="list-group-item">
                        <spring:message code="messages.tests"/>
                    </a>
                </div>
            </div>
        </div>

        <div class="chatbox chatbox--tray chatbox--empty g ">
            <div class="chatbox__title">
                <h5><a href="#">Customer Service</a></h5>
                <button class="chatbox__title__tray" >
                    <span></span>
                </button>
            </div>
            <div class="chatbox__body">
                <div class="chatbox__body__message chatbox__body__message--right">
                    <div class="messageChat" style="border-radius: 3px">
                        <div id="response"></div>
                        <c:forEach items="${messages}" var="message">
                            <fmt:formatDate var="aDate" pattern = 'dd-MM-yyyy HH:mm' value='${message.date}'/>
                            <c:choose>
                                <c:when test="${personalInfoDTO.firstname!=message.from.firstname&&personalInfoDTO.lastname!=message.from.lastname}">

                                    <p style="background-color: #6bd9aa"><span >${aDate}  <br>  ${message.from.firstname} ${message.from.lastname}</span><br> ${message.text} </p>
                                    <br />
                                </c:when>
                                <c:otherwise>
                                    <p style="background-color: #e1e1e1"><span >${aDate} <br>   ${message.from.firstname} ${message.from.lastname}</span><br> ${message.text} </p>
                                    <br />
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>


                    </div>



                </div>
            </div>
            <div id="conversationDiv">
                <textarea class="chatbox__message" id="text" placeholder="Write something interesting"></textarea>
                <button class="btn btn-clinic form-control" onclick="sendMessage()" >Send</button>
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
                                    <form:input type="text" class="form-control" path="address"/>
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
                    </div>
                    </form:form>
                    <form:form method="POST" modelAttribute="passwordDTO" action="/user/changePassword">
                        <div class="col-md-7 col-md-offset-4">
                        <hr>
                        <h3><spring:message code="messages.changePassword"/></h3>
                            <div class="form-group">
                                <label class="col-md-3 control-label"><spring:message
                                        code="messages.userNewPassword"/>:</label>
                                <div class="col-md-7">
                                    <form:input id="pass" type="password" class="form-control" path="password"/>
                                    <form:errors path="password" cssClass="text-danger"/>
                                </div>
                            </div>
                            <div class="form-group" id="wrapper">
                                <label class="col-md-3 control-label"><spring:message
                                        code="messages.userConfirmPassword"/>:</label>
                                <div class="col-md-7">
                                    <form:input id="passMatch" type="password" class="form-control" path="matchingPassword"/>
                                    <form:errors path="matchingPassword" cssClass="text-danger"/>
                                    <div id="signDiv">
                                        <div style="z-index: 0;" id="sign">
                                            <i id="signMatch"></i>
                                        </div>
                                    </div>
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
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>


