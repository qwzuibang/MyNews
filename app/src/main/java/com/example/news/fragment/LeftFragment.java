package com.example.news.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.news.R;
import com.example.news.adapter.LeftFragmentAdapter;
import com.example.news.control.MainActivity;
import com.example.news.model.entity.LeftMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 权威 on 2016/12/22.
 * 给左边策划栏设置的Fragment
 */

public class LeftFragment extends android.support.v4.app.Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sliding_left_fragment,null);
    }
    public  List<LeftMenu> mListLeftMenu=new ArrayList<LeftMenu>();

    public int[]icon={R.mipmap.biz_navigation_tab_news,R.mipmap.biz_navigation_tab_read,R.mipmap.biz_navigation_tab_local_news
    ,R.mipmap.biz_navigation_tab_ties,R.mipmap.biz_navigation_tab_pics};
   public String[] nameChinese={"新闻","收藏","本地","跟帖","图片"};
   public  String [] nameEnglish={"NEWS","FAVORRITE","LOCAL","COMMENT","PHOTO"};

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ListView mListLeftFragment= (ListView) getView().findViewById(R.id.list_sliding_left_fragment);
        initDate();
       LeftFragmentAdapter adapter= new LeftFragmentAdapter(getActivity(),mListLeftMenu);
        mListLeftFragment.setAdapter(adapter);
        mListLeftFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity activity= (MainActivity) getActivity();
            switch (i){
                case 0:
                    activity.addNews();
                    break;
                case  1:
                    activity.collectNews();
            }
            }
        });
    }
    //加载数据
    public  void initDate(){
        LeftMenu mLeftmenu;
        for (int i=0;i<icon.length;i++){
            mLeftmenu=new LeftMenu(nameChinese[i],nameEnglish[i],icon[i]);
            mListLeftMenu.add(mLeftmenu);
        }
    }
}
