$("#sample-table-1 input").blur(function(){
	var obj = $(this);
  	var id = $(this).attr("alt");
  	var code = $(this).val();
  	var url = "";
  	var t = $(this).attr("t");
  	if(t == 'cat') {
  		url = "updateCatalogCode.do";
  	} else if(t == 'type') {
  		url = "updateTypeCode.do";
  	} else if(t == 'brand') {
  		url = "updateBrandCode.do";
  	}
  	if(code != "") {
  		$.post(url,{id:id,code:code},function(data){
  			if(data == 1) {
  				//alert("添加成功!");
  			} else if(data == 0) {
  				alert("添加失败，请稍候再试!");
  			} else if(data == -1) {
  				alert("您填入的简称已经存在，请换一个!");
  				$(obj).focus();
  			}
  		});
  	}
});