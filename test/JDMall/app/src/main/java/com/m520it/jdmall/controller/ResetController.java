package com.m520it.jdmall.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.m520it.jdmall.bean.RResult;
import com.m520it.jdmall.cons.IdiyMessage;
import com.m520it.jdmall.cons.NetWorkCons;
import com.m520it.jdmall.util.NetworkUtil;

import java.util.HashMap;

/**
 * @author 杨飞
 * @time 2016/9/2  1:08
 * @desc 实现具体的网络请求:
 */
public class ResetController extends BaseController {

    public ResetController(Context c) {
        super(c);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.RESET_ACTION:
                //具体的方法携带数据 进行网络访问
                mListener.onModelChanged(IdiyMessage.RESET_ACTION_RESULT,
                        reset((String)values[0]));
                break;
        }
    }

    private RResult reset(String name) {
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("username",name);
        String jsonStr = NetworkUtil.doPost(NetWorkCons.RESET_URL, params);
        return JSON.parseObject(jsonStr,RResult.class);
    }

}
