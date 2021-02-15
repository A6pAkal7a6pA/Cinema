<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/error.css">
    <title>Cinema</title>
</head>
<body>
<div class="wrapper">
    <div class="error">
        <div class="error__body">
            <div class="error__content">
                <div class="error__image">
                    <img src="<%=request.getContextPath()%>/images/homer.png" alt="doh">
                </div>
                <h1 class="error__title">
                    ERROR
                </h1>
            </div>
            <p class="error__subtitle">
                Something went wrong?<br>
                Do not worry
                we are already fixing it!
                <a href="<%=request.getContextPath()%>/" class="error__subtitle">Back to main menu</a>
            </p>
        </div>
    </div>
</div>
</body>
</html>