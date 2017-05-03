var validator = null;
$().ready(function() {

	validator = $('form').validate({
		errorElement : 'span',
		errorClass : 'help-block',
		focusInvalid : false,
		rules : {
			name : {
				required : true,
				maxlength : 128
			},
			memo : {
				maxlength : 256
			}
		},
		messages : {
			name : {
				required : "角色名称是必填项"
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

});

function save() {
	if (validator.form()) {
		var submitting = $(".form").data("submitting") || false;
		// ------------------------------------------------------------------------------------------------------------------------------
		if (submitting) {
			csjbotui.ui.msg.alert("数据提交中，请耐心等待。。。");
			return;
		}
		// --------------------------------------------------------------------------------------------------------------------------------------------
		$(".form").data("submitting", true);
		// ------------------------------------------------------------------------------------------------------------------------------
		var param = $(".form").serializeJson();
		$.ajax({
			type : 'post',
			url : $(".form").attr('action'),
			dataType : 'json',
			contentType : 'application/json',
			data : JSON.stringify(param),
			success : function(data) {
				if (data.status == "S") {
					window.location = _path + "/role/list";
				} else if (data.status == "F") {
					alert(data.msg);
				}
				$(".form").data("submitting", false);
			},
			error : function(data) {
				$(".form").data("submitting", false);
			}
		});
	} 
}

function closePage() {
	history.go(-1);
}
