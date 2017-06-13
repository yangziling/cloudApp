package com.m520it.jdmall.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m520it.jdmall.R;
import com.m520it.jdmall.bean.RRecommendBean;
import com.m520it.jdmall.cons.NetWorkCons;

import image.SmartImageView;

/**
 * @author 杨飞
 * @time 2016/9/5  17:20
 * @desc ${TODD}
 */
public class RecommendAdapter extends JDBaseAdpter<RRecommendBean> {
    class ViewHolder {
        SmartImageView smiv;
        TextView nameTv;
        TextView priceTv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommend_item_layout,
                    parent, false);
            holder = new ViewHolder();
            //找到item中的子控件
            holder.smiv = (SmartImageView) convertView.findViewById(R.id.image_iv);
            holder.nameTv = (TextView) convertView.findViewById(R.id.name_tv);
            holder.priceTv = (TextView) convertView.findViewById(R.id.price_tv);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        RRecommendBean bean = mDatas.get(position);
        holder.nameTv.setText(bean.getName());
        holder.smiv.setImageUrl(NetWorkCons.BASE_URL +bean.getIconUrl());
        holder.priceTv.setText("¥ "+bean.getPrice());
        return convertView;
    }
}
