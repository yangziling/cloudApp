package com.m520it.jdmall.bean;

/**
 * @author 杨飞
 * @time 2016/9/5  17:22
 * @desc ${TODD}
 */
public class RRecommendBean {
    private long productId;
    private String name;
    private String iconUrl;
    private double price;

    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
