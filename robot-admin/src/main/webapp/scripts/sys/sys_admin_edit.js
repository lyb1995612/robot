
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
	
	validator= $('#user_edit_form').validate({
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

function updateUser(){
	var id = $('#id').val();
	var realname = $('#realname').val();
	var sex = $("input[name='sex']:checked").val();
	var phone = $('#phone').val();
	var email = $('#email').val();
	var status = $("input[name='status']:checked").val();
	var dateEffect = $("#dateEffect").val();
	var dateExpire = $("#dateExpire").val();
	dateEffect = dateEffect.replace(/-/g,"/");
	dateEffect = new Date(dateEffect );
	dateExpire = dateExpire.replace(/-/g,"/");
	dateExpire = new Date(dateExpire );
	
	var url =_path+"/sys/admin/sysAdminUpdate";
	var data = {id:id,realname:realname,sex:sex,phone:phone,email:email,status:status,dateEffect:dateEffect,dateExpire:dateExpire};
	if(validator.form()){
		user_Controller(data,"POST",url);
	}
}

 function user_Controller(data,subType,url) {
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
            	 location.href=_path+"/sys/admin/list";
             }else	alert(msg);
         },  
         error : function(data) {  
           alert("对不起,编辑系统管理员失败");
           lock=false;
         }  
    });  
 }
 