package com.xiakee.service.utils.php;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class PhpSerializerUtil {
	private static Logger log = Logger.getLogger(PhpSerializerUtil.class);

	public static String getProductAttr(String attributes) {
		String attrib = null;
		if (StringUtils.isNotBlank(attributes)) {
			try {
				Map protMap = (Map) PHPSerializer.unserialize(
						attributes.getBytes(), Map.class);
				Map labelMap = (Map) protMap.get("product_attr");
				Iterator iter = labelMap.entrySet().iterator();
				attrib = "[";
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					Map val = (Map) entry.getValue();

					attrib += val.get("value") + ",";
				}
				attrib = attrib.substring(0,attrib.length() - 1);
				attrib += "]";
			} catch (Exception e) {
				log.error("商城商品属性php数据反序列化失败", e);
			}
		}
		return attrib;
	}
}
