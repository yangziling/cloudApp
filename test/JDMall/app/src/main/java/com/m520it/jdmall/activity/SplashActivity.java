package com.m520it.jdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.m520it.jdmall.R;

/**
 * @author 杨飞
 * @time 2016/9/1  11:30
 * @desc ${TODD}
 */
public class SplashActivity extends BaseActivity {


    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            //执行页面跳转
            Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initAlphaAni();
        startApp();

    }

    //进入登录页面
    private void startApp() {
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(2000);
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    //设置动画效果
    private void initAlphaAni() {
        AlphaAnimation alpha = new AlphaAnimation(0.2f,1.0f);
        alpha.setDuration(2000);
        alpha.setFillAfter(true);
        ImageView logoView = (ImageView) findViewById(R.id.logo_iv);
        logoView.startAnimation(alpha);
    }
}
