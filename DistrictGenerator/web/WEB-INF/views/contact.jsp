<!DOCTYPE HTML>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> Contact Us </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="res/css/main.css">
    <link rel="stylesheet" href="res/css/contact.css">
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
    <h3>Contact</h3>
</div>
<div class="container content-container">
    <div class="email-group">
        <h3 id="email-header">E-mails</h3>
        <p id="email-info" class="lead">
            Billy Kong : billy.kong@stonybrook.edu</br>
            Xiaocheng Ji: xiaocheng.ji@stonybrook.edu</br>
            Abderrahman Dandoune: abderrahman.dandoune@stonybrook.edu</br>
            Billy Ko: billy.ko@stonybrook.edu</br>
        </p>
    </div>
</div>
<div class="col-md-4"></div>
</div>
</body>
</html>