<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>录制交易</title>
    <%@ include file="../template/header.jsp" %>
    <%@ include file="../template/menu-top.jsp" %>
</head>
<body>

<div class="container">

    <%--<%@ include file="../template/menu-left.jsp"%>--%>
    <div class="nav-path" style="margin-left: -30px;">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#" class="active">交易录制</a></li>
        </ol>
    </div>
    <div style="width:900px; vertical-align: top; display: inline-block">
        <div class="row">
            <div class="row menu-bar form-inline" style="margin-bottom: 15px">
                <div class="input-group">
                    <div class="input-group-addon">输入网址</div>
                    <input class="form-control col-md-2" name="url">
                    <div class="input-group-btn">
                        <button class="btn btn-info" id="jump">确定</button>
                    </div>
                </div>

            </div>
            <div class="page-container" style="margin-left: -15px;">
                <iframe id="page" src="trade.html" seamless="seamless" width="1200px" style="border-right: none">

                </iframe>
            </div>
            <div class="row">
                <button class="btn btn-info" id="get">获取交易信息</button>
                <button class="btn btn-success" id="getPage">保存当前交易页面</button>
            </div>
            <!--<div class="row">-->


            <div class="info-container col-md-7" style="margin-left: -30px;">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">我录入的信息</h3>
                    </div>
                    <table class="table table-bordered" id="fieldInfo">
                        <thead>
                        <tr>
                            <th class="hidden">name</th>
                            <th class="col-md-3">名称</th>
                            <th class="col-md-6">我的录入</th>
                            <th class="col-md-2">参考栏位</th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>


            </div>
            <div class="col-md-5">
                <form class="form form-horizontal">
                    <div class="form-group">
                        <label class="control-label col-md-3">交易名称</label>
                        <div class="col-md-9">
                            <input type="text" name="name" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">交易码</label>
                        <div class="col-md-9">
                            <input type="text" name="code" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">场景描述</label>
                        <div class="col-md-9">
                            <textarea name="description" class="form-control" rows="10"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <button id="save" class="btn btn-info btn-commit col-md-offset-3">保存试题</button>
                    </div>
                </form>
            </div>
            <!--</div>-->
        </div>

    </div>
    <script>

        $(function () {
            /**
             * iframe加载完毕后对高度和宽度进行处理
             * */
            $("#page").on("load", function () {
                console.log("iframe loaded。。。");
                $("#get").trigger("click");
                var iframe = $("iframe")[0].contentDocument;
                var $iframe = $(iframe);

                $iframe.on("dblclick", "input, select", function () {
                    console.log("double click");
                });
//            console.log($iframe.height)
                var height = $("iframe")[0].contentWindow.document.documentElement.scrollHeight;
                console.log("height:", height)

                //另外一种获取height的方法
                height = $("#page").contents().find("html").height();
                console.log("height:", height)
                var width = $("#page").contents().find("html").width();
                console.log("width: ", width);
                $("#page").width(width);

                $("#page").height(height + 20);
            })
            $("#get").on("click", function () {
                var iframe = $("iframe")[0].contentDocument;
                var $iframe = $(iframe);
                var buffer = "";
                $iframe.find("input, select").each(function (idx, obj) {
                    var label = $(obj).prev().text();
                    var name = $(obj).attr("name");
                    var val = $(obj).val();
                    var type = $(obj).type;
                    console.log("type: ", type);
                    if($(obj).type == "select"){

                    }
                    var text = $(obj).text();
//                console.log("label: ", label);
//                console.log("idx: ", idx);
//                console.log("obj: ", $(obj).val());
                    buffer += "<tr>";
                    buffer += "<td class='hidden'>" + name + "</td>" + "<td class='hidden'>" + val + "</td>" + "<td>" + label + "</td><td>" + text +
                        "</td><td style='padding: 0px;'><input class='checkbox' style='height:' type='checkbox'></td>"
                    buffer += "</tr>";
                });
                $("#fieldInfo").find("tbody").html(buffer);
            })

            $("#jump").on("click", function () {
                var url = $("[name='url']").val();
                console.log("url: ", url);
                $("#page").attr("src", url);
            })

            $("#getPage").on("click", function () {
                var html = $("#page").contents().find("html").html();
//            html = $("#page")[0].contentDocument;
//            console.log("html", html);
                html = "<html>" + html + "</html>";
                console.log("iframe", html);
                $.ajax({
                    url: "savePage.do",
                    type: 'post',
                    data: {"info": html},
                    success: function (data) {
                        var res = JSON.parse(data);
                        if (res.code == 0) {
                            console.log("save successed");
                        } else {
                            console.log("save error");
                        }
                    },
                    error: function (data) {
                        console.log("save error");
                    }
                })
            })
        })

    </script>

</div>
</body>
</html>
