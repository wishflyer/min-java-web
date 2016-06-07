<%@ page language="java" pageEncoding="UTF-8"%>
    <!--=== Header ===-->
    <div class="header">
        <div class="container">
            <!-- Logo -->
            <a class="logo" href="./index">
                <img src="<%=basePath%>static/image/logo/logo.png" alt="Logo">
            </a>
            <!-- End Logo -->

            <!-- Topbar -->
            <div class="topbar">
                <ul class="loginbar pull-right">
                    <li class="hoverSelector">
                        <i class="fa fa-globe"></i>
                        <a>Languages</a>
                        <ul class="languages hoverSelectorBlock">
                            <li class="active">
                                <a href="#">中文 <i class="fa fa-check"></i></a>
                            </li>
                            <li><a href="#">English</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- End Topbar -->

            <!-- Toggle get grouped for better mobile display -->
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="fa fa-bars"></span>
            </button>
            <!-- End Toggle -->
        </div><!--/end container-->

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse mega-menu navbar-responsive-collapse">
            <div class="container">
                <ul class="nav navbar-nav">
                    <!-- Home -->
                    <li class="dropdown">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                            名瑞婚纱
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="index.html">新款上市</a></li>
                            <li><a href="page_home8.html">Grace</a></li>
                            <li><a href="page_home8.html">Sensuous</a></li>
                            <li><a href="page_home8.html">Rosa</a></li>
                            <li><a href="page_home8.html">Refined</a></li>
                            <li><a href="page_home8.html">Plus Size Bride</a></li>
                        </ul>
                    </li>
                    <!-- End Home -->

                    <!-- Pages -->
                    <li class="dropdown">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                            晚礼服
                        </a>
                        <ul class="dropdown-menu">

                            <!-- Invoice Page -->
                            <li><a href="page_invoice.html">新款上市</a></li>
                            <li><a href="page_invoice.html">Gorgeous</a></li>
                            <li><a href="page_invoice.html">Brilliant</a></li>
                            <li><a href="page_invoice.html">Bridesmaid</a></li>
                            <!-- End Invoice Page -->

                        </ul>
                    </li>
                    <!-- End Pages -->

                    <!-- Blog -->
                    <li class="dropdown">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                            网上商城
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="blog_masonry_3col.html">淘宝官方店</a></li>
                            <li><a href="blog_timeline.html">京东官方店</a></li>
                        </ul>
                    </li>
                    <!-- End Blog -->


                    <!-- Blog -->
                    <li class="dropdown">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                            体验店
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="blog_masonry_3col.html">名瑞婚纱晚礼服旗舰店</a></li>
                        </ul>
                    </li>
                    <!-- End Blog -->

                    <!-- Blog -->
                    <li class="dropdown">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                            名瑞杯
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="blog_masonry_3col.html">名瑞杯婚纱设计大赛</a></li>
                            <li><a href="blog_timeline.html">名瑞杯晚礼服设计大赛</a></li>
                        </ul>
                    </li>
                    <!-- End Blog -->

                    <!-- Blog -->
                    <li class="dropdown">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                            时装周
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="./fashion">国际时装周</a></li>
                        </ul>
                    </li>
                    <!-- End Blog -->


                    <!-- Blog -->
                    <li class="dropdown">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                            品牌风采
                        </a>
                        <ul class="dropdown-menu">
                        <li><a href="blog_masonry_3col.html">技术交流</a></li>
                        <li><a href="blog_masonry_3col.html">信息交流</a></li>
                        <li><a href="blog_masonry_3col.html">高端对话</a></li>
                        <li><a href="blog_masonry_3col.html">国际时装周</a></li>
                        <li><a href="blog_masonry_3col.html">名瑞集团&清华大学</a></li>
                        <li><a href="blog_masonry_3col.html">媒体聚焦</a></li>
                        <li><a href="blog_masonry_3col.html">重要启事</a></li>
                        <li><a href="blog_masonry_3col.html">品牌宣传片</a></li>

                        </ul>
                    </li>
                    <!-- End Blog -->

                    <!-- about us -->
                    <li class="dropdown">
                        <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                            关于我们
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="./about">品牌介绍</a></li>
                            <li><a href="./about">集团介绍</a></li>
                            <li><a href="./about">生产线</a></li>
                            <li><a href="./about">名瑞蕾丝</a></li>
                            <li><a href="./about">精湛潮秀</a></li>
                            <li><a href="./about">商务洽谈</a></li>
                            <li><a href="./about">质量证书</a></li>
                            <li><a href="./about">郑重声明</a></li>
                            <li><a href="./about">集团招聘</a></li>
                            <li><a href="./about">加盟条件</a></li>
                        </ul>
                    </li>
                    <!-- End about us -->


                    <!-- Search Block -->
                    <li>
                        <i class="search fa fa-search search-btn"></i>
                        <div class="search-open">
                            <div class="input-group animated fadeInDown">
                                <input type="text" class="form-control" placeholder="搜索产品">
									<span class="input-group-btn">
										<button class="btn-u" type="button">Go</button>
									</span>
                            </div>
                        </div>
                    </li>
                    <!-- End Search Block -->
                </ul>
            </div><!--/end container-->
        </div><!--/navbar-collapse-->
    </div>
    <!--=== End Header ===-->

