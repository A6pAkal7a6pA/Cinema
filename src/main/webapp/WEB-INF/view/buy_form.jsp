<%@include file="header.jsp" %>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
<section class="film">
    <div class="film__body">
        <div class="film__image">
            <img src="<%=request.getContextPath()%>/images/<c:out value="${requestScope.seance.img}"/>" alt="">
        </div>
        <div class="film__content">
            <div class="film__title">
                <%--                    <input type="hidden" value="<c:out value="${requestScope.seance.id}"/>">--%>
                <span><c:out value="${requestScope.seance.filmName}"/></span>
                <div class="film__datetime">
              <span class="film__date">
                <c:out value="${requestScope.seance.date}"/>
              </span>
                    <span class="film__time">
                <c:out value="${requestScope.seance.timeSeance}"/>
              </span>
                </div>
            </div>
            <div class="film__price">
                Cost: <c:out value="${requestScope.seance.priceSeance}"/>
            </div>
            <div class="film__free-place">
                Available: <c:out value="${requestScope.seance.freePlaces}"/>/<c:out
                    value="${requestScope.seance.numberOfSeats}"/>
            </div>
            <div class="film__duration">
                Duration: <c:out value="${requestScope.seance.duration}"/> minutes
            </div>
            <div class="film__description">
                <c:out value="${requestScope.seance.description}"/>
            </div>
        </div>
    </div>
</section>

<section class="hall">
    <div class="hall__body">
        <div class="hall__screen">
            <img src="<%=request.getContextPath()%>/images/screen.svg" alt="">
        </div>
        <div class="hall__content">
            <%--                <form class="hall__form" action="#">--%>
            <form class="hall__form" action="<%=request.getContextPath()%>/buy_ticket" method="post">
                <c:if test="${not empty seance}">
                    <input type="hidden" value="<c:out value='${seance.id}' />" name="id"/>
                </c:if>

                <c:forEach items="${requestScope.mapPlaces}" var="entry">
                    <div class="hall__item">
                            <%--                        <input type="checkbox" name="hall_check" value="<c:out value='${entry.key}' />" <c:out--%>
                            <%--                                    value="${entry.value}"/>/>--%>
                        <input class="hall__checkbox" type="checkbox" name="hall_check"
                               value="<c:out value='${entry.key}' />" id="hall_check-<c:out value='${entry.key}' />"
                            <c:out
                                    value="${entry.value}"/>>
                        <label for="hall_check-<c:out value='${entry.key}' />" class="hall__label"></label>
                    </div>
                </c:forEach>

                <div class="hall__button button">
                    <button type="submit" class="hall__button-link button__link">buy ticket</button>
                </div>
            </form>
        </div>
    </div>
</section>

<%@include file="footer.jsp" %>