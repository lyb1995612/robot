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
		$('#group_name').val('');
	});
	
	function initOptions(){
		var queryData = {group_name:$("#group_name").val()};
		var options = {
				processing: true,
		        serverSide: true,
		    	ajax: {
		    		url:_path + "/rbg/search",
		    		method: "POST",
		    		data: queryData,
		    		error: function(xhr, msg){
		    			alert("数据加载失败");
		    		}
		    	},
				columns : [{
					title : "群组名",
					data : "group_name",
					width : "15%"
				},{
					title : "类型",
					data : "type_name",
					width : "15%"
				},{
					title : "数量",
					data : "relevance_num",
					width : "15%"
				}, {
					title : "操作",
					width : "15%",
					style : "operation-column"
				}],
				columnDefs: [{
					targets: [2],
					render: function(data, type, row){
						 if(row.relevance_num == null||row.relevance_num ==''){
							 return 0;
						 }else{
							 return data;
						 }
					}
				},{
	               targets: [ 3 ],
	               render: operation,
	               orderable: false
	            }],
	            order: [[ 0, 'desc' ]],
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
    var editor =  "<a class='opt' id='detail_"+ row.id +"' href=\"javascript:void(0);\" ><span>关联机器人</span></a>&nbsp;&nbsp;"
        $(document).off("click", "#detail_" + row.id).on("click", "#detail_" + row.id, function(){
            window.location = _path + "/rbg/" + row.id+ "/toRobotGroupRef";
         });
    return editor;
}

