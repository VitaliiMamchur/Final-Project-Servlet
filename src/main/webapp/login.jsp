<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body class="hm-gradient">

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<header class="header">
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark" role="navigation">

        <a class="navbar-brand" href="/index" role="banner">Main</a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsDefault" aria-controls="navbarsDefault" aria-expanded="false" aria-label="Переключить навигацию">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsDefault">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/request">Create Repair Request<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/userlist">My Request List<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/managerlist">Check Request List<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/masterlist">Check Request List<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/login">Log in<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/registration">Sign in<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="@{/logout}">Log out</a>
                </li>
            </ul>
        </div>
    </nav>
    </br>
</header>
<br><br>

<div class="container">
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center">Log In</h4>
            <form class="form-signin" action="${pageContext.request.contextPath}/login" method="post">

                <input type="username"  name="username" class="form-control" placeholder="Login" required>
                <input type="password" name="password" class="form-control" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Log in</button>
                <p class="text-center">Do not have an account? <a href="${pageContext.request.contextPath}/registration">Sign In</a></p>
            </form>
        </article>
    </div>
</div>

</body>
</html>