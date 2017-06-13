package com.m520it.jdmall.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m520it.jdmall.R;
import com.m520it.jdmall.bean.RSecKill;
import com.m520it.jdmall.cons.NetWorkCons;

import image.SmartImageView;

/**
 * @author 杨飞
 * @time 2016/9/6  15:50
 * @desc ${TODD}
 */
public class ProductListAdapter extends JDBaseAdpter<RSecKill> {
    class ViewHolder {
        SmartImageView smiv;
        TextView mNormalPriceTv;
        TextView mNowPriceTv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder=null;
        if(convertView==null) {
            convertView=LayoutInflater.from(parent.getContext()).inflate(R.layout.sec_kill_item_layout,
                    parent,false);
            holder = new ViewHolder();
            //找到控件
            holder.smiv = (SmartImageView) convertView.findViewById(R.id.image_iv);
            holder.mNormalPriceTv = (TextView) convertView.findViewById(R.id.normalprice_tv);
            holder.mNowPriceTv = (TextView) convertView.findViewById(R.id.nowprice_tv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //先获取数据
        RSecKill bean = mDatas.get(position);
        //给控件设置值
        holder.smiv.setImageUrl(NetWorkCons.BASE_URL+bean.getIconUrl());
        holder.mNormalPriceTv.setText(bean.getAllPrice());
        holder.mNowPriceTv.setText(bean.getPointPrice());

        return convertView;
    }
}
