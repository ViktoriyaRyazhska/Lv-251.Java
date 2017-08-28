<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container">
    <div >
        <div class="container" style="width: 30%; float: left">
            <div class="row row-content">
                <div class="list-group doc-menu">
                    <a href="/moderator/cabinet" class="list-group-item ">
                        <spring:message code="messages.clinic" />
                    </a>
                    <a href="/moderator/cabinet/doctor" class=" list-group-item ">
                        <spring:message code="messages.doctors" /><span class="badge">${doctors.size()}</span>
                    </a>
                    <a href="/moderator/cabinet/add/doctor" class="list-group-item ">
                        <spring:message code="messages.addDoctors" />
                    </a>
                    <a href="/moderator/cabinet/make/doctor" class="list-group-item navbar-inverse">
                        <spring:message code="messages.makeDoctor" />
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div>
        <div class="container" style="width: 70%; float: right">
            <div class="row row-content">
                <h3 class="text-center">${moderator.clinic.clinic_name}</h3>
                <hr>
                <div class="row">
                    <div class="col-lg-10" style="margin-left: 10%">
                        <form:form action="/moderator/cabinet/make/doctor" method="post" modelAttribute="usersToDoctor">
                            <div class="form-group">
                                <label class="col-lg-3 control-label"><spring:message
                                        code="messages.contactEmail"/>:</label>
                                <div class="col-lg-7">
                                    <form:errors path="email" cssClass="text-danger"/>
                                    <form:input type="text" class="form-control" id="autocomplete-user" path="email"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"> <spring:message
                                        code="messages.specialization"/>:</label>
                                <div class="col-lg-7">
                                    <form:errors path="specialization" cssClass="text-danger"/>
                                    <form:input type="text" class="form-control" id="autocomplete-spec" path="specialization"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-lg-3 control-label"> <spring:message
                                        code="messages.Description"/>:</label>
                                <div class="col-lg-7">
                                        <form:errors path="description" cssClass="text-danger"/>
                                    <form:input type="text" class="form-control"  path="description"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label"></label>
                                <div class="col-md-7">
                                    <form:button class="btn btn-clinic"><spring:message
                                            code="messages.saveChanges"/></form:button>
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
</div>