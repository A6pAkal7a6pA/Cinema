<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<c:if test="${role == 'USER'}">
    <c:out value="${sessionScope.id}"/>
</c:if>
<h1>Cinema</h1>
<br/>
<a href="/register">Registration</a><br>
<a href="/login">Login</a><br>
<a href="/account">Personal account</a>

    <c:forEach items="${requestScope.listSeances}" var="schedule">
        <div align="center">
        <input type="hidden" name="id" value="<c:out value="${schedule.id}"/>">
        <div><img width="200" height="300" src="/images/<c:out value="${schedule.img}"/>" alt=""></div>
        <div><c:out value="${schedule.filmName}"/></div>
        <div><c:out value="${schedule.date}"/></div>
        <div><c:out value="${schedule.timeSeance}"/></div>
        <div><c:out value="${schedule.priceSeance}"/></div>
        <div><c:out value="${schedule.duration}"/></div>
        <div>/<c:out value="${schedule.numberOfSeats}"/></div>
        <div><c:out value="${schedule.director}"/></div>
        <div><c:out value="${schedule.description}"/></div>
        <input type="hidden" name="filmId" value="<c:out value="${schedule.filmId}"/>">
        <a href="buy_ticket?id=<c:out value='${schedule.id}' />">Buy ticket</a>
        </div>
    </c:forEach>



<c:forEach items="${requestScope.result}" var="res">
    <div><c:out value='${res}'/></div>
</c:forEach>


</body>
</html>
