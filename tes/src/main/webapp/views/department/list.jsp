<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>部门管理</title>
    <%@ include file="../template/header.jsp" %>

</head>
<body>
<div class="container">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#" class="active">部门管理</a></li>
        </ol>
    </div>
    <div class="row info-search">
        <div class="pull-right form-inline">
            <input type="text" class="form-control" placeholder="输入关键词，例如名称、品牌、序号、供应商等">
            <!--<span class="input-group-btn">-->
            <button class="btn btn-info" type="button">搜索</button>
        </div>
    </div>

    <div class="row pre-table">
        <div class="pull-right">
            <jsp:include page="../template/page-nav.jsp"/>
        </div>
    </div>
    <div class="row table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th class="col-md-1"><input type="checkbox" class="selectAll"></th>
                <th class="col-md-2">名称</th>
                <th class="col-md-1">机构编码</th>
                <th class="col-md-2">上级部门</th>
                <th class="col-md-1">状态</th>
                <th class="col-md-2">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pi.data}" var="department" varStatus="line">
                <tr>
                    <td><input type="checkbox" objId="${department.id}"></td>
                    <td>${department.name}</td>
                    <td>${department.code}</td>
                    <%--<td><fmt:formatDate value="${department.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </td>--%>
                    <td>${departmentInfo.get(department.superiorId)}</td>
                    <td>${department.status == 0? "启用":"禁用"}</td>
                <%--<td><fmt:formatDate value="${department.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </td>--%>
                    <td>
                        <a href="edit.do?id=${department.id}&type=detail" class="opr">详情</a>
                        <a href="edit.do?id=${department.id}" class="opr">修改</a>
                        <a href="javascript:del(${department.id})" class="opr">删除</a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

    <div class="row after-table">
        <div class="pull-left form-inline">
            <select class="form-control show-count">
                <option value="10" <c:if test="${ pi.showCount == 10 }">selected</c:if>>10</option>
                <option value="25" <c:if test="${ pi.showCount == 25}">selected</c:if>>25</option>
                <option value="50" <c:if test="${ pi.showCount == 50}">selected</c:if>>50</option>
            </select>
            <div>条/页</div>

        </div>
        <div class="pull-right">

        </div>
    </div>
</div>
<script type="javascript">

</script>
</body>
</html>