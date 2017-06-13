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
 * @time 2016/9/5  12:48
 * @desc ${TODD}
 */
public class SecondKillAdapter extends JDBaseAdpter<RSecKill> {
    class ViewHolder {
        SmartImageView smiv;
        TextView mNormalPriceTv;
        TextView mNowPriceTv;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sec_kill_item_layout,
                    parent, false);
            //找控件
            holder = new ViewHolder();
            holder.smiv = (SmartImageView) convertView.findViewById(R.id.image_iv);
            holder.mNormalPriceTv = (TextView) convertView.findViewById(R.id.normalprice_tv);
            holder.mNowPriceTv = (TextView) convertView.findViewById(R.id.nowprice_tv);
            //将对象设置
            convertView.setTag(holder);
        }else{
            //走到这里 说明holder对象已经存在
            holder  = (ViewHolder) convertView.getTag();
        }
        RSecKill bean = mDatas.get(position);
        holder.smiv.setImageUrl(NetWorkCons.BASE_URL +bean.getIconUrl());
        holder.mNormalPriceTv.setText(bean.getAllPrice());
        holder.mNowPriceTv.setText(bean.getPointPrice());
        return convertView;
    }
}
