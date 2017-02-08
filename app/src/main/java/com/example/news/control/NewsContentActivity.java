package com.example.news.control;

import android.content.Intent;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.R;

import com.example.news.base.control.BaseActivity;
import com.example.news.common.Constant;
import com.example.news.model.db.DBWrapper;
import com.example.news.model.entity.CommentNum;
import com.example.news.model.entity.NewsDatail;
import com.example.news.presenter.NewsPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsContentActivity extends BaseActivity {

    @Bind(R.id.img_news_contant_back)
    ImageView imgNewsContantBack;
    @Bind(R.id.img_news_contant_collect)
    ImageView imgNewsContantCollect;
    @Bind(R.id.web_news)
    WebView webNews;
    @Bind(R.id.text_num_invitation)
    TextView textViewInvitionNum;
    @Bind(R.id.text_name_invitation)
    TextView textViewInvitionName;

     String link;
     String summary;
     String icon;
     String title;
     String stamp;
     String nid;
    //评论的数目
    String pathOfGetNum;
     List<NewsDatail> newsDatails=new ArrayList<NewsDatail>();
    StringBuffer buf=new StringBuffer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);
        ButterKnife.bind(this);

        //获取网址并打开网址
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
        link=bundle.getString("link");
        title=bundle.getString("title");
        icon=bundle.getString("icon");
        stamp=bundle.getString("stamp");
        summary=bundle.getString("summary");
        nid=bundle.getString("nid");}

        //获取跟帖的数目
        pathOfGetNum=buf.append(Constant.COMMENT_COUNT).append(nid).toString();

        NewsPresenter  newsPresenter=new NewsPresenter(pathOfGetNum, this, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                //成功响应
                Gson gson=new Gson();
                CommentNum commmentNum= gson.fromJson(jsonObject.toString(),new TypeToken<CommentNum>(){}.getType());

                if(commmentNum.getMessage().equals("OK")){
                    textViewInvitionNum.setText(Integer.toString(commmentNum.getData()));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //响应失败
                Toast.makeText(NewsContentActivity.this, "Net Error", Toast.LENGTH_SHORT).show();
            }
        });
        newsPresenter.requestHttp();

       //打开连接
        WebSettings settings =  webNews.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        webNews.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webNews.loadUrl(link);


    }



    PopupWindow popupWindow;
    @OnClick({R.id.img_news_contant_back, R.id.img_news_contant_collect,R.id.text_num_invitation,R.id.text_name_invitation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_news_contant_back:
                toActivity(MainActivity.class);
                break;
            case R.id.img_news_contant_collect:
                //创建收藏的窗口
                TextView textView=new TextView(this);
                textView.setText("加入收藏");
                textView.setTextSize(18);

                textView.setBackgroundColor(getResources().getColor(R.color.colorGray));
                 popupWindow=new PopupWindow(textView,105,35);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        DBWrapper dbWrapper=new DBWrapper(NewsContentActivity.this);
                        newsDatails= dbWrapper.select();
                        for (int i=0;i<newsDatails.size();i++){
                            if(link.equals(newsDatails.get(i).link)) {
                                Toast.makeText(NewsContentActivity.this, "当前新闻已收藏", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        NewsDatail newsDatail=new NewsDatail(summary,icon,title,stamp,link);
                        //将数据添加到数据库
                        dbWrapper.inset(newsDatail);
                    }
                });
                Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
              //设置窗口的焦点及隐藏
                popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new PaintDrawable());
                //显示窗口
                popupWindow.showAsDropDown(view);
                break;

                case R.id.text_num_invitation:

                case R.id.text_name_invitation:
                Bundle bundle=new Bundle();
                bundle.putString("nid",nid);
                toActivity(InavtationActivity.class,bundle);

                break;
        }

    }
}
