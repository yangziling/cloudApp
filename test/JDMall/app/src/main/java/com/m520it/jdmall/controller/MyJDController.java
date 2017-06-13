package com.m520it.jdmall.controller;

import android.content.Context;

import com.m520it.jdmall.cons.IdiyMessage;

/**
 * @author 杨飞
 * @time 2016/9/3  14:46
 * @desc ${TODD}
 */
public class MyJDController extends UserController {
    public MyJDController(Context c) {
        super(c);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.DELETE_USER_ACTION:

                mListener.onModelChanged(IdiyMessage.DELETE_USER_ACTION_RESULT,
                        deleteUser());
                break;
        }
    }

    private boolean deleteUser() {
        return mDao.cleanUserTable();
    }
}
