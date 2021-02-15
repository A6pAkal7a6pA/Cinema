<%@include file="../header.jsp"%>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
<h1 class="hire__title">Admin profile</h1>
<h3 class="hire__subltitle">Hello <c:out value="${userName}"/></h3>

<div class="admin-menu__wrapper">

</div>
<div class="button__wrapper admin-button__wrapper">
    <div class="button table__button">
        <a class="button__link table__button-link" href="<%=request.getContextPath()%>/account/users_list">List of Users</a>
    </div>
</div>

<div class="button__wrapper admin-button__wrapper">
    <div class="button table__button">
        <a class="button__link table__button-link" href="<%=request.getContextPath()%>/account/films_list">List of Films</a>
    </div>
</div>

<div class="button__wrapper admin-button__wrapper">
    <div class="button table__button">
        <a class="button__link table__button-link" href="<%=request.getContextPath()%>/account/seances_list">List of Seances</a>
    </div>
</div>
<%@include file="../footer.jsp"%>