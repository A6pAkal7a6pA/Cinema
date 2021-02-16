<%@include file="../header.jsp" %>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
<c:if test="${role != 'ADMIN'}">
    <c:redirect url="/"/>
</c:if>

<section class="add-edit">
    <div class="add-edit__body">
        <div class="add-edit__content">
            <div class="add-edit__title">
                <c:if test="${not empty film}">
                    <fmt:message key="admin.films.form.title.edit" bundle="${bundle}" var="afiformedit"/>${afiformedit}
                </c:if>
                <c:if test="${empty film}">
                    <fmt:message key="admin.films.form.title.add" bundle="${bundle}" var="afiformadd"/>${afiformadd}
                </c:if>
            </div>

            <c:if test="${not empty film}">
            <form action="edit_film" class="add-edit__form" method="post"
                  enctype="multipart/form-data">
                </c:if>

                <c:if test="${empty film}">
                <form action="add_new_film" class="add-edit__form" method="post" enctype="multipart/form-data">
                    </c:if>

                    <ul class="add-edit__list">
                        <c:if test="${not empty film}">
                            <input type="hidden" value="<c:out value='${film.id}' />" name="id"/>
                        </c:if>
                        <li class="add-edit__list-item">
                            <label for="movie" class="add-edit__label">
                                <fmt:message key="admin.films.movietitle" bundle="${bundle}" var="afimovti"/>${afimovti}:
                            </label>
                            <input type="text" name="title" class="add-edit__input" id="movie" value="<c:out value='${film.name}' />"
                                   required>
                        </li>
                        <li class="add-edit__list-item">
                            <label for="director" class="add-edit__label">
                                <fmt:message key="admin.films.director" bundle="${bundle}" var="afidir"/>${afidir}:
                            </label>
                            <input type="text" name="director" class="add-edit__input" id="director"
                                   value="<c:out value='${film.directedBy}' />" required>
                        </li>
                        <li class="add-edit__list-item">
                            <label for="duration" class="add-edit__label">
                                <fmt:message key="admin.films.duration" bundle="${bundle}" var="afidur"/>${afidur}:
                            </label>
                            <input type="number" class="add-edit__input" id="duration" name="duration"
                                   value="<c:out value='${film.duration}' />" min="0" max="10000" required>
                        </li>
                        <li class="add-edit__list-item">
                            <label for="description" class="add-edit__label">
                                <fmt:message key="admin.films.description" bundle="${bundle}" var="afidesc"/>${afidesc}:
                            </label>
                            <textarea name="description" id="description" cols="50" rows="1" wrap="hard" class="add-edit__textarea"
                                      required>
                        <c:out value='${film.description}'/>
                        </textarea>
                        </li>
                        <li class="add-edit__list-item">
                            <input type="file" class="add-edit__input inputfile" accept="image/*" name="fileFilm" required id="cover"
                                   data-multiple-caption="{count}">
                            <label for="cover" class="add-edit__label-file">
                                <span>
                                    <fmt:message key="admin.films.form.upload" bundle="${bundle}" var="afiformuplo"/>${afiformuplo}
                                </span>
                            </label>
                        </li>
                    </ul>
                    <div class="registration__block-button-1">
                        <div class="registration__block-button-2">
                            <button type="submit" class="registration__button">
                                <fmt:message key="admin.films.form.button.save" bundle="${bundle}" var="afiformbuttsave"/>${afiformbuttsave}
                            </button>
                        </div>
                    </div>
                </form>
        </div>
    </div>
</section>


<%--        <table>--%>
<%--            <c:if test="${not empty film}">--%>
<%--                <input type="hidden" value="<c:out value='${film.id}' />" name="id"/>--%>
<%--            </c:if>--%>
<%--            <tr>--%>
<%--                <td>Movie title:</td>--%>
<%--                <td><input type="text" value="<c:out value='${film.name}' />" name="title"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Film director:</td>--%>
<%--                <td><input type="text" value="<c:out value='${film.directedBy}' />" name="director"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Duration:</td>--%>
<%--                <td><input type="text" value="<c:out value='${film.duration}' />" name="duration"></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Description:</td>--%>
<%--                <td><textarea name="description" cols="30" rows="5"><c:out value='${film.description}'/></textarea></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Cover:</td>--%>
<%--                <td><input type="file" name="fileFilm" value="<c:out value="${film.img}"/>"/></td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--        <button type="submit">Save</button>--%>
<%--    </form>--%>


<%--<section class="registration">--%>
<%--    <div class="registration__body">--%>
<%--        <div class="registration__title">--%>
<%--            <c:if test="${not empty film}">--%>
<%--                Edit Film--%>
<%--            </c:if>--%>
<%--            <c:if test="${empty film}">--%>
<%--                Add New Film--%>
<%--            </c:if>--%>
<%--        </div>--%>
<%--        <c:if test="${not empty film}">--%>
<%--        <form action="edit_film" method="post" enctype="multipart/form-data">--%>
<%--            </c:if>--%>

<%--            <c:if test="${empty film}">--%>
<%--            <form action="add_new_film" method="post" enctype="multipart/form-data">--%>
<%--                </c:if>--%>
<%--            <ul class="registration__list">--%>
<%--                <c:if test="${not empty film}">--%>
<%--                    <input type="hidden" value="<c:out value='${film.id}' />" name="id"/>--%>
<%--                </c:if>--%>
<%--                <li class="registration__list-item">--%>
<%--                    <label for="movie">Movie title:</label>--%>
<%--                    <input type="text" value="<c:out value='${film.name}' />" name="title" id="movie" required>--%>
<%--                </li>--%>

<%--                <li class="registration__list-item">--%>
<%--                    <label for="directedBy">Film director:</label>--%>
<%--                    <input type="text" value="<c:out value='${film.directedBy}' />" name="director" id="directedBy" required>--%>
<%--                </li>--%>
<%--                <li class="registration__list-item">--%>
<%--                    <label for="directedBy">Duration:</label>--%>
<%--                    <input type="number" value="<c:out value='${film.duration}' />" name="duration" id="duration" required>--%>
<%--                </li>--%>
<%--                <li class="registration__list-item">--%>
<%--                    <label for="description">Description:</label>--%>
<%--                    <textarea name="description" id="description" cols="30" rows="5">--%>
<%--                        <c:out value='${film.description}' />--%>
<%--                    </textarea>--%>
<%--                </li>--%>
<%--                <li class="registration__list-item">--%>
<%--                    <label for="fileFilm">Cover image:</label>--%>
<%--                    <input type="file" value="<c:out value='${film.img}' />" name="fileFilm" id="fileFilm" required>--%>
<%--                </li>--%>
<%--            </ul>--%>
<%--            <div class="registration__block-button-1">--%>
<%--                <div class="registration__block-button-2">--%>
<%--                    <button type="submit" class="registration__button">--%>
<%--                        Save--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</section>--%>
<%@include file="../footer.jsp" %>