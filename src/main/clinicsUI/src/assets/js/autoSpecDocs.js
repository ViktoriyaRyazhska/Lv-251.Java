/**
 * Created by Admin on 02.08.2017.
 */


    $("#autocomplete-spec").autocomplete({
        serviceUrl:'/rest/autocomplete/specializations/byName',
        paramName: "name",
        transformResult: function (response) {
            console.log("before doc by spec" );
            return {

                suggestions: $.map($.parseJSON(response), function (item) {
                    var i = item.name;
                    console.log(i);
                    return {value: i, data: item.name};
                })
            };
        } });



$("#autocomplete-user").autocomplete({
    serviceUrl: '/moderator/users',
    paramName: "name",
    transformResult: function (response) {
        console.log("before user " );
        return {

            suggestions: $.map($.parseJSON(response), function (item) {
                var i = item.name;
                console.log(i);
                return {value: i, data: item.name};
            })
        };
    } });
