function topage() {
	$("#currPage").val($("#pageId").val());
	$("form").submit();
}

function page(index) {
	var currentPage = $("#currPage").val();
	var totalPage = $("#totalPage").val();
	if (index == 0) {
		if (currentPage == 1) {
			return;
		}
		$("#currPage").val(parseInt(currentPage) - 1);
	} else {
		if (currentPage == totalPage) {
			return;
		}
		$("#currPage").val(parseInt(currentPage) + 1);
	}
	$("form").submit();
}