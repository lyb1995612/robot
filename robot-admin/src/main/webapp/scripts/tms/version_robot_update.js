$(function(){  
    
	validator = $('#version_form').validate({
	    	errorElement : 'span',  
	        errorClass : 'help-block',  
	        focusInvalid : false,  
	        rules : {  
	        	version_name : {  
	                required : true,
	                rangelength:[0, 25]
	            },
	            version_code : {
	            	required : true,
	            	digits   : true
	            	
	            }
	           
	        },  
	        messages : {    
	        	version_name : {  
	                required : "必须填写版本号."
	            },
	            version_code : {  
	                required : "必须填写Version Code."
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
    			title : "正在编辑版本",
    			msg : "请稍等..."
    		});
    		$("#version_form").ajaxSubmit({
                type: "POST",
                url: _path + "/vrc/update",
                dataType: "json",
                success: function(data){
                	csjbotui.ui.msg.waiting.remove();
                	if (data.msg == "S") {
                		csjbotui.ui.msg.alert({
                			msg : "编辑版本成功!",
                			ok : function(){
                				window.location = _path + "/vrc/list";
                			}
                		});
                	} else {
                		csjbotui.ui.msg.alert("编辑版本失败!");
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