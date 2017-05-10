<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="page.apartment.info" var="title" />
<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="${title}"/>
    <jsp:param name="activeLink" value=""/>
</jsp:include>

<div class="content">
    <div class="container">

        <div class="page-title">
            <h1 class="home-brand"><spring:message code="page.apartment.info" /></h1>
            <p><spring:message code="apartment.title" />: ${fullApartment.title}</p>
            <p><spring:message code="apartment.description" />: ${fullApartment.description}</p>
            <p><spring:message code="apartment.type" />: ${fullApartment.apartmentType}</p>
            <p><spring:message code="apartment.price" />: ${fullApartment.price}</p>
            <p><spring:message code="apartment.max.guest.number" />: ${fullApartment.maxGuestNumber}</p>
            <p><spring:message code="apartment.bed.number" />: ${fullApartment.bedNumber}</p>
            <p><spring:message code="apartment.status" />: ${fullApartment.apartmentStatus}</p>
            <p><spring:message code="apartment.address" />: ${fullApartment.address}</p>
            <p><spring:message code="apartment.city" />: ${fullApartment.city.title}</p>
            <p><spring:message code="apartment.country" />: ${fullApartment.city.country.title}</p>
        </div>

    </div><!-- /.container -->
</div>
<!-- /.content -->

<%@ include file="/WEB-INF/views/footer.jsp" %>