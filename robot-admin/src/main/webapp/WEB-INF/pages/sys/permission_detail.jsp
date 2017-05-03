<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../common/meta.jsp" />
    <jsp:include page="../common/resources.jsp" />
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
                        <li><a href="${path}/permission/list">权限管理</a></li>
                        <li class="active">权限详情</li>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content" style="height: 550px;">
                    <div style="height: 40px;"></div>
                    <div class="panel panel-default">
	                    <div class="panel-heading">
	                        <table style="width: 99%;">
	                            <tr>
	                                <td style="width: 4%;text-align: left;">
	                                   <a class="btn btn-primary" type="button" href="${path}/permission/list">返回</a>
	                                </td>
	                                <td class="panel-title" style="width: 96%;text-align: center;font-weight:bold">权限详情</td>
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
                                                  <div class="input-group-addon">权限名称</div>
                                                  <input class="form-control" type="text" id="name" name="name" value="${permission.name }" readonly />
                                              </div>
                                          </div>
                                      </div>
                                      <div class="col-md-6">
                                          <div class="form-group">
                                              <div class="input-group">
                                                  <div class="input-group-addon">权限代号</div>
                                                  <input class="form-control" type="text" id="code" name="code" value="${permission.code}" readonly />
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
                                                     <div class="input-group-addon">状态</div>
                                                     <input class="form-control" type="text"  id="isValid" name="isValid" value="${permission.status }" readonly />
                                                 </div>
                                             </div>
                                         </div>
                                         <div class="col-md-6">
                                             <div class="form-group">
                                                 <div class="input-group">
                                                     <div class="input-group-addon">权限类型</div>
                                                     <input class="form-control" type="text" id="type" name="type" value="${permission.typeName  }" readonly />
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
                                                     <div class="input-group-addon">创建日期</div>
                                                     <input class="form-control" type="text" id="date_create" name="date_create" value="<fmt:formatDate value="${ permission.dateOfCreate}" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly />
                                                 </div>
                                             </div>
                                         </div>
                                         <div class="col-md-6">
                                             <div class="form-group">
                                                 <div class="input-group">
                                                     <div class="input-group-addon">更新日期</div>
                                                     <input class="form-control" type="text" id="date_update" name="date_update" value="<fmt:formatDate value="${permission.dateOfUpdate }" pattern="yyyy-MM-dd HH:mm:ss"/>" readonly />
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
                                                     <div class="input-group-addon">创建人</div>
                                                     <input class="form-control" type="text" id="creator" name="creator" value="${ permission.creator}" readonly />
                                                 </div>
                                             </div>
                                         </div>
                                         <div class="col-md-6">
                                             <div class="form-group">
                                                 <div class="input-group">
                                                     <div class="input-group-addon">更新人</div>
                                                     <input class="form-control" type="text" id="updater" name="updater" value="${permission.updater }" readonly />
                                                 </div>
                                             </div>
                                         </div>
                                     </div>
                                 </div>
                                 <div class="row">
                                     <div class="col-md-12">
                                         <div class="col-md-12">
                                             <div class="form-group">
                                                 <div class="input-group">
                                                     <div class="input-group-addon">备注</div>
                                                     <textArea class="form-control" id="memo" name="memo" readonly >${permission.memo }</textArea>
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
