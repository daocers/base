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
            <li><a href="#">品类管理</a></li>
            <li><a href="#" class="active">品类编辑</a></li>
        </ol>
    </div>
    <input type="hidden" value="${type}" id="type">
    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" method="post" action="save.do" data-toggle="validator" role="form">
                <input id="id" type="hidden" name="id" value="${questionbank.id}">



                <div class="form-group">
                    <label class="control-label col-md-2">题库名称</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="name" value="${questionbank.name}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">描述</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="description" value="${questionbank.description}"
                        >
                        <span class="help-block with-errors">对该题库的使用场景，题目信息和出题部门简要说明</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">创建时间</label>
                    <div class="col-md-10">
                        <p class="form-control-static">${questionbank.createTime}</p>
                        <span class="help-block with-errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">创建用户</label>
                    <div class="col-md-10">
                        <p class="form-control-static">${questionbank.createUserId}</p>
                        <span class="help-block with-errors"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">更新时间</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="updateTime" value="${questionbank.updateTime}"
                        >
                        <span class="help-block with-errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">更新用户</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="updateUserId" value="${questionbank.updateUserId}"
                        >
                        <span class="help-block with-errors"></span>
                    </div>
                </div>
                <div class="button pull-right">
                    <button class="btn btn-primary btn-commit">保存</button>
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
