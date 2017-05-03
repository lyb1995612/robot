var table = "";
var initable = "";
$(function() {
	
	// ----------------条件搜索-----------------------------------------------------------
	$("#searchButton").click(function() {
		table.state.clear(); 
		table.destroy();
		table = $("#tp").DataTable(initOptions());
	});
	$(document).keydown(function(e){
		if (e && e.keyCode == 13) {
			$("#searchButton").click();
			return false;
		}
	});

	// ----------------重置搜索条件--------------------------------------------------------
	$('#resetButton').click(function() {
		$('#customer').val('');
	});
	
	function initOptions(){
		var queryData = {customer:$("#customer").val()};
		var options = {
				processing: true,
		        serverSide: true,
		    	ajax: {
		    		url:_path + "/cms/search",
		    		method: "POST",
		    		data: queryData,
		    		error: function(xhr, msg){
		    			alert("数据加载失败");
		    		}
		    	},
				columns : [{
					title : "商户名",
					data : "customer",
					width : "15%"
				}, {
					title : "值",
					data : "value",
					width : "15%"
				}, {
					title : "键值",
					data : "code",
					width : "15%"
				}, {
					title : "分组",
					data : "code_group",
					width : "15%"
				}, {
					title : "备注",
					data : "remark",
					width : "15%"
				},{
					title : "操作",
					width : "15%",
					style : "operation-column"
				}],
				columnDefs: [
				{
					targets: [1],
					render: function(data, type, row){
						return "<div style='word-break:break-all;overflow-y:scroll;overflow:auto;max-height:10rem;'>"+data+"</div>";
					}
				},
	            {
	               targets: [ 5 ],
	               render: operation,
	               orderable: false
	            }],
		        remoteSort : true,
				pagination : true,
				paginationParam : {
					pageSize : 20,
					pagerLength : 5
				},
		        emptyMsg : "查无结果"
		 };
		 return options;
	}
	initable = initOptions();
	table = $("#tp").DataTable(initOptions());
	
});

function operation( data, type, row ) {
    var editor =   "<a class='opt' id='detail_"+row.code_group + row.code +"' href=\"javascript:void(0);\" ><span>详情</span></a>&nbsp;&nbsp;"
    	         + "<a class='opt' id='edit_"+row.code_group + row.code+"' href=\"javascript:void(0);\" ><span>编辑</span></a>&nbsp;&nbsp;"
                 + "<a class='opt' id='delete_"+row.code_group + row.code+"' href=\"javascript:void(0);\" ><span>删除</span></a>&nbsp;&nbsp;"
        $(document).off("click", "#detail_" + row.code_group + row.code).on("click", "#detail_" + row.code_group + row.code, function(){
            window.location = _path + "/cms/" + row.code_group + "/" + row.code + "/toCustomerDetail";
         });
	    $(document).off("click", "#edit_" + row.code_group + row.code).on("click", "#edit_" + row.code_group + row.code, function(){
	    	window.location = _path + "/cms/" + row.code_group + "/" + row.code+"/toCustomerUpdate";
	     });    
	    $(document).off("click", "#delete_" + row.code_group + row.code).on("click", "#delete_" + row.code_group + row.code, function(){
	       	 csjbotui.ui.msg.confirm({
	 		        title : "警告",
					msg : "您确定要删除该商戶【"+row.customer+"】?",
					ok:function(){
						 $.ajax({
			        		type : "POST",
			  	            url : _path + "/cms/" + row.code_group + "/" + row.code + "/customerDelete",
			  	            dataType : "json",
			  	            success : function(data){ 
			  	            	if (data.msg == "S") {
	  	            				window.location.href = _path + "/cms/list";
			  	            	} else {
			  	            		csjbotui.ui.msg.alert( data.msg );
			  	            	}
			  	            },
				        	 error : function(xhr, msg, error) {
				             	csjbotui.ui.msg.alert("Internal Server Error!");
				             } 
			        	 });
					}
				});	    	
	     });   
    return editor;
}


	
	
	
