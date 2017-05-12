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
                    <span class="sr-only"><spring:message code="navigation.toggle" /></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">&lt Apartment Booking &gt</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <c:choose>
                        <c:when test="${param.activeLink == 'home'}"><li class="active"><a href="/"><spring:message code="page.main" /></a></li></c:when>
                        <c:otherwise><li><a href="/"><spring:message code="page.main" /></a></li></c:otherwise>
                    </c:choose>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <c:choose>
                        <c:when test="${not empty user || not empty user.role}">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${user.login}<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li class="user-menu-link"><spring:message code="user.role"/>: <c:choose><c:when test="${user.role == 1}"><spring:message code="user.role.admin"/></c:when><c:otherwise><spring:message code="user.role.user"/></c:otherwise></c:choose></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="/change-password"><spring:message code="action.change.password"/></a></li>
                                    <li><a href="/logout"><spring:message code="action.logout"/></a></li>
                                </ul>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${param.activeLink == 'signin'}"><li class="active"><a href="/login"><spring:message code="page.sign.in" /></a></li></c:when>
                                <c:otherwise><li><a href="/login"><spring:message code="page.sign.in" /></a></li></c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${param.activeLink == 'signup'}"><li class="active"><a href="/register"><spring:message code="page.sign.up" /></a></li></c:when>
                                <c:otherwise><li><a href="/register"><spring:message code="page.sign.up" /></a></li></c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="locale.change" /><span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="?lang=en">English</a></li>
                            <li><a href="?lang=ru">Русский</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
    </nav>