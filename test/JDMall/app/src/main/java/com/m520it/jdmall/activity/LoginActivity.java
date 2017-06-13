package com.m520it.jdmall.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.m520it.jdmall.R;
import com.m520it.jdmall.appliacation.JDAppliacation;
import com.m520it.jdmall.bean.RLogin;
import com.m520it.jdmall.bean.RResult;
import com.m520it.jdmall.bean.UserBnean;
import com.m520it.jdmall.cons.IdiyMessage;
import com.m520it.jdmall.controller.LoginController;
import com.m520it.jdmall.listener.IModelChangeListener;
import com.m520it.jdmall.util.ActivityUtil;

import java.lang.ref.WeakReference;

/**
 * @author 杨飞
 * @time 2016/9/1  12:52
 * @desc ${TODD}
 */
public class LoginActivity extends BaseActivity implements IModelChangeListener {

    public EditText mName_et;
    public EditText mPwd_et;
    public String mName;
    public String mPwd;

    //private LoginController mController;//controller是网络访问的执行者

    private MyHandler mHandler = new MyHandler(this);

    public static class MyHandler extends Handler {
        private WeakReference<LoginActivity> mReference;

        public MyHandler(LoginActivity ac) {
            mReference = new WeakReference<LoginActivity>(ac);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IdiyMessage.LOGIN_ACTION_RESULT:
                    handleLoginResult(msg);
                    break;
                case IdiyMessage.SAVE_USER_ACTION_RESULT:
                    handleSaveUserInfo((Boolean) msg.obj);
                    break;
                case IdiyMessage.QUERY_USER_ACTION_RESULT:
                    handleQueryUserInfo((UserBnean) msg.obj);
                    break;
            }
        }

        //查询 用户信息 如果有  进行回显 将查询到的数据设置到控件上.
        private void handleQueryUserInfo(UserBnean bean) {
            if (bean != null) {
                mReference.get().mName_et.setText(bean.name);
                mReference.get().mPwd_et.setText(bean.pwd);
            }
        }
        private void handleSaveUserInfo(Boolean ifsaved) {
            if (ifsaved) {
                ActivityUtil.start(mReference.get(), MainActivity.class);
            } else {
                mReference.get().tip("保存用户异常信息");
            }
        }

        private void handleLoginResult(Message msg) {
            RResult result = (RResult) msg.obj;
            if (result.isSuccess()) {
                //登陆成功
                if (mReference != null && mReference.get() != null) {
                    RLogin login = JSON.parseObject(result.getResult(),
                            RLogin.class);
                    //1.将返回的Json数据解析成Rlogin对象.
                    ((JDAppliacation) (mReference.get().getApplication())).mUserInfo = login;
                    //2.将账号和密码保存到数据库中
                    mReference.get().mController.sendAsyncMessage(IdiyMessage.SAVE_USER_ACTION,
                            mReference.get().mName, mReference.get().mPwd);
                }
            } else {
                mReference.get().tip("登录失败:" + result.getErrorMsg());
            }

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //现有Controller 在进行ui界面设置.
        initController();
        initView();
        //发送异步请求 查询数据库中是否有数据
        mController.sendAsyncMessage(IdiyMessage.QUERY_USER_ACTION, 0);
    }

    private void initController() {
        mController = new LoginController(this);
        mController.setModeChangeListener(this);
    }

    //初始化控件
    private void initView() {
        mName_et = (EditText) findViewById(R.id.name_et);
        mPwd_et = (EditText) findViewById(R.id.pwd_et);

    }

    //登录
    public void loginClick(View view) {
        //获取用户得到的文本 进行登录
        mName = mName_et.getText().toString().trim();
        mPwd = mPwd_et.getText().toString().trim();
        //判断获得的值
        if (TextUtils.isEmpty(mName) || TextUtils.isEmpty(mPwd)) {
            tip("密码或登录名为空");
            return;
        }
        mController.sendAsyncMessage(IdiyMessage.LOGIN_ACTION, mName, mPwd);
    }

    //新用户点击事件
    public void registClick(View view) {
        //点击之后跳转到注册页面新的activity
        ActivityUtil.startWithOutFinish(this, RegisterActivity.class);
    }

    //密码重置的操作
    public void resetPwdClick(View view) {
        //点击之后跳转到登录界面 密码默认设置为6
        ActivityUtil.startWithOutFinish(this, ResetActivity.class);
    }

    //该代码在子线  @Override
    public void onModelChanged(int action, Object... values) {
        //返回的action,有多个值,
        switch (action) {
            case IdiyMessage.LOGIN_ACTION_RESULT:
                mHandler.obtainMessage(IdiyMessage.LOGIN_ACTION_RESULT, values[0]).sendToTarget();
                break;
            case IdiyMessage.SAVE_USER_ACTION_RESULT:
                mHandler.obtainMessage(IdiyMessage.SAVE_USER_ACTION_RESULT, values[0]).sendToTarget();
                break;
            case IdiyMessage.QUERY_USER_ACTION_RESULT:
                mHandler.obtainMessage(IdiyMessage.QUERY_USER_ACTION_RESULT, values[0]).sendToTarget();
                break;

        }
    }
}


