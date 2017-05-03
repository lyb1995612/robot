
var lock = false;
var validator = null;
$().ready(function() { 
	
	  $('.form_datetime').datetimepicker({
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
	        showMeridian: 1
	    });
	
	jQuery.validator.addMethod("isName", function(value, element) {   
	    var name = /^[\u4E00-\u9FA5A-Za-z]+$/;
	    return this.optional(element) || (name.test(value));
	}, "请按照规则输入姓名");
	
	jQuery.validator.addMethod("isCellPhone", function(value, element) {   
	    var cellPhone = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0|6|7|8]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
	    return this.optional(element) || (cellPhone.test(value));
	}, "请按照规则输入手机号码");
	
	jQuery.validator.addMethod("emailAddress", function(value, element) {   
	    var mail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	    return this.optional(element) || (mail.test(value));
	}, "请输入正确的email地址");
	
	jQuery.validator.addMethod("isPassword", function(value, element) {   
	    var password = /^[a-zA-Z0-9_!@#$%^&*()+.~`,<>{}\|-]+$/;
	    return this.optional(element) || (password.test(value));
	}, "请按照规则输入密码");
	
	jQuery.validator.addMethod("isUserName", function(value, element) {   
	    var tel = /^[a-zA-Z][a-zA-Z0-9_!@#$%&*()+.]+$/;
	    return this.optional(element) || (tel.test(value));
	}, "请按照规则填写账号");
	
	validator= $('#user_add_form').validate({
    errorElement : 'span',  
        errorClass : 'help-block',  
        focusInvalid : false,  
        rules : {  
            realname : {  
                required : true,
                isName:true,
                rangelength:[2,40]
            },  
            phone : {  
                required : true,
                isCellPhone	:true
            },
            email : {  
                required : true,
                email: true
            },
            username:{
            	required : true,
            	isUserName : true,
            	rangelength:[4,20]
            },
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
        	realname : {  
                required : "必须填写姓名.",
                rangelength:jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串")
            },  
            phone : {  
                required : "必须填写手机号码.",
                digits: "电话号码必须是数字"
            },
            email : {  
                required : 	"必须填写电子邮箱.",
                email: 		"请输入正确的email地址"
            },
            username: {  
                required : "必须填写账号.",
                rangelength:jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串")
            },  
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
	
	
});

function addUser(num){
	var realname = $('#realname').val();
	var sex = $("input[name='sex']:checked").val();
	var phone = $('#phone').val();
	var email = $('#email').val();
	var username = $('#username').val();
	var status = $("input[name='status']:checked").val();
	var password = $('#password').val();
	var dateEffect = $("#dateEffect").val();
	var dateExpire = $("#dateExpire").val();
	dateEffect = dateEffect.replace(/-/g,"/");
	dateEffect = new Date(dateEffect );
	dateExpire = dateExpire.replace(/-/g,"/");
	dateExpire = new Date(dateExpire );
	
	
	var url =_path+"/sys/admin/sysAdminAdd";
	var data = {realname:realname,sex:sex,phone:phone,email:email,username:username,status:status,password:password,dateEffect:dateEffect,dateExpire:dateExpire};
	if(validator.form()){
		user_Controller(data,"POST",url,num);
	}
}

 function user_Controller(data,subType,url,num) {
	 $.ajax({
    	 type : subType,  
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
            	 alert(msg);
            	 if(num==1) location.href=_path+"/sys/admin/list";
                 else	location.href=_path+"/sys/admin/toSysAdminAdd"; 
             }else	alert(msg);
         },  
         error : function(data) {  
           alert("对不起,新增系统管理员失败")  ;
           lock=false;
         }  
    });  
 }
 