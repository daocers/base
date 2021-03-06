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
            <li><a href="#">首页</a> </li>
            <li><a href="#" class="active">商品管理</a> </li>
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
                <th><input type="checkbox" class="selectAll"> </th>
				                    <th >authCode</th>
				                    <th >beginTime</th>
				                    <th >branchId</th>
				                    <th >changePaper</th>
				                    <th >code</th>
				                    <th >createTime</th>
				                    <th >createUserId</th>
				                    <th >delay</th>
				                    <th >departmentId</th>
				                    <th >duration</th>
				                    <th >endTime</th>
				                    <th >name</th>
				                    <th >paperPolicyId</th>
				                    <th >reason</th>
				                    <th >remark</th>
				                    <th >status</th>
				                    <th >updateTime</th>
				                    <th >updateUserId</th>
				                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pi.data}" var="scene" varStatus="line">
                <tr>
                    <td><input type="checkbox" objId="${scene.id}"></td>
					                        <td>${scene.authCode}</td>
					                        <td>${scene.beginTime}</td>
					                        <td>${scene.branchId}</td>
					                        <td>${scene.changePaper}</td>
					                        <td>${scene.code}</td>
					                        <td>${scene.createTime}</td>
					                        <td>${scene.createUserId}</td>
					                        <td>${scene.delay}</td>
					                        <td>${scene.departmentId}</td>
					                        <td>${scene.duration}</td>
					                        <td>${scene.endTime}</td>
					                        <td>${scene.name}</td>
					                        <td>${scene.paperPolicyId}</td>
					                        <td>${scene.reason}</td>
					                        <td>${scene.remark}</td>
					                        <td>${scene.status}</td>
					                        <td>${scene.updateTime}</td>
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
                        <select class="form-control show-count" >
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