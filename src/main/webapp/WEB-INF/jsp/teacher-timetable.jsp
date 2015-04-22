<%-- 
    Document   : teacher-timetable
    Created on : Apr 20, 2015, 4:35:36 PM
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
            <table border="1">
                    <th>num</th>
                    <th>name</th>
                    <th>classRoom</th>
                <c:forEach var="subject" items="${timetable}" varStatus="status">
                    <tr>
                        <td>${subject.subjectOrder}</td>
                        <td>
                            <a href="<c:url value="/teacher/${subject.name}-${subject.id}/${subject.classRoom.name}" />" >
                                ${subject.name}
                            </a>                               
                        </td>                          
                        <td><a href="<c:url value="/class-room/${subject.classRoom.name}" />">${subject.classRoom.name}</a></td>                          
                    </tr>
                </c:forEach> 
            </table>
        </div>
        
<h:html_end_tag />
