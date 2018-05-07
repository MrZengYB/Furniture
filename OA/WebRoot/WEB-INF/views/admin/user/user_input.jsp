<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>编辑员工</title>
    <link rel="icon" type="image/png" href="/views/admin/homePage/i/favicon.png">
    <link rel="stylesheet" href="/views/admin/homePage/css/amazeui.min.css" />
    <link rel="stylesheet" href="/views/admin/homePage/css/admin.css">
    <link rel="stylesheet" href="/views/admin/homePage/css/app.css">
    <link rel="stylesheet" href="/views/common/public.css">
    
    <script src="/views/admin/homePage/js/jquery.min.js"></script>
    <script src="/views/admin/homePage/js/amazeui.min.js"></script>
    <script src="/views/admin/homePage/js/app.js"></script>
    <script src="/views/common/layer/layer.js"></script>
    <script src="/views/common/jquery.validate.min.js"></script>
    <style type="text/css">
	    /* input + .error{ 
	 		display: none !important; 
	 	} */
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
	                <span class="am-icon-code"></span> 编辑员工
	            </div>
	        </div>
	        <div class="tpl-block">
	            <div class="am-g tpl-amazeui-form">
	                <div class="am-u-sm-12 am-u-md-11">
	                    <form class="am-form am-form-horizontal" data-am-validator>
	                    	<input type="hidden" name="yhid" value="${data.yhid}" />
	                    	<div class="am-form-group">
	                            <label class="am-u-sm-3 am-form-label">手机 / Telephone</label>
	                            <div class="am-u-sm-9">
	                                <input type="tel" name="username" value="${data.username}" placeholder="输入你的手机号码 / Telephone" required />
	                            </div>
	                        </div>
	                        <div class="am-form-group">
	                            <label class="am-u-sm-3 am-form-label">密码 / Password</label>
	                            <div class="am-u-sm-9">
	                                <input type="password" id="dlmm" name="yhmm" placeholder="输入密码 / Password" required />
	                            </div>
	                        </div>
	                        <div class="am-form-group">
	                            <label class="am-u-sm-3 am-form-label">确认密码 / password</label>
	                            <div class="am-u-sm-9">
	                                <input type="password" name="ensurePassword" data-equal-to="#dlmm" placeholder="再次输入密码 / Password" required />
	                            </div>
	                        </div>
	                        <div class="am-form-group" style="margin-bottom: 5px;">
	                            <label class="am-u-sm-3 am-form-label">姓名 / Name</label>
	                            <div class="am-u-sm-9">
	                                <input type="text" name="zsxm" value="${data.zsxm}" placeholder="姓名 / Name" required />
	                            </div>
	                        </div>
	                        <div class="am-form-group">
	                        	<label class="am-u-sm-3 am-form-label">性别 / sex</label>
	                        	<div class="am-u-sm-9">
	                        		<label class="am-radio-inline">
						        		<input type="radio" name="xb" value="false"  data-am-ucheck checked />男　
						        	</label>
						        	<label class="am-radio-inline">
						        		<input type="radio" name="xb" value="true"  data-am-ucheck />女　　
	                        		</label>
	                        	</div>
						    </div>
						    <div class="am-form-group">
	                            <label class="am-u-sm-3 am-form-label">出生日期 / Birthday</label>
	                            <div class="am-u-sm-9">
	                                <input type="date" name="yhsr" value="${data.yhsr}" placeholder="选择出生日期 / Birthday" required />
	                            </div>
	                        </div>
	                        <div class="am-form-group">
	                            <label class="am-u-sm-3 am-form-label">身份证号 / ID card</label>
	                            <div class="am-u-sm-9">
	                                <input type="text" name="sfzh" value="${data.sfzh}" placeholder="输入身份证号/ ID card" required />
	                            </div>
	                        </div>
	                        <div class="am-form-group">
	                            <label class="am-u-sm-3 am-form-label">现居住地址/ Address </label>
	                            <div class="am-u-sm-9">
	                                <input type="text" name="yhdz" value="${data.yhdz}" placeholder="输入现居住地址/ Address " required />
	                            </div>
	                        </div>
	                        <div class="am-form-group">
	                            <label class="am-u-sm-3 am-form-label">学历/ Education </label>
	                            <div class="am-u-sm-9">
	                                <input type="text" name="yhxl" value="${data.yhxl}" placeholder="输入学历/ Education " required />
	                            </div>
	                        </div>
	                        <div class="am-form-group">
	                            <label class="am-u-sm-3 am-form-label">电子邮件 </label>
	                            <div class="am-u-sm-9"> 
	                                <input type="email" name="yhyx" value="${data.yhyx}" placeholder="输入你的电子邮件 / Email" required />
	                            </div>
	                        </div>
	                        <div class="am-form-group">
			                    <label class="am-u-sm-3 am-form-label">头像 / portrait</label>
			                    <div class="am-u-sm-9">
			                    	<img id="user-img" src="/views/admin/homePage/img/user01.png" onerror="Javascript:this.src='/views/admin/homePage/img/user01.png';" style="max-width:450px;max-height:250px;margin-right:15px;">
			                    	<button class="am-btn am-btn-success am-radius" type="button" id="user-uploadImgBtn" >上传图片</button>
			                        <input id="user-img-input" type="hidden" class="form-control" name="yhtx" value="${data.yhtx}" required />
			                    </div>
			                </div>
			                <div class="am-form-group">
	                            <label class="am-u-sm-3 am-form-label">所属部门 / Department</label>
	                            <div class="am-u-sm-9">
	                                <input type="text" name="yhbmid" value="${data.yhbmid}" placeholder="输入所属部门 / Department" required />
	                            </div>
	                        </div>
	                        <div class="am-form-group">
	                            <label class="am-u-sm-3 am-form-label">职位 / Position</label>
	                            <div class="am-u-sm-9">
	                                <input type="text" name="yhzwid" value="${data.yhzwid}" placeholder="输入职位 / Position" required />
	                            </div>
	                        </div>
	                        <div class="am-form-group">
	                            <label class="am-u-sm-3 am-form-label">简介 / Intro</label>
	                            <div class="am-u-sm-9">
	                                <textarea rows="5" name="yhjj" placeholder="输入个人简介 / Intro">${data.yhjj}</textarea>
	                            </div>
	                        </div>
	                        <div class="am-form-group">
	                            <div class="am-u-sm-9 am-u-sm-push-3">
	                                <button type="submit" class="am-btn am-btn-primary am-radius">保存</button>
	                                <button type="button" class="am-btn am-btn-default am-radius" onclick="userInput_back()">返回</button>
	                            </div>
	                        </div>
	                    </form>
	                    <form method="post" enctype="multipart/form-data" id="userData-edit-upload">
							<input type="file" name="upload" class="user-fmtimg" style="display:none;" onchange="userInput_imgurl(this.value)"/>
						</form>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</body>
<script type="text/javascript">

	var yhid = $("input[name = yhid]").val();
	
	$.validator.addMethod("checkUsernameExist", function(value, element, param){
        var res = true;
        $.ajax({
            type:"POST",
            async: false, 
            url:"/admin/user.do?checkUsernameExist",
            dataType : 'json',
            data: {
            	yhid : yhid,
            	username : value
            },
            success:function(data){
           		res = !data.success;
           		if(!res) $("input[name = username]").focus();
            }
        });
        return res;
    }, function(){layer.tips("该手机号码已存在！","input[name = username]",{tips : 1})});

	var form = $('body').find('form');
	form.validate({
		rules : {
	        username : {checkUsernameExist : true},
	     	ensurePassword : {equalTo: "#dlmm"}
	    }, messages : {
 			ensurePassword : {
            	equalTo : function(){layer.tips("密码输入不一致！","input[name = ensurePassword]",{tips : 1});}
            }
        },
		submitHandler: function(f) {
			$.ajax({
				url : '/admin/user.do?save',
				type: 'post',
				data : $(f).serialize(),
				dataType : 'json',
				success: function(res) {
				    layer.msg(res.title);
				    if(res.success){
				    	setTimeout(function(){ userInput_back(); }, 1000);
				    }
				},
				error: function(data) {
					layer.msg(data);
				}
			});
		}
	});

	var userInput_back = function(){
		window.history.back();
	};
	
	var xb = "${data.xb}";
	if(xb != null && xb != "" && xb != undefined){
		if(xb == 'true'){
			$("input[type = radio]").eq(1).attr("checked","checked");
		} else {
			$("input[type = radio]").eq(0).attr("checked","checked");
		}
	}
	
	$("#user-uploadImgBtn").click(function(){
    	$(".user-fmtimg").click();
    });
    
    var userInput_imgurl = function(){
    	var index = parent.layer.open({
			title: '提示',
			icon: 0,
			btn: ['确定', '取消'],
			content: '是否上传?',
			cancel: function() {},
			yes: function() {
				var uploadImgLoad =  parent.layer.load();
				$.ajax({
					url : '/admin/upload.do?file',
					type: 'POST',
		        	cache: false,
					data: new FormData($('#userData-edit-upload')[0]),
					processData: false,
		        	contentType: false,
					success : function(data){
						 parent.layer.close(uploadImgLoad);
						 var res = eval ("(" + data + ")");
						 var title = res.error == 0 ? "上传成功" : "上传失败";
						 var index = parent.layer.alert(title, function() {
						  	$("#user-img").attr("src", res.url);
						  	$("#user-img-input").val(res.url);
						  	parent.layer.close(index);
						 });
					}
				});				
			}
		});
	}
</script>
</html>
 
