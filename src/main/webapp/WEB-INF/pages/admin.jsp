<%@ page language="java" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>static/vendors/normalize/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="http://127.0.0.1:3000/dd.css"/>
    <%--<link rel="stylesheet" type="text/css" href="<%=basePath%>static/js/dist/dd.css"/>--%>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>static/js/style.css"/>


    <link rel="icon" href="<%=basePath%>static/favicon/favicon.ico" mce_href="<%=basePath%>static/favicon/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="<%=basePath%>static/favicon/favicon.ico" mce_href="<%=basePath%>static/favicon/favicon.ico" type="image/x-icon">

    <!--[if lt IE 10]>
    <script src="https://as.alipayobjects.com/g/component/??console-polyfill/0.2.2/index.js,es5-shim/4.5.7/es5-shim.min.js,es5-shim/4.5.7/es5-sham.min.js,html5shiv/3.7.2/html5shiv.min.js,media-match/2.0.2/media.match.min.js"></script>
    <![endif]-->

</head>
<body>
<!--[if lt IE 9]>
<p class="browserupgrade">您的浏览器实在是 <strong>太古董啦~~~</strong> . 现代人要使用现代浏览器， <a href="http://browsehappy.com/">点击这里</a> 升级您的浏览器吧！ </p>
<![endif]-->
<div id="base"></div>
</body>

<script src="<%=basePath%>static/vendors/jquery.min.js"></script>

<script src="<%=basePath%>static/pages/core/admin/base/config.js"></script>

<%--<script src="<%=basePath%>static/js/dist/common.js"></script>--%>
<%--<script src="<%=basePath%>static/js/dist/dd.js"></script>--%>

<script src="http://127.0.0.1:3000/common.js"></script>
<script src="http://127.0.0.1:3000/dd.js"></script>


<%--<script src="../..<%=basePath%>static/vendors/JSXTransformer.js"></script>--%>
<script src="<%=basePath%>static/vendors/browser-es3.min.js"></script>

<!--路由文件-->
<script src="<%=basePath%>static/pages/core/admin/base/router.js"></script>

<!--<script src="static/vendors/browser.js"></script>



</html>
