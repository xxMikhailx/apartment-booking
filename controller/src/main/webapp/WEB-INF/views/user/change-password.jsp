<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="page.change.password" var="title" />
<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="${title}"/>
</jsp:include>

<div class="content">
    <div class="container">

        <div class="page-title">
            <h1><spring:message code="page.change.password" /></h1>
        </div>

        <form:form method="POST" commandName="userPassword" action="check-change-password" class="form-horizontal">
            <div class="form-group">
                <label for="oldPassword" class="col-sm-offset-2 col-sm-2 control-label"><spring:message code="password.old" /></label>
                <div class="col-sm-4">
                    <form:password path="oldPassword" class="form-control"/>
                </div>
                <div class="col-sm-offset-4 col-sm-4">
                    <form:errors path="oldPassword" class="text-red"/>
                </div>
            </div>
            <div class="form-group">
                <label for="newPassword" class="col-sm-offset-2 col-sm-2 control-label"><spring:message code="password.new" /></label>
                <div class="col-sm-4">
                    <form:password path="newPassword" class="form-control"/>
                </div>
                <div class="col-sm-offset-4 col-sm-4">
                    <form:errors path="newPassword" class="text-red"/>
                </div>
            </div>
            <div class="form-group">
                <label for="newPasswordCopy" class="col-sm-offset-2 col-sm-2 control-label"><spring:message code="password.new.copy" /></label>
                <div class="col-sm-4">
                    <form:password path="newPasswordCopy" class="form-control"/>
                </div>
                <div class="col-sm-offset-4 col-sm-4">
                    <form:errors path="newPasswordCopy" class="text-red"/>
                </div>
            </div>
            <span class="error-message-text col-sm-offset-4 col-sm-4"><c:out value="${changePasswordError}"/></span>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <button type="submit" class="btn btn-primary"><spring:message code="action.change.password" /></button>
                </div>
            </div>
        </form:form>

    </div><!-- /.container -->
</div>
<!-- /.content -->

<%@ include file="/WEB-INF/views/footer.jsp" %>