<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>选择人员</title>
</head>
<body>
<div class="container">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#">考试管理</a></li>
            <li><a href="#" class="active">选择参考人员</a></li>
        </ol>
    </div>
    <input type="hidden" value="${type}" id="type">
    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" method="post" action="saveUser.do" data-toggle="validator" role="form">
                <input id="id" type="hidden" name="id" value="${scene.id}">

                <div class="form-group form-inline">
                    <label class="control-label">部门</label>
                    <select class="form-control" name="departmentId">
                        <option value="">请选择</option>
                        <c:forEach var="department" items="${departmentList}">
                            <option value="${department.id}">${department.name}</option>
                        </c:forEach>
                    </select>
                    <label class="control-label">机构</label>
                    <select class="form-control" name="branchId">
                        <option value="">请选择</option>
                        <c:forEach var="branch" items="${branchList}">
                            <option value="${branch.id}">${branch.name}</option>
                        </c:forEach>
                    </select>
                    <label class="control-label">岗位</label>
                    <select class="form-control" name="stationId">
                        <option value="">请选择</option>
                        <c:forEach var="station" items="${stationList}">
                            <option value="${station.id}">${station.name}</option>
                        </c:forEach>
                    </select>
                    <button class="btn btn-info" type="button" onclick="javascript:search();">查询</button>
                </div>
                <div class="" style="min-height: 300px;">
                    <button class="btn btn-info" type="button" id="selectAll">全选/全不选</button>
                    <button class="btn btn-info" type="button" id="add">添加</button>
                    <table class="table table-bordered data-table">
                        <thead>
                        <tr>
                            <th><input type="checkbox" class="selectAll"></th>
                            <th>姓名</th>
                            <th>机构</th>
                            <th>部门</th>
                            <th>岗位</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td colspan="5">请选择筛选条件！</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">已选人员</label>
                    <div class="col-md-2">
                        <input class="form-control" type="text">
                        <span class="help-block with-errors">提示信息</span>
                    </div>
                </div>

                <div class="button pull-right">
                    <button class="btn btn-warning btn-commit">上一步</button>
                    <div class="space">

                    </div>
                    <button class="btn btn-primary btn-cancel">下一步</button>
                </div>
            </form>
        </div>
        <div class="col-md-4">
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">已选人员</div>
                    <input type="text" id="count" readonly class="form-control" value="0">
                </div>
            </div>


            <table class="table table-bordered data-selected">
                <thead>
                <tr>
                    <th>姓名</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>

    </div>
</div>
<script src="../assets/js/my.js"></script>
<script>
    $(function () {
        $("#selectAll").on("click", function () {
            $(".selectAll").trigger("click");
        });

        $("#add").on("click", function () {
            var html = "";
            $(".data-table tbody").find("input:checked").each(function () {
                var id = $(this).val();
                var name = $(this).parents("tr").find("td:eq(1)").html();
                html += "<tr userid='" + id + "'>";
                html = html + "<td>" + name + "</td>";
                html += "<td><a href='javascript:remove(" + id + ")'>删除</a></td>";
                html += "</tr>"
            });
            $(".data-selected > tbody").append(html);
            getSelectCount();
        })


    })

    function getSelectCount() {
        var count = $(".data-selected tbody").find("tr").length;
        $("#count").val(count);

    }

    function remove(id) {
        $("tr[userid=" + id + "]").remove();
        getSelectCount();
    }


    /**
     * 筛选用户
     * @returns {boolean}
     */
    function search() {
        var deptId = $("[name='departmentId']").val();
        var branchId = $("[name='branchId']").val();
        var stationId = $("[name='stationId']").val();

        if (!deptId && !branchId && !stationId) {
            zeroModal.alert("请选择筛选条件");
            return false;
        }
        zeroModal.loading(3);
        $.ajax({
            url: '/user/listAll.do',
            data: {departmentId: deptId, branchId: branchId, stationId: stationId},
            success: function (data) {
                if (data == '-1') {
                    zeroModal.closeAll();
                    zeroModal.error("获取用户信息失败");
                } else {
                    console.log("data: ", data);

                    var userList = JSON.parse(data);
                    var html = "";
                    $.each(userList, function (index, val) {
                        console.log("index: ", index);
                        html += "<tr>";
                        html += '<td><input type="checkbox" value="' + val.id + '"></td>';
                        var name = "";
                        if (val.profile) {
                            if (val.profile.name) {
                                name = val.profile.name;
                            }
                        }
                        html += "<td>" + name + "</td>"
                        html += "<td>" + val.branchId + "</td>"
                        html += "<td>" + val.departmentId + "</td>"
                        html += "<td>" + val.stationId + "</td>"
                        html += "</tr>";
                    });
                    if (html == "") {
                        html = "<tr><td colspan='5'>查询无数据！</td></tr>";
                    }
                    console.log("html: ", html);
                    $(".data-table > tbody").html(html);
                    zeroModal.closeAll();
                }
            },
            error: function (data) {
                zeroModal.closeAll();
                zeroModal.error("获取用户信息失败");
            }
        })
    }
</script>
</body>
</html>
