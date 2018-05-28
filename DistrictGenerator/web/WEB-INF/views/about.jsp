<!DOCTYPE HTML>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> About Us </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
<div class="container head-container">
    <h3>
        About
        <small class="text-muted"> Guides and Overview</small>
    </h3>
</div>
<div class="container content-container">
    <div class="overview-group">
        <h3 id="overview-header"><b>Overview</b></h3>
        <p align="center" style="font-size:150%;">This is a Congressional Districts Map generator</p>
        <p class="lead">
            It can generate the voting districts for states of Maryland, Rhode island, and West Virginia.
            It can generate many different congressional districts by different years that doesn't
            use gerrymadering in an unfair way. It generates base upon compactness, contiguity, equal
            population, preservation of eisting political communities, partisan fairness, and racial fairness.
        </p>
    </div>
    <div class="instructions-group">
        <h3 id="instructions-header"><b>Instructions</b></h3>
        <p class="lead">The way to use this map generator is to select a state from the state drop down menu,
            select a year from the year drop down menu,
            set the weights and check the boxes for the desired functionalities to be applied to the generating map.
            All the options can be found in the
            <a href="index.html">home page</a> ! </br>
            Make sure to click generate !
        </p>
    </div>
    <div class="measures-group">
        <h3 id="measures-header"><b>Measure Explaination</b></h3>
        <ul class="lead">
            <li><b>Compactness</b>-This is the first slide weight measure on the main page.
                The measure of an electoral district’s compactness refers to the simplicity of its geometric shape.
                The slider will contain weights from 0-10. 0 means setting nothing for Compactness and 10 means setting full for
                Compactness. It contains 4 types of Compactness which are radio buttons below, one of the type will be default
                selected. Note: if weight 0 is selected the 4 types
                will be worthless because Compactness is set to 0.
                <ul>
                    <li><b>PolsbyPopper</b></li>-This is the ratio of the area of the district to the area of a circle whose circumference is equal to the perimeter of the district.
                    The corresponding formula is <img src="res/img/Posby-Popper.PNG" style="width:90px;height:60px;"/> for score.
                    <li><b>Shwartzberg</b></li>-This is the ratio of the perimeter of the district to the circumference of a circle whose area is equal to the area of the district.
                    To generate the Schwartzberg score, first the circumference of a circle with an equal area of the district must be calculated.
                    The corresponding formula is <img src="res/img/Schwartzberg.PNG" style="width:100px;height:40px;"/> for radius,
                    <img src="res/img/Schwartzberg1.PNG" style="width:95px;height:40px;"/> for circumference, and
                    <img src="res/img/Schwartzberg2.PNG" style="width:70px;height:50px;"/> for actual score.
                    <li><b>AreaConvexHull</b></li>-This is a ratio of the area of the district to the area of the minimum convex polygon that can enclose the district’s geometry.
                    The corresponding formula is to create a new table with the convex hull polygons, then make sure that the SRID is Albers Equal Area for calculations
                    and bring the convex hull area into the original districts table to calculate the ratio.
                    <img src="res/img/AreaConvexHull.PNG" style="width:250px;height:60px;"/> for actual score.
                    <li><b>Reock</b></li>-This is a measure of the ratio of the area of the district to the area of the minimum bounding circle that encloses the district’s geometry.
                    The corresponding formula is making new table with minimum bounding circles for each district, then bring the convex hull area into the original districts table to calculate the ratio.
                </ul>
            </li>
            <li><b>Partisan Fairnesss</b>-This if the second slide weight measure on the main page.
                This is to achieve to opposite of partisan gerrymandering which it makes it easier for the party in control to win the maximum
                number of seats, resulting in disproportionate representation of the two parties.
                The slider will contain weights from 0-10. 0 means setting nothing for Partisan Fairnesss and 10 means setting full for
                Partisan Fairnesss. It contains 3 types of Partisan Fairnesss which are radio buttons below, one of the type will be default
                selected. Note: if weight 0 is selected the 4 types
                will be worthless because Partisan Fairnesss is set to 0.
                <ul>
                    <li><b>Symmetry</b></li>-This is the showing how fairly the two parties are treated at the state level
                    by comparing the average number of seats won with the average number of votes received. Analysts often generate additional data points
                    using information from similar but different elections.
                    <li><b>SwingRatio</b></li>-This is a measure of the competitiveness of an election.This is the change in seats won relative to the change
                    in the votes received; it is the rate of change of the number of seats a party wins as a function of the votes they receive.It is generally viewed as being
                    most useful for gauging gerrymandering designed to protect incumbents, because this type of
                    gerrymandering acts to make it more difficult for the vote to swing in the direction of the challenger.
                    <li><b>Efficiency Gap</b></li>-This is a metric intended to quantify the difference in voting effectiveness between the two parties
                    It measures a quantity very similar to symmetry, it does so using a more direct approach and without the use of data derived from different elections.
                </ul>
            </li>
            <li><b>Equal Population</b></li>-This is the third slide weight measure on the main page.
            It means that each person’s vote carries the same power in electing a lawmaker.This is calculated by measuring how much
            each district’s population varies from the number of people
            each district should have in it, based on the most recent census figures. The greater the variation, the lower the map scores.
            The slider will contain weights from 0-10. 0 means setting nothing for Equal Population and 10 means setting full for
            Equal Population. It contains 4 types of Equal Population which are radio buttons below, one of the type will be default
            selected. Note: if weight 0 is selected the 4 types
            will be worthless because Equal Population is set to 0.
            <li><b>Racial Fairness</b></li>-This is the fourth slide weight measure on the main page.
            This fairness helps to compute evenly distrubuted races for districts.
            The slider will contain weights from 0-10. 0 means setting nothing for Racial Fairness and 10 means setting full for
            Racial Fairness. It contains 4 types of Racial Fairness which are radio buttons below, one of the type will be default
            selected. Note: if weight 0 is selected the 4 types
            will be worthless because Racial Fairness is set to 0.


            <li><b>Contiguity</b></li>- This is the first checkbox measure on the main page.
            Checking if the precints is continuously connected, it can't be broken off geographically. There are exceptions.
            If the checkbox is ticked, it means there is Contiguity, and when not ticked means there is no Contiguity.

            <li><b>Conservation of existing political community</b></li>-This is the second checkbox measure on the main page.
            It means keeping the current political community as possible. Geographical areas, such as neighborhoods of a city or regions of a state,
            where the residents have common political interests that do not necessarily coincide
            with the boundaries of a political subdivision, such as a city or county.
            If the checkbox is ticked, it means there is Conservation of existing political community,
            and when not ticked means there is no Conservation of existing political community.
        </ul>

    </div>
</div>
</div>
</body>
</html>