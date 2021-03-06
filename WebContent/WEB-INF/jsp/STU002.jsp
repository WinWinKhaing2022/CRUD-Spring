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
        
        <spring:url value="/resourse/css/test.css" var="baseCss" />
	<link href="${baseCss}" rel="stylesheet" />

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
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
        <form:form action="/StudentRegister_Spring/updatestudent" method="post" modelAttribute="data">

            <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student Details</h2>
             <h3 style="color: red; text-align: center;">${error}</h3>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="studentID" class="col-md-2 col-form-label">Student ID</label>
                <div class="col-md-4">
                <form:input  class="form-control" value="${data.id}" path="id" id="studentID" readonly="readonly" />
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="name" class="col-md-2 col-form-label">Name</label>
                <div class="col-md-4">
                    <form:input type="text" class="form-control" id="name" value="${data.name }" path="name"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="dob" class="col-md-2 col-form-label">DOB</label>
                <div class="col-md-4">
                    <form:input type="date" class="form-control" id="dob" value="${data.dob}" path="dob"/>
                </div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Gender</legend>
                <div class="col-md-4">
                    <c:choose>
								<c:when test="${empty data.gender}">

									<div class="form-check-inline">
										<form:radiobutton class="form-check-input"  path="gender"
											id="gridRadios1" value="Male" checked="checked"/> <label
											class="form-check-label" for="gridRadios1">Male</label>
									</div>
									<div class="form-check-inline">
										<form:radiobutton class="form-check-input" path="gender"
											id="gridRadios2" value="Female"/> <label
											class="form-check-label" for="gridRadios2">Female</label>
									</div>
									<!--  -->
								</c:when>
								<c:otherwise>
									<c:if test="${data.gender eq \"Male\"}">
										<div class="form-check-inline">
											<form:radiobutton class="form-check-input" path="gender"
												id="gridRadios1" value="Male" checked="checked"/> <label
												class="form-check-label" for="gridRadios1"> Male </label>
										</div>
										<div class="form-check-inline">
											<form:radiobutton class="form-check-input" path="gender"
												id="gridRadios2" value="Female"/> <label
												class="form-check-label" for="gridRadios2">Female</label>
										</div>
									</c:if>
									<c:if test="${data.gender eq \"Female\"}">
										<div class="form-check-inline">
											<form:radiobutton class="form-check-input" path="gender"
												id="gridRadios1" value="Male"/> <label
												class="form-check-label" for="gridRadios1"> Male </label>
										</div>
										<div class="form-check-inline">
											<form:radiobutton class="form-check-input" path="gender"
												id="gridRadios2" value="Female" checked="checked"/> <label
												class="form-check-label" for="gridRadios2">Female</label>
										</div>
									</c:if>
								</c:otherwise>
							</c:choose>
							</div>
            </fieldset>
    
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="phone" class="col-md-2 col-form-label">Phone</label>
                <div class="col-md-4">
                    <form:input type="text" class="form-control" id="phone" value="${data.phone }" path= "phone"/>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-2"></div>
                <label for="education" class="col-md-2 col-form-label">Education</label>
                <div class="col-md-4">
                    <form:select class="form-select" aria-label="Education" id="education" path = "education">
								<c:if test="${not empty data.education}">
									<form:option value="${data.education}">${data.education}</form:option>
								</c:if>
								<c:if
									test="${data.education != \"Bachelor of Information Technology\"}">
									<form:option value="Bachelor of Information Technology">Bachelor
										of Information Technology</form:option>
								</c:if>
								<c:if test="${data.education != \"Diploma in IT\"}">
									<form:option value="Diploma in IT">Diploma in IT</form:option>
								</c:if>
								<c:if
									test="${data.education != \"Bachelor of Computer Science\"}">
									<form:option value="Bachelor of Computer Science">Bachelor
										of Computer Science</form:option>
								</c:if>
								
							</form:select>
                </div>
            </div>
            <fieldset class="row mb-4">
                <div class="col-md-2"></div>
                <legend class="col-form-label col-md-2 pt-0">Attend</legend>
    
                <div class="col-md-4">
                  
								<div class="col-md-6 offset-md-4 mt-4">
									<c:forEach var="course" items="${courses}">
										<div class="form-check-inline col-md-2">
											<form:checkbox class="form-check-input" path="attendCourses" id="gridRadios1" value="${course.id}" />
											<label class="form-check-label" for="gridRadios1">${course.name}</label>
										</div>
									</c:forEach>
								</div>
							
                    </div>
                     <div class="col-md-4">
         <!--         <c:choose>
							<c:when test="${empty courses}">
								<div class="col-md-6 offset-md-4 mt-4">
									<c:forEach var="course" items="${courses}">
										<div class="form-check-inline col-md-2">
											<form:checkbox class="form-check-input" 
											
												path="attendCourses" id="gridRadios1" value="${course.id}"/>
											<label class="form-check-label" for="gridRadios1">${course.name}</label>
										</div>
									</c:forEach>
								</div>
							</c:when>
							<c:otherwise>
								<div class="col-md-6 offset-md-4 mt-4">
									<c:forEach var="course" items="${courses}">
										<div class="form-check-inline col-md-2">
											<form:checkbox class="form-check-input" 
												path="attendCourses" id="gridRadios1" value="${course.id}"/>
											<label class="form-check-label" for="gridRadios1">${course.name}</label>
										</div>
									</c:forEach>
								</div>
							</c:otherwise>
						</c:choose>-->
                    </div>
            </fieldset>
            
    
            <div class="row mb-4">
                <div class="col-md-4"></div>
    
                <div class="col-md-4">
                   
                        <button type="submit" class="btn btn-secondary">
                          
                            <span>Update</span>
                        </button>
                   
    
                    <!-- Button trigger modal -->
                 <button type="button" class="btn btn-danger" onclick="location.href = '/StudentRegister_Spring/Deletestudent/${data.id}'">
                         
                       <span>Delete</span> 
                    </button>
    
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                        aria-hidden="true">
                        <div class="modal-dialog modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Student Deletion</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                                        aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    Are you sure you want to delete?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Ok</button>
                                    <button type="button" class="btn btn-danger">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
    
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

    