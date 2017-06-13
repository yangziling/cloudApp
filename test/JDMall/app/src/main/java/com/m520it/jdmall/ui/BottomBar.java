package com.m520it.jdmall.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.m520it.jdmall.R;
import com.m520it.jdmall.listener.IBottomBarChangeListener;

/**
 * @author 杨飞
 * @time 2016/9/2  2:50
 * @desc ${TODD}
 */
public class BottomBar extends LinearLayout implements View.OnClickListener {
    private TextView mHomeTv;
    private ImageView mHomeIv;
    private TextView mCategoryTv;
    private ImageView mCategoryIv;
    private ImageView mShopcarIv;
    private TextView mShopcarTv;
    private ImageView mMimeIv;
    private TextView mMimeTv;

    private IBottomBarChangeListener mListener;
     int mCurrentTabId = -1;

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setOnBottonClickListener(IBottomBarChangeListener listener) {
        mListener = listener;
    }
    //将布局文件转化成view
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        findViewById(R.id.frag_main_ll).setOnClickListener(this);
        findViewById(R.id.frag_category_ll).setOnClickListener(this);
        findViewById(R.id.frag_shopcar_ll).setOnClickListener(this);
        findViewById(R.id.frag_mine_ll).setOnClickListener(this);

        mHomeIv = (ImageView) findViewById(R.id.frag_main_iv);
        mHomeTv = (TextView) findViewById(R.id.frag_main);
        mCategoryIv = (ImageView) findViewById(R.id.frag_category_iv);
        mCategoryTv = (TextView) findViewById(R.id.frag_category);
        mShopcarIv = (ImageView) findViewById(R.id.frag_shopcar_iv);
        mShopcarTv = (TextView) findViewById(R.id.frag_shopcar);
        mMimeIv = (ImageView) findViewById(R.id.frag_mine_iv);
        mMimeTv = (TextView) findViewById(R.id.frag_mine);
    }

    //给一个方法 当前的tab默认未被选中
    private  void indicate(){
        mHomeIv.setSelected(false);
        mHomeTv.setSelected(false);
        mCategoryIv.setSelected(false);
        mCategoryTv.setSelected(false);
        mShopcarIv.setSelected(false);
        mShopcarTv.setSelected(false);
        mMimeIv.setSelected(false);
        mMimeTv.setSelected(false);
    }
    @Override
    public void onClick(View v) {
        //判断一下  如果点击的id 是当前的额id 就不需要执行一下操作
        if(mCurrentTabId==v.getId()) {
            return;
        }
        mCurrentTabId = v.getId();

        indicate();
        mListener.onBottomItemOnClick(v.getId());
        switch (v.getId()) {
            //根据id'值 进行fragement的相互切换.
            case  R.id.frag_main_ll:
                mHomeIv.setSelected(true);
                mHomeTv.setSelected(true);
                break;
            case R.id.frag_category_ll:
                mCategoryIv.setSelected(true);
                mCategoryTv.setSelected(true);
                break;
            case R.id.frag_shopcar_ll:
                mShopcarIv.setSelected(true);
                mShopcarTv.setSelected(true);
                break;
            case R.id.frag_mine_ll:
                mMimeIv.setSelected(true);
                mMimeTv.setSelected(true);
                break;
        }

    }
//模拟一个点计算事件. more选中
    public void defaultShow(){
        findViewById(R.id.frag_main_ll).performClick();
    }

}
