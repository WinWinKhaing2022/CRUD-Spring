<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">

<head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="test.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        
        <spring:url value="/resourse/css/test.css" var="baseCss" />
	<link href="${baseCss}" rel="stylesheet" />
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
    
        <title>Course Registration</title>
</head>

<body>
<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if (session.getAttribute("userdata") == null) {
		response.sendRedirect("/StudentRegister_Spring/login");
	}
	%> 
    <%@ include file="header.jsp" %>
    <!-- <div id="testsidebar">Hello World </div> -->
    <div class="container">
   <%@ include file="sidenav.jsp" %>
      <div class="main_contents">
    <div id="sub_content">
    <p>
	<div style="color:red;">${error}</div>
    <div style="color:red;">${msg}</div>
        <form:form  action="/StudentRegister_Spring/UpdateUser" method="post" modelAttribute="ubean">

        <h2 class="col-md-6 offset-md-2 mb-5 mt-4">User Update</h2>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="email" class="col-md-2 col-form-label">ID</label>
            <div class="col-md-4">
               <form:input type="hidden" path="id" value="${ubean.id}"/>
            </div>
		</div>
		<div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="name" class="col-md-2 col-form-label">name</label>
            <div class="col-md-4">
                <form:input type="text" class="form-control" id="name" value="${ubean.name}" path="name"/>
            </div>
		</div>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="email" class="col-md-2 col-form-label">Email</label>
            <div class="col-md-4">
                <form:input type="email" class="form-control" id="email" value="${ubean.email}" path="email"/>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="Passowrd" class="col-md-2 col-form-label">Passowrd</label>
            <div class="col-md-4">
                <form:input type="password" class="form-control" id="name" value="${ubean.password }" path="password"/>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="confirmPassword" class="col-md-2 col-form-label">Confirm Passowrd</label>
            <div class="col-md-4">
                <input type="password" class="form-control" id="confirmPassword" value="${ubean.password }" name="cpassword">
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="userRole" class="col-md-2 col-form-label">User Role</label>
            <div class="col-md-4">
                <form:select class="form-select" aria-label="Education" id="userRole" path="role">
  								<c:choose>
									<c:when test="${ubean.role eq \"Admin\"}">
										<form:option value="Admin">Admin</form:option>
										<form:option value="User">User</form:option>
									</c:when>
									<c:otherwise>
										<form:option value="User">User</form:option>
										<form:option value="Admin">Admin</form:option>
									</c:otherwise>
								</c:choose>
							</form:select>
            </div>
        </div>
       
            <div class="col-md-4"></div>

            <div class="col-md-6">
               

                <button type="submit" class="btn btn-success col-md-2" data-bs-toggle="modal" data-bs-target="#exampleModal">Update</button>
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">User Update</h5>
                                <button type="submit" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                               
                               <h5 style="color: rgb(127, 209, 131);">Succesfully Updated !</h5>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-success col-md-2" data-bs-dismiss="modal">Ok</button>
                               
                            </div>
                        </div>
                    </div>
            </div>
            <button type="button" class="btn btn-secondary col-md-2 " onclick="location.href = '/StudentRegister_Spring/showuser';">
                Back
            </button>
    

        </div>
        </form:form>
    </div>
</div>
</div>
        <div id="testfooter">
            <span>Copyright &#169; ACE Inspiration 2022</span>
        </div>
        <script>
            /* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
            var dropdown = document.getElementsByClassName("dropdown-btn");
            var i;
            
            for (i = 0; i < dropdown.length; i++) {
              dropdown[i].addEventListener("click", function() {
              this.classList.toggle("active");
              var dropdownContent = this.nextElementSibling;
              if (dropdownContent.style.display === "block") {
              dropdownContent.style.display = "none";
              } else {
              dropdownContent.style.display = "block";
              }
              });
            }
            </script>
</body>

</html>

    


    
    