$(function() {
	$('#file_upload').uploadify({
		queueID : "myqueue",
		multi : "", // 传单个文件
		width : 80,
		height : 30,
		buttonText : "文件上传",
		buttonCursor : "hand",
		fileTypeDesc : "支持格式：.xlsx",
		fileTypeExts : '*.xlsx',
		fileSizeLimit : '0',
		swf : 'js/uploadify/uploadify.swf',
		uploader : '/sys/upload.do',
		onUploadSuccess : function(file, serverData, status) {
			if (serverData == 0) {
				alert("上传文件可是不对")
			} else {
				window.setInterval("surplusQueueCount()", 5000);
			}
		},
		onSelect : function() {

		}
	});
});

function surplusQueueCount() {
	$.get("surplusQueue.do", function(data) {
		if (data == 0) {
			window.clearInterval(int);
			$("#myqueue").html("队列中暂无需要处理的数据")
			return;
		}
		$("#myqueue").html("队列中还剩余" + data + "条数据未处理");
	});
}