package com.example.news.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by 权威 on 2016/12/26.
 */

public class LeadAdapter extends PagerAdapter{
  protected List<ImageView> mListImgView;
    public LeadAdapter(List<ImageView> listImgView) {
        this.mListImgView=listImgView;
    }

    @Override
    public int getCount() {
        return mListImgView!=null?mListImgView.size():0;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mListImgView.get(position));
        return mListImgView.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView(mListImgView.get(position));
    }
}
