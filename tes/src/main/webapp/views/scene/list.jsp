<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>场次管理</title>
</head>
<body>
<%@ include file="../template/header.jsp" %>
<%@ include file="../template/menu-top.jsp"%>
<%@ include file="../template/menu-left.jsp"%>
<div class="" style="width:780px; vertical-align: top; display: inline-block">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#" class="active">场次管理</a></li>
        </ol>
    </div>

    <div class="row table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><input type="checkbox" class="selectAll"></th>
                <th>考试名称</th>
                <th>场次编码</th>
                <th>识别码</th>
                <th>开场时间</th>
                <th>顺延时间</th>
                <th>考试时长</th>
                <th>结束时间</th>
                <th>允许换卷</th>
                <th>创建时间</th>
                <th>开场用户</th>
                <th>机构</th>
                <th>部门</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pi.data}" var="scene" varStatus="line">
                <tr>
                    <td><input type="checkbox" objId="${scene.id}"></td>
                    <td><a href="edit.do?type=detail&id=${scene.id}">${scene.name}</a></td>
                    <td>${scene.code}</td>
                    <td>${scene.authCode}</td>
                    <td><fmt:formatDate value="${scene.beginTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
                    <td>${scene.delay}</td>
                    <td>${scene.duration}</td>
                    <td><fmt:formatDate value="${scene.endTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
                    <td>${scene.changePaper == 0 ? "是":"否"}</td>
                    <td><fmt:formatDate value="${scene.createTime}" pattern="yyyy-MM-dd HH:mm"></fmt:formatDate></td>
                    <td>${scene.createUser == null ? "" : scene.createUser.username}</td>
                    <td>${scene.branch == null ? "" : scene.branch.name}</td>
                    <td>${scene.department == null ? "" : scene.department.name}</td>
                    <td>
                        <c:if test="${scene.status == 0}">
                            已开场
                        </c:if>
                        <c:if test="${scene.status == 1}">
                            已封场
                        </c:if>
                        <c:if test="${scene.status == 2}">
                            已作废
                        </c:if>
                        <c:if test="${scene.status == 3}">
                            已取消
                        </c:if>
                    </td>
                    <td>
                        <a href="edit.do?id=${scene.id}&type=detail" class="opr">详情</a>
                        <a href="edit.do?id=${scene.id}" class="opr">修改</a>
                        <a href="javascript:del(${scene.id})" class="opr">删除</a>
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
            <%--<nav>--%>
                <%--<ul class="pagination">--%>
                    <%--<li><a class="info">共12条记录，3页</a> </li>--%>
                    <%--<li><a href="#">首页</a> </li>--%>
                    <%--<li class="disabled"><a href="#">前一页</a> </li>--%>
                    <%--<li><a href="#">1</a> </li>--%>
                    <%--<li><a href="#">2</a> </li>--%>
                    <%--<li class="active"><a href="#">3</a> </li>--%>
                    <%--<li><a href="#">4</a> </li>--%>
                    <%--<li><a href="#">5</a> </li>--%>
                    <%--<li><a href="#">...</a> </li>--%>
                    <%--<li><a href="#">后一页</a> </li>--%>
                    <%--<li><a href="#">尾页</a> </li>--%>
                <%--</ul>--%>
            <%--</nav>--%>
            <jsp:include page="../template/page-nav.jsp"/>
        </div>
    </div>
</div>
<script type="javascript">

</script>
</body>
</html>