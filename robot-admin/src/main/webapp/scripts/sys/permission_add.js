$(function(){
	$("#isValid").select({
        change: function (value, label) {
        },
        width : 200
    });
	$("#type").select({
        change: function (value, label) {
        },
        width : 200
    });
});

$(function(){  
    
	validator = $('#permission_add_form').validate({
	    	errorElement : 'span',  
	        errorClass : 'help-block',  
	        focusInvalid : false,  
	        rules : {  
	        	name : {  
	                required : true,
	                rangelength:[2, 20]
	            },  
	            code : {  
	                required : true,
	                rangelength:[0, 50]
	            }
	        },  
	        messages : {    
	        	name : {  
	                required : "必须填写权限名称."
	            },  
	            code : {  
	                required : "必须填写权限代码."
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
    			title : "正在新增权限",
    			msg : "请稍等..."
    		});
    		$("#permission_add_form").ajaxSubmit({
                type: "POST",
                url: _path + "/permission/add",
                dataType: "json",
                success: function(data){
                	csjbotui.ui.msg.waiting.remove();
                	if (data.msg == "S") {
                		csjbotui.ui.msg.alert({
                			msg : "新增权限成功！",
                			ok : function(){
                				window.location = _path + "/permission/list";
                			}
                		});
                	} else {
                		csjbotui.ui.msg.alert("新增权限失败！");
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