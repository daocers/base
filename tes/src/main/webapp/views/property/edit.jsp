<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>属性编辑</title>
    <%@ include file="../template/header.jsp" %>
</head>
<body>
<div class="container">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#">品类管理</a></li>
            <li><a href="#" class="active">属性编辑</a></li>
        </ol>
    </div>
    <input type="hidden" value="${param.type}" id="type">
    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" method="post" action="save.do" data-toggle="validator" role="form">
                <input id="id" type="hidden" name="id" value="${property.id}">
                <div class="form-group">
                    <label class="control-label col-md-2">名称</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="name" value="${property.name}" required>
                        <span class="help-block with-errors">属性的名称，比如难度，用于前端展示 </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">属性编码</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="code" value="${property.code}" required>
                        <span class="help-block with-errors">属性的编码，建议使用英文，拼音或者首字母简称</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">描述</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="description" value="${property.description}"
                               required>
                        <span class="help-block with-errors">简单概括该属性的特性，使用场景和用途等</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">序号</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="idx" value="${property.idx}" required>
                        <span class="help-block with-errors">用于属性展示的先后顺序</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">状态</label>
                    <div class="col-md-10">
                        <select class="form-control" name="status" required>
                            <option value="0" <c:if test="${property.status == 0}"> selected</c:if>>启用</option>
                            <option value="1" <c:if test="${property.status == 1}"> selected</c:if>>禁用</option>
                        </select>
                        <span class="help-block with-errors">可用/禁用  </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">选项</label>
                    <input type="hidden" name="itemInfo" value="${itemInfo}">
                    <div class="col-md-10">
                        <table class="table table-bordered editable-table">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th class="cell-edit">前缀</th>
                                <th class="cell-edit">属性值</th>
                                <th class="cell-edit">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${property.propertyItemList.size() == 0 || property == null}">
                                <tr>
                                    <td>1</td>
                                    <td></td>
                                    <td></td>
                                    <td class="opr-td"><a href="" class="del-row">删除</a></td>
                                </tr>
                            </c:if>
                            <c:forEach items="${property.propertyItemList}" var="item" varStatus="line">
                                <tr>
                                    <td itemId="${item.id}">${line.count}</td>
                                    <td>${item.code}</td>
                                    <td>${item.name}</td>
                                    <td class="opr-td"><a href="" class="del-row">删除</a></td>
                                </tr>

                            </c:forEach>
                            </tbody>
                        </table>
                        <button class="btn btn-small btn-primary add-row">+</button>
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

    $("body").on("click", ".del-row", function () {
        $(this).parentsUntil("tr").parent().remove();
        return false;
    });
    /**
     * 动态添加行，并绑定事件
     */
    $(".add-row").bind("click", function () {
        var rowNum = $("tbody").find("tr:last > td:first").text();
        if(!rowNum){
            rowNum = 0;
        }
        console.log(rowNum);
        var index = parseInt(rowNum) + 1;
        console.log("<tr><td>" + index + "</td><td></td><td></td></tr>");
        $("table").find("tbody").append("<tr><td>" + index +
            "</td><td></td><td></td><td class='opr-td'><a href='' class='del-row'>删除</a></td></tr>");
        $("table").editable();//刷新表格，给刚添加的行绑定事件
        return false;
    });


    $(".btn-commit").on("click", function () {
        var flag = true;
        var res = new Array();
        $("tbody").find("tr").each(function (idx, e) {
            var id, name, code;
            $(e).find("td").each(function (idx1, e1) {
                if (idx1 == 0) {
                    id = $(e1).attr("itemId");
                }
                if (idx1 == 1) {
                    code = $(e1).text();
                    if(code == ''){
                        flag = false;
                        swal("", "第" + (idx + 1) + "行， 第" + (idx1 + 1) + "列不能为空", "warning");
                        return false;
                    }
                }
                if (idx1 == 2) {
                    name = $(e1).text();
                }
            });
            res.push({"id": id, "name": name, "code": code});
        });
        if(!flag){
            return false;
        }
        $("input[name='itemInfo']").val(JSON.stringify(res));
        $("form").submit();
    })
</script>
</body>
</html>
