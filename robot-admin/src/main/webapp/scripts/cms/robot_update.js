$(function(){  
    
	validator = $('#robot_form').validate({
	    	errorElement : 'span',  
	        errorClass : 'help-block',  
	        focusInvalid : false,  
	        rules : {  
	        	sn : {  
	                required : true,
	                rangelength:[0, 25]
	            },
	        },  
	        messages : {    
	        	sn : {  
	                required : "必须填写机器人唯一识别码."
	            },
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
    			title : "正在修改机器人清单",
    			msg : "请稍等..."
    		});
    		$("#robot_form").ajaxSubmit({
                type: "POST",
                url: _path + "/crb/update",
                dataType: "json",
                success: function(data){
                	csjbotui.ui.msg.waiting.remove();
                	if (data.msg == "S") {
                		csjbotui.ui.msg.alert({
                			msg : "修改机器人清单成功!",
                			ok : function(){
                				window.location = _path + "/crb/list";
                			}
                		});
                	} else {
                		csjbotui.ui.msg.alert("修改机器人清单失败!");
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