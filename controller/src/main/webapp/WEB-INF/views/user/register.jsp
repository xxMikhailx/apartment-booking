<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="Registration"/>
    <jsp:param name="activeLink" value="signup"/>
</jsp:include>

<div class="content">
    <div class="container">

        <div class="page-title">
            <h1>Registration</h1>
        </div>

        <form:form method="POST" commandName="user" action="check-register" class="form-horizontal">
            <div class="form-group">
                <label for="login" class="col-sm-offset-2 col-sm-2 control-label">Login</label>
                <div class="col-sm-4">
                    <form:input path="login" class="form-control"/>
                </div>
                <div class="col-sm-offset-4 col-sm-4">
                    <form:errors path="login" class="text-red"/>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-offset-2 col-sm-2 control-label">Password</label>
                <div class="col-sm-4">
                    <form:password path="password" class="form-control"/>
                </div>
                <div class="col-sm-offset-4 col-sm-4">
                    <form:errors path="password" class="text-red"/>
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-offset-2 col-sm-2 control-label">Email</label>
                <div class="col-sm-4">
                    <form:input path="email" class="form-control"/>
                </div>
                <div class="col-sm-offset-4 col-sm-4">
                    <form:errors path="email" class="text-red"/>
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-sm-offset-2 col-sm-2 control-label">Name</label>
                <div class="col-sm-4">
                    <form:input path="name" class="form-control"/>
                </div>
                <div class="col-sm-offset-4 col-sm-4">
                    <form:errors path="name" class="text-red"/>
                </div>
            </div>
            <div class="form-group">
                <label for="surname" class="col-sm-offset-2 col-sm-2 control-label">Surname</label>
                <div class="col-sm-4">
                    <form:input path="surname" class="form-control"/>
                </div>
                <div class="col-sm-offset-4 col-sm-4">
                    <form:errors path="surname" class="text-red"/>
                </div>
            </div>
            <span class="error-message-text col-sm-offset-4 col-sm-4"><c:out
                    value="${registrationErrorMessage}"/></span>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <button type="submit" class="btn btn-primary">Sign Up</button>
                </div>
            </div>
        </form:form>

    </div><!-- /.container -->
</div>
<!-- /.content -->

<%@ include file="/WEB-INF/views/footer.jsp" %>
