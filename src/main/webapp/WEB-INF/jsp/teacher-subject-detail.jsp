<%-- 
    Document   : teacher-subject-detail
    Created on : May 18, 2015, 7:16:52 PM
    Author     : Robert Cuprik <robertcuprik@hotmail.com>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h:html_start_tag />
<div class="container">
    
    <h1>${subject}</h1>
    <h2>${classRoom}</h2>
    <h3>${hw.getName()}</h3> 
    <p>${hw.getTask()}</p>
    <p>submitted: ${hw.getSubmittedCount(true)}/${hw.getSubmittedCount(false)}<p>
   
    
    <ul>
    <c:forEach var="sub" items="${hw.getSubmissions()}" varStatus="status">
      
        <li>
            ${sub.getStudent().getUsername()}
            ${sub.isSubmitted()}
            ${sub.getSubmission()}
        </li>
        
    </c:forEach>
    </ul>
   
</div>
<h:html_end_tag />
