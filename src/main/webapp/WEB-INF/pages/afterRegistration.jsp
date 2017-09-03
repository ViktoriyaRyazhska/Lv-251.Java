<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<style>
    .container {
        position: relative;
        margin: auto;
        top: 50%;
    }
</style>
<div class="container">
    <div class="medical-card alert alert-info">
        <div class="media">
            <div class="media-body">
                <h3 class="media-heading" style="text-align: center; margin: auto"><spring:message code="messages.confirmRegistration"/></h3>
            </div>
        </div>
    </div>
    <button onclick="location.href='/'" class="btn btn-info" style="left: 50%; margin-left: 47%; margin-right: 47%">
        <spring:message code="messages.home"/>
    </button>
</div>