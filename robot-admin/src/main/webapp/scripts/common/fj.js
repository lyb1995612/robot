function fj(obj){
	var values, index;  
	 values = $(obj).serializeArray();
	 var data = "{";
	 for (index = 0; index < values.length; ++index){  
		 if(values[index].value!=null&&values[index].value!=""){
			 data += "\""+values[index].name +"\":";
			 var dh = ",";
//		 	 if(values[index].value>0){
//			 	data += values[index].value+dh;
//			 }else{
//
//			 }
			 data +="\""+ values[index].value+"\""+dh;
		 }
	 }  
	 if(data.lastIndexOf(",")>0){
		 data = data.substring(0,data.lastIndexOf(","));
	 }
	 data += "}";
	
	 data = fj_Special(data);
	 
	 return data;
	 
} 

function fj_Special(str)
{
	var reg=new RegExp("\r\n","g"); 
	return str= str.replace(reg,"\\r\\n");
}

function fj2(obj) {
	var serializeObj={};
	var array=$(obj).serializeArray();
	$(array).each(function(){
		if(serializeObj[this.name]){
			if($.isArray(serializeObj[this.name])){
				serializeObj[this.name].push(this.value);
			}else{
				serializeObj[this.name]=[serializeObj[this.name],this.value];
			}
		}else{
			if(this.name!=null&&this.value!=""&&this.name!="multiselect"&&this.name!="roleIds"
				&&this.name!="permissionIds"){
				serializeObj[this.name]=this.value;
			}
			if(this.name!=null&&(this.name=="roleIds"||this.name=="permissionIds")){
				serializeObj[this.name]=[this.value];
			}
			
		}
	});
	return serializeObj;// JSON.stringify(serializeObj);
}


function fjN(obj) {
	var serializeObj={};
	var array=$(obj).serializeArray();
	$(array).each(function(){
		if(serializeObj[this.name]){
			if($.isArray(serializeObj[this.name])){
				serializeObj[this.name].push(this.value);
			}else{
				serializeObj[this.name]=[serializeObj[this.name],this.value];
			}
		}else{
			if(this.name!=null&&this.value!=""){
				serializeObj[this.name]=this.value;
			}
			
		}
	});
	return serializeObj;// JSON.stringify(serializeObj);
}

function fj2pops(obj) {
	var serializeObj={};
	var array=$(obj).serializeArray();
	$(array).each(function(){
		if(serializeObj[this.name]){
			if($.isArray(serializeObj[this.name])){
				serializeObj[this.name].push(this.value);
			}else{
				serializeObj[this.name]=[serializeObj[this.name],this.value];
			}
		}else{
			if(this.name!=null&&this.value!=""&&this.name!="qty"&&this.name!="propId"
				&&this.name!="permissionIds"){
				serializeObj[this.name]=this.value;
			}
			if(this.name!=null&&(this.name=="qty"||this.name=="propId")){
				serializeObj[this.name]=[this.value];
			}
			
		}
	});
	return serializeObj;// JSON.stringify(serializeObj);
}

