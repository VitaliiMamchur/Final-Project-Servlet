<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>Master Statement List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>

<body>
    <header class="header">
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark" role="navigation">

            <a class="navbar-brand" href="${pageContext.request.contextPath}/index" role="banner">Main</a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsDefault" aria-controls="navbarsDefault" aria-expanded="false" aria-label="Переключить навигацию">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsDefault">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/request">Create Repair Request<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/userlist">My Request List<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/managerlist">Check Request List<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/masterlist">Check Request List<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/login">Log in<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/registration">Sign in<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Log out</a>
                    </li>
                </ul>
            </div>
        </nav>
        </br>
    </header>

    <div class="container">
<%--        <div th:if="${message}" th:fragment="alert (type, message)" class="alert alert-dismissible" th:classappend="'alert-' + ${type}">--%>
<%--            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>--%>
<%--            <span th:text="${message}">Test</span>--%>
<%--        </div>--%>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th>Theme</th>
                <th>Description</th>
                <th>Request Creator</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${repairRequests}" var="repairRequest">
            <tr>
<%--                <div th:if="${statement.active == true}">--%>
                    <td>${repairRequest.theme}</td>
                    <td>${repairRequest.description}</td>
                    <td>${repairRequest.requestCreator.getUsername()}</td>
                    <form action="${pageContext.request.contextPath}/masterlist?id=2" method="post">
                        <td ><div><button type="submit">Close the request</button></div></td>
                    </form>
<%--                </div>--%>
            </tr>
            </c:forEach>
        </table>
    </div>

    <footer role="contentinfo" class="footer">
        <div class="container">
            <span class="text-muted">© Vitalii Mamchur, 2020</span>
        </div>
    </footer>

</body>

</html>