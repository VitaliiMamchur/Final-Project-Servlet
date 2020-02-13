<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body class="hm-gradient">

<%@ include file="menu.jspf" %>

<div class="container">
    <c:if test="${message ne null}">
    <div class="alert alert-dismissible alert-${type}">
        <button type="button" class="close" data-dismiss="alert" aria-label="close" aria-hidden="true">&times;</button>
        <span>${message}</span>
    </div>
    </c:if>
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center">Log In</h4>
            <form class="form-signin" action="${pageContext.request.contextPath}/login" method="post">

                <input type="username"  name="username" class="form-control" placeholder="Login" required>
                <input type="password" name="password" class="form-control" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Log in</button>
                <p class="text-center">Do not have an account? <a href="${pageContext.request.contextPath}/registration">Sign Up</a></p>
            </form>
        </article>
    </div>
</div>

</body>
</html>