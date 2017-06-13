package com.m520it.jdmall.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * @author 杨飞
 * @time 2016/9/1  21:42
 * @desc ${TODD}
 */
public class ActivityUtil  {
    public static void start(Context c,Class clazz){
        Intent intent = new Intent(c,clazz);
        //以参数的形式传过来的上下文.
        c.startActivity(intent);
        //需要关闭Activity  页面跳转之后 关闭哦当前的activity
        ((Activity)c).finish();
    }
    public static void startWithOutFinish(Context c,Class clazz){
        Intent intent = new Intent(c,clazz);
        //以参数的形式传过来的上下文.
        c.startActivity(intent);
    }
}
