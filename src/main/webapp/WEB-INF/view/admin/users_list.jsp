<%@include file="../header.jsp"%>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
<c:if test="${role != 'ADMIN'}">
    <c:redirect url="/"/>
</c:if>
<h1 class="hire__title table__title">List of User</h1>
<table cellspacing="10" class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Login</th>
        <th>Contact Number</th>
        <th>Role</th>
        <th>Email</th>
        <th>Date of registration</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestScope.listUsers}" var="currentUserProfile">
        <tr>
            <td><c:out value="${currentUserProfile.id}"/></td>
            <td><c:out value="${currentUserProfile.firstName}"/></td>
            <td><c:out value="${currentUserProfile.lastName}"/></td>
            <td><c:out value="${currentUserProfile.login}"/></td>
            <td><c:out value="${currentUserProfile.contact}"/></td>
            <td><c:out value="${currentUserProfile.role}"/></td>
            <td><c:out value="${currentUserProfile.email}"/></td>
            <td><c:out value="${currentUserProfile.date}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%@include file="../footer.jsp"%>