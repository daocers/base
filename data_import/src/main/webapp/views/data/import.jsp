<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>旧数据导入</title>
    <%@include file="../_template/header.jsp" %>
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
            <li><a href="#" class="active">旧数据导入</a> </li>
        </ol>
    </div>

    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" method="post" action="import.do"  enctype="multipart/form-data"  data-toggle="validator" role="form">

                <div class="form-group">
                    <div class="col-md-offset-1" style="padding-left: 15px;">
                        <input type="file" name="file" class="jfilestyle" data-input="true" data-buttonText="导入文件">
                        <button class="btn btn-success" onclick="javascript:commit()">上传</button>
                    </div>

                </div>

            </form>
        </div>

    </div>


    <div>
        <button class="btn btn-info" id="tongji">添加资产对标统计</button>
    </div>
</div>
<script>
    $("#tongji").on("click", function () {
        window.location.href = 'tongji.do';
    })
</script>
</body>
</html>