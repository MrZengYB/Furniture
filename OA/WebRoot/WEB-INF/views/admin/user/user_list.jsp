<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>用户列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/views/admin/homePage/css/amazeui.min.css" />
	<link rel="stylesheet" href="/views/admin/homePage/css/admin.css">
	<link rel="stylesheet" href="/views/admin/homePage/css/app.css">
    <link rel="stylesheet" href="/views/common/animate.min.css">
    
    <script src="/views/common/jquery-3.2.1.min.js"></script>
    <script src="/views/admin/homePage/js/amazeui.min.js"></script>
    <script src="/views/common/template-web.js"></script>
    <script src="/views/common/layer/layer.js"></script>
    <script src="/views/common/until.js"></script>
</head>
<body>
	<div class="am-panel am-panel-default">
  		<div class="am-panel-hd">用户管理</div>
  		<div class="am-panel-bd">
  			<div class="am-g">
	    		<button onclick="userList_add()" class="am-btn am-btn-primary am-radius" type="button">&nbsp;添加</button>
  				<div class="am-u-sm-4"></div>
  				<div class="am-u-sm-8 am-text-right am-form">
  					<div class="am-form-group">
			    		<button onclick="userList_search()" class="am-btn am-btn-primary am-radius am-fr" type="button">&nbsp;搜索</button>
                        <div class="am-u-sm-6 am-fr" style="padding-right: 0px;"> 
		  					<input type="text" id="keyWord" placeholder="姓名/ 手机 / 部门/ 职位"/>
                        </div>
                    </div>
  				</div>
  			</div>
  			<div class="am-g" id="userList-user-content">
  				<script type="text/html" id="userList-user-template">
				{{each users}}
  				<div class="am-u-sm-6" style="margin-top: 10px;padding-left:0px;">
	  				<div class="tpl-content-scope">
		                <div class="note note-info">
		                	<div class="am-g">
		                    	<h3 class="am-u-sm-7" >真实姓名：<span class="close" data-close="note">{{$value.zsxm}}</span></h3>
		                    	<div class="am-u-sm-5 am-text-right" style="padding-right: 0px;">
			                    	<button onclick="userList_edit('{{$value.yhid}}')" class="am-btn am-btn-secondary am-radius" type="button">&nbsp;编辑</button>
			                    	<button onclick="userList_delete('{{$value.yhid}}')" class="am-btn am-btn-danger am-radius" type="button">&nbsp;删除</button>
		                    	</div>
		                    </div>
		                    <div style="position:absolute;right:10px;top:60px;">
		                    	<img id="user-img" src="{{$value.yhtx}}" onerror="Javascript:this.src='/views/admin/homePage/img/user01.png';" style="max-width:450px;max-height:250px;margin-right:15px;">
		                    </div>
		                    <div class="am-g">
			                    <p class="am-u-sm-4"> 性别：{{if $value.xb}}女{{else}}男{{/if}}</p>
			                    <p class="am-u-sm-6 am-u-end"> 手机：{{$value.username}}</p>
		                    </div>
		                    <div class="am-g">
			                    <p class="am-u-sm-4"> 学历：{{$value.yhxl}}</p>
			                    <p class="am-u-sm-6 am-u-end"> 邮箱：{{$value.yhyx}}</p>
		                    </div>
		                    <div class="am-g">
			                    <p class="am-u-sm-4"> 职位：{{$value.yhzwid}}</p>
			                    <p class="am-u-sm-6 am-u-end"> 所属部门：{{$value.yhbmid}}</p>
		                    </div>
		                    <div class="am-g">
			                    <p class="am-u-sm-10"> 居住地址：{{$value.yhdz}}</p>
		                    </div>
		                </div>
		            </div>
	  			</div>
	  			{{/each}}
				</script>
  			</div>
  		</div>
	</div>
</body>
<script type="text/javascript">
	
	var page = 1, rows = 10;
	var userList_loadData = function() {
		$.ajax({
			url:'/admin/user.do?datas', 
			type: 'post',
			data: {
				page: page,
				rows: rows,
				name: $("#keyWord").val()
			}, 
			dataType: 'json',
			success: function(res) {
				var html = template('userList-user-template', {users: res.rows});
				$('#userList-user-content').html(html);
			}
		});
	};
	userList_loadData();
	
	var userList_add = function() {
		$(this)[0].location.href = 'user.do?add';
	};
	
	var userList_edit = function(value) {
		$(this)[0].location.href = 'user.do?edit&yhid=' + value;
	};
	
	var userList_search = function(value) {
		page = 1;
		rows = 10;
		userList_loadData();
	};
	
	var userList_delete = function(value) {
		var index = layer.open({
			title: '提示',
			icon: 0,
			btn: ['确定', '取消'],
			content: '您确定要删除该用户数据吗？',
			cancel: function() {},
			yes: function() {
				$.ajax({
					url : '/admin/user.do?delete',
					type: 'post',
					data : {yhid: value},
					dataType : 'json',
					success : function(res) {
						if(res.success) {
							$('#userList-grid').bootstrapTable('refresh');
							layer.close(index);
							layer.msg(res.title);
						}
					}
				});				
			}
		});
	};
</script>
</html>
 
