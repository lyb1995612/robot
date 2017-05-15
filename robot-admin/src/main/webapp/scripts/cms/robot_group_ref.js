$().ready(function() {

	$(".btn.save").click(function() {
		var $form = $("#robotGroupForm");
		var submitting = $form.data("submitting") || false;
		if (submitting) {
			alert("数据加载中");
			return;
		}
		$form.data("submitting", true);
		$form.ajaxSubmit({
			dataType : 'json',
			success : function(data) {
				if (data.status == "S") {
					window.location = _path + "/rbg/list";
				} else if (data.status == "F") {
					alert(data.msg);
				}
				$form.data("submitting", false);
			}
		});
		
	});

});