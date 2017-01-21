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
            <li><a href="#" class="active">商品管理</a></li>
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
                <th><input type="checkbox" class="selectAll"></th>
                <th>场次名称</th>
                <th>场次编码</th>
                <th>场次识别码</th>
                <th>开场时间</th>
                <th>顺延时间</th>
                <th>考试时长</th>
                <th>结束时间</th>


                <th>是否允许换卷</th>
                <th>试卷策略</th>

                <th>创建时间</th>
                <th>开场用户</th>

                <th>机构</th>
                <th>部门</th>


                <th>变更原因</th>
                <th>备注</th>
                <th>状态</th>
                <th>更新时间</th>
                <th>更新用户</th>
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
                    <td><fmt:formatDate value="${scene.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                    <td>${scene.delay}</td>
                    <td>${scene.duration}</td>
                    <td><fmt:formatDate value="${scene.endTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>

                    <td>${scene.changePaper}</td>
                    <td>${scene.paperPolicyId}</td>
                    <td><fmt:formatDate value="${scene.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>

                    <td>${scene.createUserId}</td>

                    <td>${scene.branchId}</td>
                    <td>${scene.departmentId}</td>


                    <td>${scene.reason}</td>
                    <td>${scene.remark}</td>
                    <td>${scene.status}</td>
                    <td><fmt:formatDate value="${scene.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>

                    <td>${scene.updateUserId}</td>
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

        </div>
    </div>
</div>
<script type="javascript">

</script>
</body>
</html>