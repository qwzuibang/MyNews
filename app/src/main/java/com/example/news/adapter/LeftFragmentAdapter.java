package com.example.news.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.base.adapter.MyBaseAdapter;
import com.example.news.model.entity.LeftMenu;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 权威 on 2016/12/23.
 */

public class LeftFragmentAdapter  extends MyBaseAdapter<LeftMenu> {
    public LeftFragmentAdapter(Context context, List<LeftMenu> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View view) {
        ViewHolder holder;
         if(view==null){
             view=mInflater.inflate(R.layout.list_item_left_fragment,null);
              holder=new ViewHolder(view);
             view.setTag(holder);
}  else{
             holder= (ViewHolder) view.getTag();
         }
        LeftMenu leftMenu= mData.get(position);
        holder.mImg.setImageResource(leftMenu.leftMenuIcon);
        holder.mNameChinese.setText(leftMenu.chineseName);
        holder.mNameEnglish.setText(leftMenu.englishName);
        return view;
    }
    class ViewHolder{
        //绑定
        @Bind(R.id.img_slide_left_fragment_item)
        ImageView mImg;
        @Bind(R.id.text_slide_lift_china_name)
        TextView mNameChinese;
        @Bind(R.id.text_slide_lift_english_name)
        TextView mNameEnglish;

        public ViewHolder(View itemView) {
            //初始化
            ButterKnife.bind(this,itemView);
        }
    }
}
