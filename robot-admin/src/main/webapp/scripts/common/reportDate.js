/*************************
 * @author Xiong Feng
 * 报表时间选择框
 * 
 * 
 *************************/
(function($){
	
	$.reportDateParam = {
	    defaults: {
			id: "",            // prefix (必填)
			chartURL : "",     // 图表的URL (必填)
			chartDivID : "",   // 放图表的DIV的id, 默认为  prefix + "_chart" (可选)
			subitemURL : "",   // 图表文字总结的URL (可选)
			subitemDivID : "", // 放总结的DIV的id, 默认为  prefix + "_subitem" (可选)
			otherDivID : "",    // 非总结页面DIV(可选)
			show : false      // 为true时显示隐藏的时间选择框,  为false时无作为
	    }	
	};
	
	$.fn.reportDate = function(options){
		options = $.extend({}, $.reportDateParam.defaults, options);
		var prefix = options.id;
		
		function loadChart(configdata){
			if ($("#position").val() == "summary") {
				 if (options.chartDivID == "") {
					 if (options.show == true) {
						 $("#" + prefix + "_chart").load(options.chartURL, configdata, function(){
							 if($("#loginForm").length > 0) {
			        			window.location.reload();
			        			return;
			        		 }
							 $("#" + prefix + "_report").prev().show();
							 
						 });
					 } else {
						 $("#" + prefix + "_chart").load(options.chartURL, configdata, function(){
							 if($("#loginForm").length > 0) {
								window.location.reload();
			        			return;
			        		 }
						 });
					 }
					 
				 }else {
					 if (options.show == true) {
						 $("#" + options.chartDivID).load(options.chartURL, configdata, function(){
							 if($("#loginForm").length > 0) {
								window.location.reload();
			        			return;
			        		 }
							 $("#" + prefix + "_report").prev().show();
						 });
					 } else {
						 $("#" + options.chartDivID).load(options.chartURL, configdata, function(){
							 if($("#loginForm").length > 0) {
								window.location.reload();
			        			return;
			        		 }
						 });
					 }
				 }
				 
				 if (options.subitemURL != ""){
					 if (options.subitemDivID == "") {
						 $("#" + prefix + "_subitem").load(options.subitemURL, configdata, function(){
							 if($("#loginForm").length > 0) {
								window.location.reload();
			        			return;
			        		 }
						 });
					 }else {
						 $("#" + options.subitemDivID).load(options.subitemURL, configdata, function(){
							 if($("#loginForm").length > 0) {
								window.location.reload();
			        			return;
			        		 }
						 });
					 }
				 }
			 } else {
				 $("#" + options.otherDivID).load(options.chartURL, configdata, function(){
					 if($("#loginForm").length > 0) {
						window.location.reload();
	        			return;
	        		 }
				 });
			 }
		}
		
//		$("#" + prefix + "_showtime").change(function(){
//			 var showId = parseInt($(this).val());
//			 if(showId > 0) {
//				 var configdata = {};
//				 if ($("#tenantId").val() != null) {
//					 configdata = {showId:showId, tenant_fk : $("#tenantId").val()};
//				 } else {
//					 configdata = {showId:showId};
//				 }
//				 loadChart(configdata);
//			 }else if(showId == 0){
//				 $("#" + prefix + "_anytime").click();
//			 }
//		});
		
	    $("#" + prefix + "_showtime").select({
	        change: function (value, label) {
				 var showId = parseInt($(this).val());
				 if(showId > 0) {
					 var configdata = {};
					 if ($("#tenantId").val() != null) {
						 configdata = {showId:showId, tenant_fk : $("#tenantId").val()};
					 } else {
						 configdata = {showId:showId};
					 }
					 loadChart(configdata);
				 }else if(showId == 0){
					 $("#" + prefix + "_anytime").click();
				 }
			
	        },
	       width:200
	    });
		
		$("#" + prefix + "_confirm").click(function() {
			 
			 if($("#" + prefix + "_datepicker_form").validate().form()){
				 var start = $("#" + prefix + "_time").val();
				 var end = $("#" + prefix + "_timeEnd").val();
				 var startDate = new Date((start).replace(/-/g,"/"));
		         var endDate = new Date((end).replace(/-/g,"/"));   
		         
		         if(startDate >endDate) {
		        	 alert("开始时间必须小于结束时间!");
		        	 return false;
		         }
		         var configdata = {};
		         if ($("#tenantId").val() != null) {
		        	 configdata =  {showId:0,start:start,end:end,tenant_fk: $("#tenantId").val()};
		         } else {
		        	 configdata =  {showId:0,start:start,end:end};
		         }
		         loadChart(configdata);
			  }
		});
        
		$("#" + prefix + "_option0").click(function() {
			$("#" + prefix + "_anytime").click();
		});
		
		$("#" + prefix + "_modalCancel").click(function(){
			$("#dk_container_" + prefix + "_showtime ul li").each(function(){
	        	if($(this).find("a").attr("data-dk-dropdown-value") == $("#" + prefix + "_selected").val()) {
        	        $("#dk_container_" + prefix + "_showtime").find('.dk_option_current').removeClass('dk_option_current');
        	        $(this).addClass('dk_option_current');
        	        $("#dk_container_" + prefix + "_showtime .dk_label").html( $("#dk_container_" + prefix + "_showtime").find('.dk_option_current a').html());
        	        return false;
	        	}
	        });
		});	
		
		$("#" + prefix + "_cancel").click(function() {
			$("#" + prefix + "_modalCancel").click();
			$("#dk_container_" + prefix + "_showtime ul li").each(function(){
	        	if($(this).find("a").attr("data-dk-dropdown-value") == $("#" + prefix + "_selected").val()) {
        	        $("#dk_container_" + prefix + "_showtime").find('.dk_option_current').removeClass('dk_option_current');
        	        $(this).addClass('dk_option_current');
        	        $("#dk_container_" + prefix + "_showtime .dk_label").html( $("#dk_container_" + prefix + "_showtime").find('.dk_option_current a').html());
        	        return false;
	        	}
	        });
		});
		
		var rules = '{"' + prefix + '_time" :{"required" : true}, "' + prefix + '_timeEnd" :{"required" : true} }';
		var messages = '{"' + prefix + '_time" :{"required" : "必须选择开始时间"}, "' + prefix + '_timeEnd" :{"required" : "必须选择结束时间"} }';
	    $("#" + prefix + "_datepicker_form").validate({
		    errorElement : 'span',  
	        errorClass : 'help-block',  
	        focusInvalid : false,  
	        rules : JSON.parse(rules),  
	        messages : JSON.parse(messages),
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
	    
		$("#" + prefix + "_time").datetimepicker({
			language : 'zh-CN',
			format : 'yyyy-mm-dd',
			autoclose : true,
			todayBtn : true,
			todayHighlight : 1,
			startView : 2,
			minView : 2
		});
		
		$("#" + prefix + "_timeEnd").datetimepicker({
			language : 'zh-CN',
			format : 'yyyy-mm-dd',
			autoclose : true,
			todayBtn : true,
			todayHighlight : 1,
			startView : 2,
			minView : 2
		});
	};
	
	
})(jQuery);