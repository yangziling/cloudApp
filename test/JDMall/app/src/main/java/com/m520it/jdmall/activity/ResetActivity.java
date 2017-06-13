package com.m520it.jdmall.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.m520it.jdmall.R;
import com.m520it.jdmall.bean.RResult;
import com.m520it.jdmall.cons.IdiyMessage;
import com.m520it.jdmall.controller.ResetController;
import com.m520it.jdmall.listener.IModelChangeListener;

/**
 * @author 杨飞
 * @time 2016/9/2  0:55
 * @desc ${TODD}
 */
public class ResetActivity extends BaseActivity implements IModelChangeListener {

    private EditText mTitleTv;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(android.os.Message msg) {
            if(msg.what == IdiyMessage.RESET_ACTION_RESULT) {
                handleResetResult(msg);

            }
        }
    };
    private EditText mNameEt;

    //   private ResetController mController;//controller是网络访问的执行者
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        initController();
        initView();
    }

    private void initView() {
        mNameEt = (EditText) findViewById(R.id.username_et);
    }

    //初始化 控制器
    private void initController() {
        mController = new ResetController(this);
        mController.setModeChangeListener(this);
    }

    public void resetClick(View view){
        String name = mNameEt.getText().toString().trim();
        if(TextUtils.isEmpty(name)) {
            tip("账号不能为空");
            return ;
        }
        mController.sendAsyncMessage(IdiyMessage.RESET_ACTION,name);
    }

    @Override
    public void onModelChanged(int action,Object... values) {
        mHandler.obtainMessage(action,values[0]).sendToTarget();
    }

    private void handleResetResult(android.os.Message msg) {
        RResult resultBean = (RResult) msg.obj;
        if(resultBean.isSuccess()) {
            tip("密码已重置为123456");
            finish();
        }else{
            tip("重置失败"+resultBean.getErrorMsg());
        }
    }
}
