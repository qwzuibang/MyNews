package com.example.news.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.base.adapter.MyBaseAdapter;
import com.example.news.model.entity.NewsDatail;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

/**
 * Created by 权威 on 2016/12/22.
 */

public class MainAdapter extends MyBaseAdapter<NewsDatail> {


    public MainAdapter(Context context, List<NewsDatail> data) {
        super(context, data);
    }
    @Override
    public View getView(int position, View view) {
        ViewHolder viewHolder;
        if(view==null){
            viewHolder=new ViewHolder();
           view=mInflater.inflate(R.layout.main_news_item,null);
            viewHolder.mImageView= (ImageView) view.findViewById(R.id.img_main_item_icon);
            viewHolder.mTextViewTitle= (TextView) view.findViewById(R.id.txt_main_news_item_title);
            viewHolder.mTextViewSummer= (TextView) view.findViewById(R.id.txt_main_news_item_summer);
            viewHolder.mTextViewStamp= (TextView) view.findViewById(R.id.txt_main_news_item_stamp);
           view.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) view.getTag();
        }
        //将返回图片的地址转成图片设置给imageView
        NewsDatail newsDatail=getItem(position);

        RequestCreator requestCreator=Picasso.with(mContext).load(newsDatail.icon);

        //设置占位符
        requestCreator.placeholder(R.mipmap.defaultpic);
        //加载失败显示
        requestCreator.error(R.mipmap.ic_launcher);
        //设置图片大小
        requestCreator.resize(120,80);
        requestCreator.into( viewHolder.mImageView);


        viewHolder.mTextViewTitle.setText(newsDatail.title);
        viewHolder.mTextViewSummer.setText(newsDatail.summary);
        viewHolder.mTextViewStamp.setText(newsDatail.stamp);
        return view;
    }
    class  ViewHolder{
    ImageView mImageView;
        TextView mTextViewTitle;
        TextView mTextViewSummer;
        TextView mTextViewStamp;


    }
}
