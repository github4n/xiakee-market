$().ready(addAbroadUrl());

function addAbroadUrl() {
	var $addButton = $("#addAbroadUrl");
	$addButton.bind("click", displayCatalogs);
}

function displayCatalogs() {
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";

	$(grid_selector)
			.jqGrid(
					{
						url : "displayCatalogs.do",
						datatype : "json",
						height : '100%',
						mtype : "POST",
						colNames : [ '类型名称', '登录名', '部门', '部门', '操作' ],
						colModel : [
								{
									name : 'cat_name',
									index : 'cat_name',
									width : 100
								},
								{
									name : 'parent_id',
									index : 'parent_id',
									width : 100,
									fixed : false
								},
								{
									name : 'catCode',
									index : 'catCode',
									width : 100,
									fixed : false
								},
								{
									name : 'createTime',
									index : 'createTime',
									width : 100,
									hidden : true,
									fixed : false
								},
								{
									name : 'op',
									width : 150,
									formatter : function(p1, p2, record) {
										var o1 = '<a href="/sys/user-admin/detail.html?id='
												+ record.cat_id
												+ '" title="查看详情">查看详情</a>';
										if (record.cat_id < 40) {
											var o2 = '<a href="/sys/user-admin/edit.html?id='
													+ record.cat_id
													+ '" title="修改" >修改</a>';
										} else {
											var o2 = '';
										}
										return o1 + "&nbsp;&nbsp;&nbsp;" + o2;
									}
								} ],
						viewrecords : true,
						rowNum : 100,
						rowList : [ 100, 100, 100 ],
						pager : pager_selector,
						altRows : true,
						multiselect : false,
						multiboxonly : false,
						loadComplete : function() {
							jqGrid.reset(jQuery);

						},
						autowidth : true
					});
}

function delSku(id) {
	if (confirm("确定要删除数据吗？")) {
		window.location.href = "delSkuInfo.do?id=" + id;
	}
}

function importShop(skuCode) {
	if (confirm("确定要导入商城吗？")) {
		bootbox.alert({
			buttons : {
				ok : {
					label : '关闭',
					className : 'btn-myStyle'
				}
			},
			message : "正在导入数据...请稍候在商城后台查看",
			callback : function() {

			},
			title : "系统提示",
		});
		var url = "importShop.do?skuCode=" + skuCode;
		$.get(url, function(data) {
		});
	}
}

function checkClick(obj) {
	if ($(obj).val() == 0) {
		if ($(obj).is(':checked')) {
			$(":input[name='checkbox']").prop('checked', true);
		} else {
			$(":input[name='checkbox']").prop('checked', false);
		}
	} else {
		if (!$(obj).is(':checked')) {
			$("#checkboxAll").prop('checked', false);
		}
	}
}

function save() {
	var goodsnoArray = new Array();
	var priceArray = new Array();
	var costArray = new Array();
	var storeArray = new Array();
	$('input[name="checkbox"]:checked').each(function() {
		var goodsno = $(this).val();
		var price = $("#" + goodsno + "Price").val();
		var cost = $("#" + goodsno + "Cost").val();
		var mktprice = $("#" + goodsno + "Mktprice").val();
		var store = $("#" + goodsno + "Store").val();
		goodsnoArray.push(goodsno);
		priceArray.push(price);
		costArray.push(cost);
		storeArray.push(store);
	});
	if (goodsnoArray.length == 0) {
		alert("请勾选要更新的货品");
		return;
	}
	var skuCode = $("#skuCode").val();
	var grossId = $("#grossProfitMarginSelect").val();
	var grossProfitMargin = $("#grossProfitMargin").val();
	if (grossProfitMargin == "") {
		return;
	}
	var result = grossProfitMargin.match("^[0-9]+(.[0-9]+)?$");
	if (result == null) {
		alert("你的毛利率输入有误");
		return;
	}
	var goodsnoStr = JSON.stringify(goodsnoArray);
	var priceStr = JSON.stringify(priceArray);
	var costStr = JSON.stringify(costArray);
	var storeStr = JSON.stringify(storeArray);

	$.post("updatePrice.do", {
		goodsno : goodsnoStr,
		price : priceStr,
		cost : costStr,
		store : storeStr,
		skuCode : skuCode,
		grossId : grossId,
		grossProfitMargin : grossProfitMargin
	}, function(data) {
		if (data == 1) {
			alert("修改成功!");
			location.reload();
		} else {
			alert("更新失败!");
		}
	});
}

function showPriceIncreaseSelect(obj) {
	if ($(obj).val() == "") {
		$("#priceIncreaseSelect").css("display", "none");
		$('select[name="priceIncreaseArithmetic"]').val("");
		$('input[name="priceIncrease"]').val("");
	} else {
		$("#priceIncreaseSelect").css("display", "block");
	}
}

function priceIncreaseArithmeticSelect(obj) {
	if ($(obj).val() == "") {
		$('input[name="priceIncrease"]').removeAttr("pattern");
		$('input[name="priceIncrease"]').removeAttr("required");
		$('input[name="priceIncrease"]').removeAttr("title");
		pattern = ""
	} else if ($(obj).val() == "3") {
		$('input[name="priceIncrease"]').attr("pattern", "\\d+%-\\d+%");
		$('input[name="priceIncrease"]').attr("required", "");
		$('input[name="priceIncrease"]').attr("title", "请按照正确格式输入：例如10%-30%");
	} else {
		$('input[name="priceIncrease"]').attr("pattern", "\\d+%");
		$('input[name="priceIncrease"]').attr("required", "");
		$('input[name="priceIncrease"]').attr("title", "请按照正确格式输入：例如10%");
	}
}

function savePriceLockTime(){
	var pid = $("#pid").val();
	var time = $("#priceLockTimeId").val();
	$.post(
		"setPriceLockTime.do",
		{
			pid:pid,
			time:time
		},
		function(data){
			if(data == 1) {
				alert("设置成功");
				$("#myModal").modal("hide");
				$("#locktimetd"+pid).text(time);
			}
		}
	);
}