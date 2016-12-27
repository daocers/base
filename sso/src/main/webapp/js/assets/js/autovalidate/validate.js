/*
表单验证插件
表单【form】中的控件添加verify属性
属性格式【text/30/1/0】 
*/
(function($){
	function validateOnChange(e)
	{
		var $e = $(e);
		var $tip=$("#"+$(e).attr("id")+"Tip");
		var value=$.trim($e.val());
		var verify=$e.attr("verify");
		if(verify){
		var p=verify.split('/');
		var reg=p[0];//正则
		if(reg == 'bool'){
			return true;
		}
		var len=p[1];//value值长度
		var isnull=p[2];//是否为空
		var dolength=p[3];//小数点的位数
		var tolength='0';
		eval("var freg = /^\\d+\\.\\d{"+dolength+"}$/;");
		var msg='';
		switch(reg)
		{
			case 'text': msg='格式必须为文本类型';tolength='1';reg = /\S+/i;break;
			case 'date-': msg='格式必须为日期类型，如：20120101';tolength='0';reg = /^(?:(?!0000)[0-9]{4}(?:(?:0[1-9]|1[0-2])(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])(?:29|30)|(?:0[13578]|1[02])31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)0229)$/i;break;
			case 'date':  msg='格式必须为日期类型，如：2015-04-02';tolength='0';reg = /^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/i;break;
			case 'time':  msg='格式必须为时间类型，如：2012-01-01 00:00:00';tolength='0';reg =  /(\d{4}-\d{2}-\d{2}\d{2}:\d{2}:\d{2})|(\d{2}-\d{2}\d{2}:\d{2}:\d{2})|(\d{2}:\d{2}:\d{2})$/i;break;
			case 'number':	 msg='格式必须为数字类型';tolength='0';reg = /^\d+$/i;break;
			case 'floatOld':  msg='格式必须为整数或小数类型且必须有'+dolength+'位小数';tolength='0';reg =freg;break;
			case 'float': eval("var fregNew = /^\\d+(\\.\\d{0,"+dolength+"})?$/;"); msg='格式为整数或小数类型且最多有'+dolength+'位小数';tolength='0';reg =fregNew;break;
			case 'decimal': eval("var fregNew2 = /^(0\\.\\d{0,"+dolength+"})?$/;"); msg='格式为小于1的浮点数且最多有'+dolength+'位小数,如0.66';tolength='0';reg =fregNew2;break;
			case 'email': msg='格式必须为邮箱类型， 如: example@email.com'; tolength='0';reg=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/; break;
			case 'mobile': msg='格式必须为手机号类型，如：18612345678';tolength='0';reg=/^1[3|4|5|8][0-9]\d{8}$/;break;
			case 'telephone': msg='格式必须为手机号码或电话号码类型';tolength='0';reg=/^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;break;
			case 'special': msg='该输入项只能输入中文、英文、数字或下划线';tolength='1';reg=/^([\u4e00-\u9fa5]|[a-zA-Z0-9_])+$/;break;
			case 'unspecial': msg='该输入项不包含'+"|\\?'.\"><;"+'等特殊字符';tolength='1';reg = /^([\u4e00-\u9fa5]|[a-zA-Z0-9]|[^|\\'?."><;])+$/;break;
			case 'china-english': msg='该输入项只能输入中文或英文字母';tolength='1';reg = /^[\u4e00-\u9fa5a-zA-Z]+$/;break;
			case 'china': msg='该输入项只能输入中文';tolength='1';reg = /^[\u4e00-\u9fa5]+$/;break;
			case 'chinamark': msg='该输入项只能输入中文';tolength='1';reg = /^[\u4e00-\u9fa5\、\,\《\》]+$/;break;
			case 'english': msg='该输入项只能输入英文字母';reg = /^[A-Za-z]+$/;tolength='0';break;
			case 'host': msg='该输入项只能输入英文字母、数字、.';reg = /^[A-Za-z0-9\.]+$/;tolength='0';break;
			case 'ip4':msg='该输入项只能输入ip地址';reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;tolength='0';break;	//ip地址'
			case 'fax':msg='格式必须为传真格式如：传真格式为:XXX-12345678或XXXX-1234567或XXXX-12345678';reg = /^(\d{3,4}-)?\d{7,8}$/;tolength='0';break;	//传真格式
			default:
				break;
		}

        if(isnull==1 && (value=='' || value=='undefined')){
    		showMsg($e,$tip,'此项不能为空',true);
        	return false;
        }
        if(value!=''){
        	if(tolength=='1'){
        		var strlength =value.replace(/[^\x00-\xff]/g,'aa').length; //字符长度 一个汉字两个字符
	        	if(strlength > len && len > 0){
	        		var charLen = (len%2==0)?(len/2):((len-1)/2);
	        		showMsg($e,$tip,'数据长度最大为'+len+'个字符（'+charLen+'个汉字）',true);
	        		return false;
	        	}  
        	}else{
        		if(value.length>len && len>0 ){
        			showMsg($e,$tip,'数据长度最大为'+len+'个字符',true);
        			return false;
        		}
    		}
    		if(value.search(reg)==-1){
    			showMsg($e,$tip,msg,true);
        		return false;
        	}
        }
        showMsg($e,$tip,"",false);
		return true;
		}
	}
	function showMsg($e,$tip,msg,flag){
		$e.attr('title',msg);
		if($tip.length>0){
			$tip.html(msg);
		}
		if(flag){
			$e.closest(".form-group").removeClass("has-success");
			$e.closest(".form-group").addClass("has-error");
			//$e.closest("span").append('<i class="icon-remove-sign"></i>');
		}else{
			$e.closest(".form-group").removeClass("has-error");
			$e.closest(".form-group").addClass("has-success");	
			$e.closest("span").find("i").remove();
		}
		
		
	}
	//点击保存时提交
	$.fn.validate=function(){
		var invalidate=true;
		this.each(function(j){
			var len=this.elements.length;
			for(var i=0;i<len;i++){
				var e=this.elements[i];
				var $e = $(e);
				if (e.type != "text" && e.type!="password"&& e.type!="textarea"&& e.type!="select-one") continue;
				var verify=$e.attr("verify");
				if(verify){
					validateOnChange(e);
				}
				if($e.closest(".form-group").hasClass("has-error")){
					if(invalidate) e.focus();
					invalidate = invalidate && false;
				}
				if(invalidate==false){
					
				}
			}
		});
		return invalidate;
	}
	//自动验证
	$.fn.autovalidate=function(){
		this.each(function(j){
			var len=this.elements.length;
			for(var i=0;i<len;i++){
				var e=this.elements[i];
				if (e.type != "text" && e.type!="password"&& e.type!="textarea"&& e.type!="select-one") continue;
				
			 	$(e).die().live("keyup change mousedown blur focus", function(){
			 		validateOnChange(this);
			 	});
//			 	$(e).die("change").live("change", function(){
//			 		validateOnChange(this);
//			 	});
//			 	$(e).die("mousedown").live("mousedown", function(){
//			 		validateOnChange(this);
//			 	});
//			 	$(e).die("blur").live("blur", function(){
//			 		validateOnChange(this);
//			 	});
//			 	if($(e).attr('class')=='Wdate'){
//			 		$(e).die("focus").live("focus", function(){
//				 		validateOnChange(this);
//				 	});
//			 	}
			}
		});
		return this;
	}
})(jQuery);
