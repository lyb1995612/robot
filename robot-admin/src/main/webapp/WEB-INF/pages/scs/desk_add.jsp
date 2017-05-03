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
    <script src="${path}/scripts/scs/desk_add.js"></script>
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
                        <li><a href="${path}/scs/list">桌号管理</a></li>
                        <li class="active">新增桌号</li>
                    </ul><!-- .breadcrumb -->
                </div>
            
                <div class="page-content" style="height: 550px;">
                    <div style="height: 40px;"></div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<table style="width: 99%;">
								<tr>
									<td style="width: 4%;text-align: left;">
									   <a class="btn btn-primary" type="button" href="${path}/scs/list">返回</a></td>
									<td class="panel-title" style="width: 96%;text-align: center;font-weight:bold">新增桌号</td>
								</tr>
							</table>
						</div>
						<div class="panel-body">
							<div class="row">
								<form id="desk_form" class="form">
									<div class="row">
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">桌号名</div>
														<input class="form-control" type="text" id="number" name="number" maxlength="100" placeholder="请输入桌号名"/>
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">桌号别名</div>
                                              		    <input  class="form-control" type="text" id="alias" name="alias"  maxlength="100" placeholder="请输入桌号别名"/> 
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">桌位类型</div>
                                              		    <input  class="form-control" type="text" id="desk_type" name="desk_type"  maxlength="2" placeholder="请输入桌位类型"/> 
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">坐标X</div>
                                              		    <input  class="form-control" type="text" id="deskx" name="deskx"  maxlength="6" placeholder="请输入坐标X"/> 
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">坐标Y</div>
                                              		    <input  class="form-control" type="text" id="desky" name="desky"  maxlength="6" placeholder="请输入坐标Y"/> 
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">坐标Z</div>
                                              		    <input  class="form-control" type="text" id="deskz" name="deskz"  maxlength="6" placeholder="请输入坐标Z"/> 
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">坐标W</div>
                                              		    <input  class="form-control" type="text" id="deskw" name="deskw"  maxlength="6" placeholder="请输入坐标W"/> 
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">坐标V</div>
                                              		    <input  class="form-control" type="text" id="deskv" name="deskv"  maxlength="6" placeholder="请输入坐标V"/> 
													</div>
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group">
													<div class="input-group">
														<div class="input-group-addon">坐标Q</div>
                                              		    <input  class="form-control" type="text" id="deskq" name="deskq"  maxlength="6" placeholder="请输入坐标Q"/> 
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
														<textArea class="form-control" id="memo" name="memo" maxlength="512" style="resize:none" rows="4"></textArea>
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
