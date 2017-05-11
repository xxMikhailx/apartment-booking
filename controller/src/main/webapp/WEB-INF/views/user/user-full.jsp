<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="page.user.info" var="title" />
<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="${title}"/>
    <jsp:param name="activeLink" value=""/>
</jsp:include>

<div class="content">
    <div class="container">

        <div class="page-title">
            <h1 class="home-brand"><spring:message code="page.user.info" /></h1>
            <c:choose>
                <c:when test="${not empty fullUser}">
                    <p><spring:message code="user.login" />: ${fullUser.login}</p>
                    <p><spring:message code="user.email" />: ${fullUser.email}</p>
                    <p><spring:message code="user.name" />: ${fullUser.name}</p>
                    <p><spring:message code="user.last.name" />: ${fullUser.surname}</p>
                    <p><spring:message code="user.creation.date" />: ${fullUser.creationDate}</p>
                </c:when>
                <c:otherwise>
                    <h3><spring:message code="user.not.found" /></h3>
                </c:otherwise>
            </c:choose>
        </div>

    </div><!-- /.container -->
</div>
<!-- /.content -->

<%@ include file="/WEB-INF/views/footer.jsp" %>