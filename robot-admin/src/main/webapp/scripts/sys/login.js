	$().ready(function() {  
        $('#loginForm').validate({
        errorElement : 'span',  
            errorClass : 'help-block',  
            focusInvalid : false,    
            rules : {  
            	username : {  
                    required : true,
                    minlength: 4,
                    maxlength:20 
                },  
                password : {  
                    required : true  
                }
            },  
            messages : {  
            	username : {  
                    required : "必须输入账号",
                    minlength: "账号过短，请重新输入",
                    maxlength: "输入账号不能超过20位"
                },  
                password : {  
                    required : "必须输入密码"  
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
            },
            submitHandler : function(form) {  
                form.submit();  
            }  
            
        }); 
           
});