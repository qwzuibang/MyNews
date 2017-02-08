package com.example.news.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.base.adapter.MyBaseAdapter;
import com.example.news.model.entity.NewsDatail;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 权威 on 2017/1/3.
 */

public class CollectAdapter extends MyBaseAdapter<NewsDatail> {
    public CollectAdapter(Context context, List<NewsDatail> data) {

        super(context, data);
    }

    @Override
    public View getView(int position, View view) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.collect_fragment_item, null);
          holder=new ViewHolder(view);
            view.setTag(holder);
        }
        else{
            holder= (ViewHolder) view.getTag();
        }
        NewsDatail newsDatail=mData.get(position);
        Picasso picasso = Picasso.with(mContext);
        RequestCreator requestCreator = picasso.load(newsDatail.icon);
        requestCreator.resize(120,80);
        requestCreator.into(holder.imgCollectIcon);

        holder.txtCollectSummey.setText(newsDatail.summary);
        holder.txtCollectStamp.setText(newsDatail.stamp);
        holder.txtCollectTitle.setText(newsDatail.title);
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.img_collect_icon)
        ImageView imgCollectIcon;
        @Bind(R.id.txt_collect_title)
        TextView txtCollectTitle;
        @Bind(R.id.txt_collect_summey)
        TextView txtCollectSummey;
        @Bind(R.id.txt_collect_stamp)
        TextView txtCollectStamp;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
