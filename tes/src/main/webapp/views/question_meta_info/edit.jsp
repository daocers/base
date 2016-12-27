<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>品类编辑</title>
    <style>
        .prop{
            display: inline-block;
        }
    </style>
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
                <input id="id" type="hidden" name="id" value="${questionmetainfo.id}">
                <div class="form-group">
                    <label class="control-label col-md-2">题型名称</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="name" value="${questionmetainfo.name}" required>
                        <span class="help-block with-errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">题型编码</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="code" value="${questionmetainfo.code}" required>
                        <span class="help-block with-errors">题型编码，建议使用英文，拼音或者首字母简称等</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">题型描述</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="description"
                               value="${questionmetainfo.description}" required>
                        <span class="help-block with-errors">简要描述该题型，作答方式，得分规则等</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">状态</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="status" value="${questionmetainfo.status}"
                               required>
                        <span class="help-block with-errors">设置禁用/启用</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">选择属性</label>
                    <div class="col-md-10">
                        <div class="prop-container">
                            <c:forEach items="${propertyList}" var="property">
                                <ul class="list-group col-md-3">
                                    <li class="list-group-item list-group-item-success">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" name="propertyId" value="${property.id}"
                                                    <c:if test="${propertyIdList.contains( property.id)}"> checked</c:if> >
                                                    ${property.name}
                                            </label>
                                        </div>
                                    </li>
                                    <c:forEach items="${property.propertyItemList}" var="item">
                                        <li class="list-group-item list-group-item-info">${item.code} - ${item.name}</li>
                                    </c:forEach>
                                </ul>
                            </c:forEach>
                        </div>
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
