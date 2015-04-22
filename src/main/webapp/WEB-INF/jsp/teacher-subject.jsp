<%-- 
    Document   : teacher-subject
    Created on : Apr 22, 2015, 5:12:54 PM
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

    <c:if test="${not empty lessons}">

        has Lessons fASDFASDFASDF

    </c:if>

    <c:url var="post_url"  value="/${actionPath}" />
    <form:form method="post" action="${post_url}" modelAttribute="addLessonForm">

        <form:input path="description" />
        <c:forEach items="${addLessonForm.students}" var="student" varStatus="status">

            <br/>
            ${student.student.username}
            <form:checkbox path="students[${status.index}].present"></form:checkbox>
            <form:hidden path="students[${status.index}].userId" />



        </c:forEach>   
        <br/>
        <input value="Save" type="submit">

    </form:form>




</div>

<h:html_end_tag />

