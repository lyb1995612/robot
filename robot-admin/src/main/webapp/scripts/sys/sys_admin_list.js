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
			url : _path + "/sys/admin/page",
			method : "POST",
			params : {sorter:'update'}
		},
		columns : [ {
			title : "ID",
			hidden : true,
			dataAttribute : "id"
		}, {
			title : "账号",
			dataAttribute : "username",
			width : "10%"
		}, {
			title : "姓名",
			width : "10%",
			dataAttribute : "realname"
		}, {
			title : "最近登录时间",
			dataAttribute : "lastLoginTime",
			width : "15%",
			dataType: "datetime"
		}, {
			title : "最近操作人",
			dataAttribute : "updater",
			width : "10%",
		}, {
			title : "最近操作时间",
			dataAttribute : "dateOfUpdate",
			width : "15%",
			dataType: "datetime"
		}, {
			title : "操作栏",
			width : "15%",
			style : "table-operation-column",
			renderer : operation
		} ],
		remoteSort : true,
		pagination : true,
		paginationParam : {
			pageSize : 10,
			pagerLength : 5
		},
		emptyMsg : "根据您输入的搜索条件,没有找到符合条件的管理员，请更换搜索条件再次搜索!"
	});
	
	
});