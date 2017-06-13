package com.m520it.jdmall.bean;

/**
 * @author 杨飞
 * @time 2016/9/4  14:01
 * @desc ${TODD}
 */
public class AdBanner {
    private long id;
    private int type;//1跳转到网页，2跳转到商品详情，3跳转到分类去
    private String adUrl;
    private String webUrl;
    private int adKind;//1为导航banner，2为广告banner

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public int getAdKind() {
        return adKind;
    }

    public void setAdKind(int adKind) {
        this.adKind = adKind;
    }
}
