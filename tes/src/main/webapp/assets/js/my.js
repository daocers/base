/**
 * Created by daocers on 2016/7/19.
 */


$(function () {
    /**
     * 全选
     */
    $(".selectAll").on("click", function () {
        var checked = $(this).prop('checked');
        console.log(checked);
        $("table").find("input[type='checkbox']").prop("checked", checked);
    }); 
})


/**
 * 如果查看详情，禁用掉所有的 按钮和输入框
 */
$(function () {
    var type = $("#type").val();
    alert(type)
    if (type == "detail") {
        $("input").attr("readonly", true);
        $("select").attr("readonly", true);
        $("button").attr("disabled", "disabled");
    }
});


$(function () {

    /**
     * 页面加载完成查看是否有异常信息，进行提示
     * @type {*}
     */
    var err = $("#err").text().trim();
    if(err && err != ''){
        zeroModal.error(err);
    }

    var msg = $("#msg").text().trim();
    if(msg && msg != ''){
        zeroModal.alert(msg);
    }


    /**
     * 初始化时间日期插件，默认是不显示时间，如果有需要可以进行个性化设置，data-date-format进行设置
     */
    $(".date").flatpickr();
})


