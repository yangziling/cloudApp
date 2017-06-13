package com.m520it.jdmall.controller;

import android.content.Context;

import com.m520it.jdmall.listener.IModelChangeListener;

/**
 * @author 杨飞
 * @time 2016/9/1  13:42
 * @desc 控制器的基类.
 */
public abstract  class BaseController {
    protected IModelChangeListener mListener;
    protected Context mContext;

    public BaseController(Context c) {
        mContext = c;
    }

    public void setModeChangeListener(IModelChangeListener listener){
        mListener = listener;
    }


    /**
     * 发送异步数据时子类handleMessage(action,values) 来处理
     */
    public void sendAsyncMessage(final int action, final Object... values){
        new Thread(){
            public void run(){
                //针对每个请求   父类创建不同的线程
                handleMessage(action,values);
            }
        }.start();
    }
    /**
     * 处理获取数据的操作  获取的数据不一样.具体的处理留给子类去实现.
     * 第一个参数  识别在当前页面上 具体的是哪一个请求
     * 后面的不定参数   请求时的参数的个数无法确定
     */
    protected abstract void handleMessage(int action,Object... values);


    /**
     * 发送同步数据
     */
    protected void sendMessage(final int action, final Object... values){
        handleMessage(action,values);
    }


}
