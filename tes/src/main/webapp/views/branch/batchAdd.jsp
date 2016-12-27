<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/navi.jsp" %>
<%@ include file="../template/header.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理</title>
</head>
<body>
<div class="container">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a> </li>
            <li><a href="#" class="active">商品管理</a> </li>
        </ol>
    </div>

    <div class="row">
        <form class="form-horizontal" method="post" action="batchAdd.do"  enctype="multipart/form-data">
            <input type="file" name="file" class="jfilestyle" data-input="true" data-buttonText="导入文件">
            <button class="btn btn-success">上传</button>
            ？   没有模板文件<a href="downModel.do">点击下载</a>模板
        </form>
    </div>
</div>
<script type="javascript">

</script>
</body>
</html>