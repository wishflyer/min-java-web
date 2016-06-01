<%@ page language="java" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<head>
    <title>Header v3 | Unify - Responsive Website Template</title>

    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <%@include file="/WEB-INF/pages/commons/link.jsp"%>

</head>

<body class="header-fixed">

<div class="wrapper">

    <%@include file="WEB-INF/pages/header/header_v3.jsp"%>

</div><!--/wrapper-->

<%@include file="/WEB-INF/pages/commons/script.jsp"%>

</body>
</html>
