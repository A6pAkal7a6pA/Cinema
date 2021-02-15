<%@include file="../header.jsp"%>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
<c:if test="${role != 'ADMIN'}" >
    <c:redirect url="/"/>
</c:if>
<h3 class="hire__title table__title">List of Seances</h3>
<div class="button__wrapper">
    <div class="button table__button">
        <a class="button__link table__button-link" href="<%=request.getContextPath()%>/add_new_seance">Add New
            seance</a>
    </div>
</div>
<table class="table" cellspacing="10">
    <thead>
    <tr>
        <th>ID</th>
        <th>Movie title</th>
        <th>Date of film</th>
        <th>Time</th>
        <th>Price</th>
        <th>Number of seats</th>
        <th>Editing</th>
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
                <a class="hire__title table__button-edit" href="edit_seance?id=<c:out value='${schedule.id}' />">Edit</a>
                <hr>
                <a class="hire__title table__button-delete" href="delete_seance?id=<c:out value='${schedule.id}' />">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@ include file="../footer.jsp"%>