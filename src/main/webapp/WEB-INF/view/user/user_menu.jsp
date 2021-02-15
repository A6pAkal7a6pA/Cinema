<%@include file="../header.jsp" %>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
<section class="tickets">
    <div class="tickets__body">
        <div class="tickets__title">
            my active sessions:
        </div>
        <div class="tickets__content">

            <c:forEach items="${requestScope.tickets}" var="ticket">
                <div class="tickets__item ticket">
                    <div class="ticket__body">
                        <div class="ticket__title">
                            ticket ?????? <c:out value="${ticket.id}"/>
                        </div>
                        <div class="ticket__element">
                            Movie title: <c:out value="${ticket.filmName}"/>
                        </div>
                        <div class="ticket__element">
                            Date: <c:out value="${ticket.dateSeance}"/>
                        </div>
                        <div class="ticket__element">
                            Time: <c:out value="${ticket.timeSeance}"/>
                        </div>
                        <div class="ticket__element">
                            Place: <c:out value="${ticket.numberSeat}"/>
                        </div>
                        <div class="ticket__element">
                            Price: <c:out value="${ticket.priceSeance}"/>
                        </div>
                        <div class="ticket__descript">
                            You can get a ticket right before the screening at the box office of our cinema, booked
                            tickets are
                            served without queuing at specially designated box offices. You can also get your ticket any
                            day before
                            the date of the session, print it yourself, or use the web version.
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
<%--<table>--%>
<%--    <c:forEach items="${requestScope.userProfile}" var="currentUserProfile">--%>
<%--        <tr>--%>
<%--            <td>First name:</td>--%>
<%--            <td><c:out value="${currentUserProfile.firstName}"/></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Last name:</td>--%>
<%--            <td><c:out value="${currentUserProfile.lastName}"/></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Contact:</td>--%>
<%--            <td><c:out value="${currentUserProfile.contact}"/></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Email:</td>--%>
<%--            <td><c:out value="${currentUserProfile.email}"/></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Date of registration:</td>--%>
<%--            <td><c:out value="${currentUserProfile.date}"/></td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>
<%--<h2>Tickets:</h2>--%>
<%--<c:forEach items="${requestScope.tickets}" var="ticket">--%>
<%--    <div>Ticket number:<c:out value="${ticket.id}"/></div>--%>
<%--    <div>Movie title:<c:out value="${ticket.filmName}"/></div>--%>
<%--    <div>Session date:<c:out value="${ticket.dateSeance}"/></div>--%>
<%--    <div>Session time:<c:out value="${ticket.timeSeance}"/></div>--%>
<%--    <div>Place:<c:out value="${ticket.numberSeat}"/></div>--%>
<%--    <div>Price:<c:out value="${ticket.priceSeance}"/></div>--%>
<%--    <br><br>--%>
<%--</c:forEach>--%>

<%@include file="../footer.jsp" %>
