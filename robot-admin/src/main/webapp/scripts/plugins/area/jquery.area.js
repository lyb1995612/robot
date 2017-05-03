;$.extend({
	/**
	 * @author LIUQIANG
	 * @date 2014.12.16
	 * @version 0.0.1
	 * @description 地区两级联动控件
	 */
	showArea : function(options){
		if($("#areaModal") != null && $("#areaModal") != undefined)
		{
			$("#areaModal").remove();
		}
		//地区弹出层
		var div_province = '<div class="modal fade" id="areaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">'+
				'<div class="modal-dialog"><div class="modal-content"><div class="modal-header">'+
				'<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'+
				'<h4 class="modal-title" id="myModalLabel">选择地区</h4></div>'+
				'<div class="modal-body"><div class="row"><div class="col-md-6"><div class="form-group">'+
				'<div class="input-group"><div class="input-group-addon">省</div><select class="form-control" id="province" name="province">';
		
		var div_subArea = '</select></div></div></div>'+
				'<div class="col-md-6"><div class="form-group"><div class="input-group">'+
				'<div class="input-group-addon">市</div><select class="form-control" id="subArea" name="subArea">';
				
		var option_subArea = '<option value="-1">请选择</option>';
				
		var div_end = '</select></div></div></div></div></div><div class="modal-footer">'+
				'<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>'+
				'<button id = "save_area" type="button" class="btn btn-primary" data-dismiss="modal">保存</button>'+
				'</div></div></div></div>';
		var div = div_province + div_subArea + option_subArea + div_end;
		
		var list,result;
		
		var defaults = {
				callback:null
			};
		var config = $.extend({}, defaults, options);
		
		$('body').append(div);
		
		//初始化所有省份列表
		$.ajax({
			type: "GET",
			url: _path+"/masterdate/listProvinces",
			dataType: "json",
			cache: false,
			success: function(data){
				var html = '<option value="-1">请选择</option>';
				$.each(data.list, function(index, area){
					html = html + ' <option value="'+area.provinceCode+'">'+area.provinceName+'</option>' ;
				});
				$('#province').html(html);
			}
		});
		
		//获取所选省份中的所有市区（联动效果）
		$('#province').change(function(){
			var provinceCode = $('#province').val();
			if(provinceCode == -1){
				$('#subArea').html('<option value="-1">请选择</option>');
				$('#subArea').val(-1);
			}else{
				$.ajax({
					type: "GET",
					url: _path+"/masterdate/listByProvinceCode/"+provinceCode,
					dataType: "json",
					cache: false,
					success: function(data){
						list = data.list;
						var html = '<option value="-1">请选择</option>';
						$.each(data.list, function(index, area){
							html = html + ' <option value="'+area.id+'">'+area.name+'</option>' ;
						});
						$('#subArea').html(html);
					}
				});
			}
		});
		
		//暂存选择结果
		$('#subArea').change(function(){
			var value = this.value;
			var flag = false;
			$.each(list, function(index, area){
				if(area.id == value){
					flag = true;
					result = area;
				}
			});
			if(!flag){
				result = null;
			}
		});
		
		//
		$('#save_area').click(function(){
			if(result != null && result != undefined){
				config.callback(result);
				$('#areaModal').remove();
			}
		});
	}

	
	
	
	


});