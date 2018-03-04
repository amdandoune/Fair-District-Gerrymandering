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
        draggable: false,
        center: new google.maps.LatLng(41.850033, -87.6500523),
        zoom: 4,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        mapTypeId: google.maps.MapTypeId.TERRAIN
    }
    map = new google.maps.Map(document.getElementById("map"), mapOptions);
    map.set('styles', mapStyle)
}

function processLocation(lat, long, state, zoom) {
    map.setCenter({
        lat: lat,
        lng: long
    });

    map.data.addGeoJson(state);
    var featureStyle = {
        strokeColor: '#000000',
        strokeWeight: 4
    }
    map.data.setStyle(featureStyle);
    map.setZoom(zoom);

}

// google.maps.event.addDomListener(window, 'load', initMaps);

$(document).ready(function() {
    $('#state-selector').on('change', function() {
        state = $(this).find("option:selected").val();

        switch(state) {
            case 'Maryland':
                processLocation(39.045753, -76.641273, maryland, 7.5);
                break;
            case 'West Virginia':
                processLocation(38.9, -80.690674, westvirginia, 7)
                break;
            case 'Rhode Island':
                processLocation(41.5800945, -71.4774291, rhodeisland, 9);
                break;
        }
    });
});
