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
    <script src="${path }/scripts/sys/permission_list.js"></script>
    <script src="${path }/scripts/plugins/datatable/jquery.csjbotui.datatable.js"></script>
    <style>
      .dk_container{
          line-height:19px;
       }
       .opt{
          display: inline-block;
          width: 50px;
          padding: 5px;
      }
    </style>
    <script>
        function operation(data, el) {

                var $editor = $("<a class=\"detail opt\" href=\"javascript:void(0);\"><span>详情</span></a>");
                $(el).append($editor);
                $(el).find(".detail").unbind("click").click(function() {
                    window.location = _path + "/permission/" + data.id + "/detail";
                });
                <% 
                if(subject.isPermittedAll("modify:permission")) {
                %>
                // -------------------------------------------------------------------------------------------------------------------------------------------------------
                $editor = $("<a class=\"edit opt\" href=\"javascript:void(0);\"><span>编辑</span></a>");
                $(el).append($editor);
                $(el).find(".edit").unbind("click").click(function() {
                	//if (data.type == 1) {
                		 window.location = _path + "/permission/" + data.id + "/toEdit";
                	//} else {
                	//	csjbotui.ui.msg.alert("其它 类型的权限不可编辑！");
                	//}
                });
                <% 
                }
                %>
                
            }
        
        $(function(){
            
            $("#isValid").select({
		        change: function (value, label) {
		        },
		        width : 200
		    });
            $("#type").select({
                change: function (value, label) {
                },
                width : 200
            });
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
                        <li class="active">权限管理</li>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content">
                    <div style="height: 40px;"></div>
                    <div class="panel panel-default">
			            <div class="panel-heading panel-title">
				             <div class="row">
				                 <div class="col-xs-6 text-left"> 权限管理</div>
				                 <div class="col-xs-6 text-right action-bar">
				                    <shiro:hasPermission name="modify:permission">
                                        <input type="button" class="btn btn-large btn-primary add-permission" value="新增权限" /> 
                                    </shiro:hasPermission>
				                 </div>
				             </div>
			            </div>
			            <div class="panel-body">
			              <div class="row">
			                <form class="form searchform" id="searchform" name="searchform">
			                  <div class="row">
			                     <div class="col-md-12">
			                    <div class="col-md-3">
			                      <div class="form-group">
			                        <div class="input-group">
			                          <div class="input-group-addon">权限名称</div>
			                          <input class="form-control" id="name" name="name" />
			                        </div>
			                      </div>
			                    </div>
			                    <div class="col-md-3">
			                      <div class="form-group">
			                        <div class="input-group">
			                          <div class="input-group-addon">状态</div>
			                          <select class="form-control" id="isValid" name="isValid">
			                            <option value="-1">全部</option>
			                            <option value="1">启用</option>
			                            <option value="0">停用</option>
			                          </select>
			                        </div>
			                      </div>
			                    </div>
			                    <div class="col-md-3">
                                  <div class="form-group">
                                    <div class="input-group">
                                      <div class="input-group-addon">权限类型</div>
                                      <select class="form-control" id="type" name="type">
                                        <option value="-1">全部</option>
                                        <option value="1">菜单</option>
                                        <option value="0">其它</option>
                                      </select>
                                    </div>
                                  </div>
                                </div>
			                    <div class="col-md-3 text-center">
                                  <input type="button" class="btn btn-large btn-primary searchBtn" value="搜索" /> 
                                  <input type="button" class="btn btn-primary reset" value="重置" />
                                </div>
                                </div>
			                  </div>
			                </form>
			              </div>
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