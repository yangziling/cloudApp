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
 * @time 2016/9/1  22:14
 * @desc ${TODD}
 */
public class RegisterController extends BaseController {

    public RegisterController(Context c) {
        super(c);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        //注册的下一步
        switch (action) {
            case IdiyMessage.REGIST_ACTION:
                RResult result = regist((String) values[0], (String) values[1]);
                mListener.onModelChanged(IdiyMessage.REGIST_ACTION_RESULT, result);
                break;
        }
    }

    //网络请求的 方法
    private RResult regist(String name, String pwd) {
        //1.获取Url
        //做一个Post请求
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", name);
        params.put("pwd", pwd);
        String jsonStr = NetworkUtil.doPost(NetWorkCons.REGISTER_URL, params);
        //3.解析Json
        return JSON.parseObject(jsonStr, RResult.class);
    }
}
