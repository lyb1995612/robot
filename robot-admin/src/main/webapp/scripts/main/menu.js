
$(function() {
	$(document).on("click", "#dashboard", function(){
		location.href = _path+"/pmd/dashboard";
	});
	
	$(document).on("click", "#sidebar-collapse", function(){
		if(!$("#sidebar").hasClass("menu-min")){
			$.cookie("menumin", "1", { expires: 7, path: '/admin/' });  
		}else{
			 $.cookie("menumin", "0", { expires: 7, path: '/admin/' });  
		}
    });
	
	var menuflag = $.cookie("menumin");
	if(menuflag == "1"){
		$("#sidebar").addClass("menu-min");
		$("#sidebararrow").removeClass().addClass("icon-double-angle-right");
	}else{
		$("#sidebar").removeClass("menu-min");
		$("#sidebararrow").removeClass().addClass("icon-double-angle-left");
	}
});

