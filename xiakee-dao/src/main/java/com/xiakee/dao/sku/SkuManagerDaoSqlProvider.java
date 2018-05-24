package com.xiakee.dao.sku;

import java.util.Map;

import org.springframework.util.StringUtils;

public class SkuManagerDaoSqlProvider {

	public String selectByBrandAndClassify_page(Map<String, Object> param) {
		StringBuffer sqlsb = new StringBuffer();
		sqlsb.append("select * from sku_manage where 1=1 ");
		if (!StringUtils.isEmpty(param.get("brand"))) {
			sqlsb.append(" and brand = ").append(param.get("brand"));
		}
		if (!StringUtils.isEmpty(param.get("classify"))) {
			sqlsb.append(" and classify = ").append(param.get("classify"));
		}
		if (!StringUtils.isEmpty(param.get("skuCode"))) {
			sqlsb.append(" and skuCode = '").append(param.get("skuCode")).append("'");
		}
		if (!StringUtils.isEmpty(param.get("name"))) {
			sqlsb.append(" and (enName like '%");
			sqlsb.append(String.valueOf(param.get("name")).replaceAll("'", "\\'"));
			sqlsb.append("%' or zhName like '%");
			sqlsb.append(String.valueOf(param.get("name")).replaceAll("'", "\\'"));
			sqlsb.append("%')");
		}
		if (!StringUtils.isEmpty(param.get("isImport"))) {
			sqlsb.append(" and isImport = ").append(param.get("isImport"));
		}
		if (!StringUtils.isEmpty(param.get("grossId"))) {
			sqlsb.append(" and grossId = ").append(param.get("grossId"));
		}
		if (!StringUtils.isEmpty(param.get("priceLockDatas"))) {
			String[] dates = String.valueOf(param.get("priceLockDatas")).split(" - ");
			String beginStr = dates[0].replace("/", "-") + " 00:00:00";
			String endStr = dates[1].replace("/", "-") + " 23:59:59";
			sqlsb.append(" and priceLockTime BETWEEN '").append(beginStr).append("' AND '").append(endStr).append("'");
		}
		return sqlsb.toString() + " order by created desc";
	}
}