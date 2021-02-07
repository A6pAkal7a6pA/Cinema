<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cinema</title>
</head>
<body>
<c:if test="${role != 'ADMIN'}">
    <c:redirect url="/"/>
</c:if>

<c:if test="${not empty film}">
<form action="edit_film" method="post">
    </c:if>

    <c:if test="${empty film}">
    <form action="add_new_film" method="post" enctype="multipart/form-data">
        </c:if>

        <h2>
            <c:if test="${not empty film}">
                Edit Film
            </c:if>
            <c:if test="${empty film}">
                Add New Film
            </c:if>
        </h2>


        <table>
            <c:if test="${not empty film}">
                <input type="hidden" value="<c:out value='${film.id}' />" name="id"/>
            </c:if>
            <tr>
                <td>Movie title:</td>
                <td><input type="text" value="<c:out value='${film.name}' />" name="title"></td>
            </tr>
            <tr>
                <td>Film director:</td>
                <td><input type="text" value="<c:out value='${film.directedBy}' />" name="director"></td>
            </tr>
            <tr>
                <td>Duration:</td>
                <td><input type="text" value="<c:out value='${film.duration}' />" name="duration"></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><textarea name="description" cols="30" rows="5"><c:out value='${film.description}'/></textarea></td>
            </tr>
            <tr>
                <td>Cover:</td>
                <td><input type="file" name="fileFilm"/></td>
            </tr>
        </table>
        <button type="submit">Save</button>
    </form>
</body>
</html>