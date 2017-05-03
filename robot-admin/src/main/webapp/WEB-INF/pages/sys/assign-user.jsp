<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../common/meta.jsp" />
    <jsp:include page="../common/resources.jsp" />
    <link rel="stylesheet" type="text/css" href="${path }/resources/iCheck/skins/all.css" />
    <script src="${path }/resources/iCheck/icheck.min.js"></script>
    <script src="${path }/scripts/sys/assign-user.js"></script>
    <script src="${path }/scripts/plugins/datatable/jquery.csjbotui.datatable.js"></script>
    <style>
      .panel-body .row {
        margin: 0px;
      }
      
      .row span {
        padding-right: 25px;
        height: 32px;
        line-height: 32px;
      }
      
      .check-column {
        width: 40px;
      }
     .dk_container span.dk_label{
          line-height:18px;
      }
      .dk_container{
          line-height:18px;
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
           
              $("#status").select();
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
                        <li class="active">关联用户</li>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content">
                    <div style="height: 40px;"></div>
                    <div class="panel panel-default">
			            <div class="panel-heading panel-title">关联用户</div>
			            <div class="panel-body">
			              <div class="row">
			                <form class="form searchform" id="searchform" name="searchform">
			                  <div class="row">
			                    <div class="col-md-4">
			                      <div class="form-group">
			                        <div class="input-group">
			                          <div class="input-group-addon">账号</div>
			                          <input class="form-control" id="account" name="account" />
			                        </div>
			                      </div>
			                    </div>
			                    <div class="col-md-4">
			                      <div class="form-group">
			                        <div class="input-group">
			                          <div class="input-group-addon">姓名</div>
			                          <input class="form-control" id="username" name="username" />
			                        </div>
			                      </div>
			                    </div>
			                    <div class="col-md-4">
			                      <div class="form-group">
			                        <div class="input-group">
			                          <div class="input-group-addon">账号状态</div>
			                          <select class="form-control" id="status" name="status">
			                            <option value="">全部</option>
			                            <option value="10">启用</option>
			                            <option value="20">停用</option>
			                          </select>
			                        </div>
			                      </div>
			                    </div>
			                  </div>
			                  <div class="row">
			                    <div class="col-md-12 text-center">
			                      <input type="button" class="btn btn-large btn-primary searchBtn" value="搜索" /> 
			                      <input type="button" class="btn btn-primary reset" value="重置" />
			                    </div>
			                  </div>
			                </form>
			              </div>
			            </div>
			          </div>
			  
			          <div class="row">
			            <div class="col-md-12">
			              <span><label>角色名称：</label>${role.name }</span>
			              <span>目前用户数量<b>${userSize }</b>个</span>
			            </div>
			          </div>
			          
			          <form class="form roleUserForm" name="form" action="${path }/role/${role.id }/user/save" method="post">
			          <input type="hidden" id="UserRoleRef-totalSize" id="UserRoleRef-totalSize" name="UserRoleRef-totalSize" value="${fn:length(admins) }" />
			          <div class="row">
			            <div class="col-xs-12">
			              <table id="tp" class="table table-striped table-hover table-bordered table-responsive" data-url="${path }/role/${role.id }/user/search">
			                <thead>
			                  <tr>
			                    <th class="check-column"><input type="checkbox" id="select-all" name="select-all" value="true"/></th>
			                    <th>账号</th>
			                    <th>姓名</th>
			                    <th>手机</th>
			                    <th>账号状态</th>
			                  </tr>
			                </thead>
			                <tbody>
			                  <c:if test="${fn:length(admins) == 0 }">
			                  <tr>
			                    <td colspan=99><span>没有可用的用户</span></td>
			                  </tr>
			                  </c:if>
			                  <c:forEach var="admin" items="${admins }" varStatus="status">
			                  <tr>
			                    <td>
			                      <input type="checkbox" id="UserRoleRef-${status.index }-checked" name="UserRoleRef-${status.index }-checked" value="true" <c:if test="${admin.checked eq true }">checked</c:if> />
			                      <input type="hidden" id="UserRoleRef-${status.index }-id" name="UserRoleRef-${status.index }-id" value="${admin.id }" />
			                      <input type="hidden" id="UserRoleRef-${status.index }-user_fk" name="UserRoleRef-${status.index }-user_fk" value="${admin.user_fk }" />
			                    </td>
			                    <td>${admin.account }</td>
			                    <td>${admin.username }</td>
			                    <td>${admin.cellphone }</td>
			                    <td>${admin.userStatus }</td>
			                  </tr>
			                  </c:forEach>
			                </tbody>
			              </table>
			            </div>
			          </div>
			  
			          <div class="row">
			            <div class="col-md-12 text-center">
			              <input type="button" class="btn btn-primary save" value="确认" />
			              <input type="button" class="btn btn-default" onclick="closePage()" value="取消" />
			            </div>
			          </div>
			          <!--/.row-->
			          </form>
                    
                </div><!-- /.page-content -->
            </div><!-- /.main-content -->
            
        </div>
        
    </div><!-- /.main-container-inner -->
    
    <jsp:include page="../common/footer.jsp" />
</body>
</html>