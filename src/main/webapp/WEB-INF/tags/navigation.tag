<%@attribute name="path" %>

<%@attribute name="page" type="java.lang.Integer" %>
<%@attribute name="maxPage" type="java.lang.Integer" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${page == 1}">
        <div class="pagination__item">
            <span class="fas fa-chevron-left"></span>
        </div>
    </c:when>
    <c:otherwise>
        <div class="pagination__item">
            <a href="${path}?sortRequest=${sessionScope.sortSession}&page=${page - 1}" class="fas fa-chevron-left"></a>
        </div>
    </c:otherwise>
</c:choose>

<c:forEach items="${requestScope.listPagination}" var="pagin">
    <c:choose>
        <c:when test="${page == pagin}">
            <div class="pagination__item">
                <span><c:out value="${pagin}"/></span>
            </div>
        </c:when>
        <c:otherwise>
            <div class="pagination__item">
                <a href="${path}?sortRequest=${sessionScope.sortSession}&page=<c:out value="${pagin}"/>"><c:out
                        value="${pagin}"/></a>
            </div>
        </c:otherwise>
    </c:choose>
</c:forEach>

<c:choose>
    <c:when test="${page == maxPage}">
        <div class="pagination__item">
            <span class="fas fa-chevron-right"></span>
        </div>
    </c:when>
    <c:otherwise>
        <div class="pagination__item">
            <a href="${path}?sortRequest=${sessionScope.sortSession}&page=${page + 1}" class="fas fa-chevron-right"></a>
        </div>
    </c:otherwise>
</c:choose>