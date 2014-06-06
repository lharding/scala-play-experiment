$(function() {
    var $userpicker = $("#userpicker");

    $userpicker.change(function(event) {
        $(".well").html("<h3>Visualize the loading spinner of your choice...</h3>");
        loadStats($userpicker.val());
    });

    var loadStats = function(username) {
        // make an ajax get request to get the message
        jsRoutes.controllers.NerdStatsController.getStats().ajax({
            headers: {
                "Authorization": "Basic " + btoa(username)
            },
            success: function(data) {
                console.log(data);
                $(".well").html(data);
            }
        });
    };
});