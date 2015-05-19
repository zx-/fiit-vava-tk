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
    
    
        
    Add homework:
    <c:url var="post_url"  value="/${actionPath}" />
    <form:form class="homework_form" method="post" action="${post_url}" modelAttribute="addHomeworkForm">
        <div>
        <label for="name">name:</label>
            <form:input path="name" />      
        </div>
        <div>
            <label for="task">task:</label>
            <form:input path="task" />      
        </div>
        <input value="Save" type="submit">

    </form:form>
        
    <ul class="homeworks">   
    <c:forEach var="hw" items="${homeworks}" varStatus="status">
        
        <li>
            <a href="${post_url}/${hw.getId()}">
            <h4>
                
                ${hw.getName()}
                
            </h4>
            <p>${hw.getTask()}</p>
            <p>submitted: ${hw.getSubmittedCount(true)}/${hw.getSubmittedCount(false)}</p>
            </a>
        </li>
         
    </c:forEach>
    </ul>
        
</div>

<h:html_end_tag />