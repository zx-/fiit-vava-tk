<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <title>Vava Triedna kniha</title>
    </head>
    <body>
        <div align="center">
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
            <p><a href="<c:url value="/user-login" />" > login</a></p>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <p><a href="<c:url value="/admin" />" > admin menu</a></p>
            </sec:authorize>
            
        </div>
    </body>
</html>