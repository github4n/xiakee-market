package com.xiakee.domain.utils;

import java.io.Serializable;
import java.util.TreeMap;

/**
 * 递优物流节点信息
 * @Product: xiakee-domain
 * @Title: UdfexParamTypeEnum.java
 * @Package com.xiakee.domain.utils
 * @Description: 该项目主要为了打通国际物流与后台客服等信息流，满足海淘的快捷服务
 * @Company: 遐客行-进口户外装备特卖
 * @Author 谢坚柏
 * @Email boge@xiakee.com[如发现代码问题，恳请随时反馈]
 * @Date 2015年9月14日 下午4:59:40
 * @Version 1.0
 * @Copyright: Copyright (c) 2015
 */
public enum UdfexParamTypeEnum implements Serializable {
	CI(1, "CI", "收到货物，打印标签"),//storage 5
	SC(2, "SC","已称重"),
	PA(3, "PA","已支付"),
	SM(4, "SM","已预报"),
	CM(5, "CM","已审核"),
	PR(6, "PR","打印面单"),
	PU(7, "PU","拣货完成"),
	SN(8, "SN","扫描确认"),
	PK(9, "PK","已打包"),
	CO(10, "CO","已出库"),//terminal 6
	IC(11, "IC","海关查验"),
	HI(12, "HI","海关扣留"),
	RC(13, "RC","海关放行"),
	OK(14, "OK","已妥投"),
	CL(15, "CL","已取消"),
	PP(16, "PP","打印支付标签"),
	HW(17, "HW","仓库扣货"),
	UL(18, "UL","已起飞"),//boarding 7
	CD(19, "CD","预报关"),
	IS(20, "IS","入监管仓"),//customs 8
	IR(21, "IR","入国内转运仓"),
	TR(22, "TR","已转运"),//domestic 9
	OD(23, "OD","已派送"),
	UC(24, "UC","未预报包裹入库"),
	DL(25, "DL","航班到达"),
	AP(26, "AP","审核通过"),
	AR(27, "AR","审核拒绝"),
	KW(28, "KW","已核重"),
	AL(29, "AL","机场货运站"),
	PI(30, "PI","提供说明"),
	CP(31, "CP","取消打包");
	
    private Integer code;
    private String name;
    private String description;

    /**
     * @param description 中文描述
     */
    private UdfexParamTypeEnum(String description) {
        this.description = description;
    }

    /**
     * @param code        数字编码
     * @param description 中文描述
     */
    private UdfexParamTypeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * @param name        英文编码名称
     * @param description 中文描述
     */
    private UdfexParamTypeEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @param code        数字编码
     * @param name        英文编码名称
     * @param description 中文描述
     */
    private UdfexParamTypeEnum(Integer code, String name, String description) {
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
    public static UdfexParamTypeEnum enumValueOf(Integer code) {
        UdfexParamTypeEnum[] values = UdfexParamTypeEnum.values();
        UdfexParamTypeEnum v = null;
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
    public static UdfexParamTypeEnum enumValueOf(String name) {
        UdfexParamTypeEnum[] values = UdfexParamTypeEnum.values();
        UdfexParamTypeEnum v = null;
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
        for (int i = 0; i < UdfexParamTypeEnum.values().length; i++) {
            map.put(UdfexParamTypeEnum.values()[i].toCode(), UdfexParamTypeEnum.values()[i].toDescription());
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
        for (int i = 0; i < UdfexParamTypeEnum.values().length; i++) {
            map.put(UdfexParamTypeEnum.values()[i].toName(), UdfexParamTypeEnum.values()[i].toDescription());
        }
        return map;
    }
}
