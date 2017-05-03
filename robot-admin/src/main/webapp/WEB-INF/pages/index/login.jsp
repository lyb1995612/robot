<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglibs.jsp" %> 
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<jsp:include page="../common/meta.jsp" />
		<title>登录</title>
		<jsp:include page="../common/resources.jsp" />
		<script src="${path }/scripts/sys/login.js" ></script>
		<!-- Custom styles for this template -->
		<link href="${path}/resources/bootstrap/3.3.0/css/signin.css" rel="stylesheet">
	</head>
	<body>
		<shiro:authenticated>
			<script>
				window.location = "<%=request.getContextPath()%>";
			</script>
		</shiro:authenticated>
		<div class="container">
			<c:if test="${not empty error}">
				<div class="error alert alert-warning alert-dismissable bottom-margin">
					${error}
				</div>
			</c:if>
			 <div class="panel panel-default">
	            <div class="panel-heading">
	               <h3 class="text-center">Csjbot-后台管理系统</h3>
	            </div>
	            <div class="panel-body">
		            <div class="row panel-title">
						<form class="form-signin" id ="loginForm" role="form" method="post">
						  <fieldset>
	                          <label class="block clearfix">
	                              <span class="block input-icon input-icon-right">
	                                  <input type="text" class="form-control"  id="username" name="username" placeholder="请输入账号" />
	                                  <i class="icon-user"></i>
	                              </span>
	                          </label>
	
	                          <label class="block clearfix">
	                              <span class="block input-icon input-icon-right">
	                                  <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" />
	                                  <i class="icon-lock"></i>
	                              </span>
	                          </label>
	
	                          <div class="space"></div>
	
	                          <div class="clearfix">
	
	                              <button type="submit" class="btn btn-block btn-lg btn-primary"><i class="icon-key"></i>登录</button>
	                          </div>
	                          <div class="space-4"></div>
	                      </fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
