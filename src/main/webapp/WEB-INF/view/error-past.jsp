<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" language="java"  pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/errpast.css">
  <title>Cinema</title>
</head>
<body>

  <div class="errpast">
    <img src="<%=request.getContextPath()%>/images/errbg.jpg" alt="">
    <h2 class="errpast__subtitle">Are you going back in time?</h2>
    <h1 class="errpast__title">
      <span class="errpast__before fas fa-long-arrow-alt-right">
        
      </span>
      <a href="<%=request.getContextPath()%>/">Return to the present</a>

      <span class="errpast__after fas fa-long-arrow-alt-left">
      </span>
    </h1>
  </div>
  
  <script src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/129ede24b4.js" crossorigin="anonymous"></script>
</body>
</html>