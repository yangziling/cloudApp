package com.m520it.jdmall.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.m520it.jdmall.R;
import com.m520it.jdmall.adapter.ABannerAdapter;
import com.m520it.jdmall.adapter.RecommendAdapter;
import com.m520it.jdmall.adapter.SecondKillAdapter;
import com.m520it.jdmall.bean.AdBanner;
import com.m520it.jdmall.bean.RRecommendBean;
import com.m520it.jdmall.bean.RSecKill;
import com.m520it.jdmall.cons.IdiyMessage;
import com.m520it.jdmall.controller.HomeController;
import com.m520it.jdmall.listener.IModelChangeListener;
import com.m520it.jdmall.ui.HorizontalListView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author 杨飞
 * @time 2016/9/3  17:47
 * @desc ${TODD}
 */
public class HomeFragment extends BaseFragment implements IModelChangeListener {

    private ViewPager mViewPager;
    private ABannerAdapter mAdapter;
    private Timer mTimer;
    private LinearLayout mAdIndicatorLl;
    private GridView mGridViews;
    private RecommendAdapter mRecommendAdapter;

    private Handler mHandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case  IdiyMessage.AD_BANNER_ACTION_RESULT:
                    handleLoadAdBanner(msg);
                    break;
                case  IdiyMessage.SEC_KILL_ACTION_RESULT:
                    handleSecKillResult((ArrayList<RSecKill>)msg.obj);
                    break;
                case  IdiyMessage.RECOMMEND_ACTION_RESULT:
                    handleRecommendResult((ArrayList<RRecommendBean>) msg.obj);
                    break;
            }
        }
    };

    //猜你喜欢栏位  信息处理
    private void handleRecommendResult(ArrayList<RRecommendBean> beans) {
        mRecommendAdapter.setDatas(beans);
        mRecommendAdapter.notifyDataSetChanged();
    }


    private void handleSecKillResult(ArrayList<RSecKill> beans) {
        mSecondKillAdapter.setDatas(beans);
        mSecondKillAdapter.notifyDataSetChanged();
    }
    //秒杀栏位的信息处理

    private HorizontalListView mSecondKillLv;
    private SecondKillAdapter mSecondKillAdapter;


    //处理信息
    private void handleLoadAdBanner(Message msg) {
        //1.为广告栏设置 数据
       final ArrayList<AdBanner> adBanners = (ArrayList<AdBanner>) msg.obj;
        mAdapter.setDatas(getActivity(),adBanners);
        mAdapter.notifyDataSetChanged();
        //2.广告的指示器 设置样式
        for (int i = 0;i <adBanners.size();i++){
            ImageView iv = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(30, 0, 0, 0);
           // iv.setLayoutParams(params);
            iv.setImageResource(R.drawable.ad_indicator_shap_bg);
            mAdIndicatorLl.addView(iv,params);
        }
        changeAdIndicatorStyle(0);//设置指示器的状态

        //3.为指示器设置一个定时器
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int index = mViewPager.getCurrentItem();
                        index++;
                        if(index >adBanners.size()-1) {
                            index = 0;
                        }
                        mViewPager.setCurrentItem(index,true);
                    }
                });
            }
        },3000,3000);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                changeAdIndicatorStyle(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    @Override
    public void onDestroy() {//退出时,定时器还在执行.所有会导致空指针.
        super.onDestroy();
        mTimer.cancel();
    }

    /**
     * 设置当前选中的那一项
     * @param
     */
    private void changeAdIndicatorStyle(int position) {
        int childCount = mAdIndicatorLl.getChildCount();
        for(int i=0;i <childCount;i++){
            mAdIndicatorLl.getChildAt(i).setEnabled(false);
        }
        mAdIndicatorLl.getChildAt(position).setEnabled(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, null);
    }
    //只能在onActivityCreated 这个方法下面初始化控件
    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initController();
        initView();
        //controller view也有啦,做一个网络请求   进行异步请求是 需不需传参数 就看后台的接口
        mController.sendAsyncMessage(IdiyMessage.AD_BANNER_ACTION,1);
        //数据都是从后台来的,而且需要存放在adapter中  所以需要创建一个controller对象.
        mController.sendAsyncMessage(IdiyMessage.SEC_KILL_ACTION,0);
        //发送异步请求
        mController.sendAsyncMessage(IdiyMessage.RECOMMEND_ACTION,0);
    }
    private void initController() {
        mController = new HomeController(getActivity());
        mController.setModeChangeListener(this);
    }
    private void initView() {
        //viewpager 存放视图的容器
        mViewPager = (ViewPager) getActivity().findViewById(R.id.ad_vp);
        mAdapter= new ABannerAdapter();
        mViewPager.setAdapter(mAdapter);

        mAdIndicatorLl = (LinearLayout) getActivity().findViewById(
                R.id.ad_indicator);
        //初始化秒杀列表
        mSecondKillLv = (HorizontalListView) getActivity().findViewById(R.id.horizon_listview);
        mSecondKillAdapter = new SecondKillAdapter();
        mSecondKillLv.setAdapter(mSecondKillAdapter);
        //猜你喜欢
        mGridViews = (GridView) getActivity().findViewById(R.id.recommend_gv);
        mRecommendAdapter = new RecommendAdapter();
        mGridViews.setAdapter(mRecommendAdapter);
    }

    @Override

    public void onModelChanged(int action, Object... values) {
        mHandler.obtainMessage(action,values[0]).sendToTarget();
    }
}

