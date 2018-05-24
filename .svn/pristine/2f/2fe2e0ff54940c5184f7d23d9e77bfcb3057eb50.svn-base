package com.xiakee.domain.utils;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * 发送短信的类型，即公众号验证，物流用户发送
 * @Product: xiakee-service
 * @Title: SmscContentTypeEnum.java
 * @Package com.xiakee.service.utils
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年5月25日 下午3:53:08
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public enum SmscContentTypeEnum implements Serializable {
	PUBLICNUM(0, "publicNum", "公众号验证短信"),//消息type属性不需要加
	LOGISTONE(1, "logistOne","订单已支付成功，我们将尽快发货，感谢您的支持"),
	LOGISTTWO(2, "logistTwo", "已到达遐客行海外仓，正等待出库，请耐心等待"),
	LOGISTTHRESS(3, "logistThress", "已登机回国，即将送达国内，敬请等待"),
	LOGISTFOUR(4, "logistFour", "已通关完毕，正在转国内快递，祝您购物愉快");

    private Integer code;
    private String name;
    private String description;

    /**
     * @param description 中文描述
     */
    private SmscContentTypeEnum(String description) {
        this.description = description;
    }

    /**
     * @param code        数字编码
     * @param description 中文描述
     */
    private SmscContentTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * @param name        英文编码名称
     * @param description 中文描述
     */
    private SmscContentTypeEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @param code        数字编码
     * @param name        英文编码名称
     * @param description 中文描述
     */
    private SmscContentTypeEnum(Integer code, String name, String description) {
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
    public static SmscContentTypeEnum enumValueOf(Integer code) {
        SmscContentTypeEnum[] values = SmscContentTypeEnum.values();
        SmscContentTypeEnum v = null;
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
    public static SmscContentTypeEnum enumValueOf(String name) {
        SmscContentTypeEnum[] values = SmscContentTypeEnum.values();
        SmscContentTypeEnum v = null;
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
        for (int i = 0; i < SmscContentTypeEnum.values().length; i++) {
            map.put(SmscContentTypeEnum.values()[i].toCode(), SmscContentTypeEnum.values()[i].toDescription());
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
        for (int i = 0; i < SmscContentTypeEnum.values().length; i++) {
            map.put(SmscContentTypeEnum.values()[i].toName(), SmscContentTypeEnum.values()[i].toDescription());
        }
        return map;
    }
}
