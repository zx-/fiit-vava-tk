<%-- 
    Document   : student-timetable
    Created on : Apr 19, 2015, 11:18:11 PM
    Author     : Robert Cuprik <robertcuprik@hotmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
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
        
        <h:menu_tag />
        
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
        
    </body>
</html>
