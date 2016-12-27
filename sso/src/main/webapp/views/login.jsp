<%@page language="java" pageEncoding="utf-8" %>
<html>
<body>
<h3>登录</h3>
<hr/>

<form action="signIn" method="post">
    <input type="hidden" name="url" value="${url}">
    <input type="text" name="username">
    <input type="text" name="password">
    <button type="submit">提交</button>
</form>
</body>
</html>
