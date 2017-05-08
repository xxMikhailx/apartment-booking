<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>${param.title}</title>

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
<div class="wrapper-page">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Apartment Booking</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <c:choose>
                        <c:when test="${param.activeLink == 'home'}"><li class="active"><a href="home">Home</a></li></c:when>
                        <c:otherwise><li><a href="home">Home</a></li></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${param.activeLink == 'about'}"><li class="active"><a href="#">About</a></li></c:when>
                        <c:otherwise><li><a href="#">About</a></li></c:otherwise>
                    </c:choose>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${param.activeLink == 'signin'}"><li class="active"><a href="login">Sign In</a></li></c:when>
                        <c:otherwise><li><a href="login">Sign In</a></li></c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${param.activeLink == 'signup'}"><li class="active"><a href="register">Sign Up</a></li></c:when>
                        <c:otherwise><li><a href="register">Sign Up</a></li></c:otherwise>
                    </c:choose>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Language<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">English</a></li>
                            <li><a href="#">Russian</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>