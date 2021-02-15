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
                        <c:if test="${role == null}">
                            <a href="register">
                                <img src="<%=request.getContextPath()%>/images/<c:out value="${seance.img}"/>"
                                     alt="images">
                            </a>
                        </c:if>
                        <c:if test="${role != null}">
                            <a href="buy_ticket?id=<c:out value="${seance.id}"/>">
                                <img src="<%=request.getContextPath()%>/images/<c:out value="${seance.img}"/>"
                                     alt="images">
                            </a>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

<section class="hire">
    <div class="hire__body">
        <div class="hire__title">
            film sessions
        </div>
        <div class="hire__subltitle">
            only a registered user can buy a ticket for a movie
        </div>
        <div class="hire__main-content">
            <div class="hire__sidebar sidebar">
                <div class="sidebar__body">
                    <div class="sidebar__sort sort-by">
                        <div class="sort-by__body">
                            <div class="sort-by__title">
                                sort by:
                            </div>
                            <div class="sort-by__content">
                                <div class="sort-by__item">
                                    <a href="<%=request.getContextPath()%>/?sortRequest=dateTimeSortAsc"
                                       class="sort-by__link">
                                        Date/time
                                    </a>
                                </div>
                                <div class="sort-by__item">
                                    <a href="<%=request.getContextPath()%>/?sortRequest=movieTitleSortAsc"
                                       class="sort-by__link">
                                        Movie title
                                    </a>
                                </div>
                                <div class="sort-by__item">
                                    <a href="<%=request.getContextPath()%>/?sortRequest=availableSeatsSortAsc"
                                       class="sort-by__link">
                                        available seats
                                    </a>
                                </div>
                                <div class="sort-by__item">
                                    <a href="<%=request.getContextPath()%>/?sortRequest=priceSortAsc"
                                       class="sort-by__link">
                                        price
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
                        <util:navigation path="<%=request.getContextPath()%>" page="${requestScope.start}"
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
                                <p class="content-hire__price">Cost: <c:out value="${schedule.priceSeance}"/></p>
                                <p class="content-hire__places">Available: <c:out
                                        value="${schedule.freePlaces}"/>/<c:out
                                        value="${schedule.numberOfSeats}"/></p>
                                <p class="content-hire__duration">Duration: <c:out value="${schedule.duration}"/>
                                    minutes</p>
                                <p class="content-hire__duration">Director: <c:out
                                        value="${schedule.director}"/></p>
                                <p class="content-hire__description">
                                    <c:out
                                            value="${schedule.description}"/>
                                </p>
                                <input type="hidden" name="filmId" value="<c:out value="${schedule.filmId}"/>">
                                <c:if test="${role == null}">
                                    <div class="content-hire__button button">
                                        <a href="register" class="button__link">choose a place</a>
                                    </div>
                                </c:if>
                                <c:if test="${role != null}">
                                    <div class="content-hire__button button">
                                        <a href="buy_ticket?id=<c:out value='${schedule.id}' />" class="button__link">choose
                                            a place</a>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="hire__pagination pagination">
                    <div class="pagination__body">
                        <util:navigation path="<%=request.getContextPath()%>" page="${requestScope.start}"
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
