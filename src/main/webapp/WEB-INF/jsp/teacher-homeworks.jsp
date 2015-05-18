<%-- 
    Document   : teacher-homeworks
    Created on : May 18, 2015, 5:47:45 PM
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
    
    <h1>${subject}</h1>
    <h2>${classRoom}</h2>
    <h3><spring:message code="homeworks" /></h3>
    
    <ul class="homeworks">
        
    <c:url var="post_url"  value="/${actionPath}" />
    <form:form method="post" action="${post_url}" modelAttribute="addHomeworkForm">
        name:
        <form:input path="name" />      
        <br/>
        task:
        <form:input path="task" />      
        <br/>
        <input value="Save" type="submit">

    </form:form>
        
    <c:forEach var="hw" items="${homeworks}" varStatus="status">
      
        <li>
            <h4>
                <a href="${post_url}/${hw.getId()}">
                    ${hw.getName()}
                </a> 
            </h4>
            <p>${hw.getTask()}<p>
            <p>submitted: ${hw.getSubmittedCount(true)}/${hw.getSubmittedCount(false)}<p>
            
        </li>
        
    </c:forEach>
    </ul>
        
</div>

<h:html_end_tag />