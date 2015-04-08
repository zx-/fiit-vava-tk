<%-- 
    Document   : login
    Created on : Mar 30, 2015, 9:18:40 PM
    Author     : Robert Cuprik <robertcuprik@hotmail.com>
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <title>Vava Triedna kniha</title>
    </head>
    <body onload='document.loginForm.username.focus();'>

        <h1>Spring Security Custom Login Form (XML)</h1>

        <div id="login-box">

            <h3>Login with Username and Password</h3>

            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>

            <form method="post" action="<c:url value='j_spring_security_check'/>" >
                <table>
                    <tbody>
                        <tr>
                            <td>Login:</td>
                            <td><input type="text" name="j_username" id="j_username"size="30" maxlength="40" /></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input type="password" name="j_password" id="j_password" size="30" maxlength="32" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input type="submit" value="Login" /></td>
                        </tr>
                    </tbody>
                </table>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </form> 



        </div>

    </body>
</html>
