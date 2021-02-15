<%@include file="header.jsp"%>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
    <section class="registration">
        <div class="registration__body">
            <div class="registration__title">
                registration
            </div>
            <form action="<%= request.getContextPath() %>/register" method="post" class="registration__form">
                <ul class="registration__list">
                    <li class="registration__list-item">
                        <label for="firstName">first name:</label>
                        <input type="text" name="firstName" id="firstName" required>
                    </li>
                    <li class="registration__list-item">
                        <label for="lastName">last name:</label>
                        <input type="text" name="lastName" id="lastName" required>
                    </li>
                    <li class="registration__list-item">
                        <label for="login">login:</label>
                        <input type="text" name="login" id="login" required>
                    </li>
                    <li class="registration__list-item">
                        <label for="password">password:</label>
                        <input type="password" name="password" id="password" required>
                    </li>
                    <li class="registration__list-item">
                        <label for="contact">phone number:</label>
                        <input type="tel" name="contact" id="contact">
                    </li>
                    <li class="registration__list-item">
                        <label for="email">email:</label>
                        <input type="email" name="email" id="email">
                    </li>
                </ul>
                <div class="registration__block-button-1">
                    <div class="registration__block-button-2">
                        <button type="submit" class="registration__button">
                            create account
                        </button>
                    </div>
                </div>

            </form>
        </div>
    </section>

<%@include file="footer.jsp"%>