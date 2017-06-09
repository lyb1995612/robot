$(function(){  
	validator = $('#shop_form').validate({
		errorElement : 'span',  
		errorClass : 'help-block',  
		focusInvalid : false,  
		rules : {  
			shop_name : {  
				required : true,
				rangelength:[0, 32]
			},
			shop_code : {
				required : true,
				isEnglishNumSymbol: true,
				rangelength:[0, 32]
			},
			shop_address : {
				rangelength:[0, 256]
			},
			memo : {
				rangelength:[0, 512]
			}
		},  
		messages : {    
			shop_name : {  
				required : "必须填写店铺名称."
			},
			shop_code : {  
				required : "必须填写店铺编码."
			}
		},
		highlight : function(element) {  
			$(element).closest('.form-group').addClass('has-error');  
		},
		success : function(label) {  
			label.closest('.form-group').removeClass('has-error');  
			label.remove();  
		}, 
		errorPlacement : function(error, element) {  
			element.parent('div').parent('div').append(error);  
		}   
	});
	$("#submit").click(function(){
		if (validator.form()) {
			csjbotui.ui.msg.waiting({
				title : "正在新增店铺",
				msg : "请稍等..."
			});
			$("#shop_form").ajaxSubmit({
                type: "POST",
                url: _path + "/shop/add",
                dataType: "json",
                success: function(data){
                	csjbotui.ui.msg.waiting.remove();
                	if (data.msg == "S") {
                		csjbotui.ui.msg.alert({
                			msg : "新增店铺成功!",
                			ok : function(){
                				window.location = _path + "/shop/list";
                			}
                		});
                	} else {
                		csjbotui.ui.msg.alert("新增店铺失败!");
                	}
                },  
                error : function(xhr, msg, error) {  
                	csjbotui.ui.msg.waiting.remove();
                	if ("timeout" == msg || "parsererror" == msg) {
                        csjbotui.ui.msg.alert({
                			msg : "对不起，session过期，请重新登录!",
                			ok : function(){
                				window.location.reload();
                			}
                		});
                    } else {
                        csjbotui.ui.msg.alert("Internal Server Error!");
                    }
                }  
            });
    	}
	});
});