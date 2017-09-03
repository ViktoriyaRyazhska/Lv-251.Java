<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">

    <div class="container">
        <div class="row">
            <div class="row row-content">
                <div class="container-fluid">
                    <div class="row col-lg-3">
                        <div>
                            <button data-toggle="modal" data-target="#modal_add" class="btn btn-success">
                                <spring:message code="messages.addModerator"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Large modal -->
            <div class="modal fade bs-example-modal-lg" id="modal_add" tabindex="-1" role="dialog"
                 aria-labelledby="myLargeModalLabel">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <%--<div class="modal-header" align="center">--%>
                            <%--<h4 class="modal-title" id="myModalLabel"><spring:message--%>
                                    <%--code="messages.addModerator"/></h4>--%>
                        <%--</div>--%>
                        <form:form method="POST" action="/admin/cabinet/add" modelAttribute="addModerator">
                            <div class="modal-body">

                                <h4><spring:message code="messages.moderatorInfo"/></h4>
                                <hr>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><spring:message
                                            code="messages.moderatorFirstname"/>:</label>
                                    <div class="col-sm-10">
                                        <form:input type="text" path="firstName" class="form-control"/>
                                    </div>
                                </div>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><spring:message
                                            code="messages.moderatorLastname"/>:</label>
                                    <div class="col-sm-10">
                                        <form:input type="text" path="lastName" class="form-control"/>
                                    </div>
                                </div>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><spring:message
                                            code="messages.email"/>:</label>
                                    <div class="col-sm-10">
                                        <form:input type="text" path="moderatorEmail" class="form-control"/>
                                    </div>
                                </div>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><spring:message
                                            code="messages.password"/>:</label>
                                    <div class="col-sm-10">
                                        <form:input type="text" path="password" class="form-control"/>
                                    </div>
                                </div>
                                <h4><spring:message code="messages.clinicInfo"/></h4>
                                <hr>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><spring:message
                                            code="messages.clinicName"/>:</label>
                                    <div class="col-sm-10">
                                        <form:input type="text" path="clinicName" class="form-control"/>
                                    </div>
                                </div>
                                <!-- Text input-->
                                <div class="form-group">
                                    <label class="col-sm-2 control-label"><spring:message
                                            code="messages.email"/>:</label>
                                    <div class="col-sm-10">
                                        <form:input type="text" path="clinicEmail" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-10">
                                        <div class="pull-right">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                                <spring:message code="messages.cancel"/></button>
                                            <button type="submit" class="btn btn-primary"><spring:message
                                                    code="messages.save"/></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>

            <c:choose>
                <c:when test="${moderators.size()>0}">
                    <c:forEach items="${moderators}" var="moderator">

                        <div class="row row-content">
                            <div class="container-fluid">
                                <div class="row" style="margin: 0">
                                    <div class="col-xs-6 col-lg-5">
                                        <h3><spring:message
                                                code="messages.moderatorOf"/>${moderator.clinic.clinic_name} </h3>
                                    </div>
                                    <div class="col-xs-6 col-lg-1 pull-right" style="padding: 0 15px 0 0">
                                        <button data-toggle="modal" data-target="#modal_${moderator.id}"
                                                class="btn btn-danger">
                                            <spring:message code="messages.delete"/>
                                        </button>
                                    </div>
                                </div>
                                <!-- Large modal -->
                                <div class="modal fade bs-example-modal-sm" id="modal_${moderator.id}" tabindex="-1"
                                     role="dialog" aria-labelledby="myLargeModalLabel">
                                    <div class="modal-dialog modal-sm" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h4 class="modal-title" id="myModalDelete"><spring:message
                                                        code="messages.sureToDelete"/></h4>
                                            </div>
                                            <div class="modal-body">
                                                <div> ${moderator.firstname} ${moderator.lastname}</div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                                    <spring:message code="messages.cancel"/></button>
                                                <a href="/admin/cabinet/delete/${moderator.id}">
                                                    <button class="btn btn-primary"><spring:message
                                                            code="messages.delete"/></button>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <hr>
                                <div class="row">
                                    <div class="col-xs-6 col-md-2">
                                        <a href="#" class="thumbnail">
                                            <img width="150" height="150"
                                                 src="data:image/jpeg;base64,${moderator.photo}"
                                                 alt="...">
                                        </a>
                                    </div>
                                    <form:form method="POST" action="/admin/cabinet/edit/${moderator.id}"
                                               modelAttribute="editModerator">
                                        <div class="col-xs-6 col-md-4" style="border-right: thin solid grey;">
                                            <label for="firstname"><spring:message
                                                    code="messages.moderatorFirstname"/></label>
                                            <form:input type="text" path="firstname" id="firstname" class="form-control"
                                                        value="${moderator.firstname}"/>
                                            <label for="lastname"><spring:message
                                                    code="messages.moderatorLastname"/>:</label>
                                            <form:input type="text" id="lastname" path="lastname" class="form-control"
                                                        value="${moderator.lastname}"/>
                                            <label for="email"><spring:message code="messages.email"/>:</label>
                                            <form:input type="text" id="email" path="email" class="form-control"
                                                        value="${moderator.email}"/>
                                            <form:button class="btn btn-primary pull-right"
                                                         style="margin: 10px"><spring:message
                                                    code="messages.save"/></form:button>
                                        </div>
                                    </form:form>

                                    <div class="col-xs-6 col-md-2">
                                        <a href="#" class="thumbnail">
                                            <img width="150" height="150"
                                                 src="data:image/jpeg;base64,${moderator.clinic.photo}"
                                                 alt="...">
                                        </a>
                                    </div>

                                    <div class="col-xs-6 col-md-4">
                                        <label for="clinicname"><spring:message code="messages.clinicName"/>:</label>
                                        <input disabled type="text" id="clinicname" class="form-control"
                                               value="${moderator.clinic.clinic_name}">
                                        <label for="city"><spring:message code="messages.userCity"/>:</label>
                                        <input disabled type="text" id="city" class="form-control"
                                               value="${moderator.clinic.contact.city}">
                                        <label for="description"><spring:message code="messages.Description"/>:</label>
                                        <input disabled type="text" id="description" class="form-control"
                                               value="${moderator.clinic.description}">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p><spring:message code="messages.noModerators"/></p>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</div>