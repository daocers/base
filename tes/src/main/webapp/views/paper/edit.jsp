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
                <input id="id" type="hidden" name="id" value="${paper.id}">

                <div class="form-group">
                    <label class="control-label col-md-2">answerFlag</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="answerFlag" value="${paper.answerFlag}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">beginTime</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="beginTime" value="${paper.beginTime}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">endTime</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="endTime" value="${paper.endTime}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">mark</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="mark" value="${paper.mark}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">sceneId</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="sceneId" value="${paper.sceneId}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">status</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="status" value="${paper.status}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">userId</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="userId" value="${paper.userId}" required>
                        <span class="help-block with-errors">提示信息</span>
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
