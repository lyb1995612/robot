<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<script src="${path }/scripts/main/menu.js" ></script>
<a class="menu-toggler" id="menu-toggler" href="#"> <span
	class="menu-text"></span>
</a>
<div class="sidebar sidebar-fixed" id="sidebar">

    <!--  menu  -->
	<ul class="nav nav-list" id="menuContainer">
	   
	</ul>
	<!-- /.nav-list -->
    
    <script type="text/javascript">
    
	    $(document).on("click", ".submenu li", function(){
	        var activeId_2 = $(this).attr("id");    
	        var activeId_1 = $(this).parent().parent().attr("id");
	        $.cookie("activeId_1", activeId_1, { path: _admin });
	        $.cookie("activeId_2", activeId_2, { path: _admin });  
	    });
	    
	    $(document).on("click", ".menu-item", function(){
            var activeId_1 = $(this).attr("id");    
            $.cookie("activeId_1", null, { path: _admin });
            $.cookie("activeId_2", activeId_1, { path: _admin });  
        });
	    
	    $(document).on("click", "#logout", function(){
            $.cookie("activeId_1", null, { path: _admin });
            $.cookie("activeId_2", null, { path: _admin });  
        });
	    
	    $.ajax({
	    	url : _path + "/menu/showMenus",
	    	type : "POST",
	    	dataType : "json",
	    	success : function(data){
	    		 if (data.msg == "S") {
	                 $("#menuContainer").html(data.content);
	                 var activeId_1 = $.cookie("activeId_1");
	                 var activeId_2 = $.cookie("activeId_2");
	                
	                 $("#" + activeId_2).addClass("active");
	                 $("#" + activeId_1).addClass("open");
	                 $("#" + activeId_1).addClass("active");
	             } else {
	                 csjbotui.ui.msg.alert("加载菜单失败！");
	             }
	    	},
	    	error : function(msg, xhr, error){
	    		if ("timeout" == msg || "parsererror" == msg) {
	                csjbotui.ui.msg.alert({
	                    msg : "对不起，session过期，请重新登录!",
	                    ok : function(){
	                        window.location.reload();
	                    }
	                });
	            } else {
	                csjbotui.ui.msg.alert("Internal Server Error!");
	               //window.location.reload();
	            }
	    	}
	    });
	    
    </script>
    
	<div class="sidebar-collapse" id="sidebar-collapse">
		<i id="sidebararrow" class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	</div>
	
</div>
