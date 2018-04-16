<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Register new user</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="res/js/register.js"></script>
    <link rel="stylesheet" href="res/css/main.css">
    <link rel="stylesheet" href="res/css/register.css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
</head>
<body>
<div class="blankContainer" id="bContainer">
    <c:choose>
        <c:when test="${sessionScope.loggedIn == 1}">
            <jsp:include page="membernav.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="nav.jsp"/>
        </c:otherwise>
    </c:choose>
</div>
<div class="container head-container">
    <h3>Register Account</h3>
</div>
<div class="container" id="content-container">
    <div class="error-div ed-1">
        <p style="color:red;">
            Invalid username selection
        </p>
    </div>
    <form id="register-form" action="#" modelAttribute="signUpFormData">
        <div class="col-md-4"></div>
        <div class="col-md-4 form-line">
            <div class="form-group">
                <label>Username:</label>
                <input type="text" class="form-control" name="username" placeholder="Enter a username">
            </div>
            <div class="form-group">
                <label>Nickname:</label>
                <input type="text" class="form-control" name="nickname" placeholder="Enter desired nickname">
            </div>
            <div class="form-group">
                <label>Password:</label>
                <input id="pw1" type="password" class="form-control" name="password" placeholder="Enter a password">
            </div>
            <div class="form-group">
                <label>Re-enter password:</label>
                <input id="pw2" type="password" class="form-control" placeholder="Re-enter password">
            </div>
            <div class="error-div ed-2">
                <p style="color:red;">
                    Invalid password selection
                </p>
            </div>
            <div>
                <button type="submit" id="register-submit" class="btn btn-default submit"> Register</button>
            </div>
        </div>
        <div class="col-md-4"></div>
    </form>
</div>
</body>
</html>