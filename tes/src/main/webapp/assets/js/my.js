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
    $(".date").flatpickr({
        dateFormat: 'yyyy-mm-dd'
    });

    $(".time").flatpickr({
        dataFormat: 'yyyy-mm-dd HH:ii:SS',
        enableTime: true,
        enableSeconds: true,
        minuteIncrement: 1,
    })
})


/**
 * 扩展jqeury，添加设置时间和设置日期的功能
 * 该扩展依赖于flatpickr日期选择插件
 */
jQuery.fn.extend({
    setDate: function (date) {
        $(this).flatpickr({
            defaultDate: date
        })
    },
    setTime: function (time) {
        $(this).flatpickr({
            dataFormat: 'yyyy-mm-dd HH:ii:SS',
            enableTime: true,
            enableSeconds: true,
            defaultDate: time
        })
    }
})
