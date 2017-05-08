<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="page.sign.in" var="title" />
<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="${title}"/>
    <jsp:param name="activeLink" value="signin"/>
</jsp:include>

<div class="content">
    <div class="container">

        <div class="page-title">
            <h1><spring:message code="page.sign.in" /></h1>
        </div>

        <form:form method="POST" commandName="userCredential" action="check-user" class="form-horizontal">
            <div class="form-group">
                <label for="login" class="col-sm-offset-2 col-sm-2 control-label"><spring:message code="user.login" /></label>
                <div class="col-sm-4">
                    <form:input path="login" class="form-control"/>
                </div>
                <div class="col-sm-offset-4 col-sm-4">
                    <form:errors path="login" class="text-red"/>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-offset-2 col-sm-2 control-label"><spring:message code="user.password" /></label>
                <div class="col-sm-4">
                    <form:password path="password" class="form-control"/>
                </div>
                <div class="col-sm-offset-4 col-sm-4">
                    <form:errors path="password" class="text-red"/>
                </div>
            </div>
            <span class="error-message-text col-sm-offset-4 col-sm-4"><c:out
                    value="${incorrectLoginOrPasswordMessage}"/></span>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-1">
                    <button type="submit" class="btn btn-primary"><spring:message code="action.sign.in" /></button>
                </div>
                <div class="col-sm-1">
                    <a href="restore-password"><input type="button" class="btn btn-default" value="<spring:message code="action.restore.password" />"/></a>
                </div>
            </div>
        </form:form>

    </div><!-- /.container -->
</div>
<!-- /.content -->

<%@ include file="/WEB-INF/views/footer.jsp" %>