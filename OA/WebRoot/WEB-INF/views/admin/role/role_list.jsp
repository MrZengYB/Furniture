<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>角色管理</title>
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
  			<div class="am-g" id="roleList-role-content">
  				<script type="text/html" id="roleList-role-template">
				{{each roles}}
  				<div class="am-u-sm-6" style="margin-top: 10px;padding-left:0px;float:left;">
	  				<div class="tpl-content-scope">
		                <div class="note note-info">
		                	<div class="am-g">
		                    	<h3 class="am-u-sm-6" ><span class="close" data-close="note">{{$value.mc}}</span></h3>
		                    	<div class="am-u-sm-6 am-text-right" style="padding-right: 0px;">
			                    	<button onclick="roleList_edit('{{$value.jsid}}')" class="am-btn am-btn-secondary am-radius" type="button">&nbsp;编辑</button>
			                    	<button onclick="roleList_delete('{{$value.jsid}}')" class="am-btn am-btn-danger am-radius" type="button">&nbsp;删除</button>
		                    	</div>
		                    </div>
		                </div>
		            </div>
	  			</div>
	  			{{/each}}
				</script>
  			</div>
  			<div class="am-g" id="public_pagination"></div>
  		</div>
	</div>
</body>
<script type="text/javascript">

	var page = 1, rows = 10, total = 0;
	var roleList_loadData = function() {
		$.ajax({
			url : '/admin/role.do?data',
			type: 'POST',
			dataType : 'json',
			data : {
				page : page,
				rows : rows,
				keyword : $("#keyWord").val()
			},
			success : function(res) {
				console.log(res)
				$('#roleList-role-content').html(template('roleList-role-template', {roles : res.rows}));
				pagination(res, 'public_pagination');
			}
		});
	};
	roleList_loadData();
	
	var pagination = function(data, divID){
		pageCount = data.pageCount;
		page = data.pageIndex;
		total = data.total - ((pageCount-1) * rows);
		var paginationHtml = '<ul class="am-pagination am-pagination-centered">';
		if ( page > 2 ) paginationHtml += '<li class="page" data-page="1"><a href="javascript:pagination_selectPage(1);">首页</a></li>';
		if(page == 1) paginationHtml += '<li class="am-disabled"><a href="javascript:pagination_upPage();">&laquo;</a></li>';
		else paginationHtml += '<li><a href="javascript:pagination_upPage();">&laquo;</a></li>';
		if ( page > 3 && page > data.pageCount - 2 ){
			paginationHtml += '<li><span>...</span></li>';
		}
		for (var i = 1; i <= data.pageCount; i++){
			if ((i >= page - 2 && i <= page + 2 )){
				if (i == page){
					paginationHtml += '<li class="am-active"><a href="javascript:pagination_selectPage('+ i +');">'+ i +'</a></li>';
				} else {
					paginationHtml += '<li><a href="javascript:pagination_selectPage('+ i +');">'+ i +'</a></li>';
				}
			}
		}
		if (page < data.pageCount && page != data.pageCount - 1 ) paginationHtml += '<li><span>...</span></li>';
		if(page == pageCount) paginationHtml += '<li class="am-disabled"><a href="javascript:pagination_downPage();">&raquo;</a></li>';
		else paginationHtml += '<li><a href="javascript:pagination_downPage();">&raquo;</a></li>';
		paginationHtml += '</ul>';
		document.getElementById(divID).innerHTML = paginationHtml;
	};

	var pagination_selectPage = function(pageIndex){
		page = pageIndex;
		roleList_loadData();
		$("html, body").animate({scrollTop : 0}, 100);
	};

	var pagination_upPage = function(){
		if(page > 1){
			page--;
			roleList_loadData();
			$("html, body").animate({scrollTop : 0}, 100);
		}
	};

	var pagination_downPage = function(){
		if(page < pageCount){
			page++;
			roleList_loadData();
			$("html, body").animate({scrollTop : 0}, 100);
		}
	};
	
	var roleList_search = function(){
	  	roleList_loadData();
    }
	
	var roleList_add = function() {
		$(this)[0].location.href = 'role.do?add';
	};
	
	var roleList_edit = function(value) {
		$(this)[0].location.href = 'role.do?edit&jsid=' + value;
	};
	
	var roleList_delete = function(value) {
		var content = '您确定要删除该角色数据吗？';
		$.ajax({
			url : '/admin/role.do?checkjsused',
			type: 'POST',
			dataType : 'json',
			data : {
				jsid : value
			},
			success : function(data) {
				if(data.title == "false"){
					content = '有用户正在使用该角色，您确定要删除该角色数据吗？';
				}
				var index = parent.layer.open({
					title: '提示',
					icon: 0,
					btn: ['确定', '取消'],
					content: content,
					cancel: function() {},
					yes: function() {
						var uploadLoad =  parent.layer.load();
						$.ajax({
							url : '/admin/role.do?delete',
							type: 'post',
							data : {jsid : value},
							dataType : 'json',
							success : function(res) {
								parent.layer.close(uploadLoad);
								if(res.success) {
									if (total == 1 && page > 1){
										page--;
									}
									roleList_loadData();
									parent.layer.close(index);
									parent.layer.msg(res.title);
								}
							}
						});				
					}
				});
			}
		});
	};
</script>
</html>
