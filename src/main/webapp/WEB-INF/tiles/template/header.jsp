<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<%--
    Author: Vitaliy Kovalevskyy
    Last updated by: Vitaliy Kovalevskyy
--%>
<!--NAVBAR***************************************************************************************************************************************-->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                <img src="${pageContext.request.contextPath}/resources/img/heartbeat2.png" height=35 width=100>
            </a>
        </div>

        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${pageContext.request.contextPath}/"><span class="glyphicon glyphicon-home"></span> <spring:message code="messages.home" />
                </a></li>
                <li>
                    <a href="${pageContext.request.contextPath}/clinics/1">
                        <span class="glyphicon glyphicon-plus-sign"></span><spring:message code="messages.clinics" />
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/allDoctors">
                        <span class="glyphicon glyphicon-user"></span><spring:message code="messages.doctors" />
                    </a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-envelope-o"></i> <spring:message code="messages.contact" />
                    </a>
                </li>
                <li>
                    <a class="navbar-brand pull-right" href="${pageContext.request.contextPath}">
                        <img src="${pageContext.request.contextPath}/resources/img/heartbeat2.png" height=35 width=100>
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="nav navbar-nav flags">
                    <a class=""  href="?lang=en" style="padding: 20px 0 ; float: left">
                        <img src="${pageContext.request.contextPath}/resources/img/flag-gb.png" class="flag flag-gb"
                             alt="Great Britain"/>
                    </a>
                    <a class=""  href="?lang=ua"  style="padding: 20px 3px ; float: left ">
                        <img src="${pageContext.request.contextPath}/resources/img/flag-ua.png" class="flag flag-ua"
                             alt="Ukraine"/>
                    </a>
                </li>

                <sec:authorize access="!isAuthenticated()">
                    <li><a type="button" id="loginBtn" data-toggle="modal" data-target="#loginModal" style="cursor:pointer;">
                        <span class="glyphicon glyphicon-log-in"></span> <spring:message code="messages.login"/> </a>
                    </li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a type="button" id="logoutBtn" style="cursor:pointer;" href="${pageContext.request.contextPath}/logout">
                            <span class="glyphicon glyphicon-log-out"></span> <spring:message code="messages.logout"/>
                        </a>
                    </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>

<sec:authorize access="!isAuthenticated()">
    <!--LOGIN MODAL________________________________________________________________________________________________________________________________________-->
    <div id="loginModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <div class="col-lg-3">
                        <h3 class="form-heading"><spring:message code="messages.login"/></h3>
                    </div>
                    <div class="col-lg-9">
                        <button class="close" type="button" data-dismiss="modal">
                            <i class="fa fa-close"></i>
                        </button>
                    </div>
                </div>

                <form action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
                    <div class="form-group ${error != null ? 'has-error' : ''}">
                        <span>${message}</span>
                        <div class="modal-body">
                            <input name="j_username" type="email" class="form-control" placeholder="Email"
                                   autofocus=""/>
                            <input name="j_password" type="password" class="form-control" placeholder="Password"/>
                            <span>${error}</span>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-lg btn-primary btn-block">
                                <spring:message code="messages.login"/>
                            </button>
                            <h4 class="text-center">
                                <a href="${contextPath}/registration"><spring:message code="messages.createAccount"/></a>
                            </h4>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</sec:authorize>