<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
import="java.text.SimpleDateFormat"
%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%  
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Date date = new Date();    
%>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../common/meta.jsp" />
    <jsp:include page="../common/resources.jsp" />
    <script src="${path }/scripts/sys/personal_password.js" ></script>
    <script type="text/javascript">
        $(function(){
        	$.cookie("activeId_1", null, { path: _admin });
            $.cookie("activeId_2", null, { path: _admin });
        });
    </script>
    <style type="text/css">
		.main-content {margin-left: 190px; margin-right: 190px;}
	</style>
</head>
  
<body class="navbar-fixed">
    <jsp:include page="../common/header.jsp" />
    <div class="main-container" >
        <div class="main-container-inner">  
            <div class="main-content" id="mainContent">
                <div class="page-content">
                    <div style="height: 40px;"></div>
                    <div class="panel panel-default">
			            <div class="panel-heading">
			                <table style="width: 99%;">
			                    <tr>
		                            <td class="panel-title" style="width: 96%;text-align: center;font-weight:bold">首次登录请修改密码</td>
		                        </tr>
			               </table>
			            </div>
			            <div class="panel-body">
			            <div class="row">
			            <input type="hidden" id="id" name="id"  value="${user.id}" readonly>
			            <form  id="password_edit_form" class="form" role ="form">
			            
			            <div class="row">
			                <div class="col-md-12">
			                    <!-- 账号 -->
			                    <div class="col-md-3"></div>
			                    <div class="col-md-6">
			                        <div class="form-group">
			                            <div class="input-group">
			                                <div class="input-group-addon">&nbsp;账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;</div>
			                                <input class="form-control" type="text" id="username" name="username"  value="${user.username}" disabled="disabled">                               
			                            </div>
			                        </div>
			                    </div>
			                    
			                </div>                  
			            </div>
			            <div class="row">
			                <div class="col-md-12">
			                    <!-- 姓名 -->
			                    <div class="col-md-3"></div>
			                    <div class="col-md-6">
			                        <div class="form-group">
			                            <div class="input-group">
			                                <div class="input-group-addon">&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;</div>
			                                <input class="form-control" type="text" id="realname" name="realname"  value="${user.realname}" disabled="disabled" >                              
			                            </div>
			                        </div>
			                    </div>
			                </div>                  
			            </div>
			            <div class="row">
			                <div class="col-md-12">
			                    <!-- 登录密码 -->
			                    <div class="col-md-3"></div>
			                    <div class="col-md-6">
			                        <div class="form-group">
			                            <div class="input-group">
			                                <div class="input-group-addon">&nbsp;新&nbsp;密&nbsp;码&nbsp;</div>
			                                <input class="form-control" type="password" id="password" name="password" placeholder="5-15位，支持字母、数字和符号，字母区分大小写">                              
			                            </div>
			                        </div>
			                    </div>
			                    
			                </div>                  
			            </div>
			            <div class="row">
			                <div class="col-md-12">
			                    <!-- 确认密码-->
			                    <div class="col-md-3"></div>
			                    <div class="col-md-6">
			                        <div class="form-group">
			                            <div class="input-group">
			                                <div class="input-group-addon">确认密码</div>
			                                <input class="form-control" type="password" id="confirmPassword" name="confirmPassword"  placeholder="请再输一次刚输入的登录密码，确保两次输入一致">                             
			                            </div>
			                        </div>
			                    </div>
			                </div>                  
			            </div>
			             <div class="text-center">
			                <button type="button" class="btn btn-primary" id="changePassword">保存修改 </button>
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