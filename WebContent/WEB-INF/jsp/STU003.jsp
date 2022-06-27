<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
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
      <form action="/StudentRegister_Spring/searchstudent" method="post">
      
        <div class="col-auto">
          <label for="staticEmail2" class="visually-hidden">studentID</label>
          <input type="text"  class="form-control" id="staticEmail2" placeholder="Student ID" name="id" >
        </div>
        <div class="col-auto">
          <label for="inputPassword2" class="visually-hidden">studentName</label>
          <input type="text" class="form-control" id="inputPassword2" placeholder="Student Name" name="name">
        </div>
        <div class="col-auto">
            <label for="inputPassword2" class="visually-hidden">Course</label>
            <input type="text" class="form-control" id="inputPassword2" placeholder="Course" name="attendCourses">
          </div>
        <div class="col-auto">
          <button type="submit" class="btn btn-success mb-3">Search</button>
        </div>
        <div class="col-auto">
          <button type="reset" class="btn btn-secondary mb-3">Reset</button>
        </div>
      </form>
<div>
      <table class="table table-striped" id="stduentTable">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Student ID</th>
            <th scope="col">Name</th>
            <th scope="col">Course Name</th>
            <th scope="col">Details</th>
          </tr>
        </thead>
        <tbody>
       
          							<%-- 	<c:set var="courseList"  value="${coursesLists}" scope="page"/> --%>
									<c:set var="count" value="0" scope="page" />
									<c:forEach var="student" items="${studentList}" varStatus="loop">
										<tr>
											<c:set var="count" value="${count + 1}" scope="page" />
											<th scope="row">${count}</th>
											<td>${student.id}</td>
											<td>${student.name}</td>
											<td><c:forEach var="course" items="${coursesLists[loop.index] }">
													${course.name},
												</c:forEach></td>
											<td><a href='/StudentRegister_Spring/seemore/${student.id}'><button type="submit"
														class="btn btn-secondary mb-2">See More</button></a></td>
										</tr>
									</c:forEach>
								
        </tbody>
      </table>
    </div>
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