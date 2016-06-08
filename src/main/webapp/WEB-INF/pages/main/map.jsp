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


</head>

<body class="header-fixed">

<div class="wrapper">

    <%@include file="../commons/header.jsp"%>

    <%@include file="../base/map.jsp"%>

    <%@include file="../commons/footer.jsp"%>

</div><!--/wrapper-->

<%@include file="../commons/script.jsp"%>


<script src="<%=basePath%>static/vendors/echarts/myEcharts/echarts.min.js"></script>
<script src="<%=basePath%>static/vendors/echarts/mapData/china.js"></script>
<script>

        var chart = echarts.init(document.getElementById('map'));

chart.setOption({
    tooltip: {
        trigger: 'item',
        formatter: '{b}'
    },
    series: [
        {
            name: '中国',
            type: 'map',
            mapType: 'china',
            selectedMode : 'multiple',
            label: {
                normal: {
                    show: false
                },
                emphasis: {
                    show: true
                }
            },
            data:[
                {name:'广东', selected:true},
                {name:'香港', selected:true},
                {name:'北京', selected:true},
            ]
        }
    ]
});


</script>

</body>
</html>
