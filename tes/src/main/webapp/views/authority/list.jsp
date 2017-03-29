<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>权限列表</title>
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
        <table class="table table-bordered editable-table">
            <thead>
            <tr>
                <th><input type="checkbox" class="selectAll"></th>
                <th>action</th>
                <th>controller</th>
                <th>描述</th>
                <th>名称</th>
                <th>参数</th>
                <th>状态</th>
                <th>上级权限</th>
                <th>类型</th>
                <th>url</th>
                <th>请求方法</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pi.data}" var="authority" varStatus="line">
                <tr>
                    <td><input type="checkbox" objId="${authority.id}"></td>
                    <td>${authority.action}</td>
                    <td>${authority.controller}</td>
                    <td>${authority.description}</td>
                    <td>${authority.name}</td>
                    <td>${authority.param}</td>
                    <td>${authority.status}</td>
                    <td>${authority.superiorId}</td>
                    <td>${authority.type}</td>
                    <td>${authority.url}</td>
                    <td>${authority.acceptMethod}</td>
                    <td>
                        <a href="edit.do?id=${authority.id}&type=detail" class="opr">详情</a>
                        <a href="edit.do?id=${authority.id}" class="opr">修改</a>
                        <a href="javascript:del(${authority.id})" class="opr">删除</a>
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
    $(function () {
        $("td").on("blur", ".cell-input > input", function (e) {
            console.log("开始")
            var id, name, desc;
            $(this).parents("tr").find("td").each(function (idx, e) {

                if (idx == 0) {
                    id = $(e).find("input").attr("objId");
                }
                if (idx == 3) {
                    desc = $(e).find(".cell-label").text();
                }
                if (idx == 4) {
                    name = $(e).find(".cell-label").text();
                }
            });
            $.ajax({
                url: "commit.do",
                type: "post",
                data: {"id": id, "name": name, "description": desc},
                success: function (data) {
                    if (data == "0") {
                        console.log("更新成功");
                    } else {
                        alert("更新失败");
                    }
                },
                error: function () {
                    alert("更新失败");
                }
            })
        })
    })

</script>
</body>
</html>