<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<html>
<head>
    <title>Cinema</title>
</head>
<body>
<h1>Admin profile</h1>
<h3>Hello <c:out value="${userName}"/></h3>
<a href="<%=request.getContextPath()%>/">Back to main menu</a><br>
<a href="<%=request.getContextPath()%>/account/users_list">List of Users</a><br>
<a href="<%=request.getContextPath()%>/account/films_list">List of Films</a><br>
<a href="<%=request.getContextPath()%>/account/seances_list">List of Seances</a><br>
<a href="/logout">Logout</a>
<%--<h3>List of Users</h3>--%>
<%--<table border="1" cellspacing="0">--%>
<%--    <tr>--%>
<%--        <th>ID</th>--%>
<%--        <th>First Name</th>--%>
<%--        <th>Last Name</th>--%>
<%--        <th>Login</th>--%>
<%--        <th>Contact Number</th>--%>
<%--        <th>Role</th>--%>
<%--        <th>Email</th>--%>
<%--        <th>Date of registration</th>--%>
<%--    </tr>--%>
<%--    <c:forEach items="${requestScope.listUsers}" var="currentUserProfile">--%>
<%--        <tr>--%>
<%--            <td><c:out value="${currentUserProfile.id}"/></td>--%>
<%--            <td><c:out value="${currentUserProfile.firstName}"/></td>--%>
<%--            <td><c:out value="${currentUserProfile.lastName}"/></td>--%>
<%--            <td><c:out value="${currentUserProfile.login}"/></td>--%>
<%--            <td><c:out value="${currentUserProfile.contact}"/></td>--%>
<%--            <td><c:out value="${currentUserProfile.role}"/></td>--%>
<%--            <td><c:out value="${currentUserProfile.email}"/></td>--%>
<%--            <td><c:out value="${currentUserProfile.date}"/></td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>

<%--<h3>List of Films</h3>--%>
<%--<hr>--%>
<%--<div>--%>
<%--    <a href="<%=request.getContextPath()%>/add_new_film">Add New Film</a>--%>
<%--</div>--%>
<%--<br>--%>
<%--<table border="1" cellspacing="0">--%>
<%--    <tr>--%>
<%--        <th>ID</th>--%>
<%--        <th>Movie title</th>--%>
<%--        <th>Film director</th>--%>
<%--        <th>Duration</th>--%>
<%--        <th>Description</th>--%>
<%--        <th>Add time</th>--%>
<%--        <th>Editing</th>--%>
<%--    </tr>--%>
<%--    <c:forEach items="${requestScope.listFilms}" var="filmsss">--%>
<%--        <tr>--%>
<%--            <td><c:out value="${filmsss.id}"/></td>--%>
<%--            <td><c:out value="${filmsss.name}"/></td>--%>
<%--            <td><c:out value="${filmsss.directedBy}"/></td>--%>
<%--            <td><c:out value="${filmsss.duration}"/></td>--%>
<%--            <td><c:out value="${filmsss.description}"/></td>--%>
<%--            <td><c:out value="${filmsss.date}"/></td>--%>
<%--            <td><a href="edit_film?id=<c:out value='${filmsss.id}' />">Edit</a>--%>
<%--                <a href="delete_film?id=<c:out value='${filmsss.id}' />">Delete</a>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>


<%--<h3>List of Seances</h3>--%>
<%--<a href="/add_new_seance">Add new seance</a>--%>
<%--<table border="1" cellspacing="0">--%>
<%--    <tr>--%>
<%--        <th>ID</th>--%>
<%--        <th>Movie title</th>--%>
<%--        <th>Date of film</th>--%>
<%--        <th>Time</th>--%>
<%--        <th>Price</th>--%>
<%--        <th>Number of seats</th>--%>
<%--        <th>Editing</th>--%>
<%--    </tr>--%>
<%--    <c:forEach items="${requestScope.listSeances}" var="schedule">--%>
<%--        <tr>--%>
<%--            <td><c:out value="${schedule.id}"/></td>--%>
<%--            <td><c:out value="${schedule.filmName}"/></td>--%>
<%--            <td><c:out value="${schedule.date}"/></td>--%>
<%--            <td><c:out value="${schedule.timeSeance}"/></td>--%>
<%--            <td><c:out value="${schedule.priceSeance}"/></td>--%>
<%--            <td><c:out value="${schedule.numberOfSeats}"/></td>--%>
<%--            <td><a href="delete_seance?id=<c:out value='${schedule.id}' />">Delete</a></td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>
</body>
</html>