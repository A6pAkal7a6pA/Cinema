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
<h3>List of Users</h3>
<table border="1" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Login</th>
        <th>Contact Number</th>
        <th>Role</th>
        <th>Email</th>
        <th>Date of registration</th>
    </tr>
    <c:forEach items="${requestScope.listUsers}" var="currentUserProfile">
        <tr>
            <td><c:out value="${currentUserProfile.id}"/></td>
            <td><c:out value="${currentUserProfile.firstName}"/></td>
            <td><c:out value="${currentUserProfile.lastName}"/></td>
            <td><c:out value="${currentUserProfile.login}"/></td>
            <td><c:out value="${currentUserProfile.contact}"/></td>
            <td><c:out value="${currentUserProfile.role}"/></td>
            <td><c:out value="${currentUserProfile.email}"/></td>
            <td><c:out value="${currentUserProfile.date}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>