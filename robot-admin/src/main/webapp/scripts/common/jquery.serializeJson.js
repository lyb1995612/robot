/**
 * To serialize form data to JSON object or JSON string.
 * 
 * @author cjay
 */
(function($) {

	$.fn.serializeJson = function() {
		var serializeObj = {};
		var array = this.serializeArray();
		$(array).each(function() {
			if (serializeObj[this.name]) {
				if ($.isArray(serializeObj[this.name])) {
					serializeObj[this.name].push(this.value);
				} else {
					serializeObj[this.name] = [ serializeObj[this.name], this.value ];
				}
			} else {
				serializeObj[this.name] = this.value;
			}
		});
		// --------------------------------------------------------------------------------------------------------------------------------------
		return serializeObj;
	};

	$.fn.serializeJsonString = function() {
		var serializeObj = {};
		var array = this.serializeArray();
		var str = "{";
		$.each(array, function(n, object) {
			if (array[n].value != null && array[n].value != "") {
				str += "\"" + array[n].name + "\":\"" + array[n].value + "\"";
				if (n != (array.length - 1)) {
					str += ",";
				}
			}
		});
		str += "}";
		// --------------------------------------------------------------------------------------------------------------------------------------
		return str;
	};

})(jQuery);