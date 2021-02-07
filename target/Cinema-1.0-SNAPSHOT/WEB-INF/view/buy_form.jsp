<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cinema</title>
</head>
<body>
<h1>Buy ticket</h1>
<c:if test="${role == 'USER'}">
    User N <input type="hidden" value="<c:out value='${sessionScope.id}' />" name="userId"/>
</c:if>

<form action="/buy_ticket" method="post">
    <c:if test="${not empty seance}">
        <input type="hidden" value="<c:out value='${seance.id}' />" name="id"/>
    </c:if>
    <c:forEach items="${requestScope.mapPlaces}" var="entry">
        <input type="checkbox" name="hall_check" value="<c:out value='${entry.key}' />" <c:out value="${entry.value}"/>/>
    </c:forEach>
    <button type="submit">Buy</button>
</form>

</body>
</html>