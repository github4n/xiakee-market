$(function() {
	var upload_url = "http://img.xiakee.com/xiakee-image/upload";
	var param = null;
	$("#file_upload").uploadify({
		queueID : "myqueue",
		multi : "false", // 传单个文件
		width : 60,
		height : 30,
		buttonText : "图片上传",
		fileTypeDesc : "支持格式：.gif;*.jpg;*.jpeg;",
		fileTypeExts : '*.gif;*.jpg;*.jpeg;',
		fileSizeLimit : '200KB',
		swf : 'js/uploadify/uploadify.swf',
		uploader : upload_url,
		onUploadSuccess : function(file, serverData, status) {
			var json = eval("(" + serverData + ")");
			if (json.status == 0) {
				$("#message").html(json.message);
			} else {
				$("#uploadImageId").attr("src", json.message);
				$("#materialImage").val(json.message);
			}
		},
		onSelect : function() {
			$.ajax({
				type : "GET",
				url : "upload-param.do?type=material",
				async : false,
				success : function(data) {
					$("#file_upload").uploadify("settings", "formData", {
						'signature' : data.signature,
						'timestamp' : data.timestamp,
						'param' : data.param,
						'type' : data.type,
						'width' : 600,
						'height' : 500
					});
				},
				dataType : "json"
			});
		}
	});
});