<%--
* Added by Pavlo Kuchereshko.
* Registration page.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<div class="modal-dialog">
    <div class="row">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="messages.createAccount"/></h4>
            </div>
            <div class="modal-header">
        <c:if test="${message != null}"><div role="alert" class="${classCss}">${message}</div></c:if>
        <form:form method="POST" modelAttribute="userForm" id="registration">
            <spring:bind path="firstName">
                <div class="form-group">
                    <label class="sr-only" for="firstName"><spring:message code="messages.firstName" var="firstNameMess"/></label>
                    <form:input type="text" id="firstName" path="firstName"
                                name="firstName" class="form-control" placeholder="${firstNameMess}" autofocus="true"/>
                    <form:errors path="firstName" cssClass="text-danger"/>
                    <span class="strengthPsw" id="firstNameWarn" style="pointer-events:none; float:left; white-space: pre;"></span>
                </div>
            </spring:bind>
            <spring:bind path="lastName">
                <div class="form-group">
                    <label class="sr-only" for="lastName"><spring:message code="messages.lastName" var="lastNameMess"/></label>
                    <form:input type="text" id="lastName" path="lastName" name="lastName"
                                class="form-control" placeholder="${lastNameMess}" autofocus="true"/>
                    <form:errors path="lastName" cssClass="text-danger"/>
                    <span class="strengthPsw" id="lastNameWarn" style="pointer-events:none; float:left; white-space: pre;"></span>
                </div>
            </spring:bind>
            <spring:bind path="email">
                <div class="form-group">
                    <label class="sr-only" for="emailReg"><spring:message code="messages.email" var="emailRegMess"/></label>
                    <form:input type="text" id="emailReg" path="email"
                                name="emailReg" class="form-control" placeholder="${emailRegMess}" autofocus="true"/>
                    <form:errors path="email" cssClass="text-danger"/>
                    <span class="strengthPsw" id="emailWarn" style="pointer-events:none; float:left; white-space: pre;"></span>
                </div>
            </spring:bind>
            <spring:bind path="password">
                <div class="form-group" id="eye_wrapper">
                    <div id="wrapper">
                        <label class="sr-only" for="passwordReg"><spring:message code="messages.password" var="passwordRegMess"/></label>
                        <form:input type="password" id="passwordReg" path="password"
                                    name="passwordReg" class="form-control" placeholder="${passwordRegMess}" cssStyle="background-color: inherit; z-index: -1"/>
                        <form:errors path="password" cssClass="text-danger"/>
                        <div id="eye_button">
                            <button type="button" tabindex="-1" id="eye" style="z-index: 0;">
                                <i class="fa fa-eye" id="eyeSymbol"></i>
                            </button>
                        </div>
                    </div>
                        <div class="password-background" style="z-index: 0; pointer-events:none;"></div>
                        <span class="strengthPsw" id="lengthWarn" style="pointer-events:none; float:left; white-space: pre;"></span>
                        <span class="strengthPsw" id="strengthPswId" style="pointer-events:none; float:left;/* white-space: pre; display: block*/"></span>
                </div>
            </spring:bind>
            <spring:bind path="matchingPassword">
                <div class="form-group">
                    <label class="sr-only" for="passwordConfirmReg"><spring:message code="messages.confirmPassword" var="passwordConfirmRegMess"/></label>
                    <form:input type="password" id="passwordConfirmReg" path="matchingPassword" cssStyle="z-index: 0"
                                name="passwordConfirm" class="form-control" placeholder="${passwordConfirmRegMess}"/>
                    <form:errors path="matchingPassword" cssClass="text-danger"/>
                    <span id="matchPsw"></span>
                </div>
            </spring:bind>
            <div class="form-group">
                <button class="btn btn-info"><spring:message code="messages.register"/></button>
            </div>
        </form:form>
            </div>
        </div>
    </div>
</div>

<div id="invisibleDiv" style="display: none; size: 0">
    <p id="errorWordNotEmpty"><spring:message code="error.wordNotEmpty"/></p>
    <p id="errorWordLength"><spring:message code="error.wordLength"/></p>
    <p id="errorStrengthVeryWeak"><spring:message code="error.strengthVeryWeak"/></p>
    <p id="errorStrengthWeak"><spring:message code="error.strengthWeak"/></p>
    <p id="errorStrengthMedium"><spring:message code="error.strengthMedium"/></p>
    <p id="errorStrengthStrong"><spring:message code="error.strengthStrong"/></p>
    <p id="errorStrengthVeryStrong"><spring:message code="error.strengthVeryStrong"/></p>
    <p id="errorPasswordMatch"><spring:message code="error.passwordMatch"/></p>
</div>