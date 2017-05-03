<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
  String path = request.getContextPath();
  request.setAttribute("path", path);
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 禁止页面缓存 -->
<meta http-equiv="pragram" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="icon" href="${path }/images/favicon.ico" type="image/x-icon">  
<link rel="shortcut icon" href="${path }/images/favicon.ico" type="image/x-icon">
       
<title><c:if test="${title eq null }">Csjbot后台管理系统</c:if><c:if test="${title ne null }">${title }</c:if></title>
      
<script>
	_path = "${path }";
	_admin = "/admin/";
</script>