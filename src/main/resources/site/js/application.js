$(function () {
    "use strict";

    var socket = $.atmosphere;
    var request = { url: 'http://localhost:8040/testws',
                    contentType : "application/json",
                    logLevel : 'debug',
                    transport : 'websocket' ,
                    trackMessageLength : true,
                    fallbackTransport: 'long-polling'};

    request.onOpen = function(response) {
        console.log('Atmosphere connected using ' + response.transport);
    };

    request.onMessage = function (response) {
        var message = response.responseBody;
        console.log("onMessage:",message);
    };

    request.onClose = function(response) {
        console.log("onClose");
    }

    request.onError = function(response) {
        console.log("onError");
    };

    var subSocket = socket.subscribe(request);

    setInterval(function(){
    	subSocket.push("PING");
    }, 3000);


});

