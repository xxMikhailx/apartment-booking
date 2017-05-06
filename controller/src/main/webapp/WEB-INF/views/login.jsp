<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Sign In</title>

    <!-- Bootstrap -->
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/css" media="all">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" media="all" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <div class="sign-in">
        <h1>Signing in</h1>
    </div>

    <form:form method="POST" commandName="userCredential" action="check-user" class="form-horizontal">
        <div class="form-group">
            <label for="login" class="col-sm-offset-2 col-sm-2 control-label">Login</label>
            <div class="col-sm-4">
                <form:input path="login" class="form-control" />
            </div>
            <div class="col-sm-offset-4 col-sm-4">
                <form:errors path="login" class="text-red" />
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-offset-2 col-sm-2 control-label">Password</label>
            <div class="col-sm-4">
                <form:password path="password" class="form-control" />
            </div>
            <div class="col-sm-offset-4 col-sm-4">
                <form:errors path="password" class="text-red" />
            </div>
        </div>
        <span class="error-message-text col-sm-offset-4 col-sm-4"><c:out value="${incorrectLoginOrPasswordMessage}" /></span>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-4">
                <button type="submit" class="btn btn-default">Sign in</button>
            </div>
        </div>
    </form:form>

</div><!-- /.container -->


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>
