<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>编辑角色</title>
    <link rel="icon" type="image/png" href="/views/admin/homePage/i/favicon.png">
    <link rel="stylesheet" href="/views/admin/homePage/css/amazeui.min.css" />
    <link rel="stylesheet" href="/views/admin/homePage/css/admin.css">
    <link rel="stylesheet" href="/views/admin/homePage/css/app.css">
    <link rel="stylesheet" href="/views/common/jsTree/style.min.css">
    <link rel="stylesheet" href="/views/common/public.css">
    
    <script src="/views/admin/homePage/js/jquery.min.js"></script>
    <script src="/views/admin/homePage/js/amazeui.min.js"></script>
    <script src="/views/admin/homePage/js/app.js"></script>
    <script src="/views/common/layer/layer.js"></script>
    <script src="/views/common/jsTree/jstree.min.js"></script>
    <script src="/views/common/jquery.validate.min.js"></script>
    <style type="text/css">
		label.error{
			display: none !important;
		}
		.am-ucheck-icons{
			height: 50px;
			line-height: 40px;
		}
    </style>
</head>
<body data-type="generalComponents">
	<div class="mainBox">
	    <div class="tpl-portlet-components" style="margin-bottom: 10px;">
	        <div class="portlet-title">
	            <div class="caption font-green bold">
	                <span class="am-icon-code"></span> 编辑角色
	            </div>
	        </div>
	        <div class="tpl-block">
	            <div class="am-g tpl-amazeui-form">
	                <div class="am-u-sm-12 am-u-md-11">
	                    <form class="am-form am-form-horizontal" data-am-validator>
	                    	<input type="hidden" name="jsid" value="${data.jsid}" />
	                    	<div class="am-form-group">
	                            <label class="am-u-sm-3 am-form-label">名称 / Role</label>
	                            <div class="am-u-sm-9">
	                                <input type="tel" name="mc" value="${data.mc}" placeholder="输入角色名称 / Role" required />
	                            </div>
	                        </div>
	                        <div class="am-form-group">
	                        	<label class="am-u-sm-3 am-form-label">是否显示 / show</label>
	                        	<div class="am-u-sm-9">
	                        		<label class="am-radio-inline">
						        		<input type="radio" name="sfxs" value="true"  data-am-ucheck checked />是　
						        	</label>
						        	<label class="am-radio-inline">
						        		<input type="radio" name="sfxs" value="false"  data-am-ucheck />否　　
	                        		</label>
	                        	</div>
						    </div>
	                        <div class="am-form-group">
	                            <label class="am-u-sm-3 am-form-label">权限设置 / Authority</label>
	                            <div class="am-u-sm-9">
	                                <div id="roleinput-menus"></div>
	                            </div>
	                        </div>
	                        <div class="am-form-group">
	                            <div class="am-u-sm-9 am-u-sm-push-3">
	                                <button type="submit" class="am-btn am-btn-primary am-radius">保存</button>
	                                <button type="button" class="am-btn am-btn-default am-radius" onclick="roleInput_back()">返回</button>
	                            </div>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
<script type="text/javascript">

	var jscdids = "${jscdids}";
	var _jscdids = jscdids.substring(1, jscdids.length - 1).split(",");
	
    var menus = {};
    var jsid = $("input[name = jsid]").val();
    
    $('#roleinput-menus').jstree({
		plugins: ['checkbox'], 
		'checkbox': {
             'keep_selected_style': false,//是否默认选中
             'three_state': true //父子级别级联选择
         },
         'core': {
         	 'check_callback': true,
             'data': function(obj, callback) {
             	var jsonarray = [];
                $.ajax({
                    type : 'POST',
                    url : '/admin/role.do?menu&&jsid=' + jsid,
                    dataType : 'json',
                    async : false,
                    success : function(datas) {
                        for(var i = 0 ; i < datas.length; i++) {
                            var arr = {
                            	'id' : datas[i].cdid,
                                'parent' : datas[i].sjcdid === '' || datas[i].sjcdid === null ? '#' : datas[i].sjcdid,
                                'text' : datas[i].cdmc,
                                'state' : {
                                	'opened' : true,
                                	'selected' : jscdids.indexOf(datas[i].cdid) > 0 ? true : false
                            	}
                            }; 
                            jsonarray.push(arr);
                        }
                    }
                });
                callback.call(this, jsonarray);
             }
         }
     }).on('activate_node.jstree', function(obj, e) {
     	var node = e.node;
	    menus = {};
	    var checked = $('#roleinput-menus').jstree('get_checked');
    	for(var i = 0; i < node.parents.length; i++) {
    		if(node.parents[i] === '#') {
    			continue;
    		}
    		if(node.state.checked) {
    			menus[node.parents[i]] = node.parents[i];
    		}
    	}
    	for(var i = 0; i < checked.length; i++) {
    		menus[checked[i]] = checked[i];
    	}
     }).on('loaded.jstree', function (event, data) {
    	 for(var v in _jscdids){
	     	menus[_jscdids[v]] = _jscdids[v];
	     	if(!data.instance.is_parent(_jscdids[v])) {
	     		data.instance.check_node(_jscdids[v]);
	     	}
    	 }
     });
     
     $.validator.addMethod("checkMcExist", function(value, element, param){
        var res = true;
        $.ajax({
            type:"POST",
            async: false, 
            url:"/admin/role.do?checkmcexist",
            dataType : 'json',
            data: {
            	jsid : jsid,
            	mc : value
            },
            success:function(data){
            	if(data.title == "false") {
            		res = false;
            	}
            }
        });
        return res;
    },"<font color='#E47068'>该角色名称已存在</font>");
    
	var form = $('body').find('form');
	form.validate({
		rules : {
	        mc : {checkMcExist : true}
        },
		submitHandler: function(f) {
			var menuids = '';
			for(var v in menus) {
				menuids += v + ',';
			}
			menuids = menuids.substring(0, menuids.length - 1);
  			$.ajax({
				url : '/admin/role.do?save&menus=' + menuids,
				type: 'post',
				data : $(f).serialize(),
				dataType : 'json',
				success: function(res) {
					if(res.success) {
						roleInput_back();
						layer.msg(res.title);
					}
				}
			});
		}
	});
	
	var roleInput_back = function() {
		$(this)[0].location.href = '/admin/role.do?list';
	};
</script>
</body>
</html>
