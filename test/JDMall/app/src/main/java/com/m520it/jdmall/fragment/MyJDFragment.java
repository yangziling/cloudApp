package com.m520it.jdmall.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m520it.jdmall.R;
import com.m520it.jdmall.activity.LoginActivity;
import com.m520it.jdmall.appliacation.JDAppliacation;
import com.m520it.jdmall.bean.RLogin;
import com.m520it.jdmall.cons.IdiyMessage;
import com.m520it.jdmall.controller.MyJDController;
import com.m520it.jdmall.listener.IModelChangeListener;
import com.m520it.jdmall.util.ActivityUtil;

/**
 * @author 杨飞
 * @time 2016/9/3  14:50
 * @desc ${TODD}
 */
public class MyJDFragment extends BaseFragment implements View.OnClickListener,
        IModelChangeListener {

    private TextView mNameTv;
    private TextView mUserLevelTv;
    private TextView mWaitPayTv;
    private TextView mWaitReceiveTv;
    private MyJDController mMyJDController;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IdiyMessage.DELETE_USER_ACTION_RESULT:
handleDeleteUserResult((Boolean)msg.obj);
                break;
            }
        }
    };

    private void handleDeleteUserResult(Boolean obj) {
        if(obj) {
            // 2.跳转到LoginActivity中 直接进行页面跳转就可以
            ActivityUtil.start(getActivity(), LoginActivity.class);
        }else{
            tip("退出失败");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myjd, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initController();


    }

    private void initController() {
        mMyJDController = new MyJDController(getActivity());
        mMyJDController.setModeChangeListener(this);
    }

    //初始化ui界面
    private void initView() {
        mNameTv = (TextView) getActivity().findViewById(R.id.user_name_tv);
        mUserLevelTv = (TextView) getActivity().findViewById(R.id.user_level_tv);
        mWaitPayTv = (TextView) getActivity().findViewById(R.id.wait_pay_tv);
        mWaitReceiveTv = (TextView) getActivity().findViewById(R.id.wait_receive_tv);
        //在京东这个fragment中拿到用户对象
        RLogin mUserInfo = ((JDAppliacation) (getActivity().getApplication())).mUserInfo;
        //有了对象之后给这个fragment中的额相应的 控件设置
        mNameTv.setText(mUserInfo.getUserName());
        //等级的话 是int类型  有多个 等级 swi语句
        initUserLevelTv(mUserInfo);
        mWaitPayTv.setText(mUserInfo.getWaitPayCount() + "");
        mWaitReceiveTv.setText(mUserInfo.getWaitReceiveCount() + "");

        //退出按钮为什么不能给点击事件 而是通过id去做. 因为点击事件 都会跳到activity中执行.这里是fragment只能
        //通过id去操作
        getActivity().findViewById(R.id.logout_btn).setOnClickListener(this);


    }

    private void initUserLevelTv(RLogin mUserInfo) {
        switch (mUserInfo.getUserLevel()) {
            case 2:
                mUserLevelTv.setText("铜牌会员");
                break;
            case 3:
                mUserLevelTv.setText("银牌会员");
                break;
            case 4:
                mUserLevelTv.setText("金牌会员");
                break;
            case 5:
                mUserLevelTv.setText("钻石会员");
                break;
            case 1:
            default:
                mUserLevelTv.setText("注册会员");
                break;
        }
    }

    //退出登录
    @Override
    public void onClick(View v) {
        //1.清除数据库
        mMyJDController.sendAsyncMessage(IdiyMessage.DELETE_USER_ACTION, 0);

        //2.跳转到LoginActivity中

    }

    @Override
    public void onModelChanged(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();
        //发送消息:   在最上面的handler中处理消息
    }
}
