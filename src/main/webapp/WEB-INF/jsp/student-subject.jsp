<%-- 
    Document   : student-subject
    Created on : May 14, 2015, 5:48:40 PM
    Author     : Robert Cuprik <robertcuprik@hotmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h:html_start_tag />
<div class="container">
    
    <h1>${subjectName}</h1>    
    
    <h3><spring:message code="grades" />:</h3>
    ${grades}
    <h3><spring:message code="attendance" />:</h3>
    <ul>
        <c:forEach items="${attendance}" var="entry">
             <li>${entry.wasPresent}</li>               
        </c:forEach>
    </ul>
</div>

<h:html_end_tag />