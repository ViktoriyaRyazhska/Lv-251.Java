<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <li class="active"><a href="${pageContext.request.contextPath}/"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                <li>
                    <a href="${pageContext.request.contextPath}WEB-INF/pages/clinics">
                        <span class="glyphicon glyphicon-plus-sign"></span>Clinics</a>
                </li>
                <li>
                    <a href="<${pageContext.request.contextPath}WEB-INF/pages/allDoctors">
                        <span class="glyphicon glyphicon-user"></span>Doctors</a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-envelope-o"></i> Contact</a>
                </li>
                <li>
                    <a class="navbar-brand pull-right" href="${pageContext.request.contextPath}">
                        <img src="${pageContext.request.contextPath}/resources/img/heartbeat2.png" height=35 width=100>
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a type="button" id="loginBtn" data-toggle="modal" data-target="#loginModal">
                    <span class="glyphicon glyphicon-log-in"></span> Login</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
