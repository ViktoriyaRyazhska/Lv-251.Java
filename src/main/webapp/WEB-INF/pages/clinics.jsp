<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<div class="container">

    <%--<select name="size"  class="selectpicker show-menu-arrow">--%>
        <%--<option>10</option>--%>
        <%--<option>20</option>--%>
        <%--<option>50</option>--%>
        <%--<option>100</option>--%>
        <%--<option>200</option>--%>
    <%--</select>--%>

        <%--<form method="GET">--%>
        <%--<input value="${numberChain = 10}" id="size" name="size" type="text">--%>
        <%--</form>--%>

    <c:forEach items="${getClinics}" var="clinic">
        <a href="clinic/${clinic.id}">
            <div class="row row-content">
                <div class="container-fluid">
                    <p>${clinic.clinic_name}</p>
                    <img class="show-logo" alt="logo" src="<c:url value="/resources/img/clinic_logo.png"/>">
                </div>
            </div>
        </a>
    </c:forEach>

    <ul class="pagination">
            <li class="page-item"><a class="page-link" href="">&laquo;</a></li>
            <c:forEach begin="1" end="${numberChain}" varStatus="loop">
                <li id="current" class="page-item"><a class="page-link" href="/clinics/${loop.index}">${loop.index}</a></li>
            </c:forEach>
            <li class="page-item"><a class="page-link" href="">&raquo;</a></li>
    </ul>
</div>






