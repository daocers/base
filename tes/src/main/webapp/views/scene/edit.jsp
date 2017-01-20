<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>设置场次参数</title>
    <style>
        .switch-btn {
            position: relative;
            display: block;
            vertical-align: top;
            width: 80px;
            height: 30px;
            border-radius: 5px;
            cursor: pointer;
        }
        .checked-switch {
            position: absolute;
            top: 0;
            left: 0;
            opacity: 0;
        }
        .text-switch {
            background-color: #ed5b49;
            border: 1px solid #d2402e;
            border-radius: inherit;
            color: #fff;
            display: block;
            font-size: 15px;
            height: inherit;
            position: relative;
            text-transform: uppercase;
        }
        .text-switch:before,
        .text-switch:after {
            position: absolute;
            top: 50%;
            margin-top: -.5em;
            line-height: 1;
            -webkit-transition: inherit;
            -moz-transition: inherit;
            -o-transition: inherit;
            transition: inherit;
        }
        .text-switch:before {
            content: attr(data-no);
            right: 11px;
        }
        .text-switch:after {
            content: attr(data-yes);
            left: 11px;
            color: #FFFFFF;
            opacity: 0;
        }
        .checked-switch:checked ~ .text-switch {
            background-color: #285eaf;
            border: 1px solid #1d53af;
        }
        .checked-switch:checked ~ .text-switch:before {
            opacity: 0;
        }
        .checked-switch:checked ~ .text-switch:after {
            opacity: 1;
        }
        .toggle-btn {
            background: linear-gradient(#eee, #fafafa);
            border-radius: 5px;
            height: 28px;
            left: 1px;
            position: absolute;
            top: 1px;
            width: 28px;
        }
        .toggle-btn::before {
            color: #aaaaaa; content: "|||";
            display: inline-block;
            font-size: 12px;
            letter-spacing: -2px;
            padding: 4px 0;
            vertical-align: middle;
        }
        .checked-switch:checked ~ .toggle-btn {
            left: 51px;
        }
        .text-switch, .toggle-btn {
            transition: All 0.3s ease;
            -webkit-transition: All 0.3s ease;
            -moz-transition: All 0.3s ease;
            -o-transition: All 0.3s ease;
        }
    </style>
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
    <input type="hidden" value="${param.type}" id="type">
    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" method="post" action="save.do" data-toggle="validator" role="form">
                <%--<input id="id" type="hidden" name="id" value="${scene.id}">--%>
                <div class="form-group">
                    <label class="control-label col-md-2">场次名称</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="name" value="${scene.name}" required>
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>
                    <div class="form-group">
                        <label class="control-label col-md-2">场次编码</label>
                        <div class="col-md-10">
                            <input class="form-control" type="text" name="code" value="${scene.code}" required>
                            <span class="help-block with-errors"></span>
                        </div>
                    </div>
                <div class="form-group">
                    <label class="control-label col-md-2">场次授权码</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="authCode" value="${scene.authCode}" required>
                        <span class="help-block with-errors">本场考试的唯一标识，建议使用字母数字组合</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">开始时间</label>
                    <div class="col-md-10">
                        <input class="form-control time" type="text" name="beginTime"
                               value="<fmt:formatDate value="${scene.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> ">
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>


                <div class="form-group">
                    <label class="control-label col-md-2">考试顺延时间</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="delay" value="${scene.delay}" required>
                        <span class="help-block with-errors">考生顺延时间内开始考试，作答时间不变。超过顺延时间，作答时间需要减去超出部分。</span>
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
                        <input class="form-control time" type="text" name="endTime"
                               value="<fmt:formatDate value="${scene.endTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> " disabled>
                        <span class="help-block with-errors">超过该时间点后，所有的本场考试的试卷将会被自动提交。(根据开始时间，作答时长，顺延时间计算，不能手动输入)</span>
                    </div>
                </div>


                <%--<div class="form-group">--%>
                    <%--<label class="control-label col-md-2">机构信息</label>--%>
                    <%--<div class="col-md-10">--%>
                        <%--<input class="form-control" type="text" name="branchId" value="${scene.branchId}" required>--%>
                        <%--<span class="help-block with-errors">场次所属的机构信息</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <label class="control-label col-md-2">是否允许更换试卷</label>
                    <%--<div class="col-md-10 switch" data-on="primary" data-off="danger" tabindex="0">--%>
                        <%--<input type="checkbox" name="chanagePaper"--%>
                               <%--value="${scene.changePaper}" onclick="this.value=this.checked?0:1">--%>
                        <%--<span class="help-block with-errors">考试中允许更换一次试卷，成绩更换后的试卷为准</span>--%>
                    <%--</div>--%>
                    <div class="col-md-3">
                        <div class="switch" style="height:30px;">
                            <input class="change-paper form-control" data-on="primary" data-off="info" type="checkbox"
                                   data-on-text="是" data-off-text="否"
                                   value="${scene.changePaper}" onclick="this.value=this.checked?0:1" style="height: 30px;">
                        </div>

                    </div>

                </div>


                <%--<div class="form-group">--%>
                    <%--<label class="control-label col-md-2">开场人</label>--%>
                    <%--<div class="col-md-10">--%>
                        <%--<input class="form-control" type="text" name="createUserId" value="${scene.createUserId}"--%>
                               <%--required>--%>
                        <%--<span class="help-block with-errors">提示信息</span>--%>
                    <%--</div>--%>
                <%--</div>--%>

                <%--<div class="form-group">--%>
                    <%--<label class="control-label col-md-2">部门信息</label>--%>
                    <%--<div class="col-md-10">--%>
                        <%--<input class="form-control" type="text" name="departmentId" value="${scene.departmentId}"--%>
                               <%--required>--%>
                        <%--<span class="help-block with-errors">提示信息</span>--%>
                    <%--</div>--%>
                <%--</div>--%>



                <%--<div class="form-group">--%>
                    <%--<label class="control-label col-md-2">试卷策略</label>--%>
                    <%--<div class="col-md-10">--%>
                        <%--<select class="form-control">--%>
                            <%--<option value="">请选择</option>--%>
                            <%--<c:forEach items="${paperPolicyList}" var="paperPolicy">--%>
                                <%--<option value="${paperPolicy.id}"--%>
                                        <%--<c:if test="${paperPolicy.id == scene.paperPolicyId}">selected</c:if>>--%>
                                        <%--${paperPolicy.name}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--&lt;%&ndash;<input class="form-control" type="text" name="paperPolicyId" value="${scene.paperPolicyId}"&ndash;%&gt;--%>
                               <%--&lt;%&ndash;required>&ndash;%&gt;--%>
                        <%--<span class="help-block with-errors">提示信息</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<label class="control-label col-md-2">取消原因</label>--%>
                    <%--<div class="col-md-10">--%>
                        <%--<input class="form-control" type="text" name="reason" value="${scene.reason}" required>--%>
                        <%--<span class="help-block with-errors">提示信息</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<label class="control-label col-md-2">remark</label>--%>
                    <%--<div class="col-md-10">--%>
                        <%--<input class="form-control" type="text" name="remark" value="${scene.remark}" required>--%>
                        <%--<span class="help-block with-errors">提示信息</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<label class="control-label col-md-2">status</label>--%>
                    <%--<div class="col-md-10">--%>
                        <%--<input class="form-control" type="text" name="status" value="${scene.status}" required>--%>
                        <%--<span class="help-block with-errors">提示信息</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<label class="control-label col-md-2">更新时间</label>--%>
                    <%--<div class="col-md-10">--%>
                        <%--<input class="form-control" type="text" name="updateTime" value="${scene.updateTime}" required>--%>
                        <%--<span class="help-block with-errors">提示信息</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<label class="control-label col-md-2">更新用户id</label>--%>
                    <%--<div class="col-md-10">--%>
                        <%--<input class="form-control" type="text" name="updateUserId" value="${scene.updateUserId}"--%>
                               <%--required>--%>
                        <%--<span class="help-block with-errors">提示信息</span>--%>
                    <%--</div>--%>
                <%--</div>--%>

                <div class="button pull-right">
                    <button class="btn btn-warning btn-cancel" onclick="history.back();">取消</button>

                    <div class="space">

                    </div>
                    <button class="btn btn-primary btn-commit">继续</button>

                </div>
            </form>
        </div>

    </div>
</div>
<script>
    /**
     * 计算结束时间
     */
    $("[name='beginTime'], [name='delay'], [name='duration']").bind("change", function () {
        var beginTime = $("[name='beginTime']").val();
        var delay = $("[name='delay']").val().trim();
        var duration = $("[name='duration']").val().trim();
        var date = new Date(beginTime);
        var time = date.getTime() + delay* 60 * 1000 + duration * 60 * 1000;
        var endDate = new Date();
        endDate.setTime(time);
        console.log("time: ", time);
        if(beginTime && delay && duration){
            $("[name='endTime']").setTime(endDate);
        }
    })

    $(function () {
        $(".change-paper").bootstrapSwitch();
    })


</script>
</body>
</html>
