<%@ page contentType="text/html" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<%--<c:choose>--%>
<%--    <c:when test="${locale == 'ru'}">--%>
<%--        <fmt:setLocale value="ru"/>--%>
<%--    </c:when>--%>
<%--    <c:otherwise>--%>
<%--        <fmt:setLocale value="en"/>--%>
<%--    </c:otherwise>--%>
<%--</c:choose>--%>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="local" var="bundle"/>
<!DOCTYPE html>
<html lang="${language}">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    <link rel="icon" href="<%=request.getContextPath()%>/images/Vector.svg">
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
                            <fmt:message key="header.poster.ref" bundle="${bundle}" var="poster"/>
                            ${poster}

                        </a>
                    </li>
                    <li class="menu__list-item">
                        <a href="<%=request.getContextPath()%>/schedule?day=<c:out value="${requestScope.currentDay}"/>"
                           class="menu__list-link">
                            <fmt:message key="header.schedule.ref" bundle="${bundle}" var="schedule"/>
                            ${schedule}
                        </a>
                    </li>
                    <c:if test="${role == 'ADMIN'}">
                        <li class="menu__list-item">
                            <a href="<%=request.getContextPath()%>/films_list" class="menu__list-link">
                                <fmt:message key="header.films.ref" bundle="${bundle}" var="filmsref"/>
                                    ${filmsref}
                            </a>
                        </li>
                        <li class="menu__list-item">
                            <a href="<%=request.getContextPath()%>/seances_list" class="menu__list-link">
                                <fmt:message key="header.seances.ref" bundle="${bundle}" var="seancesref"/>
                                    ${seancesref}
                            </a>
                        </li>
                        <li class="menu__list-item">
                            <a href="<%=request.getContextPath()%>/users_list" class="menu__list-link">
                                <fmt:message key="header.users.ref" bundle="${bundle}" var="usersref"/>
                                    ${usersref}
                            </a>
                        </li>
                        <li class="menu__list-item">
                            <a href="<%=request.getContextPath()%>/statistics" class="menu__list-link">
                                <img src="<%=request.getContextPath()%>/images/static.svg" alt="">
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${role == 'USER'}">
                        <li class="menu__list-item">
                            <a href="<%=request.getContextPath()%>/my_ticket" class="menu__list-link">
                                <fmt:message key="header.myticket.ref" bundle="${bundle}" var="myticketref"/>
                                    ${myticketref}
                            </a>
                        </li>
                    </c:if>
                    <li class="menu__list-item">
                        <div class="menu__select lang-select">
                            <div class="lang-select__header">
                                                         <span class="lang-select__current">
                                                             <c:if test="${language == 'ru'}">
                                                                 <fmt:message key="header.local_button.name.ru"
                                                                              bundle="${bundle}"
                                                                              var="headru"/>${headru}
                                                             </c:if>
                                                              <c:if test="${language == 'en' || language == null}">
                                                                  <fmt:message key="header.local_button.name.en"
                                                                               bundle="${bundle}"
                                                                               var="headen"/>${headen}
                                                              </c:if>
                                                        </span>
                                <span class="fas fa-caret-down"></span>
                                <span class="fas fa-caret-up"></span>
                            </div>
                            <div class="lang-select__body">
                                <div class="lang-select__item">
                                    <a href="?language=en"
                                       class="lang-select__item-link">
                                        <c:if test="${language == 'en' || language == null}">
                                            english
                                        </c:if>
                                        <c:if test="${language == 'ru'}">
                                            английский
                                        </c:if>
                                    </a>
                                </div>
                                <div class="lang-select__item">
                                    <a href="?language=ru"
                                       class="lang-select__item-link">
                                        <c:if test="${language == 'en' || language == null}">
                                            russian
                                        </c:if>
                                        <c:if test="${language == 'ru'}">
                                            русский
                                        </c:if>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <%--                        <a href="<%=request.getContextPath()%>/change_lang?language=en" class="menu__list-link">--%>
                        <%--                            english--%>
                        <%--                        </a>--%>
                        <%--                        <a href="<%=request.getContextPath()%>/change_lang?language=ru" class="menu__list-link">--%>
                        <%--                            russian--%>
                        <%--                        </a>--%>
                        <%--                            <div class="custom-select-wrapper">--%>
                        <%--                                <div class="custom-select "onchange="submit()">--%>
                        <%--                                    <div class="custom-select__trigger"><span>english</span>--%>
                        <%--                                        <div class="arrow"></div>--%>
                        <%--                                    </div>--%>
                        <%--                                    <div class="custom-options">--%>
                        <%--                                        <span class="custom-option "--%>
                        <%--                                              data-value="ru" ${language == 'ru' ? class="selected" : ''}>russian</span>--%>
                        <%--                                        <span class="custom-option"--%>
                        <%--                                              data-value="en" ${language == 'en' ? class="selected" : ''}>english</span>--%>
                        <%--                                    </div>--%>
                        <%--                                </div>--%>
                        <%--                            </div>--%>
                        <%--                                                        <select name="language" id="" onchange="submit()">--%>
                        <%--                                                            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>--%>
                        <%--                                                            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>--%>
                        <%--                                                        </select>--%>
                    </li>
                    <c:if test="${role == null}">
                        <li class="menu__list-item">
                            <a href="<%=request.getContextPath()%>/login" class="menu__list-link">
                                <fmt:message key="header.login.ref" bundle="${bundle}" var="loginref"/>
                                    ${loginref}
                            </a>
                            <span>/</span>
                            <a href="<%=request.getContextPath()%>/register" class="menu__list-link">
                                <fmt:message key="header.register.ref" bundle="${bundle}" var="registerref"/>
                                    ${registerref}
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${role != null}">
                        <li class="menu__list-item">
                            <a href="<%=request.getContextPath()%>/logout" class="menu__list-link">
                                <img width="35" height="35" src="<%=request.getContextPath()%>/images/logout.svg"
                                     alt="">
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </header>