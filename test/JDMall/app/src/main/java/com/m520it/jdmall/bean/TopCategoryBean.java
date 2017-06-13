package com.m520it.jdmall.bean;

/**
 * @author 杨飞
 * @time 2016/9/6  0:54
 * @desc ${TODD}
 */
public class TopCategoryBean {

    private long id;
    private String bannerUrl;//右边的图片地址
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
