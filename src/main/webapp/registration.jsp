<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Registration</title>
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

<div class="container">
    <div class="card bg-light">
        <article class="card-body mx-auto" style="max-width: 400px;">
            <h4 class="card-title mt-3 text-center">Create Account</h4>
            <p class="text-center">Get started with your free account</p>
            <br>
            <form action="/registration" method="post">
                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                    </div>
                    <input name="username" class="form-control" placeholder="Full name" type="text" required>
                </div>

                <div class="form-group input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
                    </div>
                    <input name="password" class="form-control" placeholder="Create password" type="password" required>
                </div> <!-- form-group// -->

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> Create Account</button>
                </div> <!-- form-group// -->
                <p class="text-center">Have an account? <a href="/login">Log In</a></p>
            </form>
            <div th:if="${message}" th:fragment="alert (type, message)" class="alert alert-dismissible" th:classappend="'alert-' + ${type}">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                <span th:text="${message}">Test</span>
            </div>
        </article>
    </div> <!-- card.// -->
</div>
<!--container end.//-->
<br>
</body>
</html>