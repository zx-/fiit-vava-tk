<%-- 
    Document   : student-homeworks
    Created on : May 18, 2015, 7:33:05 PM
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
    
    <ul>
        
    <c:url var="post_url"  value="/${actionPath}" />
    <c:forEach var="sub" items="${hw}" varStatus="status">
      
        <li>
            <h4>
            ${sub.getHomework().getSubject().getName()}
            </h4>
            <h4>
            ${sub.getHomework().getName()}
            </h4>
            <h4>
            ${sub.getHomework().getTask()}
            </h4>
            <form:form method="post" action="${post_url}" modelAttribute="editHomeworkSubmissionForm">
                
                solution:
                <form:input path="submission" value="${sub.getSubmission()}"/>   
                <form:hidden path="submissionId" value="${sub.getId()}" />   
                <br/>
                <input value="Save" type="submit">

            </form:form>
        </li>
        
    </c:forEach>
    </ul>
    
</div>
<h:html_end_tag />