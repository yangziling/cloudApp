package com.m520it.jdmall.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.m520it.jdmall.R;
import com.m520it.jdmall.fragment.CategoryFragment;
import com.m520it.jdmall.fragment.HomeFragment;
import com.m520it.jdmall.fragment.MyJDFragment;
import com.m520it.jdmall.fragment.ShopcarFragment;
import com.m520it.jdmall.listener.IBottomBarChangeListener;
import com.m520it.jdmall.ui.BottomBar;

public class MainActivity extends BaseActivity implements IBottomBarChangeListener {
    private FrameLayout mTopBar;
    private BottomBar mBottomBar;
    private HomeFragment mHomeFragment;
    private CategoryFragment mCategoryFragment;
    private ShopcarFragment mShopcarFragment;
    private MyJDFragment mMyJDFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mTopBar = (FrameLayout) findViewById(R.id.top_bar);
        mBottomBar = (BottomBar) findViewById(R.id.bottom_bar);
        //将接口传过去. 设置这个botton接口的监听  在botton类种中创建这个方法
        mBottomBar.setOnBottonClickListener(this);

        //初始化Fragment
        mHomeFragment = new HomeFragment();
        mCategoryFragment = new CategoryFragment();
        mShopcarFragment = new ShopcarFragment();
        mMyJDFragment = new MyJDFragment();

        //调用默认的方法
        mBottomBar.defaultShow();
    }

    //实现接口  丁重写接口的点击事件.
    @Override
    public void onBottomItemOnClick(int id) {
        //如果跳转到的主页面  我们通过获取Fragment的管理器
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (id) {
            //根据id'值 进行fragement的相互切换.
            case R.id.frag_main_ll:
                transaction.replace(R.id.top_bar, mHomeFragment);
                break;
            case R.id.frag_category_ll:
                transaction.replace(R.id.top_bar, mCategoryFragment);
                break;
            case R.id.frag_shopcar_ll:
                transaction.replace(R.id.top_bar, mShopcarFragment);
                break;
            case R.id.frag_mine_ll:
                transaction.replace(R.id.top_bar, mMyJDFragment);
                break;
        }
        transaction.commit();
    }
}