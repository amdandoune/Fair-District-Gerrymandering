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