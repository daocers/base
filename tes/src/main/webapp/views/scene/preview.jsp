<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>场次信息预览</title>
    <style type="text/css">
        /*.preview tbody.table tr th{*/
            /*width: 60px;*/
            /*min-width: 60px;*/
        /*}*/
        /*.preview tbody.table tr td{*/
            /*width: 120px;*/
            /*min-width: 120px;*/
        /*}*/
    </style>
</head>
<body>
<div class="container">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#">场次管理</a></li>
            <li><a href="#" class="active">信息预览</a></li>
        </ol>
    </div>
    <input type="hidden" value="${type}" id="type">
    <div class="row">
        <div class="col-md-8">
            <form action="confirm.do" method="post">
                <input type="hidden" id="id" name="id" value="${scene.id}">
                <table class="table table-responsive table-bordered preview">
                    <thead>
                    场次信息预览
                    </thead>
                    <tbody>
                    <tr>
                        <th class="col-md-2">名称</th>
                        <td class="col-md-4">${scene.name}</td>
                        <th class="col-md-2">编号</th>
                        <td class="col-md-4">${scene.code}</td>
                    </tr>
                    <tr>
                        <th>授权码</th>
                        <td>${scene.authCode}</td>
                        <th></th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>开始时间</th>
                        <td><fmt:formatDate value="${scene.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                        <th>结束时间</th>
                        <td><fmt:formatDate value="${scene.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                    </tr>
                    <tr>
                        <th>顺延时间（分）</th>
                        <td>${scene.delay}</td>
                        <th>考试时长（分）</th>
                        <td>${scene.duration}</td>
                    </tr>
                    <tr>
                        <th>开场用户</th>
                        <td>${scene.createUserId}</td>
                        <th>创建时间</th>
                        <td><fmt:formatDate value="${scene.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </td>
                    </tr>
                    <tr>
                        <th>更新用户</th>
                        <td>${scene.updateUserId}</td>
                        <th>更新时间</th>
                        <td><fmt:formatDate value="${scene.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </td>
                    </tr>
                    <tr>
                        <th>试卷类型</th>
                        <td>${scene.paperType}</td>
                        <th>试卷策略</th>
                        <td>${scene.paperPolicyId}</td>
                    </tr>
                    <tr>
                        <th>策略详情</th>
                        <td colspan="3">${policyInfo}</td>
                    </tr>
                    <tr>
                        <th>所属机构</th>
                        <td>${scene.branchId}</td>
                        <th>所属部门</th>
                        <td>${scene.departmentId}</td>
                    </tr>
                    <tr>
                        <th>岗位</th>
                        <td>${scene.stationId}</td>
                        <th></th>
                        <td></td>
                    </tr>
                    <tr>
                        <th>参考人员</th>
                        <td colspan="3">${scene.joinUser}</td>
                        <!--<th></th>-->
                        <!--<td></td>-->
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    </tbody>


                </table>
                <div class="row">
                    <button type="button" class="btn btn-warning">取消</button>
                    <button type="submit" class="btn btn-success">确定,开场</button>
                </div>
            </form>

        </div>

    </div>
</div>
<script>

</script>
</body>
</html>
