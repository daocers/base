<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>数字录入编辑</title>
    <%@ include file="../template/header.jsp" %>

</head>
<body>
<div class="container">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#">数字录入</a></li>
            <li><a href="#" class="active">编辑</a></li>
        </ol>
    </div>
    <input type="hidden" value="${type}" id="type">
    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" method="post" action="save.do" data-toggle="validator" role="form">
                <input id="id" type="hidden" name="id" value="${typeIn.id}">

                <div class="form-group">
                    <label class="control-label col-md-2">凭条名称</label>
                    <div class="col-md-10">
                        <input class="form-control" type="text" name="name" value="${typeIn.name}" required>
                        <span class="help-block with-errors">输入凭条名称，便于记忆</span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-md-2">凭条内容</label>
                    <div class="col-md-10">
                        <table class="table table-bordered  table-editable">
                            <thead>
                            <tr>
                                <td class="col-md-2">序号</td>
                                <td class="col-md-5">数字</td>
                            </tr>
                            </thead>
                        </table>
                        <div style="max-height: 300px; height: 300px; overflow-x: scroll;">
                            <table class="table table-bordered  table-editable">
                                <tbody>

                                </tbody>
                            </table>
                        </div>

                        <input class="form-control" type="hidden" name="content" value="${typeIn.content}" required>
                    </div>
                </div>

                <div class="button pull-right">
                    <button class="btn btn-primary btn-commit">保存</button>
                    <div class="space">

                    </div>
                    <button class="btn btn-warning btn-cancel">取消</button>
                </div>
            </form>
        </div>

    </div>
</div>
<script>

    $(function () {
        var numbers = eval(${typeIn.content});
        var buffer = "";
        if (numbers && numbers.length > 0) {
            $.each(numbers, function (idx, num) {
                buffer += "<tr><td class='col-md-2'>" + (idx + 1) + "</td><td class='cell-edit col-md-5'><input class='form-control form-control-intable' type='number' step='0.01' ' value=' " + num + "'</td></tr>"
            });
        } else {
            for (var i = 0; i < 100; i++) {
                buffer += "<tr><td class='col-md-2'>" + (i + 1) + "</td><td class='cell-edit col-md-5'><input class='form-control form-control-intable'  type='number' step='0.01'  value=''></td></tr>"
            }
        }


        $("table tbody").html(buffer);

        $(".btn-commit").on("click", function () {
            var array = new Array();
            $("table tbody tr input").each(function (idx, obj) {
                console.log("idx: ", idx);
                console.log("obj: ", obj);

                var num = $(obj).val();
                if (num == '') {
                    $(obj).parent().addClass("has-error");
                    console.log("return false");
                    return false;
                }
                array.push(num);
            });
            if (array.length != 100) {
                swal("", "请录入数字", "info");
                return false;
            }
            $("[name='content']").val(JSON.stringify(array));
            console.log(JSON.stringify(array));
        })

//        $("table tbody input").on("input propertychange", function () {
//            console.log("change");
//            var val = $(this).val();
//            $(this).val(fmoney(val));
//        })
    })

    function fmoney(s, n)
    {
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
        var l = s.split(".")[0].split("").reverse(),
            r = s.split(".")[1];
        t = "";
        for(i = 0; i < l.length; i ++ )
        {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
        }
        return t.split("").reverse().join("") + "." + r;
    }
</script>
</body>
</html>
