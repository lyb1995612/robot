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
		    		url:_path + "/dsn/search",
		    		method: "POST",
		    		data: queryData,
		    		error: function(xhr, msg){
		    			alert("数据加载失败");
		    		}
		    	},
				columns : [{
					title : "S/N",
					data : "sn",
					width : "20%"
				},{
					title : "操作",
					width : "20%",
					style : "operation-column"
				}],columnDefs: [{
	               targets: [ 1 ],
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
    var editor =  "<a class='opt' id='detail_"+ row.id +"' href=\"javascript:void(0);\" ><span>关联菜品</span></a>&nbsp;&nbsp;"
        $(document).off("click", "#detail_" + row.id).on("click", "#detail_" + row.id, function(){
            window.location = _path + "/dsn/" + row.sn+ "/toDishSNRef";
         });
    return editor;
}

