<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thong
  Date: 23/02/2024
  Time: 9:18 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Management Application</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

</head>
<body>
    <h2>List of Student</h2>
    <div class="container">
        <div class="row">
            <div class="col-4">
                <a href="students?action=create"><button class="btn btn-primary d-flex">Add</button></a>
            </div>
            <div class="col-8">
                    <form class="d-flex" role="search">
                    <div class="input-group">
                        <input type="hidden" name="action" value="search"/>
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>DateOfBirth</th>
            <th>Email</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Classroom</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="student" items="${studentList}">
            <tr>
                <td><c:out value="${student.id}"/></td>
                <td><c:out value="${student.name}"/></td>
                <td><c:out value="${student.dob}"/></td>
                <td><c:out value="${student.email}"/></td>
                <td><c:out value="${student.address}"/></td>
                <td><c:out value="${student.phone}"/></td>
                <td><c:out value="${student.classroom}"/></td>
                <td>
                    <a href="/students?action=edit&id=${student.id}"><button class="btn btn-warning">Edit</button></a>
                    <a href="/students?action=delete&id=${student.id}"><button class="btn btn-danger">Delete</button></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
