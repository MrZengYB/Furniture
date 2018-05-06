<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="utf-8">
	<title>OA-HomePage</title>
	<link rel="icon" type="image/png" href="/views/admin/homePage/i/favicon.png">
	<link rel="stylesheet" href="/views/admin/homePage/css/amazeui.min.css" />
	<link rel="stylesheet" href="/views/admin/homePage/css/admin.css">
	<link rel="stylesheet" href="/views/admin/homePage/css/app.css">
	<style type="text/css">
		.tpl-left-nav-hover::-webkit-scrollbar {/*滚动条整体样式*/
	        width: 4px;     /*高宽分别对应横竖滚动条的尺寸*/
	        height: 4px;
	        padding-right:20px;
	    }
	    .tpl-left-nav-hover::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
	        border-radius: 5px;
	        -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
	        background: rgba(0,0,0,0.2);
	    }
	    .tpl-left-nav-hover::-webkit-scrollbar-track {/*滚动条里面轨道*/
	        -webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);
	        border-radius: 0;
	        background: rgba(0,0,0,0.1);
	    }
	</style>
</head>
<body style="overflow:hidden;" onload="index_initDatas();">
	<header class="am-topbar am-topbar-inverse admin-header">
		<div class="am-topbar-brand">
			<a href="javascript:window.location='/admin/home.do?homePage';" class="tpl-logo"> 
				<img src="/views/admin/homePage/img/logo.png">
			</a>
		</div>
		<div class="am-icon-list tpl-header-nav-hover-ico am-fl am-margin-right"></div>
		<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
			<ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
				<li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
					<a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;"> 
						<span class="am-icon-bell-o"></span> 提醒 
						<span class="am-badge tpl-badge-success am-round">5</span>
					</a>
					<ul class="am-dropdown-content tpl-dropdown-content">
						<li class="tpl-dropdown-content-external">
							<h3>你有 <span class="tpl-color-success">5</span> 条提醒</h3> 
							<a href="###">全部</a>
						</li>
						<li class="tpl-dropdown-list-bdbc">
							<a href="#" class="tpl-dropdown-list-fl"><span class="am-icon-btn am-icon-plus tpl-dropdown-ico-btn-size tpl-badge-success"></span>【预览模块】移动端 查看时 手机、电脑框隐藏。</a> 
							<span class="tpl-dropdown-list-fr">3小时前</span>
						</li>
						<li class="tpl-dropdown-list-bdbc">
							<a href="#" class="tpl-dropdown-list-fl"><span class="am-icon-btn am-icon-check tpl-dropdown-ico-btn-size tpl-badge-danger"></span>移动端，导航条下边距处理</a> 
							<span class="tpl-dropdown-list-fr">15分钟前</span>
						</li>
						<li class="tpl-dropdown-list-bdbc">
							<a href="#" class="tpl-dropdown-list-fl"><span class="am-icon-btn am-icon-bell-o tpl-dropdown-ico-btn-size tpl-badge-warning"></span>追加统计代码</a> 
							<span class="tpl-dropdown-list-fr">2天前</span>
						</li>
					</ul>
				</li>
				<li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
					<a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;"> 
						<span class="am-icon-comment-o"></span> 消息
						<span class="am-badge tpl-badge-danger am-round">9</span>
					</a>
					<ul class="am-dropdown-content tpl-dropdown-content">
						<li class="tpl-dropdown-content-external">
							<h3>你有 <span class="tpl-color-danger">9</span> 条新消息</h3> 
							<a href="###">全部</a>
						</li>
						<li>
							<a href="#" class="tpl-dropdown-content-message"> 
								<span class="tpl-dropdown-content-photo"><img src="/views/admin/homePage/img/user02.png" alt=""></span> 
								<span class="tpl-dropdown-content-subject"> 
									<span class="tpl-dropdown-content-from"> 禁言小张 </span>
									<span class="tpl-dropdown-content-time">10分钟前 </span>
								</span> 
								<span class="tpl-dropdown-content-font"> Amaze UI 的诞生，依托于 GitHub 及其他技术社区上一些优秀的资源；Amaze UI 的成长，则离不开用户的支持。 </span>
							</a> 
							<a href="#" class="tpl-dropdown-content-message"> <span
									class="tpl-dropdown-content-photo"> <img
										src="/views/admin/homePage/img/user03.png" alt="">
								</span> <span class="tpl-dropdown-content-subject"> <span
										class="tpl-dropdown-content-from"> Steam </span> <span
										class="tpl-dropdown-content-time">18分钟前</span>
								</span> <span class="tpl-dropdown-content-font"> 为了能最准确的传达所描述的问题，
										建议你在反馈时附上演示，方便我们理解。 </span>
							</a>
						</li>
					</ul>
				</li>
				<li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
					<a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;"> 
						<span class="am-icon-calendar"></span> 进度 <span class="am-badge tpl-badge-primary am-round">4</span>
					</a>
					<ul class="am-dropdown-content tpl-dropdown-content">
						<li class="tpl-dropdown-content-external">
							<h3>你有 <span class="tpl-color-primary">4</span> 个任务进度</h3> <a href="###">全部</a>
						</li>
						<li>
							<a href="javascript:;" class="tpl-dropdown-content-progress"> 
								<span class="task">
									<span class="desc">Amaze UI 用户中心 v1.2 </span> 
									<span class="percent">45%</span>
								</span> 
								<span class="progress"> 
									<div class="am-progress tpl-progress am-progress-striped">
										<div class="am-progress-bar am-progress-bar-success" style="width:45%"></div>
									</div>
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:;" class="tpl-dropdown-content-progress"> 
								<span class="task">
									<span class="desc">新闻内容页 </span> <span class="percent">30%</span>
								</span> 
								<span class="progress">
									<div class="am-progress tpl-progress am-progress-striped">
										<div class="am-progress-bar am-progress-bar-secondary" style="width:30%"></div>
									</div>
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:;" class="tpl-dropdown-content-progress"> 
								<span class="task">
									<span class="desc">管理中心 </span> <span class="percent">60%</span>
								</span> 
								<span class="progress">
									<div class="am-progress tpl-progress am-progress-striped">
										<div class="am-progress-bar am-progress-bar-warning" style="width:60%"></div>
									</div>
								</span>
							</a>
						</li>
					</ul>
				</li>
				<li class="am-hide-sm-only">
					<a href="javascript:;" id="admin-fullscreen" class="tpl-header-list-link">
						<span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span>
					</a>
				</li>
				<li class="am-dropdown" data-am-dropdown data-am-dropdown-toggle>
					<a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
						<span class="tpl-header-list-user-nick">用户</span>
						<span class="tpl-header-list-user-ico"><img src="/views/admin/homePage/img/user01.png" onerror="javascript:this.src='/views/admin/homePage/img/user01.png';" ></span>
					</a>
					<ul class="am-dropdown-content">
						<li><a href="javascript:index_changeInfo();"><span class="am-icon-bell-o"></span> 资料</a></li>
						<li><a href="javascript:;"><span class="am-icon-cog"></span> 设置</a></li>
						<li><a href="javascript:index_logout();"><span class="am-icon-power-off"></span>退出</a></li>
					</ul>
				</li>
				<li>
					<a href="#" class="tpl-header-list-link"><span class="am-icon-question-circle tpl-header-list-ico-out-size"></span> 帮助</a>
				</li>
			</ul>
		</div>
	</header>
	<div class="tpl-page-container tpl-page-header-fixed">
	<!-- 左侧导航 -->
		<div class="tpl-left-nav tpl-left-nav-hover">
			<div class="tpl-left-nav-title">Menu</div>
			<div class="tpl-left-nav-list">
				<ul class="tpl-left-nav-menu" id="menu">
<!-- 					<li class="tpl-left-nav-item"> -->
<!-- 						<a href="/admin/home.do?homePage" class="nav-link active">  -->
<!-- 							<i class="am-icon-home"></i> <span>首页</span> -->
<!-- 						</a> -->
<!-- 					</li> -->
					<script type="text/html" id="homePage-menu-template">
					{{each menu}}
						<li class="tpl-left-nav-item">
							<a href="javascript:showMenu({{$index}});" class="nav-link tpl-left-nav-link-list"> 
								<i class="am-icon-table"></i> <span>{{$value.cdmc}}</span> 
								<i class="{{if $value.zcd.length > 0}}am-icon-angle-right{{/if}} tpl-left-nav-more-ico am-fr am-margin-right"></i>
							</a>
							<ul class="tpl-left-nav-sub-menu">
								<li>
									{{each $value.zcd}}
									<a href="javascript:changeMenu('{{$value.cdlj}}', '{{$value.cdmc}}');">
										<i class="am-icon-angle-right"></i><span>{{$value.cdmc}}</span> 
										<i class="tpl-left-nav-content-ico am-fr am-margin-right"></i>
									</a>
									{{/each}}
								</li>
							</ul>
						</li>
					{{/each}}
					</script>
				</ul>
			</div>
		</div>
		<div class="tpl-content-wrapper">
			<ol class="am-breadcrumb">
				<li class="am-icon-home am-active"> 首页</li>
			</ol>
			<!-- 右侧iframe -->
			<div class="rightIframe">
				<iframe id="myIframe" width="100%" height="100%" src="/admin/user.do?list" frameborder="0" style="border-radius: 10px;"></iframe>
			</div>
		</div>
	</div>
</body>
<script src="/views/admin/homePage/js/jquery.min.js"></script>
<script src="/views/common/layer/layer.js"></script>
<script src="/views/common/template-web.js"></script>
<script type="text/javascript">
	var screenHeight = $("body")[0].clientHeight - 110;
	$(".tpl-left-nav-hover").attr("style", "height:"+ screenHeight +"px;overflow:auto;overflow-x:hidden;margin-right:20px;");
	$(".rightIframe").attr("style", "height:"+ (screenHeight - 40) +"px;overflow:hidden;");
	
	var index_initDatas = function() {
		$.ajax({
			url:'/admin/home.do?user', 
			type: 'post',
			data: {}, 
			dataType: 'json',
			success: function(res) {
				$(".tpl-header-list-user-nick").html(res.zsxm);
				$(".tpl-header-list-user-ico img").attr("src", res.yhtx)
			}
		});
		
		$.ajax({
			url:'/admin/home.do?menus', 
			type: 'post',
			data: {}, 
			dataType: 'json',
			success: function(res) {
				var html = template('homePage-menu-template', {menu: res});
				$('#menu').append(html);
			}
		});
	};
	
	var changeMenu = function(cdlj, cdmc){
		$("#myIframe").attr("src", cdlj);
		var innerHtml = "<li class='am-icon-home'><a href='/admin/home.do?homePage'> 首页</a></li>"+
					"<li class='active'>"+ cdmc +"</li>";
		$(".am-breadcrumb").html(innerHtml);
	};
	
	var index_logout = function() {
		layer.open({
			title: '提示',
			icon: 0,
			btn: ['确定', '取消'],
			content: '您确定要退出本系统吗？',
			yes: function() {
				location.href="/logout";
			},
			cancel: function() {}
		});
	};
	
	var index_changeInfo = function(){
		if(isNotNull(vm.userInfo.yhid)){
			$("#myIframe").attr("src","/admin/user.do?edit&yhid="+vm.userInfo.yhid);
		} else {
			layer.msg("请先登录！")
		}
	}
	
	var showMenu = function(index){
		$('.nav-link').eq(index).toggleClass("active");
        $('.tpl-left-nav-link-list').eq(index).siblings('.tpl-left-nav-sub-menu').slideToggle(80).end().find('.tpl-left-nav-more-ico').toggleClass('tpl-left-nav-more-ico-rotate');
	}
	
</script>
<script src="/views/admin/homePage/js/amazeui.min.js"></script>
<script src="/views/admin/homePage/js/iscroll.js"></script>
<script src="/views/admin/homePage/js/app.js?v=11"></script>
</html>