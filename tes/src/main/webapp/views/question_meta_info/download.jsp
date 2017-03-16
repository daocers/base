<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>试题模板下载</title>
    <%@ include file="../template/header.jsp" %>
    <style>
    </style>
</head>
<body>
<div class="container">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a> </li>
            <li><a href="#" class="active">试题模板下载</a> </li>
        </ol>
    </div>

    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" method="post" action="downModel.do" data-toggle="validator" role="form">
                <div class="form-group">
                    <label class="control-label col-md-1">题型</label>
                    <div class="col-md-10">
                        <select class="form-control" name="metaInfoId" required>
                            <option value="">请选择</option>
                            <c:forEach var="metaInfo" items="${metaInfoList}">
                                <option value="${metaInfo.id}">${metaInfo.name}</option>
                            </c:forEach>
                        </select>
                        <span class="help-block with-errors">选择需要下载的试题模板的题型</span>
                    </div>
                </div>


                <div class="form-group">
                    <button class="btn btn-info">下载</button>
                </div>

            </form>
        </div>

    </div>
</div>
<script>
</script>
</body>
</html>