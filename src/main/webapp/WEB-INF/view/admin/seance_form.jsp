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

<c:if test="${not empty seance}">
<form action="edit_seance" method="post">
    </c:if>

    <c:if test="${empty seance}">
    <form action="add_new_seance" method="post">
        </c:if>

        <h2>
            <c:if test="${not empty seance}">
                Edit Seance
            </c:if>
            <c:if test="${empty seance}">
                Add New Seance
            </c:if>
        </h2>


        <table>
            <c:if test="${not empty seance}">
                <input type="hidden" value="<c:out value='${seance.id}' />" name="id"/>
            </c:if>
            <tr>
                <td>Movie title:</td>
                <td><input type="text" name="filmSeance"></td>
                <td>
                    <select name="filmSeanceId">
                        <c:forEach items="${requestScope.selectFilmIdName}" var="filmIdName">
                            <option value="<c:out value='${filmIdName.id}' />">
                                <c:out value="${filmIdName.name}"/>
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Date of seance:</td>
                <td><input type="date" name="dateSeance" value="<c:out value='${seance.date}' />"></td>
            </tr>
            <tr>
                <td>Time of seance:</td>
                <td><input type="time" name="timeSeance"></td>
            </tr>
            <tr>
                <td>Price per seance:</td>
                <td><input type="number" name="priceSeance" required></td>
            </tr>
        </table>
        <button type="submit">Save</button>
    </form>
</body>
</html>