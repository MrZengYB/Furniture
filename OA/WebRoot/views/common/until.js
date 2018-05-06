//时间格式化
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, //月份 
		"d+" : this.getDate(), //日 
		"h+" : this.getHours(), //小时 
		"m+" : this.getMinutes(), //分 
		"s+" : this.getSeconds(), //秒 
		"q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
		"S" : this.getMilliseconds() //毫秒 
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};

var lookImg = function(value) {
	parent.layer.open({
		title : false,
		type : 1,
		skin : 'layui-layer-demo',
		shadeClose : true,
		maxWidth : window.screen.availWidth,
		content : '<img src="' + value + '" style="max-width:400px;" />'
	});
};

/**
 * 获取地址栏参数
 * @param name
 * @returns {*}
 */
var getAttribute = function(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"),
		r = window.location.search.substr(1).match(reg),
		qs = '';
	if (r != null)
		qs = decodeURIComponent(r[2]);
	return qs;
};

/**
 * 判断一个对象是否为空.
 * @param _obj
 * @returns {*}
 */
var isNull = function(_obj) {
	if (_obj == null || _obj == "" || _obj == undefined) {
		return true;
	} else {
		return false;
	}
};

/**
 * 判断一个对象是否不为空.
 * @param _obj
 * @returns {*}
 */
var isNotNull = function(_obj) {
	if (_obj == null || _obj == "" || _obj == undefined) {
		return false;
	} else {
		return true;
	}
};

//图片上传  
var summernoteUploadImg = function(file, editor, $editable) {
	var filename = false;
	var host = window.location.host;
	try {
		filename = file['name'];
	} catch (e) {
		filename = false;
	}
	if (!filename) {
		$(".note-alarm").remove();
	}

	//以上防止在图片在编辑器内拖拽引发第二次上传导致的提示错误  
	var data = new FormData();
	data.append("file", file);
	data.append("key", filename); //唯一性参数  
	$.ajax({
		data : data,
		type : 'POST',
		url : '/admin/upload.do?file',
		cache : false,
		contentType : false,
		processData : false,
		success : function(data) {
			var res = eval("(" + data + ")");
			if (res.error == 1) {
				parent.layer.msg('上传失败，请刷新重试。', {
					title : '提示',
					icon : 2,
					time : 1500, //1.5秒后自动关闭
					skin : 'layer-ext-moon', //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
					shade : 0.6, //遮罩透明度
					anim : 1 //0-6的动画形式，-1不开启
				});
				return false;
			}
			var imgUrl = document.location.protocol + "//" + host + res.url;
			editor.insertImage($editable, imgUrl);
		},
		error : function() {
			alert("上传失败！");
			return;
		}
	});
};

var setCookie = function(name, value, time) {
	var strsec = getsec(time);
	var exp = new Date();
	exp.setTime(exp.getTime() + strsec * 1);
	document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

var getsec = function(str) {
	var str1 = str.substring(1, str.length) * 1;
	var str2 = str.substring(0, 1);
	if (str2 == "s") {
		return str1 * 1000;
	} else if (str2 == "h") {
		return str1 * 60 * 60 * 1000;
	} else if (str2 == "d") {
		return str1 * 24 * 60 * 60 * 1000;
	}
}

var getCookie = function(name) {
	var arr;
	var	reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if (arr = document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}

var delCookie = function(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}