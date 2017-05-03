<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="org.apache.shiro.subject.Subject"%>
<%
  Subject subject = SecurityUtils.getSubject();
%>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../common/meta.jsp" />
    <jsp:include page="../common/resources.jsp" />
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
    <script src="${path }/scripts/sys/dic_father_list.js"></script>
    <script src="${path }/scripts/plugins/datatable/jquery.csjbotui.datatable.js"></script>
    <script>
        function operation(data, el) {

                // -------------------------------------------------------------------------------------------------------------------------------------------------------
                $editor = $("<a class=\"sonlist\" href=\"javascript:void(0);\"><span>关联数据</span></a>");
                $(el).append($editor);
                $(el).find(".sonlist").unbind("click").click(function() {
                    window.location = _path + "/dic/" + data.id + "/sonlist";
                });
            }
        
        $(function(){
            $("#sys_dictionary").addClass("active");
            $("#sys").addClass("open");
            $("#sys").addClass("active");
        });
    </script>
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
                        <li class="active">字典信息</li>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content">
                    <div style="height: 40px;"></div>  
					<div class="panel panel-default">
					  <div class="panel-heading panel-title">字典信息</div>
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