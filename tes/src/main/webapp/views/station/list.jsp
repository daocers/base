<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <li><a href="#">首页</a></li>
            <li><a href="#" class="active">岗位管理</a></li>
        </ol>
    </div>
    <div class="row info-search">
        <div class="pull-right form-inline">
            <input type="text" class="form-control" placeholder="输入关键词，例如名称、品牌、序号、供应商等">
            <!--<span class="input-group-btn">-->
            <button class="btn btn-info" type="button">搜索</button>
        </div>
    </div>

    <div class="row param-search">
        <form class="form form-inline" action="list.do">
            <label class="control-label">名称</label>
            <input name="LK_name" value="${param.LK_name}" class="form-control">
            <label class="control-label">编码</label>
            <input name="EQ_code" value="${param.EQ_code}" class="form-control">
            <label class="control-label">id</label>
            <input name="EQ_id_desc_0" value="${param.EQ_id_desc_0}" class="form-control">
            <label class="control-label">创建时间</label>
            <input name="GT_createTime_asc_1" value="${param.GT_createTime_asc_1}" class="form-control time">

            <button class="btn btn-info">查询</button>
        </form>
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
                <th><input type="checkbox" class="selectAll"></th>
                <th>编码</th>
                <th>岗位名称</th>
                <th>岗位描述</th>
                <th>部门</th>
                <th>机构id</th>
                <th>创建时间</th>
                <th>更新时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pi.data}" var="station" varStatus="line">
                <tr>
                    <td><input type="checkbox" objId="${station.id}"></td>
                    <td>${station.code}</td>
                    <td>${station.name}</td>
                    <td>${station.description}</td>
                    <td>${station.departmentId}</td>
                    <td>${station.branchId}</td>
                    <td><fmt:formatDate value="${station.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </td>
                    <td><fmt:formatDate value="${station.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </td>
                    <td>
                        <a href="edit.do?id=${station.id}&type=detail" class="opr">详情</a>
                        <a href="edit.do?id=${station.id}" class="opr">修改</a>
                        <a href="javascript:del(${station.id})" class="opr">删除</a>
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