package com.m520it.jdmall.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.m520it.jdmall.bean.RResult;
import com.m520it.jdmall.bean.SubCategoryBean;
import com.m520it.jdmall.bean.TopCategoryBean;
import com.m520it.jdmall.cons.IdiyMessage;
import com.m520it.jdmall.cons.NetWorkCons;
import com.m520it.jdmall.util.NetworkUtil;

import java.util.ArrayList;

/**
 * @author 杨飞
 * @time 2016/9/6  1:08
 * @desc ${TODD}
 */
public class CategoryController extends BaseController {

    public CategoryController(Context c) {
        super(c);
    }

    //进行网络访问操作.
    @Override
    protected void handleMessage(int action, Object... values) {
        switch (action) {
            case IdiyMessage.TOP_CATEGORY_ACTION:
                mListener.onModelChanged(IdiyMessage.TOP_CATEGORY_ACTION_RESULT,
                        loadTopCategory());
                break;
            case IdiyMessage.SUB_CATEGORY_ACTION:
                mListener.onModelChanged(IdiyMessage.SUB_CATEGORY_ACTION_RESULT,
                        loadSubCategory((Long) values[0]));
                break;
        }
    }

    //根据后台提供的额接口 访问时西药parentid
    private ArrayList<SubCategoryBean> loadSubCategory(long parentId) {
        //开始进行网络访问
        String jsonStr = NetworkUtil.doGet(NetWorkCons.TOP_CATEGORY_URL
                + "?parentId=" + parentId);
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if(resultBean.isSuccess()) {
            return (ArrayList<SubCategoryBean>) JSON.parseArray(resultBean.getResult(),
                    SubCategoryBean.class);

        }
        return new ArrayList<SubCategoryBean> () ;
    }

    private ArrayList<TopCategoryBean> loadTopCategory() {
        String jsonStr = NetworkUtil.doGet(NetWorkCons.TOP_CATEGORY_URL);
        //阿里json的解析就是这样 不允许多层嵌套 所以必须一层一层的额进行解析
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            //如果外层的对象解析成功 在对内层的jsonStr进行解析
            return (ArrayList<TopCategoryBean>) JSON.parseArray(resultBean.getResult(),
                    TopCategoryBean.class);
        }
        return new ArrayList<TopCategoryBean>();
    }
}
