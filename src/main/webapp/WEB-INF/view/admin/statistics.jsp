<%@include file="../header.jsp" %>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8" %>
<c:if test="${role != 'ADMIN'}">
    <c:redirect url="/"/>
</c:if>

<h1 class="hire__title table__title"><fmt:message key="statistics.popular.title" bundle="${bundle}"/></h1>

<div class="stat-control">
    <div class="stat-period">
        <div class="stat-period__select select-stat">
            <div class="select-stat__header">
        <span class="select-stat__current">
          <span class="far fa-clock"></span>
        </span>
            </div>
            <div class="select-stat__body">
                <div class="select-stat__item">
                    <a href="statistics?periodFilms=day" class="select-stat__link">
                        <fmt:message key="statistics.sort.day" bundle="${bundle}"/>
                    </a>
                </div>
                <div class="select-stat__item">
                    <a href="statistics?periodFilms=week" class="select-stat__link">
                        <fmt:message key="statistics.sort.week" bundle="${bundle}"/>
                    </a>
                </div>
                <div class="select-stat__item">
                    <a href="statistics?periodFilms=month" class="select-stat__link">
                        <fmt:message key="statistics.sort.month" bundle="${bundle}"/>
                    </a>
                </div>
                <div class="select-stat__item">
                    <a href="statistics?periodFilms=year" class="select-stat__link">
                        <fmt:message key="statistics.sort.year" bundle="${bundle}"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="stat-total">
        <div class="stat-total__body">
            <fmt:message key="statistics.popular.total" bundle="${bundle}"/>:
            <span class="stat-total__amount"><c:out value="${requestScope.totalAmount.sumPrice}"/></span>
        </div>
    </div>
</div>

<table cellspacing="10" class="table">
    <thead>
    <tr>
        <th><fmt:message key="statistics.popular.movietitle" bundle="${bundle}"/></th>
        <th><fmt:message key="statistics.popular.purchased" bundle="${bundle}"/></th>
        <th><fmt:message key="statistics.popular.total" bundle="${bundle}"/></th>
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

<h1 class="hire__title table__title"><fmt:message key="statistics.amount.title" bundle="${bundle}"/></h1>
<div class="stat-control">


    <div class="stat-period">
        <div class="stat-period__select select-stat">
            <div class="select-stat__header">
        <span class="select-stat__current">
          <span class="far fa-clock"></span>
        </span>
            </div>
            <div class="select-stat__body">
                <div class="select-stat__item">
                    <a href="statistics?periodAmount=day" class="select-stat__link">
                        <fmt:message key="statistics.sort.day" bundle="${bundle}"/>
                    </a>
                </div>
                <div class="select-stat__item">
                    <a href="statistics?periodAmount=week" class="select-stat__link">
                        <fmt:message key="statistics.sort.week" bundle="${bundle}"/>
                    </a>
                </div>
                <div class="select-stat__item">
                    <a href="statistics?periodAmount=month" class="select-stat__link">
                        <fmt:message key="statistics.sort.month" bundle="${bundle}"/>
                    </a>
                </div>
                <div class="select-stat__item">
                    <a href="statistics?periodAmount=year" class="select-stat__link">
                        <fmt:message key="statistics.sort.year" bundle="${bundle}"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<table cellspacing="10" class="table">
    <thead>
    <tr>
        <th><fmt:message key="statistics.amount.date" bundle="${bundle}"/></th>
        <th><fmt:message key="statistics.amount.amount" bundle="${bundle}"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.amountForPeriod}" var="amount">
        <tr>
            <td><c:out value="${amount.dateSeance}"/></td>
            <td><c:out value="${amount.sumPrice}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h1 class="hire__title table__title"><fmt:message key="statistics.seats.title" bundle="${bundle}"/></h1>

<table cellspacing="10" class="table">
    <thead>
    <tr>
        <th><fmt:message key="statistics.seats.allpurchased" bundle="${bundle}"/></th>
        <th><fmt:message key="statistics.seats.firts" bundle="${bundle}"/></th>
        <th><fmt:message key="statistics.seats.second" bundle="${bundle}"/></th>
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