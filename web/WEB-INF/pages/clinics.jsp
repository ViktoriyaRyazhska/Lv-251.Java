<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <title>Clinics Page</title>
</head>
<body>

<h1>Clinics List</h1>

<c:if test="${!empty listClinics}">
    <table class="tg">
        <tr>
            <th width="10">ID</th>
            <th width="40">Name</th>
            <th width="70">Decryption</th>
        </tr>
        <c:forEach items="${listClinics}" var="clinic">
            <tr>
                <td>${clinic.id}</td>
                <td>${clinic.clinic_name}</td>
                <td>${clinic.description}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<div align="center">
    <form id="back_form" >
        <button id="back_button" type="button" class="button" name="back"
                onclick="location.href='<c:url value="/index.jsp"/>'">Back</button>
    </form>
</div>
<footer id="footer_users">
    <div>Posted by: lol</div>
    <div>Contact information: <a href="mailto:lol.ua@gmail.com">
        lol.ua@gmail.com</a>.</div>
</footer>
</body>
</html>