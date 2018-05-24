$(function() {
	analyOriginData(null,5,'skuSumPie');
	
	$("#skuSumDatasBtn").bind("click", function() {
		analyOriginData($('input[name=skuSumDatas]').val(),5,'skuSumPie');
	});

	$('input[name=skuSumDatas]').daterangepicker({
		dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
		format : 'YYYY/MM/DD'
	}).prev().on(ace.click_event, function() {
		$(this).next().focus();
	});
});

function analyOriginData(date,type,target) {
	$.getJSON("analyOrderOrigin.do", {
		"date" : date,
		"type":type
	}, function(data) {
		$('#' + target).highcharts(data);
	});
}