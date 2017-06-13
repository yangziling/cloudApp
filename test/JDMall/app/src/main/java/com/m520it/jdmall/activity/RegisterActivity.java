package com.m520it.jdmall.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.m520it.jdmall.R;
import com.m520it.jdmall.bean.RResult;
import com.m520it.jdmall.cons.IdiyMessage;
import com.m520it.jdmall.controller.RegisterController;
import com.m520it.jdmall.listener.IModelChangeListener;

/**
 * @author 杨飞
 * @time 2016/9/1  21:34
 * @desc ${TODD}
 */
public class RegisterActivity extends BaseActivity implements IModelChangeListener {


    private EditText mUsernameEt;
    private EditText mPwdEt;
    private EditText mSurepwdEt;
    private Handler mHandler= new Handler(){

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case  IdiyMessage.REGIST_ACTION_RESULT:
                    handleRegistResult(msg);
                    break;
            }
            }
        };

    private void handleRegistResult(Message msg) {
        RResult result = (RResult) msg.obj;
        if(result.isSuccess()) {
            //跳转到登录界面//跳转到登录
            finish();

        }else{
            //提示
            tip("注册失败:"+result.getErrorMsg());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        mUsernameEt = (EditText) findViewById(R.id.username_et);
        mPwdEt = (EditText) findViewById(R.id.pwd_et);
        mSurepwdEt = (EditText) findViewById(R.id.surepwd_et);

        mController=new RegisterController(this);
        mController.setModeChangeListener(this);
    }


    //填写完信息 下一步操作
    public void registClick(View view){
        String username = mUsernameEt.getText().toString().trim();
        String pwd= mPwdEt.getText().toString().trim();
        String surePwd = mSurepwdEt.getText().toString().trim();

        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(pwd)||TextUtils.isEmpty(surePwd)) {
            tip("请填写正确的信息");
             return;
        }
        if(!pwd.equals(surePwd)) {
            tip("两次密码不一致");
            return;
        }
        //注册操作:
        mController.sendAsyncMessage(IdiyMessage.REGIST_ACTION, username,pwd);
    }

    @Override
    public void onModelChanged(int action, Object... values) {
        mHandler.obtainMessage(action,values[0]).sendToTarget();
    }
}
