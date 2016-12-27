<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>设置场次参数</title>
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
                <input id="id" type="hidden" name="id" value="${scene.id}">

                <div class="form-group">
                    <label class="control-label col-md-2">场次识别码</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="authCode" value="${scene.authCode}" required>
                        <span class="help-block with-errors">本场考试的唯一标识，建议使用字母数字组合</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">开始时间</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="beginTime" value="${scene.beginTime}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">branchId</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="branchId" value="${scene.branchId}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">是否允许更换试卷</label>
                    <div class="col-md-10">
                        <div class="checkbox">
                            <label><input type="checkbox" name="changePaper"></label>
                        </div>
                        <%--<input class="form-control" type="checkbox" name="changePaper" value="${scene.changePaper}"--%>
                               <%--required>--%>
                        <span class="help-block with-errors">考试中允许更换一次试卷，成绩更换后的试卷为准</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">考试编码</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="code" value="${scene.code}" required>
                        <span class="help-block with-errors">标识本场考试，便于识别和记忆</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">开场时间</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="createTime" value="${scene.createTime}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">开场人</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="createUserId" value="${scene.createUserId}"
                               required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">考试顺延时间</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="delay" value="${scene.delay}" required>
                        <span class="help-block with-errors">考生顺延时间内开始考试，作答时间不变。超过顺眼时间，作答时间需要减去超出部分。</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">部门信息</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="departmentId" value="${scene.departmentId}"
                               required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">作答时间</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="duration" value="${scene.duration}" required>
                        <span class="help-block with-errors">最大答题时间，超过该时间将自动提交试卷。</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">结束时间</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="endTime" value="${scene.endTime}" required>
                        <span class="help-block with-errors">超过该时间点后，所有的本场考试的试卷将会被自动提交。</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">场次名称</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="name" value="${scene.name}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">试卷策略</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="paperPolicyId" value="${scene.paperPolicyId}"
                               required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">取消原因</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="reason" value="${scene.reason}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">remark</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="remark" value="${scene.remark}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">status</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="status" value="${scene.status}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">更新时间</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="updateTime" value="${scene.updateTime}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">更新用户id</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="updateUserId" value="${scene.updateUserId}"
                               required>
                        <span class="help-block with-errors">提示信息</span>
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
