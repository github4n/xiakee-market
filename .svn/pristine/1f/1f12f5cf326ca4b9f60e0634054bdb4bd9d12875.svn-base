$(function() {
	loadFirstClassify();
	loadSecondClassify();
	loadThirdClassify();
	addAbroadUrl();
	loadGrossProfitMargin();
});

function loadFirstClassify() {
	if (typeof (firstClassify) == "undefined") {
		getHtmlByParentId($("#firstClassifySelect"), "0", "");
	} else {
		getHtmlByParentId($("#firstClassifySelect"), "0", firstClassify);
	}
	if (typeof (firstClassify) != "undefined" && firstClassify != ""
			&& firstClassify != null) {
		getHtmlByParentId($("#secondClassifySelect"), firstClassify,
				secondClassify);
	}
	if (typeof (secondClassify) != "undefined" && secondClassify != ""
			&& secondClassify != null) {
		getHtmlByParentId($("#thirdClassifySelect"), secondClassify,
				thirdClassify);
	}
}

function loadGrossProfitMargin() {
	$("#grossProfitMarginSelect").change(function() {
		var value = $(this).val();
		$("#grossProfitMargin").val(arr[value]);
		updatePrice(arr[value]);
	});
}

function loadSecondClassify() {
	$("#firstClassifySelect").change(function() {
		var parent_id = $(this).val();
		getHtmlByParentId($("#secondClassifySelect"), parent_id, "");
	});
}

function loadThirdClassify() {
	$("#secondClassifySelect").change(function() {
		var parent_id = $(this).val();
		getHtmlByParentId($("#thirdClassifySelect"), parent_id, "");
	});
}

function getHtmlByParentId(obj, parent_id, val) {
	if (parent_id == null || parent_id == "") {
		$(obj).html("");
		getClassify();
		return;
	}
	$.get("getCatalogByParentId.do?parent_id=" + parent_id, function(data) {
		var html = '<option value=""></option>';
		for ( var index in data) {
			var item = data[index];
			if (item.catCode) {
				html += '<option value="' + item.cat_id + '">' + item.cat_name
						+ '</option>';
			}
		}
		$(obj).html(html);
		$(obj).val(val);
		getClassify();
	}, "json");
}

function addAbroadUrl() {
	var $addButton = $("#addAbroadUrl");
	$addButton
			.bind(
					"click",
					function() {
						var $html = '<label class="block clearfix"><span class="block input-icon input-icon-right"><input type="text" pattern="(http|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?" class="form-control" placeholder="海外商品参考网址" name="urls"/></span></label>';
						$addButton.before($html);
					});
}

function getClassify() {
	var classifyid = $("#thirdClassifySelect").val();
	if (classifyid == null || classifyid == '') {
		classifyid = $("#secondClassifySelect").val()
	}
	if (classifyid == null || classifyid == '') {
		classifyid = $("#firstClassifySelect").val()
	}
	if (classifyid == null || classifyid == '') {
		$("#typeSelect").val("");
		$("#typeInput").val("");
	} else {
		$.get("getCatalogTypeById.do?id=" + classifyid, function(data) {
			$("#typeSelect").val(data);
			$("#typeInput").val(data);
		});
	}
	$("#classify").val(classifyid);
}

var index = 0;
function submitCheck() {
	if (index == 1) {
		return true;
	}
	var url = $("[name='mainUrl']").val();
	if (url == '') {
		return false;
	}
	$("[name='urls']").each(function(i) {
		url = url + ";" + this.value;
	});
	$.ajax({
		type : "get",
		url : "checkUrl.do?urls=" + encodeURIComponent(url),
		async : false,
		success : function(data) {
			if (data.indexOf("已经存在") > 0) {
				alert(data);
			} else {
				index = 1;
			}
		}
	});
	if (index == 1) {
		return true;
	} else {
		return false;
	}
}

function checkUrl() {
	var url = $("[name='mainUrl']").val();
	if (url == '') {
		return;
	}
	$("[name='urls']").each(function(i) {
		url = url + ";" + this.value;
	});
	$.ajax({
		type : "get",
		url : "checkUrl.do?urls=" + encodeURIComponent(url),
		success : function(data) {
			if (data.indexOf("已经存在") > 0) {
				alert(data);
			} else {
				index = 1;
				console.log("URL检测完成")
			}
		}
	});
}

function chengeGross() {
	var value = $("#grossProfitMargin").val()
	var result = value.match("^[0-9]+(.[0-9]+)?$");
	if (result == null) {
		alert("你的毛利率输入有误");
		return;
	}
	updatePrice(value);
}

function updatePrice(value) {
	$('input[name="cost"]').each(function() {
		var cost = $(this).val();
		var goodsno = $(this).attr("alt");
		var oldprice = parseInt($("#" + goodsno + "Price").val()) % 10;
		var price = parseInt(parseFloat(cost) * (1 + parseFloat(value)));
		$("#" + goodsno + "Price").val(parseInt(price / 10) * 10 + oldprice);
	});
}
