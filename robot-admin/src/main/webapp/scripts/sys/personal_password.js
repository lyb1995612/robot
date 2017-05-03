var lock = false;
var validator = null;
$().ready(function() {
	//-----------------------------------------验证-------------------------------------
	jQuery.validator.addMethod("isName", function(value, element) {   
	    var name = /^[\u4E00-\u9FA5A-Za-z]+$/;
	    return this.optional(element) || (name.test(value));
	}, "请按照规则输入姓名");
	jQuery.validator.addMethod("isPassword", function(value, element) {   
	    var password = /^[a-zA-Z0-9_!@#$%^&*()+.~`,<>{}\|-]+$/;
	    return this.optional(element) || (password.test(value));
	}, "请按照规则输入密码");
	
	validator= $('#password_edit_form').validate({
	    errorElement : 'span',  
	        errorClass : 'help-block',  
	        focusInvalid : false,  
	        rules : {  
	            password : {  
	                required : true,
	                isPassword:true,
	                rangelength:[5,15]
	            },
	            confirmPassword : {  
	                required : true,
	                isPassword:true,
	                rangelength:[5,15],
	                equalTo: "#password"
	            }
	        },  
	        messages : {    
	            password : {  
	                required : "必须填写登录密码.", 
	                rangelength:jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串")
	            },
	            confirmPassword : {  
	                required : "必须填写确认密码.",
	                rangelength:jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
	                equalTo: "两次输入密码不一致!"
	                
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
	//-----------------------------------------验证-------------------------------------
	$('#changePassword').click(function(){
		submitPasswordData();
	});
});
function submitPasswordData(){
	if(validator.form()){
		var password = $('#password').val();
		var id = $('#id').val();
		var url = _path+"/sys/admin/passwordChange";
		var data ={id:id,password:password};
	    if(!lock){
			lock=true;
			 $.ajax({
		    	 type : "POST",  
		         contentType : 'application/json;charset=utf-8',  
		         url : url,  
		         data : JSON.stringify(data),  
		         dataType : 'json',  
		         cache : false,
		         success : function(data) {
		        	 var msg = data["message"];
		             var status = data["status"];
		        	 lock=false;
		        	if(status=='S'){
		            	 csjbotui.ui.msg.alert({
		            		 msg : msg + ", 请重新登录！",
		            		 ok : function(){
		            			 location.href=_path+"/logout"; 
		            		 }
		            	  });
		            	
		             }else{
		            	 csjbotui.ui.msg.alert(msg);
		             }	
		         },  
		         error : function(data) {  
		        	 csjbotui.ui.msg.alert("对不起,修改密码失败")  ;
		            lock=false;
		         }  
		    }); 
		}
	}
}

