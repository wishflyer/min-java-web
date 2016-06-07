
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
<!-- JS Customization -->
<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/custom.js"></script>
<!-- JS Page Level -->
<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/app.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/plugins/owl-carousel.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/plugins/style-switcher.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/plugins/parallax-slider.js"></script>	
<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/plugins/master-slider-fw.js"></script>
<script type="text/javascript" src="<%=basePath%>static/unify/assets/js/plugins/cube-portfolio/cube-portfolio-3.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        App.init();
		ParallaxSlider.initParallaxSlider();
		MSfullWidth.initMSfullWidth();
		
    });
</script>
<!--[if lt IE 9]>
<script src="<%=basePath%>static/unify/assets/plugins/respond.js"></script>
<script src="<%=basePath%>static/unify/assets/plugins/html5shiv.js"></script>
<script src="<%=basePath%>static/unify/assets/plugins/placeholder-IE-fixes.js"></script>
<![endif]-->