package com.m520it.jdmall.bean;

/**
 * @author 杨飞
 * @time 2016/9/5  12:52
 * @desc ${TODD}
 */
public class RSecKill {
    private long productId;
    private String allPrice;//原价
    private String pointPrice;//秒杀价格
    private String iconUrl;//商品图片路径
    private int timeLeft;//秒杀价剩余时间
    private int type;//1抢年货，2超值，3热卖

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }

    public String getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(String pointPrice) {
        this.pointPrice = pointPrice;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
