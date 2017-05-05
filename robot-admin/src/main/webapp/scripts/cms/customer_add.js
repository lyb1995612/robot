$(function(){  
    
	validator = $('#customer_form').validate({
	    	errorElement : 'span',  
	        errorClass : 'help-block',  
	        focusInvalid : false,  
	        rules : {  
	        	customer : {  
	                required : true,
	                rangelength:[0, 10]
	            },
	            value : {
	            	required : true,
	            	rangelength:[0, 50]
	            },
	            code : {
	            	required : true,
	            	isEnglishNumSymbol: true,
	            	rangelength:[0, 50]
	            },
	            code_group : {
	            	required : true,
	            	isEnglishNumSymbol: true,
	            	rangelength:[0, 20]
	            },
	        },  
	        messages : {    
	        	name : {  
	                required : "必须填写商户名."
	            },
	            value : {  
	                required : "必须填写值."
	            },
	            code : {  
	                required : "必须填写键值.",
	            },
	            code_group : {  
	                required : "必须填写分组.",
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
    			title : "正在新增商户",
    			msg : "请稍等..."
    		});
    		$("#customer_form").ajaxSubmit({
                type: "POST",
                url: _path + "/cms/add",
                dataType: "json",
                success: function(data){
                	csjbotui.ui.msg.waiting.remove();
                	if (data.msg == "S") {
                		csjbotui.ui.msg.alert({
                			msg : "新增商户成功!",
                			ok : function(){
                				window.location = _path + "/cms/list";
                			}
                		});
                	} else {
                		csjbotui.ui.msg.alert("新增商户失败!");
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