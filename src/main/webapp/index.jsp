<%@include file="WEB-INF/view/header.jsp" %>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8" %>
<%@taglib prefix="util" tagdir="/WEB-INF/tags" %>
<section class="cover">
    <div class="cover__body">
        <div class="cover__slider slider-cover">
            <div class="slider-cover__body">
                <c:forEach items="${requestScope.covers}" var="seance">
                    <div class="slider-cover__item">
                        <input type="hidden" name="id" value="<c:out value="${seance.id}"/>">
                        <a href="buy_ticket?id=<c:out value="${seance.id}"/>">
                            <img src="<%=request.getContextPath()%>/images/<c:out value="${seance.img}"/>"
                                 alt="images">
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

<section class="hire">
    <div class="hire__body">
        <div class="hire__title">
            <fmt:message key="poster.title" bundle="${bundle}" var="postitle"/>
            ${postitle}
        </div>
        <div class="hire__subltitle">
            <fmt:message key="poster.subtitle" bundle="${bundle}" var="possubt"/>
            ${possubt}
        </div>
        <div class="hire__main-content">

            <div class="hire__sidebar sidebar">
                <div class="sidebar__body">
                    <div class="sidebar__sort sort-by">
                        <div class="sort-by__body">
                            <div class="sort-by__title">
                                <fmt:message key="poster.sidebar.sort.title" bundle="${bundle}" var="possoti"/>
                                ${possoti}:
                            </div>
                            <div class="sort-by__content">
                                <div class="sort-by__item">
                                    <a href="<%=request.getContextPath()%>/?sortRequest=dateTimeSortAsc"
                                       class="sort-by__link">
                                        <div class="sort-by__title">
                                            <fmt:message key="poster.sidebar.sort.datetime" bundle="${bundle}" var="possodt"/>
                                            ${possodt}
                                        </div>
                                    </a>
                                </div>
                                <div class="sort-by__item">
                                    <a href="<%=request.getContextPath()%>/?sortRequest=movieTitleSortAsc"
                                       class="sort-by__link">
                                        <fmt:message key="poster.sidebar.sort.movietitle" bundle="${bundle}" var="possomot"/>
                                        ${possomot}
                                    </a>
                                </div>
                                <div class="sort-by__item">
                                    <a href="<%=request.getContextPath()%>/?sortRequest=availableSeatsSortAsc"
                                       class="sort-by__link">
                                        <fmt:message key="poster.sidebar.sort.availableseats" bundle="${bundle}" var="possoaval"/>
                                        ${possoaval}
                                    </a>
                                </div>
                                <div class="sort-by__item">
                                    <a href="<%=request.getContextPath()%>/?sortRequest=priceSortAsc"
                                       class="sort-by__link">
                                        <fmt:message key="poster.sidebar.sort.price" bundle="${bundle}" var="possoprice"/>
                                        ${possoprice}
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="hire__content content-hire">
                <div class="hire__pagination pagination">
                    <div class="pagination__body">
                        <util:navigation path="<%=request.getContextPath()%>" page="${requestScope.page}"
                                         maxPage="${requestScope.listPagination.size()}"></util:navigation>
                    </div>
                </div>
                <div class="content-hire__body">
                    <c:forEach items="${requestScope.listSeances}" var="schedule">
                        <div class="content-hire__item">
                            <input type="hidden" name="id" value="<c:out value="${schedule.id}"/>">
                            <div class="content-hire__image">
                                <img src="<%=request.getContextPath()%>/images/<c:out value="${schedule.img}"/>" alt="">
                            </div>
                            <div class="content-hire__main">
                                <div class="content-hire__main-title">
                                    <a class="content-hire__main-title-link" href="#"><c:out
                                            value="${schedule.filmName}"/></a>
                                    <div class="content-hire__datetime">
                                        <span class="content-hire__date"><c:out value="${schedule.date}"/></span>
                                        <span class="content-hire__time"><c:out
                                                value="${schedule.timeSeance}"/></span>
                                    </div>
                                </div>
                                <p class="content-hire__price">
                                    <fmt:message key="poster.sessions.cost" bundle="${bundle}" var="posseco"/>
                                        ${posseco}: <c:out value="${schedule.priceSeance}"/></p>
                                <p class="content-hire__places">
                                    <fmt:message key="poster.sessions.avalible" bundle="${bundle}" var="posseaval"/>
                                        ${posseaval}:
                                    <c:out value="${schedule.freePlaces}"/>/<c:out value="${schedule.numberOfSeats}"/></p>
                                <p class="content-hire__duration">
                                    <fmt:message key="poster.sessions.duration" bundle="${bundle}" var="possedur"/>
                                        ${possedur}:
                                    <c:out value="${schedule.duration}"/>
                                    <fmt:message key="poster.sessions.minutes" bundle="${bundle}" var="possemin"/>
                                        ${possemin}
                                </p>
                                <p class="content-hire__duration">
                                    <fmt:message key="poster.sessions.director" bundle="${bundle}" var="possedir"/>
                                        ${possedir}:
                                    <c:out value="${schedule.director}"/>
                                </p>
                                <p class="content-hire__description">
                                    <c:out value="${schedule.description}"/>
                                </p>
                                <input type="hidden" name="filmId" value="<c:out value="${schedule.filmId}"/>">
                                <div class="content-hire__button button">
                                    <a href="buy_ticket?id=<c:out value='${schedule.id}' />" class="button__link">
                                        <fmt:message key="poster.sessions.button.choose" bundle="${bundle}" var="possebuch"/>
                                            ${possebuch}
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="hire__pagination pagination">
                    <div class="pagination__body">
                        <util:navigation path="<%=request.getContextPath()%>" page="${requestScope.page}"
                                         maxPage="${requestScope.listPagination.size()}"></util:navigation>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>


<%--pagination with tag--%>


<%--pagination without tag--%>
<%--    <c:choose>--%>
<%--        <c:when test="${start == 1}">--%>
<%--            Previous--%>
<%--        </c:when>--%>
<%--        <c:otherwise>--%>
<%--            <a href="<%=request.getContextPath()%>/?sortRequest=${sessionScope.sortSession}&page=${start - 1}">Previous</a>--%>
<%--        </c:otherwise>--%>
<%--    </c:choose>--%>

<%--    <c:forEach items="${requestScope.listPagination}" var="pagin">--%>
<%--        <a href="<%=request.getContextPath()%>/?sortRequest=${sessionScope.sortSession}&page=<c:out value="${pagin}"/>"><c:out value="${pagin}"/></a>--%>
<%--    </c:forEach>--%>

<%--    <c:choose>--%>
<%--        <c:when test="${start == listPagination.size()}">--%>
<%--            Next--%>
<%--        </c:when>--%>
<%--        <c:otherwise>--%>
<%--            <a href="<%=request.getContextPath()%>/?sortRequest=${sessionScope.sortSession}&page=${start + 1}">Next</a>--%>
<%--        </c:otherwise>--%>
<%--    </c:choose>--%>

<%@include file="WEB-INF/view/footer.jsp" %>
