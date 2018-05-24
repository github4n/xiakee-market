package com.xiakee.domain.ecgoods;

public class XiakeeHaul {
    /** 运单号 */
    private Long haulId;

    /** 订单号 */
    private Long orderId;

    /** 订单明细ID */
    private Integer itemId;

    /** 下单时间 */
    private Integer createtime;

    public Long getHaulId() {
        return haulId;
    }

    public void setHaulId(Long haulId) {
        this.haulId = haulId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }
}