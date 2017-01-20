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
    <input type="hidden" value="${param.type}" id="type">
    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" method="post" action="save.do" data-toggle="validator" role="form">
                <input id="id" type="hidden" name="id" value="${branch.id}">

                <div class="form-group">
                    <label class="control-label col-md-2">机构地址</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="address" value="${branch.address}" maxlength="100">
                        <span class="help-block with-errors">请录入具体地址</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">机构编码</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="code" value="${branch.code}" required>
                        <span class="help-block with-errors">如果已经赋值，请谨慎修改</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">创建时间</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="createTime" value="${branch.createTime}" required>
                        <span class="help-block">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">机构级别</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="level" value="${branch.level}" required>
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">机构名称</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="name" value="${branch.name}" required>
                        <span class="help-block">分行、支行、网点或者分理处的具体名称</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">状态</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="status" value="${branch.status}" required>
                        <span class="help-block"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">superiorId</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="superiorId" value="${branch.superiorId}" required>
                        <span class="help-block">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">updateTime</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="updateTime" value="${branch.updateTime}" required>
                        <span class="help-block">提示信息</span>
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
<script type="text/javascript">

</script>
</body>
</html>
