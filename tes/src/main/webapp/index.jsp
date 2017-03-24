<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Web Socket JavaScript Echo Client</title>
    <script src="/assets/js/jquery-2.2.0.min.js"></script>
    <script src="/assets/js/bootstrap.js"></script>
    <link rel="stylesheet" href="/assets/css/bootstrap.css" type="text/css">
    <script src="http://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
    <script>
        var ws;
        $(function () {
            ws = new WebSocket("ws://localhost:8888/ws/my.ws");
            console.log("初始化");
            ws.onopen = function () {
                console.log("open。。。")
            }

            ws.onmessage = function (event) {
                console.log("event", event);
                var data = event.data;
                console.log("data", data);
            }

            ws.onclose = function (event) {
                console.log("event:", event);
                console.log("close...")
            }
        })



        function sendMessage(msg) {
            ws.send(msg);
        }
    </script>
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
    <form class="form form-horizontal">
        <div class="form-group">
            <label class="control-label col-md-1">请输入</label>
            <div class="col-md-2">
                <input value="" id="info" class="form-control">
            </div>
        </div>
        <button class="btn btn-info" id="btn">发送</button>

    </form>
</div>
<div id="output"></div>
<script>
    $("#btn").on("click", function () {
        sendMessage($("#info").val());
    })
</script>
</body>
</html>