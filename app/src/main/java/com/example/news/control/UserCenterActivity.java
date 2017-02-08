package com.example.news.control;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.R;
import com.example.news.adapter.UserCenterAdapter;
import com.example.news.base.control.BaseActivity;
import com.example.news.common.Constant;
import com.example.news.common.SharedPreferenceUser;
import com.example.news.model.entity.UserCenter;
import com.example.news.presenter.NewsPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserCenterActivity extends BaseActivity {

    @Bind(R.id.img_user_back)
    ImageView imgUserBack;
    @Bind(R.id.img_user_center_fragment_photo)
    ImageView imgUserCenterFragmentPhoto;
    @Bind(R.id.text_user_name)
    TextView textUserName;
    @Bind(R.id.text_user_integral_name)
    TextView textUserIntegralName;
    @Bind(R.id.text_user_integral_num)
    TextView textUserIntegralNum;
    @Bind(R.id.text_user_invition_name)
    TextView textUserInvitionName;
    @Bind(R.id.text_user_invition_num)
    TextView textUserInvitionNum;
    @Bind(R.id.list_user_center)
    ListView listUserCenter;
    @Bind(R.id.btn_user_close_logo)
    Button btnUserCloseLogo;


   protected List<UserCenter.DataBean.LoginlogBean> loginlog=new ArrayList<UserCenter.DataBean.LoginlogBean>();
   protected UserCenter userCenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_center);
        ButterKnife.bind(this);
        StringBuffer buf=new StringBuffer();

        String token=this.getSharedPreferences(Constant.LOGO_tOKEN, Context.CONTEXT_INCLUDE_CODE).getString(Constant.LOGO_tOKEN_KEY,null);

        buf.append(Constant.USER_CENTER_PATH).append("token").append("=").append(token);

    //访问网络
        NewsPresenter presenter= new NewsPresenter(buf.toString(), this,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
              //成功响应
                        Gson gson=new Gson();
                     userCenter=gson.fromJson(jsonObject.toString(),new TypeToken<UserCenter>(){}.getType());
                if(userCenter.getMessage().equals("OK")){

                loginlog= userCenter.getData().getLoginlog();
                        initData();
                        listUserCenter.setAdapter(new UserCenterAdapter(UserCenterActivity.this,loginlog));}
                  else{
                    Toast.makeText(UserCenterActivity.this, "身份账号已过期，请重新登录", Toast.LENGTH_SHORT).show();
                    UserCenterActivity.this.getSharedPreferences(Constant.LOGO_tOKEN, Context.CONTEXT_INCLUDE_CODE).edit().putString(Constant.LOGO_tOKEN_KEY,null).commit();
                }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
           //响应失败
                Toast.makeText(UserCenterActivity.this, "Net Error", Toast.LENGTH_SHORT).show();
            }
        });
        presenter.requestHttp();
    }
//用户中心的点击事件
    @OnClick({R.id.img_user_back, R.id.img_user_center_fragment_photo, R.id.btn_user_close_logo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_user_back:
                toActivity(MainActivity.class);
                break;
            case R.id.img_user_center_fragment_photo:
                break;
            case R.id.btn_user_close_logo:
                this.getSharedPreferences(Constant.LOGO_tOKEN, Context.CONTEXT_INCLUDE_CODE).edit().putString(Constant.LOGO_tOKEN_KEY,null).commit();
              toActivity(MainActivity.class);
                break;}
    }
    //初始化数据
   public void initData(){
    String photoPath= userCenter.getData().getPortrait();
       //将图片的地址转成图片设置给imgView
       RequestCreator load = Picasso.with(this).load(photoPath);
         load.resize(120,120);
       load.into(imgUserCenterFragmentPhoto);
       textUserName.setText(userCenter.getData().getUid());
       textUserIntegralNum.setText(Integer.toString(userCenter.getData().getIntegration()));
       textUserInvitionNum.setText(Integer.toString(userCenter.getData().getComnum()));
       SharedPreferenceUser.getSharedPreference(this,Constant.USER_LOGO_MESSAGE_NAME).saveLogoSharedpreference(Constant.USER_NAME_KEY,userCenter.getData().getUid());
       SharedPreferenceUser.getSharedPreference(this,Constant.USER_LOGO_MESSAGE_PHOTO).saveLogoSharedpreference(Constant.USER_PHOTO_KEY,userCenter.getData().getPortrait());
   }

}
