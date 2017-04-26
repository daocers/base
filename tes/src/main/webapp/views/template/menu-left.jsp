<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--<meta charset="UTF-8">--%>
<%--<title>菜单</title>--%>

<%--</head>--%>
<%--<body>--%>
<%--<div class="menu-left" style="display: inline-block;">--%>
<style>

    .navbar-inverse {
        border-radius: 0px;
    }

    .menu-left {
        /*min-width: 200px;*/
        /*max-width: 260px;*/

        /*width: 260px;*/
        padding-left: 0px;
        padding-right: 0px;
        margin-top: -20px;
    }

    .menu-left * {
        background-color: #f7f7f7;
        margin: 0;
        padding: 0;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }

    ul.accordion {
        list-style-type: none;
    }

    /** =======================
     * Contenedor Principal
     ===========================*/
    .accordion {
        border: 1px solid gainsboro;
        /*width: 100%;*/
        /*max-width: 360px;*/
        margin: 0 0px 20px 0px;
        background: ghostwhite;
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
        border-left: 3px solid dodgerblue;
        background-color: white;
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
        color: cornflowerblue;
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
        /*background-color: #dbf1ff;*/
        border-left: 3px solid dodgerblue;
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
        background: white;
        color: grey;
    }

    .submenu a.active {
        background: white;
        border-right: 2px solid powderblue;;
    }

</style>
<ul id="accordion" class="accordion">
    <li>
        <div class="link">系统管理<span class=" caret" style="float: right;"></span></div>
        <ul class="submenu">
            <li><a href="/user/list.do">用户管理</a></li>
            <li><a href="/role/list.do">角色管理</a></li>
            <li><a href="/authority/list.do">权限管理</a></li>
            <li><a href="/branch/list.do">机构管理</a></li>
            <li><a href="/department/list.do">部门管理</a></li>
        </ul>
    </li>
    <li>
        <div class="link"><i class="fa fa-code"></i>场次管理<i class="fa fa-chevron-down"></i></div>
        <ul class="submenu">
            <li><a href="/scene/list.do?type=join">我参加的</a></li>
            <li><a href="/scene/list.do?type=my">我创建的</a></li>
            <li><a href="/exam/index.do">我要考试</a></li>
        </ul>
    </li>
    <li>
        <div class="link"><i class="fa fa-mobile"></i>试题管理<i class="fa fa-chevron-down"></i></div>
        <ul class="submenu">
            <li><a href="/questionMetaInfo/list.do">题型设置</a></li>
            <li><a href="/question/list.do">题目管理</a></li>
            <li><a href="/tradeQuestion/list.do">交易题管理</a></li>
            <li><a href="#">知识题管理</a></li>
            <li><a href="/trade/toRecord.do">交易录制</a></li>
            <li><a href="#">翻打凭条管理</a></li>

        </ul>
    </li>
    <li>
        <div class="link"><i class="fa fa-globe"></i>考试设置<i class="fa fa-chevron-down"></i></div>
        <ul class="submenu">
            <li><a href="/questionPolicy/list.do">试题策略管理</a></li>
            <li><a href="/paperPolicy/list.do">试卷策略管理</a></li>
            <li><a href="/paper/list.do">试卷管理</a></li>
            <li><a href="#"></a></li>
        </ul>
    </li>
</ul>

<div class="hidden" id="currentUrl">
    <%=session.getAttribute("currentUrl")%>
</div>


<%--以下的script标签不要修改位置，否则可能造成不生效--%>
<script src="/assets/js/menu.js"></script>

<script>
    $(function () {
        console.log("begin")
        var cuttentUrl = $("#currentUrl").text().trim();
        var $curLi = $("li a[href='" + cuttentUrl + "']");
        $curLi.addClass("active");
        $curLi.parents(".accordion > li").addClass("open");
        $curLi.parents("ul.submenu").attr("style", "display: block;");
    })
</script>