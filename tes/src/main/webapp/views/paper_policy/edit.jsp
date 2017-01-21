<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>品类编辑</title>
    <style type="text/css">
        .inline {
            display: inline;
            margin-right: 30px;
        }

        .form-group > .radio.inline > label {
            padding-left: 15px;
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
                <input id="id" type="hidden" name="id" value="${paperpolicy.id}">

                <div class="form-group">
                    <label class="control-label col-md-2">策略名称</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="name" value="${paperpolicy.name}" required>
                        <span class="help-block with-errors"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">试卷策略编码</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="code" value="${paperpolicy.code}" required>
                        <span class="help-block with-errors">策略编码，便于识别和记忆</span>
                    </div>
                </div>

                <%--<div class="form-group">--%>
                <%--<label class="control-label col-md-2">所属机构</label>--%>
                <%--<div class="col-md-10">--%>
                <%--<select class="form-control" name="branchId" required>--%>
                <%--<option value="">请选择</option>--%>
                <%--<c:forEach items="${branchList}" var="branch">--%>
                <%--<option value="${branch.id}">${branch.name}</option>--%>
                <%--</c:forEach>--%>
                <%--</select>--%>
                <%--<span class="help-block with-errors">试题策略的归属机构</span>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                <%--<label class="control-label col-md-2">所属部门</label>--%>
                <%--<div class="col-md-10">--%>
                <%--<select class="form-control" name="departmentId" required>--%>
                <%--<option value="">请选择</option>--%>
                <%--<c:forEach var="department" items="${departmentList}">--%>
                <%--<option value="${department.id}">${department.name}</option>--%>
                <%--</c:forEach>--%>
                <%--</select>--%>
                <%--<span class="help-block with-errors">创建人所在的部门</span>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                <%--<label class="control-label col-md-2">所属岗位</label>--%>
                <%--<div class="col-md-10">--%>
                <%--<input class="form-control" type="text" name="stationId" value="${paperpolicy.stationId}"--%>
                <%--required>--%>
                <%--<span class="help-block with-errors">策略试用岗位</span>--%>
                <%--</div>--%>
                <%--</div>--%>

                <div class="form-group">
                    <label class="control-label col-md-2">选择题型</label>
                    <div class="col-md-10">
                        <c:forEach var="questionMetaInfo" items="${questionMetaInfoList}">
                            <label class="checkbox inline">
                                <input type="checkbox" name="questionMetaInfoId" value="${questionMetaInfo.id}">&nbsp;&nbsp;${questionMetaInfo.name}
                            </label>
                        </c:forEach>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">试题策略</label>
                    <input type="hidden" name="content" value="">
                    <div class="col-md-10 table-responsive">
                        <table class="table table-bordered table-editable">
                            <thead>
                            <tr>
                                <th>题型</th>
                                <th>试题策略</th>
                                <th>题量</th>
                                <th>分值</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${data}" var="questionPList">
                                <tr metaInfoId="${questionPList.get(0).questionMetaInfoId}">
                                    <td>
                                        <select class="form-control  form-control-intable" disabled>
                                            <c:forEach items="${questionMetaInfoList}" var="questionMetaInfo">
                                                <option value="${questionMetaInfo.id}"
                                                        <c:if test="${questionMetaInfo.id == questionPList.get(0).questionMetaInfoId}">
                                                            selected
                                                        </c:if>
                                                >
                                                        ${questionMetaInfo.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <select class="form-control  form-control-intable">
                                            <option>请选择</option>
                                            <c:forEach var="questionP" items="${questionPList}">
                                                <option value="${questionP.id}"
                                                        count="${questionP.count}">${questionP.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td width="80px">
                                        <input class="form-control form-control-intable" value="" readonly>
                                    </td>
                                    <td width="80px">
                                        <input class="form-control form-control-intable"
                                               onkeyup="value=value.replace(/[^\d{1,}\.\d{1, 2}]/g,'')">
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">总题量</label>
                    <div class="col-md-3">
                        <input class="form-control" value="" name="count" readonly>
                    </div>
                    <label class="control-label col-md-2 col-md-off-2" style="text-align: right">总分</label>
                    <div class="col-md-3">
                        <input class="form-control" value="" name="score" readonly>
                    </div>
                </div>

                <div class="form-group">
                    <div class="radio inline">
                        <label><input type="radio" value="" name="percentable"> &nbsp;&nbsp;是否百分制</label>
                    </div>
                </div>
                <div class="button pull-right">
                    <button class="btn btn-primary btn-commit" onclick="javascript:save();">保存</button>
                    <div class="space">

                    </div>
                    <button class="btn btn-warning btn-cancel">取消</button>
                </div>
            </form>
        </div>

    </div>
</div>
<script>
    function save() {
        var metaInfoIds = $("[name='questionMetaInfoId']:checked").val();
        console.log("metaInfoIds", metaInfoIds);
        var data = new Array();
        $("table tr").each(function (idx, e) {
            var metaInfoId = $(e).attr("metaInfoId");
            if (metaInfoIds.indexOf(metaInfoId) > -1) {
                var questionPolicyId = $(e).find("td:eq(1) select").val();
                var score = $(e).find("td:eq(3) input").val();
                if (!score) {
                    $(e).find("td:eq(3)").addClass("input-warning");
                    return false;
                }
                var item = {questionMetaInfoId: questionPolicyId, score: score}
                data.push(item);
            }
        });
        console.log(data);
        $('[name="content"]').val(JSON.stringify(data));
        $("form").submit();
    }


    //    $('[name="questionMetaInfoId"]').on("click", function () {
    $('[name="questionMetaInfoId"]').on("ifUnchecked ifChecked", function (event) {
        var id = $(this).val();
        var type = event.type;
        if (type == "ifChecked") {
            $("tr[metaInfoId='" + id + "']").show();
            console.log("checked")
        } else {
            $("tr[metaInfoId='" + id + "']").hide();
            console.log("unchecked");
        }

//        var ids = new Array();
//        $("[name='questionMetaInfoId']:checked").each(function (idx, e) {
//            ids.push($(e).val());
//        });
//        console.log(JSON.stringify(ids));
    });


    $("[type='checkbox'], [type='radio']").iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });

    $("table td:eq(1) select").on("change", function () {
        var count = $(this).find("option:selected").attr("count");
        if (!count) {
            count = 0;
        }
        $(this).parents("td").next().find("input").val(count);
        getAllCountAndScore();
    });

    $("table td:eq(3) input").on("input propertychange", function () {
        getAllCountAndScore();
    });


    /*
     * 根据表格动态获取题量和总分
     * */
    function getAllCountAndScore() {
        var totalCount = 0;
        var totalScore = 0;
        $("table tr").each(function (idx, e) {
            var count = 0;
            var score = 0;
            $(e).find("input").each(function (idx1, e1) {
                if (idx1 == 0) {
                    count = $(e1).val();
                    if (!count) {
                        count = 0;
                    }
                }
                if (idx1 == 1) {
                    score = $(e1).val();
                    if (!score) {
                        score == 0;
                    }
                }
            });
            totalCount += parseInt(count);
            totalScore += (count * score);
        });
        $("[name='count']").val(totalCount);
        $("[name='score']").val(totalScore);
    }
</script>
</body>
</html>
