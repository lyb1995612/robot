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
    <script src="${path}/scripts/pms/advertisement_update.js"></script>
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
                        <li class="active">迎宾机器人</li>
                        <li><a href="${path}/adv/list">广告管理</a></li>
                        <c:if test="${editable == 1 }"><li class="active">产品编辑</li></c:if>
                        <c:if test="${editable == 0 }"><li class="active">产品详情</li></c:if>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content" style="height: 550px;">
                    <div style="height: 40px;"></div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<table style="width: 99%;">
								<tr>
									<td style="width: 4%;text-align: left;">
									   <a class="btn btn-primary" type="button" href="${path}/adv/list">返回</a></td>
									<td class="panel-title" style="width: 96%;text-align: center;font-weight:bold">
										<c:if test="${editable == 1 }">广告编辑</c:if>
										<c:if test="${editable == 0 }">广告详情</c:if>
									</td>
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
														<input class="form-control" type="text" id="name" name="name" maxlength="25" placeholder="请输入广告名称" value="${advertisement.name}" <c:if test="${editable == 0 }">readonly</c:if>/>
														<input id="id" name="id" value="${advertisement.id}" hidden="hidden">
													</div>
												</div>
											</div>
										</div>
									</div>
		     			            <div class="row">
					                <div class="col-md-12">
					                <c:if test="${editable == 1 }">
					                    <div class="col-md-6">
					                        <div class="form-group">
					                            <div class="input-group">
					                                <div class="input-group-addon">图片</div>
					                                 <input class="form-control" type="file" id="photo" name="photo" onchange="previewImage(this,1)">
					                            </div>
					                        </div>
					                    </div>
					                 </c:if>
					                    <div class="col-md-6">
					                        <div class="form-group">
					                            <div class="input-group">
					                                 <c:if test="${editable == 1 }"> 请上传800×600像素的图片 </c:if>
					                                 <div id="preview1">
					                                  	<c:if test="${editable == 0 }">图片<br></c:if>
					                                  <img id="imghead" width=200 height=150 src='${path }${pic_url}'>
					                                </div>
					                            </div>
					                        </div>
					                    </div>
					                </div>                  
					                </div>  
		     			            <div class="row">
					                <div class="col-md-12">
					               	    <c:if test="${editable == 1 }">
					                    <div class="col-md-6">
					                        <div class="form-group">
					                            <div class="input-group">
					                                <div class="input-group-addon">音频</div>
					                                 <input class="form-control" type="file" id="audio" name="audio">
					                            </div>
					                        </div>
					                    </div>
					                    </c:if>
					                    <div class="col-md-6">
					                        <div class="form-group">
					                            <div class="input-group">
					                                 <c:if test="${editable == 0 }">音频<br></c:if> 
					                                                 已上传:<a href="${path}${audio_url}">${audio_name}</a>
					                            </div>
					                        </div>
					                    </div>					                    
					                </div>                  
					                </div> 
		     			            <div class="row">
					                <div class="col-md-12">
					              		<c:if test="${editable == 1 }">
					                	<div class="col-md-6">
					                        <div class="form-group">
					                            <div class="input-group">
					                                <div class="input-group-addon">视频</div>
					                                 <input class="form-control" type="file" id="vedio" name="vedio">
					                            </div>
					                        </div>
					                    </div> 	
					                    </c:if>
					                    <div class="col-md-6">
					                        <div class="form-group">
					                            <div class="input-group">
					                                 <c:if test="${editable == 0 }">视频<br></c:if>
					                                                           已上传:<a href="${path}${vedio_url}">${vedio_name}</a>
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
														<textArea class="form-control" id="memo" name="memo" maxlength="256" style="resize:none" rows="4" <c:if test="${editable == 0 }">readonly</c:if>>${advertisement.memo}</textArea>
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
