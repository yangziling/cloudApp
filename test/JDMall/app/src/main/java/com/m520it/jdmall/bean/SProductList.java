package com.m520it.jdmall.bean;

import com.m520it.jdmall.listener.IProductsSortPopListener;

/**
 * @author 杨飞
 * @time 2016/9/6  15:10
 * @desc ${TODD}
 */
public class SProductList {
    //将访问的参数 封装到一个对象中
    public static final int SALE=1;
    public static final int PRICE_UP2DOWN=2;
    public static final int PRICE_DOWN2UP=3;

    private long categoryId;//分类id
    private int filterType= IProductsSortPopListener.ALL_SORT;//1-综合 2-新品 3-评价
    private int sortType;//0-默认 1-销量 2-价格高到低 3-价格低到高
    //0-代表无选择 1代表京东配送 2-代表货到付款 4-代表仅看有货 3代表条件1+2 5代表条件1+4 6代表条件2+4
    private int deliverChoose;
    private double minPrice;
    private double maxPrice;
    private int brandId;

    public static int getSALE() {
        return SALE;
    }

    public static int getPriceUp2down() {
        return PRICE_UP2DOWN;
    }

    public static int getPriceDown2up() {
        return PRICE_DOWN2UP;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public int getFilterType() {
        return filterType;
    }

    public void setFilterType(int filterType) {
        this.filterType = filterType;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public int getDeliverChoose() {
        return deliverChoose;
    }

    public void setDeliverChoose(int deliverChoose) {
        this.deliverChoose = deliverChoose;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    @Override
    public String toString() {
        return "SProductList{" +
                "categoryId=" + categoryId +
                ", filterType=" + filterType +
                ", sortType=" + sortType +
                ", deliverChoose=" + deliverChoose +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", brandId=" + brandId +
                '}';
    }
}
