	$().ready(function() { 
        $('#reservation').daterangepicker({format: 'YYYY-MM-DD',separator:' TO '}, function(start, end, label) {
         
            });
            
        $('#datetimepicker').datetimepicker({
    				format: 'yyyy-mm-dd',
    				autoclose: true,
        			todayBtn: true,		
					todayHighlight: 1,
					startView: 2,
					minView: 2
					});
              
        $('#prdForm').validate({
        errorElement : 'span',  
            errorClass : 'help-block',  
            focusInvalid : false,    
            rules : {  
                prdName : {  
                    required : true  
                },  
                prdSize : {  
                    required : true  
                },  
                prdPeriod : {  
                    required : true  
                }  
            },  
            messages : {  
            	prdName : {  
                    required : "Username is required."  
                },  
                prdSize : {  
                    required : "Password is required."  
                },  
                prdPeriod : {  
                    required : "Intro is required."  
                }  
            },
             highlight : function(element) {  
                $(element).closest('.form-group').addClass('has-error');  
            }, 
            errorPlacement : function(error, element) {  
                element.parent('div').parent('div').append(error);  
            },
            submitHandler : function(form) {  
                form.submit();  
            }  
            
        }); 
           
});