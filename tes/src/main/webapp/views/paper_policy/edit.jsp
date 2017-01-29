<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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

                <input type="hidden" name="content" value='${paperpolicy.content}'>
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

                <div class="form-group ques-type">
                    <label class="control-label col-md-2">包含题型</label>
                    <div class="col-md-10">
                        <c:forEach var="questionMetaInfo" items="${questionMetaInfoList}">
                            <label class="checkbox inline">
                                <input type="checkbox" name="questionMetaInfoId"
                                    <c:if test="${fn:contains(metaInfoIds, questionMetaInfo.id)}">checked</c:if>
                                       value="${questionMetaInfo.id}">&nbsp;&nbsp;${questionMetaInfo.name}
                            </label>
                        </c:forEach>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">试题策略</label>
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
                            <c:forEach varStatus="index" var="questionMetaInfo" items="${questionMetaInfoList}">
                                <tr metaInfoId="${questionMetaInfo.id}" hidden>
                                    <td>
                                        <select class="form-control form-control-intable" disabled>
                                            <option value="${questionMetaInfo.id}"
                                                    selected>${questionMetaInfo.name}</option>
                                        </select>
                                    </td>
                                    <td>
                                        <select class="form-control form-control-intable">
                                            <option value="">请选择</option>
                                            <c:forEach items="${data[index.index]}" var="questionPolicy">
                                                <option value="${questionPolicy.id}"
                                                        <%--<c:if test="${paperpolicy.content}">selected</c:if>--%>
                                                        count="${questionPolicy.count}">${questionPolicy.name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td width="80px">
                                        <input class="form-control form-control-intable" value="" readonly>
                                    </td>
                                    <td width="80px">
                                        <input class="form-control form-control-intable score" type="text" value=""
                                        >
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
                    <label class="control-label col-md-2">是否百分制</label>
                    <div class="col-md-10">
                        <div class="switch" style="height:30px;">
                            <input class="percentable form-control" data-on-color="info" data-off-color="warning"
                                   type="checkbox" name="percentable"
                                   data-on-text="是" data-off-text="否"
                                   <c:if test="${paperpolicy.percentable == 0}">checked</c:if>
                                   value="${paperpolicy.percentable}" onclick="this.checked?0:1"
                                   style="height: 30px;">
                        </div>
                        <%--<input type="radio" value="" name="percentable">--%>
                        <span class="help-block with-errors">试卷总分将按照满分100分折合最终的成绩</span>
                    </div>
                    <div class="radio inline">


                    </div>
                </div>
                <div class="button pull-right">
                    <button type="button" class="btn btn-primary btn-commit save">保存</button>
                    <div class="space">

                    </div>
                    <button class="btn btn-warning btn-cancel">取消</button>
                </div>
            </form>
        </div>

    </div>
</div>
<script>
    $(function () {
        $("[name='questionMetaInfoId']:checked").trigger("ifChecked");
//        初始化已经选择的信息
        var content = $('[name="content"]').val();
//        console.log("content: ", content);
        if(content){
            var oldInfo = JSON.parse(content);
//            console.log("old: ", oldInfo);
            $.each(oldInfo, function (index, val) {
                var questionMetaInfoId = val.questionMetaInfoId;
                var questionPolicyId = val.questionPolicyId;
                var score = val.score;
                $("tr[metaInfoId='" + questionMetaInfoId + "']").find("td:eq(1) > select").val(questionPolicyId);
                $("tr[metaInfoId='" + questionMetaInfoId + "']").find("td:eq(1) > select").trigger("change");
                $("tr[metaInfoId='" + questionMetaInfoId + "']").find("td:eq(3) > input").val(score);

//                console.log(val.score);
//                console.log(val);
            })
        }



//        点击提交按钮
        $(".btn.save").on("click", function () {
            if (!$("[name='name']").val()) {
                $("form").validator('validate');
                return false;
            }
            $("form").validator('validate');
            var flag = true;
            $("form").on("invalid.bs.validator", function (event) {
//                console.log(event.detail);
                flag = false;
            });
            if (!flag) {
                return false;
            } else {
                var metaInfoIds = "";
                $("[name='questionMetaInfoId']:checked").each(function () {
                    metaInfoIds += $(this).val() + ",";
                });
                if (metaInfoIds == '') {
                    swal("", "请选择包含的题型", "warning");
                    return false;
                } else {
                    console.log("metaInfoIds", metaInfoIds);
                    var data = new Array();
                    var flag = true;
                    $("table tbody tr").each(function (idx, e) {
                        console.log("index: ", idx);
                        var metaInfoId = $(e).attr("metaInfoId");
                        if (metaInfoIds.indexOf(metaInfoId) > -1) {
                            var questionPolicyId = $(e).find("td:eq(1) select").val();
                            var score = $(e).find("td:eq(3) input").val();
                            if (!score || score == 0) {
                                $(e).find("td:eq(3) > input").addClass("input-warning");
                                flag = false;
                            }else{
                                $(e).find("td:eq(3) > input").removeClass("input-warning");
                            }

                            if (!questionPolicyId) {
                                $(e).find("td:eq(1) > select").addClass("input-warning");
//                                swal("", "请完善表格第" + (idx + 1) + "行数据", "error");
                                flag = false;
                            }else{
                                $(e).find("td:eq(1) > select").removeClass("input-warning");
                            }
                            var item = {questionMetaInfoId: metaInfoId, questionPolicyId: questionPolicyId, score: score}
                            data.push(item);
                        }
                    });
                    if (!flag) {
                        return false;
                    }
                    console.log(data);
                    $('[name="content"]').val(JSON.stringify(data));
                $("form").submit();
                }

            }
            return false;
        });

        /**
         * 分值只允许小数点后为5的小数或者整数
         */
        $(".score").on("keyup", function () {
            var val = $(this).val();
            if (val == '0') {
                console.log("录入为 0 ")
                return false;
            }
            if (val.substring(val.length - 1) == ".") {
                console.log("录入为.")
                return false;
            } else {
                console.log("开始匹配")
                /*两位小数*/
//                var reg = /^(([1-9]\d*(\.\d{1,2}){0,1})|0\.\d{1,2})$/g;

                var reg = /^(([1-9]\d*(\.5){0,1})|0\.5)$/g;

                var right = reg.test(val);
                if (!right) {
                    $(this).val("");
                }
            }
        });


        $(".percentable").bootstrapSwitch({
            onSwitchChange: function (event, state) {
                if (state == true) {
                    $(this).val("0");
                } else {
                    $(this).val("1");
                }
            }
        });
    })


    /**
     * *勾选题型之后的事件
     */
    $('[name="questionMetaInfoId"]').on("ifUnchecked ifChecked", function (event) {
        console.log(event)
        var id = $(this).val();
        var type = event.type;
        if (type == "ifChecked") {
            $("tr[metaInfoId='" + id + "']").show();
            console.log("checked")
        } else {
            $("tr[metaInfoId='" + id + "']").hide();
            console.log("unchecked");
        }

    });


    $(".ques-type [type='checkbox']").iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });

    $("table tbody tr").find("td:eq(1) select").on("change", function () {
        console.log("count");
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
