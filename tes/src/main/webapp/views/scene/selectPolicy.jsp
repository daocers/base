<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>生成试卷</title>
</head>
<body>
<div class="container">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#">考试管理</a></li>
            <li><a href="#" class="active">选择试卷策略</a></li>
        </ol>
    </div>
    <input type="hidden" value="${type}" id="type">
    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" method="post" action="savePolicy.do" data-toggle="validator" role="form">
                <input id="id" type="hidden" name="id" value="${scene.id}">

                <div class="form-group form-inline">
                    <label class="control-label">部门</label>
                    <select class="form-control" id="departmentId">
                        <option value="">请选择</option>
                        <c:forEach var="department" items="${departmentList}">
                            <option value="${department.id}">${department.name}</option>
                        </c:forEach>
                    </select>
                    <label class="control-label">机构</label>
                    <select class="form-control" id="branchId">
                        <option value="">请选择</option>
                        <c:forEach var="branch" items="${branchList}">
                            <option value="${branch.id}">${branch.name}</option>
                        </c:forEach>
                    </select>
                    <label class="control-label">岗位</label>
                    <select class="form-control" id="stationId">
                        <option value="">请选择</option>
                        <c:forEach var="station" items="${stationList}">
                            <option value="${station.id}">${station.name}</option>
                        </c:forEach>
                    </select>
                    <button class="btn btn-info" type="button" onclick="javascript:search();">查询</button>
                    <span style="margin-left: 30px; padding-bottom: 5px; margin-bottom: 5px;">没有合适策略？<a href="/paperpolicy/edit.do">去添加</a> </span>
                </div>

                <div class="" style="min-height: 300px;">
                    <table class="table table-bordered data-table">
                        <thead>
                        <tr>
                            <th></th>
                            <th>名称</th>
                            <th>机构</th>
                            <th>部门</th>
                            <th>岗位</th>
                            <th>试题信息</th>
                            <th>题量</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td colspan="7">请选择筛选条件！</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="button pull-right">
                    <button class="btn btn-warning btn-cancel" onclick="history.back();">上一步</button>
                    <div class="space">

                    </div>
                    <button class="btn btn-primary btn-commit">下一步</button>
                </div>
                <div class="row">

                </div>
            </form>
        </div>

        <div class="col-md-4">
            <div class="input-group">
                <div class="input-group-addon">已选策略</div>
                <input id="paperPolicyId" value="${scene.paperPolicyId}" type="hidden"/>
                <input id="policy" class="form-control" readonly value="${scene.paperPolicyId}" type="text">
            </div>
            <div class="form-group">
                <textarea id="content" class="form-control" rows="10" readonly style="background-color: beige"> </textarea>
            </div>
        </div>

    </div>
</div>
<script>
    function search() {
        var deptId = $("#departmentId").val();
        var branchId = $("#branchId").val();
        var stationId = $("#stationId").val();

        if (!deptId && !branchId && !stationId) {
            zeroModal.alert("请选择筛选条件");
            return false;
        }
        zeroModal.loading(3);
        $.ajax({
            url: '/paperpolicy/listAll.do',
            data: {departmentId: deptId, branchId: branchId, stationId: stationId},
            success: function (data) {
                if (data == '-1') {
                    zeroModal.closeAll();
                    zeroModal.error("获取试卷策略信息失败");
                } else {
                    console.log("data: ", data);

                    var paperPolicyList = JSON.parse(data);
                    var html = "";
                    $.each(paperPolicyList, function (index, val) {
                        console.log("index: ", index);
                        html += "<tr>";
                        html += '<td><input type="checkbox" name="paperPolicyId" value="' + val.id + '"></td>';
                        html += "<td>" + val.name + "</td>"
                        html += "<td>" + val.branchId + "</td>"
                        html += "<td>" + val.departmentId + "</td>"
                        html += "<td>" + val.stationId + "</td>"
                        html += "<td>" + val.content + "</td>"
                        html += "<td>" + val.count + "</td>"
                        html += "</tr>";
                    });
                    if (html == "") {
                        html = "<tr><td colspan='6'>查询无数据！</td></tr>";
                    }
                    console.log("html: ", html);
                    $(".data-table > tbody").html(html);
                    zeroModal.closeAll();
                }
            },
            error: function (data) {
                zeroModal.closeAll();
                zeroModal.error("获取试卷策略信息失败");
            }
        })
    }
    
    $(function () {
        $("table").on("click", "input[type='checkbox']", function () {
            if($(this).is(":checked")){
                $("#policy").val($(this).parentsUntil("tr").next().text());
                var paperPolicyId = $(this).val();
                $.ajax({
                    url: "/paperpolicy/getPolicyInfo.do",
                    data: {id: paperPolicyId},
                    success: function (data) {
                        $("#content").val(data);
                    },
                    error: function (data) {
                        swal("", "获取已选策略信息失败", "error");
                        return false;
                    }
                });
//                $("#content").val($(this).parentsUntil("tr").parent().find("td:eq(5)").text());
            }
        });

        /**
         * 如果已经选择试卷策略，获取信息
         * @type {*}
         */
        var paperPolicyId = $("#paperPolicyId").val();
        if(paperPolicyId){
            $.ajax({
                url: "/paperpolicy/getPolicyInfo.do",
                data: {id: paperPolicyId},
                success: function (data) {
                    $("#content").val(data);
                },
                error: function (data) {
                    swal("", "获取已选策略信息失败", "error");
                    return false;
                }
            })
        }

    })
</script>
</body>
</html>