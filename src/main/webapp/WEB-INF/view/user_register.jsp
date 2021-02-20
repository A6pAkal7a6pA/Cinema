<%@include file="header.jsp"%>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
    <section class="registration">
        <div class="registration__body">
            <c:if test="${not empty requestScope.message }">
                <h2 class="block-message-error">
      <span class="message-error">
              ${requestScope.message}
      </span>
                </h2>
            </c:if>
            <div class="registration__title">
                <fmt:message key="signup.title" bundle="${bundle}" var="sut"/>${sut}
            </div>
            <form action="<%= request.getContextPath() %>/register" method="post" class="registration__form">
                <ul class="registration__list">
                    <li class="registration__list-item">
                        <label for="firstName"><fmt:message key="signup.firstname" bundle="${bundle}" var="sufn"/>${sufn}:</label>
                        <input type="text" name="firstName" minlength="2" id="firstName" required>
                    </li>
                    <li class="registration__list-item">
                        <label for="lastName"><fmt:message key="signup.lastname" bundle="${bundle}" var="suln"/>${suln}:</label>
                        <input type="text" name="lastName" minlength="2" id="lastName" required>
                    </li>
                    <li class="registration__list-item">
                        <label for="login"><fmt:message key="signup.login" bundle="${bundle}" var="sulog"/>${sulog}:</label>
                        <input type="text" name="login" minlength="4" maxlength="16" id="login" required>
                    </li>
                    <li class="registration__list-item">
                        <label for="password"><fmt:message key="signup.password" bundle="${bundle}" var="supass"/>${supass}:</label>
                        <input type="password" name="password" id="password" minlength="4" required>
                    </li>
                    <li class="registration__list-item">
                        <label for="contact"><fmt:message key="signup.phone" bundle="${bundle}" var="suphone"/>${suphone}:</label>
                        <input type="tel" name="contact" id="contact" class="input__phone">
                    </li>
                    <li class="registration__list-item">
                        <label for="email"><fmt:message key="signup.email" bundle="${bundle}" var="suemail"/>${suemail}:</label>
                        <input type="email" name="email" id="email">
                    </li>
                </ul>
                <div class="registration__block-button-1">
                    <div class="registration__block-button-2">
                        <button type="submit" class="registration__button">
                            <fmt:message key="signup.button" bundle="${bundle}" var="subutt"/>${subutt}
                        </button>
                    </div>
                </div>

            </form>
        </div>
    </section>





<%@include file="footer.jsp"%>
