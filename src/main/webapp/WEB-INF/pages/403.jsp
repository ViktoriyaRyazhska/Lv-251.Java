<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Access Denied Page - ProgrammingFree</title>
</head>
<body>  
 
<div class="alert-danger">
      <h3>You do not have permission to access this page!</h3>
     
</div>
 
<form action="<c:url value="/logout"/>" method="post">
    <input type="submit" class="button red big" value="Sign in as different user"/>
</form>
  
</body>
</html>
