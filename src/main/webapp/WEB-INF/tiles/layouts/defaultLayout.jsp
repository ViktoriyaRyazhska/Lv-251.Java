<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><tiles:getAsString name="title" /></title>

    <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-theme.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/font-awesome.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap-social.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/search.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/passwordStrength.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/medicalCard.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="/resources/css/chatbox.css">


</head>

<body>
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
            <a class="navbar-brand hidden-md hidden-sm" href="${pageContext.request.contextPath}/">
                <img src="${pageContext.request.contextPath}/resources/img/heartbeat2.png" height=35 width=100>
            </a>

            <div class="hidden-lg hidden-md hidden-sm col-xs-6 pull-right">
                <li class="nav navbar-nav flags pull-right">
                    <a class="" href="?lang=en" style="padding: 20px 0 ; float: left">
                        <img src="${pageContext.request.contextPath}/resources/img/flag-gb.png" class="flag flag-gb"
                             alt="Great Britain"/>
                    </a>
                    <a class="" href="?lang=uk"  style="padding: 20px 3px ; float: left ">
                        <img src="${pageContext.request.contextPath}/resources/img/flag-ua.png" class="flag flag-ua"
                             alt="Ukraine"/>
                    </a>
                    <span>&nbsp;&nbsp;</span>
                </li>
            </div>
        </div>

        <tilesx:useAttribute name="current"/>

        <div id="navbar" class="navbar-collapse collapse">
            <ul  class="nav navbar-nav">
                <li class="${current == 'home' ? 'active': ''}">
                    <a href="${pageContext.request.contextPath}/">
                        <span class="glyphicon glyphicon-home"></span>
                        <spring:message code="messages.home" />
                    </a>
                </li>
                <li class="${current == 'clinics' ? 'active': ''}">
                    <a href="${pageContext.request.contextPath}/clinics/1">
                        <span class="glyphicon glyphicon-plus-sign"></span><spring:message code="messages.clinics" />
                    </a>
                </li>
                <li class="${current == 'allDoctors' ? 'active': ''}">
                    <a href="${pageContext.request.contextPath}/allDoctors/1">
                        <span class="glyphicon glyphicon-user"></span><spring:message code="messages.doctors" />
                    </a>
                </li>

                <li class="${current == 'contact' ? 'active': ''}">
                    <a href="${pageContext.request.contextPath}/contact"><i class="fa fa-envelope-o"></i> <spring:message code="messages.contact" />
                    </a>
                </li>
                <li class="${current == 'userCabinet' ? 'active': ''}">
                    <sec:authorize access="hasAuthority('ROLE_USER')">
                        <a href="${pageContext.request.contextPath}/user/cabinet">
                            <i class="fa fa-id-card"></i>
                            <spring:message code="messages.userCabinet" />
                        </a>
                    </sec:authorize>
                </li>
                <li class="${current == 'adminCabinet' ? 'active': ''}">
                    <sec:authorize access="hasAuthority('ROLE_ADMIN')">
                        <a href="${pageContext.request.contextPath}/admin/cabinet">
                            <i class="fa fa-id-card"></i>
                            <spring:message code="messages.adminCabinet" />
                        </a>
                    </sec:authorize>
                </li>
                <li class="${current == 'doctor/сabinet' ? 'active': ''}">
                    <sec:authorize access="hasAuthority('ROLE_DOCTOR')">
                        <a href="${pageContext.request.contextPath}/doctor/сabinet"><i class="fa fa-tasks"></i> <spring:message code="messages.doctorCabinet" />
                        </a>
                    </sec:authorize>
                </li>
                <li class="${current=='moderator/cabinet'? 'active':''}">
                <sec:authorize access="hasAuthority('ROLE_MODERATOR')">
                    <a href="${pageContext.request.contextPath}/moderator/cabinet"><i class="fa fa-cogs"></i> <spring:message code="messages.moderatorCabinet" />
                    </a>
                </sec:authorize>
                </li>
                <li>
                    <a class="navbar-brand pull-right hidden-xs hidden-md hidden-sm" href="${pageContext.request.contextPath}">
                        <img src="${pageContext.request.contextPath}/resources/img/heartbeat2.png" height=35 width=100>
                    </a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <sec:authorize access="isAuthenticated()">
                        <a type="text">
                            <%= session.getAttribute("username") %>
                        </a>
                    </sec:authorize>
                </li>
                <li class="nav navbar-nav flags hidden-xs">
                    <a class="" href="?lang=en" style="padding: 20px 0 ; float: left">
                        <img src="${pageContext.request.contextPath}/resources/img/flag-gb.png" class="flag flag-gb"
                             alt="Great Britain"/>
                    </a>
                    <a class="" href="?lang=uk"  style="padding: 20px 3px ; float: left ">
                        <img src="${pageContext.request.contextPath}/resources/img/flag-ua.png" class="flag flag-ua"
                             alt="Ukraine"/>
                    </a>
                    <span>&nbsp;&nbsp;</span>
                </li>

                <sec:authorize access="!isAuthenticated()">
                    <li><a type="button" onclick="hideText()" id="loginBtn" data-toggle="modal" data-target="#loginModal" style="cursor:pointer;">
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
                            <%--<span>${message}</span>--%>
                            <div class="modal-body">
                                <div class="form-group">
                                    <label class="control-label" for="email">
                                        <spring:message code="messages.email"/>
                                    </label>
                                    <input id="email" name="j_username" type="email" class="form-control"
                                           placeholder="<spring:message code="messages.email"/>"
                                       autofocus=""/>
                                </div>
                                <div class="form-group">
                                    <label class="control-label" for="password">
                                        <spring:message code="messages.password"/>
                                    </label>
                                    <input id="password" name="j_password" type="password" class="form-control"
                                           placeholder="<spring:message code="messages.password"/>"/>
                                </div>
                                <div class="form-group">
                                    <input id="remember" type="checkbox" name="remember-me" value="true">
                                    <label class="control-label" for="remember">
                                        <spring:message code="messages.rememberMe"/>
                                    </label>
                                </div>

                                <p id="wrong" style="color: red"></p>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-lg btn-primary btn-block">
                                    <spring:message code="messages.login"/>
                                </button>
                                <h4 class="text-center">
                                    <a href="<c:url value="/registration"/>"><spring:message code="messages.createAccount"/></a>
                                </h4>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </sec:authorize>

<script src="<c:url value="/resources/js/jquery.1.10.2.min.js"/>"></script>

<script>
    function hideText() {
        document.getElementById('wrong').innerHTML = '';
    }
</script>


<c:if test="${flag}">
    <script>
        document.getElementById('wrong').innerHTML = '<spring:message code="messages.invalidLoginOrPassword"/>';
        jQuery(window).load(function(){
            jQuery('#loginModal').modal('show')
        });
        <c:set var="flag" value="false"/>
    </script>
</c:if>

<c:if test="${login}">
    <script>
        document.getElementById('wrong').innerHTML = '';
        jQuery(window).load(function(){
            jQuery('#loginModal').modal('show')
        });
        <c:set var="login" value="false"/>
    </script>
</c:if>

    <section id="site-content">
        <tiles:insertAttribute name="body" />
    </section>

    <footer id="footer">
        <tiles:insertAttribute name="footer"/>
    </footer>

<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.autocomplete.min.js"/>"></script>
<script src="<c:url value="/resources/js/zxcvbn.min.js"/>"></script>
<script src="<c:url value="/resources/js/passwordStrength.js"/>"></script>
<script src="<c:url value="/resources/js/search.js"/>"></script>
<script src="<c:url value="/resources/js/mainSearch.js"/>"></script>
<script src="<c:url value="/resources/js/autoSpecDocs.js"/>"></script>
<script src="<c:url value="/resources/js/profile.js"/>"></script>
<script src="<c:url value="/resources/js/matchPassword.js"/>"></script>
<script src="<c:url value="/resources/js/addDoctorValidate.js"/>"></script>
<script src="/resources/js/sockjs-0.3.4.js"></script>
<script src="/resources/js/stomp.js"></script>
<script src="<c:url value="/resources/js/chat.js"/>"></script>
</body>
</html>
