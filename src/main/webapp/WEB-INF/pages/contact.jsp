<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<br>
<br>
<br>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
                <form class="form-horizontal col-sm-12" name="commentform" method="post"
                      action="send_form_email.php">
                    <div class="form-group">
                        <div class="col-lg-12">
                            <input type="text" class="form-control" name="first_name" id="first_name"
                                   placeholder="<spring:message code='messages.contactName'/>" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12">
                            <input type="email" class="form-control" id="email" name="email"
                                   placeholder="<spring:message code='messages.contactEmail'/>" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-lg-12">
                                    <textarea name="comments" id="comments" class="form-control" rows="13"
                                              placeholder="<spring:message code='messages.contactMessage'/>"
                                              required></textarea>
                        </div>
                    </div>
                    <input type="submit" name="submit" id="submit"
                           value="<spring:message code='messages.contactButton'/>"
                           class="btn btn-info pull-right">
                </form>
            </div>
    </div>
</div>
