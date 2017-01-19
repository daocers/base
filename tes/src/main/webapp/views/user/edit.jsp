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
            <li><a href="#">用户管理</a></li>
            <li><a href="#" class="active">添加用户</a></li>
        </ol>
    </div>
    <input type="hidden" value="${type}" id="type">
    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" method="post" action="save.do" data-toggle="validator" role="form">
                <input id="id" type="hidden" name="id" value="${user.id}">

                <div class="form-group">
                    <label class="control-label col-md-2">用户名</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="username" value="${user.username}" maxlength="16"
                               minlength="3" required readonly>
                        <span class="help-block">输入用户名，使用员工号</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">姓名</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="profile.name" value="${user.profile.name}"
                               required minlength="2" maxlength="10">
                        <span class="help-block with-errors">用户姓名</span>
                    </div>
                </div>
                <div class="form-group hidden">
                    <label class="control-label col-md-2">profile id</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="profile.id" value="${user.profile.id}" required readonly>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">身份证号码</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="profile.idNo" value="${user.profile.idNo}">
                        <span class="help-block with-errors"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">所属机构</label>
                    <div class="col-md-10">
                        <select class="form-control" name="branchId" required style="display: inline-block;">
                            <option>请选择</option>
                            <c:forEach items="${branchList}" var="branch">
                                <option value="${branch.id}" <c:if
                                        test="${user.branchId == branch.id}"> selected</c:if> >${branch.name}</option>
                            </c:forEach>
                        </select>
                        ？缺少机构 去<a href="../branch/edit.do">添加</a>
                        <span class="help-block with-errors">用于所在分行、支行、网点或者分理处</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">部门</label>
                    <div class="col-md-10">
                        <select class="form-control" name="departmentId" required style="display: inline-block;">
                            <option>请选择</option>
                            <c:forEach items="${departmentList}" var="department">
                                <option value="${department.id}" <c:if
                                        test="${user.departmentId == department.id}"> selected</c:if> >${department.name}</option>
                            </c:forEach>
                        </select>
                        ？缺少部门 去<a href="../department/edit.do">添加</a>
                        <span class="help-block">用户所在部门</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">岗位信息</label>
                    <div class="col-md-10">
                        <select class="form-control" name="stationId" required style="display: inline-block;">
                            <option>请选择</option>
                            <c:forEach items="${stationList}" var="station">
                                <option value="${station.id}" <c:if
                                        test="${user.stationId == station.id}"> selected</c:if> >${station.name}</option>
                            </c:forEach>
                        </select>
                        ？缺少岗位 去<a href="../station/edit.do">添加</a>
                        <span class="help-block">用户当前岗位</span>
                    </div>
                </div>
                <%--<div class="form-group">--%>
                    <%--<label class="control-label col-md-2">状态</label>--%>
                    <%--<div class="col-md-10">--%>
                        <%--<input class="form-control" type="text" name="status" value="${user.status}" required>--%>
                        <%--<span class="help-block"></span>--%>
                    <%--</div>--%>
                <%--</div>--%>



                <div class="form-group">
                    <label class="control-label col-md-2">考试状态</label>
                    <div class="col-md-10">
                        <select class="form-control" name="profile.examStatus">
                            <option value="0"
                            <c:if test="${user.profile.examStatus == 0}"> selected </c:if>
                            >正常</option>
                            <option value="1"  <c:if test="${user.profile.examStatus == 1}"> selected </c:if>>缺考</option>
                            <option value="2"  <c:if test="${user.profile.examStatus == 2}"> selected </c:if>>作弊</option>
                        </select>
                        <%--<input class="form-control" type="text" name="examStatus" value="${profile.examStatus}"--%>
                               <%--required>--%>
                        <span class="help-block with-errors">作弊状态下，柜员将在指定时间内不能使用本系统进行练习和考试</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">考试状态更新时间</label>
                    <div class="col-md-10">
                        <input class="form-control time" type="text" name="profile.examStatusUpdate"
                               value="<fmt:formatDate value="${user.profile.examStatusUpdate}" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled
                        >
                        <span class="help-block with-errors"></span>
                    </div>
                </div>

                <div class="form-group hidden">
                    <label class="control-label col-md-2">注册时间</label>
                    <div class="col-md-10">
                        <input class="form-control time" type="text" name="profile.registTime" value="${user.profile.registTime}"
                               required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">柜员类型</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="profile.type" value="${user.profile.type}" required>
                        <span class="help-block with-errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">柜员等级</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="profile.level" value="${user.profile.level}" required>
                        <span class="help-block with-errors"></span>
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
