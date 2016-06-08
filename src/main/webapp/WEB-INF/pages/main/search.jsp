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
    <title>famory</title>

    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <%@include file="../commons/link.jsp"%>

    <link rel="stylesheet" href="<%=basePath%>static/unify/assets/css/pages/page_search_inner.css">

</head>

<body class="header-fixed">

<div class="wrapper">

    <%@include file="../commons/header.jsp"%>

    <%@include file="../base/search.jsp"%>

    <%@include file="../commons/footer.jsp"%>

</div><!--/wrapper-->

<%@include file="../commons/script.jsp"%>



</body>
</html>
