package com.m520it.jdmall.bean;

/**
 * @author 杨飞
 * @time 2016/9/6  9:44
 * @desc ${TODD}
 */
public class SubCategoryBean {
    private long id;
    private String name;
    //FastJson 解析的时候不能嵌套  但是构建的时候是可以嵌套
    private String thirdCategory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThirdCategory() {
        return thirdCategory;
    }

    public void setThirdCategory(String thirdCategory) {
        this.thirdCategory = thirdCategory;
    }
}
