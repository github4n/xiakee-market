package com.xiakee.api.enums;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * 短信发送的模板，主要为了规定对外接口所用
 * @Product: xiakee-api
 * @Title: SmscTemplateEnum.java
 * @Package com.xiakee.api.enums
 * @Description: 遐客行后台管理系统
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com
 * @Date 2015年5月11日 下午9:56:18
 * @Version 1.0
 * @Copyright: Copyright (c) 2014
 */
public enum SmscTemplateEnum implements Serializable {
    TEXT(0, "text", "文本类信息"),//消息type属性不需要加
    FEEDBACK(1, "feedback", "聊天回执信息"),
    MUSTUPDATE(2, "mustupdate", "强制应用更新"),
    UPDATE(3, "update", "应用更新"),
    HTML5(4, "html5", "html5下载"),
    IMG(5, "img", "图片"),
    SYSTEM(6,"system","平台消息");

    private Integer code;
    private String name;
    private String description;

    /**
     * @param description 中文描述
     */
    private SmscTemplateEnum(String description) {
        this.description = description;
    }

    /**
     * @param code        数字编码
     * @param description 中文描述
     */
    private SmscTemplateEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * @param name        英文编码名称
     * @param description 中文描述
     */
    private SmscTemplateEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @param code        数字编码
     * @param name        英文编码名称
     * @param description 中文描述
     */
    private SmscTemplateEnum(Integer code, String name, String description) {
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
    public static SmscTemplateEnum enumValueOf(Integer code) {
        SmscTemplateEnum[] values = SmscTemplateEnum.values();
        SmscTemplateEnum v = null;
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
    public static SmscTemplateEnum enumValueOf(String name) {
        SmscTemplateEnum[] values = SmscTemplateEnum.values();
        SmscTemplateEnum v = null;
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
        for (int i = 0; i < SmscTemplateEnum.values().length; i++) {
            map.put(SmscTemplateEnum.values()[i].toCode(), SmscTemplateEnum.values()[i].toDescription());
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
        for (int i = 0; i < SmscTemplateEnum.values().length; i++) {
            map.put(SmscTemplateEnum.values()[i].toName(), SmscTemplateEnum.values()[i].toDescription());
        }
        return map;
    }
}
