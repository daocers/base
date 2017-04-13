<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>mq发送消息</title>
    <%@include file="../_template/header.jsp" %>
    <style>
    </style>
</head>
<body>
<div class="container">

    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" method="post" action="import.do"  enctype="multipart/form-data"  data-toggle="validator" role="form">

                <div class="form-group">
                    <label class="control-label col-md-2">NameServerAddr</label>
                    <div class="col-md-5">
                        <input class="form-control" type="text" name="nameServerAddr" placeholder="输入nameServer地址">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Topic</label>
                    <div class="col-md-5">
                        <input  class="form-control" type="text" name="topic" placeholder="输入topic">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Tags</label>
                    <div class="col-md-5">
                        <input class="form-control"  type="text" name="tags" placeholder="输入tags">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-2">Topic</label>
                    <div class="col-md-5">
                        <textarea rows="10" class="form-control" name="body">

                        </textarea>
                    </div>
                </div>


                <div class="form-group">
                    <label class="control-label col-md-2">发送结果</label>
                    <div class="col-md-5">
                        <textarea rows="5" class="form-control" id="result">

                        </textarea>
                    </div>
                </div>

                <div>
                    <button class="btn btn-info" id="send">发送</button>
                </div>

            </form>
        </div>

    </div>
</div>
<script>
</script>
</body>
</html>