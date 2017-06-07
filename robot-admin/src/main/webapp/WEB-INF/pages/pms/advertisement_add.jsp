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
<script src="${path}/scripts/pms/advertisement_add.js"></script>
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
						<li class="active">迎宾机器人</li>
						<li><a href="${path}/adv/list">广告管理</a></li>
						<li class="active">新增广告</li>
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
										class="btn btn-primary" type="button" href="${path}/adv/list">返回</a></td>
									<td class="panel-title"
										style="width: 96%; text-align: center; font-weight: bold">新增广告</td>
								</tr>
							</table>
						</div>
						<div class="panel-body">
							<div class="row">
								<form id="advertisement_form" class="form">
									<div class="row">
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">广告名称</div>
														<input class="form-control" type="text" id="name"
															name="name" maxlength="25" placeholder="请输入广告名称" />
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">广告类型</div>
														<select name="type" id="type" style="height:34px;width:130px">
															<option value="0"/>
															<option value="1">&nbsp;图片/音频</option>
															<option value="2">&nbsp;视频</option>
														</select>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row" id="image">
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片</div>
														<input class="form-control" type="file" id="photo"
															name="photo" onchange="previewImage(this,1)">
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														请上传800×600像素的图片
														<div id="preview1">
															<img id="imghead" width=200 height=150
																src='${path }/images/nophoto.jpg'>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row" id="audio">
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">音&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;频</div>
														<input class="form-control" type="file" id="audio"
															name="audio">
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">推荐mp3格式</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row" id="video">
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">视&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;频</div>
														<input class="form-control" type="file" id="vedio"
															name="vedio">
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">推荐mp4格式</div>
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
															maxlength="256" style="resize: none" rows="4"></textArea>
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
