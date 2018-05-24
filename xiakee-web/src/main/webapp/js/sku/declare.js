$(function(){
	var size = $("#size").val();
	for (var int = 1; int <= size; int++) {
		var cityV = $("#consigneeCityCodeHidden"+int).val();
		consigneeStateCodeChange($("#consigneeStateCode"+int), int, cityV);
		var districtV = $("#consigneeDistrictCodeHidden" + int).val();
		consigneeCityCodeChange($("#consigneeCityCode"+int), int, districtV);
	}
});

function consigneeStateCodeChange(obj, i , v) {
	var value = $(obj).val();
	if(value == ""){
		return;
	}
	$.ajax({  
        type : "get",  
        url : "udfexCity.do?stateCode="+value,  
        async : false,
        dataType : "json",
        success : function(data){  
        	var html = '<option value=""></option>';
    		for ( var index in data) {
    			var item = data[index];
    			html += '<option value="' + item.cityCode + '"';
    			if(item.cityCode == v) {
    				html += ' selected=selected ';
    			}
    			html += '>' + item.cityName + '</option>';
    		}
    		$("#consigneeCityCode"+i).html(html);
    		$("#consigneeDistrictCode"+i).html("<option value=''></option>"); 
        }  
    });
}

function consigneeCityCodeChange(obj, i, v) {
	var value = $(obj).val();
	if(value == ""){
		return;
	}
	$.get("udfexDistrict.do?cityCode="+value, function(data){
		var html = '<option value=""></option>';
		for ( var index in data) {
			var item = data[index];
			html += '<option value="' + item.districtCode + '" ';
			if(item.districtCode == v) {
				html += ' selected=selected ';
			}
			html += '>' + item.districtName + '</option>';
		}
		$("#consigneeDistrictCode"+i).html(html);
	},"json");
}

function classifyChange(obj, i) {
	var value = $(obj).val();
	if(value == ""){
		return;
	}
	$.get("udfexClassify.do?classifyId="+value, function(data){
		var html = '<option value=""></option>';
		for ( var index in data) {
			var item = data[index];
			html += '<option value="' + item.categoryName + '">' + item.categoryName + '</option>';
		}
		$("#goodsCode"+i).html(html);
	},"json");
}

function topage() {
	window.location.href = 'displayAllDeclareBeans.do?currPage='+$("#pageId").val();
}

function page(index) {
	var topage = 0;
	var currentPage = $("#currPage").val();
	var totalPage = $("#totalPage").val();
	if (index == 0) {
		if (currentPage == 1) {
			return;
		}
		topage = parseInt(currentPage) - 1
	} else {
		if (currentPage == totalPage) {
			return;
		}
		topage = parseInt(currentPage) + 1
	}
	window.location.href = 'displayAllDeclareBeans.do?currPage='+topage;
}

