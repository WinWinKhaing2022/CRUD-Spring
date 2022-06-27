<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
      <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html>
<html>
<head>
<spring:url value="/resourse/css/test.css" var="baseCss" />
	<link href="${baseCss}" rel="stylesheet" />
<link rel="stylesheet" href="test.css">
<title> Student Registration LGN001 </title>
</head>
<body class="login-page-body"> 
  
    <div class="login-page">
      <div class="form">
        <div class="login">
          <div class="login-header">
            <h1>Welcome!</h1>
            <p><div style="color:red;">${error}</div></p>
          </div>
        </div>
        <form:form class="login-form" action="/StudentRegister_Spring/login" method="post" name="confirm" modelAttribute="ubean">
          <form:input type="email" placeholder="email" value="${ubean.email }" path="email"/>
          <form:input type="password" placeholder="Password" value="${ubean.password }" path="password"/>
          <button>login</button>
          <p class="message">Not registered? <a href="#">Create an account</a></p>
        </form:form>
      </div>
    </div>
</body>

</html>