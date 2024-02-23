<%--
  Created by IntelliJ IDEA.
  User: thong
  Date: 23/02/2024
  Time: 10:16 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student Management Application</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</head>
<body>
<center>
    <h1>Student Management</h1>
    <h2>
        <a href="/students">List All Student</a>
    </h2>
</center>
<div class="container mt-5">
    <h2>Edit Student info</h2>
    <form method="post">
        <c:if test="${student != null}">
            <input type="hidden" name="id" value="${student.id}"/>
        </c:if>
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" placeholder="Enter name" name="name" value='${student.name}'>
        </div>

        <div class="form-group">
            <label for="email">Email address:</label>
            <input type="email" class="form-control" id ="email" placeholder ="Enter email address"name ="email" value='${student.email}'>
        </div>

        <!-- Date of Birth -->
        <div class = "form-group">
            <label for = "dob">Date of Birth:</label >
            <input type = "text" pattern="\d{1,2}/\d{1,2}/\d{4}" title ="DD/MM/YYYY format required" class = "form-control" id = "dob"
                   placeholder ="Enter date of birth as DD/MM/YYYY" name = "dob" value='${student.dob}'>
        </div>

        <!-- Address -->
        <div class = "form-group">
            <label for = "address">Address:</label >
            <input type = "text" class = "form-control" id ="address" placeholder ="Enter address" name="address" value='${student.address}'>
        </div >

        <!-- Phone Number-->
        <div class ="form-group">
            <label for ="phone">Phone Number :</label><br>
            <input type="tel" class ="form-control" id ="phone" placeholder ="Enter phone number" name ="phone" value='${student.phone}'>
        </div >
        <div class="form-group">
            <label for="classroom">Class:</label>
            <select name="classroom" id="classroom" class ="form-control">
                <c:forEach var="classroom" items="${classroomList}">
                    <option value="${classroom.id}" <c:if test="${student.classroom eq classroom.name}">selected</c:if>
                    >${classroom.name}</option>
                </c:forEach>
            </select>


        </div>
        <br>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
