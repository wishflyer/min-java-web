<%@ page language="java" pageEncoding="UTF-8"%>

<!-- JS Global Compulsory -->
<script type="text/javascript" src="<%=basePath%>static/js/base/vendor/jquery-1.12.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/plugins/jquery/jquery-migrate.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- JS Implementing Plugins -->
<script type="text/javascript" src="<%=basePath%>static/unify/assets/plugins/back-to-top.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/plugins/smoothScroll.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/plugins/jquery.parallax.js"></script>

<script type="text/javascript" src="<%=basePath%>static/unify/assets/plugins/parallax-slider/js/modernizr.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/plugins/parallax-slider/js/jquery.cslider.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/plugins/owl-carousel/owl-carousel/owl.carousel.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/plugins/cube-portfolio/cubeportfolio/js/jquery.cubeportfolio.min.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/plugins/master-slider/masterslider/masterslider.min.js"></script>

<!--my plugins-->
<script type="text/javascript" src="<%=basePath%>static/vendors/layer/layer/layer.js"></script>

<!-- JS Customization -->
<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/custom.js"></script>
<!-- JS Page Level -->
<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/app.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/plugins/owl-carousel.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/plugins/style-switcher.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/plugins/parallax-slider.js"></script>	
<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/plugins/master-slider-fw.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/plugins/cube-portfolio/cube-portfolio-3.js"></script>

<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/plugins/owl-recent-works.js"></script>


<script type="text/javascript">

	var openContent = function(){


		// 获取窗口宽度
		if (window.innerWidth)
		winWidth = window.innerWidth;
		else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;
		// 获取窗口高度
		if (window.innerHeight)
		winHeight = window.innerHeight;
		else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;
		// 通过深入 Document 内部对 body 进行检测，获取窗口大小
		if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth)
		{
		winHeight = document.documentElement.clientHeight;
		winWidth = document.documentElement.clientWidth;
		}

		winHeightPX = winHeight*0.8+"px";
		winWidthPX = winWidth*0.8+"px";

		layer.open({
	        type: 2,
	        title: '蔡中涵2015婚纱礼服新品发布会',
	        maxmin: true,
	        shadeClose: true, //点击遮罩关闭层
	        area : [winWidthPX , winHeightPX],
	        content: './content'
    	});
	}

    jQuery(document).ready(function() {
        App.init();
		ParallaxSlider.initParallaxSlider();
		MSfullWidth.initMSfullWidth();
		OwlRecentWorks.initOwlRecentWorksV1();
    });
</script>
<!--[if lt IE 9]>
<script src="<%=basePath%>static/unify/assets/plugins/respond.js"></script>
<script src="<%=basePath%>static/unify/assets/plugins/html5shiv.js"></script>
<script src="<%=basePath%>static/unify/assets/plugins/placeholder-IE-fixes.js"></script>
<![endif]-->