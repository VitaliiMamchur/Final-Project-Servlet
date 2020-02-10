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
<%--                <c:if test="${repairRequest.active} eq true">--%>
                    <tr>
                        <td>${repairRequest.theme}</td>
                        <td>${repairRequest.description}</td>
                        <td>${repairRequest.requestCreator.getUsername()}</td>
                        <form action="${pageContext.request.contextPath}/managerlist?acceptId=${repairRequest.id}" method="post">
                            <td><div><label> Price: <input type="number"  min="0" name="price" required/> </label></div></td>
                            <td ><div><button type="submit" class="btn btn-success">Accept the request</button></div></td>
                        </form>

                        <form action="'javascript:confirmDelete(' + ?declineId=${repairRequest.id} + ');'">
                            <td ><div><button type="submit" class="btn btn-danger">Decline the request</button></div></td>
                        </form>
                    </tr>
<%--                </c:if>--%>
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