<%@include file="../header.jsp" %>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8" %>
<c:if test="${role != 'ADMIN'}">
    <c:redirect url="/"/>
</c:if>
<h3 class="hire__title table__title">
    <fmt:message key="admin.seances.title" bundle="${bundle}" var="asetitle"/>${asetitle}
</h3>
<div class="button__wrapper">
    <div class="button table__button">
        <a class="button__link table__button-link" href="<%=request.getContextPath()%>/add_new_seance">
            <fmt:message key="admin.seances.button.add" bundle="${bundle}" var="asebuttadd"/>${asebuttadd}
        </a>
    </div>
</div>
<table class="table" cellspacing="10">
    <thead>
    <tr>
        <th>ID</th>
        <th><fmt:message key="admin.seances.movietitle" bundle="${bundle}" var="asemovtit"/>${asemovtit}</th>
        <th><fmt:message key="admin.seances.date" bundle="${bundle}" var="asedate"/>${asedate}</th>
        <th><fmt:message key="admin.seances.time" bundle="${bundle}" var="asetime"/>${asetime}</th>
        <th><fmt:message key="admin.seances.price" bundle="${bundle}" var="aseprice"/>${aseprice}</th>
        <th><fmt:message key="admin.seances.numofseats" bundle="${bundle}" var="asenumofseat"/>${asenumofseat}</th>
        <th><fmt:message key="admin.seances.editing" bundle="${bundle}" var="aseediting"/>${aseediting}</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.listSeances}" var="schedule">
        <tr>
            <td><c:out value="${schedule.id}"/></td>
            <td><c:out value="${schedule.filmName}"/></td>
            <td><c:out value="${schedule.date}"/></td>
            <td><c:out value="${schedule.timeSeance}"/></td>
            <td><c:out value="${schedule.priceSeance}"/></td>
            <td><c:out value="${schedule.numberOfSeats}"/></td>
            <td class="table__edit">
                <a class="hire__title table__button-edit" href="edit_seance?id=<c:out value='${schedule.id}' />">
                    <fmt:message key="admin.seances.button.edit" bundle="${bundle}" var="asebuttedit"/>${asebuttedit}
                </a>
                <hr>
                <a class="hire__title table__button-delete" href="delete_seance?id=<c:out value='${schedule.id}' />">
                    <fmt:message key="admin.seances.button.delete" bundle="${bundle}" var="asebuttdelete"/>${asebuttdelete}
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="../footer.jsp" %>