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
		$('#number').val('');
	});
	
	function initOptions(){
		var queryData = {number:$("#number").val()};
		var options = {
				processing: true,
		        serverSide: true,
		    	ajax: {
		    		url:_path + "/scs/search",
		    		method: "POST",
		    		data: queryData,
		    		error: function(xhr, msg){
		    			alert("数据加载失败！");
		    		}
		    	},
				columns : [{
					title : "桌号",
					data : "number",
					width : "7%"
				}, {
					title : "桌号别名",
					data : "alias",
					width : "10%"
				}, {
					title : "桌位类型",
					data : "desk_type",
					width : "10%"
				}, {
					title : "备注",
					data : "memo",
					width : "10%"
				}, {
					title : "坐标X",
					data : "deskx",
					width : "7%"
				}, {
					title : "坐标Y",
					data : "desky",
					width : "7%"
				}, {
					title : "坐标Z",
					data : "deskz",
					width : "7%"
				}, {
					title : "坐标W",
					data : "deskw",
					width : "7%"
				}, {
					title : "坐标V",
					data : "deskv",
					width : "7%"
				}, {
					title : "坐标Q",
					data : "deskq",
					width : "7%"
				}, {
					title : "创建时间",
					data : "date_create",
					dataType : 'datetime',
					width : "10%"
				}, {
					title : "操作",
					width : "13%",
					style : "operation-column"
				}],
				columnDefs: [{ 
		               targets: [ 10 ],
		               render: function ( data, type, row ) {
		            	   var datetime = new Date(Number(row.date_create)).Format( "yyyy-MM-dd HH:mm");
		            	 //  alert(new Date(Number(row.date_create)).Format( "yyyy-MM-dd HH:mm"));
		                   return datetime;
		               }
				},
	           {
	               targets: [ 11 ],
	               render: operation,
	               orderable: false
	           }],
		        order: [[ 10, 'desc' ]],
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
                 + "<a class='opt' id='delete_"+row.id+"' href=\"javascript:void(0);\" ><span>删除</span></a>&nbsp;&nbsp;"
             
        $(document).off("click", "#detail_" + row.id).on("click", "#detail_" + row.id, function(){
        	window.location = _path + "/scs/" + row.id + "/toDeskDetail";
         });
	    $(document).off("click", "#delete_" + row.id).on("click", "#delete_" + row.id, function(){
	       	 csjbotui.ui.msg.confirm({
	 		        title : "确认删除",
					msg : "您确定要删除该桌号【"+row.number+"】?",
					ok:function(){
						 $.ajax({
			        		type : "POST",
			  	            url : _path + "/scs/" + row.id + "/deskDelete",
			  	            dataType : "json",
			  	            success : function(data){ 
			  	            	if (data.msg == "S") {
	  	            				window.location.href = _path + "/scs/list";
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
