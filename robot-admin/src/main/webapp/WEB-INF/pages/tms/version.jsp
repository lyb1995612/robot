<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <jsp:include page="../common/meta.jsp"/>
    <jsp:include page="../common/resources.jsp"/>

    <link rel="stylesheet" type="text/css" href="${path}/css/tms/fileUpload.css">
    <script src="${path}/scripts/tms/http-client.js"></script>
    <script src="${path}/scripts/tms/fileUpload.js" defer></script>
    <script src="${path}/scripts/tms/version.js" defer></script>
</head>

<body class="navbar-fixed">
<jsp:include page="../common/header.jsp"/>
<div class="main-container">
    <div class="main-container-inner">
        <jsp:include page="../common/menu.jsp"/>

        <div class="main-content" id="mainContent">

            <div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="icon-home home-icon"></i>
                        <a href="${path}/">首页</a>
                    </li>
                    <li class="active">版本发布</li>
                    <li class="active">升级包管理</li>
                </ul><!-- .breadcrumb -->
            </div>

            <div class="page-content">
                <div style="height: 40px;"></div>
                <div class="panel panel-default">
                    <div class="panel-heading panel-title">
                        <div class="row">
                            <div class="col-xs-6 text-left">升级包管理</div>
                            <div class="col-xs-6 text-right action-bar">
                                <%--  <shiro:hasPermission name="modify:version_control"> --%>
                                <a type="button" class="btn btn-primary" id="file-upload-modal-btn"
                                <%--href="${path}/tms/fileUpload">--%>
                                   data-toggle="modal" data-target="#file-upload-modal">
                                    上传
                                </a>
                                <%--<button type="button" id="new-pkg-btn" class="btn btn-primary"--%>
                                <%--data-toggle="modal" data-target="#new-pkg-modal"> +--%>
                                <%--</button>--%>
                                <div class="modal fade" id="file-upload-modal"
                                     data-keyboard="false" data-backdrop="static"
                                     tabindex="-1" role="dialog" aria-labelledby="file-upload-modal-title"
                                     aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-body">
                                                <div class="container-fluid">
                                                    <form class="col-xl-12" id="file-upload-form">
                                                        <div class="form-group">
                                                            <div class="form-group">
                                                                <div class="col-xs-12 col-sm-2">
                                                                    <label for="local-file-input"
                                                                           class="btn btn-primary">
                                                                        <input type="file" id="local-file-input"
                                                                               style="display: none">
                                                                        选择文件
                                                                    </label>
                                                                </div>
                                                                <div class="col-xs-12 col-sm-10">
                                                                    <p class="help-text small">
                                                                        (1)服务端暂不支持版本信息维护，请仔细命名上传文件<br/>
                                                                        (2).zip后缀的压缩包将自动解压一份在同目录下
                                                                    </p>
                                                                </div>
                                                            </div>
                                                            <div class="col-xs-12 form-group">
                                                                <!--<div id="local-file-feedback" class="help-text" style="display: none">-->
                                                                <!--feedback-->
                                                                <!--</div>-->
                                                                <div id="local-file-info" class="info-text"
                                                                     style="visibility: hidden">
                                                                    <span>已选文件：</span>
                                                                    <span id="local-file-path">e:tomcat/RUN.txt</span>
                                                                    <span id="local-file-size">432kb</span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="clearfix"></div>
                                                        <div class="form-group">
                                                            <div class="col-xs-12">
                                                                <label class="control-label">
                                                                    上传至服务端文件夹：
                                                                </label>
                                                            </div>
                                                            <div class="col-xs-12">
                                                                <select class="form-control"
                                                                        id="server-dir-sel"> </select>
                                                            </div>
                                                            <div class="col-xs-12">
                                                                    <span class="help-text"> 或直接填写/创建
                                                                        <span class="help-text-tip">(最多3层、每层3-20字符、
                                                                       仅支持数字、英文字母、连字符-和下划线_)</span>：
                                                                    </span>
                                                            </div>
                                                            <div class="col-xs-12" id="new-server-dir">
                                                                /
                                                                <input type="text" class="dir-sub form-control">
                                                                /
                                                                <input type="text" class="dir-sub form-control">
                                                                /
                                                                <input type="text" class="dir-sub form-control">
                                                            </div>
                                                            <div class="col-xs-12 form-group">
                                                                <div class="info-text" id="server-dir-feedback"
                                                                     style="visibility: hidden">
                                                                    <span>已选路径：</span>
                                                                    <span id="server-dir-info"></span>
                                                                    <a id="server-dir-link" target="_blank">查看</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <%--<div class="clearfix"></div>--%>
                                                        <%--<div class="form-group">--%>
                                                        <%--<label class="col-form-label">版本编号</label>--%>
                                                        <%--<fieldset class="col-xs-12">--%>
                                                        <%--<div class="form-inline">--%>
                                                        <%--<span>Ver.</span>--%>
                                                        <%--<input class="form-control" type="text"--%>
                                                        <%--id="pkg-ver-1">--%>
                                                        <%--<span>.</span>--%>
                                                        <%--<input class="form-control" type="text"--%>
                                                        <%--id="pkg-ver-2">--%>
                                                        <%--<span>.</span>--%>
                                                        <%--<input class="form-control" type="text"--%>
                                                        <%--id="pkg-ver-3">--%>
                                                        <%--<!-- - <input class="col-1 form-control" type="text" id="pkg-ver-ab">-->--%>
                                                        <%--<span>-</span>--%>
                                                        <%--<select class="form-control" id="pkg-ver-ab">--%>
                                                        <%--<option selected></option>--%>
                                                        <%--<option>alpha</option>--%>
                                                        <%--<option>beta</option>--%>
                                                        <%--</select>--%>
                                                        <%--</div>--%>
                                                        <%--</fieldset>--%>
                                                        <%--</div>--%>
                                                        <div class="clearfix"></div>
                                                        <div class="form-group">
                                                            <div class="help-text" id="upload-feedback">
                                                                <span id="upload-info" class="help-text"> </span>
                                                                <span id="upload-state"></span>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <div class="col-xs-7"></div>
                                                            <div class="btn-group col-xs-5 pull-right">
                                                                <button type="submit"
                                                                        class="btn btn-primary"
                                                                        id="upload-btn">确认上传
                                                                </button>
                                                                <button type="submit"
                                                                        class="btn btn-secondary"
                                                                        data-dismiss="modal"
                                                                        id="cancel-upload-btn">取消/退出
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <%--  </shiro:hasPermission> --%>
                            </div>
                        </div>
                    </div>
                    <%--<div class="panel-body">--%>
                    <%--<div class="row">--%>
                    <%--<form class="form searchform" id="searchform" name="searchform">--%>
                    <%--<div class="row">--%>
                    <%--<div class="col-md-12">--%>

                    <%--<div class="col-md-4">--%>
                    <%--<div class="form-group">--%>
                    <%--<div class="input-group">--%>
                    <%--<div class="input-group-addon">包名</div>--%>
                    <%--<input class="form-control" type="text" id="username"--%>
                    <%--name="username" placeholder="请输入帐号">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-4">--%>
                    <%--<div class="form-group">--%>
                    <%--<div class="input-group">--%>
                    <%--<div class="input-group-addon">时间</div>--%>
                    <%--<input class="form-control" type="text" id="realname"--%>
                    <%--name="realname" placeholder="请输入姓名">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>


                    <%--<div class="col-md-4 text-center">--%>
                    <%--<input type="button" id="searchButton" class="btn btn-large btn-primary"--%>
                    <%--value="查询"/>--%>
                    <%--<input type="button" id="resetButton" class="btn btn-primary" value="重置"/>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</form>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                </div>

                <div class="row" style="margin-top:5px;">
                    <div class="col-xs-12">
                        <table id="file-list-table"
                               class="table table-striped table-hover table-bordered table-responsive">
                            <thead>
                            <tr>
                                <th>编号</th>
                                <th>文件名</th>
                                <th>服务端路径</th>
                                <th>上传时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${fileList}" var="file" varStatus="loop">
                                <fmt:formatDate value="${file.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"
                                                var="fmtUploadTime"/>
                                <tr data-fileId="${file.id}">
                                    <td>${loop.index+1}</td>
                                    <td>${file.name}</td>
                                    <td>${file.path}</td>
                                    <td>${fmtUploadTime}</td>
                                    <td>
                                        <a href="${baseUrl}${file.path}" target="_blank">查看</a>
                                        <c:set var="dirUrl" value="${baseUrl}${file.path}"></c:set>
                                        <c:choose>
                                            <c:when test="${file.path == '/'}">
                                                <a href="${dirUrl}${file.name}" target="_blank">下载</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="${dirUrl}/${file.name}" target="_blank">下载</a>
                                            </c:otherwise>
                                        </c:choose>
                                        <a href="#">删除</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div><!--/row-->

            </div><!-- /.page-content -->
        </div><!-- /.main-content -->

    </div>

</div><!-- /.main-container-inner -->

<jsp:include page="../common/footer.jsp"/>
</body>
</html>
