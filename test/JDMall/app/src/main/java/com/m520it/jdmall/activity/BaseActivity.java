package com.m520it.jdmall.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.Toast;

import com.m520it.jdmall.controller.BaseController;

/**
 * @author 杨飞
 * @time 2016/9/1  13:34
 * @desc ${TODD}
 */
public class BaseActivity extends FragmentActivity {
    protected BaseController mController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
    protected void tip(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
