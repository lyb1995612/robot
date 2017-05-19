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
		$('#sn').val('');
	});
	
	function initOptions(){
		var queryData = {sn:$("#sn").val()};
		var options = {
				processing: true,
		        serverSide: true,
		    	ajax: {
		    		url:_path + "/ad/search",
		    		method: "POST",
		    		data: queryData,
		    		error: function(xhr, msg){
		    			alert("数据加载失败！");
		    		}
		    	},
				columns : [{
					title : "机器人SN",
					data : "sn",
					width : "30%"
				}, {
					title : "广告数(条)",
					data : "number",
					width : "30%"
				}, {
					title : "操作",
					width : "30%",
					style : "operation-column"
				}],
				columnDefs: [
	           {
	               targets: [ 2 ],
	               render: operation,
	               orderable: false
	           }],
		        order: [[ 0, 'desc' ]],
		        remoteSort : true,
				pagination : true,
				paginationParam : {
					pageSize : 10,
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
    var editor = "<a class='opt' id='distribute_"+row.id+"' href=\"javascript:void(0);\" ><span>分配</span></a>&nbsp;&nbsp;"
                 + "<a class='opt' id='delete_"+row.id+"' href=\"javascript:void(0);\" ><span>删除</span></a>&nbsp;&nbsp;"
             
        $(document).off("click", "#distribute_" + row.id).on("click", "#distribute_" + row.id, function(){
        	window.location = _path + "/ad/" + row.sn + "/toAdvertisement_distributeUpdate";
         }); 
	    $(document).off("click", "#delete_" + row.id).on("click", "#delete_" + row.id, function(){
	       	 csjbotui.ui.msg.confirm({
	 		        title : "确认删除",
					msg : "您确定要删除该广告分配【"+row.sn+"】?",
					ok:function(){
						 $.ajax({
			        		type : "POST",                    
			  	            url : _path + "/ad/" + row.sn + "/advertisementDistributeDelete",
			  	            dataType : "json",
			  	            success : function(data){ 
			  	            	if (data.msg == "S") {
	  	            				window.location.href = _path + "/ad/list";
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


	
	
	
