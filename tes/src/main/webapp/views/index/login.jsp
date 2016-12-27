<%--
  Created by IntelliJ IDEA.
  User: daocers
  Date: 2016/8/16
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<html>
<head>
    <title>登录</title>
    <style>
        .form-group{
            width: 300px;
            float: right;
        }
        .pass-span{
            position: absolute;
            right: 1px;
            top: 1px;
            padding-left: 10px;
            padding-right: 10px;
            line-height: 33px;
        }
        .pass-span:hover{
            cursor: pointer;
            background-color: gainsboro;
        }
        .pass-span > a:link{
            text-decoration: none;
        }
        .login{
            margin-right: 10px;
        }
        .qrcode{
            margin-left: 10px;

        }
    </style>
</head>

<body>
<div class="container">
    <div class="row text-center">
        用户登录
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="pull-right login">
                <div class="form-group">
                    <span>账号登录</span>
                    <span class="pull-right">还没有账号？<a href="#">快速注册</a> </span>
                </div>
                <div class="form-group">
                    <input class="form-control" name="username" placeholder="用户名/员工号">
                </div>
                <div class="form-group">
                    <div class="inline-box" style="position: relative">
                        <input class="form-control inline-left" name="password" placeholder="密码">
                        <span class="pass-span"><a href="forget.do">忘记密码</a></span>
                    </div>

                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <label><input type="checkbox">记住我</label>
                    </div>
                </div>
                <div class="form-group">
                    <button class="btn btn-info btn-block">登录</button>
                </div>
            </div>
        </div>
        <div class="col-md-6 ">
            <div class="pull-left qrcode">
                <img height="200px" width="200px" class="img-rounded" src="../assets/images/gallery/image-1.jpg">
            </div>
        </div>
    </div>
</div>
</body>
</html>
