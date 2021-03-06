<%-- 
    Document   : admin
    Created on : Mar 30, 2015, 7:38:38 PM
    Author     : Robert Cuprik <robertcuprik@hotmail.com>
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
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
	
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>
 
	
 
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | 
		</h2>
	</c:if>
        
        <p>
            <a href="<c:url value="/admin/populate" />">Populate DATABASE</a> | 
            <a href="<c:url value="/" />">Home</a>
        </p>
 
</body>
</html>
