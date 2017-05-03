$().ready(function() {

	$(".searchform .searchBtn").click(function() {
		var param = $(".searchform").serialize2Json();
		$("#tp").datatable('load', param);
	});

	$(".searchform .reset").click(function() {
		$(".searchform")[0].reset();
		$("#isValid").val('-1');
		$("#type").val('-1');
		$(".dk_label").html("全部");
		$(".dk_options_inner li a").each(function(){
			if($(this).val()=="全部") {
				$(this).parent().addClass("dk_option_current");
			} else {
				$(this).parent().removeClass("dk_option_current");
			}
		});
	});

	$(".add-permission").click(function() {
		window.location = _path + "/permission/toAdd";
	});

	$("#tp").datatable({
		data : {
			url : _path + "/permission/page",
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
			title : "权限名称",
			dataAttribute : "name",
			width : "15%"
		}, {
			title : "代号",
			dataAttribute : "code",
			width : "15%"
		}, {
			title : "状态",
			dataAttribute : "status",
			width : "5%"
		}, {
			title : "备注",
			dataAttribute : "memo",
			width : "15%"
		}, {
			title : "类型",
			dataAttribute : "typeName",
			width : "10%"
		}, {
			title : "创建人",
			dataAttribute : "creator",
			width : "15%"
		}, {
			title : "创建时间",
			dataAttribute : "dateOfCreate",
			dataType : 'datetime',
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