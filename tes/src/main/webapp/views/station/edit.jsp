<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>品类编辑</title>
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
                <input id="id" type="hidden" name="id" value="${station.id}">
                <div class="form-group">
                    <label class="control-label col-md-2">岗位名称</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="name" value="${station.name}" required>
                        <span class="help-block with-errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">岗位编码</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="code" value="${station.code}">
                        <span class="help-block with-errors">岗位编号，建议使用英文，数字，拼音等形式</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">岗位描述</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="description" value="${station.description}">
                        <span class="help-block with-errors">对该岗位的人员分配，职责规划等信息做简要记录</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">所属机构</label>
                    <div class="col-md-10">
                        <select class="form-control" name="branchId">
                            <option>请选择</option>
                            <c:forEach var="branch" items="${branchList}">
                                <option value="${branch.id}"
                                        <c:if test="${branch.id == station.branchId}">selected</c:if> >${branch.name}</option>
                            </c:forEach>
                        </select>
                        <span class="help-block with-errors">该岗位是某个机构及其上级机构特有，可以不选，表示可以被所有机构使用</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">所属部门</label>
                    <div class="col-md-10">
                        <select class="form-control" name="departmentId">
                            <option>请选择</option>
                            <c:forEach items="${departmentList}" var="dept">
                                <option value="${dept.id}"
                                        <c:if test="${dept.id == station.departmentId}">selected</c:if> >${dept.name}</option>
                            </c:forEach>
                        </select>
                        <span class="help-block with-errors">该岗位归属的部门信息，可以不选，表示可以被所有部门使用</span>
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
