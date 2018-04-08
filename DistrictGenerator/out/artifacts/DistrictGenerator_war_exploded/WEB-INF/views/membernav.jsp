<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>

<head>

    <script src="res/js/register.js"></script>

</head>
<body>
<nav class="navbar navbar-light">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">District Generator</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/group">Group</a></li>
            <li><a href="/contact">Contact</a></li>
            <li><a href="/about">About</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"> Hello ${sessionScope.userNickname} &nbsp <span class="glyphicon glyphicon-user"></span>
            </a></li>
            <li><a id="logout-button" href=#"> <span class="glyphicon glyphicon-log-out"></span> &nbsp Log out </a></li>
        </ul>
    </div>
</nav>
</body>

</html>