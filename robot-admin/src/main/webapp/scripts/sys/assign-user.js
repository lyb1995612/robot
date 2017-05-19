$().ready(function() {

	$(".searchform .searchBtn").click(function() {
		var param = $(".searchform").serialize2Json();
		search(param);
	});

	$(".searchform .reset").click(function() {
		$(".searchform")[0].reset();
	});

	$(".btn.save").click(function() {
		save();
	});

	function search(param) {
		var submitting = $("#tp").data("submitting") || false;

		if (submitting) {
			alert("数据加载中");
			return;
		}
		$("#tp").data("submitting", true);

		$.ajax({
			type : 'post',
			url : $("#tp").attr('data-url'),
			dataType : 'json',
			contentType : 'application/json',
			data : JSON.stringify(param),
			success : function(data) {
				renderTable(data);
				$("#tp").data("submitting", false);
			},
			error : function(data) {

				$("#tp").data("submitting", false);
			}
		});

		function renderTable(data) {
			var $table = $("#tp tbody");
			// -----------------------------------------------------------------------------------------------------------------------------------------------
			$table.empty();
			// -----------------------------------------------------------------------------------------------------------------------------------------------
			$.each(data.list, function(i, data) {
				var $row = $("<tr></tr>");
				$row.append("<td><input class=\"ace\" type=\"checkbox\" id=\"UserRoleRef-" + i + "-checked\" name=\"UserRoleRef-" + i + "-checked\" value=\"true\" " + ((data.checked == true) ? "checked" : "") + " /><input type=\"hidden\" id=\"UserRoleRef-" + i + "-id\" name=\"UserRoleRef-" + i + "-id\" value=\"" + data.id + "\" /><input type=\"hidden\" id=\"UserRoleRef-" + i + "-user_fk\" name=\"UserRoleRef-" + i + "-user_fk\" value=\"" + data.user_fk + "\" /></td>");
				$row.append("<td>" + data.account + "</td>");
				$row.append("<td>" + data.username + "</td>");
				$row.append("<td>" + data.cellphone + "</td>");
				$row.append("<td>" + data.userStatus + "</td>");
				// -----------------------------------------------------------------------------------------------------------------------------------------------
				$table.append($row);
			});
			// -----------------------------------------------------------------------------------------------------------------------------------------------
			if (data.list.length == 0) {
				var $row = $("<tr><td colspan=99>查无结果</td></tr>");
				$table.append($row);
			}
			// -----------------------------------------------------------------------------------------------------------------------------------------------
			$("#UserRoleRef-totalSize").val(data.list.length);
		}
	}

	function save() {
		var $form = $(".roleUserForm");
		var submitting = $form.data("submitting") || false;
		// -----------------------------------------------------------------------------------------------------------------------------------------------
		if (submitting) {
			alert("数据加载中");
			return;
		}
		$form.data("submitting", true);
		// -----------------------------------------------------------------------------------------------------------------------------------------------
		$form.ajaxSubmit({
			// url : _path + ,
			dataType : 'json',
			success : function(data) {
				if (data.status == "S") {
					window.location = _path + "/role/list";
				} else if (data.status == "F") {
					alert(data.msg);
				}
				$form.data("submitting", false);
			}
		});
	}

});