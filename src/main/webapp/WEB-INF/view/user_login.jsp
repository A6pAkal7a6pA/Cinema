<%@include file="header.jsp"%>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
<%--    <h1>User Login Form</h1>--%>
<%--    <form action="<%= request.getContextPath() %>/login" method="post">--%>
<%--        <table>--%>
<%--            <tr>--%>
<%--                <td>Login</td>--%>
<%--                <td><input type="text" name="login"/></td>--%>
<%--            </tr>--%>
<%--            <tr>--%>
<%--                <td>Password</td>--%>
<%--                <td><input type="password" name="password"/></td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--        <button type="submit">Login</button>--%>
<%--    </form>--%>

    <section class="registration">
        <div class="registration__body">
            <div class="registration__title">
                sign in
            </div>
            <form class="registration__form" action="<%= request.getContextPath() %>/login" method="post">
                <ul class="registration__list">
                    <li class="registration__list-item">
                        <label for="login">login:</label>
                        <input type="text" name="login" id="login" required>
                    </li>
                    <li class="registration__list-item">
                        <label for="password">password:</label>
                        <input type="password" name="password" id="password" required>
                    </li>
                </ul>
                <div class="registration__block-button-1">
                    <div class="registration__block-button-2">
                        <button type="submit" class="registration__button">
                            sign in
                        </button>
                    </div>
                </div>

            </form>
        </div>
    </section>
<%@include file="footer.jsp"%>