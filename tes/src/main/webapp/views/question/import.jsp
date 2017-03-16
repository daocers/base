<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>导入试题</title>
    <%@ include file="../template/header.jsp" %>

    <style>
        .prop-box > *{
            display: inline-block;
        }

        ul.list-group{
            width: auto;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a> </li>
            <li><a href="#" class="active">商品管理</a> </li>
        </ol>
    </div>

    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" method="post" action="batchAdd.do"  enctype="multipart/form-data"  data-toggle="validator" role="form">
                <div class="form-group">
                    <label class="control-label col-md-1">题型</label>
                    <div class="col-md-10">
                        <select class="form-control" name="metaInfoId" required>
                            <option value="">请选择</option>
                            <c:forEach var="metaInfo" items="${metaInfoList}">
                                <option value="${metaInfo.id}" <c:if test="${metaInfoId == metaInfo.id}">selected</c:if>>${metaInfo.name}</option>
                            </c:forEach>
                        </select>
                        <span class="help-block with-errors">选择需要导入的题型</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-1">题库</label>
                    <div class="col-md-10">
                        <select class="form-control" name="questionBankId" required>
                            <option value="">请选择</option>
                            <c:forEach var="bank" items="${questionBankList}">
                                <option value="${bank.id}" <c:if test="${bank.id = questionBankId}">selected</c:if> >${bank.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group prop-list-container">
                    <label class="control-label col-md-1">选择属性</label>
                    <div class="col-md-11 prop-box">

                    </div>
                </div>
                <input type="hidden" name="propItemIdInfo">

                <div class="form-group">
                    <div class="col-md-offset-1" style="padding-left: 15px;">
                        <input type="file" name="file" class="jfilestyle" data-input="true" data-buttonText="选择文件">
                        <button class="btn btn-success btn-commit">上传</button>
                        ？   没有模板文件<a href="javascript:download();">点击下载</a>模板
                    </div>

                </div>

            </form>
        </div>

    </div>
</div>
<script>

    /**
     * 下载提醒对应的模板
     */
    function download() {
        var id = $("select").val();
        if(!id){
            alert("请选择题型")
        }else{
            window.location.href = "/questionMetaInfo/downModel.do?metaInfoId=" + id;
        }
    }
</script>
</body>
</html>