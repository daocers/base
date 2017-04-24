<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>首页|菜单在左</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <%--<link rel="icon" href="../../favicon.ico">--%>

    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/assets/css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        .navbar-inverse {
            border-radius: 0px;
        }

        .menu-left {
            min-width: 200px;
            max-width: 260px;

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
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">布谷培训</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="#">我的布谷</a></li>
                <li><a href="#">我是考生</a></li>
                <li><a href="#">我是教师</a></li>
                <!--<li><a href="#">培训学习</a></li>-->
                <!--<li><a href="#">统计分析</a></li>-->
                <!--<li><a href="#">考试管理</a></li>-->


            </ul>

            <ul class="nav navbar-nav navbar-right">
                <form class="navbar-form navbar-left form-inline" role="search">
                    <div class="input-group">
                        <input class="form-control">
                        <div class="input-group-btn">
                            <button class="btn btn-default">搜索</button>
                        </div>
                    </div>
                </form>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"> <span class="person-center"></span>
                        个人中心 <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">账户设置</a></li>
                        <li><a href="#">我的布谷</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">安全退出</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-0 col-md-2 sidebar menu-left">
            <ul id="accordion" class="accordion">
                <li>
                    <div class="link">系统管理<span class=" caret" style="float: right;"></span></div>
                    <ul class="submenu">
                        <li><a href="/user/list.do">用户管理</a></li>
                        <li><a href="/role/list.do">角色管理</a></li>
                        <li><a href="#">权限管理</a></li>
                        <li><a href="#">机构管理</a></li>
                        <li><a href="#">部门管理</a></li>
                    </ul>
                </li>
                <li>
                    <div class="link"><i class="fa fa-code"></i>场次管理<i class="fa fa-chevron-down"></i></div>
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
                        <li><a href="#">交易题管理</a></li>
                        <li><a href="#">知识题管理</a></li>
                        <li><a href="#">交易录制</a></li>
                        <li><a href="#">翻打凭条管理</a></li>

                    </ul>
                </li>
                <li>
                    <div class="link"><i class="fa fa-globe"></i>考试设置<i class="fa fa-chevron-down"></i></div>
                    <ul class="submenu">
                        <li><a href="#">试题策略管理</a></li>
                        <li><a href="#">试卷策略管理</a></li>
                        <li><a href="#">试卷管理</a></li>
                        <li><a href="#"></a></li>
                    </ul>
                </li>
            </ul>
            <!--<ul class="nav nav-sidebar">-->
            <!--<li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li>-->
            <!--<li><a href="#">Reports</a></li>-->
            <!--<li><a href="#">Analytics</a></li>-->
            <!--<li><a href="#">Export</a></li>-->
            <!--</ul>-->
            <!--<ul class="nav nav-sidebar">-->
            <!--<li><a href="">Nav item</a></li>-->
            <!--<li><a href="">Nav item again</a></li>-->
            <!--<li><a href="">One more nav</a></li>-->
            <!--<li><a href="">Another nav item</a></li>-->
            <!--<li><a href="">More navigation</a></li>-->
            <!--</ul>-->
            <!--<ul class="nav nav-sidebar">-->
            <!--<li><a href="">Nav item again</a></li>-->
            <!--<li><a href="">One more nav</a></li>-->
            <!--<li><a href="">Another nav item</a></li>-->
            <!--</ul>-->
        </div>
        <div class="col-sm-12 col-sm-offset-0 col-md-10 col-md-offset-2 main" id="main">
            <h1 class="page-header">Dashboard</h1>

            <div class="row placeholders">
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                         width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">Something else</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                         width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">Something else</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                         width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">Something else</span>
                </div>
                <div class="col-xs-6 col-sm-3 placeholder">
                    <img src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                         width="200" height="200" class="img-responsive" alt="Generic placeholder thumbnail">
                    <h4>Label</h4>
                    <span class="text-muted">Something else</span>
                </div>
            </div>

            <h2 class="sub-header">Section title</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Header</th>
                        <th>Header</th>
                        <th>Header</th>
                        <th>Header</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,002</td>
                        <td>amet</td>
                        <td>consectetur</td>
                        <td>adipiscing</td>
                        <td>elit</td>
                    </tr>
                    <tr>
                        <td>1,003</td>
                        <td>Integer</td>
                        <td>nec</td>
                        <td>odio</td>
                        <td>Praesent</td>
                    </tr>
                    <tr>
                        <td>1,003</td>
                        <td>libero</td>
                        <td>Sed</td>
                        <td>cursus</td>
                        <td>ante</td>
                    </tr>
                    <tr>
                        <td>1,004</td>
                        <td>dapibus</td>
                        <td>diam</td>
                        <td>Sed</td>
                        <td>nisi</td>
                    </tr>
                    <tr>
                        <td>1,005</td>
                        <td>Nulla</td>
                        <td>quis</td>
                        <td>sem</td>
                        <td>at</td>
                    </tr>
                    <tr>
                        <td>1,006</td>
                        <td>nibh</td>
                        <td>elementum</td>
                        <td>imperdiet</td>
                        <td>Duis</td>
                    </tr>
                    <tr>
                        <td>1,007</td>
                        <td>sagittis</td>
                        <td>ipsum</td>
                        <td>Praesent</td>
                        <td>mauris</td>
                    </tr>
                    <tr>
                        <td>1,008</td>
                        <td>Fusce</td>
                        <td>nec</td>
                        <td>tellus</td>
                        <td>sed</td>
                    </tr>
                    <tr>
                        <td>1,009</td>
                        <td>augue</td>
                        <td>semper</td>
                        <td>porta</td>
                        <td>Mauris</td>
                    </tr>
                    <tr>
                        <td>1,010</td>
                        <td>massa</td>
                        <td>Vestibulum</td>
                        <td>lacinia</td>
                        <td>arcu</td>
                    </tr>
                    <tr>
                        <td>1,011</td>
                        <td>eget</td>
                        <td>nulla</td>
                        <td>Class</td>
                        <td>aptent</td>
                    </tr>
                    <tr>
                        <td>1,012</td>
                        <td>taciti</td>
                        <td>sociosqu</td>
                        <td>ad</td>
                        <td>litora</td>
                    </tr>
                    <tr>
                        <td>1,013</td>
                        <td>torquent</td>
                        <td>per</td>
                        <td>conubia</td>
                        <td>nostra</td>
                    </tr>
                    <tr>
                        <td>1,014</td>
                        <td>per</td>
                        <td>inceptos</td>
                        <td>himenaeos</td>
                        <td>Curabitur</td>
                    </tr>
                    <tr>
                        <td>1,015</td>
                        <td>sodales</td>
                        <td>ligula</td>
                        <td>in</td>
                        <td>libero</td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <footer class="footer">
                <div class="container text-center">
                    @bugu.co <a href="#">联系我们</a>
                </div>
            </footer>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/jquery-2.2.0.min.js"><\/script>')</script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="/assets/js/holder.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/assets/js/ie10-viewport-bug-workaround.js"></script>

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


        $(".menu-left .submenu li a").on("click", function () {
            $(".menu-left a").removeClass("active");
            $(this).addClass("active");
            var url = $(this).attr("href");
            if (!url || url == "#" || url == '') {
                return false;
            }
            $("#main").load(url);
            return false;
        })

    })
</script>
</body>
</html>
