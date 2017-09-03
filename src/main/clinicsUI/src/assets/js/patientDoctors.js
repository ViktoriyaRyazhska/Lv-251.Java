/**
 * Created by Taras on 03.08.2017.
 */

var doctors;


function init() {
    loadData();

    $('#search').on('input', function() {
        clearAll()
        var s = document.getElementById("search");
        var val = s.value.toString().toLowerCase();
        for (var i = 0; i < doctors.length; i++) {
            var item = doctors[i];
            if(item.fullName.toString().toLowerCase().includes(val)){
                addItem(item.id, item.fullName);
            }
        }
    });
}



function loadData() {
    var searchValue = document.getElementById("search").value;

    $.ajax({
        type: "GET",
        data: searchValue,
        url: window.location.protocol + "//" + window.location.host + "/rest/patient/doctors/",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            doctors = result;
            for (var i = 0; i < doctors.length; i++) {

                var item = doctors[i];
                addItem(item.id, item.fullName);

            }

        },
        error: function (response) {

        }
    });
}

function addItem(id, text){
    var tbody = document.getElementById("dynamic-list");
    var tr = document.createElement("tr");
    // tr.setAttribute('id',id.toString());
    var td = document.createElement("td");
    td.innerHTML = text;
    tr.appendChild(td);
    tbody.appendChild(tr);
}


function clearAll(){
    var tbody = document.getElementById("dynamic-list");
    while (tbody.firstChild) {
        tbody.removeChild(tbody.firstChild);
    }
}

