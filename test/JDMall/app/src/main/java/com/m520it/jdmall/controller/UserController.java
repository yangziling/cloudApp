package com.m520it.jdmall.controller;

import android.content.Context;

import com.m520it.jdmall.dao.UserDao;

/**
 * @author 杨飞
 * @time 2016/9/3  12:00
 * @desc ${TODD}
 */
public abstract class UserController extends BaseController {
    protected UserDao mDao;

    public UserController(Context c) {
        super(c);
        mDao = new UserDao(c);
    }
}
