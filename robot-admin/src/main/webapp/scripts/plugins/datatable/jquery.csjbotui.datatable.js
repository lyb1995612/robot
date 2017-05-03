/**
 * Paginate, sort, render html tables
 * 
 * @author cjay
 * @version 0.0.1-alpha1
 * 
 */
(function($) {

	function init(target, options) {
		$(target).data("options", options);
		// -------------------------------------------------------------------------------------------------------------------------------
		renderHeader(target, options);
		if(options.pagination) renderPaginationBar(target, options);
		// -------------------------------------------------------------------------------------------------------------------------------
		loadData(target, options);
	}

	function renderHeader(target, options) {
		var columns = options.columns || {};
		// -------------------------------------------------------------------------------------------------------------------------------
		var row = $("<tr></tr>");
		var headerBar = $("<thead></thead>").append(row);
		$.each(columns, function(i, column) {
			var header = $("<th>" + (column.title || ("column-" + i)) + "</th>");
			if (column.width) $(header).css({ width : column.width });
			if (column.hidden) $(header).addClass("hidden");
			if (column.dataAttribute) $(header).addClass(column.dataAttribute);
			if (column.sortable) {
				$(header).addClass("sortable");
				$(header).click(function(){
					sort(target, header, column.dataAttribute);
				});
			}
			// -------------------------------------------------------------------------------------------------------------------------------
			$(row).append(header);
		});
		// -------------------------------------------------------------------------------------------------------------------------------
		$(target).append(headerBar);
	}

	function sort(target, column, sorter){
		var sortOrder;
		// -------------------------------------------------------------------------------------------------------------------------------
		resetSorterImage(target, sorter);
		// -------------------------------------------------------------------------------------------------------------------------------
		if($(column).find(".sortOrderImage").length <= 0) {
			$(column).append("<span class=\"sortOrderImage\" >↓<span>");
			sortOrder = "DESC";
		} else {
			var sortOrderValue = $(column).find(".sortOrderImage").text();
			$(column).find(".sortOrderImage").empty();
			if(sortOrderValue == "↑" || sortOrderValue == "") {
				sortOrder = "DESC";
				$(column).find(".sortOrderImage").append("↓");
			} else if (sortOrderValue == "↓") {
				sortOrder = "ASC";
				$(column).find(".sortOrderImage").append("↑");
			}
		}
		var options = $(target).data("options");
		var params = {sorter : sorter, sortOrder : sortOrder};
		$(target).data("sorter", sorter);
		// -------------------------------------------------------------------------------------------------------------------------------
		loadData(target, options, params);
	}
	
	function resetSorterImage(target, sorter){
		var oldSorter = $(target).data("sorter");
		if(sorter == oldSorter) return;
		// -------------------------------------------------------------------------------------------------------------------------------
		if(oldSorter) {
			var column = $(target).find("." + oldSorter);
			$(column).find(".sortOrderImage").empty();
		}
	}
	
	function resetSorter(target){
		var querCondition = $(target).data("conditions") || {};
		querCondition.sorter = "";
		querCondition.sortOrder = "";
		// -------------------------------------------------------------------------------------------------------------------------------
		$(target).data("conditions", querCondition);
	}
	
	function renderPaginationBar(target, options) {
		var pager = $("<div class=\"text-center pager\">" 
						+ "<input type=hidden class=\"pageNow\" value=\"1\" />" 
						+ "<input type=hidden class=\"allPage\" value=\"0\" />" 
						+ "<input type=hidden class=\"pageStart\" value=\"0\" />" 
						+ "<ul class=\"pagination\" ></ul>" 
					+ "</div>");
		// -------------------------------------------------------------------------------------------------------------------------------
		$(target).after(pager);
	}
	
	function renderPager(target, options, dataSize) {
		var pager = $(target).next(".pager");
		var pagination = $(pager).find(".pagination");
		$(pagination).empty();
		// -------------------------------------------------------------------------------------------------------------------------------
		var paginationParam = options.paginationParam || {pageSize: 10, pagerLength: 5};
		var total = Math.ceil(dataSize / paginationParam.pageSize);
		// -------------------------------------------------------------------------------------------------------------------------------
		$(pager).find(".allPage").val(total);
		var pageno = $(pager).find(".pageNow").val() * 1;
		// -------------------------------------------------------------------------------------------------------------------------------
		var pageStart = (pageno - 1) * paginationParam.pageSize;
		$(pager).find(".pageStart").val(pageStart);
		// -------------------------------------------------------------------------------------------------------------------------------
		var end = total;
		var start = 1;
		if (pageno - (paginationParam.pagerLength -1) <= 0) {
			start = 1;
		} else {
			start = pageno;
		}
		// -------------------------------------------------------------------------------------------------------------------------------
		if (pageno + (paginationParam.pagerLength -1) < total) {
			end = pageno + (paginationParam.pagerLength -1);
			start = end - (paginationParam.pagerLength -1);
		} else {
			end = total;
			start = total - (paginationParam.pagerLength -1);
		}
		if (total <= paginationParam.pagerLength) {
			start = 1;
			end = total;
		}
		// -------------------------------------------------------------------------------------------------------------------------------------------------------------------
		var pagerBtn = $("<li><a title='跳转至首页' class='firstPage'>首页</a></li><li><a title='跳转至上一页' class='prePage'>上一页</a></li>");
		$(pagerBtn).find(".firstPage").unbind("click").click(function(){
			firstPage(pager);
			loadData(target, options);
		});
		$(pagerBtn).find(".prePage").unbind("click").click(function(){
			prePage(pager);
			loadData(target, options);
		});
		$(pagination).append(pagerBtn);
		// -------------------------------------------------------------------------------------------------------------------------------------------------------------------
		for ( var i = start; i <= end; i++) {
			pagerBtn = $("<li><a title='跳转至第" + i + "页' class='pageBtn' >" + i + "</a></li> ");
			if(i == pageno) {
				$(pagerBtn).find(".pageBtn").css({"color" : "red"});
			} 
			$(pagerBtn).find("a").unbind("click").click(function(){
				setPage(pager, this);
				loadData(target, options);
			});
			// -------------------------------------------------------------------------------------------------------------------------------------------------------------------			
			$(pagination).append(pagerBtn);
		}
		// -------------------------------------------------------------------------------------------------------------------------------------------------------------------			
		pagerBtn = $("<li><a title='跳转至下一页' class='nextPage'>下一页</a></li><li><a title='跳转至尾页' class='lastPage'>尾页</a></li>");
		$(pagerBtn).find(".nextPage").unbind("click").click(function(){
			nextPage(pager);
			loadData(target, options);
		});
		$(pagerBtn).find(".lastPage").unbind("click").click(function(){
			lastPage(pager);
			loadData(target, options);
		});
		$(pagination).append(pagerBtn);
	}
	
	
	function loadData(target, options, params) {
		var pager = $(target).next(".pager");
		// --------------------------------------------------------------------------------------------------------
		var pageNow = $(pager).find(".pageNow").val() * 1;
		var pageStart = $(pager).find(".pageStart").val() * 1;
		// --------------------------------------------------------------------------------------------------------
		var param = {pageSize: options.paginationParam.pageSize, pageNow: pageNow, pageStart: pageStart};
		$.extend(param, params);
		// --------------------------------------------------------------------------------------------------------
		var querier = $(target).data("conditions") || {};
		$.extend(querier, param);
		$.extend(querier, options.data.params);
		$(target).data("conditions", querier);
		// --------------------------------------------------------------------------------------------------------
		var url = options.data.url;
		// --------------------------------------------------------------------------------------------------------
		var lock = $(target).data("loaded") || false;
		if (!lock) {
			$(target).data("loaded", true);
			// --------------------------------------------------------------------------------------------------------
			$.ajax({
				type : options.data.method,
				contentType : 'application/json;charset=utf-8',
				url : url,
				data : JSON.stringify(querier),
				dataType : 'json',
				success : function(data) {
					var list = data["list"];
					var totalSize = data["totalSize"] || list.length;
					// --------------------------------------------------------------------------------------------------------
					renderResult(target, options, list);
					// --------------------------------------------------------------------------------------------------------
					renderPager(target, options, totalSize);
					// --------------------------------------------------------------------------------------------------------
					$(target).data("loaded", false);
				},
				error : function(xhr, msg) {
					if ("timeout" == msg || "parsererror" == msg) {
						csjbotui.ui.msg.alert("对不起，session过期，请重新登录!");
						$(document).on("click", "#closeModal", function(){
							window.location.reload();
						});
					} else {
						csjbotui.ui.msg.alert("对不起，表格数据加载失败!");
						// --------------------------------------------------------------------------------------------------------
						$(target).data("loaded", false);
						// $(target).mask('close');
					}
				}
			});
		}
	}

	function renderResult(target, options, data) {
		var columns = options.columns || {};
		// -------------------------------------------------------------------------------------------------------------------------------
		$(target).find('tbody').empty();
		// -------------------------------------------------------------------------------------------------------------------------------
		if(data.length > 0) {
			$.each(data, function(n, product) {
				var row = $("<tr></tr>");
				$.each(columns, function(i, column) {
					var cell = $("<td></td>");
					if(column.hidden) $(cell).addClass("hidden");
					if(column.style) $(cell).addClass(column.style);
					// -------------------------------------------------------------------------------------------------------------------------------
					var cellBody;
					if(column.dataAttribute != null) {
						var dataType = column.dataType || "string";
						if( dataType == "date" || dataType == "datetime" ) {
							if(product[column.dataAttribute] !='' && product[column.dataAttribute] !=null){
								cellBody = new Date(Number(product[column.dataAttribute])).Format( dataType == "date" ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm");
							}else{
								cellBody = '';
							}
						} else {
							cellBody = product[column.dataAttribute];
						}
					} else if(column.renderer != null) {
						var renderer = column.renderer;
						renderer(product, cell);
					};
					$(cell).append(cellBody);
					$(row).append(cell);
				});
				// -------------------------------------------------------------------------------------------------------------------------------
				$(target).append(row);
			});
		} else {
			$(target).append("<tr><td colspan=99><span>" + (options.emptyMsg || "no data found.") + "</span></td></tr>");
		}
		// -------------------------------------------------------------------------------------------------------------------------------
		
	}
	
	function firstPage(pager) {
		var pageno = $(pager).find(".pageNow").val() * 1;
		// --------------------------------------------------------------------------------------------------------------------------
		if (1 == pageno) {
			return;
		}
		pageno = 1;
		// --------------------------------------------------------------------------------------------------------------------------
		$(pager).find(".pageNow").val(pageno);
	}

	function prePage(pager) {
		var pageno = $(pager).find(".pageNow").val() * 1;
		// --------------------------------------------------------------------------------------------------------------------------
		if (1 == pageno) {
			return;
		}
		pageno--;
		// --------------------------------------------------------------------------------------------------------------------------
		$(pager).find(".pageNow").val(pageno);
	}

	function nextPage(pager) {
		var pageno = $(pager).find(".pageNow").val() * 1;
		var total = $(pager).find(".allPage").val() * 1;
		// --------------------------------------------------------------------------------------------------------------------------
		if (total == pageno) {
			return;
		}
		pageno++;
		// --------------------------------------------------------------------------------------------------------------------------
		$(pager).find(".pageNow").val(pageno);
	}

	function lastPage(pager) {
		var pageno = $(pager).find(".pageNow").val() * 1;
		var total = $(pager).find(".allPage").val() * 1;
		// --------------------------------------------------------------------------------------------------------------------------
		if (total == pageno) {
			return;
		}
		$(pager).find(".pageNow").val(total);
	}

	function setPage(pager, el) {
		var total = $(pager).find(".allPage").val() * 1;
		var pageTo = (el.innerText || el.text) * 1;
		// --------------------------------------------------------------------------------------------------------------------------
		if (pageTo > total) {
			pageTo = total;
		}
		if (pageTo < 1) {
			pageTo = 1;
		}
		// --------------------------------------------------------------------------------------------------------------------------
		$(pager).find(".pageNow").val(pageTo);
	}

	function reload(target, options) {
		resetPager(target);
		resetSorterImage(target);
		resetSorter(target);
		// --------------------------------------------------------------------------------------------------------------------------
		loadData(target, options);
	}
	
	function resetPager(target){
		var pager = $(target).next(".pager");
		// --------------------------------------------------------------------------------------------------------------------------
		$(pager).find(".pageNow").val(1);
		$(pager).find(".pageStart").val(0);
	}
	
	function load(target, param) {
		var options = $(target).data("options");
		// --------------------------------------------------------------------------------------------------------------------------
		resetPager(target);
		resetSorterImage(target);
		resetSorter(target);
		// --------------------------------------------------------------------------------------------------------------------------
		loadData(target, options, param);
	}

	$.fn.datatable = function(options, param) {
		if (typeof options == 'string'){
			var method = $.fn.datatable.methods[options];
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
	
	$.fn.datatable.methods = {
		reload: reload,
		load: load
			
	};

	$.fn.datatable.defaults = {
		data : {
			url : null,
			method : "GET",
			params : {}
		},
		columns : [ {
			title : "ID",
			hidden : true,
			dataAttribute : "id",
			dataType: "long",
			width : "15%",
			style : "",
			sortable : true
		}, {
			title : "产品名称",
			dataAttribute : "name"
		}, {
			title : "提交人",
			dataAttribute : "creator"
		}, {
			title : "类型",
			dataAttribute : "category"
		}, {
			title : "保存日期",
			dataAttribute : "dateOfUpdate",
			dataType: "date",
			sortable : true
		}, {
			title : "操作",
			renderer : function(data, el) {
				$(el).append("<a href=\"javascript:void(0);\"><span>详情</span></a><a href=\"javascript:void(0);\"><span>编辑</span></a><a href=\"javascript:void(0);\"><span>提交审核</span>");
			}
		} ],
		remoteSort : true,
		pagination : true,
		paginationParam : {
			pageSize : 10,
			pagerLength : 5
		}
	};

}(jQuery));