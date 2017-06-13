package com.m520it.jdmall.fragment;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.m520it.jdmall.controller.BaseController;

/**
 * @author 杨飞
 * @time 2016/9/3  14:51
 * @desc ${TODD}
 */
public class BaseFragment extends Fragment {

    protected BaseController mController;
    public void tip(String msg){
        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }
}
