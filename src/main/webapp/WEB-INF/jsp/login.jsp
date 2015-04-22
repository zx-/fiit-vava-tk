<%-- 
    Document   : login
    Created on : Mar 30, 2015, 9:18:40 PM
    Author     : Robert Cuprik <robertcuprik@hotmail.com>
--%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h:html_start_tag />

<div class="container" >
        <h1>Vava FIIT 2015</h1>

        <div id="login-box" onload='document.loginForm.username.focus();'>

            <h3>Login with Username and Password</h3>

            <c:if test="${not empty error}">
                <div class="error">${error}
                    <p>${sessionScope["SPRING_SECURITY_LAST_EXCPETION"].message}</p>
                </div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>

            <form method="post" action="<c:url value='j_spring_security_check'/>" >
                Login:
                <input type="text" name="j_username" id="j_username"size="30" maxlength="40" />
                Password:
                <input type="password" name="j_password" id="j_password" size="30" maxlength="32" />
                <td><input type="submit" value="Login" /></td>
                      
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </form> 

        </div>
            
</div>
            
<h:html_end_tag />
