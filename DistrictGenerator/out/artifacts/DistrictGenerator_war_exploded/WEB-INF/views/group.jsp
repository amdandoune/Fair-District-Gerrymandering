<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="res/js/googlemap.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="res/css/main.css">
    <link rel="stylesheet" href="res/css/group.css">

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

<div class="container group-container">

    <h3 id="group-header"> Expos </h3>

    <div class="col-md-4"></div>

    <div class="col-md-4">

        <ul>

            <li id="list-head">Group Members</li>
            <li> Abderrahman Dandoune - Back-end developement.</li>
            <li> Billy Ko - Back-end developement</li>
            <li> Billy Kong - Front-end developement</li>
            <li> Xiaocheng Ji - Front-end developement</li>

        </ul>

    </div>

    <div class="col-md-4"></div>

</div>

</body>

</html>