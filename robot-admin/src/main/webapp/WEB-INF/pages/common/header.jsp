<%@ page language="java" pageEncoding="UTF-8"%>
<div class="navbar navbar-default navbar-fixed-top" id="navbar" style="height: 45px;">
    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="${path}/" class="navbar-brand">
                <small><i class="icon-cloud"></i>  Csjbot后台管理系统</small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">

                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="${path }/resources/ace/images/user.png"  alt="" />
                        <span><small>您好,</small></span>
                        <span><%=request.getUserPrincipal()%></span><small>&nbsp;!&nbsp;&nbsp;</small>  
                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li id="changePersonalPassword">
                            <a href="${path}/sys/admin/toChangePersonalPassword"><i class="icon-edit"></i>修改密码</a>
                        </li>                    
                        <li>
                            <a href="${path}/logout"><i class="icon-off"></i>退出</a>
                        </li>
                    </ul>
                </li>
            </ul><!-- /.ace-nav -->
        </div><!-- /.navbar-header -->
    </div><!-- /.container -->
</div>