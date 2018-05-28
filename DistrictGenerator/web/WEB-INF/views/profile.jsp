<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title> ${sessionScope.currentUser.nickname}'s Profile </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="res/css/main.css">
    <link rel="stylesheet" href="res/css/profile.css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <script src="res/js/main.js"></script>
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
    <div class="container head-container">
        <h3>Profile</h3>
    </div>
    <div class="container content-container" id="profile-container">
        <div class="row">
            <div class="col-md-2">
                <label>Username: </label>
            </div>
            <div class="col-md-2">
                <p> ${sessionScope.currentUser.username} </p>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>Nickname: </label>
            </div>
            <div class="col-md-2">
                <p>${sessionScope.currentUser.nickname}</p>
            </div>
            <form id="change-nickname-form" action="#" modelAttribute="changeNicknameData">
                <div class="col-md-4">
                    <input name="nickname" type="text" placeholder="Enter nickname"/>
                </div>
                <div class="col-md-2">
                    <input class="profile-form-button" type="submit" value="Proceed"/>
                </div>
            </form>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>Password: </label>
            </div>
            <div class="col-md-2">
                <p>****</p>
            </div>
            <form id="change-password-form" action="#" modelAttribute="changePasswordData">
                <div class="col-md-4">
                    <input id="pw1" name="password" type="password" placeholder="Enter new password"/>
                    <input id="pw2" type="password" placeholder="Enter again"/>
                </div>
                <div class="col-md-2">
                    <input class="profile-form-button" type="submit" value="Proceed"/>
                </div>
                <div class="col-md-2">
                    <div id="error-div">
                        <p>Invalid password selection</p>
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <div class="col-md-2">
                <label>Access Level: </label>
            </div>
            <div class="col-md-2">
                <p>
                    <c:choose>
                        <c:when test="${sessionScope.currentUser.admin == 1}">
                            Admin
                        </c:when>
                        <c:otherwise>
                            Member
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>
            <div class="col-md-2"></div>
            <div>
                <c:choose>
                    <c:when test="${sessionScope.currentUser.admin == 1}">
                        <button id="admin-button" href="/ziZdQ9CSTT">Panel</button>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>
</html>
