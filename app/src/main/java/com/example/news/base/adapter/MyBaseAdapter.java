package com.example.news.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.news.base.control.BaseActivity;

import java.util.List;

/**
 * Created by 权威 on 2016/12/22.
 * 公共的基础适配器
 */

public  abstract class MyBaseAdapter<T> extends BaseAdapter{
    protected LayoutInflater  mInflater;
    protected List<T> mData;
    protected Context mContext;
    public MyBaseAdapter(Context context, List<T> data) {
        this.mInflater=LayoutInflater.from(context);
         this.mData=data;
        this.mContext=context;
    }
public void update( List<T> data){
    this.mData=data;
    notifyDataSetChanged();
}
    @Override
    public int getCount() {
        return mData!=null?mData.size():0;
    }

    @Override
    public T getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getView(i,view);
    }
    public abstract View getView(int position,View view);


}
