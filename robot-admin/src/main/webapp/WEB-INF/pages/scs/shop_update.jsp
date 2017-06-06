<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<jsp:include page="../common/meta.jsp" />
<jsp:include page="../common/resources.jsp" />
<script src="${path}/scripts/scs/shop_update.js"></script>
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
						<li><i class="icon-home home-icon"></i> <a href="${path}/">首页</a>
						</li>
						<li class="active">送餐机器人</li>
						<li><a href="${path}/shop/list">商铺管理</a></li>
						<c:if test="${editable == 1 }">
							<li class="active">商铺编辑</li>
						</c:if>
						<c:if test="${editable == 0 }">
							<li class="active">商铺详情</li>
						</c:if>
					</ul>
					<!-- .breadcrumb -->
				</div>
				<div class="page-content" style="height: 550px;">
					<div style="height: 40px;"></div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<table style="width: 99%;">
								<tr>
									<td style="width: 4%; text-align: left;"><a
										class="btn btn-primary" type="button" href="${path}/shop/list">返回</a></td>
									<td class="panel-title"
										style="width: 96%; text-align: center; font-weight: bold">
										<c:if test="${editable == 1 }">商铺编辑</c:if> <c:if
											test="${editable == 0 }">商铺详情</c:if>
									</td>
								</tr>
							</table>
						</div>
						<div class="panel-body">
							<div class="row">
								<form id="shop_form" class="form">
									<div class="row">
										<div class="col-md-12">
											<div class="col-md-12">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">商铺名称</div>
														<input class="form-control" type="text" id="shop_name"
															name="shop_name" maxlength="32" value="${shop.shop_name}"
															placeholder="请输入商铺名称"
															<c:if test="${editable == 0 }">readonly</c:if> /> <input
															type="text" id="id" name="id" value="${shop.id}"
															hidden="hidden">
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
														<div class="input-group-addon">商铺编码</div>
														<input class="form-control" type="text" id="shop_code"
															name="shop_code" maxlength="32" value="${shop.shop_code}"
															placeholder="请输入商铺编码"
															<c:if test="${editable == 0 }">readonly</c:if> />
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
														<div class="input-group-addon">商铺地址</div>
														<input class="form-control" type="text" id="shop_address"
															name="shop_address" maxlength="256" value="${shop.shop_address}"
															placeholder="请输入商铺地址"
															<c:if test="${editable == 0 }">readonly</c:if> />
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
														<textArea class="form-control" id="memo" name="memo"
															maxlength="512" value="${shop.memo}" style="resize: none"
															rows="4" placeholder="请输入备注信息"
															<c:if test="${editable == 0 }">readonly</c:if>>${shop.memo}</textArea>
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
				</div>
				<!-- /.page-content -->
			</div>
			<!-- /.main-content -->

		</div>

	</div>
	<!-- /.main-container-inner -->

	<jsp:include page="../common/footer.jsp" />
</body>
</html>
