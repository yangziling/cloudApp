package com.m520it.jdmall.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.m520it.jdmall.bean.RBrand;
import com.m520it.jdmall.bean.RResult;
import com.m520it.jdmall.cons.IdiyMessage;
import com.m520it.jdmall.cons.NetWorkCons;
import com.m520it.jdmall.util.NetworkUtil;

import java.util.ArrayList;

/**
 * @author 杨飞
 * @time 2016/9/6  15:35
 * @desc ${TODD}
 */
public class ProductListController extends BaseController{
    public ProductListController(Context c) {
        super(c);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        //通过不同的action进行访问
        switch (action) {
            case IdiyMessage.BRAND_ACTION://对结果进行处理
                mListener.onModelChanged(IdiyMessage.BRAND_ACTION_RESULT,
                       loadBrand((Long)values[0]));
                break;
        }
    }
    //加载 RBrand 集合
    private ArrayList<RBrand> loadBrand(long topCategoryId) {
        String jsonStr = NetworkUtil.doGet(NetWorkCons.BASE_URL
                + "?categoryId=" + topCategoryId);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        //阿里提供的fastjson 不支持 嵌套解析  所以先解析出外层 在进行判断
        if(resultBean.isSuccess()) {
            return ( ArrayList<RBrand>)JSONArray.parseArray(resultBean.getResult(),RBrand.class);
        }
        return new ArrayList<RBrand>();
    }
}
