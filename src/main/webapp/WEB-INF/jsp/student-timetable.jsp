<%-- 
    Document   : student-timetable
    Created on : Apr 19, 2015, 11:18:11 PM
    Author     : Robert Cuprik <robertcuprik@hotmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h:html_start_tag />
                
        <div class="container">
            <h1><spring:message code="student.timetable" /></h1>
            <h2>${classroom}</h2>
            <table border="1">
                    <th>num</th>
                    <th>name</th>
                <c:forEach var="subject" items="${timetable}" varStatus="status">
                    <tr>
                        <td>${subject.subjectOrder}</td>
                        <td>${subject.name}</td>                          
                    </tr>
                </c:forEach> 
            </table>
        </div>
        
<h:html_end_tag />
