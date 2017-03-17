<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>试题管理</title>
    <%@ include file="../template/header.jsp" %>

</head>
<body>
<div class="container">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#" class="active">商品管理</a></li>
        </ol>
    </div>
    <div class="row info-search">
        <div class="pull-right form-inline">
            <input type="text" class="form-control" placeholder="输入关键词，例如名称、品牌、序号、供应商等">
            <!--<span class="input-group-btn">-->
            <button class="btn btn-info" type="button">搜索</button>
        </div>
    </div>

    <div class="row pre-table">
        <div class="pull-right">
            <jsp:include page="../template/page-nav.jsp"/>
        </div>
    </div>
    <div class="row table-responsive">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th><input type="checkbox" class="selectAll"></th>
                <th class="col-md-2">题目</th>
                <th class="col-md-3">题干</th>
                <th class="col-md-1">答案</th>
                <th class="col-md-2">额外信息</th>
                <th class="col-md-1">题型</th>
                <th class="col-md-1">题库</th>
                <th class="col-md-2">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pi.data}" var="question" varStatus="line">
                <tr>
                    <td><input type="checkbox" objId="${question.id}"></td>
                    <td>${question.title}</td>
                    <td>${question.content}</td>
                    <td>${question.answer}</td>
                    <td>${question.extraInfo}</td>
                    <td>${metaInfoMap.get(question.metaInfoId)}</td>
                    <td>${bankMap.get(question.questionBankId)}</td>
                    <td>
                        <a href="edit.do?id=${question.id}&type=detail" class="opr">详情</a>
                        <a href="edit.do?id=${question.id}" class="opr">修改</a>
                        <a href="javascript:del(${question.id})" class="opr">删除</a>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

    <div class="row after-table">
        <div class="pull-left form-inline">
            <select class="form-control show-count">
                <option value="10" <c:if test="${ pi.showCount == 10 }">selected</c:if>>10</option>
                <option value="25" <c:if test="${ pi.showCount == 25}">selected</c:if>>25</option>
                <option value="50" <c:if test="${ pi.showCount == 50}">selected</c:if>>50</option>
            </select>
            <div>条/页</div>

        </div>
        <div class="pull-right">

        </div>
    </div>
</div>
<script type="javascript">

</script>
</body>
</html>