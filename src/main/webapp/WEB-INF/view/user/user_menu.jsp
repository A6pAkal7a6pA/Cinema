<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<html>
<head>
    <title>Cinema</title>
</head>
<body>
<h1>User profile</h1>
<h3>Hello <c:out value="${requestScope.userName}"/></h3>
<a href="<%=request.getContextPath()%>/">Back to main menu</a>
<table>
    <c:forEach items="${requestScope.userProfile}" var="currentUserProfile">
        <tr>
            <td>First name:</td>
            <td><c:out value="${currentUserProfile.firstName}"/></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><c:out value="${currentUserProfile.lastName}"/></td>
        </tr>
        <tr>
            <td>Contact:</td>
            <td><c:out value="${currentUserProfile.contact}"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><c:out value="${currentUserProfile.email}"/></td>
        </tr>
        <tr>
            <td>Date of registration:</td>
            <td><c:out value="${currentUserProfile.date}"/></td>
        </tr>
    </c:forEach>
</table>
<a href="/logout">Logout</a>
</body>
</html>