<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../common/meta.jsp" />
    <jsp:include page="../common/resources.jsp" />
    <script src="${path}/scripts/common/jquery.serializeJson.js"></script>
    <script src="${path }/scripts/sys/dic_son.js"></script>
    <style>
      .row {
          margin: 0px;
      }
      .form-control label {
        padding-right: 25px;
      }
    </style>
    <script type="text/javascript">
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
                        <li><a href="${path}/dic/list">字典信息</a></li>
                        <li class="active">数据维护</li>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content">
                    <div style="height: 40px;"></div>
                    <div class="panel panel-default">
			            <div class="panel-heading">
			                <table style="width: 99%;">
			                    <tr>
			                        <td style="width: 4%;text-align: left;" nowrap="nowrap" >
			                        <a  class="btn btn-primary" type="button" href="javascript:window.history.back();" >返回</a></td>
			                        <td class="panel-title" style="width: 96%;text-align: center;font-weight:bold">${sysdata.code}&nbsp;${sysdata.name}&nbsp;数据维护</td>
			                    </tr>
			               </table>
			            </div>
			            <div class="panel-body">
			              <div class="row">
			                <form class="form" id="dicform" <c:if test="${action eq 'EDIT' }">action="${path }/dic/edit"</c:if><c:if test="${action eq 'SAVE' }">action="${path }/dic/save"</c:if> method="POST">
			                  <input type="hidden" value="${sysdata.id}" id="dataFk" name="dataFk"/>
			                  <div class="row">
			                    <div class="col-md-6">
			                      <div class="form-group">
			                        <div class="input-group">
			                          <div class="input-group-addon">ID</div>
			                          <input class="form-control" id="id" name="id" value="${dicdata.id }" <c:if test="${action eq 'EDIT' }">readonly</c:if> />
			                        </div>
			                      </div>
			                    </div>
			                    <div class="col-md-6">
                                  <div class="form-group">
                                    <div class="input-group">
                                      <div class="input-group-addon">规则</div>
                                      <input class="form-control" id="rule" name="rule" value="${dicdata.rule }" />
                                    </div>
                                  </div>
                                </div>
			                  </div>
			                  <div class="row">
			                    <div class="col-md-6">
			                      <div class="form-group">
			                        <div class="input-group">
			                          <div class="input-group-addon">名称</div>
			                          <input class="form-control" id="name" name="name" value="${dicdata.name }" />
			                        </div>
			                      </div>
			                    </div>
			                    <div class="col-md-6">
			                      <div class="form-group">
			                        <div class="input-group">
			                          <div class="input-group-addon">对应</div>
			                          <input class="form-control" id="memo" name="memo" value="${dicdata.memo }"></input>
			                        </div>
			                      </div>
			                    </div>                    
			                  </div>
			                  <div class="row">
			                    <div class="col-md-6">
			                      <div class="form-group">
			                        <div class="input-group">
			                          <div class="input-group-addon">最小值</div>
			                          <input class="form-control" id="min" name="min" value="${dicdata.min }"></input>
			                        </div>
			                      </div>
			                    </div>
			                    <div class="col-md-6">
			                      <div class="form-group">
			                        <div class="input-group">
			                          <div class="input-group-addon">最大值</div>
			                          <input class="form-control" id="max" name="max" value="${dicdata.max }"></input>
			                        </div>
			                      </div>
			                    </div>                    
			                  </div>   
			                   <div class="row">
			                    <div class="col-md-6">
			                      <div class="form-group">
			                        <div class="input-group">
			                          <div class="input-group-addon">排序</div>
			                          <input class="form-control" id="sort" name="sort" value="${dicdata.sort }"></input>
			                        </div>
			                      </div>
			                    </div>
			                    <div class="col-md-6">
			                      <div class="form-group">
			                        <div class="input-group">
			                          <div class="input-group-addon">状态</div>
			                          <div class="form-control">
			                              <input type="radio" id="valid-true" name="valid" value="1" <c:if test="${dicdata.valid eq 1 }">checked</c:if> /><label for="valid-true">启用</label>
			                              <input type="radio" id="valid-false" name="valid" value="0" <c:if test="${dicdata.valid eq 0 }">checked</c:if> /><label for="valid-false">停用</label>
			                          </div>
			                        </div>
			                      </div>
			                    </div>  
			                  </div>              
			                  <div class="row">
			                    <div class="col-md-12 text-center">
			                      <input type="button" class="btn btn-primary submit" onclick="save()" value="保存" />
			                    </div>
			                  </div>
			                </form>
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