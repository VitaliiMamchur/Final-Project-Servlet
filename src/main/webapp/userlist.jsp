<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>User Statement List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>

<body>

    <%@ include file="menu.jspf" %>

    <div class="container">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th>Theme</th>
                <th>Description</th>
                <th>Price</th>
                <th>Status</th>
                <th>FeedBack</th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${repairRequests}" var="repairRequest">
                <tr>
                        <%--                <div th:if="${statement.active == true}">--%>
                    <td>${repairRequest.theme}</td>
                    <td>${repairRequest.description}</td>
                    <td>${repairRequest.price}</td>
                    <td>${repairRequest.statusToString()}</td>
                    <c:if test="${repairRequest.feedback eq null}">
                            <form action="${pageContext.request.contextPath}/userlist?id=${repairRequest.id}" method="post">
                                <td><div><label> Feedback: <input type="text" name="feedback" required/> </label></div></td>
                                <td ><div><button class="btn btn-info" type="submit">Send feedback</button></div></td>
                            </form>
                    </c:if>
                    <td>${repairRequest.feedback}</td>
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