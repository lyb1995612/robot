var validator = null;
$().ready(function() {

	validator= $('#dicform').validate({
	    errorElement : 'span',  
	        errorClass : 'help-block',  
	        focusInvalid : false,  
	        rules : {  
	        	id : {  
	        		required : true,
	                rangelength:[0,10],
	                digits: "只能输入整数"
	            },  
	            name : {  
	                required : false,
	                rangelength:[0,50]
	            },  
	            memo : {  
	            	required : false,
	                rangelength:[0,100]
	            },
	            min: {  
	                required : false,
	                rangelength:[0,10],
	                digits: "只能输入整数"
	            },
	            max : {  
	            	required : false,
	            	rangelength:[0,10],
	                digits: "只能输入整数"
	            },
	            rule : {  
	                required : false,
	                rangelength:[0,50]
	            }, 
	            sort : {  
	                required : true,
	                rangelength:[0,10],
	                digits: "只能输入整数"
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


function save(){
	if(validator.form()){
		dicson_Controller();
	}
}

 function dicson_Controller() {
	 $("#dicform").ajaxSubmit({
			type: "POST",
			url:$(".form").attr('action'),
			dataType: "json",
		    success: function(data){
		    	var msg = data["message"];
		    	var flag = data["status"];
		    	if(flag=='S'){
		    		alert(msg);
	            	window.location = _path + "/dic/" + $("#dataFk").val() +"/sonlist";
		    	}
		    	else{
		    		alert(msg);
		    	}
			},  
		    error : function(data) {  
		    	csjbotui.ui.msg.alert("对不起数据维护失败")  ;
	        }  
		});
 }
 
