<%@include file="../header.jsp" %>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8" %>
<c:if test="${role != 'ADMIN'}">
    <c:redirect url="/"/>
</c:if>
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<h2>Films by popularity</h2>
<a href="statistics?periodFilms=day">Day</a>
<a href="statistics?periodFilms=week">Week</a>
<a href="statistics?periodFilms=month">Month</a>
<a href="statistics?periodFilms=year">Year</a>
<c:forEach items="${requestScope.filmsByPopularity}" var="filmByPopularity">
    Movie title: <c:out value="${filmByPopularity.filmName}"/>
    Bought tickets: <c:out value="${filmByPopularity.purchasedPlaces}"/>
    Amount of revenue: <c:out value="${filmByPopularity.sumPrice}"/>
    <br>
</c:forEach>
<br>

<a href="statistics?periodAmount=day">Day</a>
<a href="statistics?periodAmount=week">Week</a>
<a href="statistics?periodAmount=month">Month</a>
<a href="statistics?periodAmount=year">Year</a>
<c:forEach items="${requestScope.amountForPeriod}" var="sumForPeriod">
    Date seance: <c:out value="${sumForPeriod.dateSeance}"/>
    Amount price: <c:out value="${sumForPeriod.sumPrice}"/>
    <br>
</c:forEach>

All occupied places<c:out value="${requestScope.occupiedPlaces.allOccupiedPlaces}"/>
First half occupied places<c:out value="${requestScope.occupiedPlaces.firstHalfDay}"/>
Second half occupied places<c:out value="${requestScope.occupiedPlaces.secondHalfDay}"/>

<%@ include file="../footer.jsp" %>