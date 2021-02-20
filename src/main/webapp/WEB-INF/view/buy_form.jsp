<%@include file="header.jsp" %>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8" %>
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
                <fmt:message key="user.buyform.cost" bundle="${bundle}" var="ubfcost"/>${ubfcost}: <c:out
                    value="${requestScope.seance.priceSeance}"/>
            </div>
            <div class="film__free-place">
                <fmt:message key="user.buyform.available" bundle="${bundle}" var="ubfaval"/>${ubfaval}:
                <c:out value="${requestScope.seance.freePlaces}"/>/<c:out value="${requestScope.seance.numberOfSeats}"/>
            </div>
            <div class="film__duration">
                <fmt:message key="user.buyform.duration" bundle="${bundle}" var="ubfdur"/>${ubfdur}: <c:out
                    value="${requestScope.seance.duration}"/> minutes
            </div>
            <div class="film__duration">
                <fmt:message key="user.buyform.director" bundle="${bundle}" var="ubfdir"/>${ubfdir}: <c:out
                    value="${requestScope.seance.director}"/>
            </div>
            <div class="film__description">
                <c:out value="${requestScope.seance.description}"/>
            </div>
        </div>
    </div>
</section>

<%--<c:forEach items="${requestScope.allSeancesThisFilm}" var="thisFilm">--%>
<%--    <c:out value="${thisFilm.dayName}"/>--%>
<%--    <c:out value="${thisFilm.date}"/>--%>
<%--    <c:out value="${thisFilm.timeSeance}"/>--%>
<%--    <br>--%>
<%--</c:forEach>--%>

    <c:if test="${not empty requestScope.message }">
        <h2 class="block-message-error">
      <span class="message-error">
              ${requestScope.message}
      </span>
        </h2>
    </c:if>

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
                <div id="hint"></div>
                <c:forEach items="${requestScope.mapPlaces}" var="entry">
                    <div class="hall__item">
                            <%--                        <input type="checkbox" name="hall_check" value="<c:out value='${entry.key}' />" <c:out--%>
                            <%--                                    value="${entry.value}"/>/>--%>
                        <input class="hall__checkbox" type="checkbox" name="hall_check"
                               value="<c:out value='${entry.key}' />" id="hall_check-<c:out value='${entry.key}' />"
                            <c:out
                                    value="${entry.value}"/>>
                        <label for="hall_check-<c:out value='${entry.key}' />" class="hall__label" data-hint="Place <c:out value='${entry.key}' />" id="<c:out value='${entry.key}' />"></label>
                    </div>
                </c:forEach>
                <c:if test="${role == null}">
                    <div class="hall__button button">
                        <a href="<%=request.getContextPath()%>/register" class="hall__button-link button__link">
                            <fmt:message key="user.buyform.button" bundle="${bundle}" var="ubfbutt"/>${ubfbutt}
                        </a>
                    </div>
                </c:if>
                <c:if test="${role == 'USER'}">
                    <div class="hall__button button">
                        <button type="submit" class="hall__button-link button__link">buy ticket</button>
                    </div>
                </c:if>
            </form>
        </div>
    </div>
</section>

<%@include file="footer.jsp" %>