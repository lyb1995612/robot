(function($) {
	
	$.fn.extend({

		
	});
})(jQuery);


(function($) {
	
	function init(target, options) {
		open(target, msg);
	}

	function open(target, msg) {
		var container = $(target).find(".mask");
		// add Mask
		if (!container || !container[0]) {
			var container = $("<div class='mask' style='position:absolute; z-index:99999; height:40px; background:#FFF; border:1px solid #ACE'></div>");
			
			var mask = $("<div class=\"modal-backdrop in\"></div>");
			
			var body = $("<div class='mask-body' style='position:relative;text-align:center;' ></div>");
			if (msg) {
				body.append("<img class=\"mask-loading\" />").append("<span style='font-size:12px; margin-left:2px; vertical-align:text-top'>" + msg + "</span>");
			}
			$(target).append(container.append(mask).append(body));
			container.css("top", $(target)[0].offsetTop + ($(target)[0].offsetHeight - container[0].offsetHeight) / 2);
			container.css("left", $(target)[0].offsetLeft + ($(target)[0].offsetWidth - container[0].offsetWidth) / 2);
			container.css("padding-top", (container[0].offsetHeight - body[0].offsetHeight) / 2);
		}
		container.css("z-index", 99999);
	}
	
	function close(target, options) {
		$(target).find(".mask").remove();
	}
	
	$.fn.mask = function(options, param) {
		if (typeof options == 'string'){
			var method = $.fn.mask.methods[options];
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
	
	$.fn.mask.methods = {
		open: open,
		close: close
			
	};
	
	$.fn.mask.defaults = {
		msg : "loading"
	};

}(jQuery));