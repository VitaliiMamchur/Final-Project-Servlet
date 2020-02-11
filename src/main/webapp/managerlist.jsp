<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Manager Statement List</title>
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
                <th>Request Creator</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${repairRequests}" var="repairRequest">
                    <tr>
                        <td>${repairRequest.theme}</td>
                        <td>${repairRequest.description}</td>
                        <td>${repairRequest.requestCreator.getUsername()}</td>
                        <form action="${pageContext.request.contextPath}/managerlist?acceptId=${repairRequest.id}" method="post">
                            <td><div><label> Price: <input type="number"  min="0" name="price" required/> </label></div></td>
                            <td ><div><button type="submit" class="btn btn-success">Accept the request</button></div></td>
                        </form>

                        <form action="${pageContext.request.contextPath}/managerlist?declineId=${repairRequest.id}" method="post">
                            <td ><div><button type="submit" class="btn btn-danger">Decline the request</button></div></td>
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