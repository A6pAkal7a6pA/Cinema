<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<html>
<head>
    <title>Cinema</title>
</head>
<body>
<c:if test="${role != 'ADMIN'}" >
    <c:redirect url="/"/>
</c:if>
<h3>List of Seances</h3>
<a href="/add_new_seance">Add new seance</a>
<table border="1" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Movie title</th>
        <th>Date of film</th>
        <th>Time</th>
        <th>Price</th>
        <th>Number of seats</th>
        <th>Editing</th>
    </tr>
    <c:forEach items="${requestScope.listSeances}" var="schedule">
        <tr>
            <td><c:out value="${schedule.id}"/></td>
            <td><c:out value="${schedule.filmName}"/></td>
            <td><c:out value="${schedule.date}"/></td>
            <td><c:out value="${schedule.timeSeance}"/></td>
            <td><c:out value="${schedule.priceSeance}"/></td>
            <td><c:out value="${schedule.numberOfSeats}"/></td>
            <td><a href="edit_seance?id=<c:out value='${schedule.id}' />">Edit</a></td>
            <td><a href="delete_seance?id=<c:out value='${schedule.id}' />">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>