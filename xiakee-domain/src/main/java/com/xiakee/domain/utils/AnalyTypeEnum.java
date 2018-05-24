package com.xiakee.domain.utils;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * 数据分析类型
 * @Product: xiakee-domain
 * @Title: TransferTypeEnum.java
 * @Package com.xiakee.domain.utils
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年8月25日 下午9:34:00
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public enum AnalyTypeEnum implements Serializable {
	ORDER_HOUR(1, "hour","每小时区间订单统计"),
	ORDER_DATE(2, "date", "每天订单数据统计"),
	ORDER_PROVINCE(3, "province","省份订单数据统计"),
	ORDER_REFER(4,"referrer","订单访问渠道统计"),
	SKU_SUM(5,"sku","sku上新总数统计"),
	ORDER_MONTH(6,"month","订单月度总量统计"),
	COST_MONTH(7,"cost","订单月度金额统计");

    private Integer code;
    private String name;
    private String description;

    /**
     * @param description 中文描述
     */
    private AnalyTypeEnum(String description) {
        this.description = description;
    }

    /**
     * @param code        数字编码
     * @param description 中文描述
     */
    private AnalyTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * @param name        英文编码名称
     * @param description 中文描述
     */
    private AnalyTypeEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @param code        数字编码
     * @param name        英文编码名称
     * @param description 中文描述
     */
    private AnalyTypeEnum(Integer code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }


    /**
     * 获取枚举类型数值编码
     */
    public Integer toCode() {
        return this.code == null ? this.ordinal() : this.code;
    }

    /**
     * 获取枚举类型英文编码名称
     */
    public String toName() {
        return this.name == null ? this.name() : this.name;
    }

    /**
     * 获取枚举类型中文描述
     */
    public String toDescription() {
        return this.description;
    }

    /**
     * 获取枚举类型中文描述
     */
    public String toString() {
        return this.description;
    }

    /**
     * 按数值获取对应的枚举类型
     *
     * @param code 数值
     * @return 枚举类型
     */
    public static AnalyTypeEnum enumValueOf(Integer code) {
        AnalyTypeEnum[] values = AnalyTypeEnum.values();
        AnalyTypeEnum v = null;
        for (int i = 0; i < values.length; i++) {
            if (values[i].toCode().equals(code)) {
                v = values[i];
                break;
            }
        }
        return v;
    }

    /**
     * 按英文编码获取对应的枚举类型
     *
     * @param name 英文编码
     * @return 枚举类型
     */
    public static AnalyTypeEnum enumValueOf(String name) {
        AnalyTypeEnum[] values = AnalyTypeEnum.values();
        AnalyTypeEnum v = null;
        for (int i = 0; i < values.length; i++) {
            if (values[i].toName().equalsIgnoreCase(name)) {
                v = values[i];
                break;
            }
        }
        return v;
    }

    /**
     * 获取枚举类型的所有<数字编码,中文描述>对
     *
     * @return
     */
    public static TreeMap<Integer, String> toCodeDescriptionMap() {
        TreeMap<Integer, String> map = new TreeMap<Integer, String>();
        for (int i = 0; i < AnalyTypeEnum.values().length; i++) {
            map.put(AnalyTypeEnum.values()[i].toCode(), AnalyTypeEnum.values()[i].toDescription());
        }
        return map;
    }

    /**
     * 获取枚举类型的所有<英文编码名称,中文描述>对
     *
     * @return
     */
    public static TreeMap<String, String> toNameDescriptionMap() {
        TreeMap<String, String> map = new TreeMap<String, String>();
        for (int i = 0; i < AnalyTypeEnum.values().length; i++) {
            map.put(AnalyTypeEnum.values()[i].toName(), AnalyTypeEnum.values()[i].toDescription());
        }
        return map;
    }
}
