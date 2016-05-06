var pos = {lat: -27.477900, lng: -58.819607};//centro de corrientes
var lugar = new google.maps.LatLng(pos.lat, pos.lng);
var map, marker, circle;
var markers = [];
function initialize() {
    var mapProp = {
        center: lugar,
        zoom: 15,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        mapTypeControlOptions: {
            style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
            position: google.maps.ControlPosition.TOP_RIGHT
        }
    };//propiedades
    map = new google.maps.Map(document.getElementById("googleMap"), mapProp);//mapa
}
function addLocation(location) {
    var pinIcon = new google.maps.MarkerImage(img_url + 'Person.png', null, null, null, new google.maps.Size(60, 60));
    marker = new google.maps.Marker({
        position: location,
        map: map,
        icon: pinIcon
    });
    markers.push(marker);
    map.panTo(marker.getPosition());
    drawCircle(marker);
}
function drawCircle(center_marker) {
    var correccion = 350;
    var radioMetros = Number(distanciaKM) * 1000;
    if (radioMetros <= 0) {radioMetros = 1000;}
    // Add circle overlay and bind to marker
    circle = new google.maps.Circle({
        map: map,
        radius: radioMetros + correccion,
        fillColor: '#AA0000'
    });
    circle.bindTo('center', center_marker, 'position');
    console.log(circle.getBounds().toJSON());
}
function loadMarks(pos) {
    var marker, infowindow, content, pinIcon;//variables a utilizar
    //petición de json con los establecimientos, base_url e img_url provienen de main.jsp
    if(distanciaKM <= 0 || distanciaKM == null){distanciaKM=1;}
    $.get(base_url + "/establecimientos/getby", {lat: pos.lat, lng: pos.lng, distanciaKM: distanciaKM}, function (data) {
        //foreach obj"i" in json
        $.each(data, function (i) {
            //pinIcon = new google.maps.MarkerImage(img_url + data[i].categoria.descripcion + '.png', null, null, null, new google.maps.Size(60, 60));
            lugar = new google.maps.LatLng(data[i].latitud, data[i].longitud);
            marker = new google.maps.Marker({position: lugar, title: data[i].nombre});//marcador
            marker.setMap(map);//insertar marcador al mapa
            markers.push(marker);
            content = "<h5>" + data[i].nombre + "</h5><p>" + data[i].regimen + "</p>";//texto al hacer clic
            infowindow = new google.maps.InfoWindow({content: content});//insertar texto
            //evento on click mostrar contenido de marca "para cada marca"
            google.maps.event.addListener(marker, 'click', (function (marker, content, infowindow) {
                return function () {
                    infowindow.setContent(content);
                    infowindow.open(map, marker);
                };
            })(marker, content, infowindow));
        });
    });
}
function deleteAllMarksAndCircle() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    circle.setMap(null);
}
function success(position) {
    pos = {lat: position.coords.latitude, lng: position.coords.longitude};
    addLocation(pos);
    loadMarks(pos);
}
function error() {
    console.log("Unable to retrieve your location");
    addLocation(pos);
    loadMarks(pos);
}
function getLocationAndLoadMarks() {
    if ("geolocation" in navigator) {
        //solicitar coordenada
        navigator.geolocation.getCurrentPosition(success, error);
    } else {
        addLocation(pos);
        loadMarks(pos);
    }
}

$(function () {
    initialize();//inicializar mapa
    getLocationAndLoadMarks();
});
