package com.example.news.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.base.adapter.MyBaseAdapter;
import com.example.news.model.entity.CommentShow;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 权威 on 2017/1/4.
 * 评论列表
 *
 */

public class CommentShowAdapter extends MyBaseAdapter<CommentShow.DataBean> {
    public CommentShowAdapter(Context context, List<CommentShow.DataBean> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View view) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.commont_show_xlist, null);
       holder=new ViewHolder(view);
            view.setTag(holder);
        }
        else {
            holder= (ViewHolder) view.getTag();
        }
        CommentShow.DataBean dataBean=mData.get(position);
        RequestCreator requestCreator = Picasso.with(mContext).load(dataBean.getPortrait());
        requestCreator.resize(50,50);
        requestCreator.into(holder.imgCommentShowPhoto);
        holder.textCommentShowName.setText(dataBean.getUid());
        holder.textCommentShowContent.setText(dataBean.getContent());
        holder.textCommentShowStamp.setText(dataBean.getStamp());
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.img_comment_show_photo)
        ImageView imgCommentShowPhoto;
        @Bind(R.id.text_comment_show_name)
        TextView textCommentShowName;
        @Bind(R.id.text_comment_show_stamp)
        TextView textCommentShowStamp;
        @Bind(R.id.text_comment_show_content)
        TextView textCommentShowContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
