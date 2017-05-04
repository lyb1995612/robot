$().ready(function() {
	// ----------------重置搜索条件--------------------------------------------------------
	$('#resetButton').click(function() {
		$("#searchform")[0].reset();
		
	});
	// ----------------条件搜索-----------------------------------------------------------
	$("input[id='searchButton']").click(function() {
		var param = $(".searchform").serialize2Json();
		$("#tp").datatable('load', param);
	});
	// ----------------列表展示-----------------------------------------------------------
	$("#tp").datatable({
		data : {
			url : _path + "/cms/search",
			method : "POST",
			params : { 
				
            }
		},
		/*columnDefs: [ {
			  targets: [2],
			     render: function(data, type, row){
			     return "<div style='word-break:break-all;overflow-y:scroll;overflow:auto;max-height:10rem;'>"+data+"</div>";
		  }
		  }],*/
		columns : [ {
			title : "ID",
			hidden : true,
			dataAttribute : "id"
		}, {
			title : "商户名",
			dataAttribute : "customer",
			width : "15%"
		}, {
			title : "值",
			data : "value",
			width : "15%",
		//	render: example
		}, {
			title : "键值",
			dataAttribute : "code",
			width : "15%"
		}, {
			title : "分组",
			dataAttribute : "code_group",
			width : "15%"
		}, {
			title : "备注",
			dataAttribute : "remark",
			width : "15%"
		}, {
			title : "操作",
			width : "15%",
			style : "operation-column",
			renderer : operation
		}],
		remoteSort : true,
		pagination : true,
		paginationParam : {
			pageSize : 20,
			pagerLength : 5
		},
		emptyMsg : "查无结果"
	});
});	

function operation(data, el) {

        var $editor = $("<a class=\"detail opt\" href=\"javascript:void(0);\"><span>详情&nbsp;&nbsp;</span></a>");
        $(el).append($editor);
        $(el).find(".detail").unbind("click").click(function() {
            window.location = _path + "/cms/" + data.code_group + "/" +data.code + "/toCustomerDetail";
        });
        // -------------------------------------------------------------------------------------------------------------------------------------------------------
        $editor = $("<a class=\"edit opt\" href=\"javascript:void(0);\"><span>编辑&nbsp;&nbsp;</span></a>");
        $(el).append($editor);
        $(el).find(".edit").unbind("click").click(function() {
        		 window.location = _path + "/cms/" + data.code_group + "/" +data.code + "/toCustomerUpdate";
        });
        $editor = $("<a class=\"delete opt\" href=\"javascript:void(0);\"><span>删除</span></a>");
        $(el).append($editor);
        $(el).find(".delete").unbind("click").click(function() {
        	    window.location = _path + "/cms/" + data.code_group + "/" +data.code + "/customerDelete"
        });
}
/*function operation( data, type, row ) {
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
}*/