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
    <script src="${path }/scripts/cms/robot_group_ref.js"></script>
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
       		$('table th input:checkbox').on('click' , function(){
			var that = this;
			$(this).closest('table').find('tr > td:first-child input:checkbox')
			.each(function(){
				this.checked = that.checked;
				$(this).closest('tr').toggleClass('selected');
			});
				
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
                        <li class="active">机器人管理</li>
                        <li><a href="${path}/rbg/list">机器人群组</a></li>
                        <li class="active">关联机器人</li>
                    </ul><!-- .breadcrumb -->
                </div>
			          
			    <div class="page-content">
                  <div style="height: 40px;"></div>
                    <div class="panel panel-default">
			          <div class="panel-heading panel-title">关联机器人</div>
			             <div class="panel-body">
		                     <div class="row">
		                     <form class="form searchform" id="searchform" name="searchform">
									<div class="row" >
		                                <div class="col-md-12">
		                                
		                                    <div class="col-md-3">
			                                     <div class="form-group">
			                                          <div class="input-group">
			                                              <div class="input-group-addon">机器人类型</div>
			                                              <select  class="form-control" id="type" name="type">
			                                             	   <option value="-1">全部</option>
			                                                   <c:forEach items="${cplist}" var="one" >
			                                                       <option value="${one.id }">${one.name}</option>
			                                                   </c:forEach>
			                                              </select>
			                                          </div>
			                                      </div>		                                    
		                                     </div>
		                                    
		                                    <div class="col-md-3">
		                                        <div class="form-group">
		                                            <div class="input-group">
		                                                <div class="input-group-addon">S/N</div>
		                                                <input  class="form-control" type="text" id="sn" name="sn" maxlength="25" />  
		                                            </div>
		                                        </div>
		                                    </div>  
		                                     
		                                    <div >
		                                        <input type="button" class="btn btn-large btn-primary searchBtn" value="搜索" /> 
			                                    <input type="button" class="btn btn-primary reset" value="重置" />
		                                   </div>	
		                                   		                                    	                                    	
		                                </div>
		                            </div>	                     
		                        </form>
		                     </div>
		                 </div>
		              </div>
			          
			          <form class="form" id="robotGroupForm" name="form" action="${path }/rbg/${robotGroup.id}/robotGroupRef/save" method="post">
			          <input type="hidden"  id="robotGroupRef-totalSize" name="robotGroupRef-totalSize" value="${fn:length(robot) }" />
			          <div class="row">
			            <div class="col-xs-12">
			              <table id="tp" class="table table-striped table-hover table-bordered table-responsive" data-url="${path }/rbg/${robotGroup.id }/robotGroupRef/search">
			                <thead>
			                  <tr>
			                    <th class="check-column"><input type="checkbox" id="select-all" name="select-all" value="true"/></th>
			                    <th>S/N号</th>
			                    <th>机器人类型</th>
			                  </tr>
			                </thead>
			                <tbody>
			                  <c:if test="${fn:length(robot) == 0 }">
			                  <tr>
			                    <td colspan=99><span>没有可用的用户</span></td>
			                  </tr>
			                  </c:if>
			                  <c:forEach var="robot" items="${robot }" varStatus="status">
			                  <tr>
			                    <td>
			                      <input type="checkbox" name="CmsRobotGroupRef-${status.index }-checked" value="true" <c:if test="${robot.checked eq true}">checked</c:if> />
			                      <input type="hidden"  name="CmsRobotGroupRef-${status.index }-group_fk" value="${robot.robot_id }" />
			                      <input type="hidden"  name="CmsRobotGroupRef-${status.index }-id" value="${robot.id}" />
			                      <input type="hidden"  name="CmsRobotGroupRef-${status.index }-sn" value="${robot.sn}" />
			                    </td>
			                    <td>${robot.sn }</td>
			                    <td>${robot.type_name }</td>
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