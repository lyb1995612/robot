    var pageno = 1;
	var pageSize = 5;
	var total = 0;
	function firstPage(){
		if(1 == pageno){
			return;
			
		}	
		pageno = 1;
		getdata();
	}

	function prePage(){	
		if(1 == pageno){
			return;
		}	
		pageno--;
		getdata();
	}

	function nextPage(){
		if(total == pageno){
			return;
		}
		
		pageno++;
		getdata();
	}

	function lastPage(){
		if(total == pageno){
			return;
		}
		
		pageno = total;
		
		getdata();
	}

	function setPage(pageTo){
		if(pageTo>total){
			pageTo = total;
		}
		if(pageTo < 1){
			pageTo = 1;
		}
		pageno = pageTo;
		getdata();
	}

function queryPage(ctotal,flag){
	if(flag == 1){
		if(ctotal==0){
			ctotal=1;
		}
		$("#allPage").val(ctotal);  
		total = ctotal;
	}
	var end = total;
	var start = 1;
	if(pageno-4<=0){
		start = 1;
	}else{
		start = pageno;
	}
	
	if(pageno+4<total){
		end = pageno+4;
		start = end-4;
	}else{
		end = total;
		start = total-4;
	}
	if(total<=5){
		start=1;
		end=total;
	}
	var html = "";
	html ="<li><a title='Go to first page' href='#' onclick='firstPage();'>首页</a></li><li><a title='Go to pre page' href='#' onclick='prePage();'>上一页</a></li>";
	for(var i=start;i<=end;i++){
		if(i==pageno){
			html+= "<li><a style=\"color:red\" title='Go to "+i+" page' href='#' onclick='setPage("+i+");'>"+i+"</a></li> ";	
		}else{
			html+= "<li><a title='Go to "+i+" page' href='#' onclick='setPage("+i+");'>"+i+"</a></li> ";
		}
	}	
	html += "<li><a title='Go to next page' href='#' onclick='nextPage();'>下一页</a></li><li><a title='Go to last page' href='#' onclick='lastPage();'>尾页</a></li>";
    $("#pagination").html(html);
}

function czGetData(){
	 pageno=1;
	 total=0;
	 getdata();
}

