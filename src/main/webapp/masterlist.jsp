<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>Master Statement List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>

<body>

    <%@ include file="menu.jspf" %>

    <div class="container">
        <c:if test="${message ne null}">
            <div class="alert alert-dismissible alert-${type}">
                <button type="button" class="close" data-dismiss="alert" aria-label="close" aria-hidden="true">&times;</button>
                <span>${message}</span>
            </div>
        </c:if>
        <c:remove var="message" scope="session"/>
        <c:remove var="type" scope="session"/>
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
                    <td>${repairRequest.theme}</td>
                    <td>${repairRequest.description}</td>
                    <td>${repairRequest.requestCreator.getUsername()}</td>
                    <form action="${pageContext.request.contextPath}/masterlist?id=${repairRequest.id}" method="post">
                        <td ><div><button type="submit">Close the request</button></div></td>
                    </form>
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