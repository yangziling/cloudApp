package com.m520it.jdmall.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.m520it.jdmall.bean.RResult;
import com.m520it.jdmall.bean.UserBnean;
import com.m520it.jdmall.cons.IdiyMessage;
import com.m520it.jdmall.cons.NetWorkCons;
import com.m520it.jdmall.util.AESUtils;
import com.m520it.jdmall.util.NetworkUtil;

import java.util.HashMap;

/**
 * @author 杨飞
 * @time 2016/9/1  13:54
 * @desc ${TODD}
 */
public class LoginController extends UserController {

    public LoginController(Context c) {
        super(c);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.LOGIN_ACTION:
                RResult login = login((String) values[0], (String) values[1]);
                //告诉界面 数据已经改变  接下来压迫修改界面的信息
                mListener.onModelChanged(IdiyMessage.LOGIN_ACTION_RESULT, login);
                break;
            case IdiyMessage.SAVE_USER_ACTION:
                boolean saveUser2DB = saveUser2DB((String) values[0],
                        (String) values[1]);
                mListener.onModelChanged(IdiyMessage.SAVE_USER_ACTION_RESULT,
                        saveUser2DB);
                break;
            case IdiyMessage.QUERY_USER_ACTION:
                mListener.onModelChanged(IdiyMessage.QUERY_USER_ACTION_RESULT,
                        queryUserFromDB());
                break;
        }
    }

    private boolean saveUser2DB(String name, String pwd) {
        try {
            name = AESUtils.encrypt(name);
            pwd = AESUtils.encrypt(pwd);
            mDao.cleanUserTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDao.saveUser(name, pwd);
    }

    private UserBnean queryUserFromDB() {
        UserBnean bnean = mDao.queryUserTable();
        if (bnean != null) {
            try {
                return new UserBnean(AESUtils.decrypt(bnean.name),
                        AESUtils.decrypt(bnean.pwd));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    //偶一个登录请求
    private RResult login(String name, String psw) {
        String urlPath = NetWorkCons.LOGIN_URL;
        //这里hashMap没有用父类进行表示
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", name);
        params.put("pwd", psw);
        String jsonStr = NetworkUtil.doPost(urlPath, params);
        return JSON.parseObject(jsonStr, RResult.class);
    }
}
