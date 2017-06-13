package com.m520it.jdmall.bean;

/**
 * @author 杨飞
 * @time 2016/9/1  22:20
 * @desc ${TODD}
 */
public class RResultRegister {
    private String result;
    private String errorMsg;
    private boolean sucess;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }
}
