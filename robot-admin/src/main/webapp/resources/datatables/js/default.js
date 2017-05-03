
$.extend( true, $.fn.dataTable.defaults, {
    searching: false,                    // 搜索
    ordering: true,                      // 排序
    autoWidth: true,                     // 自适应宽度
    lengthMenu: [10, 25, 50, 100 , 200] ,   // PageSize 下拉框
    pagingType: 'full_numbers',          // 分页样式: numbers , simple, simple_numbers, full, full_numbers
    stateSave: true,                     // cookie缓存页数 和 PageSize
    language: {
        lengthMenu: "每页 _MENU_ 条记录",
        zeroRecords: "没有找到记录",
        //info: "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
        info: "第 _PAGE_ 页 ( 总共 _PAGES_ 页，_TOTAL_条记录 )",
        emptyTable: "没有找到任何记录！",
        infoEmpty: "无记录",
        infoFiltered: "(从 _MAX_ 条记录过滤)",
        paginate: {
            previous: "&laquo;",
            next: "&raquo;",
            first: "首页",
            last: "尾页"
        },
        search: "过滤"
    }
});
	
	

