<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">
    <div >
        <div class="container" style="width: 30%; float: left">
            <div class="row row-content">
                <div class="list-group doc-menu">
                    <a href="<c:url value="/moderator/cabinet"/>" class="list-group-item navbar-inverse">
                        <spring:message code="messages.clinic" />
                    </a>
                    <a href="<c:url value="/moderator/cabinet/doctors"/>" class=" list-group-item ">
                        <spring:message code="messages.doctors" /><span class="badge">${doctors.size()}</span>
                    </a>
                    <a href="<c:url value="/moderator/cabinet/add/doctor"/>" class="list-group-item ">
                        <spring:message code="messages.addDoctors" />
                    </a>
                    <a href="<c:url value="/moderator/cabinet/make/doctor"/>" class="list-group-item ">
                        <spring:message code="messages.makeDoctor" />
                    </a>


                </div>
                <div></div>
            </div>
        </div>
    </div>
    <div>
        <div class="chatbox chatbox--tray chatbox--empty g ">
            <div class="chatbox__title">
                <h5><a href="#">Customer Service</a></h5>
                <button class="chatbox__title__tray" >
                    <span></span>
                </button>
            </div>
            <div class="chatbox__body">
                <div class="chatbox__body__message chatbox__body__message--right">
                    <div class="messageChat"  style=" border-radius: 3px">
                        <div id="response"></div>
                        <c:forEach items="${messages}" var="message">
                            <fmt:formatDate var="aDate" pattern = 'dd-MM-yyyy HH:mm' value='${message.date}'/>
                            <c:choose>
                                <c:when test="${moderator.firstname!=message.from.firstname&&moderator.lastname!=message.from.lastname}">
                                    <p style="background-color: #6bd9aa"><span >${aDate} <br>  ${message.from.firstname} ${message.from.lastname}</span><br> ${message.text} </p>
                                    <br />
                                </c:when>
                                <c:otherwise>
                                    <p style="background-color: #e1e1e1"><span >${aDate}<br>   ${message.from.firstname} ${message.from.lastname}</span><br> ${message.text} </p>
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
                <h3 class="text-center na">${moderator.clinic.clinic_name}</h3>
                <c:if test="${message != null}"><div role="alert" class=" ${classCss} text-center">${message}</div></c:if>
                <hr>
                <div class="row">
                    <div class="row">
                        <!-- left column -->
                        <div class="col-md-4 col-md-offset-1">
                            <div class="text-center">
                                <div class="image-container ">

                                <img src="data:image/jpeg;base64,${moderator.clinic.photo}" class="avatar img-circle image img-responsive "  alt="avatar" width="100%"height="100%">

                                    <div class="middle">
                                        <div class="text">Change</div>
                                    </div>
                                </div>
                                <div class="col-lg-7">
                                <form:form action="/moderator/upload/clinicPhoto" method="post" onsubmit=" return validPhoto(this)" enctype="multipart/form-data" modelAttribute="photoForm" >


                                        <div style="position:relative;">
                                            <div id="errorPhoto" style="display: none;color: darkred"> More then 100Kb or Invalid Format</div>
                                                <a class='btn btn-file' href='javascript:;'>
                                                    Choose File...
                                                    <form:errors path="multipartFile"></form:errors>
                                                    <form:input  path="multipartFile" type="file" id="photo" onblur="validPhoto()" style='position:absolute;z-index:2;top:0;left:0;filter: alpha(opacity=0);-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";opacity:0;background-color:transparent;color:transparent;' name="file_source" size="40"  onchange='$("#upload-file-info").html($(this).val());'/>
                                                </a>
                                                &nbsp;
                                                <span class='label label-info' id="upload-file-info"></span>


                                                <form:button class='btn btn-github' style="margin-top: 5px;margin-left: 20px">Change</form:button>

                                        </div>

                                    </div>
                                </form:form>
                            </div>
                            <div></div>
                        </div>
                        <div class="col-md-7 personal-info">
                    <form:form method="POST" modelAttribute="clinicDTO" action="/moderator/cabinet" onsubmit="return validName(this)">
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><spring:message code="messages.clinicName"/>:</label>
                        <div class="col-lg-7">

                            <form:errors path="clinic_name"/>
                            <div id="errorClinicName" style="display: none;color: darkred"> Cant be empty</div>
                            <form:input type="text" id="clinicName"  class="form-control" onblur="validName()" path="clinic_name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"> <spring:message code="messages.Description"/>:</label>
                        <div class="col-lg-7">
                            <form:textarea cssClass="form-control" path="description" value="${clinicDTO.description}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><spring:message code="messages.userEmail"/>:</label>
                        <div class="col-lg-7">
                            <form:input type="text" class="form-control" path="email" value="${clinicDTO.email}" disabled="false" cssStyle="background-color: #72d9b9;"/>
                        </div>
                    </div>
                        <div class="form-group">
                        <label class="col-lg-3 control-label"><spring:message code="messages.userAddress"/>:</label>
                        <div class="col-lg-7">
                            <form:input type="text" class="form-control d" value="${clinicDTO.address}" path="address" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><spring:message code="messages.userCity"/>:</label>
                        <div class="col-lg-7">
                            <form:input type="text" class="form-control" path="city" value="${clinicDTO.city}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><spring:message code="messages.userZipcode"/>:</label>
                        <div class="col-lg-7">
                            <form:input type="text" class="form-control" path="zipCode" value="${clinicDTO.zipCode}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><spring:message code="messages.userFirstPhone"/>:</label>
                        <div class="col-lg-7">
                            <form:input type="text" class="form-control" path="firstPhone" value="${clinicDTO.firstPhone}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><spring:message code="messages.userSecondPhone"/>:</label>
                        <div class="col-lg-7">
                            <form:input type="text" class="form-control" path="secondPhone" value="${clinicDTO.secondPhone}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><spring:message code="messages.userThirdPhone"/>:</label>
                        <div class="col-lg-7">
                            <form:input type="text" class="form-control" path="thirdPhone" value="${clinicDTO.thirdPhone}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label"></label>
                        <div class="col-md-7">
                            <form:button class="btn btn-clinic"><spring:message
                                    code="messages.saveChanges"/></form:button>
                            <span></span>
                            <input type="reset" class="btn btn-github"
                                   value="<spring:message code="messages.cancel"/>">
                        </div>
                    </div>
                </form:form>
                        </div>
                </div>
            </div>


        </div>
    </div>
</div>
</div>