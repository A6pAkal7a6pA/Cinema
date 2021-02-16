<%@include file="../header.jsp"%>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
<c:if test="${role != 'ADMIN'}">
    <c:redirect url="/"/>
</c:if>
<h1 class="hire__title table__title"><fmt:message key="admin.users.title" bundle="${bundle}" var="austitle"/>${austitle}</h1>
<table cellspacing="10" class="table">
    <thead>
    <tr>
        <th>ID</th>
        <th><fmt:message key="admin.users.firstname" bundle="${bundle}" var="ausfname"/>${ausfname}</th>
        <th><fmt:message key="admin.users.lastname" bundle="${bundle}" var="auslname"/>${auslname}</th>
        <th><fmt:message key="admin.users.login" bundle="${bundle}" var="auslogin"/>${auslogin}</th>
        <th><fmt:message key="admin.users.phone" bundle="${bundle}" var="ausphone"/>${ausphone}</th>
        <th><fmt:message key="admin.users.role" bundle="${bundle}" var="ausrole"/>${ausrole}</th>
        <th><fmt:message key="admin.users.email" bundle="${bundle}" var="ausemail"/>${ausemail}</th>
        <th><fmt:message key="admin.users.date" bundle="${bundle}" var="ausdate"/>${ausdate}</th>
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