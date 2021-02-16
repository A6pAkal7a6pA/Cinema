<%@include file="header.jsp"%>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>

    <section class="registration">
        <div class="registration__body">
            <div class="registration__title">
                <fmt:message key="signin.title" bundle="${bundle}" var="sititle"/>${sititle}
            </div>
            <form class="registration__form" action="<%= request.getContextPath() %>/login" method="post">
                <ul class="registration__list">
                    <li class="registration__list-item">
                        <label for="login"><fmt:message key="signin.login" bundle="${bundle}" var="silog"/>${silog}:</label>
                        <input type="text" name="login" id="login" required>
                    </li>
                    <li class="registration__list-item">
                        <label for="password"><fmt:message key="signin.password" bundle="${bundle}" var="sipass"/>${sipass}:</label>
                        <input type="password" name="password" id="password" required>
                    </li>
                </ul>
                <div class="registration__block-button-1">
                    <div class="registration__block-button-2">
                        <button type="submit" class="registration__button">
                            <fmt:message key="signin.button" bundle="${bundle}" var="sibutt"/>${sibutt}
                        </button>
                    </div>
                </div>

            </form>
        </div>
    </section>
<%@include file="footer.jsp"%>