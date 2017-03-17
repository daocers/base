<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>题库编辑</title>
    <%@ include file="../template/header.jsp" %>

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
                <input id="id" type="hidden" name="id" value="${questionBank.id}">



                <div class="form-group">
                    <label class="control-label col-md-2">题库名称</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="name" value="${questionBank.name}" required maxlength="30">
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">描述</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="description" value="${questionBank.description}"
                        maxlength="100">
                        <span class="help-block with-errors">对该题库的使用场景，题目信息和出题部门简要说明</span>
                    </div>
                </div>

                <div class="form-group" <c:if test="${param.type != 'detail'}">hidden</c:if>>
                    <label class="control-label col-md-2">创建时间</label>
                    <div class="col-md-10">
                        <p class="form-control-static"><fmt:formatDate value="${questionBank.createTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate> </p>
                        <span class="help-block with-errors"></span>
                    </div>
                </div>
                <div class="form-group" <c:if test="${param.type != 'detail'}">hidden</c:if>>
                    <label class="control-label col-md-2">创建用户</label>
                    <div class="col-md-10">
                        <p class="form-control-static">${username}</p>
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
