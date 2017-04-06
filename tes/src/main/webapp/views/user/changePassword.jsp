<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <%@ include file="../template/header.jsp" %>

</head>
<body>
<div class="container">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#">用户管理</a></li>
            <li><a href="#" class="active">添加用户</a></li>
        </ol>
    </div>
    <input type="hidden" value="${param.type}" id="type">
    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" method="post" data-toggle="validator" role="form">
                <div class="form-group">
                    <label class="control-label col-md-2">旧密码</label>
                    <div class="col-md-10">
                        <input class="form-control" type="password" name="oldPassword" id="oldPassword"
                               required maxlength="16" minlength="6">
                        <span class="help-block with-errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">新密码</label>
                    <div class="col-md-10">
                        <input class="form-control" type="password" name="password" id="password" value="${user.password}"
                               required maxlength="16" minlength="6">
                        <span class="help-block with-errors">用户密码，6-16位字母、数字、符号组合</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">再次输入</label>
                    <div class="col-md-10">
                        <input class="form-control" type="password" minlength="6" maxlength="16"
                               required data-match="#password" data-error="两次密码输入不一致">
                        <span class="help-block with-errors">再次输入密码</span>
                    </div>
                </div>

                <div class="row">
                    <button class="btn btn-info btn-commit" type="button">确认</button>
                    <button class="btn btn-info" type="button">返回</button>
                </div>
            </form>
        </div>

    </div>
</div>
<script>
    $(function () {
        $(".btn-commit").on("click", function () {
            $("form").validator();
            $.ajax({
                url: 'changePassword.do',
                type: 'post',
                data: $("form").serialize(),
                success: function (data) {
                    var res = JSON.parse(data);
                    if(res.code == 0){
                        window.location.href = '/login.do';
                    }else{
                        swal("", res.err, "error");
                    }
                },
                error: function (data) {
                    swal("", "修改密码失败", "error");
                }
            });
            return false;
        });


    })

</script>
</body>
</html>
