package com.xiakee.domain.utils;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * 物流追踪信息类型
 * @Product: xiakee-domain
 * @Title: LogistNodeEnum.java
 * @Package com.xiakee.domain.utils
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年5月27日 下午4:21:16
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public enum LogistNodeEnum implements Serializable {
	CREATE(1, "create", "用户下单"),//消息type属性不需要加
	PAY(2, "pay","用户支付"),//这个节点发送短信
	ABROADORDER(3, "abroadorder", "您的商品正在备货中，请耐心等待"),
	EXPRESSNO(4, "expressno", "您的商品备货完毕，正在快速送往遐客行海外仓"),
	STORAGE(5, "storage", "您的商品已到达遐客行海外仓，正办理回国手续，等待出库"),//这个节点发送短信
	TERMINAL(6, "terminal", "您的商品已出库，正前往机场等待登机回国"),
	BOARDING(7, "boarding", "您的商品已登机回国，遐客行正全程护送，即将送达国内"),//这个节点发送短信
	CUSTOMS(8, "customs", "您的商品已经抵达中国，正在海关办理通关手续"),
	DOMESTIC(9, "domestic", "您的商品已通关完毕，正在转国内快递"),//这个节点发送短信
	FINISH(10, "finish", "您的商品已成功送达，签收完毕，感谢支持！"),//
	OK(100, "ok", "该订单物流节点已结束");//物流结束节点，根据该节点code判断该订单物流是否继续遍历读取

    private Integer code;
    private String name;
    private String description;

    /**
     * @param description 中文描述
     */
    private LogistNodeEnum(String description) {
        this.description = description;
    }

    /**
     * @param code        数字编码
     * @param description 中文描述
     */
    private LogistNodeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * @param name        英文编码名称
     * @param description 中文描述
     */
    private LogistNodeEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @param code        数字编码
     * @param name        英文编码名称
     * @param description 中文描述
     */
    private LogistNodeEnum(Integer code, String name, String description) {
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
    public static LogistNodeEnum enumValueOf(Integer code) {
        LogistNodeEnum[] values = LogistNodeEnum.values();
        LogistNodeEnum v = null;
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
    public static LogistNodeEnum enumValueOf(String name) {
        LogistNodeEnum[] values = LogistNodeEnum.values();
        LogistNodeEnum v = null;
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
        for (int i = 0; i < LogistNodeEnum.values().length; i++) {
            map.put(LogistNodeEnum.values()[i].toCode(), LogistNodeEnum.values()[i].toDescription());
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
        for (int i = 0; i < LogistNodeEnum.values().length; i++) {
            map.put(LogistNodeEnum.values()[i].toName(), LogistNodeEnum.values()[i].toDescription());
        }
        return map;
    }
}
