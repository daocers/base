<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>布谷考培|用户列表</title>
    <%@ include file="../template/header.jsp" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">


</head>

<body>

<%@ include file="../template/menu-top.jsp" %>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-0 col-md-2 sidebar menu-left">
            <%@ include file="../template/menu-left.jsp" %>
        </div>
        <div class="col-sm-12 col-sm-offset-0 col-md-10 col-md-offset-2 main" id="main">
            <%--<h1 class="page-header">Dashboard</h1>--%>
            <div class="page-header nav-path">
                <ol class="breadcrumb">
                    <li><a href="#">首页</a></li>
                    <li><a href="#" class="active">用户列表</a></li>
                </ol>
            </div>

            <div class="container-fluid">

                <div class=" pre-table">
                    <div class="pull-left form-inline">
                        <button class="btn btn-info" id="import">导入数据</button>
                        <button class="btn btn-primary" id="download">下载模板</button>
                    </div>

                </div>
                <div class="row">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th><input type="checkbox" class="selectAll"></th>
                            <th>branchId</th>
                            <th>departmentId</th>
                            <th>password</th>
                            <th>stationId</th>
                            <th>status</th>
                            <th>username</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pi.data}" var="user" varStatus="line">
                            <tr>
                                <td><input type="checkbox" objId="${user.id}"></td>
                                <td>${user.branchId}</td>
                                <td>${user.departmentId}</td>
                                <td>${user.password}</td>
                                <td>${user.stationId}</td>
                                <td>${user.status}</td>
                                <td>${user.username}</td>
                                <td>
                                    <a href="edit.do?id=${user.id}&type=detail" class="opr">详情</a>
                                    <a href="edit.do?id=${user.id}" class="opr">修改</a>
                                    <a href="javascript:del(${user.id})" class="opr">删除</a>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>

                <div class="after-table">
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

            <div class="modal fade container" id="modal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal"><span
                                    aria-hidden="true">&times;</span><span
                                    class="sr-only">Close</span></button>
                            <h4 class="modal-title">选择文件</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row" style="margin-left: 10px;">
                                <form id="upload" class="form-horizontal" method="post" action="batchAdd.do"
                                      enctype="multipart/form-data">
                                    <input type="file" name="file" class="jfilestyle" data-input="true"
                                           data-buttonText="导入文件">
                                    <button class="btn btn-success" onclick="javascript:upload()">上传</button>
                                    ？ 没有模板文件<a href="javascript:downloadModel()">点击下载</a>模板
                                </form>
                            </div>
                        </div>
                        <%--<div class="modal-footer">--%>
                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                        <%--<button type="button" class="btn btn-primary">Save changes</button>--%>
                        <%--</div>--%>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div>
            <script>
                $(function () {
                    $("#import").on("click", function () {
                        $("#modal").modal('show');
                    });
                    $("#download").on("click", function () {
                        window.location.href = "download.do";
                    })
                })

                function downloadModel() {
                    window.location.href = 'download.do';
                    $("#modal").modal("hide");
                }

                function upload() {
                    zeroModal.loading(3);
                    $("#upload").submit();
                }

            </script>
        </div>
    </div>
</div>


<%--此处必须单独写在此处，解决无法生效的问题--%>
<script src="/assets/js/menu.js"></script>
<script></script>
</body>
</html>
