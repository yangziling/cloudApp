package com.m520it.jdmall.ui;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.m520it.jdmall.R;
import com.m520it.jdmall.listener.IProductsSortPopListener;

/**
 * @author 杨飞
 * @time 2016/9/6  17:42
 * @desc ${TODD}
 */
public class ProductsSortPop implements View.OnClickListener {

    private IProductsSortPopListener mListener;
    private PopupWindow mPopupWindow;

    public ProductsSortPop(Context c) {
        initViews(c);
    }

    public void setOnSortChangeListener(IProductsSortPopListener listener) {
        mListener = listener;
    }

    private void initViews(Context c) {
        //初始化弹出框 contentView
        //设置弹出宽高 大小
        View convertView = LayoutInflater.from(c).inflate(R.layout.product_sort_pop_layout, null);
        convertView.findViewById(R.id.all_sort).setOnClickListener(this);
        convertView.findViewById(R.id.new_sort).setOnClickListener(this);
        convertView.findViewById(R.id.comment_sort).setOnClickListener(this);
        convertView.findViewById(R.id.all_sort).setOnClickListener(this);

        mPopupWindow = new PopupWindow(convertView, -1, -1);
        //1.让 mPopupWindow 内部的控件获取焦点
        mPopupWindow.setFocusable(true);
        //2.mPopupWindow内部获取焦点后  外部所有的控件失去焦点
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        //3.如果不马上显示popupWindow 一般建议刷新
        mPopupWindow.update();
    }

    //1.在某个控件周围显示
    //2.在某个容器里面显示
    public void onShow(View anchorView) {
        mPopupWindow.showAsDropDown(anchorView, 0, 0);
    }

    //最后点击之后会调用dismiss方法
    private void onDismiss() {
        mPopupWindow.dismiss();
    }


    @Override
    public void onClick(View v) {
        if (mListener != null) {
            switch (v.getId()) {
                case R.id.all_sort:
                    mListener.onSortChaneged(IProductsSortPopListener.ALL_SORT);
                    break;
                case R.id.new_sort:
                    mListener.onSortChaneged(IProductsSortPopListener.NEW_SORT);
                    break;
                case R.id.comment_sort:
                    mListener.onSortChaneged(IProductsSortPopListener.COMMENT_SORT);
                    break;
            }
        }
        onDismiss();
    }
}
