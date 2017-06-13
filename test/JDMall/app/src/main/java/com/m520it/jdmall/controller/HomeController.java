package com.m520it.jdmall.controller;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.m520it.jdmall.bean.AdBanner;
import com.m520it.jdmall.bean.RRecommendBean;
import com.m520it.jdmall.bean.RResult;
import com.m520it.jdmall.bean.RSecKill;
import com.m520it.jdmall.cons.IdiyMessage;
import com.m520it.jdmall.cons.NetWorkCons;
import com.m520it.jdmall.util.NetworkUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author 杨飞
 * @time 2016/9/4  13:10
 * @desc ${TODD}
 */
public class HomeController extends BaseController {
    public HomeController(Context c) {
        super(c);
    }

    @Override
    protected void handleMessage(int action, Object... values) {
        //有homeFragment传过来 action 在这里进行网络请求访问
        //在handleMessage中进行获取
        switch (action) {
            case IdiyMessage.AD_BANNER_ACTION:
                ArrayList<AdBanner> adBanner = getAdBanner((Integer) values[0]);
                mListener.onModelChanged(IdiyMessage.AD_BANNER_ACTION_RESULT,
                        adBanner);
                break;
            case IdiyMessage.SEC_KILL_ACTION:
                mListener.onModelChanged(IdiyMessage.SEC_KILL_ACTION_RESULT,
                        loadSecKill());
                break;
            case IdiyMessage.RECOMMEND_ACTION:
                mListener.onModelChanged(IdiyMessage.RECOMMEND_ACTION_RESULT,
                        loadRecommend());
                break;
        }

    }

    private ArrayList<RRecommendBean> loadRecommend() {
        //进行网络请求
        try {
            String JsonStr = NetworkUtil.doGet(NetWorkCons.RECOMMEMD_URL);
            RResult resultBean = JSON.parseObject(JsonStr, RResult.class);
            if (resultBean.isSuccess()) {

                JSONObject jsonObject = new JSONObject(resultBean.getResult());
                String datarows = (String) jsonObject.getString("rows");
                return (ArrayList<RRecommendBean>) JSON.parseArray(datarows, RRecommendBean.class);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<RRecommendBean>();

    }

    private ArrayList<RSecKill> loadSecKill() {
        //进行网络请求
        try {
            String JsonStr = NetworkUtil.doGet(NetWorkCons.SECKILL_URL);
            RResult resultBean = JSON.parseObject(JsonStr, RResult.class);
            if (resultBean.isSuccess()) {

                JSONObject jsonObject = new JSONObject(resultBean.getResult());
                String datarows = (String) jsonObject.getString("rows");
            return (ArrayList<RSecKill>) JSON.parseArray(datarows, RSecKill.class);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<RSecKill>();
    }

    private ArrayList<AdBanner> getAdBanner(int adKind) {

        //获取网络数据  需要创建url地址 ADBANNER_URL
        String jsonStr = NetworkUtil.doGet(NetWorkCons.ADBANNER_URL + "?adKind=" + adKind);
        //请求返回的数据 是 json代码
        RResult resultBean = JSON.parseObject(jsonStr, RResult.class);
        if (resultBean.isSuccess()) {
            return (ArrayList<AdBanner>) JSON.parseArray(resultBean.getResult(), AdBanner.class);
        }
        return new ArrayList<AdBanner>();
    }


}
