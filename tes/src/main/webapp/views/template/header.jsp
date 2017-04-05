<%--
  Created by IntelliJ IDEA.
  User: daocers
  Date: 2016/7/6
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="../assets/css/bootstrap.css" rel="stylesheet" >
    <script src="../assets/js/jquery-2.2.0.min.js"></script>
    <script src="../assets/js/bootstrap.js"></script>
    <script src="../assets/js/bugu.extend.js"></script>

    <link href="../assets/css/bootstrap-switch.css" rel="stylesheet">
    <script src="../assets/js/bootstrap-switch.js"></script>

    <link href="../assets/css/jquery.filer.css" type="text/css" rel="stylesheet"/>
    <link href="../assets/css/jquery.filer-dragdropbox-theme.css" type="text/css" rel="stylesheet"/>
    <script src="../assets/js/jquery.filer.js"></script>
    
    <link href="../assets/css/kacha-table-editable.css" rel="stylesheet"/>
    <script src="../assets/js/bugu.editable-table.js"></script>

    <script src="../assets/js/eModal.js"></script>
    
    <%--<script src="../assets/js/bootstrap-datetimepicker.min.js"></script>--%>
    <%--<script src="../assets/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>--%>
    <%--<link href="../assets/css/bootstrap-datetimepicker.min.css" rel="stylesheet" >--%>

    <%--<link href="../assets/css/kacha.css" rel="stylesheet">--%>


    <%--表单校验--%>
    <script src="../assets/js/validator.js"></script>

    <%--b表单优化插件--%>
    <link rel="stylesheet" href="../assets/css/jquery-filestyle.css">
    <script src="../assets/js/jquery-filestyle.min.js"></script>

    <%--定时器--%>
    <%--<link rel="stylesheet" href="../assets/css/jquery.syotimer.css">--%>
    <%--<script src="../assets/js/jquery.syotimer.js"></script>--%>

    <%--弹出框--%>
    <link rel="stylesheet" href="../assets/css/sweetalert2.css">
    <script src="../assets/js/sweetalert2.js"></script>

    <%--另外一个定时器--%>
    <%--<script src="../assets/js/jquery.countdown.js"></script>--%>


    <%--美化checkbox和radio--%>
    <link href="../assets/css/square/blue.css" rel="stylesheet">
    <script src="../assets/js/icheck.min.js"></script>


    <%--模态对话框--%>
    <link href="../assets/css/zeroModal.css" rel="stylesheet">
    <script src="../assets/js/zeroModal.min.js"></script>


    <%--日期选择框--%>
    <link href="../assets/css/flatpickr.material_blue.min.css" rel="stylesheet">
    <script src="../assets/js/flatpickr.min.js"></script>
    <script src="../assets/js/flatpickr.l10n.zh.js"></script>

    <script src="../assets/js/timer.jquery.min.js"></script>


<%--自定义的js和css，因为依赖其他js，故放在最后--%>
    <link href="../assets/css/my.css" rel="stylesheet">
    <script src="../assets/js/page-nav.js"></script>
    <script src="../assets/js/my.js"></script>
    <script src="../assets/js/common.js"></script>
    <script>
        /**
         * 修改swal默认设置
         */
        swal.setDefaults({
            confirmButtonText: '确定',
            cancelButtonText: "取消"
        });
    </script>
</head>
<body>

    <div class="hide" id="err">
        ${err}
    </div>
    <div class="hide" id="msg">
        ${msg}
    </div>


</body>
</html>
