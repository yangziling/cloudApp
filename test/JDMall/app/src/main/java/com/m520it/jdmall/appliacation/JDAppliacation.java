package com.m520it.jdmall.appliacation;

import android.app.Application;

import com.m520it.jdmall.bean.RLogin;

/**
 * @author 杨飞
 * @time 2016/9/3  11:24
 * @desc 保存用信息  方便APP来取  启动app 系统会首先启动Application
 */
public class JDAppliacation extends Application {
    public RLogin mUserInfo;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
