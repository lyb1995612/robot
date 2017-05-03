/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
jQuery.extend(jQuery.validator.messages, {
        required: "必选字段",
		remote: "请修正该字段",
		email: "请输入正确格式的电子邮件",
		url: "请输入合法的网址",
		date: "请输入合法的日期",
		dateISO: "请输入合法的日期 (ISO).",
		number: "请输入合法的数字",
		digits: "只能输入整数",
		creditcard: "请输入合法的信用卡号",
		equalTo: "请再次输入相同的值",
		accept: "请输入拥有合法后缀名的字符串",
		maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
		minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
		rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
		range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
		max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
		min: jQuery.validator.format("请输入一个最小为 {0} 的值")
});

jQuery.extend(jQuery.validator.defaults, {
    errorElement: "span"
});

jQuery.validator.addMethod("isEnglishNum", function(value, element) {   
    var englishNum =  /^[0-9a-zA-Z]+$/;
    return this.optional(element) || (englishNum.test(value));
}, "只能输入字母和数字");

jQuery.validator.addMethod("isEnglishNumSymbol", function(value, element) {   
    var englishNum =  /^[0-9a-zA-Z_]+$/;
    return this.optional(element) || (englishNum.test(value));
}, "只能输入字母、数字和下划线");

jQuery.validator.addMethod("isPassword", function(value, element) {   
    var password = /^[a-zA-Z0-9_!@#$%^&*()+.~`,<>{}\|-]+$/;
    return this.optional(element) || (password.test(value));
}, "请按照规则输入密码");

jQuery.validator.addMethod("isLowercaseNum", function(value, element) {   
    var englishNum =  /^[0-9a-z]+$/;
    return this.optional(element) || (englishNum.test(value));
}, "只能输入小写字母和数字");

jQuery.validator.addMethod("isVersionNum", function(value, element) {   
    var englishNum = /^[\x00-\xff]+$/;
    return this.optional(element) || (englishNum.test(value));
}, "不能输入中文、中文标点");

jQuery.validator.addMethod("spaceNotAllow", function(value, element) {   
    var englishNum = /^[^\s]+$/;
    return this.optional(element) || (englishNum.test(value));
}, "输入的内容不能有空格");

jQuery.validator.addMethod("isFloatNum2", function(value, element) {   
    var englishNum =  /^[1-9]{1}[0-9]{0,10}(\.[0-9]{0,2})?$/;
    return this.optional(element) || (englishNum.test(value));
}, "请输入数字, 支持2位小数, 整数位不能超过11位");

jQuery.validator.addMethod("isFloatNum", function(value, element) {   
    var englishNum =  /^[1-9]{1}[0-9]{0,10}(\.[0-9]{0,1})?$/;
    return this.optional(element) || (englishNum.test(value));
}, "请输入数字, 支持1位小数, 整数位不能超过11位");
jQuery.validator.addMethod("isFloatNum3", function(value, element) {   
    var englishNum =  /^[\-0-9]+$/;
    return this.optional(element) || (englishNum.test(value));
}, "请输入数字, 只能输入整数");

