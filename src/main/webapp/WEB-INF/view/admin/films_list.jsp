<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cinema</title>
</head>
<body>
<c:if test="${role != 'ADMIN'}">
    <c:redirect url="/"/>
</c:if>
<h3>List of Films</h3>
<div>
    <a href="<%=request.getContextPath()%>/add_new_film">Add New Film</a>
</div>
<br>
<table border="1" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Cover</th>
        <th>Movie title</th>
        <th>Film director</th>
        <th>Duration</th>
        <th>Description</th>
        <th>Add time</th>
        <th>Editing</th>
    </tr>
    <c:forEach items="${requestScope.listFilms}" var="listFilm">
        <tr>
            <td><c:out value="${listFilm.id}"/></td>
            <td><img width="200" height="300" src="/images/<c:out value="${listFilm.img}"/>" alt=""></td>
            <td><c:out value="${listFilm.name}"/></td>
            <td><c:out value="${listFilm.directedBy}"/></td>
            <td><c:out value="${listFilm.duration}"/></td>
            <td><c:out value="${listFilm.description}"/></td>
            <td><c:out value="${listFilm.date}"/></td>
            <td><a href="edit_film?id=<c:out value='${listFilm.id}' />">Edit</a>
                <a href="delete_film?id=<c:out value='${listFilm.id}' />">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>