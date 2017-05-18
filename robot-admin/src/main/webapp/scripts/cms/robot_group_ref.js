$().ready(function() {
	$(".searchform .searchBtn").click(function() {
		var param = $(".searchform").serialize2Json();
		search(param);
	});

	$(".searchform .reset").click(function() {
		$(".searchform")[0].reset();
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
				$row.append("<td><input type=\"checkbox\" id=\"CmsRobotGroupRef-" + i + "-checked\" name=\"CmsRobotGroupRef-" + i + "-checked\" value=\"true\" " + ((data.checked == true) ? "checked" : "") + " />" +
						        "<input type=\"hidden\" id=\"CmsRobotGroupRef-" + i + "-group_fk\" name=\"CmsRobotGroupRef-" + i + "-group_fk\" value=\"" + data.robot_id + "\" />" +
						        "<input type=\"hidden\" id=\"CmsRobotGroupRef-" + i + "-id\"  value=\"" + data.id + "\" />" +
						        "<input type=\"hidden\" id=\"CmsRobotGroupRef-" + i + "-sn\"  value=\"" + data.sn + "\" /></td>");
				$row.append("<td>" + data.sn + "</td>");
				$row.append("<td>" + data.type_name + "</td>");
				// -----------------------------------------------------------------------------------------------------------------------------------------------
				$table.append($row);
			});
			// -----------------------------------------------------------------------------------------------------------------------------------------------
			if (data.list.length == 0) {
				var $row = $("<tr><td colspan=99>查无结果</td></tr>");
				$table.append($row);
			}
			// -----------------------------------------------------------------------------------------------------------------------------------------------
			$("#robotGroupRef-totalSize").val(data.list.length);
		}
	}

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