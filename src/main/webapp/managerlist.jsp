<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>Manager Statement List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="/js/script.js"></script>
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
        <div th:if="${message}" th:fragment="alert (type, message)" class="alert alert-dismissible" th:classappend="'alert-' + ${type}">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <span th:text="${message}">Test</span>
        </div>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th>Theme</th>
                <th>Description</th>
                <th>Request Creator</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${repairRequests}" var="repairRequest">
    <%--        <div th:if="${statement.active == true}">--%>
                <tr>
                    <td>${repairRequest.theme}</td>
                    <td>${repairRequest.description}</td>
                    <td>${repairRequest.requestCreator.getUsername()}</td>
                    <form action="/managerstatementlist/?=${statement.id}" method="post">
                        <td><div><label> Price: <input type="number"  min="0" name="price" required/> </label></div></td>
                        <td ><div><button type="submit" class="btn btn-success">Accept the request</button></div></td>
                    </form>

                    <form action="'javascript:confirmDelete(' + ${repairRequest.id} + ');'">
                        <td ><div><button type="submit" class="btn btn-danger">Decline the request</button></div></td>
                    </form>
                </tr>
                <%--            </div>--%>
            </c:forEach>
        </table>
    </div>

    <div class="modal" id="myModal">
        <div class="modal-dialog modal-dialog-centered" >
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Close Statement?</h4>
                </div>
                <div class="modal-footer">
                    <form id="deleteForm" method="post" >
                        <input type="submit" id="submitBtn" value="Confirm">
                        <input type="button" value="Cancel" onclick="cancel()">
                    </form>
                </div>
            </div>
        </div>
    </div>

    <footer role="contentinfo" class="footer">
        <div class="container">
            <span class="text-muted">© Vitalii Mamchur, 2020</span>
        </div>
    </footer>

</body>
</html>