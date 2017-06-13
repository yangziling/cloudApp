package com.m520it.jdmall.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m520it.jdmall.R;
import com.m520it.jdmall.bean.RBrand;

/**
 * @author 杨飞
 * @time 2016/9/6  16:20
 * @desc ${TODD}
 */
public class BrandAdapter extends JDBaseAdpter<RBrand>{
    //记录当前的点击的状态
    public int mCurrentTabIndex=-1;
    class ViewHolder {
        TextView btn;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null) {
            convertView= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.brand_item_layout,
                    parent,false);
            holder = new ViewHolder();
            //找到控件
            holder.btn = (TextView) convertView.findViewById(R.id.brand_tv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //先获取数据
        RBrand bean = mDatas.get(position);
        //给控件设置值
        holder.btn.setText(bean.getName());
        holder.btn.setSelected(mCurrentTabIndex==position);
        return convertView;
    }
}
