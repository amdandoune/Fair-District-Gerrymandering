<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="res/js/googlemap.js"></script>
    <script src="res/js/main.js"></script>

    <!-- <script src="res/js/maryland.js"></script> -->
    <!-- <script src="res/js/westvirginia.js"></script> -->
    <script src="res/js/rhodeisland.js"></script>


    <!-- voting precints -->
    <%--<script src="res/js/westvirginia_voting_2011.js"></script>--%>
    <%--<script src="res/js/maryland_voting_2010.js"></script>--%>
    <%--<script src="res/js/missouri_2010.js"></script>--%>

    <link rel="stylesheet" href="res/css/main.css">
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

<div class="container filter">
    <div class="row">
        <form method="POST">
            <div class="col-md-4">
                <select class="selectpicker" id="state-selector" data-style="btn-info">
                    <option value="" selected disabled hidden>Select State</option>
                    <option value="Maryland">Maryland</option>
                    <!-- <option value="Rhode Island">Rhode Island</option> -->
                    <option value="West Virginia">West Virginia</option>
                    <!-- <option value="Maine">Maine</option> -->
                    <option value="Missouri">Missouri</option>
                </select>

                <select class="selectpicker" data-style="btn-info">
                    <option value="" selected disabled hidden>2010</option>
                    <option value="2017">2010</option>
                </select>
            </div>
            <div class="col-md-4">
                <select class="selectpicker selectprio" id="priority-high" data-style="btn-info">
                    <option value=" "> Select High Priority</option>
                    <option value="Historical">Display Historical Map</option>
                    <option value="Compactness">Compactness</option>
                    <option value="Contiguity">Contiguity</option>
                    <option value="Population">Preservation Population</option>
                    <option value="Preservation">Preservation of Existing Political Communities</option>
                    <option value="Partisan">Partisan Fairness</option>
                    <option value="Racial">Racial Fairness</option>
                </select>
                <select class="selectpicker selectprio" id="priority-med" data-style="btn-info">
                    <option value=" "> Select Medium Priority</option>
                    <option value="Compactness">Compactness</option>
                    <option value="Contiguity">Contiguity</option>
                    <option value="Population">Preservation Population</option>
                    <option value="Preservation">Preservation of Existing Political Communities</option>
                    <option value="Partisan">Partisan Fairness</option>
                    <option value="Racial">Racial Fairness</option>
                </select>
                <select class="selectpicker selectprio" id="priority-low" data-style="btn-info">
                    <option value=" "> Select Low Priority</option>
                    <option value="Compactness">Compactness</option>
                    <option value="Contiguity">Contiguity</option>
                    <option value="Population">Preservation Population</option>
                    <option value="Preservation">Preservation of Existing Political Communities</option>
                    <option value="Partisan">Partisan Fairness</option>
                    <option value="Racial">Racial Fairness</option>
                </select>
            </div>
            <div class="col-md-4">
                <button id="submit-filter" class="btn btn-primary">Generate</button>
            </div>
        </form>
    </div>
</div>
<div class="container content-container">
    <div class="row">
        <div class="col-md-8">
            <div class="container map-container">
                <div id="map"></div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="container info-container">
                <h3> Demographic Information </h3>
                <div class="row">
                    <label>State: </label> <label id="state-label"></label>
                </div>
                <div class="row">
                    <label>Population:</label> <label id="pop-label"></label>
                </div>
                <div class="row">
                    <label>Ethnicities:</label> <label id="ethnicity-label"></label>
                </div>
                <div class="row">
                    <label>Leading Political Party:</label> <label id="party-label"></label>
                </div>
                <div class="row">
                    <label>Male Population: </label> <label id="male-pop-label"></label>
                </div>
                <div class="row">
                    <label>Female Population: </label> <label id="female-pop-label"></label>
                </div>
                <div class="row">
                    <label>Population(18+): </label> <label id="over18-label"></label>
                </div>
                <div class="row">
                    <label>Population(18-): </label> <label id="under18-label"></label>
                </div>

            </div>
        </div>
    </div>
</div>
<!--    <script src="https://maps.googleapis.com/maps/api/js?callback=myMap"></script>-->
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC7wVgVkYNIiAY7G91MzeD3PSxjFlKxo8I&callback=initMap"
        type="text/javascript"></script>
</body>
</html>
