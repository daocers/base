<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>布谷考培|首页</title>
    <%@ include file="../template/header.jsp" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">

    <style>
        .menu-item:hover{
            cursor: pointer;
        }
        .menu-item{
            padding: 15px;
            border: 1px solid white;
            background-color: #78c9ff;
        }
        .menu-item .top .left{
            display: inline-block;
            width: 160px;
            vertical-align: bottom;
            font-size: 24px;
            padding-left: 10px;
        }
        .menu-item .top .right{
            display: inline-block;
        }
        .menu-item .bottom{
            height: 35px;
            font-size: 16px;
            margin-top: 10px;
            padding-left: 10px;
        }
    </style>
</head>

<body>

<%@ include file="../template/menu-top.jsp" %>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-0 col-md-2 sidebar menu-left">
            <%@ include file="../template/menu-left.jsp" %>
        </div>
        <div class="col-sm-12 col-sm-offset-0 col-md-10 col-md-offset-2 main" id="main">
            <div class="menu-item col-md-3">
                <div class="top">
                    <div class="left">
                        开场
                    </div>
                    <div class="right">
                        <img src="K线图.png">
                    </div>
                </div>

                <div class="bottom">
                    添加信息发的离开发发达懒
                </div>
            </div>
            <div class="menu-item col-md-3">
                <div class="top">
                    <div class="left">
                        添加信息
                    </div>
                    <div class="right">
                        <img src="K线图.png">
                    </div>
                </div>

                <div class="bottom">
                    添加信息发的离开发发达懒
                </div>
            </div>
            <div class="menu-item col-md-3">
                <div class="top">
                    <div class="left">
                        添加信息
                    </div>
                    <div class="right">
                        <img src="K线图.png">
                    </div>
                </div>

                <div class="bottom">
                    添加信息发的离开发发达懒
                </div>
            </div>

        </div>
    </div>
</div>

<script></script>
</body>
</html>
