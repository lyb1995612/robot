$(function() {
	// -------------选择广告类型----------------------------------------------------------------------
	$("select").change(function() {
		var type = $(this).val();
		if (type == "0") {
			$("#image,#audio,#video").show();
		} else if (type == "1") {
			$("#image,#audio").show();
			$("#video").hide();
		} else if (type == "2") {
			$("#image,#audio").hide();
			$("#video").show();
		}
	});
	
	validator = $('#advertisement_form').validate({
		errorElement : 'span',
		errorClass : 'help-block',
		focusInvalid : false,
		rules : {
			name : {
				required : true,
				rangelength : [ 0, 25 ]
			},
			type : {
				required : true
			}
		},
		messages : {
			name : {
				required : "必须填写广告名称！"
			},
			type : {
				required : "必须选择广告类型！"
			}
		},
		highlight : function(element) {
			$(element).closest('.form-group').addClass('has-error');
		},
		success : function(label) {
			label.closest('.form-group').removeClass('has-error');
			label.remove();
		},
		errorPlacement : function(error, element) {
			element.parent('div').parent('div').append(error);
		}

	});

	$("#submit").click(function() {
		if (validator.form()) {
			csjbotui.ui.msg.waiting({
				title : "正在新增广告",
				msg : "请稍等..."
			});
			$("#advertisement_form").ajaxSubmit({
				type : "POST",
				url : _path + "/adv/add",
				dataType : "json",
				success : function(data) {
					csjbotui.ui.msg.waiting.remove();
					if (data.msg == "S") {
						csjbotui.ui.msg.alert({
							msg : "新增产品成功!",
							ok : function() {
								window.location = _path + "/adv/list";
							}
						});
					} else {
						csjbotui.ui.msg.alert("新增广告失败!");
					}
				},
				error : function(xhr, msg, error) {
					csjbotui.ui.msg.waiting.remove();
					if ("timeout" == msg || "parsererror" == msg) {
						csjbotui.ui.msg.alert({
							msg : "对不起，session过期，请重新登录!",
							ok : function() {
								window.location.reload();
							}
						});
					} else {
						csjbotui.ui.msg.alert("Internal Server Error!");
					}
				}
			});
		}
	});
});