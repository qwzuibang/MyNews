package com.example.news.control;
/**
 * 跟帖界面
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.R;
import com.example.news.adapter.CommentShowAdapter;
import com.example.news.base.control.BaseActivity;
import com.example.news.common.Constant;
import com.example.news.model.entity.CommentIssure;
import com.example.news.model.entity.CommentShow;
import com.example.news.presenter.NewsPresenter;
import com.example.news.view.XListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InavtationActivity extends BaseActivity {

    @Bind(R.id.img_invatation_back)
    ImageView imgInvatationBack;
    @Bind(R.id.list_invatation)
    XListView listInvatation;
    @Bind(R.id.edt_comment_invatation)
    EditText edtCommentInvatation;
    @Bind(R.id.img_invatation_send)
    ImageView imgInvatationSend;

    String nid;
    String pathCommontShow;
    String pathCommontIssue;
    List<CommentShow.DataBean> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inavtation);
        ButterKnife.bind(this);
        Intent intent=this.getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        nid=bundle.getString("nid");
       StringBuffer buf=new StringBuffer();
        pathCommontShow=buf.append(Constant.COMMENT_SHOW).append(nid).append("&").append("type").
                append("=").append("1").append("&").append("stamp").
                append("=").append("20160606").append("&").append("cid").
                append("=").append("1").append("&").append("dir").
                append("=").append("1").append("&").append("cnt").
                append("=").append("20").append("&").toString();
        NewsPresenter newsPresenter=new NewsPresenter(pathCommontShow, this, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson=new Gson();
             CommentShow commentShow=gson.fromJson(jsonObject.toString(),new TypeToken<CommentShow>(){}.getType());
            data=commentShow.getData();
             //创建适配器
             listInvatation.setAdapter(new CommentShowAdapter(InavtationActivity.this,data));
                refrenshAndLoad();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(InavtationActivity.this, "Net Error", Toast.LENGTH_SHORT).show();
            }
        });
      newsPresenter.requestHttp();
    }

    @OnClick({R.id.img_invatation_back, R.id.img_invatation_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_invatation_back:
                toActivity(MainActivity.class);
                break;
            case R.id.img_invatation_send:
                commentIssure();

                break;
        }
    }



    //上拉加载和下拉刷新
public void refrenshAndLoad(){
    //允许下拉加载
    listInvatation.setPullLoadEnable(true);
    //格式化时间
    SimpleDateFormat date=new SimpleDateFormat("yyyyMMddHHmmss");
    //设置系统时间
    listInvatation.setRefreshTime(date.format(System.currentTimeMillis()));
    //刷新加载的监听事件
    listInvatation.setXListViewListener(new XListView.IXListViewListener() {
        @Override
        public void onRefresh() {
            //停止刷新
           listInvatation.stopRefresh();
        }

        @Override
        public void onLoadMore() {
          //停止加载
            listInvatation.stopLoadMore();
        }
    });
}
    //发送评论
    public void commentIssure(){
        StringBuffer sbuf=new StringBuffer();
        //cmt_commit?ver=版本号&nid=新闻编号&token=用户令牌&imei=手机标识符&ctx=评论内容
        String token=this.getSharedPreferences(Constant.LOGO_tOKEN,
                Context.CONTEXT_INCLUDE_CODE).getString(Constant.LOGO_tOKEN_KEY,null);
    pathCommontIssue= sbuf.append(Constant.COMMENT_ISSUE).append(nid).append("&").
              append("token").append("=").append(token).append("&").
              append("imei").append("=").append("111").append("&").
              append("ctx").append("=").append(edtCommentInvatation.getText()).toString();
    Log.e("lujing----------",pathCommontIssue);
        if(token!=null){
        NewsPresenter presenter=new NewsPresenter(pathCommontIssue, this, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
             Gson gson=new Gson();
               CommentIssure  commentIssure = gson.fromJson(jsonObject.toString(), new TypeToken<CommentIssure>() {
                }.getType());
                if(commentIssure.getMessage().equals("OK")){
                    Toast.makeText(InavtationActivity.this,commentIssure.getData().getExplain(), Toast.LENGTH_SHORT).show();
                    edtCommentInvatation.setText("");
                }
                else{
                    Toast.makeText(InavtationActivity.this, "发送失败,"+commentIssure.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(InavtationActivity.this, "Net Error", Toast.LENGTH_SHORT).show();
            }
        });
            presenter.requestHttp();
    }else{
            Toast.makeText(this, "登录后参与评论", Toast.LENGTH_SHORT).show();
        }
    }


    }

