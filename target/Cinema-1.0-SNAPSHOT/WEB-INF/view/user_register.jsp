<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Cinema</title>
</head>
<body>
<c:if test="${login != null}" >
    <c:redirect url="/account"/>
</c:if>
<div align="center">
    <h1>User Register Form</h1>
    <form action="<%= request.getContextPath() %>/register" method="post">
        <table>
            <tr>
                <td>First Name</td>
                <td><input type="text" name="firstName"/></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastName"/></td>
            </tr>
            <tr>
                <td>Login</td>
                <td><input type="text" name="login"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td>Contact </td>
                <td><input type="text" name="contact"/></td>
            </tr>
            <tr>
                <td>Email </td>
                <td><input type="text" name="email"/></td>
            </tr>
        </table>
        <button type="submit">Register</button>
    </form>
</div>
</body>
</html>