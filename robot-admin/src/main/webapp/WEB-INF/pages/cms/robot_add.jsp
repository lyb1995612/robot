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
    <script src="${path}/scripts/cms/robot_add.js"></script>
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
                        <li class="active">新增清单</li>
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
									<td class="panel-title" style="width: 96%;text-align: center;font-weight:bold">新增清单</td>
								</tr>
							</table>
						</div>
						<div class="panel-body">
							<div class="row">
								<form id="robot_form" class="form">
									<div class="row">
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">机器人类型</div>
														<select class="form-control" id="type" name="type">
															<c:forEach items="${cplist}" var="one">
																<option value="${one.id }">${one.name}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>											
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon" style="width:95px">S / N</div>
														<input style="width:478px" class="form-control" type="text" id="sn" name="sn"
															maxlength="25" placeholder="请输入机器人唯一识别码" />
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon" style="width:95px">店 铺</div>
														<select style="width:478px" class="form-control" id="shop_fk" name="shop_fk">
															<c:forEach var="shop" items = "${shop_list}">
																<option value="${shop.id}">${shop.shop_name}</option>
															</c:forEach>
														</select>														
													</div>
												</div>
											</div>											
										</div>
							        </div>
									<!-- <div class="row">
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group date form_datetime " data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_input1">
														<span class="input-group-addon"><span class="glyphicon glyphicon-th hidden"></span><span>注册时间</span></span>
														<input class="form-control" size="16" type="text" value="" id="registerTime" name="registerTime" placeholder="请选择时间" readonly>
													</div>
													<input type="hidden" id="dtp_input1" value="" /><br />
												</div>
											</div>
										</div>
									</div> -->
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
