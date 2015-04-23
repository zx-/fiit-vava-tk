<%-- 
    Document   : html_end_tag
    Created on : Apr 20, 2015, 3:31:00 PM
    Author     : Robert Cuprik <robertcuprik@hotmail.com>
--%>

<%@tag description="html end " pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%--<%@attribute name="message"%>--%>

<%-- any content can be specified here e.g.: --%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

    
<div class="push"></div>
</div>

    <footer class="footer">
      <div class="container">
        <p class="text-muted">
            <spring:message code="common.language" /> : <a href="?locale=en"><spring:message code="common.en" /></a> | <a href="?locale=sk"><spring:message code="common.sk" /></a>
            
        </p>
        <a href="https://github.com/zx-/fiit-vava-tk" target="_blank" >
            <img width="25" src="<c:url value="/resources/imgs/GitHub-Mark-64px.png" />" />
        </a>
      </div>
    </footer>
    

</body>
</html>