<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<p id="result"></p>
<div class="container">
    <div class="row">
        <h2></h2>
        <div id="custom-search-input">
<form action="/searchResult" method="get">
            <div class="input-group col-md-12">
                <input type="text" id="autocomplete" name="search" class="  search-query form-control" placeholder="Search" />
                <span class="input-group-btn">
                                    <button class="btn btn-danger" type="submit"  id="search-doctor">
                                        <span class=" glyphicon glyphicon-search"></span>
                                    </button>
                                </span>
            </div>
</form>
        </div>
    </div>
</div>
<div id="content">

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<c:choose>
    <c:when test="${doctors.size()>0}">
        <div class="container">
        <c:forEach items="${doctors}" var="doctor">
            <a href="doctor/${doctor.id}">
                <div class="row row-content">
                    <div class="container-fluid">
                        <p>${doctor.firstname} ${doctor.lastname} ${doctor.middlename}</p>
                        <p>Specialization:${doctor.specialization.name}</p>
                        <img class="show-logo" alt="logo" src="<c:url value="/resources/img/clinic_logo.png"/>">

                    </div>
                </div>
            </a>
        </c:forEach>
        </div>
    </c:when>
    <c:otherwise>
        <div class="container">
        <div class="row row-content">
            <div class="container-fluid">
               There are no doctors in database
            </div>
        </div>
    </c:otherwise>
</c:choose>
        </div>
</div>