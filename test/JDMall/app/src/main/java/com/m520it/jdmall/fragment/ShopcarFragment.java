package com.m520it.jdmall.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.m520it.jdmall.R;

/**
 * @author 杨飞
 * @time 2016/9/3  17:56
 * @desc ${TODD}
 */
public class ShopcarFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopcar, container, false);
    }
}
