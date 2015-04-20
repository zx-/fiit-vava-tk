<%-- 
    Document   : classroom-classroom
    Created on : Apr 20, 2015, 9:23:59 PM
    Author     : Robert Cuprik <robertcuprik@hotmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h:html_start_tag />
                
        <div class="container">
            
            <h2>${classroom}</h2>
            
            <h3>students:</h3>
            
            <table border="1">
                <th>student_id</th>
                <th>student name</th>
                <th>Email</th>
                 
                <c:forEach var="user" items="${students}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                             
                </tr>
                </c:forEach>             
            </table>
            
        </div>
        
<h:html_end_tag />
