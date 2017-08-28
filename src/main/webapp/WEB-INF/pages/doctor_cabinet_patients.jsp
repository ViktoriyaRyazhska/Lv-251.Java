<%--
  Created by IntelliJ IDEA.
  User: Taras
  Date: 01.08.2017
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Title</title>

    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">--%>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>

</head>
<body onLoad="init()">


<div class="container">
    <div class="container" style="width: 30%; float: left">
        <div class="row row-content">
            <div class="list-group doc-menu">
                <a href="/user/cabinet" class="list-group-item">
                    <spring:message code="messages.profile"/>
                </a>
                <a href="#" class="list-group-item">
                    <spring:message code="messages.doctor"/>
                </a>
                <a href="#" class="list-group-item">
                    <spring:message code="messages.medicalCard"/>
                </a>
                <a href="/user/appointment" class="list-group-item">
                    <spring:message code="messages.appointment"/>
                </a>
                <a href="/doctor/patients" class="active list-group-item">
                    <spring:message code="messages.patients"/>
                </a>
            </div>
        </div>
    </div>

        <div class="container" style="width: 70%">
            <script src="<c:url value="/resources/js/doctorsPatients.js"/>"></script>

            <div class="form-group">
                <div class="icon-addon addon-md">
                    <input type="text" placeholder="Search" class="form-control" id="search">
                </div>

            </div>
            <div class="container">
                <table class="table table-hover">
                    <tbody id="dynamic-list">
                    <tr>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>



</div>




</body>
</html>
