function getPrice() {
	var url = $("#materialUrl").val();
	$.get("getPrice.do?url=" + url, function(data) {
		if (data.result == 1) {
			$("#price").val(data.price);
			$("#mktprice").val(data.mktprice);
		}
	}, "json");

}