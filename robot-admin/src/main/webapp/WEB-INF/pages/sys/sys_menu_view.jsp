<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../common/meta.jsp" />
    <jsp:include page="../common/resources.jsp" />
    <script type="text/javascript" src="${path}/resources/ace/js/fuelux.tree.min.js"></script>
    <script src="${path}/scripts/sys/sys_menu_view.js"></script>
</head>
  
<body class="navbar-fixed">
    <jsp:include page="../common/header.jsp" />
    <div class="main-container">
        <div class="main-container-inner">  
            <jsp:include page="../common/menu.jsp" />
            
            <div class="main-content" id="mainContent">
                <div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
            
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home home-icon"></i>
                            <a href="${path}/">首页</a>
                        </li>
                        <li class="active">菜单管理</li>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content">
                    <div style="height: 40px;"></div>
                    <div class="panel panel-default">
		                 <div class="panel-heading panel-title">
		                    <div class="row">
		                      <div class="col-xs-6">菜单管理</div>
		                      <div class="col-xs-6 text-right action-bar">
		                         <shiro:hasPermission name="modify:menu_management">
		                              <a type="button" class="btn btn-primary" href="${path}/menu/toAddMenu" >新增菜单</a>
		                         </shiro:hasPermission>
		                      </div>
		                    </div>                                    
                         </div>
		            </div>
		            
		            <div class="row" style="margin-top:5px;">
		                <div class="col-xs-12">
		                   <div class="col-xs-12">
		                       <div><a id="openAll" style="cursor:pointer"><i id="node" class="icon-plus"></i></a></div>
		                       <script>
			                       $("#openAll").click(function(){
			                           if(($("#node").attr("class"))=="icon-plus"){
					                    	    $(".icon-folder-close").click();
					                    	   	$("#node").attr("class", "icon-minus"); 
				                    	   }else{
				                    	        $(".icon-folder-open").click();
					                    	   	$("#node").attr("class", "icon-plus"); 
				                    	   }
			                       });
		                       </script>
	                           <div id="menuTree" class="tree tree-unselectable"></div>
	                           <div style="height: 50px;"></div>
	                       </div>
		                </div>
		            </div><!--/row-->
                    
                </div><!-- /.page-content -->
            </div><!-- /.main-content -->
            
        </div>
        
    </div><!-- /.main-container-inner -->
    
    <jsp:include page="../common/footer.jsp" />
</body>
</html>
