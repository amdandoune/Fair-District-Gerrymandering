<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <link rel="stylesheet" href="res/css/register.css">
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
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><b>Login</b> <span
                        class="glyphicon glyphicon-log-in"></span></a>
                <ul id="login-dp" class="dropdown-menu">
                    <li>
                        <div class="row">
                            <div class="col-md-12">
                                <form id="login-form" class="form" role="form" accept-charset="UTF-8" id="login-nav">
                                    <div class="form-group">
                                        <label>Member Access</label>
                                        <div class="error-div ed-3"><p>Invalid username and/or password</p></div>
                                        <input name="username" type="text" class="form-control"
                                               placeholder="Username" required>
                                    </div>
                                    <div class="form-group">
                                        <input name="password" type="password" class="form-control"
                                               placeholder="Password" required>
                                        <%--<div class="help-block text-right"><a href="">Forget the password ?</a></div>--%>
                                    </div>
                                    <div class="form-group">
                                        <button type="submit" class="btn btn-primary btn-block">Log in</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </li>
                </ul>
            </li>
            <li><a href="${pageContext.request.contextPath}/signup" id="register-button"><span
                    class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        </ul>
    </div>
</nav>
</body>
</html>