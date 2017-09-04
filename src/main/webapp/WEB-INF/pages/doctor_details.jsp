<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<div class="container">
    <div class="row row-content">
        <div class="card-block">

            <img class="show-logo" alt="logo" width="200px" src="data:image/jpeg;base64,${doctor.photo}">
            <h4>${doctor.firstname} ${doctor.lastname} ${doctor.middlename}</h4>
            <p>Specialization:${doctor.specialization.name}</p>
            <div class="row">
                <div class="container">
                    <p>${doctor.description}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="card">
        <div class="container">
            <h4><spring:message code="messages.responds"/></h4>
        </div>
        <c:if test="${responds.size() == 0}">
        <div class="card">
            <div class="container">
                <h5><spring:message code="messages.noResponds"/></h5>
            </div>
        </div>
        </c:if>
        <c:forEach items="${responds}" var="respond">
            <div class="card">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-3">
                            <p style="font-size: medium">${respond.userFullName} :</p>
                            <p style="font-size: small">${respond.date}</p>
                        </div>
                        <div class="col-xs-6">
                            <div class="container">
                                <p style="font-size: medium">
                                        ${respond.description}
                                </p>
                            </div>
                        </div>
                        <div class="col-xs-3">
                            <c:forEach begin="1" end="${respond.raiting}" varStatus="loop">
                                <span style="font-size:200%;color:darkcyan;">&starf;</span>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
