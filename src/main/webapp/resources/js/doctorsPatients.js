/**
 * Created by Taras on 01.08.2017.
 */

var patients;


function init() {
    loadData();

    $('#search').on('input', function() {
        clearAll()
        var s = document.getElementById("search");
        var val = s.value.toString().toLowerCase();
        for (var i = 0; i < patients.length; i++) {
            var item = patients[i];
            if(item.fullName.toString().toLowerCase().includes(val)){
                addItem2(item.id, item.fullName);
            }
        }
    });
}



function loadData() {
    // var searchValue = document.getElementById("search").value;
    var searchValue = "";


    $.ajax({
        type: "GET",
        data: searchValue,
        url: window.location.protocol + "//" + window.location.host + "/rest/doctor/patients/",
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        success: function (result) {
            patients = result;
            for (var i = 0; i < patients.length; i++) {

                var item = patients[i];
                addItem2(item.id, item.fullName);

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


function addItem2(id, text){
    if(text==null){
        text = "no name";
    }
    var dataItems = {
        "Name" : text,
        "id" : id};

    $('#template').tmpl(dataItems).appendTo('#dynamic-list');
}

function clearAll(){
    var tbody = document.getElementById("dynamic-list");
    while (tbody.firstChild) {
        tbody.removeChild(tbody.firstChild);
    }
}

