/**
 * 
 * @author cjay
 */

(function($) {
	$.fn.serialize2Json = function() {
		var json = {};
		$(this.serializeArray()).each(function() {
			json[this.name] = this.value;
		});
		return json;
	};
})(jQuery);


var csjbotui = csjbotui || {};

(function(BUI) {

	BUI.isEmpty = function(str) {
		if (str == null)
			return true;
		if (str == '')
			return true;
		// -------------------------------------------------------------------------------------------------------------------------
		return false;
	};

	BUI.bool = function(str) {
		if (str == 'Y' || str == 'YES')
			return true;
		if (str > 1)
			return true;
		// -------------------------------------------------------------------------------------------------------------------------
		return false;
	};

}(csjbotui));

/**
 * 数据处理
 */
csjbotui.data = csjbotui.data || {};

(function($, BUI) {

	BUI.data.request = function(url, method, param, callback, settings) {
		$.ajax({
			type : method,
			contentType : 'application/json;charset=utf-8',
			url : url,
			data : !(settings && settings.rawParam) ? JSON.stringify(param) : param,
			dataType : 'json',
			success : function(data) {
				if (callback)
					callback.success(data);
			},
			error : function(data) {
				if (callback)
					callback.error(data);
			}
		});
	};	
	
	BUI.data.get = function(url, param, callback, settings) {
		BUI.data.request(url, 'GET', param, callback, settings);
	};
	
	BUI.data.post = function(url, param, callback, settings) {
		BUI.data.request(url, 'POST', param, callback, settings);
	};
	
}(jQuery, csjbotui));
