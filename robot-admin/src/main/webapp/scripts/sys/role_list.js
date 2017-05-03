$().ready(function() {

	$(".searchform .searchBtn").click(function() {
		var param = $(".searchform").serialize2Json();
		$("#tp").datatable('load', param);
	});

	$(".searchform .reset").click(function() {
		$(".searchform")[0].reset();
	});

	$(".add-role").click(function() {
		window.location = _path + "/role/create";
	});

	$("#tp").datatable({
		data : {
			url : _path + "/role/search",
			method : "POST",
			params : {

			}
		},
		columns : [ {
			title : "ID",
			hidden : true,
			dataAttribute : "id",
			dataType : "long"
		}, {
			title : "角色名称",
			dataAttribute : "name",
			width : "15%"
		}, {
			title : "状态",
			dataAttribute : "status",
			width : "15%"
		}, {
			title : "用户数量",
			dataAttribute : "userSize",
			width : "15%"
		}, {
			title : "创建人",
			dataAttribute : "creator",
			width : "15%"
		}, {
			title : "创建时间",
			dataAttribute : "dateOfCreate",
			dataType : 'datetime',
			width : "15%"
		}, {
			title : "操作",
			width : "25%",
			style : "operation-column",
			renderer : operation
		} ],
		remoteSort : true,
		pagination : true,
		paginationParam : {
			pageSize : 10,
			pagerLength : 5
		},
		emptyMsg : "查无结果"
	});

});