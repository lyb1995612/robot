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
    <script src="${path}/scripts/cms/robot_update.js"></script>
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
                        <li class="active">机器人管理</li>
                        <li><a href="${path}/crb/list">机器人清单</a></li>
                        <c:if test="${editable == 1 }"><li class="active">清单编辑</li></c:if>
                        <c:if test="${editable == 0 }"><li class="active">清单详情</li></c:if>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content" style="height: 550px;">
                    <div style="height: 40px;"></div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<table style="width: 99%;">
								<tr>
									<td style="width: 4%;text-align: left;">
									   <a class="btn btn-primary" type="button" href="${path}/crb/list">返回</a></td>
									<td class="panel-title" style="width: 96%;text-align: center;font-weight:bold">
										<c:if test="${editable == 1 }">清单编辑</c:if>
										<c:if test="${editable == 0 }">清单详情</c:if>
									</td>
								</tr>
							</table>
						</div>
						<div class="panel-body">
							<div class="row">
								<form id="robot_form" class="form">
									<div class="row">
										<div class="col-md-12">
										    <div class="col-md-12">
										     <div class="col-md-6">
			                                     <div class="form-group">
			                                          <div class="input-group">
			                                              <div class="input-group-addon">机器人类型</div>
			                                              <select  class="form-control" id="type" name="type"  <c:if test="${editable == 0 }">disabled="disabled"</c:if>>
			                                                   <c:forEach items="${cplist}" var="one" >
			                                                       <option value="${one.id }" <c:if test="${one.id eq robot.type}">selected="selected"</c:if>>${one.name}</option>
			                                                   </c:forEach>
			                                              </select>
			                                          </div>
			                                      </div>
			                                </div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon" style="width:95px">S / N</div>
														<input  style="width:478px" class="form-control" type="text" id="sn" name="sn" maxlength="25" value="${robot.sn}" placeholder="请输入机器人唯一识别码" <c:if test="${editable == 0 }">readonly</c:if>/>
														<input type="text" id="id" name="id" value="${robot.id}" hidden="hidden">
														<input type="text" id="valid" name="valid" value="${robot.valid}" hidden="hidden">
														<input type="text" id="register" name="register" value="${robot.register}" hidden="hidden">
														<input type="text" id="creator_fk" name="creator_fk" value="${robot.creator_fk}" hidden="hidden">
														<input type="text" id="date_create" name="date_create" value="${robot.date_create}" hidden="hidden">
													</div>
												</div>
											</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
										    <div class="col-md-12">
										       <div class="col-md-6">
			                                      <div class="form-group">
													<div class="input-group">
														<div class="input-group-addon" style="width:95px">店 铺</div>
			                                        	<select class="form-control" style="width:463px" id="shop_name" name="shop_name"  <c:if test="${editable == 0 }">disabled="disabled"</c:if>>
			                                        		<c:forEach items="${shop_list}" var="shop" >
			                                         		<option id="shop_fk" value="${shop.id }" <c:if test="${shop.id eq robot.shop_fk}">selected="selected"</c:if>>${shop.shop_name}</option>
			                                         		</c:forEach>
			                                        	</select>
													</div>
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
