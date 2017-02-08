package com.example.news.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.base.adapter.MyBaseAdapter;
import com.example.news.model.entity.UserCenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 权威 on 2016/12/29.
 */

public class UserCenterAdapter extends MyBaseAdapter<UserCenter.DataBean.LoginlogBean> {
    public UserCenterAdapter(Context context, List<UserCenter.DataBean.LoginlogBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View view) {
        ViewHolder mHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.user_center_list_item, null);
            mHolder=new ViewHolder(view);
            view.setTag(mHolder);
         }
        else{ mHolder = (ViewHolder) view.getTag();}
        UserCenter.DataBean.LoginlogBean login=mData.get(position);
        mHolder.textUserItemTimer.setText(login.getTime().substring(0,10));
        mHolder.textUserItemAdress.setText(login.getAddress());
        mHolder.textUserItemDevoce.setText( Integer.toString(login.getDevice()));
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.text_user_item_timer)
        TextView textUserItemTimer;
        @Bind(R.id.text_user_item_adress)
        TextView textUserItemAdress;
        @Bind(R.id.text_user_item_devoce)
        TextView textUserItemDevoce;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
