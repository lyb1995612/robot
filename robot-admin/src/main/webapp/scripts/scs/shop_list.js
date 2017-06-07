var table = "";
var initable = "";

$(function() {
	// ----------------条件搜索--------------------------------------------------------------------------
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
	
	// ----------------重置搜索条件-----------------------------------------------------------------------
	$('#resetButton').click(function() {
		$('#shop_name').val('');
		$('#shop_code').val('');
	});
	
	function initOptions(){
		var queryData = {shop_name:$("#shop_name").val(),shop_code:$("#shop_code").val()};
		var options = {
				processing: true,
		        serverSide: true,
		    	ajax: {
		    		url:_path + "/shop/search",
		    		method: "POST",
		    		data: queryData,
		    		error: function(xhr, msg){
		    			alert("数据加载失败！");
		    		}
		    	},
				columns : [{
					title : "商铺id",
					data : "id",
					width : "9%"
				}, {
					title : "商铺编码",
					data : "shop_code",
					width : "13%"
				}, {
					title : "商铺名称",
					data : "shop_name",
					width : "13%"
				}, {
					title : "商铺地址",
					data : "shop_address",
					width : "20%"
				}, {
					title : "备注",
					data : "memo",
					width : "15%"
				}, {
					title : "创建时间",
					data : "date_create",
					dataType : 'datetime',
					width : "15%"
				}, {
					title : "操作",
					width : "15%",
					style : "operation-column"
				}],
				columnDefs: [/*{
					targets : [ 0 ], //将商铺id这列隐藏起来
					visible : false
				},*/{ 
		        	targets: [ 5 ],
		        	render: function ( data, type, row ) {
		        		var datetime = new Date(Number(row.date_create)).Format( "yyyy-MM-dd HH:mm");
		        		return datetime;
		        	}
				},{
	            	targets: [ 6 ],
	            	render: operation,
	            	orderable: false
				}],
		        order: [[ 1, 'asc' ]],
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
    var editor = "<a class='opt' id='detail_"+row.id+"' href=\"javascript:void(0);\" ><span>详情</span></a>&nbsp;&nbsp;"
    + "<a class='opt' id='edit_"+row.id+"' href=\"javascript:void(0);\" ><span>编辑</span></a>&nbsp;&nbsp;"	            
    + "<a class='opt' id='delete_"+row.id+"' href=\"javascript:void(0);\" ><span>删除</span></a>&nbsp;&nbsp;"
   
    $(document).off("click", "#detail_" + row.id).on("click", "#detail_" + row.id, function(){
    	window.location = _path + "/shop/" + row.id + "/shopDetail";
    });
    
    $(document).off("click", "#edit_" + row.id).on("click", "#edit_" + row.id, function() {
		window.location = _path + "/shop/" + row.id + "/shopUpdate";
	});
    
	$(document).off("click", "#delete_" + row.id).on("click", "#delete_" + row.id, function(){
		csjbotui.ui.msg.confirm({
			title : "确认删除",
			msg : "您确定要删除商铺【"+row.shop_name+"】吗?",
			ok:function(){
				$.ajax({
					type : "POST",
					url : _path + "/shop/" + row.id + "/shopDelete",
					dataType : "json",
					success : function(data){ 
						if (data.msg == "S") {
							window.location.href = _path + "/shop/list";
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