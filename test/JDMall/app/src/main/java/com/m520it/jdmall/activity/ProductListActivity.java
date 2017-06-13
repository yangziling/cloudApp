package com.m520it.jdmall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.m520it.jdmall.R;
import com.m520it.jdmall.adapter.BrandAdapter;
import com.m520it.jdmall.adapter.ProductListAdapter;
import com.m520it.jdmall.bean.RBrand;
import com.m520it.jdmall.bean.SProductList;
import com.m520it.jdmall.cons.IdiyMessage;
import com.m520it.jdmall.cons.IntentKeys;
import com.m520it.jdmall.controller.ProductListController;
import com.m520it.jdmall.listener.IModelChangeListener;
import com.m520it.jdmall.listener.IProductsSortPopListener;
import com.m520it.jdmall.ui.FlexiListView;
import com.m520it.jdmall.ui.ProductsSortPop;

import java.util.ArrayList;

/**
 * @author 杨飞
 * @time 2016/9/6  14:16
 * @desc ${TODD}
 */
public class ProductListActivity extends BaseActivity implements IModelChangeListener,
        View.OnClickListener, IProductsSortPopListener {

    private long mCategoryId;
    private long mTopcategoryId;
    private SProductList mSProductList;
    private ProductListController mController;
    private TextView mAllIndicatorTv;
    private TextView mSaleIndicator;
    private TextView mPriceIndicator;
    private FlexiListView mProductsLv;
    private ProductListAdapter mAdapter;
    private GridView mBrandGv;
    private BrandAdapter mBrandAdapter;
    private DrawerLayout mDrawerable;
    private View mSlideView;
    private ProductsSortPop mProductsSortPop;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case IdiyMessage.BRAND_ACTION_RESULT:
                    handleBrandResult((ArrayList<RBrand>) msg.obj);
                    break;
            }
        }
    };

    //给Adapter设置数据
    private void handleBrandResult(ArrayList<RBrand> datas) {
        mBrandAdapter.setDatas(datas);
        mBrandAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        //初始化数据
        initDatas();
        //初始化controller
        initController();
        //初始化界面
        initView();
        //发送一个异步请求   请求时需要携带一个parentid
        mController.sendAsyncMessage(IdiyMessage.BRAND_ACTION, mTopcategoryId);
        //发送一个网络请求 给mSProductList 设置一参数
        mSProductList.setCategoryId(mCategoryId);
        mController.sendAsyncMessage(IdiyMessage.BRAND_ACTION, mSProductList);
    }

    private void initView() {
        initMainView();//底部的主界面
        initSlideView();//侧拉的界面.
    }

    //侧滑页面
    private void initSlideView() {
        //1.创建品牌列表
        mBrandGv = (GridView) findViewById(R.id.gv);
        mBrandAdapter = new BrandAdapter();
        mBrandGv.setAdapter(mBrandAdapter);
        mBrandGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mBrandAdapter.mCurrentTabIndex = position;
                mBrandAdapter.notifyDataSetChanged();
            }
        });
        //获取滑动页面的控件
        mDrawerable = (DrawerLayout) findViewById(R.id.drawerlayout);
        mSlideView = findViewById(R.id.slide_view);
    }

    //主界面
    private void initMainView() {
        //筛选按钮  每个标题都给其设置 点击事件
        findViewById(R.id.choose_indicator).setOnClickListener(this);
        mAllIndicatorTv = (TextView) findViewById(R.id.all_indicator);
        mAllIndicatorTv.setOnClickListener(this);

        mSaleIndicator = (TextView) findViewById(R.id.sale_indicator);
        mSaleIndicator.setOnClickListener(this);

        mPriceIndicator = (TextView) findViewById(R.id.price_indicator);
        mPriceIndicator.setOnClickListener(this);

        //获取listview的控件 然后进行跟数据进行绑定
        mProductsLv = (FlexiListView) findViewById(R.id.product_lv);
        mAdapter = new ProductListAdapter();
        mProductsLv.setAdapter(mAdapter);
    }

    private void initController() {
        mController = new ProductListController(this);
        //设置回调
        mController.setModeChangeListener(this);
    }

    private void initDatas() {
        Intent intent = getIntent();
        //页面跳转时携带有数据
        mCategoryId = intent.getLongExtra(IntentKeys.THIRDCATEGORYID, 0);
        mTopcategoryId = intent.getLongExtra(IntentKeys.TOPCATEGORYID, 0);
        if (mCategoryId == 0 && mTopcategoryId == 0) {
            finish();
        }
        //看到商品搜索的后台的
        mSProductList = new SProductList();

    }

    @Override
    public void onModelChanged(int action, Object... values) {
        mHandler.obtainMessage(action, values[0]).sendToTarget();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_indicator:
                mDrawerable.openDrawer(mSlideView);
                break;
            case R.id.ad_indicator://综合查询
                //弹出一个对话框  PopupWindow 单例
                //一个弹出框 应该自己本身封装好自己的业务
                if (mProductsSortPop == null) {
                    mProductsSortPop = new ProductsSortPop(this);
                    mProductsSortPop.setOnSortChangeListener(this);
                }
                mProductsSortPop.onShow(mAllIndicatorTv);
                //设置一下字体的颜色
                mAllIndicatorTv.setSelected(true);
                break;
            case R.id.sale_indicator://销量
                // TODO: 2016/9/6
                mSProductList.setSortType(SProductList.SALE);
                //发送异步请求
                mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSProductList);
                break;
            case R.id.price_indicator://价格
                //0-默认 1-销量 2-价格高到低 3-价格低到高   TODO
                //从高到底: sortType=0||sortType=1
                //从低到高: sortType=2
                int sortType = mSProductList.getSortType();
                if (sortType == SProductList.SALE || sortType == 0) {
                    mSProductList.setSortType(SProductList.PRICE_UP2DOWN);
                } else if (sortType == SProductList.PRICE_UP2DOWN) {
                    mSProductList.setSortType(SProductList.PRICE_DOWN2UP);
                }
                mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSProductList);
                break;
        }

    }

    @Override
    public void onSortChaneged(int sort) {
        switch (sort) {
            case IProductsSortPopListener.ALL_SORT:
                mAllIndicatorTv.setText("综合");
                break;
            case IProductsSortPopListener.NEW_SORT:
                mAllIndicatorTv.setText("新品");
                break;
            case IProductsSortPopListener.COMMENT_SORT:
                mAllIndicatorTv.setText("评论");
                break;
        }
        //发送一个网络请求  TODO
        mSProductList.setFilterType(sort);
        mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION,mSProductList);
    }
    /**
     * 确定按钮
     */
    public  void chooseSearchClick(View view){
        //TODO
        //1.找到服务 查看服务有没选中
        //2.找到价格区间 看里面有没值(价格区间必须两个都写上)
        //3.品牌是否有选中
    }

    /**
     *	重置按钮
     */
    public void resetClick(View v){
        mSProductList=new SProductList();
        mController.sendAsyncMessage(IdiyMessage.PRODUCT_LIST_ACTION, mSProductList);
        //TODO 将界面的所有操作复原

    }

}
