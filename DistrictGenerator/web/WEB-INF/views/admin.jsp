<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin Panel</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="res/css/main.css">
    <link rel="stylesheet" href="res/css/profile.css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <script src="res/js/main.js"></script>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.loggedIn == 1}">
        <jsp:include page="membernav.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="nav.jsp"/>
    </c:otherwise>
</c:choose>


<c:choose>
    <c:when test="${sessionScope.currentUser.admin == 1}">
        <div class="container content-container">
            <div class="container" id="panel-container">
                <div class="container head-container">
                    <h3>Adminstration Panel</h3>
                </div>
                <div class="row">
                    <form id="panel-form">
                        <div class="col-md-3">
                            <div class="row">
                                <label id="error-label">Username</label>
                            </div>
                            <div class="row">
                                <input name="username" type="text"/>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="row">
                                <label>Remove User</label>
                            </div>
                            <div class="row">
                                <input type="submit" class="admin-submit" id="remove-user" value="Proceed"/>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="row">
                                <label>Add Privileges</label>
                            </div>
                            <div class="row">
                                <input type="submit" class="admin-submit" id="add-privilege" value="Proceed"/>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="row">
                                <label>Remove Privileges</label>
                            </div>
                            <div class="row">
                                <input type="submit" class="admin-submit" id="remove-privilege" value="Proceed"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="container" id="userlist-container">
                <div class="row table-head">
                    <div class="col-md-3">
                        <h3>Username</h3>
                    </div>
                    <div class="col-md-3">
                        <h3>Nickname</h3>
                    </div>
                    <div class="col-md-3">
                        <h3>Password</h3>
                    </div>
                    <div class="col-md-3">
                        <h3>Admin</h3>
                    </div>
                </div>
                <c:set var="counter" value="${0}"/>
                <c:forEach items="${sessionScope.userList}" var="user" varStatus="status">
                    <c:choose>
                        <c:when test="${status.index % 2 == 0}">
                            <div class="row table-row even-row">
                                <div class="col-md-3">
                                    <p>${user.username}</p>
                                </div>
                                <div class="col-md-3">
                                    <p>${user.nickname}</p>
                                </div>
                                <div class="col-md-3">
                                    <p>${user.password}</p>
                                </div>
                                <div class="col-md-3">
                                    <p>${user.admin}</p>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="row table-row odd-row">
                                <div class="col-md-3">
                                    <p>${user.username}</p>
                                </div>
                                <div class="col-md-3">
                                    <p>${user.nickname}</p>
                                </div>
                                <div class="col-md-3">
                                    <p>${user.password}</p>
                                </div>
                                <div class="col-md-3">
                                    <p>${user.admin}</p>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </div>

        <div class="container">
            <div class="container head-container">
                <h3>State Frequency</h3>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <label>Maryland</label>
                </div>
                <div class="col-md-4">
                    <label>West Virginia</label>
                </div>
                <div class="col-md-4">
                    <label>Missouri</label>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <p>${sessionScope.visitedMaryland}</p>
                </div>
                <div class="col-md-4">
                    <p>${sessionScope.visitedWestVirginia}</p>
                </div>
                <div class="col-md-4">
                    <p>${sessionScope.visitedMissouri}</p>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="container head-container">
                <h3>Edit Properties File</h3>
            </div>
            <form id="propertiesForm">
                <div class="row">
                    <textarea id="propertiesText" name="propertiesContent">${sessionScope.propertiesContent}</textarea>
                </div>
                <div class="row">
                    <input type="submit" id="submit-properties" value="Update"/>
                </div>
            </form>
        </div>
        >
    </c:when>
    <c:otherwise>
        there's nothing here. . . . .
    </c:otherwise>
</c:choose>


</body>
</html>
