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
    <script src="res/js/page.js"></script>
    <script async defer
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC7wVgVkYNIiAY7G91MzeD3PSxjFlKxo8I&callback=doMap"
            type="text/javascript"></script>
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
<div class="content">
    <div class="left">
        <form id="filter-form" method="POST">
            <div class="row f-row">
                <div class="col-md-3"><p id="state-error">Please select a State</p></div>
                <select class="custom-select" name="state" id="state-selector" data-style="btn-info"
                        onclick="addToList()">
                    <option value="none" selected disabled hidden>Select State</option>
                    <option value="Maryland">Maryland</option>
                    <option value="West Virginia">West Virginia</option>
                    <option value="Missouri">Missouri</option>
                </select>
                <select class="custom-select" name="year" data-style="btn-info">
                    <option value="2010" selected="selected">2010</option>
                </select>
            </div>
            <div class="row f-row">
                <div class="row">
                    <label>Polsby Popper - </label>
                    <label id="polsbyPopperWeight">0</label>
                    <input id="polsbyPopperWeightInput" value="0" name="polsbyPopper" type="hidden">
                </div>
                <div class="row">
                    <div class="col-md-9">
                        <input type="range" id="polsbyPopper" min="0" max="10" value="0" class="slider"
                               onchange="getPolsbyPopperWeight()">
                    </div>
                </div>
            </div>
            <div class="row f-row">
                <div class="row">
                    <label>Shwartzberg - </label>
                    <label id="schwartzbergWeight">0</label>
                    <input id="schwartzbergWeightInput" value="0" name="schwartzberg" type="hidden">
                </div>
                <div class="row">
                    <div class="col-md-9">
                        <input type="range" id="schwartzberg" min="0" max="10" value="0" class="slider"
                               onchange="getSchwartzbergWeight()">
                    </div>
                </div>
            </div>
            <div class="row f-row">
                <div class="row">
                    <label>Reock - </label>
                    <label id="reockWeight">0</label>
                    <input id="reockWeightInput" value="0" name="reock" type="hidden">
                </div>
                <div class="row">
                    <div class="col-md-9">
                        <input type="range" id="reock" min="0" max="10" value="0" class="slider"
                               onchange="getReockWeight()">
                    </div>
                </div>
            </div>
            <div class="row f-row">
                <div class="row">
                    <label>Area Convex Hull - </label>
                    <label id="areaConvexHullWeight">0</label>
                    <input id="areaConvexHullWeightInput" value="0" name="areaConvexHull" type="hidden">
                </div>
                <div class="row">
                    <div class="col-md-9">
                        <input type="range" id="areaConvexHull" min="0" max="10" value="0" class="slider"
                               onchange="getAreaConvexHullWeight()">
                    </div>
                </div>
            </div>
            <div class="row f-row">
                <div class="row">
                    <label>Efficiency Gap - </label>
                    <label id="efficiencyGapWeight">0</label>
                    <input id="efficiencyGapWeightInput" value="0" name="efficiencyGap" type="hidden">
                </div>
                <div class="row">
                    <div class="col-md-9">
                        <input type="range" id="efficiencyGap" min="0" max="10" value="0" class="slider"
                               onchange="getEfficiencyGap()">
                    </div>
                </div>
            </div>
            <div class="row f-row">
                <div class="row">
                    <label>Equal Vote Weight - </label>
                    <label id="equalVoteWeightWeight">0</label>
                    <input id="equalVoteWeightWeightInput" value="0" name="equalVoteWeight" type="hidden">
                </div>
                <div class="row">
                    <div class="col-md-9">
                        <input type="range" id="equalVoteWeight" min="0" max="10" value="0" class="slider"
                               onchange="getEqualVoteWeightWeight()">
                    </div>
                </div>
            </div>
            <div class="row f-row">
                <div class="row">
                    <label>Equal Population - </label>
                    <label value="1" id="EqualPopulationWeight">0</label>
                    <input id="EqualPopulationWeightInput" value="0" name="equalPopulation" type="hidden">
                </div>
                <div class="row">
                    <div class="col-md-9">
                        <input type="range" id="equal-population" min="0" max="10" value="0" class="slider"
                               onchange="getEqualPopulationWeight()">
                    </div>
                </div>
            </div>
            <div class="row f-row">
                <div class="row">
                    <label>Racial Fairness - </label>
                    <label id="RacialFairnessWeight">0</label>
                    <input id="RacialFairnessWeightInput" value="0" name="racialFairness" type="hidden">
                </div>
                <div class="row">
                    <div class="col-md-9">
                        <input type="range" id="racial-fairness" min="0" max="10" value="0" class="slider"
                               onchange="getRacialFairnessWeight()">
                    </div>
                </div>
            </div>
            <div class="row f-row">
                <button id="reset-button" class="btn btn-primary">Reset</button>
                <button id="submit-filter" class="btn btn-primary" disabled="disabled">Start</button>
            </div>
        </form>
        <div class="f-row">
            <button id="toggleLayer" class="btn btn-primary">Display Original</button>
            <button id="download-button" class="btn btn-primary">Download</button>
        </div>
        <div class="input-group">
            <input id="search" list="anrede">
            <datalist id="anrede"></datalist>
            <button id="search-button" onclick="mapSearch()" class="btn btn-primary" disabled>Search</button>
        </div>
    </div>
    <div class="right">
        <div class="inner-left">
            <div id="map"></div>
        </div>
        <div class="inner-right">
            <h5><label><u>Original State Goodness</u></label></h5>
            <div class="row">
                <label>State: </label>
                <p id="originalState-label">NaN</p>
            </div>
            <div class="row">
                <label>Polsby Popper:</label>
                <p id="originalPolsbyPopper-label">0</p>
            </div>
            <div class="row">
                <label>Schwartzberg:</label>
                <p id="originalSchwartzberg-label">0</p>
            </div>
            <div class="row">
                <label>Reock:</label>
                <p id="originalReock-label">0</p>
            </div>
            <div class="row">
                <label>Area Convex Hull:</label>
                <p id="originalAreaConvexHull-label">0</p>
            </div>
            <div class="row">
                <label>Efficiency Gap: </label>
                <p id="originalEfficiencyGap-label">0</p>
            </div>
            <div class="row">
                <label>Equal Vote Weight: </label>
                <p id="originalEqualVoteWeight-label">0</p>
            </div>
            <div class="row">
                <label>Equal Population: </label>
                <p id="originalEqualPopulation-label">0</p>
            </div>
            <div class="row">
                <label>Racial Fairness: </label>
                <p id="originalRacialFairness-label">0</p>
            </div>
            <h5><label><u>Current State Goodness</u></label></h5>
            <div class="row">
                <label>State: </label>
                <p id="currentState-label">NaN</p>
            </div>
            <div class="row">
                <label>Polsby Popper:</label>
                <p id="currentPolsbyPopper-label">0</p>
            </div>
            <div class="row">
                <label>Schwartzberg:</label>
                <p id="currentSchwartzberg-label">0</p>
            </div>
            <div class="row">
                <label>Reock:</label>
                <p id="currentReock-label">0</p>
            </div>
            <div class="row">
                <label>Area Convex Hull:</label>
                <p id="currentAreaConvexHull-label">0</p>
            </div>
            <div class="row">
                <label>Efficiency Gap: </label>
                <p id="currentEfficiencyGap-label">0</p>
            </div>
            <div class="row">
                <label>Equal Vote Weight: </label>
                <p id="currentEqualVoteWeight-label">0</p>
            </div>
            <div class="row">
                <label>Equal Population: </label>
                <p id="currentEqualPopulation-label">0</p>
            </div>
            <div class="row">
                <label>Racial Fairness: </label>
                <p id="currentRacialFairness-label">0</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>