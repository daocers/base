<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>添加菜单</title>
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
    <%@ include file="../template/header.jsp" %>
    <%@ include file="../template/menu-top.jsp" %>
    <%@ include file="../template/menu-left.jsp" %>
    <div class="" style="width:780px; vertical-align: top; display: inline-block">
        <form class="form-horizontal" method="post" action="add.do" data-toggle="validator" role="form">
            <input id="id" type="hidden" name="id" value="${authority.id}">


            <div class="form-group">
                <label class="control-label col-md-2">名称</label>
                <div class="col-md-10">
                    <input class="form-control" type="text" name="name" value="${authority.name}" required
                           maxlength="15">
                    <span class="help-block with-errors">权限名称</span>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-md-2">描述</label>
                <div class="col-md-10">
                    <textarea class="form-control" name="description" rows="5" maxlength="150" required>
                        ${authority.description}
                    </textarea>
                    <span class="help-block with-errors">简要描述该权限作用，使用场景，使用用户</span>
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
<script>

</script>
</body>
</html>
