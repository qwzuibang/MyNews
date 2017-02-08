package com.example.news.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.R;
import com.example.news.adapter.MainAdapter;
import com.example.news.common.Constant;
import com.example.news.control.MainActivity;
import com.example.news.control.NewsContentActivity;
import com.example.news.model.entity.News;
import com.example.news.model.entity.NewsDatail;
import com.example.news.presenter.NewsPresenter;
import com.example.news.view.XListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by 权威 on 2016/12/26.
 * 新闻列表
 */

public class NewsFragment extends Fragment {
    News<List<NewsDatail>> news;
    XListView mListMenu;
    @Bind(R.id.img_news_fragment_left)
    ImageView imgNewsFragmentLeft;
    @Bind(R.id.img_news_fragment_right)
    ImageView imgNewsFragmentRight;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
      mListMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            //携带网址跳转
             String link=news.data.get(i-1).link;
              Intent intent=new Intent(getActivity(), NewsContentActivity.class);
              Bundle bundle=new Bundle();
              bundle.putString("link",link);
              bundle.putString("icon",news.data.get(i-1).icon);
              bundle.putString("title",news.data.get(i-1).title);
              bundle.putString("summary",news.data.get(i-1).summary);
              bundle.putString("stamp",news.data.get(i-1).stamp);
              bundle.putString("nid",news.data.get(i-1).nid);
              intent.putExtras(bundle);
              startActivity(intent);
          }
      });

    }

    //初始化数据
    public void initData() {
        //绑定数据
        mListMenu = (XListView) getActivity().findViewById(R.id.list_news_fragment);

        //访问网络
        NewsPresenter newsPresenter = new NewsPresenter(Constant.NEWS_LIST_PATH,this.getActivity(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                news = gson.fromJson(jsonObject.toString(),
                        new TypeToken<News<List<NewsDatail>>>() {
                        }.getType());


               // Log.e("tag-------","tag====="+news.data.toString());
                //创建适配器
                MainAdapter mainAdapter = new MainAdapter(NewsFragment.this.getActivity(), news.data);
                mListMenu.setAdapter(mainAdapter);

                refreshAndLoad();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Tag", "error");
            }
        });
        //设置图片来源
        Picasso.with(getActivity()).setIndicatorsEnabled(true);
        newsPresenter.requestHttp();
    }

    //设置下拉刷新，上拉加载
    public void refreshAndLoad() {
        //允许上拉加载
        mListMenu.setPullLoadEnable(true);
        //设置时间格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //设置系统时间
        mListMenu.setRefreshTime(sdf.format(System.currentTimeMillis()));
        mListMenu.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                //停止刷新
                mListMenu.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                //停止加载
                mListMenu.stopLoadMore();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //设置点击事件
    @OnClick({R.id.img_news_fragment_left, R.id.img_news_fragment_right})
    public void onClick(View view) {
        MainActivity activity = (MainActivity) getActivity();
        switch (view.getId()) {

            case R.id.img_news_fragment_left:
                activity.slidMenu.showMenu();
                break;
            case R.id.img_news_fragment_right:
                activity.slidMenu.showSecondaryMenu();
                break;

        }
    }

}
