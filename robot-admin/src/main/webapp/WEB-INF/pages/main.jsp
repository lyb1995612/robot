<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<jsp:include page="common/meta.jsp" />
	<jsp:include page="common/resources.jsp" />
	<script src="${path }/scripts/main/portal.js" ></script>
	<style type="text/css">
	   #welcome{
	      display: block;
          width: 100%;
          background-image: url('${path}/images/welcome.png');
          background-size:100% 100%;
	   }
	   #welcomeTitle{
	      color: #333333; 
	      font-family: '微软雅黑';
	      font-size: 46px;
	      padding-top: 200px;
	   }
	   #welcomeSub{
          color: #b3b3b3; 
          font-family: arial;
          font-size: 14px;
       }
	</style>
</head>
  
<body class="navbar-fixed">
    <jsp:include page="common/header.jsp" />
    <div class="main-container">
	    <div class="main-container-inner">  
			<jsp:include page="common/menu.jsp" />
			
			<div class="main-content" id="mainContent">
			    <div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
			
			        <ul class="breadcrumb">
			            <li>
			                <i class="icon-home home-icon"></i>
			                <a href="${path}/">首页</a>
			            </li>
			            <li class="active">欢迎页面</li>
			        </ul><!-- .breadcrumb -->
			    </div>
			
			    <div class="page-content">
			        <script type="text/javascript">
                       $(function(){
                           setHeight();
                           $(window).resize(function(){
                               setHeight();
                           });
                           function setHeight(){
                               var x = $(window).width();
                               var y = $(window).height();
                               if (x < 950) {
                                   $("#welcome").height(y - 120);
                               } else {
                                   $("#welcome").height(y - 80);
                               }
                           }
                           $("#btn-scroll-up").hide();
                       });
                       
                    </script>
			        <div class="row">
			            <div class="col-xs-12">
			                <div class=" text-center" id="welcome">
			                     <p id="welcomeTitle">欢迎登录Csjbot</p>
			                     <p id="welcomeSub">WELCOME LOGIN CSJBOT</p>
			                </div>
			            </div><!-- /.col -->
			        </div><!-- /.row -->
			        
			    </div><!-- /.page-content -->
			</div><!-- /.main-content -->
			
	    </div>
	    
    </div><!-- /.main-container-inner -->
    
    <jsp:include page="common/footer.jsp" />
</body>
</html>
