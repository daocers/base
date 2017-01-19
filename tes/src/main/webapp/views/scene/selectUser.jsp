<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>选择人员</title>
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
            <form class="form-horizontal" method="post" action="saveUser.do" data-toggle="validator" role="form">
                <input id="id" type="hidden" name="id" value="${scene.id}">

                <div class="form-group form-inline">
                    <label class="control-label col-md-2">部门</label>
                    <div class="col-md-2">
                        <select class="form-control">
                            <option value="">请选择</option>
                            <c:forEach var="department" items="${departmentList}">
                                <option value="${department.id}">${department.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label class="control-label col-md-1">机构</label>
                    <div class="col-md-2">
                        <select class="form-control">
                            <option value="">请选择</option>
                            <c:forEach var="branch" items="${branchList}">
                                <option value="${branch.id}">${branch.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label class="control-label col-md-1">岗位</label>
                    <div class="col-md-2">
                        <select class="form-control">
                            <option value="">请选择</option>
                            <c:forEach var="station" items="${stationList}">
                                <option value="${station.id}">${station.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th><input type="checkbox"></th>
                            <th>姓名</th>
                            <th>机构</th>
                            <th>部门</th>
                            <th>岗位</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>雷阳</td>
                            <td>总行</td>
                            <td>会计部</td>
                            <td>客户经理</td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>雷阳</td>
                            <td>总行</td>
                            <td>会计部</td>
                            <td>客户经理</td>
                        </tr>
                    </tbody>
                </table>
                <div class="form-group">
                    <label class="control-label col-md-2">已选人员</label>
                    <div class="col-md-2">
                        <input class="form-control" type="text">
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>

                <div class="button pull-right">
                    <button class="btn btn-warning btn-commit">上一步</button>
                    <div class="space">

                    </div>
                    <button class="btn btn-primary btn-cancel">下一步</button>
                </div>
            </form>
        </div>
        <div class="col-md-4">
            <label class="label label-info">已选人员</label>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>姓名</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>leiyang</td>
                    <td>删除</td>
                </tr>
                </tbody>
            </table>
        </div>

    </div>
</div>
<script>

</script>
</body>
</html>
