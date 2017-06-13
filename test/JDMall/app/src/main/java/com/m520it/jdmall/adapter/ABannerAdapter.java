package com.m520it.jdmall.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.m520it.jdmall.bean.AdBanner;
import com.m520it.jdmall.cons.NetWorkCons;

import java.util.ArrayList;

import image.SmartImageView;

/**
 * @author 杨飞
 * @time 2016/9/4  13:05
 * @desc ${TODD}
 */
public class ABannerAdapter extends PagerAdapter {

    private ArrayList<AdBanner> mDatas;
    private ArrayList<SmartImageView> mViews;

    public void setDatas(Context c , ArrayList<AdBanner> adBanners){
        mDatas = adBanners;
        mViews = new ArrayList<SmartImageView>();
        for (int i =0 ;i < mDatas.size();i++){
            SmartImageView iv = new SmartImageView(c);
            iv.setLayoutParams(new ViewGroup.LayoutParams(-1,-1));
            iv.setImageUrl(NetWorkCons.BASE_URL+mDatas.get(i).getAdUrl());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            mViews.add(iv);
        }
    }
    @Override
    public int getCount() {
        return mDatas !=null?mDatas.size():0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    //用来创建的viewpager的

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SmartImageView smiv = mViews.get(position);
        container.addView(smiv);
        return smiv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        SmartImageView smiv = mViews.get(position);
        container.removeView(smiv);
    }
}
