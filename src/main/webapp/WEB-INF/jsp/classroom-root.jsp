<%-- 
    Document   : classroom-root
    Created on : Apr 20, 2015, 9:03:20 PM
    Author     : Robert Cuprik <robertcuprik@hotmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h:html_start_tag />
                
        <div class="container">
            Class room root page
            
            <h2>Class rooms</h2>
            <table border="1">
                    <th>rooms</th>
                <c:forEach var="classroom" items="${classrooms}" varStatus="status">
                    <tr>
                        <td><a href="<c:url value="/class-room/${classroom.name}" />" >${classroom.name}</a></td>                          
                    </tr>
                </c:forEach> 
            </table>
            
        </div>
        
<h:html_end_tag />
