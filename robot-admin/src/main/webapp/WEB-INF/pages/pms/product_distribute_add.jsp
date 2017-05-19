<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
  import="org.apache.shiro.SecurityUtils"
  import="org.apache.shiro.subject.Subject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%  
    Subject subject = SecurityUtils.getSubject();
%>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../common/meta.jsp" />
    <jsp:include page="../common/resources.jsp" />
    <link rel="stylesheet" type="text/css" href="${path }/resources/iCheck/skins/all.css" />
    <script src="${path }/resources/iCheck/icheck.min.js"></script>
    <link rel="stylesheet" href="${path }/resources/datatables/css/dataTables.bootstrap.min.css" />
    
    <script src="${path }/resources/datatables/js/jquery.dataTables.min.js"></script>
    <script src="${path }/resources/datatables/js/dataTables.bootstrap.min.js"></script>
    <script src="${path }/resources/datatables/js/default.js"></script>
    <script src="${path }/scripts/pms/product_distribute_add.js" ></script>
    
    
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

</head> 
<body class="navbar-fixed">
    
    <jsp:include page="../common/header.jsp" />
    <div class="main-container" >
        <div class="main-container-inner">  
            <jsp:include page="../common/menu.jsp" />
            
            <div class="main-content" id="mainContent">
                <div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
            
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home home-icon"></i>
                            <a href="${path}/">首页</a>
                        </li>
                        <li class="active">迎宾机器人</li>
                        <li><a href="${path}/pd/list">产品分配</a></li>
                        <li class="active">产品分配新增</li>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content">
                    <div style="height: 40px;"></div>
                    <div class="panel panel-default">
			            <div class="panel-heading panel-title">产品分配</div>
			            <div class="panel-body">
			              <div class="row">
			                <form class="form searchform" id="searchform" name="searchform">
			                  <div class="row">
			                    <div class="col-md-4">
			                      <div class="form-group">
			                        <div class="input-group">
			                          <div class="input-group-addon">机器人SN</div>
			                          <select class="form-control" id="cmsRobot" name="cmsRobot">
<!--                                          <option value="">&nbsp;1111111</option> -->
<!--                                          <option value="">&nbsp;2222222</option> -->
<!--                                          <option value="">&nbsp;3333333</option> -->
                                         <c:forEach items="${cmsRobots }" var="cmsRobot" >	                                                          
                                             <option value="${cmsRobot.sn }">${cmsRobot.sn }</option>
                                         </c:forEach>
			                          </select>			                          
			                        </div>
			                      </div>
			                    </div>
			                  </div>
			                </form>
			              </div>
			            </div>
			          </div>
			          
			          <form class="form pms_productForm" name="form" action="${path }/pd/add" method="post">
			          <input type="hidden" id="UserRoleRef-totalSize" id="UserRoleRef-totalSize" name="UserRoleRef-totalSize" value="${fn:length(pms_products) }" />
			          <div class="row">
			            <div class="col-xs-12">
			              <table id="tp" class="table table-striped table-hover table-bordered table-responsive" data-url="${path }/role/${role.id }/user/search">
			                <thead>
			                  <tr>
			                    <th class="check-column"><input type="checkbox" id="select-all" name="select-all" value="true"/></th>
			                    <th>产品名</th>
			                  </tr>
			                </thead>
			                <tbody>
			                  <c:if test="${fn:length(pms_products) == 0 }">
			                  <tr>
			                    <td colspan=99><span>没有可用的产品</span></td>
			                  </tr>
			                  </c:if>
			                  <c:forEach var="pms_product" items="${pms_products }" varStatus="status">
			                  <tr>
			                    <td>
			                      <input type="checkbox" id="UserRoleRef-${status.index }-checked" name="checkboxs" value="${pms_product.id }" />
			                      <input type="hidden" id="UserRoleRef-${status.index }-id" name="UserRoleRef-${status.index }-id" value="${pms_product.id }" />
			                    </td>
			                    <td>${pms_product.name }</td>
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