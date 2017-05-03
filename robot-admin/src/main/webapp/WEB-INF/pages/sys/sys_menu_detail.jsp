<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../common/meta.jsp" />
    <jsp:include page="../common/resources.jsp" />
    <script type="text/javascript">
	    $(function(){
	        $("#sys_menu").addClass("active");
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
                        <li><a href="${path}/menu/toListMenus">菜单管理</a></li>
                        <li class="active">菜单详情</li>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content" style="height: 550px;">
                    <div style="height: 40px;"></div>
                    <div class="panel panel-default">
	                    <div class="panel-heading">
	                        <table style="width: 99%;">
	                            <tr>
	                                <td style="width: 4%;text-align: left;">
	                                   <a class="btn btn-primary" type="button" href="${path}/menu/toListMenus">返回</a>
	                                </td>
	                                <td class="panel-title" style="width: 96%;text-align: center;font-weight:bold">菜单详情</td>
	                            </tr>
	                        </table>
	                    </div>
	                    <div class="panel-body">
	                        <div class="row">
                              <div class="row">
                                  <div class="col-md-12">
                                      <div class="col-md-6">
                                          <div class="form-group">
                                              <div class="input-group">
                                                  <div class="input-group-addon">菜单名称</div>
                                                  <input class="form-control" type="text" id="name" name="name" value="${menu.name }" readonly />
                                              </div>
                                          </div>
                                      </div>
                                      <div class="col-md-6">
                                          <div class="form-group">
                                              <div class="input-group">
                                                  <div class="input-group-addon">关联权限</div>
                                                  <input class="form-control" type="text" id="permission_fk" name="permission_fk" value="${permission.name } - ${permission.code}" readonly />
                                              </div>
                                          </div>
                                      </div>
                                  </div>
                              </div>
                              <div class="row">
                                     <div class="col-md-12">
                                         <div class="col-md-6">
                                             <div class="form-group">
                                                 <div class="input-group">
                                                     <div class="input-group-addon">菜单层级</div>
                                                     <input class="form-control" type="text"  id="level" name="level" value="${menu.level }" readonly />
                                                 </div>
                                             </div>
                                         </div>
                                         <div class="col-md-6">
                                             <div class="form-group" id="parents_select" >
                                                 <div class="input-group">
                                                     <div class="input-group-addon">父级菜单</div>
                                                     <input class="form-control" type="text" id="parent_fk" name="parent_fk" value="${parent.name }" readonly />
                                                 </div>
                                             </div>
                                         </div>
                                     </div>
                                 </div>
                              <div class="row">
                                     <div class="col-md-12">
                                         <div class="col-md-6">
                                             <div class="form-group">
                                                 <div class="input-group">
                                                     <div class="input-group-addon">菜单标识</div>
                                                     <input class="form-control" type="text" id="activeId" name="activeId" value="${menu.activeId }" readonly />
                                                 </div>
                                             </div>
                                         </div>
                                         <div class="col-md-6">
                                             <div class="form-group">
                                                 <div class="input-group">
                                                     <div class="input-group-addon">菜单排序</div>
                                                     <input class="form-control" type="text" id="sorter" name="sorter" value="${menu.sorter }" readonly />
                                                 </div>
                                             </div>
                                         </div>
                                     </div>
                                 </div>
                                 <div class="row">
                                     <div class="col-md-12">
                                         <div class="col-md-6">
                                             <div class="form-group">
                                                 <div class="input-group">
                                                     <div class="input-group-addon">访问链接</div>
                                                     <input class="form-control" type="text" id="url" name="url" value="${ menu.url}" readonly />
                                                 </div>
                                             </div>
                                         </div>
                                         <div class="col-md-6">
                                             <div class="form-group">
                                                 <div class="input-group">
                                                     <div class="input-group-addon">菜单图标</div>
                                                     <input class="form-control" type="text" id="style" name="style" value="${menu.style }" readonly />
                                                 </div>
                                             </div>
                                         </div>
                                     </div>
                                 </div>
                                 <div class="row">
                                     <div class="col-md-12">
                                         <div class="col-md-6">
                                             <div class="form-group">
                                                 <div class="input-group">
                                                     <div class="input-group-addon">是否启用</div>
                                                     <input class="form-control" type="text" id="valid" name="valid" value="${menu.valid == 1 ? '启用' : '禁用' }" readonly />
                                                 </div>
                                             </div>
                                         </div>
                                     </div>
                                 </div>
	                        </div>
	                    </div>
	                </div>
                    
                </div><!-- /.page-content -->
            </div><!-- /.main-content -->
            
        </div>
        
    </div><!-- /.main-container-inner -->
    
    <jsp:include page="../common/footer.jsp" />
</body>
</html>
