<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>OA - 登录</title>
<link rel="icon" type="image/png" href="/views/admin/homePage/i/favicon.png">
<link href="/loginPage/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/loginPage/js/jQuery.js"></script>
<script type="text/javascript" src="/loginPage/js/fun.base.js"></script>
<script type="text/javascript" src="/loginPage/js/script.js"></script>
<style type="text/css">
	a { cursor: pointer; font-size: 14px; }
	a:HOVER { color: #31b6e7; font-weight: bold; }
</style>
</head>
<body>
	<div class="login">
		<div class="box png">
			<form action="/j_spring_security_check" method="post">
				<div class="logo png"></div>
				<div class="input">
					<div class="log">
						<div class="name">
							<label>用户名</label>
							<input type="text" class="text" id="value_1" placeholder="用户名" name="j_username" tabindex="1">
						</div>
						<div class="pwd">
							<label>密 码</label>
							<input type="password" class="text" id="value_2" placeholder="密码" name="j_password" tabindex="2">
							<input type="submit" class="submit" tabindex="3" value="登  录" style="width:100px;">
							<div class="check"></div>
						</div>
						<div class="tip"></div>
						<div style="float:right;">
							<a><i>注册</i></a>&nbsp; <font size="3">/</font> <a><i>忘记密码？</i></a>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="air-balloon ab-1 png"></div>
		<div class="air-balloon ab-2 png"></div>
		<div class="footer"></div>
	</div>
	<div style="text-align:center;margin:100px 0; font:normal 14px/24px 'MicroSoft YaHei';">
		<p>第7小组网络办公自动化平台 —— 厚德D1-743版权所有</p>
	</div>
</body>
</html>