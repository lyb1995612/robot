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
    <script src="${path }/scripts/cms/robot_group_ref.js"></script>
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
	<!--  <script>
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
	</script>  -->
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
			
			            <div class="row summary-banner">
			              <div class="col-md-12">
			                <span><label>群组名：</label>${robotGroup.group_name }</span> <span>目前数量<b>${relevance_num}</b>个</span>
			              </div>
			            </div>
			            <form class="form" id="robotGroupForm" name="form" action="${path }/rbg/${robotGroup.id}/robotGroupRef/save" method="post">
			            <input type="hidden" name="robotGroupRef-totalSize" value="${fn:length(robot) }" />
			             
			            <div class="row" >
			                <table id="tp" class="table table-striped table-hover table-bordered table-responsive">
			                    <thead>
			                        <tr>
			                        <th style="width: 40px;"><input type="checkbox" id="select-all" value="true" onclick="DoCheck()"/></th>
			                        <th style="width: 35%;">S/N号</th><th>机器人类型</th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                        <c:if test="${fn:length(robot) == 0 }">
			                          <tr>
			                            <td colspan=99><span>没有可关联的机器人</span></td>
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