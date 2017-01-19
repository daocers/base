<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>选择人员</title>
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
            <form class="form-horizontal" method="post" action="saveUser.do" data-toggle="validator" role="form">
                <input id="id" type="hidden" name="id" value="${scene.id}">

                <div class="form-group">
                    <label class="control-label col-md-2">部门</label>
                    <select class="form-group">
                        <option value="">会计部</option>
                    </select>
                    <label class="control-label col-md-2">机构</label>
                    <select class="form-group">
                        <option value="">总行</option>
                    </select>
                    <label class="control-label col-md-2">岗位</label>
                    <select class="form-group">
                        <option value="">客户经理</option>
                    </select>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">已选人员</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="authCode" value="${scene.authCode}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>

                <div class="button pull-right">
                    <button class="btn btn-warning btn-commit">上一步</button>
                    <div class="space">

                    </div>
                    <button class="btn btn-primary btn-cancel">下一步</button>
                </div>
            </form>
        </div>

    </div>
</div>
<script>

</script>
</body>
</html>
