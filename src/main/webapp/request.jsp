<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Creation of request</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>

    <%@ include file="menu.jspf" %>

    <main role="main">
        <div class="jumbotron">
            <div class="container">
                <c:if test="${message ne null}">
                    <div class="alert alert-dismissible alert-${type}">
                        <button type="button" class="close" data-dismiss="alert" aria-label="close" aria-hidden="true">&times;</button>
                        <span>${message}</span>
                    </div>
                </c:if>
                <c:remove var="message" scope="session"/>
                <c:remove var="type" scope="session"/>

                <h1 class="display-3">Create Repair Request</h1>
                <form action="/request" method="post">
                    <div><label> Theme : <input type="text" name="theme"  required autofocus/> </label></div>
                    <div><label> Description: <input type="text" name="description"  required autofocus/> </label></div>
                    <div><input class="btn btn-primary" type="submit" value="Push Statement"/></div>
                </form>
                <br>
            </div>
        </div>
    </main>

    <footer role="contentinfo" class="footer">
        <div class="container">
            <span class="text-muted">© Vitalii Mamchur, 2020</span>
        </div>
    </footer>

</body>

</html>