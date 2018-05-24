function analyOriginData(date,type,target) {
	$.getJSON("analyOrderOrigin.do", {
		"date" : date,
		"type":type
	}, function(data) {
		$('#' + target).highcharts(data);
	});
}