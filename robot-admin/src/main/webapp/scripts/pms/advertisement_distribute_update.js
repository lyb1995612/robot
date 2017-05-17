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
		  var sn = $("#sn").val();
		//获取被选中多选按钮值 isChecked()
		  var arr=[];

		  $("input[type='checkbox']:checked").each(function(){ 
		       arr.push(this.value);
		  })
		  arr.push(sn);
		
		$.ajax({
						
            type: "POST",
            url: _path + "/ad/update",
            dataType: "json",
            contentType:"application/json",   
            data : JSON.stringify(arr),
            success: function(data){
            	if (data.msg == "S") {
            		csjbotui.ui.msg.alert({
            			msg : "修改广告分配成功!",
            			ok : function(){
            				window.location = _path + "/ad/list";
            			}
            		});
            	} else {
            		csjbotui.ui.msg.alert("修改广告分配失败!");
            	}
            },

		});

	}

});