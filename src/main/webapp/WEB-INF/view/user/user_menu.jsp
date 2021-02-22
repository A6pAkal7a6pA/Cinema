<%@include file="../header.jsp" %>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8" %>
<section class="tickets">
    <div class="tickets__body">
        <div class="tickets__title">
            <fmt:message key="user.mytickets.title" bundle="${bundle}" var="umttitle"/>${umttitle}
        </div>
        <div class="tickets__content">

            <c:forEach items="${requestScope.tickets}" var="ticket">
                <div class="tickets__item ticket">
                    <div class="ticket__body">
                        <div class="ticket__title">
                            <fmt:message key="user.mytickets.ticket.title" bundle="${bundle}" var="umtticktitle"/>${umtticktitle}
                            <c:out value="${ticket.id}"/>
                        </div>
                        <div class="ticket__element">
                            <fmt:message key="user.mytickets.ticket.movietitle" bundle="${bundle}" var="umttickmt"/>${umttickmt}: <c:out value="${ticket.filmName}"/>
                        </div>
                        <div class="ticket__element">
                            <fmt:message key="user.mytickets.ticket.date" bundle="${bundle}" var="umttickdate"/>${umttickdate}: <c:out value="${ticket.dateSeance}"/>
                        </div>
                        <div class="ticket__element">
                            <fmt:message key="user.mytickets.ticket.time" bundle="${bundle}" var="umtticktime"/>${umtticktime}: <c:out value="${ticket.timeSeance}"/>
                        </div>
                        <div class="ticket__element">
                            <fmt:message key="user.mytickets.ticket.place" bundle="${bundle}" var="umttickplace"/>${umttickplace}: <c:out value="${ticket.numberSeat}"/>
                        </div>
                        <div class="ticket__element">
                            <fmt:message key="user.mytickets.ticket.price" bundle="${bundle}" var="umttickprice"/>${umttickprice}: <c:out value="${ticket.priceSeance}"/>
                        </div>
                        <div class="ticket__element ticket__return">
                            <a href="delete_ticket?id=<c:out value="${ticket.id}"/>"><fmt:message key="user.mytickets.ticket.button.return" bundle="${bundle}"/></a>
                        </div>
                        <div class="ticket__descript">
                            <fmt:message key="user.mytickets.ticket.description" bundle="${bundle}" var="umttickdesc"/>${umttickdesc}
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>


<%@include file="../footer.jsp" %>
