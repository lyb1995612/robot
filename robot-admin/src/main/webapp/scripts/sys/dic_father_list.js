$().ready(function() {

	$("#tp").datatable({
		data : {
			url : _path + "/dic/search",
			method : "POST",
			params : {

			}
		},
		columns : [ {
			title : "ID",
			dataAttribute : "id",
			dataType : "long",
			width : "10%"
		}, {
			title : "Code",
			dataAttribute : "code",
			width : "10%"
		}, {
			title : "名称",
			dataAttribute : "name",
			width : "20%"
		}, {
			title : "说明",
			dataAttribute : "memo",
			width : "30%"
		}, {
			title : "状态",
			dataAttribute : "status",
			width : "10%"
		}, {
			title : "操作",
			width : "20%",
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