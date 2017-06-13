package com.m520it.jdmall.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.m520it.jdmall.R;
import com.m520it.jdmall.adapter.TopCategoryAdapter;
import com.m520it.jdmall.bean.TopCategoryBean;
import com.m520it.jdmall.cons.IdiyMessage;
import com.m520it.jdmall.controller.CategoryController;
import com.m520it.jdmall.listener.IModelChangeListener;
import com.m520it.jdmall.ui.SubCategoryView;

import java.util.ArrayList;

/**
 * @author 杨飞
 * @time 2016/9/3  17:54
 * @desc ${TODD}
 */
public class CategoryFragment extends BaseFragment implements IModelChangeListener,
        OnItemClickListener {

    private CategoryController mController;
    private ListView mTopCategoryLv;
    private SubCategoryView mSubCategoryView;
    private TopCategoryAdapter mTopCatgoryAdapter;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case  IdiyMessage.TOP_CATEGORY_ACTION_RESULT:
                    handleTopCategoryResult((ArrayList< TopCategoryBean>)msg.obj);
                    break;
            }
        }
    };

    private void handleTopCategoryResult(ArrayList< TopCategoryBean> datas) {
        //这里需要将数据设置daoAdapter中
        mTopCatgoryAdapter.setDatas(datas);
        mTopCatgoryAdapter.notifyDataSetChanged();
        //模拟点击第一项
        mTopCategoryLv.performItemClick(null, 0, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    //所有控件都需要在这里进行初始化
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initController();
        initUI();
        //获取首层目录 不需要 参数 所以 进行异步请求 直接写个0
        mController.sendAsyncMessage(IdiyMessage.TOP_CATEGORY_ACTION,0);
    }

    //初始化ui控件
    private void initUI() {
        mTopCategoryLv = (ListView) getActivity().findViewById(R.id.top_lv);
        mTopCatgoryAdapter = new TopCategoryAdapter();
        mTopCategoryLv.setAdapter(mTopCatgoryAdapter);
        mTopCategoryLv.setOnItemClickListener(this);

        //获取子分类控件
        mSubCategoryView = (SubCategoryView) getActivity().findViewById(R.id.subcategory);
    }

    //初始化 控制器
    private void initController() {
        mController = new CategoryController(getActivity());
        //设置回调函数  :当后台数据 有变动时 ,直接通知fragment 进行修改ui界面
        mController.setModeChangeListener(this);
    }
    @Override
    public void onModelChanged(int action, Object... values) {
        mHandler.obtainMessage(action,values[0]).sendToTarget();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mTopCatgoryAdapter.mCurrentTabId =position;
        mTopCatgoryAdapter.notifyDataSetChanged();
        //点击最上层的目录 可以显示右边子控件
        //并将数据传递给子控件
        TopCategoryBean bean = (TopCategoryBean) mTopCatgoryAdapter.getItem(position);
        mSubCategoryView.onShow(bean);


    }
}
