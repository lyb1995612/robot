<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../common/meta.jsp" />
    <jsp:include page="../common/resources.jsp" />
    <script src="${path}/scripts/common/preview.js"></script>
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
                        <li class="active">送餐机器人</li>
                        <li><a href="${path}/dish/list">附件管理</a></li>
                        <c:if test="${editable == 1 }"><li class="active">附件编辑</li></c:if>
                        <c:if test="${editable == 0 }"><li class="active">附件详情</li></c:if>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content" style="height: 550px;">
                    <div style="height: 40px;"></div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<table style="width: 99%;">
								<tr>
									<td style="width: 4%;text-align: left;">
									   <a class="btn btn-primary" type="button" href="${path}/acce/list">返回</a></td>
									<td class="panel-title" style="width: 96%;text-align: center;font-weight:bold">
										<c:if test="${editable == 1 }">附件编辑</c:if>
										<c:if test="${editable == 0 }">附件详情</c:if>
									</td>
								</tr>
							</table>
						</div>
						<div class="panel-body">
							<div class="row">
								<form id="acce_form" class="form">
									<div class="row">
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">附件名称</div>
														<input class="form-control" type="text" id="name" name="name" maxlength="25" value="${acce.name}" placeholder="请输入菜品名称" <c:if test="${editable == 0 }">readonly</c:if>/>
														<input type="text" id="id" name="id" value="${acce.id}" hidden="hidden">
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">附件类型</div>
                                              		    <input  class="form-control" type="text" id="file_type" name="file_type"  maxlength="14" value="${file.file_type}" placeholder="请输入" <c:if test="${editable == 0 }">readonly</c:if>/> 
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
														<div class="input-group-addon">附件地址</div>
                                              		    <input  class="form-control" type="text" id="address" name="address"  maxlength="14" value="${file.location}" placeholder="请输入" <c:if test="${editable == 0 }">readonly</c:if>/> 
													</div>
												</div>
											</div>
                                            <div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">店铺</div>
														<select class="form-control" id="shop_fk" name="shop_fk" <c:if test="${editable == 0 }">disabled</c:if>>
															<c:forEach var="shop_fk" items = "${shop_list}">
																<option value="${shop_fk.id}" <c:if test="${shop_fk.id eq acce.shop_fk}">selected="selected"</c:if>>${shop_fk.shop_name}</option>
															</c:forEach>
														</select>														
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<div class="col-md-12">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">备注</div>
														<textArea class="form-control" id="memo" name="memo" maxlength="256" style="resize:none" rows="4" <c:if test="${editable == 0 }">readonly</c:if>>${dish.memo}</textArea>
													</div>
												</div>
											</div>
										</div>
									</div>
									<c:if test="${editable == 1 }">
										<div class="text-center">
	                                        <button type="button" class="btn btn-primary" id="submit">提交</button>
	                                    </div>
                                    </c:if>
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
