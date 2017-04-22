<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="../template/header.jsp" %>--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>权限列表</title>
</head>
<body>
<%@ include file="../template/header.jsp" %>
<%@ include file="../template/menu-top.jsp" %>
<%@ include file="../template/menu-left.jsp" %>
<%--<form class="" style="width:780px; vertical-align: top; display: inline-block">--%>
<div class="container">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#" class="active">商品管理</a></li>
        </ol>
    </div>
    <form class="form form-inline search-form" action="list.do" method="post">
        <div class="form-group form-group-sm">
            <label class="control-label">名称</label>
            <input type="text" class="form-control" name="name" value="${param.name}">
        </div>
        <div class="form-group form-group-sm">
            <label class="control-label">类型</label>
            <select class="form-control" name="type">
                <option value="-1"></option>
                <option value="0"
                        <c:if test="${param.type == 0}">selected</c:if>>操作权限
                </option>
                <option value="2"
                        <c:if test="${param.type == 2}">selected</c:if>>菜单权限
                </option>
            </select>
        </div>
        <div class="form-group form-group-sm">
            <label class="control-label">controller</label>
            <select class="form-control" name="controller" value="${param.controller}">
                <option></option>
                <c:forEach var="con" items="${controllerList}">
                    <c:if test="${'' != con}">
                        <option value="${con}"
                                <c:if test="${param.controller == con}">selected</c:if> >${con}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
        <button class="btn btn-sm btn-info">提交</button>
    </form>
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
            <jsp:include page="../template/page-nav.jsp"/>
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