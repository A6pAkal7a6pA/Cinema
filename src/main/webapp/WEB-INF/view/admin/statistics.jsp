<%@include file="../header.jsp" %>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8" %>
<c:if test="${role != 'ADMIN'}">
    <c:redirect url="/"/>
</c:if>

<h1 class="hire__title table__title">Films by popularity</h1>
<a href="statistics?periodFilms=day">Day</a>
<a href="statistics?periodFilms=week">Week</a>
<a href="statistics?periodFilms=month">Month</a>
<a href="statistics?periodFilms=year">Year</a>
<br>
Total amount: <c:out value="${requestScope.totalAmount.sumPrice}"/>

<table cellspacing="10" class="table">
    <thead>
    <tr>
        <th>Movie title</th>
        <th>Purchased places</th>
        <th>Total profit</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.filmsByPopularity}" var="filmByPopularity">
        <tr>
            <td><c:out value="${filmByPopularity.filmName}"/></td>
            <td><c:out value="${filmByPopularity.purchasedPlaces}"/></td>
            <td><c:out value="${filmByPopularity.sumPrice}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h1 class="hire__title table__title">Amount by period</h1>

<a href="statistics?periodAmount=day">Day</a>
<a href="statistics?periodAmount=week">Week</a>
<a href="statistics?periodAmount=month">Month</a>
<a href="statistics?periodAmount=year">Year</a>

<table cellspacing="10" class="table">
    <thead>
    <tr>
        <th>Date seance</th>
        <th>Amount</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.amountForPeriod}" var="amount" >
        <tr>
            <td><c:out value="${amount.dateSeance}"/></td>
            <td><c:out value="${amount.sumPrice}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<table cellspacing="10" class="table">
    <thead>
    <tr>
        <th>All occupied places</th>
        <th>First half occupied places</th>
        <th>Second half occupied places</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td><c:out value="${requestScope.occupiedPlaces.allOccupiedPlaces}"/></td>
            <td><c:out value="${requestScope.occupiedPlaces.firstHalfDay}"/></td>
            <td><c:out value="${requestScope.occupiedPlaces.secondHalfDay}"/></td>
        </tr>
    </tbody>
</table>

<%@ include file="../footer.jsp" %>