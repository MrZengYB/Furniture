<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>聊天室</title>
	<link rel="stylesheet" href="/views/admin/homePage/css/amazeui.min.css" />
	<link rel="stylesheet" href="/views/admin/homePage/css/admin.css">
	<link rel="stylesheet" href="/views/admin/homePage/css/app.css">
    <link rel="stylesheet" href="/views/common/animate.min.css">
    <link rel="stylesheet" href="/views/common/public.css">
    
    <script src="/views/common/jquery-3.2.1.min.js"></script> 
    <script src="/views/admin/homePage/js/amazeui.min.js"></script>
    <script src="/views/common/template-web.js"></script>
    <script src="/views/common/layer/layer.js"></script>
    <script src="/views/common/until.js"></script>
</head>
<body>
	<div class="am-panel am-panel-default" style="border: none;margin:0px;box-shadow:none;">
  		<div class="am-panel-hd">角色管理</div>
  		<div class="am-panel-bd">
  			<div class="am-g">
	    		<button onclick="roleList_add()" class="am-btn am-btn-primary am-radius" type="button">&nbsp;添加</button>
  				<div class="am-u-sm-4"></div>
  				<div class="am-u-sm-8 am-text-right am-form">
  					<div class="am-form-group">
			    		<button onclick="roleList_search()" class="am-btn am-btn-primary am-radius am-fr" type="button">&nbsp;搜索</button>
                        <div class="am-u-sm-4 am-fr" style="padding-right: 0px;"> 
		  					<input type="text" id="keyWord" placeholder="名称"/>
                        </div>
                    </div>
  				</div>
  			</div>
  		</div>
	</div>
</body>
<script type="text/javascript">

	
</script>
</html>
