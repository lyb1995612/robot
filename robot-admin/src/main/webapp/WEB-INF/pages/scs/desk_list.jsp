<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
  import="org.apache.shiro.SecurityUtils"
  import="org.apache.shiro.subject.Subject"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <script src="${path }/scripts/scs/desk_list.js" ></script>
    <script src="${path }/scripts/plugins/datatable/jquery.csjbotui.datatable.js"></script>
    
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
                        <li class="active">桌号管理</li>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content">
                    
                    <div style="height: 40px;"></div>
                    <div class="panel panel-default">
		                 <div class="panel-heading panel-title">
		                      <table style="width: 100%;">
	                            <tr>
	                                <td style="width: 15%;text-align: left;">桌号管理</td>
	                                <%-- <td style="width: 10%;text-align: right;">
	                                       <a type="button" class="btn btn-primary" href="${path}/scs/toDeskAdd">新增桌号</a>
	                                </td> --%>
	                            </tr>
		                      </table>		                 
		                 </div>
		                 <div class="panel-body">
		                     <div class="row">
		                     <form class="form searchform" id="searchform" name="searchform">
									<div class="row" >
		                                <div class="col-md-12">
		                                    <div class="col-md-3">
		                                        <div class="form-group">
		                                            <div class="input-group">
		                                                <div class="input-group-addon">桌号</div>
		                                                <input  class="form-control" type="text" id="number" name="number" maxlength="100" />  
		                                            </div>
		                                        </div>
		                                    </div>   
		                                    <div >
		                                        <input type="button" id="searchButton" class="btn btn-large btn-primary" value="搜索"/>
		                                        <input type="button" id="resetButton" class="btn btn-primary" value="重置" />
		                                   </div>			                                    	                                    	
		                                </div>
		                            </div>	                     
		                     </form>
		                     </div>
		                 </div>
		            </div>
		            <div class="row">
		                <div class="col-xs-12">
		                        <table id="tp" class="table table-striped table-hover table-bordered table-responsive" style="width:100%;">                  
		                        </table>
		                </div>
		            </div><!--/row-->
                    
                </div><!-- /.page-content -->
            </div><!-- /.main-content -->
            
        </div>
        
    </div><!-- /.main-container-inner -->
    
    <jsp:include page="../common/footer.jsp" />
</body>
</html>