/**
 * Created by Yana on 25.07.2017.
 */
$(document).ready(function() {
    $("#autocomplete").autocomplete({
        serviceUrl: '/all/doc',
        paramName: "name",
        delimiter: ",",
        transformResult: function (response) {
console.log("before");
            return {

                suggestions: $.map($.parseJSON(response), function (item) {
                    console.log(item);
              var i=item.firstname+" "+item.lastname+" "+item.specialization.name;
                    return {value:i, data:item.id};

                })


            };


        },
        onSelect: function (suggestion) {
            console.log('You selected: ' + suggestion.value + ', ' + suggestion.data);
            var id=suggestion.data;
                $.ajax({
                    url:'/searchResult/'+id,
                    method:'GET',
                    contentType:'application/json',

                    success:function (result) {


                        console.log(result.firstname);
                        $("#content").empty();
                        $("#content").append("<div class='row row-content'><div class='container-fluid'>"+result.firstname+"" +
                            " "+result.lastname +"</div></div>")



                    }

                })

            }



    });
});
