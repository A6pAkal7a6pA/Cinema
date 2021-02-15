<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <title>Cinema</title>
</head>
<style>
    <%@include file="../../css/style.css"%>
</style>
<body>
<div class="wrapper">
    <header class="header">
        <div class="header__body">
            <div class="header__logo">
                <a href="<%=request.getContextPath()%>/"><img src="<%=request.getContextPath()%>/images/logo.svg"
                                                              alt="logo"></a>
            </div>
            <nav class="header__menu menu">
                <ul class="menu__list">
                    <li class="menu__list-item">
                        <a href="<%=request.getContextPath()%>/" class="menu__list-link">
                            poster
                        </a>
                    </li>
                    <li class="menu__list-item">
                        <a href="<%=request.getContextPath()%>/schedule?day=<c:out value="${requestScope.currentDay}"/>"
                           class="menu__list-link">
                            schedule
                        </a>
                    </li>
                    <c:if test="${role == 'ADMIN'}">

                    <li class="menu__list-item">
                        <a href="<%=request.getContextPath()%>/films_list" class="menu__list-link">
                            films
                        </a>
                    </li>
                    <li class="menu__list-item">
                        <a href="<%=request.getContextPath()%>/seances_list" class="menu__list-link">
                            seances
                        </a>
                    </li>
                    <li class="menu__list-item">
                        <a href="<%=request.getContextPath()%>/users_list" class="menu__list-link">
                            users
                        </a>
                    </li>
                    </c:if>
                    <c:if test="${role == 'USER'}">
                        <li class="menu__list-item">
                            <a href="<%=request.getContextPath()%>/my_ticket" class="menu__list-link">
                                my tickets
                            </a>
                        </li>
                    </c:if>
                    <li class="menu__list-item">
                        <a href="#" class="menu__list-link">
                            english
                        </a>
                    </li>
                    <c:if test="${role == null}">
                        <li class="menu__list-item">
                            <a href="<%=request.getContextPath()%>/login" class="menu__list-link">
                                sign in
                            </a>
                            <span>/</span>
                            <a href="<%=request.getContextPath()%>/register" class="menu__list-link">
                                sign up
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${role != null}">
                        <li class="menu__list-item">
                            <a href="<%=request.getContextPath()%>/logout" class="menu__list-link">
                                <img width="35" height="35" src="<%=request.getContextPath()%>/images/logout.svg" alt="">
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </header>