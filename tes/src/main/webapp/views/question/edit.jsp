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
                <input id="id" type="hidden" name="id" value="${question.id}">

                <div class="form-group">
                    <label class="control-label col-md-2">题目</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="title" value="${question.title}" required>
                        <span class="help-block with-errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">题干</label>
                    <div class="col-md-10">
                        <textarea class="form-control" name="content" rows="5">${question.content}</textarea>
                        <%--<input class="form-control" type="text" name="content" value="${question.content}" required>--%>
                        <span class="help-block with-errors">试题选项等题干信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">最佳答案</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="answer" value="${question.answer}" required>
                        <span class="help-block with-errors with-errors">最优答案选项</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">附加信息</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="extraInfo" value="${question.extraInfo}" >
                        <span class="help-block with-errors">本试题附加的一些其他信息</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">题型</label>
                    <div class="col-md-10">
                        <select class="form-control" name="metaInfoId" required>
                            <option value="">请选择</option>
                            <c:forEach var="metaInfo" items="${metaInfoList}">
                                <option value="${metaInfo.id}"
                                    <c:if test="${question.metaInfoId == metaInfo.id}">selected</c:if>
                                >${metaInfo.name}</option>
                            </c:forEach>
                        </select>
                        <span class="help-block with-errors"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">题库</label>
                    <div class="col-md-10">
                        <select class="form-control" name="questionBankId" required>
                            <option value="">请选择</option>
                            <c:forEach var="bank" items="${questionBankList}">
                                <option value="${bank.id}"
                                    <c:if test="${question.questionBankId == bank.id}">selected</c:if>
                                >${bank.name}</option>
                            </c:forEach>
                        </select>
                        <span class="help-block with-errors">试题归属题库</span>
                    </div>
                </div>


                <div class="form-group">
                    <input type="hidden" name="propItemIdInfo" required>
                    <label class="control-label col-md-2">属性</label>
                    <div class="col-md-10 prop-box">
                        <c:forEach items="${propertyList}" var="property">
                            <ul class="list-group col-md-2">
                                <li class="list-group-item list-group-item-success">${property.name}</li>
                                <c:forEach var="propItem" items="${property.propertyItemList}">
                                    <li class="list-group-item list-group-item-info">
                                        <div class="radio">
                                            <label><input type="radio" name="${property.id}" value="${propItem.id}"
                                                <c:if test="${propItemIdList.contains(propItem.id)}">checked</c:if>
                                            >${propItem.name}</label>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:forEach>
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
    //提交前处理信息
    $(".btn-commit").on("click", function () {
        var res = new Array();
        if($("ul.list-group").length != $("[type='radio']:checked").length){
            swal("", "请选择试题属性", "error");
            return false;
        }
        $("[type='radio']:checked").each(function (idx, e) {
            res.push($(e).val());
        });

        $("[name='propItemIdInfo']").val(JSON.stringify(res));
        $("form").submit();
        return false;
    });

    $("[name='metaInfoId']").on("change", function () {
        var id = $(this).val();
        if(!id){
            swal("", "请选择题型", "warning");
            return false;
        }
        $(this).attr("disabled");
        $.ajax({
            url: "getPropertyList.do",
            type: "get",
            data: {"id": id},
            success: function (data) {
                if(data == -1){
                    swal("", "获取题型属性信息失败", "error");
                }else{
                    var json = eval(data);
                    var builder = "";
                    $.each(json, function (idx, e) {
                        builder += ' <ul class="list-group col-md-2">';
                        builder += '<li class="list-group-item list-group-item-success">';
                        builder += e.name;
                        builder += '</li>';

                        $.each(e.propertyItemList, function (idx1, e1) {
                            builder += '<li class="list-group-item list-group-item-info">';
                            builder += '<div class="radio"><label><input type="radio" name="' + e.id + '" value="' + e1.id + '">'
                                    + e1.name + '</label></div>';
                            builder += "</li>"
                        });
                        builder += '</ul>';
                    });

                    $(".prop-box").html(builder);
                }
            },
            error: function () {
                swal("", "获取题型属性失败", "error");
            }
        })
        $(this).removeAttr("disabled");
    });
</script>
</body>
</html>
