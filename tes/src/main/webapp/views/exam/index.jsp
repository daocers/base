<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>考试须知</title>
    <%@ include file="../template/header.jsp" %>
</head>
<body>
<div class="jumbotron">
    <div class="container">
        <form action="note.do" method="post" class="form">
            <h3>请输入场次授权码</h3>
            <input type="text" class="form-control col-md-3" name="authCode">
            <button class="btn btn-info">进入考试</button>
        </form>
    </div>
</div>
</body>
</html>
