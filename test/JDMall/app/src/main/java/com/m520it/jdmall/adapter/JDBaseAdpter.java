package com.m520it.jdmall.adapter;

import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * @author 杨飞
 * @time 2016/9/4  23:08
 * @desc ${TODD}
 */
public abstract class JDBaseAdpter<T> extends BaseAdapter {

    protected ArrayList<T> mDatas;

    public   void setDatas(ArrayList<T> datas){
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas!=null?mDatas.size():0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
