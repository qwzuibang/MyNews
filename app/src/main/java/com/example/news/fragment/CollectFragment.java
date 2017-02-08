package com.example.news.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.news.R;
import com.example.news.adapter.CollectAdapter;
import com.example.news.control.MainActivity;
import com.example.news.control.NewsContentActivity;
import com.example.news.model.db.DBWrapper;
import com.example.news.model.entity.NewsDatail;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 权威 on 2017/1/3.
 */

public class CollectFragment extends Fragment {
    @Bind(R.id.img_collect_fragment_left)
    ImageView imgCollectFragmentLeft;
    @Bind(R.id.img_collect_fragment_right)
    ImageView imgCollectFragmentRight;
    @Bind(R.id.list_collect_fragment)
    ListView listCollectFragment;




    List<NewsDatail> newsDatails=new ArrayList<NewsDatail>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.collect_fragment, null);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //查询所有数据
        DBWrapper dbWrapper=new DBWrapper(getActivity());
        newsDatails=dbWrapper.select();
        CollectAdapter collectAdapter=new CollectAdapter(getActivity(),newsDatails);
        listCollectFragment.setAdapter(collectAdapter);
        listCollectFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(getActivity(), NewsContentActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("link",newsDatails.get(i).link);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.img_collect_fragment_left, R.id.img_collect_fragment_right})
    public void onClick(View view) {
        MainActivity activity= (MainActivity) getActivity();
        switch (view.getId()) {
            case R.id.img_collect_fragment_left:
                activity.slidMenu.showMenu();
                break;
            case R.id.img_collect_fragment_right:
                activity.slidMenu.showSecondaryMenu();
                break;
        }
    }
}
