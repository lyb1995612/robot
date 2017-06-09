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
<script src="${path}/scripts/common/preview.js"></script>
<script src="${path}/scripts/scs/shop_add.js"></script>
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
						<li><a href="${path}/cms/list">店铺管理</a></li>
						<li class="active">新增店铺</li>
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
										style="width: 96%; text-align: center;">新增店铺</td>
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
														<div class="input-group-addon">店铺名称</div>
														<input class="form-control" type="text" id="shop_name"
															name="shop_name" maxlength="15" placeholder="请输入店铺名称" />
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
														<div class="input-group-addon">店铺编码</div>
														<input class="form-control" type="text" id="shop_code"
															name="shop_code" maxlength="15" placeholder="请输入店铺编码" />
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
														<div class="input-group-addon">店铺地址</div>
														<input class="form-control" type="text" id="shop_address"
															name="shop_address" maxlength="50" placeholder="请输入店铺地址" />
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
														<div class="input-group-addon">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</div>
														<textArea class="form-control" id="memo" name="memo"
															maxlength="50" style="resize: none" rows="4"></textArea>
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
