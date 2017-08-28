<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">

    <div class="container" style="width: 70%; float: right">
        <div class="row row-content">
            <div class="row">
                <c:choose>
                    <c:when test="${moderators.size()>0}">
                        <c:forEach items="${moderators}" var="doctor">
                            <div class="col-xs-6 col-sm-6 col-md-10 col-lg-10 col-md-offset-1 ">
                                <div class="row">
                                    <div class="col-xs-6 col-md-3 col-lg-2">
                                        <img src="data:image/jpeg;base64,${moderators.photo}" width="100"
                                             class="avatar img-circle" alt="avatar">
                                    </div>
                                    <div class="col-md-1 col-lg-1"></div>
                                    <div class="col-xs-6 col-md-8 col-lg-9">
                                        <div class="row">
                                            <div> ${doctor.firstname} ${doctor.lastname}</div>
                                        </div>
                                        <hr>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p>No moderators in the database</p>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
    </div>
</div>