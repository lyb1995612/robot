<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../common/meta.jsp" />
    <jsp:include page="../common/resources.jsp" />
    
    <link rel="stylesheet" type="text/css" href="${path }/resources/iCheck/skins/all.css" />
    <script src="${path }/resources/iCheck/icheck.min.js"></script>
    <script src="${path }/scripts/sys/assign-permission.js"></script>
    <style>
	  .panel-body .row {
	    margin: 0px;
	  }
	  
	  .summary-banner span {
	    padding-right: 25px;
	    height: 32px;
	    line-height: 32px;
	  }
	  
	  .resource-treepanel {
	    height: 600px;
	    overflow: scroll;
	  }
	</style>
	<script>
	    $(document).ready(function(){
	      
	      $('input').iCheck({
	        checkboxClass: 'icheckbox_flat-blue',
	        increaseArea: '20%' // optional
	      });
	      
	      $("#select-all").on('ifChanged', function(event){
	          var checked = $("#select-all").is(":checked");
	          $.each($("#tp tbody tr input[type='checkbox']"), function(i, box) {
	              if(checked){
	                  $(box).iCheck('check');
	              }else{
	                  $(box).iCheck('uncheck');
	              }
	          });
	      });
	      
	    });
	    
	    $(function(){
            $("#sys_permission").addClass("active");
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
                        <li><a href="${path}/role/list">角色权限管理</a></li>
                        <li class="active">关联权限</li>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content">
                    <div style="height: 40px;"></div>
                    <div class="panel panel-default">
			          <div class="panel-heading panel-title">关联权限</div>
			          <div class="panel-body">
			
			            <div class="row summary-banner">
			              <div class="col-md-12">
			                <span><label>角色名称：</label>${role.name }</span> <span>目前用户数量<b>${userSize }</b>个</span>
			              </div>
			            </div>
			            <form class="form" id="permissionRoleForm" name="form" action="${path }/role/${role.id}/permission/save" method="post">
			            <input type="hidden" name="rolePerminssionRef-totalSize" value="${fn:length(permissions) }" />
			            <div class="row" >
			                <table id="tp" class="table table-striped table-hover table-bordered table-responsive">
			                    <thead>
			                        <tr>
			                        <th style="width: 40px;"><input type="checkbox" id="select-all" value="true"/></th>
			                        <th style="width: 35%;">code</th><th>name</th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                        <c:if test="${fn:length(permissions) == 0 }">
			                          <tr>
			                            <td colspan=99><span>没有可用的用户</span></td>
			                          </tr>
			                      </c:if>
			                      <c:forEach var="permission" items="${permissions }" varStatus="status">
			                      <tr>
			                        <td>
			                          <input type="checkbox" name="RolePermissionRef-${status.index }-checked" value="true" <c:if test="${permission.checked eq true }">checked</c:if> />
			                          <input type="hidden"  name="RolePermissionRef-${status.index }-permission_fk" value="${permission.permission_id }" />
			                          <input type="hidden"  name="RolePermissionRef-${status.index }-id" value="${permission.id}" />
			                        </td>
			                        <td>${permission.code }</td>
			                        <td>${permission.name }</td>
			                      </tr>
			                      </c:forEach>
			                    </tbody>
			                </table>
			            </div>
			            
			            <div class="row">
			              <div class="col-md-12 text-center">
			                <input type="button" class="btn btn-primary save" value="确认" />
			                <input type="button" class="btn btn-default" onclick="closePage()" value="取消" />
			              </div>
			            </div>
			            </form>
			            
			          </div>
			        </div>
                    
                </div><!-- /.page-content -->
            </div><!-- /.main-content -->
            
        </div>
        
    </div><!-- /.main-container-inner -->
    
    <jsp:include page="../common/footer.jsp" />
</body>
</html>