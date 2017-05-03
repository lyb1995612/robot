customPlugin = {
    loadchartModel : function(id, prefix, height) {
        $("#"+id).html("<div id='"+prefix+"model' class=\"reveal-modal\" style=\"top: -50px; left: 60%;     margin-left: -100px;\">"+
        "<h4>自选时间段：</h4>"+
        "<form  id='"+prefix+"datepicker_form' class=\"form\" >"+
             "<div class=\"col-md-6\">"+
                "<div class=\"form-group\">"+
                    "<div class=\"input-group\">"+
                        "<div class=\"input-group-addon\">开始时间</div>"+
                        "<input  class=\"form-control datepicker \" type=\"text\" id='"+prefix+"startTime' name='"+prefix+"startTime' placeholder=\"请选择时间\">"+    
                    "</div>"+
                "</div>"+
            "</div>"+
            "<div class=\"col-md-6\">"+
                "<div class=\"form-group\">"+
                    "<div class=\"input-group\">"+
                        "<div class=\"input-group-addon\">结束时间</div>"+
                        "<input  class=\"form-control datepicker \" type=\"text\" id='"+prefix+"endTime' name='"+prefix+"endTime' placeholder=\"请选择时间\">"+
                    "</div>"+
                "</div>"+
            "</div>"+
        "</form>"+
         "<a class=\"close-reveal-modal\" id='"+prefix+"modelCancel'>&#215;</a>"+
         "<div class=\"col-xs-12 text-right\"><input type=\"button\" value=\"确认\"  class=\"btn btn-primary\" id='"+prefix+"btnConfirm' />"+
       " <input type=\"button\" value=\"取消\" style=\"margin-left:10px\" class=\"btn btn-info\" id='"+prefix+"btnCancel'/></div>"+
      "</div>"+
      "<a href='#' data-reveal-id='"+prefix+"model' id='"+prefix+"anytime' hidden=\"hidden\"></a> "+
    "<br>"+
    "<div class=\"row\">"+
        "<div class=\"col-xs-12 pull-right\">"+
                "<div id='"+prefix+"timeForm' class=\"input-group pull-right\" style='width:200px;'>"+
                    "<div class=\"input-group-addon\">显示时间段:</div>"+
                    "<select class=\"form-control\" id='"+prefix+"showtime' name='"+prefix+"showtime'>"+
                        " <option value='30' id='"+prefix+"option30' selected >过去30天</option>"+
                        " <option value='90' id='"+prefix+"option90'>过去90天</option>"+
                        " <option value='365' id='"+prefix+"option365'>过去一年</option>"+
                        " <option value='0' id='"+prefix+"option0'>自选时间段</option>"+
                    "</select>"+
           "</div>"+
        "</div>"+
    "</div>"+
    "<div id='"+prefix+"title'></div>"+
    "<div id='"+prefix+"report' style='width:100%;height:"+height+"px;' class=\"report\"></div>"
    );
        this.datetimepicker(prefix,"startTime");
        this.datetimepicker(prefix,"endTime");
    },
      getValidObj : function(prefix){
    	  var rules = '{"' + prefix + 'startTime" :{"required" : true}, "' + prefix + 'endTime" :{"required" : true} }';
  		  var messages = '{"' + prefix + 'startTime" :{"required" : "必须选择开始时间"}, "' + prefix + 'endTime" :{"required" : "必须选择结束时间"} }';
  	      var validateObj ={
  	            errorElement : 'span',  
  	          errorClass : 'help-block',  
  	          focusInvalid : false,  
  	           highlight : function(element) {  
  	              $(element).closest('.form-group').addClass('has-error');  
  	          },
  	          success : function(label) {  
  	              label.closest('.form-group').removeClass('has-error');  
  	              label.remove();  
  	          }, 
  	          errorPlacement : function(error, element) {  
  	              element.parent('div').parent('div').append(error);  
  	          }
  	          
  	      };
  	      validateObj.messages=JSON.parse(messages);
  	      validateObj.rules=JSON.parse(rules);
  	      return validateObj;
  	      
      },
    
    getDateString : function(d){
    	  if(!d) throw new Error("输入日期对象");
    	  var year = d.getFullYear();
          var month = d .getMonth()+1;
          var day = d.getUTCDate();
          var dateString =  year +"-"+ (month<10?(0+""+month):month) +"-"+ (day<10?(0+""+day):day);
          return dateString;
    },
    //获取数组中最大的值在数组中的index，只使用于数组中的数值都为正(若要适用于任何实数可用Number.MIN_VALUE代替0)
    max : function(arr){
        var max = 0;
        var j = 0;
        if(arr&&arr.length){
            for(var i = 0; i<arr.length;i++){
                if(max < arr[i]) {
                    j = i;
                    max = arr[i];
                }
             }
        }
        return j;
    },
    
    //获取数组中的最小值对应的index(相同的取前面的)
    min:function(arr) {
    	var index = 0;
    	if(arr && arr.length) {
    		var min = Math.min.apply(this, arr);
    		for(var i =0;i<arr.length; i++){
        		if(min == arr[i]){
        			index = i;
        			break;
        		}
        	}
    	}
    	
    	return index;
    },
    //获取数组中第N大的值的index,只适用于positive number
    maxN : function(arr,n){
        var array = arr.slice(0);
        var index = 0;
        for(var j = 0 ; j< n; j++) {
            index = this.max(array);
            array.splice(this.max(array), 1, 0);
        }
        return index;
    },
    datetimepicker :function(prefix,position){
    	$("#"+prefix+position).datetimepicker({
    		language : 'zh-CN',
    		format : 'yyyy-mm-dd',
    		autoclose : true,
    		todayBtn : true,
    		todayHighlight : 1,
    		startView : 2,
    		minView : 2
    	});
    },
    dateTransform: function(str){
    	if(str && str.indexOf("月")>0) {
    		var d = new Date();
    		var y = d.getFullYear();
    		var m = str.substring(0,str.indexOf("月"));
    		var d = str.substring(str.indexOf("月")+1, str.length-1);
    		return y+"-"+(m.length==1?(0+m):m) +"-"+( d.length==1?(0+d):d);
    	}
    }
    	
//    $().ready(function() {
//    	
//    	$(".timeEnd").datetimepicker({
//    		language : 'zh-CN',
//    		format : 'yyyy-mm-dd',
//    		autoclose : true,
//    		todayBtn : true,
//    		todayHighlight : 1,
//    		startView : 2,
//    		minView : 2
//    	});
//    });

};