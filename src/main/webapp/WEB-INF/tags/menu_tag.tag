<%-- 
    Document   : menu_tag
    Created on : Apr 19, 2015, 8:43:25 PM
    Author     : Robert Cuprik <robertcuprik@hotmail.com>
--%>

<%@tag description="Upper Menu" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%--<%@attribute name="message"%>--%>

<%-- any content can be specified here e.g.: --%>


<div class="container">
<nav id="main-menu" class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<c:url value="/" />">VAVA FIIT 2015</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <sec:authorize access="isAuthenticated()"> 
        
            <c:url value="/j_spring_security_logout" var="logoutUrl" />

            <!-- csrt for log out-->
            <form action="${logoutUrl}" method="post" id="logoutForm">
              <input type="hidden" 
                    name="${_csrf.parameterName}"
                    value="${_csrf.token}" />
            </form>

            <script>
                    function formSubmit() {
                            document.getElementById("logoutForm").submit();
                    }
            </script>

            <li><span><sec:authentication property="principal.username" /><span></li>
            <sec:authorize access="hasRole('ROLE_STUDENT')">

                <li><a href="<c:url value="/student/timetable" />"> <spring:message code="student.timetable" /></a></li> 
                <li>
                    <a href="<c:url value="/student/report" />">report</a> 
                </li>
                
            </sec:authorize>
                <sec:authorize access="hasRole('ROLE_TEACHER')">

                <li><a href="<c:url value="/teacher/timetable" />"> <spring:message code="student.timetable" /></a></li> 
                <li><a href="<c:url value="/class-room" />"> <spring:message code="common.class-room" /></a></li> 

            </sec:authorize>

            <li><a href="javascript:formSubmit()"> <spring:message code="utils.logout" /></a></li>    

        </sec:authorize>
    <sec:authorize access="!isAuthenticated()"> 
        
        <li><span><spring:message code="welcome.greeting" /></span></li> 
        <li><a href="<c:url value="/user-login" />" ><spring:message code="utils.login" /></a></li>  
    
    </sec:authorize>
        
      </ul>
       
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</div>