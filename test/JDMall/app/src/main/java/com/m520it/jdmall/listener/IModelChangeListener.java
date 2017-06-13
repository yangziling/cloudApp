package com.m520it.jdmall.listener;

/**
 * @author 杨飞
 * @time 2016/9/1  19:45
 * @desc ${TODD}
 */
public interface IModelChangeListener {
    //告诉view层 数据加载完毕
    //action 区分一个界面可能有多个请求  返回不同的结果.
    public void onModelChanged(int action,Object... values);
}
