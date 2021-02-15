<%@include file="../header.jsp" %>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
<c:if test="${role != 'ADMIN'}">
    <c:redirect url="/"/>
</c:if>

<h3 class="hire__title table__title">List of Films</h3>
<div class="button__wrapper">
    <div class="button table__button">
        <a class="button__link table__button-link" href="<%=request.getContextPath()%>/add_new_film">Add New
            Film</a>
    </div>
</div>

<table cellspacing="10" class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>Cover</th>
        <th>Movie title</th>
        <th>Film director</th>
        <th>Duration</th>
        <th>Description</th>
        <th>Date Added</th>
        <th>Editing</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.listFilms}" var="listFilm">
        <tr>
            <td><c:out value="${listFilm.id}"/></td>
            <td><img width="100" height="150"
                     src="<%=request.getContextPath()%>/images/<c:out value="${listFilm.img}"/>" alt=""></td>
            <td class="table__title-in"><c:out value="${listFilm.name}"/></td>
            <td><c:out value="${listFilm.directedBy}"/></td>
            <td><c:out value="${listFilm.duration}"/></td>
            <td class="table__description"><c:out value="${listFilm.description}"/></td>
            <td class="table__date-added"><c:out value="${listFilm.date}"/></td>
            <td class="table__edit">
                <a class="hire__title table__button-edit"
                   href="<%=request.getContextPath()%>/edit_film?id=<c:out value="${listFilm.id}"/>">Edit</a>
                <hr>
                <a class="hire__title table__button-delete"
                   href="<%=request.getContextPath()%>/delete_film?id=<c:out value="${listFilm.id}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="../footer.jsp" %>