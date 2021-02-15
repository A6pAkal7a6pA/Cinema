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
                    Edit Seance
                </c:if>
                <c:if test="${empty seance}">
                    Add New Seance
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
                                Movie title:
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
                                Date of seance:
                            </label>
                            <input type="date" name="dateSeance" min="${requestScope.currentDate}"
                                   class="add-edit__input" id="dateSeance"
                                   value="<c:out value='${seance.date}' />" required>
                        </li>

                        <li class="add-edit__list-item">
                            <label for="timeSeance" class="add-edit__label">
                                Time of seance:
                            </label>
                            <input type="time" name="timeSeance" class="add-edit__input" id="timeSeance"
                                   value="<c:out value='${seance.timeSeance}' />" required>
                        </li>

                        <li class="add-edit__list-item">
                            <label for="priceSeance" class="add-edit__label">
                                Cost:
                            </label>
                            <input type="number" name="priceSeance" class="add-edit__input" id="priceSeance"
                                   value="<c:out value='${seance.priceSeance}' />" required>
                        </li>
                    </ul>
                    <div class="registration__block-button-1">
                        <div class="registration__block-button-2">
                            <button type="submit" class="registration__button">
                                save
                            </button>
                        </div>
                    </div>
                </form>
        </div>
    </div>
</section>
<%@include file="../footer.jsp" %>