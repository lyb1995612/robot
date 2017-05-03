/**
 * 
 * @author cjay
 */

(function($) {
	
	function init(target, options) {
		var _modelName = options.modelName,
				_dataLimit = options.dataLimit;
		var _table = $("#" + _modelName + "List");
		
		$(_table).data("options", options);
		
		// 当阶梯佣金的金额单位变化时，变化表格中所有金额单位
		$("#" + _modelName + "-tmpl-measureUnit_fk").change(function() {
			var value = $(this).val();
			$.each($(_table).find(".measureUnit_fk"), function(i, compo){
				$(compo).val(value);
			});
		});

		// 绑定佣金区间最大值中的输入事件。这里用blur事件代替change事件主要是因为IE浏览器下onchange事件存在bug
		var a = $(target).find("tr[class='item']");
		$.each($(target).find("tr[class='item']"), function(i, row){
			$(row).find(".value1").blur(function() {
				var nextRow = $(this).parentsUntil("tr").parent().next();
				$($(nextRow).find(".value1")).val($(this).val());
			});
			
			$(row).find(".value2").blur(function() {
				var nextRow = $(this).parentsUntil("tr").parent().next();
				$($(nextRow).find(".value1")).val($(this).val());
			});
			
			$(row).find(".row-remove").unbind("click").click(function() {
				var nextRow = $(row).next();
				var rowIndex = $(row).attr("rowindex");
				$(row).remove();
				// reset table size.
				resetRowSize(_table);
				resetRowIndex(_table, rowIndex);
				resetRowValue(nextRow);
				// --------------------------------------------------------------------------------------------------------------------------------------------
				return false;
			});
			
		});
		
		
		$(".numberbox").unbind("keyup").keyup(function() {
			this.value = this.value.replace(/[^\d\.]/g, '');
			if(this.value != ""){
				$(this).parent().removeClass('has-error');
				$(this).next(".error-tip").remove();
			}
		});
		
		// 绑定addRow button事件
		$(target).find(".addRow").unbind("click").click({target: _table, options: options, }, addRow);
	}
	;

	/**
	 * 新增数据事件方法
	 */
	function addRow(e) {
		var _table = e.data.target,
			_dataLimit = e.data.options.dataLimit,
			_modelName = e.data.options.modelName;
		
		_table.options = e.data.options;
		
		var tmpl = $(_table).find("tr:hidden")[0].innerHTML;
		var rowNum = $(_table).find("tr:not(:hidden)").length;
		if (rowNum >= _dataLimit) {
			alert("最多可设定20条浮动佣金规则.");
			return;
		}
		tmpl = tmpl.replaceAll("@ROWINX@", rowNum - 1);
		var row = $("<tr rowindex=" + ( rowNum - 1 ) + " class=\"item\" >" + tmpl + "</tr>");
		var lastRow = $(_table).find("tr:last");
		$(lastRow).before(row);
		
		// 设置表格数据大小
		$("#" + _modelName + "-totalSize").val(rowNum + 1);
		
		// 按照逻辑规则设置上一笔和下一笔的值
		resetRowValue(row);
		
		// 设置最后一笔记录的id和name的索引值
		resetLastRowIndex(_table);
		
		// 设置模板中的值是为解决新增一行中单位的数据
		$("#" + _modelName + "-" + (rowNum - 1) + "-measureUnit_fk").val($("#" + _modelName + "-tmpl-measureUnit_fk").val());
		
		// 设置新增的栏位中所有数字框只能输入数字
		$(row).find(".numberbox").unbind("keyup").keyup(function() {
			this.value = this.value.replace(/[^\d\.]/g, '');
			if(this.value != ""){
				$(this).parent().removeClass('has-error');
				$(this).next(".error-tip").remove();
			}
		});

		$(row).find(".value2").unbind("blur").blur(function() {
			var nextRow = $(row).next();
			$($(nextRow).find(".value1")).val($(this).val());
		});
		
		// 绑定移除按钮的事件
		$(row).find(".row-remove").unbind("click").click(function() {
			var row = $(this).parentsUntil("tr").parent();
			var nextRow = $(row).next();
			var rowIndex = $(row).attr("rowindex");
			$(row).remove();
			// reset table size.
			resetRowSize(_table);
			resetRowIndex(_table, rowIndex);
			resetRowValue(nextRow);
			// --------------------------------------------------------------------------------------------------------------------------------------------
			return false;
		});

	}

	function resetRowSize(table) {
		var options = $(table).data("options") || {};
		// --------------------------------------------------------------------------------------------------------------------------------------------
		var rowNum = $(table).find("tr:not(:hidden)").length;
		$("#" + options.modelName + "-totalSize").val(rowNum);
	}

	function resetLastRowIndex(table) {
		var options = $(table).data("options") || {};
		var _modelName = options.modelName;
		// ----------------------------------------------------------------------------------		
		var lastRow = $(table).find("tr:last");
		var rows = $(table).children("tbody").children("tr:not(:hidden)");
		var rowNum = rows.length;
		var i = rows.length - 1;
		// ----------------------------------------------------------------------------------
		var cols = $(lastRow).find("td");
		// ----------------------------------------------------------------------------------
		// set row number.
		$(cols[0]).html(i + ". ");
		// ----------------------------------------------------------------------------------
		$.each($(lastRow).find(".field"), function(n, field){
			var fieldName = $(field).attr("name");
			var names = fieldName.split("-");
			var name = _modelName + "-" + i + "-" + names[2];
			
			$(field).attr("id", name).attr("name", name);
		});
		/**
		// reset operator1 id & name.
		var component = $(lastRow).find(".operator1");
		if(component) $(component).attr("id", _modelName + "-" + i + "-operator1").attr("name", _modelName + "-" + i + "-operator1");
		// reset value1 id & name.
		component = $(lastRow).find(".value1");
		if(component) $(component).attr("id", _modelName + "-" + i + "-value1").attr("name", _modelName + "-" + i + "-value1");
		// reset measureUnit_fk id & name.
		component = $(lastRow).find(".measureUnit_fk");
		if(component) $(component).attr("id", _modelName + "-" + i + "-measureUnit_fk").attr("name", _modelName + "-" + i + "-measureUnit_fk");
		// reset extraCommission id & name.
		component = $(lastRow).find(".extraCommission");
		if(component) $(component).attr("id", _modelName + "-" + i + "-extraCommission").attr("name", _modelName + "-" + i + "-extraCommission");
		*/
	}

	function resetRowValue(currentRow) {
		var prevRow = $(currentRow).prev();
		var nextRow = $(currentRow).next();
		var prevValue = $(prevRow).find(".value2").length == 0 ?  $($(prevRow).find(".value1")).val(): $($(prevRow).find(".value2")).val();
		var nextValue = $(nextRow).find(".value1").length == 0 ? null : $($(nextRow).find(".value1")).val();	
		$(currentRow).find(".value1").val(prevValue);
		if($(currentRow).find(".value2").length > 0) $(currentRow).find(".value2").val(nextValue);
	}

	function resetRowIndex(table, rowIndex) {
		var options = $(table).data("options") || {};
		var _modelName = options.modelName;
		// ----------------------------------------------------------------------------------
		var rows = $(table).children("tbody").children("tr:not(:hidden)");
		var rowNum = rows.length;
		if (rowNum >= 2 && rowIndex > 0) {
			for ( var i = rowIndex; i < rowNum; i++) {
				var row = rows[i];
				$(row).attr("rowindex", i);
				var cols = $(row).find("td");
				// ----------------------------------------------------------------------------------
				// set row number.
				$(cols[0]).html(i + ". ");
				// ----------------------------------------------------------------------------------
				$.each($(row).find(".field"), function(n, field){
					var fieldName = $(field).attr("name");
					var names = fieldName.split("-");
					var name = _modelName + "-" + i + "-" + names[2];
					
					$(field).attr("id", name).attr("name", name);
				});
				// ----------------------------------------------------------------------------------
				/**
				// reset operator1 id & name.
				var component = $(row).find(".operator1");
				if(component) $(component).attr("id", _modelName + "-" + i + "-operator1").attr("name", _modelName + "-" + i + "-operator1");
				// reset value1 id & name.
				component = $(row).find(".value1");
				if(component) $(component).attr("id", _modelName + "-" + i + "-value1").attr("name", _modelName + "-" + i + "-value1");
				// reset operator2 id & name.
				component = $(row).find(".operator2");
				if(component) $(component).attr("id", _modelName + "-" + i + "-operator2").attr("name", _modelName + "-" + i + "-operator2");
				// reset value2 id & name.
				component = $(row).find(".value2");
				if(component) $(component).attr("id", _modelName + "-" + i + "-value2").attr("name", _modelName + "-" + i + "-value2");
				// reset measureUnit_fk id & name.
				component = $(row).find(".measureUnit_fk");
				if(component) $(component).attr("id", _modelName + "-" + i + "-measureUnit_fk").attr("name", _modelName + "-" + i + "-measureUnit_fk");
				// reset extraCommission id & name.
				component = $(row).find(".extraCommission");
				if(component) $(component).attr("id", _modelName + "-" + i + "-extraCommission").attr("name", _modelName + "-" + i + "-extraCommission");
				*/
			}
		}
	}
	
	function validate(target, options) {
		var table = $(target);
		// -----------------------------------------------------------------------------------------------------------------------------------------
		var isValid = true;
		// -----------------------------------------------------------------------------------------------------------------------------------------
		$.each($(table).find("tr:not(:hidden)"), function(i, tr) {
			$.each($(tr).find("input"), function(i, comp) {
				if ($(comp).val() == "") {
					isValid = false;
					// -----------------------------------------------------------------------------------------------------------------------------------------
					$(comp).parent().addClass('has-error');
					if($(comp).next(".error-tip").length == 0) $(comp).after('<span for="' + $(comp).attr("id") + '" class="error-tip">此为必填项</span>');
				} else {
					$(comp).parent().removeClass('has-error');
					$(comp).next(".error-tip").remove();
				}
			});

			if($(tr).find(".value2").length > 0) {
				var value1 = $(tr).find(".value1").val() * 1;
				var value2 = $(tr).find(".value2").val() * 1;
				if(value2 <= value1) {
					$(tr).find(".value2").parent().addClass('has-error');
					if($(tr).find(".value2").next(".error-tip").length == 0) $(tr).find(".value2").after('<span for="' + $(tr).find(".value2").attr("id") + '" class="error-tip">此栏位值需大于前一栏位值</span>');
					// -----------------------------------------------------------------------------------------------------------------------------------------			
					isValid = false;
				} else {
					$(tr).find(".value2").parent().removeClass('has-error');
					$(tr).find(".value2").next(".error-tip").remove();
				}
			};
			// -----------------------------------------------------------------------------------------------------------------------------------------			
			if (isValid == false) {
				return false;
			}
		});
		// -----------------------------------------------------------------------------------------------------------------------------------------
		return isValid;
	}
	
	$.fn.floatingrategrid = function(options, param) {
		if (typeof options == 'string'){
			var method = $.fn.floatingrategrid.methods[options];
			if (method){
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
	
	$.fn.floatingrategrid.methods = {
		validate: validate
			
	};
	
	$.fn.floatingrategrid.defaults = {
		modelName : "FloatingRate",
		title : "阶梯利率设置",
		dataLimit: 20
	};

}(jQuery));