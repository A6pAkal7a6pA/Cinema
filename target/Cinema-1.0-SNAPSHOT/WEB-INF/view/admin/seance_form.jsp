<%@include file="../header.jsp" %>
<%@ page contentType="text/html" language="java" pageEncoding="utf-8" %>
<c:if test="${role != 'ADMIN'}">
    <c:redirect url="/"/>
</c:if>

<section class="add-edit">
    <div class="add-edit__body">
        <div class="add-edit__content">
            <div class="add-edit__title">
                <c:if test="${not empty seance}">
                    <fmt:message key="admin.seances.form.title.edit" bundle="${bundle}" var="aseformedit"/>${aseformedit}
                </c:if>
                <c:if test="${empty seance}">
                    <fmt:message key="admin.seances.form.title.add" bundle="${bundle}" var="aseformadd"/>${aseformadd}
                </c:if>
            </div>

            <c:if test="${not empty seance}">
            <form action="edit_seance" class="add-edit__form" method="post">
                </c:if>

                <c:if test="${empty seance}">
                <form action="add_new_seance" class="add-edit__form" method="post">
                    </c:if>


                    <ul class="add-edit__list">
                        <c:if test="${not empty seance}">
                            <input type="hidden" value="<c:out value='${seance.id}' />" name="id"/>
                        </c:if>
                        <li class="add-edit__list-item">
                            <label for="movie" class="add-edit__label">
                                <fmt:message key="admin.seances.movietitle" bundle="${bundle}" var="asemovtit"/>${asemovtit}:
                            </label>
                            <select name="filmSeanceId" class="add-edit__input" id="movie">
                                <c:forEach items="${requestScope.selectFilmIdName}" var="filmIdName">
                                    <option value="<c:out value='${filmIdName.id}' />">
                                        <c:out value="${filmIdName.name}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </li>
                        <li class="add-edit__list-item">
                            <label for="dateSeance" class="add-edit__label">
                                <fmt:message key="admin.seances.date" bundle="${bundle}" var="asedate"/>${asedate}:
                            </label>
                            <input type="date" name="dateSeance" min="${requestScope.currentDate}"
                                   class="add-edit__input" id="dateSeance"
                                   value="<c:out value='${seance.date}' />" required>
                        </li>

                        <li class="add-edit__list-item">
                            <label for="timeSeance" class="add-edit__label">
                                <fmt:message key="admin.seances.time" bundle="${bundle}" var="asetime"/>${asetime}:
                            </label>
                            <input type="time" name="timeSeance" class="add-edit__input" min="09:00" max="22:00" id="timeSeance"
                                   value="<c:out value='${seance.timeSeance}' />" required>
                        </li>

                        <li class="add-edit__list-item">
                            <label for="priceSeance" class="add-edit__label">
                                <fmt:message key="admin.seances.price" bundle="${bundle}" var="aseprice"/>${aseprice}:
                            </label>
                            <input type="number" name="priceSeance" class="add-edit__input" id="priceSeance"
                                   value="<c:out value='${seance.priceSeance}' />" required>
                        </li>
                    </ul>
                    <div class="registration__block-button-1">
                        <div class="registration__block-button-2">
                            <button type="submit" class="registration__button">
                                <fmt:message key="admin.seances.form.button.save" bundle="${bundle}" var="aseformsave"/>${aseformsave}
                            </button>
                        </div>
                    </div>
                </form>
        </div>
    </div>
</section>
<%@include file="../footer.jsp" %>