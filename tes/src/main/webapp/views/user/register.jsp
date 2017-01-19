<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>品类编辑</title>
</head>
<body>
<div class="container">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#">用户管理</a></li>
            <li><a href="#" class="active">注册</a></li>
        </ol>
    </div>
    <input type="hidden" value="${type}" id="type">
    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" method="post" action="register.do" data-toggle="validator" role="form">

                <div class="form-group">
                    <label class="control-label col-md-2">姓名</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="profile.name" value="${user.profile.name}"
                               required minlength="2" maxlength="10">
                        <span class="help-block with-errors">用户姓名</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">用户名</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="username" value="${user.username}" maxlength="16"
                               minlength="3" required>
                        <span class="help-block with-errors">输入用户名，使用员工号</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">用户密码</label>
                    <div class="col-md-10">
                        <input class="form-control" type="password" name="password" id="password" value="${user.password}"
                               required maxlength="16" minlength="6">
                        <span class="help-block with-errors">用户密码，6-16位字母、数字、符号组合</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">密码确认</label>
                    <div class="col-md-10">
                        <input class="form-control" type="password" minlength="6" maxlength="16"
                               required data-match="#password" data-error="两次密码输入不一致">
                        <span class="help-block with-errors">再次输入密码</span>
                    </div>
                </div>

                <div class="button pull-right">
                    <button class="btn btn-primary btn-commit">注册</button>
                    <div class="space">

                    </div>
                    <button class="btn btn-warning btn-cancel">取消</button>
                </div>
            </form>
        </div>

    </div>
</div>
<script>

</script>
</body>
</html>
