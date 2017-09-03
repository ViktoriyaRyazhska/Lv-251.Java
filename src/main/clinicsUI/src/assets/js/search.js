/**
 * Created by Yana on 25.07.2017.
 */
// $(document).ready(function() {
//     $("#autocomplete").autocomplete({
//         serviceUrl: '/all/doc',
//         paramName: "name",
//         delimiter: ",",
//         transformResult: function (response) {
//              console.log("before"+response.toString());
//             return {
//                 suggestions: $.map($.parseJSON(response), function (item) {
//                     var i=item.firstname+" "+item.lastname+" "+item.specialization;
//                     console.log(i);
//                     return {value:i, data:item.id};
//                 })
//             };
//         },
//         onSelect: function (suggestion) {
//             console.log('You selected: ' + suggestion.value + ', ' + suggestion.data);
//             var id=suggestion.data;
//                 $.ajax({
//                     url:'/searchResult/'+id,
//                     method:'GET',
//                     contentType:'application/json',
//
//                     success:function (result) {
//
//
//                         console.log(result.firstname);
//                         $("#myCarousel").empty();
//                         $("#content").empty();
//                         $("#content").append("<div class='container'><div class='row row-content'><div class='container-fluid'>"+result.firstname+"" +
//                             " "+result.lastname +"</div></div></div>")
//
//
//
//                     }
//
//                 })
//
//             }
//
//
//
//     });
// });


// function allDocs() {
//           $("#autocomplete").autocomplete({
//             serviceUrl: '/all/doc',
//             paramName: "name",
//             delimiter: ",",
//             transformResult: function (response) {
//                 console.log("before"+response.toString());
//                 return {
//                     suggestions: $.map($.parseJSON(response), function (item) {
//                         var i=item.firstname+" "+item.lastname +" "+item.specialisation;
//                         console.log(i);
//                         return {value:i, data:item.id};
//
//                     })
//                 };
//             },
//             onSelect: function (suggestion) {
//                 console.log('You selected: ' + suggestion.value + ', ' + suggestion.data);
//                 var id=suggestion.data;
//                 $.ajax({
//                     url:'/searchResult/'+id,
//                     method:'GET',
//                     contentType:'application/json',
//
//                     success:function (result) {
//                         console.log(result.firstname+""+result.clinicName);
//                         var photo="data:image/jpeg;base64,"+result.photo;
//                         $("#myCarousel").empty();
//                         $("#content").empty();
//                         $("#content").append("<div class='row row-content'> <div class='container-fluid'> <div class='row'>"+
//                             "<div class='col-xs-6 col-md-3'> <a href='#' class='thumbnail'>"+
//                             "<img width=200' height='200' src='"+photo+"'alt='...'></a></div>"+
//                             "<a href='"+"/doctors/"+result.id+"'class='btn-link'><span class='doc-name'>"+result.firstname+" "+result.lastname+"</span></a>"+
//                             "<p>"+"<spring:message code='messages.specialization'/>"+result.specialisation+"<h</p><hr> <p>"+result.clinicName+"</p></div> </div>");
//
//
//
//
//                     }
//
//                 })
//
//             }
//
//
//
//
//     });
// }