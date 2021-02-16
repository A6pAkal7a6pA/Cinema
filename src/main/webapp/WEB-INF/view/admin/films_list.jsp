<%@include file="../header.jsp" %>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
<c:if test="${role != 'ADMIN'}">
    <c:redirect url="/"/>
</c:if>

<h3 class="hire__title table__title"><fmt:message key="admin.films.title" bundle="${bundle}" var="afititle"/>${afititle}</h3>
<div class="button__wrapper">
    <div class="button table__button">
        <a class="button__link table__button-link" href="<%=request.getContextPath()%>/add_new_film">
            <fmt:message key="admin.films.button.add" bundle="${bundle}" var="afibuttadd"/>${afibuttadd}
            </a>
    </div>
</div>

<table cellspacing="10" class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th><fmt:message key="admin.films.cover" bundle="${bundle}" var="aficov"/>${aficov}</th>
        <th><fmt:message key="admin.films.movietitle" bundle="${bundle}" var="afimovti"/>${afimovti}</th>
        <th><fmt:message key="admin.films.director" bundle="${bundle}" var="afidir"/>${afidir}</th>
        <th><fmt:message key="admin.films.duration" bundle="${bundle}" var="afidur"/>${afidur}</th>
        <th><fmt:message key="admin.films.description" bundle="${bundle}" var="afidesc"/>${afidesc}</th>
        <th><fmt:message key="admin.films.dateAdded" bundle="${bundle}" var="afidateadd"/>${afidateadd}</th>
        <th><fmt:message key="admin.films.editing" bundle="${bundle}" var="afiediting"/>${afiediting}</th>
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
                   href="<%=request.getContextPath()%>/edit_film?id=<c:out value="${listFilm.id}"/>"><fmt:message key="admin.films.button.edit" bundle="${bundle}" var="afibuttedit"/>${afibuttedit}</a>
                <hr>
                <a class="hire__title table__button-delete"
                   href="<%=request.getContextPath()%>/delete_film?id=<c:out value="${listFilm.id}"/>"><fmt:message key="admin.films.button.delete" bundle="${bundle}" var="afibuttdelete"/>${afibuttdelete}</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@include file="../footer.jsp" %>