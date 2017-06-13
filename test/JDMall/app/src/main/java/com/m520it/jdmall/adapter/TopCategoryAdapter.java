package com.m520it.jdmall.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.m520it.jdmall.R;
import com.m520it.jdmall.bean.TopCategoryBean;

/**
 * @author 杨飞
 * @time 2016/9/6  1:33
 * @desc ${TODD}
 */
public class TopCategoryAdapter extends JDBaseAdpter<TopCategoryBean>  {
    //来记录当前选中的item列表
    public int mCurrentTabId=-1;

    class ViewHolder{
        TextView nameTv;
        View divideView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_category_item_layout,
                    parent, false);
            holder =new ViewHolder();
            holder.nameTv = (TextView)convertView.findViewById(R.id.tv);
            holder.divideView = convertView.findViewById(R.id.divider);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        TopCategoryBean bean = mDatas.get(position);
        holder.nameTv.setText(bean.getName());

        holder.divideView.setSelected(position == mCurrentTabId);
        holder.divideView.setVisibility(position==mCurrentTabId?View.INVISIBLE:View.VISIBLE);
       //判断一下  给其设置背景
        if(position==mCurrentTabId) {
            holder.nameTv.setBackgroundResource(R.drawable.tongcheng_all_bg01);
        }else{
            holder.nameTv.setBackgroundColor(0xFFCCCCCC);
        }
        return convertView;

    }

    @Override
    public Object getItem(int position) {
        return mDatas!=null?mDatas.get(position):null;
    }
}
