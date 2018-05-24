package com.xiakee.domain.utils;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * 转运公司代码
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
public enum TransferTypeEnum implements Serializable {
	OTHER(0, "other","其它"),
	BAIWEI(1, "baiwei", "柏威"),//消息type属性不需要加
	UDFEX(2, "udfex","递优"),
	UDFEXJP(3, "udfexjp","递优");

    private Integer code;
    private String name;
    private String description;

    /**
     * @param description 中文描述
     */
    private TransferTypeEnum(String description) {
        this.description = description;
    }

    /**
     * @param code        数字编码
     * @param description 中文描述
     */
    private TransferTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * @param name        英文编码名称
     * @param description 中文描述
     */
    private TransferTypeEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @param code        数字编码
     * @param name        英文编码名称
     * @param description 中文描述
     */
    private TransferTypeEnum(Integer code, String name, String description) {
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
    public static TransferTypeEnum enumValueOf(Integer code) {
        TransferTypeEnum[] values = TransferTypeEnum.values();
        TransferTypeEnum v = null;
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
    public static TransferTypeEnum enumValueOf(String name) {
        TransferTypeEnum[] values = TransferTypeEnum.values();
        TransferTypeEnum v = null;
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
        for (int i = 0; i < TransferTypeEnum.values().length; i++) {
            map.put(TransferTypeEnum.values()[i].toCode(), TransferTypeEnum.values()[i].toDescription());
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
        for (int i = 0; i < TransferTypeEnum.values().length; i++) {
            map.put(TransferTypeEnum.values()[i].toName(), TransferTypeEnum.values()[i].toDescription());
        }
        return map;
    }
}
