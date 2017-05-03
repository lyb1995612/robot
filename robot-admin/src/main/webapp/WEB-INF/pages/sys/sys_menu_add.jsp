<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../common/meta.jsp" />
    <jsp:include page="../common/resources.jsp" />
    <link rel="stylesheet" href="${path}/resources/ace/css/chosen.min.css" />
    <script type="text/javascript" src="${path}/resources/ace/js/chosen.jquery.min.js"></script>
    <script type="text/javascript" src="${path}/scripts/sys/sys_menu_add.js" ></script>
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
                        <li class="active">新增菜单</li>
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
	                                <td class="panel-title" style="width: 96%;text-align: center;font-weight:bold">新增菜单</td>
	                            </tr>
	                        </table>
	                    </div>
	                    <div class="panel-body">
	                        <div class="row">
	                            <form id="menu_add_form" class="form">
	                                <div class="row">
	                                    <div class="col-md-12">
	                                        <div class="col-md-6">
	                                            <div class="form-group">
	                                                <div class="input-group">
	                                                    <div class="input-group-addon">菜单名称</div>
	                                                    <input class="form-control" type="text" id="name" name="name" placeholder="请输入菜单名称">
	                                                </div>
	                                            </div>
	                                        </div>
	                                        <div class="col-md-6">
	                                            <div class="form-group">
	                                                <div class="input-group" style="height: 35px;">
	                                                    <div class="input-group-addon">关联权限</div>
	                                                    <select class="form-control chosen" id="permission_fk" name="permission_fk" style="width:300px;" >
	                                                       <option value="">&nbsp;</option>
	                                                       <c:forEach items="${permissions }" var="per" >
	                                                           <option value="${per.id }">${per.name }</option>
	                                                       </c:forEach>
	                                                    </select>
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
                                                        <select class="form-control" id="level" name="level" style="width:300px;" >
                                                           <option value="1">1</option>
                                                           <option value="2">2</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group" id="parents_select" style="visibility: hidden;">
                                                    <div class="input-group">
                                                        <div class="input-group-addon">父级菜单</div>
                                                        <select class="form-control" id="parent_fk" name="parent_fk" style="width:300px;" >
                                                           <option value="">&nbsp;</option>
                                                           <c:forEach items="${parents }" var="one" >
                                                               <option value="${one.id }">${one.name }</option>
                                                           </c:forEach>
                                                        </select>
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
                                                        <input class="form-control" type="text" id="activeId" name="activeId" placeholder="请输入菜单标识">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <div class="input-group-addon">菜单排序</div>
                                                        <input class="form-control" type="text" id="sorter" name="sorter" placeholder="请输入菜单排序">
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
                                                        <input class="form-control" type="text" id="url" name="url" placeholder="请输入访问链接">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group" >
                                                    <div class="input-group">
                                                        <div class="input-group-addon">菜单图标</div>
                                                        <select class="form-control" id="style" name="style" style="width:300px;" >
                                                           <option value="">&nbsp;</option>
                                                           <option value="icon-dashboard">icon-dashboard</option>
                                                           <option value="icon-list">icon-list</option>
                                                           <option value="icon-bar-chart">icon-bar-chart</option>
                                                           <option value="icon-briefcase">icon-briefcase</option>
                                                           <option value="icon-group">icon-group</option>
                                                           <option value="icon-cogs">icon-cogs</option>
                                                           <option value="con-legal">icon-legal</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
	                                <div class="text-center">
	                                    <button type="button" class="btn btn-primary" id="submit">提交</button>
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
