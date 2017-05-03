<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../common/meta.jsp" />
    <jsp:include page="../common/resources.jsp" />
    <script>
        function operation(data, el) {
        
                // -------------------------------------------------------------------------------------------------------------------------------------------------------
                $editor = $("<a class=\"edit\" href=\"javascript:void(0);\"><span>编辑</span></a>");
                $(el).append($editor);
                $(el).find(".edit").unbind("click").click(function() {
                    window.location = _path + "/dic/" + data.id + "/sonedit";
                });
                
            }
        
        $(function(){
            $("#sys_dictionary").addClass("active");
            $("#sys").addClass("open");
            $("#sys").addClass("active");
        });
    </script>
    <script src="${path }/scripts/sys/dic_son_list.js"></script>
    <script src="${path }/scripts/plugins/datatable/jquery.csjbotui.datatable.js"></script>
    
    <style>
        .operation-column a {
          padding-right: 10px;
        }
        
        .panel-body .row {
          margin: 0px;
        }
        
        .action-bar {
          margin: 5px 0px;
        }
    </style>
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
                        <li class="active">系统配置</li>
                        <li><a href="${path}/dic/list">字典信息</a></li>
                        <li class="active">数据查看</li>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content">
                    <div style="height: 40px;"></div> 
                    <input type="hidden" value="${sysdata.id}" id="father"/>
                    <div class="panel panel-default">
		                <div class="panel-heading panel-title">
		                    <table style="width: 100%;">
		                    <tr>
		                        <td style="width: 4%;text-align: left;" nowrap="nowrap" >
		                        <a  class="btn btn-primary" type="button" href="javascript:window.history.back();" >返回</a></td>
		                        <td class="panel-title" style="width: 96%;text-align: center;font-weight:bold">${sysdata.code}&nbsp;${sysdata.name}</td>
		                    </tr></table>
		                 </div>
		            </div>
		            <div class="row">
			            <div class="col-md-12 text-right action-bar">
			              <input type="button" class="btn btn-large btn-primary add-data" value="新增数据" /> 
			            </div>
			        </div>
		          
		            <div class="row">
			            <div class="col-xs-12">
			              <table id="tp" class="table table-striped table-hover table-bordered table-responsive">
			              </table>
			            </div>
		            </div>
                    
                </div><!-- /.page-content -->
            </div><!-- /.main-content -->
            
        </div>
        
    </div><!-- /.main-container-inner -->
    
    <jsp:include page="../common/footer.jsp" />
</body>
</html>