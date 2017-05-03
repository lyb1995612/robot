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
    <script type="text/javascript" src="${path}/scripts/sys/sys_menu_edit.js" ></script>
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
                        <li class="active">编辑菜单</li>
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
	                                <td class="panel-title" style="width: 96%;text-align: center;font-weight:bold">编辑菜单</td>
	                            </tr>
	                        </table>
	                    </div>
	                    <div class="panel-body">
	                        <div class="row">
	                            <form id="menu_edit_form" class="form">
	                                <div class="row">
	                                    <div class="col-md-12">
	                                        <div class="col-md-6">
	                                            <div class="form-group">
	                                                <div class="input-group">
	                                                    <div class="input-group-addon">菜单名称</div>
	                                                    <input class="form-control" type="text" id="name" name="name" value="${menu.name }" />
	                                                    <input type="hidden" name="id" value="${menu.id }" />
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
	                                                           <option value="${per.id }" <c:if test="${per.id eq menu.permission_fk }">selected</c:if> >${per.name }</option>
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
                                                           <option value="1" <c:if test="${ '1' eq menu.level }">selected</c:if>>1</option>
                                                           <option value="2" <c:if test="${ '2' eq menu.level }">selected</c:if>>2</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group" id="parents_select" >
                                                    <div class="input-group">
                                                        <div class="input-group-addon">父级菜单</div>
                                                        <select class="form-control" id="parent_fk" name="parent_fk" style="width:300px;" >
                                                           <option value="">&nbsp;</option>
                                                           <c:forEach items="${parents }" var="one" >
                                                               <option value="${one.id }" <c:if test="${one.id eq menu.parent_fk }">selected</c:if> >${one.name }</option>
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
                                                        <input class="form-control" type="text" id="activeId" name="activeId" value="${menu.activeId }"  />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <div class="input-group">
                                                        <div class="input-group-addon">菜单排序</div>
                                                        <input class="form-control" type="text" id="sorter" name="sorter" value="${menu.sorter }" />
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
                                                        <input class="form-control" type="text" id="url" name="url" value="${menu.url }" />
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
	                                            <div class="form-group">
	                                                <div class="input-group">
                                                        <div class="input-group-addon">菜单图标</div>
                                                        <select class="form-control" id="style" name="style" style="width:300px;" >
                                                           <option value="">&nbsp;</option>
                                                           <option value="icon-dashboard" <c:if test="${ 'icon-dashboard' == menu.style }">selected</c:if> >icon-dashboard</option>
                                                           <option value="icon-list" <c:if test="${ 'icon-list' == menu.style }">selected</c:if> >icon-list</option>
                                                           <option value="icon-bar-chart" <c:if test="${ 'icon-bar-chart' == menu.style }">selected</c:if> >icon-bar-chart</option>
                                                           <option value="icon-briefcase" <c:if test="${ 'icon-briefcase' == menu.style }">selected</c:if> >icon-briefcase</option>
                                                           <option value="icon-group" <c:if test="${ 'icon-group' == menu.style }">selected</c:if> >icon-group</option>
                                                           <option value="icon-cogs" <c:if test="${ 'icon-cogs' == menu.style }">selected</c:if> >icon-cogs</option>
                                                           <option value="icon-legal" <c:if test="${ 'icon-legal' == menu.style }">selected</c:if> >icon-legal</option>
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
                                                        <div class="input-group-addon">是否启用</div>
                                                        <select class="form-control" id="valid" name="valid" style="width:300px;" >
                                                           <option value="1" <c:if test="${ 1 == menu.valid }">selected</c:if>>启用</option>
                                                           <option value="0" <c:if test="${ 0 == menu.valid }">selected</c:if>>禁用</option>
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
