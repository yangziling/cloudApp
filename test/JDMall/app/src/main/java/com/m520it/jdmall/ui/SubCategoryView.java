package com.m520it.jdmall.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.m520it.jdmall.R;
import com.m520it.jdmall.activity.ProductListActivity;
import com.m520it.jdmall.bean.SubCategoryBean;
import com.m520it.jdmall.bean.TopCategoryBean;
import com.m520it.jdmall.cons.IdiyMessage;
import com.m520it.jdmall.cons.IntentKeys;
import com.m520it.jdmall.cons.NetWorkCons;
import com.m520it.jdmall.controller.CategoryController;
import com.m520it.jdmall.listener.IModelChangeListener;
import com.m520it.jdmall.listener.IViewContainer;

import java.util.ArrayList;
import java.util.List;

import image.SmartImageView;

/**
 * @author 杨飞
 * @time 2016/9/6  0:47
 * @desc ${TODD}
 */
public class SubCategoryView extends FlexiScrollView implements IModelChangeListener,
        IViewContainer {

    private static final int MAX_COLUMNS = 3;
    private TopCategoryBean mTopCategoryBean;
    private LinearLayout mContainerLl;
    private CategoryController mController;

    public SubCategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //hanlder是处理  返回的结果的  然后去修改ui界面.所以是action_result
                case IdiyMessage.SUB_CATEGORY_ACTION_RESULT:
                    handleSubCategoryResult((ArrayList<SubCategoryBean>) msg.obj);
                    break;
            }
        }
    };

    //显示2级/3级分类
    private void handleSubCategoryResult(ArrayList<SubCategoryBean> datas) {
        //每次点击1级分类 获取2级分类的数据后先清空容器
        mContainerLl.removeAllViews();
        if (datas.size() != 0) {
            //1.加载图片的数目
            initBanner();
            //2.加载2级分类
            for (int i = 0; i < datas.size(); i++) {
                SubCategoryBean subCategoryBean = datas.get(i);
                TextView secondCategoryNameTv = new TextView(getContext());
                LinearLayout.LayoutParams secondCategoryNameTvlp
                        = new LinearLayout.LayoutParams(-2, -2);
                secondCategoryNameTvlp.setMargins(0, 5, 0, 0);
                secondCategoryNameTv.setLayoutParams(secondCategoryNameTvlp);
                secondCategoryNameTv.setText(subCategoryBean.getName());
                secondCategoryNameTv.setTextSize(17);
                //以上为2级分类
                mContainerLl.addView(secondCategoryNameTv);
                List<TopCategoryBean> thirdCategoryBean = JSON.parseArray(subCategoryBean.getThirdCategory(),
                        TopCategoryBean.class);

                //3 确定三级分类的行数
                int thirdCategoryCount = thirdCategoryBean.size();
                int line = thirdCategoryCount / MAX_COLUMNS;
                line = thirdCategoryCount % MAX_COLUMNS == 0?line:(line + 1);
                //4.为每一行数据添加一个thirdCategoryLl容器
                for (int j =0 ;j<line;j++){
                    LinearLayout thirdCategoryLl = new LinearLayout(getContext());
                    LinearLayout.LayoutParams thirdCategorylp = new LinearLayout.LayoutParams(-1, -2);
                    thirdCategorylp.setMargins(0,10,0,0);
                    thirdCategoryLl.setLayoutParams(thirdCategorylp);
                    mContainerLl.addView(thirdCategoryLl);
                    //5.添加每一列 每一列的索引只要不超过总数的索引数 就可以添加
                    if(j*MAX_COLUMNS<=thirdCategoryCount-1){//索引值是从0开始的.
                        //添加第一列
                        addThirdCategoryColumns(thirdCategoryBean.get(j*MAX_COLUMNS),
                                thirdCategoryLl);
                    }
                    if(j*MAX_COLUMNS+1<=thirdCategoryCount-1) {
                        //添加第二列
                        addThirdCategoryColumns(thirdCategoryBean.get(j*MAX_COLUMNS+1),
                                thirdCategoryLl);
                    }
                    if(j*MAX_COLUMNS+2<=thirdCategoryCount-1) {
                        //添加第二列
                        addThirdCategoryColumns(thirdCategoryBean.get(j*MAX_COLUMNS+2),
                                thirdCategoryLl);
                    }
                }

            }
        }else{
            Toast.makeText(getContext(),"加载异常",Toast.LENGTH_SHORT).show();
        }
    }

    //添加3级分类的列
    private void addThirdCategoryColumns(final TopCategoryBean bean, LinearLayout thirdLl) {
        //1.添加一个从上到下的容器   然后设置容器的额属性
        LinearLayout verticalLl = new LinearLayout(getContext());
        verticalLl.setLayoutParams(new LinearLayout.LayoutParams(getWidth()/3,-2));
        verticalLl.setGravity(Gravity.CENTER_HORIZONTAL);
        verticalLl.setOrientation(LinearLayout.VERTICAL);

        //给3级分类的获取商品列表 设置点击事件
        verticalLl.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击之后进行页面跳转
                Intent intent = new Intent(getContext(), ProductListActivity.class);
                //3级分类id用户获取商品列表
                intent.putExtra(IntentKeys.THIRDCATEGORYID,bean.getId());

                //1级分类的id 用户获取品牌列表的参数
                intent.putExtra(IntentKeys.TOPCATEGORYID,mTopCategoryBean.getId());

                getContext().startActivity(intent);
            }
        });


        //2.创建一个图片控件  添加到上面的容器中
        SmartImageView smiv = new SmartImageView(getContext());
        smiv.setLayoutParams(new ViewGroup.LayoutParams(-2,-2));//wrap_content
        smiv.setScaleType(ImageView.ScaleType.FIT_XY);
        smiv.setImageUrl(NetWorkCons.BASE_URL+bean.getBannerUrl());
        verticalLl.addView(smiv);

        //3.创建一个文本 添加到verticalLl
        TextView nameTv = new TextView(getContext());
        LinearLayout.LayoutParams nameLp = new LinearLayout.LayoutParams(-2, -2);
        nameLp.setMargins(0,5,0,0);
        nameTv.setLayoutParams(nameLp);
        nameTv.setText(bean.getName());
        verticalLl.addView(nameTv);

        //整个列的容器都创建完毕 接下来就需要添加到行的容器中
        thirdLl.addView(verticalLl);
    }

    /**
     * 顶部广告栏
     */
    private void initBanner() {
        String bannerUrl = NetWorkCons.BASE_URL + mTopCategoryBean.getBannerUrl();
        SmartImageView smiv = new SmartImageView(getContext());
        //设置图片的宽高  -1 为parent  -2为Wrapcontent
        smiv.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        smiv.setScaleType(ImageView.ScaleType.FIT_XY);
        smiv.setImageUrl(bannerUrl);
        //创建一个linearLayout 容器  存放其他的控件.
        mContainerLl.addView(smiv);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initController();
        initView();
    }

    //初始化ui
    private void initView() {
        mContainerLl = (LinearLayout) findViewById(R.id.child_container_ll);
    }

    //初始化 controller  进行网络访问
    private void initController() {
        mController = new CategoryController(getContext());
        mController.setModeChangeListener(this);

    }


    @Override
    public void onShow(Object... values) {
        //思路:当2级分类有了之后再添加顶部图片 如果数据都没有那么添加顶部图片显然没什么意义
        //1.从网络获取2级分类数据
        //2.加载顶部的图片
        //3.加载2级分类的界面
        mTopCategoryBean = (TopCategoryBean) values[0];//最上层的对象
        //非上层目录 获取值时,需要parentid
        mController.sendAsyncMessage(IdiyMessage.SUB_CATEGORY_ACTION,
                mTopCategoryBean.getId());
    }
    @Override
    public void onModelChanged(int action, Object... values) {
        mHandler.obtainMessage(action,values[0]).sendToTarget();
    }

}
