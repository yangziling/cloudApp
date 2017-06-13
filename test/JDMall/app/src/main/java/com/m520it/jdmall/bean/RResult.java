package com.m520it.jdmall.bean;

/**
 * @author 杨飞
 * @time 2016/9/1  20:01
 * @desc ${TODD}
 */
public class RResult {
    private String errorMsg;
    private String result;
    private boolean success;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
