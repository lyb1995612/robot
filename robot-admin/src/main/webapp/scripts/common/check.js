//check is null 
function isNull( str ){ 
	if ( str == "" ) return true; 
	var regu = "^[ ]+$"; 
	var re = new RegExp(regu); 
	return re.test(str); 
}
//正整数
function f_check_integer(obj)    
{           
    if (/^(\+|-)?\d+$/.test(obj)){    
       return true;    
    }else{    
        return false;    
    }    
}    
//ziranshu
function f_check_naturalnumber(obj)    
{           
    var s = obj;    
    if (/^[0-9]+$/.test( s ) && (s > 0))    
    {    
       return true;    
    }     
    else     
    {    
        f_alert(obj,"请输入自然数");    
        return false;    
    }    
}   

//判断是否是字母 
function isLetter( s ){
	var regu = "^[0-9a-zA-Z]+$"; 
	var re = new RegExp(regu); 
	if (re.test(s)) { 
		return true; 
	}else{ 
		return false; 
	}
}

//检查输入的电话号码格式是否正确
function checkPhone( strPhone ) { 
	var phoneRegWithArea = /^[0][0-9]{2,3}-[0-9]{5,10}$/; 
	var phoneRegNoArea = /^[0-9]{5,9}$/; 
	//var prompt = "您输入的电话号码不正确!" 
	if( strPhone.length > 9 ) { 
		if( phoneRegWithArea.test(strPhone) ){ 
			return true; 
		}else{ 
			//alert( prompt ); 
			return false; 
		}  
	}else{ 
		if( phoneRegNoArea.test( strPhone ) ){ 
			return true; 
		}else{ 
			//alert( prompt ); 
			return false; 
		} 
	} 
} 

//验证传真号
function checkFax(strFax) { 
	var phoneRegWithArea = /^[0][1-9]{2,3}-[0-9]{5,10}$/; 
	if( phoneRegWithArea.test(strFax) ){ 
		return true; 
	}else{ 
		return false; 
	}  
}


function checkURL(URL){
	var str=URL;
	var Expression=/http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/; 
	var objExp=new RegExp(Expression);
	if(objExp.test(str)==true){
	   return true;
	}else{
	   return false;
	}
}

//验证整数,因在此项目中，整数都double类型，所以此验证能通过的是整数或整数+.0
function isNumber( s ){   
	//var regu = "^[0-9]+$";
	var regu = "^[0-9]+[.0]*$"; 
	var re = new RegExp(regu); 
	if (re.test(s)) { 
		return true; 
	} else { 
		return false; 
	} 
} 
function isFloat( s ){   

var regu = "^[0-9]+[\.][0-9]{0,2}$"; 

var re = new RegExp(regu); 

if (re.test(s)) { 

return true; 

} else { 

return false; 

} 
}

//check mobilePhone
function checkMobile( s ){   
	var regu =/^[1][0-9]{10}$/; 
	var re = new RegExp(regu); 
	if (re.test(s)) { 
		return true; 
	}else{ 
		return false; 
	} 
}
// check email
function checkEmail(strEmail) { 
//var emailReg = /^[_a-z0-9]+@([_a-z0-9]+\.)+[a-z0-9]{2,3}$/; 
	var emailReg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/; 
	if( emailReg.test(strEmail) ){ 
		return true; 
	}else{ 
	//	alert("您输入的Email地址格式不正确！"); 
		return false; 
	} 
}

//check money
function isMoney( s ){   
	var regu = "^[0-9]+[\.][0-9]{0,8}$"; 
	var re = new RegExp(regu); 
	if (re.test(s)) { 
		return true; 
	} else { 
		return false; 
	} 
}

function isFloat( s ){   
	var regu = "^[0-9]+[\.][0-9]{0,8}$"; 
	var re = new RegExp(regu); 
	if (re.test(s)||s>0) { 
		return true; 
	} else { 
		return false; 
	} 
}

//删除字符串左边的空格
function ltrim(str) { 
	if(str.length==0)
		return(str);
	else {
		var idx=0;
		while(str.charAt(idx).search(/\s/)==0)
			idx++;
		return(str.substr(idx));
	}
}

//删除字符串右边的空格
function rtrim(str) { 
	if(str.length==0)
		return(str);
	else {
		var idx=str.length-1;
		while(str.charAt(idx).search(/\s/)==0)
			idx--;
		return(str.substring(0,idx+1));
	}
}

//删除字符串左右两边的空格
function trim(str) { 
	return(rtrim(ltrim(str)));
} 

//校验是否是实数
function isNum(num) {
	var re=new RegExp("^-?[\\d]*\\.?[\\d]*$");
	//var re=new RegExp("^(\d{1,10}.\d{1,10}|\d{1,10})$");
	if(re.test(num))
		return(!isNaN(parseFloat(num)));
	else
		return(false);
}
 
function f_check_naturalnumber(s){           
    if (/^[0-9]+$/.test( s ) && (s > 0))
    {    
       if(s>0){
        return true;    
       }else{
        return false;    
       }
    }     
    else{    
        return false;    
    }    
} 

function isNumberOrLetter(id,popMessage){//判断是否是数字或字母组成
				var idValue =document.getElementById(id).value;
				var regu="^[0-9a-zA-Z]+$";
				var re=new RegExp(regu);
				if(idValue==""){
					return true;
				}else{
					if(re.test(idValue)){
						return true;
					}else{
					alert(popMessage);
						return false;
					}
				}
			}
function isChinaOrNumbOrLett(id,popMessage){//判断是否是汉字、字母、数字、括号组成
				var idValue =document.getElementById(id).value; 
				var regu = "^[0-9a-zA-Z\u4e00-\u9fa5\(\)]+$"; 
				var re = new RegExp(regu);
				if(idValue==""){
					return true;
				}else{ 
					if (re.test(idValue)) { 
						return true; 
					}else{ 
					alert(popMessage);
						return false; 
					} 
				}
			} 
			
			
			function checkTwoDate(startDateId,endDateId,popMessage) {//判断开始时间结束时间
				var startDateIdValue =document.getElementById(startDateId).value;  
				var endDateIdValue =document.getElementById(endDateId).value;  
				if(startDateIdValue==""&&endDateIdValue!="") { 
				return true; 
				} else if(startDateIdValue!=""&&endDateIdValue=="") { 
				return true;
				} else if(startDateIdValue==""&&endDateIdValue=="") { 
				return true;
				} else if(startDateIdValue!=""&&endDateIdValue!=""&&startDateIdValue<endDateIdValue) { 
				return true;
				}else if(startDateIdValue!=""&&endDateIdValue!=""&&startDateIdValue>endDateIdValue) { 
				alert(popMessage); 
				return false; 
				} 
			}
			
			function checkMobilePhone(id,popMessage){ 
				var idValue =document.getElementById(id); 
				var regu =/^(13[0-9]{9}$)|(15[0-35-9][0-9]{8}$)|([0-9]{1}[0-9]{2,3}-[1-9]{1}[0-9]{5,8}$)|(18[05-9][0-9]{8}$)/; 
				var re = new RegExp(regu); 
				if (re.test(idValue.value)) {
				idValue.style.backgroundColor = '#A5FD9D'; 
				return true; 
				}else{ 
				idValue.style.backgroundColor = '#FFACAC';
				alert(popMessage);
				return false; 
				} 
			}
			function checkYT(id,popMessage){
			var idValue =document.getElementById(id);
				if(idValue.value==0){
				idValue.style.backgroundColor = '#FFACAC';
				alert(popMessage);
				return false;
				}else{
				idValue.style.backgroundColor = '#A5FD9D';
				return true;
				}
			}  
			
			function checkDanYinHao(id,popMessage){ 
				var idValue =document.getElementById(id);
				var regu =/^[^']*$/; 
				var re = new RegExp(regu); 
				if (re.test(idValue.value)) { 
				return true; 
				}else{
				idValue.style.backgroundColor = '#FFACAC'; 
				alert(popMessage);
				return false; 
				} 
			}
			function checkIsNotDigitalTwl(id,popMessage){
				var idValue =document.getElementById(id);
				var regu =/^\d{12}$/; 
				var re = new RegExp(regu); 
				if (re.test(idValue.value)) { 
				idValue.style.backgroundColor = '#A5FD9D';
				return true; 
				}else{
				idValue.style.backgroundColor = '#FFACAC'; 
				alert(popMessage);
				return false; 
				} 
			}
			function checkIsNotDigitalEig(id,popMessage){
				var idValue =document.getElementById(id);
				var regu =/^\d{8}$/; 
				var re = new RegExp(regu); 
				if (re.test(idValue.value)) { 
				idValue.style.backgroundColor = '#A5FD9D'; 
				return true;
				}else{
				idValue.style.backgroundColor = '#FFACAC'; 
				alert(popMessage);
				return false; 
				} 
			}
			
			function checkXSD(obj,size){
				 var value = $("#"+obj).val();
			     var length = $("#"+obj).val().length;
			     var last = value.lastIndexOf(".");//判断最后一个小数点所在的位置
			     if(last>0){
			    	 if(size == 1){
			    		 if(  (length - last) == (size+1) ){
			    			 // alert("yes");
			    		 }else{
			    			 return false;
			    		 }
			    	 }
			    	 if(size == 2){
			    		 if(  (length - last) == 3||(length - last) == 2 ){
			    			 // alert("yes");
			    		 }else{
			    			 return false;
			    		 }
			    	 }
			    	 
			     }
			     return true;
				 
			 }
			