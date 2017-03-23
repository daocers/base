<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Web Socket JavaScript Echo Client</title>
    <script src="http://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
    <script language="javascript" type="text/javascript">
        var echo_websocket;
        function init() {
            output = document.getElementById("output");
        }
        function send_echo() {
//            echo_websocket = new SockJS("http://localhost:8888/ws/my") ;   //初始化 websocket
            echo_websocket = new WebSocket("ws://localhost:8888/ws/my.ws");
            echo_websocket.onopen = function () {
                console.log('Info: connection opened.');
                echo_websocket.send("abcabc");

            };

            echo_websocket.onmessage = function (event) {
                console.log('Received: ' + event.data); //处理服务端返回消息
            };

            echo_websocket.onclose = function (event) {
                console.log('Info: connection closed.');
                console.log(event);
            };

        }

        function doSend(message) {
            echo_websocket.send(message);
            writeToScreen("Sent message: " + message);
        }
        function writeToScreen(message) {
            var pre = document.createElement("p");
            pre.style.wordWrap = "break-word";
            pre.innerHTML = message;
            output.appendChild(pre);
        }
        window.addEventListener("load", init, false);
    </script>
</head>
<body>
<h3>Echo Server</h3>
<div style="text-align: left;">
    <form action="">
        <input onclick="send_echo()" value="send websocket request" type="button">
        <input id="textID" name="message" value="Hello world, Web Sockets" type="text">
        <br>
    </form>
</div>
<div id="output"></div>
</body>
</html>