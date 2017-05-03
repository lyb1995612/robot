function isEmpty(str) {
	if (str == null)
		return true;
	if (str === '')
		return true;
	return false;
}

function notEmpty(str) {
	return !isEmpty(str);
}

Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"H+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds()
	// 毫秒
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}

	return fmt;
};

String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
};

Array.prototype.contains = function(obj) {
	var i = this.length;
	while (i >= 0) {
		if (this[i] === obj) {
			return true;
		}
		i--;
	}
	return false;
};

// 将传入的值格式化为指定位数的值
// value:传入的值 decimal:小数位数 isRound:四舍五入（0：NO，1：YES）
function parseDecimal(value, decimal, isRound) {
	value = value + "";
	var strArr = value.split('.');
	if (strArr.length > 1) {
		if (decimal > 0) {
			if (isRound == 0) {
				if (strArr[1].length < decimal) {
					value = strArr[1];
					for ( var i = 0; i < decimal - strArr[1].length; i++) {
						value = value + "0";
					}
					value = strArr[0] + "." + value;
				} else {
					value = strArr[0] + "." + strArr[1].substr(0, decimal);
				}
			} else {
				var range = Math.pow(10, decimal);
				value = parseFloat(value) * (range * 10);
				value = (value + 5) / 10;
				value = parseFloat(parseInt(value)) / range;
			}
		} else {
			value = strArr[0];
		}
	}
	return value;
}

function numberize(obj) {
	var rtnval = '';
	value = obj.value;
	var dotted = false;
	// -----------------------------------------------------------------------------------------------------------------------------------------
	value = value.trim();
	if (value == "")
		return 0;
	// -----------------------------------------------------------------------------------------------------------------------------------------
	for ( var i = 0; i < value.length; i++) {
		var c = value.charAt(i);
		if (c >= '0' && c <= '9') {
			rtnval += c;
		} else if ((c == '.')) {
			if (dotted)
				break;
			rtnval += c;
			dotted = true;
		} // if
	} // for i
	if (rtnval.charAt(0) == '.')
		rtnval = "0" + rtnval;
	if (rtnval.charAt(rtnval.length - 1) == '.')
		rtnval = rtnval + 0;
	if (rtnval == "")
		rtnval = 0;
	// -----------------------------------------------------------------------------------------------------------------------------------------
	obj.value = rtnval;
}

function numberizeStr(obj) {
	var rtnval = '';
	value = obj.value;
	// -----------------------------------------------------------------------------------------------------------------------------------------
	value = value.trim();
	if (value == "")
		return 0;
	// -----------------------------------------------------------------------------------------------------------------------------------------
	for ( var i = 0; i < value.length; i++) {
		var c = value.charAt(i);
		if (c >= '0' && c <= '9') {
			rtnval += c;
		}
	} // for i
	if (rtnval == "")
		rtnval = "";
	// -----------------------------------------------------------------------------------------------------------------------------------------
	obj.value = rtnval;
}

function integralization(obj) {
	var rtnval = '';
	value = obj.value;
	// -----------------------------------------------------------------------------------------------------------------------------------------
	value = value.trim();
	if (value == "")
		return 0;
	// -----------------------------------------------------------------------------------------------------------------------------------------
	for ( var i = 0; i < value.length; i++) {
		var c = value.charAt(i);
		if (c >= '0' && c <= '9') {
			rtnval += c;
		}
	} // for i
	if (rtnval == "")
		rtnval = 0;
	// -----------------------------------------------------------------------------------------------------------------------------------------
	obj.value = rtnval;
}

function integralizationValue(value) {
	var rtnval = '';
	// -----------------------------------------------------------------------------------------------------------------------------------------
	value = value.trim();
	if (value == "")
		return 0;
	// -----------------------------------------------------------------------------------------------------------------------------------------
	for ( var i = 0; i < value.length; i++) {
		var c = value.charAt(i);
		if (c >= '0' && c <= '9') {
			rtnval += c;
		}
	} // for i
	if (rtnval == "")
		rtnval = 0;
	// -----------------------------------------------------------------------------------------------------------------------------------------
	return rtnval;
}

function getContextPath() {
	var location = (window.location + '').split('/');
	return location[0] + '//' + location[2] + '/' + location[3];
}

function closePage() {
	if (history.length > 0) {
		history.back();
	} else {
		window.location = _path;
	}
}
