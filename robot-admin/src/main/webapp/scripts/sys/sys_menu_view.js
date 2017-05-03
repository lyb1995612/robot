$(function(){
    $("#sys_menu").addClass("active");
    
    var DataSourceTree = function(options) {
        this._data  = options.data;
        this._delay = options.delay;
    };
    DataSourceTree.prototype.data = function(options, callback) {
        //var self = this;
        var $data = null;

        if(!("name" in options) && !("type" in options)){
            $data = this._data;//the root tree
            callback({ data: $data });
            return;
        }
        else if("type" in options && options.type == "folder") {
            if("additionalParameters" in options && "children" in options.additionalParameters) {
                $data = options.additionalParameters.children;
            } else {
            	$data = {};//no data
            }
        }
        
        if($data != null) {//this setTimeout is only for mimicking some random delay
            setTimeout(function(){callback({ data: $data });} , parseInt(Math.random() * 500) + 200);
        }
    };
    
	$.ajax({
		url : _path + "/menu/listMenus",
		type : "POST",
		dataType : "json",
		success : function(data){
			 if (data.msg == "S") {
				 var treeDataSource2 = new DataSourceTree({data: data.content});
                 $("#menuTree").ace_tree({
                    dataSource: treeDataSource2 ,
                    loadingHTML:'<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
                    'open-icon' : 'icon-folder-open',
                    'close-icon' : 'icon-folder-close',
                    'selectable' : false,
                    'selected-icon' : null,
                    'unselected-icon' : null
                 });
			 } else {
				 csjbotui.ui.msg.alert("加载菜单失败！");
			 }
			 
		},
		error : function(msg, xhr, thrown){
			if ("timeout" == msg || "parsererror" == msg) {
                csjbotui.ui.msg.alert({
        			msg : "对不起，session过期，请重新登录!",
        			ok : function(){
        				window.location.reload();
        			}
        		});
            } else {
                csjbotui.ui.msg.alert("Internal Server Error!");
            }
		}
	});
   
});