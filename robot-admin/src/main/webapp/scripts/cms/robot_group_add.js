$(function(){  
    
	validator = $('#robot_group_form').validate({
	    	errorElement : 'span',  
	        errorClass : 'help-block',  
	        focusInvalid : false,  
	        rules : {  
	        	group_name : {  
	                required : true,
	                rangelength:[0, 25]
	            },
	           
	        },  
	        messages : {    
	        	group_name : {  
	                required : "必须填写群组名."
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
    			title : "正在新增机器人群组",
    			msg : "请稍等..."
    		});
    		$("#robot_group_form").ajaxSubmit({
                type: "POST",
                url: _path + "/rbg/add",
                dataType: "json",
                success: function(data){
                	csjbotui.ui.msg.waiting.remove();
                	if (data.msg == "S") {
                		csjbotui.ui.msg.alert({
                			msg : "新增机器人群组成功!",
                			ok : function(){
                				window.location = _path + "/rbg/list";
                			}
                		});
                	} else {
                		csjbotui.ui.msg.alert("新增机器人群组重名!");
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