<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>答题</title>
</head>
<body>
<div class="container">
    <div class="row nav-path">
        <ol class="breadcrumb">
            <li><a href="#">首页</a></li>
            <li><a href="#">考试管理</a></li>
            <li><a href="#" class="active">答题</a></li>
        </ol>
    </div>
    <input type="hidden" value="${type}" id="type">
    <div class="row">
        <div class="info">
            <div class="form form-inline pull-left">
                <div class="input-group">
                    <div class="input-group-addon">
                        考试名称：
                    </div>
                    <input class="form-control col-md-1" style="width: 80px;" readonly type="text" value="三综合考试第一场">
                </div>
                <div class="input-group">
                    <div class="input-group-addon">
                        更换另外一套试卷
                    </div>
                    <div class="input-group-btn">
                        <button class="btn btn-danger" onclick="alert('确定后本套试卷将失效，答题信息作废，且不能更换回本套试卷，确定更换？')">确定</button>
                    </div>
                </div>
                <div class="input-group">
                    <div class="input-group-addon">
                        题目：
                    </div>
                    <select class="form-control">
                        <option value="1">单选第一题</option>
                        <option value="2">单选第二题</option>
                        <option value="3">单选第三题</option>
                        <option value="4">多选第一题</option>
                        <option value="5">判断第一题</option>
                    </select>
                </div>
            </div>
            <div class="form form-inline pull-right">
                <div class="input-group">
                    <div class="input-group-addon">剩余时间：</div>
                    <input type="text" class="timer form-control" value="00小时11分28秒" readonly style="width: 150px;">
                </div>
            </div>
        </div>
        <div class="row container">
            <div class="col-md-8">
                <div class="form form-horizontal">
                    <div class="form-group">
                        <!--<label class="control-label">题目</label>-->
                        <div class="col-md-">
                                <textarea class="form-control" readonly style="min-height: 500px;">我国最大的银行是？
                                    A:工商银行;
                                    B:交通银行;
                                    C:建设银行;
                                    D:农业银行;
                                </textarea>

                        </div>
                    </div>
                    <div class="form-group">
                        <div class="radio-inline">
                            <label><input type="radio">A</label>
                        </div>
                        <div class="radio-inline">
                            <label><input type="radio">B</label>
                        </div>
                        <div class="radio-inline">
                            <label><input type="radio">C</label>
                        </div>
                        <div class="radio-inline">
                            <label><input type="radio">D</label>
                        </div>
                        <div class="radio-inline">
                            <label><input type="radio">E</label>
                        </div>
                    </div>
                    <div class="row pull-right">
                        <button class="btn btn-danger">上一题</button>
                        <div class="space" style="display: inline-block; margin-right: 200px;">

                        </div>
                        <button class="btn btn-primary">下一题</button>
                    </div>

                </div>
            </div>
            <div class="col-md-3 pull-right">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>题号</th>
                        <th>我的答案</th>
                        <th>最佳答案</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><a href="#">单选1</a> </td>
                        <td>c</td>
                        <td>B</td>
                    </tr>
                    <tr>
                        <td><a href="#">单选1</a> </td>
                        <td>c</td>
                        <td>B</td>
                    </tr>
                    <tr>
                        <td><a href="#">单选1</a> </td>
                        <td>c</td>
                        <td>B</td>
                    </tr>
                    <tr>
                        <td><a href="#">单选1</a> </td>
                        <td>c</td>
                        <td>B</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script>

</script>
</body>
</html>
