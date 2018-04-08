<!DOCTYPE HTML>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="res/js/googlemap.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="res/css/main.css">
    <link rel="stylesheet" href="res/css/about.css">
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

<div class="container" id="about-container">
    <h1 id="about-header">About
        <small class="text-muted"> Guides and Overview</small>
    </h1>
    <div class="col-md-12"></div>
    <div class="col-md-12">
        <div class="overview-group">
            <h3 id="overview-header">Overview</h3>
            <p align="center" style="font-size:150%;">This is a Congressional Districts Map generator</p>
            <p class="lead">
                It can generate the voting districts for states of Maryland, Rhode island, and West Virginia.
                It can generate many different congressional districts by different years that doesn't
                use gerrymadering in an unfair way. It generates base upon compactness, contiguity, equal
                population, preservation of eisting political communities, partisan fairness, and racial fairness.
            </p>
        </div>
        <div class="instructions-group">
            <h3 id="instructions-header">Instructions</h3>
            <p class="lead">The way to use this map generator is to select any of the state in the
                selecte state drop down menu, select any year in the select year drop down menu,
                and select any filter option in the filter option menu. All the options can be found in the
                <a href="index.html">home page</a> ! </br>
                Make sure to click generate !
            </p>
        </div>
    </div>
</div>
</body>
</html>