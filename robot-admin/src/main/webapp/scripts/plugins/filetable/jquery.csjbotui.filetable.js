/**
 * 
 * @author cjay
 */

(function($) {

	function init(target, options) {
		$(target).data("options", options);
		// -------------------------------------------------------------------------------------------------------------------------------
		// 绑定add button事件
		$(target).find(".add").unbind("click").click({
			target : target,
			options : options
		}, addRow);
		// 绑定delete button事件
		$(target).find(".delete").unbind("click").click({
			target : target,
			options : options
		}, deleteRow);
	}

	function resetTableIndex(target, options) {
		var modelName = options.modelName;
		// ---------------------------------------------------------------------------------------------------------------
		$.each($(target).find(".item"), function(i, row) {
			var id = modelName + "-" + i + "-id";
			var fileName = modelName + "-" + i + "-name";
			var file = modelName + "-" + i + "-file";
			// ---------------------------------------------------------------------------------------------------------------
			$(row).find(".id").attr("id", id).attr("name", id);
			$(row).find(".name").attr("id", fileName).attr("name", fileName);
			$(row).find(".file").attr("id", file).attr("name", file);
		});

	}

	function deleteRow(e) {
		var _target = e.data.target, _options = e.data.options || {
			modelName : "Attachment",
			maxDataSize : 20
		};
		// -----------------------------------------------------------------------------------------------------------------------------------------
		var size = $(_target).find("#" + _options.modelName + "-totalSize").val() * 1 - 1;
		$(this).parent().parent().remove();
		$(_target).find("#" + _options.modelName + "-totalSize").val(size);
		// -----------------------------------------------------------------------------------------------------------------------------------------
		resetTableIndex(_target, _options);
	}

	function addRow(e) {
		var _target = e.data.target, _options = e.data.options || {
			modelName : "Attachment",
			maxDataSize : 20
		};
		// -----------------------------------------------------------------------------------------------------------------------------------------
		var fileNum = $(_target).find(".item").length;
		if (fileNum >= _options.maxDataSize) {
			csjbotui.ui.msg.alert("最多上传" + _options.maxDataSize + "个文件");
			return;
		}
		// -----------------------------------------------------------------------------------------------------------------------------------------
		var row = $('<div class="row item">' 
					+ '<input class="hidden id" type="hidden" id="' + _options.modelName + '-' + fileNum + '-id" name="' + _options.modelName + '-' + fileNum + '-id" value="-1" />'
					+ '<div class="col-md-6 col-md-left">'
						+ '<div class="form-group">' 
							+ '<div class="input-group">' 
							+ '<div class="input-group-addon">文件名称</div>' 
								+ '<input class="form-control name" id="' + _options.modelName + '-' + fileNum + '-name" name="' + _options.modelName + '-' + fileNum + '-name" placeholder="请输入文件名称" maxlength=20 >' 
							+ '</div>' 
						+ '</div>'
					+ '</div>' 
					+ '<div class="col-md-5 col-md-middle">' 
						+ '<div class="form-group">' 
							+ '<input class="form-control file" type="file" id="' + _options.modelName + '-' + fileNum + '-file" name="' + _options.modelName + '-' + fileNum + '-file" accept=".docx,.xlsx,.doc,.xls,.pdf,.rar,.ppt,.pptx,.AVI,.FLV,.WMV,.3GP,.MPG,.MPGE,.MP4,.VOB,.RM,.RMVB">' 
						+ '</div>' 
					+ '</div>'
					+ '<div class = "col-md-1 col-md-right">' 
						+ '<input type="button" class="btn btn-info delete" value="删除" />' 
					+ '</div>' 
				+ '</div>');
		$(_target).append(row);
		// -----------------------------------------------------------------------------------------------------------------------------------------
		$(row).find(".delete").unbind("click").click(e.data, deleteRow);
		// -----------------------------------------------------------------------------------------------------------------------------------------
		var fileSize = $(_target).find("#" + _options.modelName + "-totalSize").val() * 1 + 1;
		$("#" + _options.modelName + "-totalSize").val(fileSize);

	}

	function resetRowSize(table) {

	}

	function validate(target) {
		var options = $(target).data("options");
		// -----------------------------------------------------------------------------------------------------------------------------------------
		var totalSize = $(target).find("#" + options.modelName + "-totalSize").val();
		for (i = 0; i < totalSize; i++) {
			var file = "input[name='" + options.modelName + "-" + i + "-file']";
			var id = parseInt($("input[name='" + options.modelName + "-" + i + "-id']").val());
			var filepath = $(file).val();
			if (id == -1 || filepath != "") {
				var pos = filepath.lastIndexOf(".");
				var suffix = filepath.substring(pos + 1).toUpperCase();
				if (!options.supportedFileTypes.contains(suffix)) {
					return false;					
				}
			}
		}
		// -----------------------------------------------------------------------------------------------------------------------------------------
		return true;
	}

	$.fn.filetable = function(options, param) {
		if (typeof options == 'string') {
			var method = $.fn.filetable.methods[options];
			if (method) {
				return method(this, param);
			} else {
				return;
			}
		}
		options = options || {};

		return this.each(function() {
			init(this, options);
		});
	};

	$.fn.filetable.methods = {
		validate : validate

	};

	$.fn.filetable.defaults = {
		modelName : "Attachment",
		title : "文件列表",
		maxDataSize : 20,
		supportedFileTypes : [ 'AVI', 'FLV', 'WMV', '3GP', 'MPG', 'MPGE', 'MP4', 'VOB', 'RM', 'RMVB', 'DOC', 'DOCX', 'PDF', 'PPT', 'PPTX', 'XLS', 'XLSX', 'RAR' ]
	};

}(jQuery));