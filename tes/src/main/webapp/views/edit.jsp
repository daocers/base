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
            <form class="form-horizontal" method="post" action="save.do">
                <input id="id" type="hidden" name="id" value="${department.id}">

                <div class="form-group">
                    <label class="control-label col-md-2">code</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="code" value="${department.code}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">createTime</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="createTime" value="${department.createTime}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">name</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="name" value="${department.name}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">status</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="status" value="${department.status}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">superiorId</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="superiorId" value="${department.superiorId}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">updateTime</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="updateTime" value="${department.updateTime}">
                    </div>
                </div>

                <div class="button pull-right">
                    <button class="btn btn-default">保存</button>
                    <div class="space">

                    </div>
                    <button class="btn btn-default">取消</button>
                </div>
            </form>
        </div>

    </div>
</div>
<script>
    $(function () {
        var type = $("#type").val();
        if (type == "detail") {
            $("input").attr("readonly", true);
            $("select").attr("readonly", true);
            $("button").attr("disabled", "disabled");
        }
    })
</script>
</body>
</html>
