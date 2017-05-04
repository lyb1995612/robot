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
			url : _path + "/scs/search",
			method : "POST",
			params : {
				sorter:'update'
			}
		},
		columns : [ {
			title : "ID",
			hidden : true,
			dataAttribute : "id"
		}, {
			title : "桌号",
			dataAttribute : "number",
			width : "7%"
		}, {
			title : "桌号别名",
			dataAttribute : "alias",
			width : "10%"
		}, {
			title : "桌位类型",
			dataAttribute : "desk_type",
			width : "8%"
		}, {
			title : "备注",
			dataAttribute : "memo",
			width : "10%"
		}, {
			title : "坐标X",
			dataAttribute : "deskx",
			width : "7%"
		}, {
			title : "坐标Y",
			dataAttribute : "desky",
			width : "7%"
		}, {
			title : "坐标Z",
			dataAttribute : "deskz",
			width : "7%"
		}, {
			title : "坐标W",
			dataAttribute : "deskw",
			width : "7%"
		}, {
			title : "坐标V",
			dataAttribute : "deskv",
			width : "7%"
		}, {
			title : "坐标Q",
			dataAttribute : "deskq",
			width : "7%"
		}, {
			title : "创建时间",
			dataAttribute : "date_create",
			width : "10%",
			dataType: "datetime"
		}, {
			title : "操作栏",
			width : "15%",
			style : "table-operation-column",
			//renderer : operation
		} ],
		remoteSort : true,
		pagination : true,
		paginationParam : {
			pageSize : 10,
			pagerLength : 5
		},
		emptyMsg : "查无结果!"
	});
});