<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>答题</title>
    <%@ include file="../template/header.jsp" %>
    <style>
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
            <li><a href="#">考试管理</a></li>
            <li><a href="#" class="active">答题</a></li>
        </ol>
    </div>
    <input type="hidden" value="${param.type}" id="type">
    <input type="hidden" value="${paper.id}" id="paperId">
    <input type="hidden" value="${paper.questionIds}" id="questionIds">
    <%--<div class="hidden" id="metaInfo">--%>
        <%--${metaInfo}--%>
    <%--</div>--%>
    <input type="hidden" value='${metaInfo}' id="metaInfo">
    <div class="row" style="margin-bottom: 15px;">
        <div class="form form-inline pull-left">
            <div class="input-group">
                <div class="input-group-addon">
                    考试名称：
                </div>
                <input class="form-control col-md-1" readonly type="text" value="${scene.name}">
            </div>
            <div class="input-group">
                <div class="input-group-addon">
                    更换另外一套试卷
                </div>
                <div class="input-group-btn">
                    <button class="btn btn-danger" id="changePaper">确定</button>
                </div>
            </div>
            <div class="input-group">
                <div class="input-group-addon">
                    题目：
                </div>
                <select class="form-control">
                    <option value="1">单选第一题</option>
                    <option value="2">单选第二题</option>
                    <option value="3">单选第三题</option>
                    <option value="4">多选第一题</option>
                    <option value="5">判断第一题</option>
                </select>
            </div>
        </div>

        <div class="form form-inline pull-right">
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">剩余时间：</div>
                    <input id="timer" type="text" class="timer form-control" value="00小时11分28秒" readonly
                           style="width: 150px;">
                </div>
            </div>

        </div>

    </div>
    <div class="row">
        <div class="col-md-8">
            <div class="form form-horizontal">
                <div class="form-group">
                    <!--<label class="control-label">题目</label>-->
                    <div class="col-md-12">
                        <div class="panel panel-default" style="margin-left: -15px;">
                            <div class="panel-heading">
                                <h3 class="panel-title" id="title" ></h3>
                            </div>
                            <div class="panel-body" style="min-height: 300px;">
                                <ul class="list-group" id="content">
                                </ul>
                            </div>

                            <%--<div class="panel-footer">Panel footer</div>--%>
                        </div>
                    </div>
                </div>
                <div class="form-group" id="single-box" style="display: none">
                    <label class="radio inline">
                        <input type="radio" name="answer" value="A">&nbsp;&nbsp;A
                    </label>
                    <label class="radio inline">
                        <input type="radio" name="answer" value="B">&nbsp;&nbsp;B
                    </label>
                    <label class="radio inline">
                        <input type="radio" name="answer" value="C">&nbsp;&nbsp;C
                    </label>
                    <label class="radio inline">
                        <input type="radio" name="answer" value="D">&nbsp;&nbsp;D
                    </label>
                    <label class="radio inline">
                        <input type="radio" name="answer" value="E">&nbsp;&nbsp;E
                    </label>
                    <label class="radio inline">
                        <input type="radio" name="answer" value="F">&nbsp;&nbsp;F
                    </label>
                </div>
                <div class="form-group" id="multi-box" style="display: none">
                    <label class="checkbox inline">
                        <input type="checkbox" name="answer" value="A">&nbsp;&nbsp;A
                    </label>
                    <label class="checkbox inline">
                        <input type="checkbox" name="answer" value="B">&nbsp;&nbsp;B
                    </label>
                    <label class="checkbox inline">
                        <input type="checkbox" name="answer" value="C">&nbsp;&nbsp;C
                    </label>
                    <label class="checkbox inline">
                        <input type="checkbox" name="answer" value="D">&nbsp;&nbsp;D
                    </label>
                    <label class="checkbox inline">
                        <input type="checkbox" name="answer" value="E">&nbsp;&nbsp;E
                    </label>
                    <label class="checkbox inline">
                        <input type="checkbox" name="answer" value="F">&nbsp;&nbsp;F
                    </label>
                    <label class="checkbox inline">
                        <input type="checkbox" name="answer" value="G">&nbsp;&nbsp;G
                    </label>
                </div>
                <div class="form-group" id="judge-box" style="display: none">
                    <label class="radio inline">
                        <input type="radio" name="answer" value="T">&nbsp;&nbsp;正确
                    </label>
                    <label class="radio inline">
                        <input type="radio" name="answer" value="F">&nbsp;&nbsp;错误
                    </label>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">备注：</label>
                    <textarea class="form-control">
                        本题备注信息
                    </textarea>
                </div>
                <div class="row">
                    <button class="btn btn-default pull-left" id="prevBtn">上一题</button>
                    <div class="space" style="display: inline-block; margin-right: 200px;">

                    </div>
                    <button class="btn btn-primary pull-right" id="nextBtn">下一题</button>
                </div>

            </div>
        </div>
        <div class="col-md-3 pull-right" style="padding-right: 0px;">
            <table class="table table-bordered table-responsive">
                <thead>
                <tr>
                    <th>题号</th>
                    <th>我的答案</th>
                    <th>最佳答案</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><a href="#">单选1</a></td>
                    <td>c</td>
                    <td>B</td>
                </tr>
                <tr>
                    <td><a href="#">单选1</a></td>
                    <td>c</td>
                    <td>B</td>
                </tr>
                <tr>
                    <td><a href="#">单选1</a></td>
                    <td>c</td>
                    <td>B</td>
                </tr>
                <tr>
                    <td><a href="#">单选1</a></td>
                    <td>c</td>
                    <td>B</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    var metaInfo = eval(${metaInfo});
    console.log("metaInfo: ", metaInfo)
    var index = 0;
    var questionIds = new Array();
    $(function () {
//        初始化定时器
        var info = $("#timer").val();
        $("#timer").timer({
            duration: '65s',
            countdown: true,
            format: '%H:%M:%S',
            callback: function () {
                swal("", "时间到", "info");
            }
        });
        questionIds = JSON.parse($("#questionIds").val());

        /**
         * 初始化题目
         */
        getQuestionInfo();



    });

    /**
     * 前一题
     */
    $("#prevBtn").on("click", function () {
        if (index == 0) {
            swal("", "已经是第一题了", "info");
        }
        zeroModal.loading(4);
        getQuestionInfo();
        index--;
        zeroModal.closeAll();
    });

    /**
     *  后一题
     * */
    $("#nextBtn").on("click", function () {
        if (index == questionIds.length - 1) {
            swal("", "已经是最后一题了", "info");
        }
        zeroModal.loading(4);
        getQuestionInfo();
        index++;
        zeroModal.closeAll();
    });
    /**
     * 获取试题信息
     */
    function getQuestionInfo() {
        if (index < 0 || index > questionIds.length - 1) {
            swal("", "数据异常，请联系管理员", "error");
            return false;
        }
        $.ajax({
            url: "getQuestion.do",
            type: "post",
            data: {paperId: $("#paperId").val(), questionId: questionIds[index + 1]},
            success: function (data) {
                console.log(data);
                var res = JSON.parse(data);
                if (res.code == 0) {
                    var question = res.data;
                    var title = question.title;
                    var content = JSON.parse(question.content);
                    var metaInfoId = question.metaInfoId;
                    if(metaInfo[metaInfoId] == "single"){
                        $("#single-box").show();
                        $("#multi-box").hide();
                        $("#judge-box").hide();
                    }else if(metaInfo[metaInfoId] == "multi"){
                        $("#single-box").hide();
                        $("#multi-box").show();
                        $("#judge-box").hide();
                    }else if(metaInfo[metaInfoId] == "judge"){
                        $("#single-box").hide();
                        $("#multi-box").hide();
                        $("#judge-box").show();
                    }


                    $("#title").html(title);
                    var buffer = '';
                    $.each(content, function (idx, obj) {
                        buffer += '<li class="list-group-item">' + obj + '</li>';
                    });
                    $("#content").html(buffer);
                    index++;
                    zeroModal.closeAll();
                } else {
                    swal("", res.msg, "warning");
                }
            },
            error: function (data) {

            }
        })
    }

    function getSeconds() {
        return $("#timer").data("seconds");
    }

    $("#changePaper").bind("click", function () {
        swal("", "更换试卷后当前试卷作废，剩余作答时间不变。且仅有一次更换机会，确认要更换试卷吗？", "warning");
    });


    //勾选插件
    $("[type='checkbox'], [type='radio']").iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue',
        increaseArea: '20%' // optional
    });
</script>
</body>
</html>
