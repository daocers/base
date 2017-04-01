<%--
  Created by IntelliJ IDEA.
  User: daocers
  Date: 2016/7/14
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>菜单栏</title>
    <%--<link rel="stylesheet" href="../asset/css/bootstrap.css">--%>
    <%--<script src="../asset/js/jquery-2.2.0.min.js"></script>--%>
    <%--<script src="../asset/js/bootstrap.js"></script>--%>
    <script>
        var Accordion = function (e, multiple) {
            this.e = e || {};
            this.multiple = multiple || false;
            var links = this.e.find(".link");
            links.on("click", {e: this.e, multiple: this.multiple}, this.dropdown)
        }

        Accordion.prototype.dropdown = function (e) {
//            $(".accordion li.open").removeClass("open");
            var $e = e.data.e;
            $this = $(this),
                $next = $this.next();

            $next.slideToggle();
            $this.parent().toggleClass('open');

            if (!e.data.multiple) {
                $e.find('.submenu').not($next).slideUp().parent().removeClass('open');
            }
            ;
        }

        $(function () {
            var accordion = new Accordion($('#accordion'), false);
        })
    </script>
    <style>
        * {
            margin: 0;
            padding: 0;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
        }

        /*body {*/
            /*!*background: #2d2c41;*!*/
            /*font-family: "Microsoft YaHei UI";*/
        /*}*/

        ul.accordion, .accordion ul {
            list-style-type: none;
        }


        /** =======================
         * Contenedor Principal
         ===========================*/
        .accordion {
            border: 1px solid gainsboro;
            width: 100%;
            max-width: 360px;
            margin: 30px auto 20px;
            background: #FFF;
            -webkit-border-radius: 4px;
            -moz-border-radius: 4px;
            border-radius: 4px;
        }

        .accordion .link {
            cursor: pointer;
            display: block;
            padding: 15px 15px 15px 42px;
            color: darkgray;
            /*background-color: #8ed7ff;*/
            font-size: 15px;
            font-weight: 400;
            border-bottom: 1px solid #CCC;
            position: relative;
            -webkit-transition: all 0.4s ease;
            -o-transition: all 0.4s ease;
            transition: all 0.4s ease;
        }

        .accordion .link:hover {
            color: darkblue;
        }

        .accordion li:last-child .link {
            border-bottom: 0;
        }

        .accordion li i {
            position: absolute;
            top: 16px;
            left: 12px;
            font-size: 18px;
            /*color: #595959;*/
            color: #8ed7ff;
            -webkit-transition: all 0.4s ease;
            -o-transition: all 0.4s ease;
            transition: all 0.4s ease;
        }

        .accordion li i.fa-chevron-down {
            right: 12px;
            left: auto;
            font-size: 16px;
        }

        .accordion li.open .link {
            color: darkblue;
            background-color: #dbf1ff;
        }

        .accordion li.open i {
            color: #b63b4d;
        }

        .accordion li.open i.fa-chevron-down {
            -webkit-transform: rotate(180deg);
            -ms-transform: rotate(180deg);
            -o-transform: rotate(180deg);
            transform: rotate(180deg);
        }

        /**
 * Submenu
 -----------------------------*/
        .submenu {
            display: none;
            /*background: #dbf1ff;*/
            /*background: #8ed7ff;*/
            font-size: 14px;
        }

        .submenu li {
            /*padding-left: 20px;*/
            border-bottom: 1px solid whitesmoke;
        }

        .submenu li:last-child {
            border-bottom-color: #ccc;
        }

        .submenu a {
            display: block;
            text-decoration: none;
            color: silver;
            padding: 12px;
            padding-left: 60px;
            -webkit-transition: all 0.25s ease;
            -o-transition: all 0.25s ease;
            transition: all 0.25s ease;
        }

        .submenu a:hover {
            background: aliceblue;
            color: grey;
        }
    </style>
</head>
<body>
<div class="row">
    <div class="menu">
        <ul id="accordion" class="accordion">
            <li>
                <div class="link"><i class="fa fa-paint-brush"></i>用户中心<i class="fa fa-chevron-down"></i></div>
                <ul class="submenu">
                    <li><a href="/user/list.do">用户列表</a></li>
                    <li><a href="">修改密码</a></li>
                    <li><a href="/user/resetPassword.do">重置密码</a></li>
                </ul>
            </li>
            <li>
                <div class="link"><i class="fa fa-code"></i>考试管理<i class="fa fa-chevron-down"></i></div>
                <ul class="submenu">
                    <li><a href="#">我参加的</a></li>
                    <li><a href="#">我创建的</a></li>
                    <li><a href="#">我要考试</a></li>
                </ul>
            </li>
            <li>
                <div class="link"><i class="fa fa-mobile"></i>试题管理<i class="fa fa-chevron-down"></i></div>
                <ul class="submenu">
                    <li><a href="#">题型设置</a></li>
                    <li><a href="#">题目管理</a></li>
                    <li><a href="#">试卷管理</a></li>
                    <li><a href="#">Otros dispositivos</a></li>
                </ul>
            </li>
            <li>
                <div class="link"><i class="fa fa-globe"></i>考试<i class="fa fa-chevron-down"></i></div>
                <ul class="submenu">
                    <li><a href="#">Google</a></li>
                    <li><a href="#">Bing</a></li>
                    <li><a href="#">Yahoo</a></li>
                    <li><a href="#">Otros buscadores</a></li>
                </ul>
            </li>
        </ul>
    </div>

</div>
</body>
</html>