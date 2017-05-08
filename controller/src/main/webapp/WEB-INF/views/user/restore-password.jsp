<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="Restore password"/>
</jsp:include>

<div class="content">
    <div class="container">

        <div class="page-title">
            <h1>Restore password</h1>
        </div>

        <form:form method="POST" commandName="userEmail" action="check-restore" class="form-horizontal">
            <div class="form-group">
                <label for="email" class="col-sm-offset-2 col-sm-2 control-label">Email</label>
                <div class="col-sm-4">
                    <form:input path="email" class="form-control"/>
                </div>
                <div class="col-sm-offset-4 col-sm-4">
                    <form:errors path="email" class="text-red"/>
                </div>
            </div>
            <span class="error-message-text col-sm-offset-4 col-sm-4"><c:out value="${restorationErrorMessage}"/></span>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <button type="submit" class="btn btn-primary">Restore password</button>
                </div>
            </div>
        </form:form>

    </div><!-- /.container -->
</div>
<!-- /.content -->

<%@ include file="/WEB-INF/views/footer.jsp" %>