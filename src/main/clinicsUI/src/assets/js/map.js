/**
 * Created by Taras on 27.07.2017.
 */
var clinicsLatLang;

function initMap() {

    var markerCluster;
    var map;
    var bounds = new google.maps.LatLngBounds();

    var infowindow = new google.maps.InfoWindow();


    $.ajax({
        type: "GET",

        url: window.location.protocol + "//" + window.location.host + "/rest/clinics/map/all",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            clinicsLatLang = result;

            var markers = new Array();
            for (i = 0; i < result.length; i++) {
                latitude = parseFloat(result[i].lat);
                longitude = parseFloat(result[i].lng);
                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(latitude, longitude)
                });

                // google.maps.event.addListener(marker, 'click', function () {
                //     infowindow.setContent('<p>Attribution:</p>');
                //     infowindow.open(map, this.marker);
                // });



                google.maps.event.addListener(marker, 'click', (function(marker, i) {
                    return function() {

                        showInfo(clinicsLatLang[i].id, map, marker, infowindow);

                    }
                })(marker, i));

                markers.push(marker);
                bounds.extend(marker.position);


            }
            map = new google.maps.Map(document.getElementById('map'));
            map.fitBounds(bounds);

            markerCluster = new MarkerClusterer(map, markers,
                {imagePath: 'https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m'});
        },
        error: function (response) {
            // alert('error');
        }
    });
}

function showInfo(id, map, marker, infowindow) {

    $.ajax({
        type: "GET",

        url: window.location.protocol + "//" + window.location.host + "/rest/clinics/" + id,
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            infowindow.setContent('<p>' + result.clinic_name + '</p>');
            infowindow.open(map, marker);
            return result;
        },
        error: function (response) {
        }
    });

}



