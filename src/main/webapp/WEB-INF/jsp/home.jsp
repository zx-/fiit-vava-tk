<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h:html_start_tag />
        
        <div class="container">
            
            <h1>User List</h1>
            <table border="1">
                <th>User_id</th>
                <th>Username</th>
                <th>Email</th>
                <th>Role</th>
                 
                <c:forEach var="user" items="${userList}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.role.role}</td>
                             
                </tr>
                </c:forEach>             
            </table>
            
            <p>current user: ${userAAA}</p>
            
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <p><a href="<c:url value="/admin" />" > admin menu</a></p>
            </sec:authorize>                
            
        </div>
            
<h:html_end_tag />