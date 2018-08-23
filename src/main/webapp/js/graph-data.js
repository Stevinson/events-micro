$(document).ready(function () {
    var text = getEvents();
});

function getEvents(){
    $.get("./api/events")
      .done(function(data) {
          if(data.length > 0) {
            data.forEach(function(element, index) {
              data[index] = AntiXSS.sanitizeInput(element)
            });
            $('#databaseNames').html("Database contents: " + JSON.stringify(data));
          }
    });
}