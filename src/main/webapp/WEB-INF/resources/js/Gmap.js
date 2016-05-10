var pos = {lat: -27.477900, lng: -58.819607};//centro de corrientes
var lugar = new google.maps.LatLng(pos.lat, pos.lng);
var map, marker, circle, geocoder;
var markers = [];
var hospitals = [];
var flag_submit = false;
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
    geocoder = new google.maps.Geocoder();
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
    if (radioMetros <= 0) {
        radioMetros = 1000;
    }
    // Add circle overlay and bind to marker
    circle = new google.maps.Circle({
        map: map,
        radius: radioMetros + correccion,
        fillColor: '#AA0000'
    });
    circle.bindTo('center', center_marker, 'position');
    //console.log(circle.getBounds().toJSON());
}
//pablito aca empieza mi codigo de google places
function loadHospitals(pos, radio){
   //configuracion oara traer hospitales elementos cercanos
   var request = {
    location: pos,
    radius: radio,
    types: ['hospital']
  };

  service = new google.maps.places.PlacesService(map);
  service.nearbySearch(request, callback);
};

function callback(results, status) {
  if (status == google.maps.places.PlacesServiceStatus.OK) {
    for (var i = 0; i < results.length; i++) {
      var place = results[i];
      var placeLoc = place.geometry.location;
      var hospital = new google.maps.MarkerImage(img_url + 'Hospital.png', null, null, null, new google.maps.Size(40, 40));
      var marker = new google.maps.Marker({
        map: map,
        position: place.geometry.location,
        icon: hospital
  });
    hospitals[i] = marker;
  
    content = place.name;
  //texto al hacer clic
    infowindow = new google.maps.InfoWindow({content: content});//insertar texto
    //evento on click mostrar contenido de marca "para cada marca"
    google.maps.event.addListener(marker, 'click', (function (marker, content, infowindow) {
        return function () {
            infowindow.setContent(content);
            infowindow.open(map, marker);
        };
    })(marker, content, infowindow));
  
    }
  }
}

function deleteHospitals(){
    for(i=0; i<hospitals.length; i++){
        hospitals[i].setMap(null);
    }
}
//pablito aca termina mi codigo de google places
function loadMarks(pos, flag) {
    var marker, infowindow, content, pinIcon, localidad, departamento;//variables a utilizar
    //petición de json con los establecimientos, base_url e img_url provienen de main.jsp
    if (distanciaKM <= 0 || distanciaKM == null) {
        distanciaKM = 1;
    }
    $.get("https://maps.googleapis.com/maps/api/geocode/json?latlng=" + pos.lat + "," + pos.lng, function (data) {
        /*var test = data.results[0].formatted_address.split(',');
        alert(test[1]+" "+test[2]);
        if (flag) {
            localidad = data.results[0].address_components[3].long_name;
            departamento = data.results[0].address_components[4].long_name;
        } else {*/
            localidad = data.results[0].address_components[2].long_name;
            departamento = data.results[0].address_components[3].long_name;
        //}
        //alert(data.results[0].address_components[2].long_name +'-'+data.results[0].address_components[3].long_name);
        //$.get(base_url + "/establecimientos/getby", {lat: pos.lat, lng: pos.lng, distanciaKM: distanciaKM,regimen:regimen}, function (data) {
        //establecimientos/getByLocDep/{localidad}/{departamento}/{lat}/{lng}/{distanciaKM}/{regimen}
        $.get(base_url + "/establecimientos/getByLocDep/" + localidad + "/" + departamento + "/" + pos.lat + "/" + pos.lng + "/" + distanciaKM + "/" + regimen, {}, function (data) {
            deleteHospitals();
            loadHospitals(pos, distanciaKM * 1000);
            //foreach obj"i" in json
            $.each(data, function (i) {
                //pablito aca recibo la posicion y multiplico por mil porq esta en km
                
                //pinIcon = new google.maps.MarkerImage(img_url + data[i].categoria.descripcion + '.png', null, null, null, new google.maps.Size(60, 60));
                lugar = new google.maps.LatLng(data[i].latitud, data[i].longitud);
                marker = new google.maps.Marker({position: lugar, title: data[i].nombre});//marcador
                marker.setMap(map);//insertar marcador al mapa
                markers.push(marker);
                content = "<h5>" + data[i].nombre + "</h5>\n\
                       <p>Cue-Anexo: " + data[i].cueAnexo + "</p>\n\
                       <p>Regimen: " + data[i].regimen + "</p>\n\
                       <p>Departamento: " + data[i].departamento + "</p>\n\
                       <p>Localidad: " + data[i].localidad + "</p>\n\
                       <p>Jurisdicción: " + data[i].jurisdiccion + "</p>\n\
                       <p>Cursos:" + data[i].cursos + "</p>\n\
                       <a target="+"_blank"+" href=http://localhost:8080/openData/establecimientos/comentatios/" + data[i].cueAnexo + "> Comentar </a>";
                //texto al hacer clic
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
    loadMarks(pos, false);
}
function error() {
    console.log("Unable to retrieve your location");
    addLocation(pos);
    loadMarks(pos, false);
}
function getLocationAndLoadMarks() {
    if (flag_submit) {
        addLocation(pos);
        loadMarks(pos, true);
    } else {
        if ("geolocation" in navigator) {
            //solicitar coordenada
            navigator.geolocation.getCurrentPosition(success, error);
        } else {
            addLocation(pos);
            loadMarks(pos, false);
        }
    }
}

$(function () {
    initialize();//inicializar mapa
    getLocationAndLoadMarks();
    $("#submit").click(function () {
        var addr = $("#address").val();
        $.get("https://maps.googleapis.com/maps/api/geocode/json?address=" + addr, function (data) {
            var lat = data.results[0].geometry.location.lat;
            var long = data.results[0].geometry.location.lng;
            pos = {lat: lat, lng: long};
            flag_submit = true;
            //alert(JSON.stringify(pos));
            deleteAllMarksAndCircle();
            addLocation(pos);
            loadMarks(pos, true);
        });
    });
});
