var mapStyle = [{
    featureType: "administrative",
    elementType: "labels",
    stylers: [ { visibility: "off" } ]
},{
    featureType: "poi",
    elementType: "labels",
    stylers: [ { visibility: "off" } ]
},{
    featureType: "water",
    elementType: "labels",
    stylers: [ { visibility: "off" } ]
},{
    featureType: "road",
    elementType: "labels",
    stylers: [ { visibility: "off" } ]
}];


var map;

function initMap() {
    var mapOptions = {
        scrollwheel: false,
        navigationControl: false,
        mapTypeControl: true,
        scaleControl: false,
        draggable: true,
        center: new google.maps.LatLng(35.850033, -95.6500523),
        zoom: 4,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        mapTypeId: google.maps.MapTypeId.TERRAIN
    }
    map = new google.maps.Map(document.getElementById("map"), mapOptions);
    map.set('styles', mapStyle)
}

function processLocation(lat, long, state, zoom, color) {

    map.data.forEach(function(feature) {
      map.data.remove(feature);
    });

    map.setCenter({
        lat: lat,
        lng: long
    });

    map.data.addGeoJson(state);
    var featureStyle = {
        strokeColor: color,
        strokeWeight: 4
    }
    map.data.setStyle(featureStyle);
    map.setZoom(zoom);
}

// google.maps.event.addDomListener(window, 'load', initMaps);

$(document).ready(function() {
    $('#submit-filter').click(function(event) {
        event.preventDefault();
        state = $('#state-selector').find("option:selected").val();
        $('#state-label').text(state);
        switch(state) {
            case 'Maryland':
                processLocation(39.045753, -77.3, maryland, 7.5, "#800000");
                $('#pop-label').text('5,773,552');
                $('#ethnicity-label').text('hispanic or latino(470,632), white(3,359,284), african american(1,700,298), asians(318,853)');
                $('#party-label').text("Democrat");
                $('#male-pop-label').text('2,791,762');
                $('#female-pop-label').text("2,981,790");
                $('#over18-label').text('4,420,588');
                $('#under18-label').text('1,352,964');
                break;
            case 'West Virginia':
                processLocation(38.9, -80.690674, westvirginia, 7, "#103486")
                $('#pop-label').text('1,852,994');
                $('#ethnicity-label').text('hispanic or latino(22268), white(1,739,988), african american(63,124), asians(12,406)');
                $('#party-label').text("Republican");
                $('#male-pop-label').text('913,586');
                $('#female-pop-label').text("939,408");
                $('#over18-label').text('1,465,576');
                $('#under18-label').text('387,418');
                break;
            case 'Rhode Island':
                processLocation(41.5800945, -71.4774291, rhodeisland, 9, "#CCCC00");
                $('#pop-label').text('1,052,567');
                $('#ethnicity-label').text('hispanic or latino(130,655), white(856,869), african american(60,189), asians(30,457)');
                $('#party-label').text("Democrat");
                $('#male-pop-label').text('508,400');
                $('#female-pop-label').text("544,167");
                $('#over18-label').text('828,611');
                $('#under18-label').text('223,956');
                break;

            case 'Missouri':
                processLocation(38.627003, -90.199402, missouri, 8, "#434444");
                $('#pop-label').text('1,052,567');
                $('#ethnicity-label').text('hispanic or latino(130,655), white(856,869), african american(60,189), asians(30,457)');
                $('#party-label').text("Democrat");
                $('#male-pop-label').text('508,400');
                $('#female-pop-label').text("544,167");
                $('#over18-label').text('828,611');
                $('#under18-label').text('223,956');
                break;

        }
    });
});
