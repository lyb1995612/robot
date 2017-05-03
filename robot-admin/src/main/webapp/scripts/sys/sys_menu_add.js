$(function(){
    $("#sys_menu").addClass("active");
    
    $("#permission_fk").chosen();
    $("#level").chosen().change(function(){
    	var level = $("#level").val();
    	if (level == "1") {
    		$("#parents_select").attr("style", "visibility: hidden;");
    		$("#parent_fk").val("");
    		$("#parent_fk").trigger("chosen:updated");
    	} else {
    		$("#parents_select").attr("style", "visibility: visible;");
    	}
    	
    });
    $("#parent_fk").chosen();
    $("#style").chosen();
});

$(function(){  
    
	validator = $('#menu_add_form').validate({
	    	errorElement : 'span',  
	        errorClass : 'help-block',  
	        focusInvalid : false,  
	        rules : {  
	        	name : {  
	                required : true,
	                rangelength:[2, 10]
	            },  
	            activeId : {  
	                required : true,
	                isEnglishNumSymbol:true,
	                rangelength:[1, 30]
	            },  	            
	            sorter : {  
	                required : true,
	                digits: true
	            }
	        },  
	        messages : {    
	        	name : {  
	                required : "必须填写菜单名称.",
	                rangelength:jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串")
	            },  
	            activeId : {  
	                required : "必须填写菜单标识.",
	                rangelength:jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串")
	            },  
	            sorter : {  
	                required : 	"必须填写菜单排序."
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
    	var level = $("#level").val();
    	if (level == "2") {
    		if(!$("#parent_fk").val()) {
    			csjbotui.ui.msg.alert("请选择父级菜单！");
    			return false;
    		}
    	} 
    	
    	if (validator.form()) {
    		csjbotui.ui.msg.waiting({
    			title : "正在新增菜单",
    			msg : "请稍等..."
    		});
    		$("#menu_add_form").ajaxSubmit({
                type: "POST",
                url: _path + "/menu/addMenu",
                dataType: "json",
                success: function(data){
                	csjbotui.ui.msg.waiting.remove();
                	if (data.msg == "S") {
                		csjbotui.ui.msg.alert({
                			msg : "新增菜单成功！",
                			ok : function(){
                				window.location = _path + "/menu/toListMenus";
                			}
                		});
                	} else {
                		csjbotui.ui.msg.alert("新增菜单失败！");
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