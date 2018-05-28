var mapStyle = [
    {
        "elementType": "labels",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "administrative",
        "elementType": "geometry",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "administrative.land_parcel",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "administrative.neighborhood",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "poi",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "road",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "road",
        "elementType": "labels.icon",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    },
    {
        "featureType": "transit",
        "stylers": [
            {
                "visibility": "off"
            }
        ]
    }
]
var map;
var layer_1;
var infoWindow;
var marker;
var myMap = new Map();
var districtMap = new Map();
var districtNames = new Array();
var state;
var bannedPrecincts = [];
var bannedDistricts = [];
var PrecintListName = new Array();
var PrecintListGEOID = new Array();
var timer;
var init = 0;
var representatives = new Object();


function doMap() {
    initMap();
    infoWindow = new google.maps.InfoWindow;
    marker = new google.maps.Marker({})
    map.data.addListener('click', function (event) {
        var precinct = event.feature.getProperty('GEOID10')
        if (bannedPrecincts.includes(precinct)) {
            removeFromArray(bannedPrecincts, precinct);
            map.data.revertStyle(event.feature);
        } else {
            bannedPrecincts.push(precinct);
            map.data.overrideStyle(event.feature, {
                strokeWeight: 5,
                strokeOpacity: 1,
                strokeColor: "black",
                fillOpacity: 1
            });
        }
    });
    map.data.addListener('mouseover', function (event) {
        timer = setTimeout(function () {
            showWindow(event)
        }, 1000);
    });
    map.data.addListener('mouseout', function (event) {
        infoWindow.close(map)
        clearTimeout(timer)
    });
    map.data.addListener('rightclick', function (event) {
        var district = event.feature.getProperty('NAMELSAD10');
        if (bannedDistricts.includes(district)) {
            removeFromArray(bannedDistricts, district)
            map.data.forEach(function (feature) {
                if (feature.getProperty("NAMELSAD10") == district) {
                    map.data.revertStyle(feature);
                }
            })
        } else {
            bannedDistricts.push(district);
            map.data.forEach(function (feature) {
                if (feature.getProperty("NAMELSAD10") == district) {
                    map.data.overrideStyle(feature, {
                        strokeWeight: 5,
                        strokeOpacity: 1,
                        strokeColor: "black",
                        fillOpacity: 1
                    });
                }
            })
        }
    });
}

function setDistrictMap() {
    map.data.forEach(function (feature) {
        if (districtNames.includes(feature.getProperty('NAMELSAD10'))) {
        } else {
            districtNames.push(feature.getProperty('NAMELSAD10'));
        }
    });
    for (var i = 0; i < districtNames.length; i++) {
        var districtPop = 0;
        var districtWhites = 0;
        var districtBlacks = 0;
        var districtDemocrats = 0;
        var districtRepublicans = 0;
        map.data.forEach(function (feature) {
            if (feature.getProperty('NAMELSAD10') == districtNames[i]) {
                districtPop += parseInt(feature.getProperty('POPULATION'));
                districtWhites += parseInt(feature.getProperty('WHITES'));
                districtBlacks += parseInt(feature.getProperty('BLACKS'));
                districtDemocrats += parseInt(feature.getProperty('DEMOCRAT'));
                districtRepublicans += parseInt(feature.getProperty('REPUBLICAN'));
            }
        });
        var districtOther = districtPop - districtWhites - districtBlacks;
        var jsonObj = {
            "districtPop": districtPop,
            "districtWhites": districtWhites,
            "districtBlacks": districtBlacks,
            "districtOther": districtOther,
            "districtDemocrats": districtDemocrats,
            "districtRepublicans": districtRepublicans
        }
        districtMap.set(districtNames[i], jsonObj);
    }
}

function removeFromArray(array, val) {
    for (var i = 0; i < array.length; i++) {
        if (array[i] == val) {
            array.splice(i, 1);
        }
    }
}

function showWindow(event) {
    if (init == 0) {
        setDistrictMap();
        init = 1;
    }
    var districtPop = 0;
    var districtWhites = 0;
    var districtBlacks = 0;
    var districtDemocrats = 0;
    var districtRepublicans = 0;
    var democraticPrecincts = 0;
    var republicanPrecincts = 0;
    var districtRepresentative = "";
    map.data.forEach(function (feature) {
        if (feature.getProperty('NAMELSAD10') == event.feature.getProperty('NAMELSAD10')) {
            districtPop += parseInt(feature.getProperty('POPULATION'));
            districtWhites += parseInt(feature.getProperty('WHITES'));
            districtBlacks += parseInt(feature.getProperty('BLACKS'));
            districtDemocrats += parseInt(feature.getProperty('DEMOCRAT'));
            districtRepublicans += parseInt(feature.getProperty('REPUBLICAN'));
            if (parseInt(feature.getProperty('DEMOCRAT')) > parseInt(feature.getProperty('REPUBLICAN'))){
                democraticPrecincts += 1;
            }
            else{
                republicanPrecincts += 1;
            }
            if (democraticPrecincts > republicanPrecincts){
                districtRepresentative = "[Democrat] " + representatives[feature.getProperty('NAMELSAD10')][0];
            }
            else{
                districtRepresentative = "[Republican] " + representatives[feature.getProperty('NAMELSAD10')][1];

            }
        }
    });
    var districtOther = districtPop - districtWhites - districtBlacks;
    if (myMap.has(event.feature.getProperty("NAMELSAD10"))) {
        var obj = myMap.get(event.feature.getProperty("NAMELSAD10"))
        infoWindow.setContent("<div style='width:500px; text-align: center;'>" +
            "<label><u>Precinct Information</u></label>" +
            "<br><label>Precinct Name: &nbsp</label>" + event.feature.getProperty("NAME10") +
            "<br><label>Precinct Population: &nbsp</label>" + event.feature.getProperty("POPULATION") +
            "<br><label>White Population: &nbsp</label>" + event.feature.getProperty("WHITES") +
            "<br><label>Black Population: &nbsp</label>" + event.feature.getProperty("BLACKS") +
            "<br><label>Other Ethnicity: &nbsp</label>" + event.feature.getProperty("OTHER RACES") +
            "<br><label>Democrats: &nbsp</label>" + parseInt(event.feature.getProperty("DEMOCRAT")) +
            "<br><label>Republicans: &nbsp</label>" + event.feature.getProperty("REPUBLICAN") +
            "<br>--------------------------------------------------------------------" +
            "<br><label><u>District Information</u></label>" +
            "<br><label>District Name: &nbsp</label>" + event.feature.getProperty("NAMELSAD10") +
            "<br><label>Representative: &nbsp</label>" + districtRepresentative +
            "<br><label>Current Population: &nbsp</label>" + districtPop + "<label>&nbsp | Original District Population: &nbsp</label>" + districtMap.get(event.feature.getProperty('NAMELSAD10')).districtPop +
            "<br><label>Current White Population: &nbsp</label>" + districtWhites + "<label>&nbsp | Original White Population: &nbsp</label>" + districtMap.get(event.feature.getProperty('NAMELSAD10')).districtWhites +
            "<br><label>Current Black Population: &nbsp</label>" + districtBlacks + "<label>&nbsp | Original Black Population: &nbsp</label>" + districtMap.get(event.feature.getProperty('NAMELSAD10')).districtBlacks +
            "<br><label>Current Other Ethnicity: &nbsp</label>" + districtOther + "<label>&nbsp | Original Other Ethnicity: &nbsp</label>" + districtMap.get(event.feature.getProperty('NAMELSAD10')).districtOther +
            "<br><label>Current Democrats: &nbsp</label>" + districtDemocrats + "<label>&nbsp | Original Democrats: &nbsp</label>" + districtMap.get(event.feature.getProperty('NAMELSAD10')).districtDemocrats +
            "<br><label>Current Republicans: &nbsp</label>" + districtRepublicans + "<label>&nbsp | Original Republicans: &nbsp</label>" + districtMap.get(event.feature.getProperty('NAMELSAD10')).districtRepublicans +
            "<br>--------------------------------------------------------------------" +
            "<br><label><u>District Goodness</u></label>" +
            "<br><label>Polsby Popper: &nbsp</label>" + obj.polsbyPopper +
            "<br><label>Schwartzberg: &nbsp</label>" + obj.schwartzberg +
            "<br><label>Reock: &nbsp</label>" + obj.reock +
            "<br><label>Area Convex Hull: &nbsp</label>" + obj.areaConvexHull +
            "<br><label>Efficiency Gap: &nbsp</label>" + obj.efficiencyGap +
            "<br><label>Equal Population: &nbsp</label>" + obj.equalPopulation +
            "<br><label>Racial Fairness: &nbsp</label>" + obj.racialFairness +
            "</div>"
        )
    } else {
        infoWindow.setContent("<div style='width:500px; text-align: center;'>" +
            "<label><u>Precinct Information</u></label>" +
            "<br><label>Precinct Name: &nbsp</label>" + event.feature.getProperty("NAME10") +
            "<br><label>Precinct Population: &nbsp</label>" + event.feature.getProperty("POPULATION") +
            "<br><label>White Population: &nbsp</label>" + event.feature.getProperty("WHITES") +
            "<br><label>Black Population: &nbsp</label>" + event.feature.getProperty("BLACKS") +
            "<br><label>Other Ethnicity: &nbsp</label>" + event.feature.getProperty("OTHER RACES") +
            "<br><label>Democrats: &nbsp</label>" + parseInt(event.feature.getProperty("DEMOCRAT")) +
            "<br><label>Republicans: &nbsp</label>" + event.feature.getProperty("REPUBLICAN") +
            "<br>--------------------------------------------------------------------" +
            "<br><label><u>District Information</u></label>" +
            "<br><label>District Name: &nbsp</label>" + event.feature.getProperty("NAMELSAD10") +
            "<br><label>Representative: &nbsp</label>" + districtRepresentative +
            "<br><label>Current Population: &nbsp</label>" + districtPop + "<label>&nbsp | Current District Population: &nbsp</label>" + districtMap.get(event.feature.getProperty('NAMELSAD10')).districtPop +
            "<br><label>Current White Population: &nbsp</label>" + districtWhites + "<label>&nbsp | Original White Population: &nbsp</label>" + districtMap.get(event.feature.getProperty('NAMELSAD10')).districtWhites +
            "<br><label>Current Black Population: &nbsp</label>" + districtBlacks + "<label>&nbsp | Original Black Population: &nbsp</label>" + districtMap.get(event.feature.getProperty('NAMELSAD10')).districtBlacks +
            "<br><label>Current Other Ethnicity: &nbsp</label>" + districtOther + "<label>&nbsp | Original Other Ethnicity: &nbsp</label>" + districtMap.get(event.feature.getProperty('NAMELSAD10')).districtOther +
            "<br><label>Current Democrats: &nbsp</label>" + districtDemocrats + "<label>&nbsp | Original Democrats: &nbsp</label>" + districtMap.get(event.feature.getProperty('NAMELSAD10')).districtDemocrats +
            "<br><label>Current Republicans: &nbsp</label>" + districtRepublicans + "<label>&nbsp | Original Republicans: &nbsp</label>" + districtMap.get(event.feature.getProperty('NAMELSAD10')).districtRepublicans +
            "</div>")
    }
    infoWindow.setPosition(event.latLng)
    infoWindow.open(map)
}

function showWindowMoved(precinct) {
    var myHTML = precinct.getProperty("NAME10")
    var lat = precinct.getProperty("INTPTLAT10").replace("+", "")
    var lon = precinct.getProperty("INTPTLON10").replace("+", "")
    var latLng = {lat: parseFloat(lat), lng: parseFloat(lon)};
    infoWindow.setContent("<div style='width:250px; text-align: center;'>" + myHTML + "</div>")
    infoWindow.setPosition(latLng)
    infoWindow.open(map)
}

function updateMap(jsonObj) {
    var precinct = map.data.getFeatureById(jsonObj.id)
    precinct.setProperty('CD111FP', jsonObj.districtId)
    precinct.setProperty('NAMELSAD10', jsonObj.districtName)
    showWindowMoved(precinct)
    setMapStyle()
    setCurrentStateInfo(jsonObj)
}

function setOriginalStateInfo(data) {
    $('#originalState-label').text(state)
    $('#originalPolsbyPopper-label').text(data[0])
    $('#originalSchwartzberg-label').text(data[1])
    $('#originalReock-label').text(data[2])
    $('#originalAreaConvexHull-label').text(data[3])
    $('#originalEfficiencyGap-label').text(data[4])
    $('#originalEqualVoteWeight-label').text(data[5])
    $('#originalEqualPopulation-label').text(data[6])
    $('#originalRacialFairness-label').text(data[7])
}

function setCurrentStateInfo(jsonObj) {
    $('#currentState-label').text(state)
    $('#currentPolsbyPopper-label').text(jsonObj.statePolsbyPopper)
    $('#currentSchwartzberg-label').text(jsonObj.stateSchwartzberg)
    $('#currentReock-label').text(jsonObj.stateReock)
    $('#currentAreaConvexHull-label').text(jsonObj.stateAreaConvexHull)
    $('#currentEfficiencyGap-label').text(jsonObj.stateEfficiencyGap)
    $('#currentEqualVoteWeight-label').text(jsonObj.stateEqualVoteWeight)
    $('#currentEqualPopulation-label').text(jsonObj.stateEqualPopulation)
    $('#currentRacialFairness-label').text(jsonObj.stateRacialFairness)
}

function resetAllInfo() {
    $('#originalState-label').text(NaN)
    $('#originalPolsbyPopper-label').text(0)
    $('#originalSchwartzberg-label').text(0)
    $('#originalReock-label').text(0)
    $('#originalAreaConvexHull-label').text(0)
    $('#originalEfficiencyGap-label').text(0)
    $('#originalEqualVoteWeight-label').text(0)
    $('#originalEqualPopulation-label').text(0)
    $('#originalRacialFairness-label').text(0)
    $('#currentState-label').text(NaN)
    $('#currentPolsbyPopper-label').text(0)
    $('#currentSchwartzberg-label').text(0)
    $('#currentReock-label').text(0)
    $('#currentEfficiencyGap-label').text(0)
    $('#currentEqualPopulation-label').text(0)
    $('#currentRacialFairness-label').text(0)
}

function initMap() {
    var mapOptions = {
        scrollwheel: true,
        navigationControl: true,
        mapTypeControl: true,
        scaleControl: true,
        draggable: true,
        center: new google.maps.LatLng(35.850033, -95.6500523),
        zoom: 4,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById("map"), mapOptions);
    map.set('styles', mapStyle);
    layer_1 = new google.maps.Data();
}

function setRepresentatives(state) {
    switch (state) {
        case 'Maryland': {
            $.getJSON('res/js/marylandRep.json', function (data) {
                representatives = data;
            });
        }
            break;
        case 'Missouri':
            $.getJSON('res/js/missouriRep.json', function (data) {
                representatives = data;
            });
            break;
        case 'West Virginia':
            $.getJSON('res/js/westvirginiaRep.json', function (data) {
                representatives = data;
            });
    }
}

function processLocation(lat, long, state, zoom, districts) {
    map.data.forEach(function (feature) {
        map.data.remove(feature);
    });
    map.setCenter({
        lat: lat,
        lng: long
    });
    map.data.loadGeoJson('res/js/' + state, {
        idPropertyName: "GEOID10"
    });
    layer_1.loadGeoJson('res/js/' + districts);
    setMapStyle();
    setLayerStyle();
    map.setZoom(zoom);
}

function loadLayer(toggle) {
    if (toggle == 0) {
        layer_1.setMap(map);
    } else {
        layer_1.setMap(null);
    }
}

function resetLayer() {
    layer_1.forEach(function (feature) {
        layer_1.remove(feature);
    })
}

function setMapStyle() {
    map.data.setStyle(function (feature) {
        var district = feature.getProperty('CD111FP');
        var color = "gray";
        if (district == "01") {
            color = "Navy ";
        }
        if (district == "02") {
            color = "Crimson";
        }
        if (district == "03") {
            color = "Tomato";
        }
        if (district == "04") {
            color = "DarkMagenta";
        }
        if (district == "05") {
            color = "SeaGreen";
        }
        if (district == "06") {
            color = "Orange";
        }
        if (district == "07") {
            color = "RoyalBlue";
        }
        if (district == "08") {
            color = "Teal";
        }
        if (district == "09") {
            color = "Maroon";
        }
        if (district == "10") {
            color = "blueviolet";
        }
        if (district == "11") {
            color = "black";
        }
        return {
            fillColor: color,
            fillOpacity: 0.6,
            strokeWeight: 1
        }
    });
}

function setLayerStyle() {
    layer_1.setStyle(function (feature) {
        var district = feature.getProperty('CD111FP');
        var color = "gray";
        if (district == "01") {
            color = "Navy ";
        }
        if (district == "02") {
            color = "Crimson";
        }
        if (district == "03") {
            color = "Tomato";
        }
        if (district == "04") {
            color = "DarkMagenta";
        }
        if (district == "05") {
            color = "SeaGreen";
        }
        if (district == "06") {
            color = "Orange";
        }
        if (district == "07") {
            color = "RoyalBlue";
        }
        if (district == "08") {
            color = "Teal";
        }
        if (district == "09") {
            color = "Maroon";
        }
        if (district == "10") {
            color = "blueviolet";
        }
        if (district == "11") {
            color = "black";
        }
        return {
            fillOpacity: 0.1,
            strokeWeight: 7,
            strokeColor: color
        }
    });
}

function addToList() {
    document.getElementById("search-button").disabled = false;
    var i = 0;
    map.data.forEach(function (feature) {
        PrecintListName[i] = (feature.getProperty("NAME10"));
        i++;
    })
    var options = '';
    for (var i = 0; i < PrecintListName.length; i++)
        options += '<option value="' + PrecintListName[i] + '" />';
    document.getElementById('anrede').innerHTML = options;
}

function mapSearch() {
    infoWindow = new google.maps.InfoWindow();
    var i = 0;
    map.data.forEach(function (feature) {
        PrecintListGEOID[i] = (feature.getProperty("GEOID10"));
        i++;
    })
    userInput = document.getElementById('search').value;
    for (var i = 0; i < PrecintListName.length; i++) {
        if (userInput == PrecintListName[i]) {
            userInputIndex = i;
        }
    }
    selectedPrecinct = PrecintListGEOID[userInputIndex];
    var latitude = map.data.getFeatureById(selectedPrecinct).getProperty("INTPTLAT10");
    var longtitude = map.data.getFeatureById(selectedPrecinct).getProperty("INTPTLON10");
    map.setCenter({
        lat: parseFloat(latitude),
        lng: parseFloat(longtitude)
    });
    map.setZoom(11);
    var precinct = map.data.getFeatureById(selectedPrecinct);
    showWindowMoved(precinct);
}