$(function(){  
    
	validator = $('#dish_form').validate({
	    	errorElement : 'span',  
	        errorClass : 'help-block',  
	        focusInvalid : false,  
	        rules : {  
	        	name : {  
	                required : true,
	                rangelength:[0, 25]
	            },
	            price : {
	            	required : true,
	            	isFloatNum2 : true
	            }
	           
	        },  
	        messages : {    
	        	name : {  
	                required : "必须填写产品名称."
	            },
	            price : {  
	                required : "必须填写价格."
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
    			title : "正在修改产品",
    			msg : "请稍等..."
    		});
    		$("#dish_form").ajaxSubmit({
                type: "POST",
                url: _path + "/dish/update",
                dataType: "json",
                success: function(data){
                	csjbotui.ui.msg.waiting.remove();
                	if (data.msg == "S") {
                		csjbotui.ui.msg.alert({
                			msg : "修改产品成功!",
                			ok : function(){
                				window.location = _path + "/dish/list";
                			}
                		});
                	} else {
                		csjbotui.ui.msg.alert("修改产品失败!");
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