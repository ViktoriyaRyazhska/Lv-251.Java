/**
 * Created by Admin on 23.08.2017.
 */
(function($) {
    $(document).ready(function() {
        var $chatbox = $('.chatbox'),
            $chatboxTitle = $('.chatbox__title'),
            $chatboxTitleClose = $('.chatbox__title__close'),
            $chatboxCredentials = $('.chatbox__credentials');
        $chatboxTitle.on('click', function(e) {
            console.log("in chat")
            connect();
            $chatbox.toggleClass('chatbox--tray');

            $chatbox.removeClass('chatbox--empty');
        });
        $chatboxTitleClose.on('click', function(e) {

            $chatbox.addClass('chatbox--closed');
        });
        $chatbox.on('transitionend', function() {
            if ($chatbox.hasClass('chatbox--closed')) disconnect();
        });
        $chatboxCredentials.on('submit', function(e) {
            e.preventDefault();
            $chatbox.removeClass('chatbox--empty');
        });
    });
})(jQuery);


var stompClient = null;

function setConnected(connected) {
    document.getElementById('response').innerHTML = '';
}

function connect() {

    if(stompClient==null){
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        console.log("hell")
        stompClient.subscribe('/topic/messages', function (messageOutput) {
            showMessageOutput(JSON.parse(messageOutput.body));
        });

    });}
}

function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {

    var text = document.getElementById('text').value;
    stompClient.send("/app/chat", {},
        JSON.stringify({  'text':text}));
    $("textarea").val("")
}

function showMessageOutput(messageOutput) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(messageOutput.from + ": "+
       " (" + messageOutput.date + ")" + messageOutput.text ));
    response.appendChild(p);
}
