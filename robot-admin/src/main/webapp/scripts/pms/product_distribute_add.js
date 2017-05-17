$().ready(function() {

	$(".btn.save").click(function() {
		save();
	});

//  $('input[type=checkbox]').iCheck({
//    checkboxClass: 'icheckbox_flat-blue',
//    increaseArea: '20%' // optional
//});

$("#select-all").on('ifChanged', function(event){
    var checked = $("#select-all").is(":checked");
    $.each($("#tp tbody tr input[type='checkbox']"), function(i, box) {
        if(checked){
            $(box).iCheck('check');
        }else{
            $(box).iCheck('uncheck');
        }
    });
});

	function save() {
		
		//获取下拉框值
		  var sn = $("#cmsRobot").val();
		//获取被选中多选按钮值 isChecked()
		  var arr=[];

		  $("input[type='checkbox']:checked").each(function(){ 
		       arr.push(this.value);
		  })
		  arr.push(sn);
//		  var json = JSON.stringify(arr);//数组转换成json
//		  alert(json);
		
//		var $form = $(".pms_productForm");
//		var submitting = $form.data("submitting") || false;
//		// -----------------------------------------------------------------------------------------------------------------------------------------------
//		if (submitting) {
//			alert("数据加载中");
//			return;
//		}
		
		$.ajax({
						
            type: "POST",
            url: _path + "/pd/add",
            dataType: "json",
            contentType:"application/json",   
            data : JSON.stringify(arr),
            success: function(data){
//            	csjbotui.ui.msg.waiting.remove();
            	if (data.msg == "S") {
            		csjbotui.ui.msg.alert({
            			msg : "新增产品分配成功!",
            			ok : function(){
            				window.location = _path + "/pd/list";
            			}
            		});
            	} else {
            		csjbotui.ui.msg.alert("新增产品分配失败!");
            	}
            },
						
//			url: _path +"/pd/add" ,
//			type:'post',
//			dataType : 'json',
//			data:json,
//			success : function(data) {
//				if (data.status == "S") {
//					window.location = _path + "/pd/list";
//				} else if (data.status == "F") {
//					alert(data.msg);
//				}
//			}
		});
//		$form.data("submitting", true);
//		
//		// -----------------------------------------------------------------------------------------------------------------------------------------------
//		$form.ajaxSubmit({
//			 url : _path +"/pd/add" ,
//			dataType : 'json',
//			//data : JSON.stringify({ "name": "cxh", "sex": "man" }),
//			//type:post,
//			//contentType : "application/json" ,
//			success : function(data) {
//				if (data.status == "S") {
//					window.location = _path + "/pd/list";
//				} else if (data.status == "F") {
//					alert(data.msg);
//				}
//				$form.data("submitting", false);
//			}
//		});
	}

});