package com.example.news.control;

import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.news.R;
import com.example.news.adapter.LeadAdapter;
import com.example.news.base.control.BaseActivity;
import com.example.news.common.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class LeadActivity extends BaseActivity {

    protected List<ImageView>  mListImg=new ArrayList<ImageView>();
    protected  int[] icon={R.mipmap.welcome,R.mipmap.wy,R.mipmap.bd,R.mipmap.small};
    protected   ImageView[]  iconPoints=new ImageView[4];
    protected  ViewPager  mViewPage;
    protected SharedPreferences  mSharePreference;
    protected SharedPreferences.Editor  mEditor;
    protected Timer  mTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FristRegist();
        setContentView(R.layout.activity_lead);
        initData();
        getData();
        //创建适配器
        mViewPage.setAdapter(new LeadAdapter(mListImg));
        initEvent();

    }
    //初始化数据
    public void initData(){
        mViewPage= (ViewPager) this.findViewById(R.id.page_lead);
        iconPoints[0]= (ImageView) this.findViewById(R.id.mg_lead_point1);
        iconPoints[1]= (ImageView) this.findViewById(R.id.mg_lead_point2);
        iconPoints[2]= (ImageView) this.findViewById(R.id.mg_lead_point3);
        iconPoints[3]= (ImageView) this.findViewById(R.id.mg_lead_point4);
    }
    //获取数据
    public void getData(){
        for(int i=0;i<icon.length;i++){
            ImageView  mImg=new ImageView(this);
            mImg.setImageResource(icon[i]);
            mListImg.add(mImg);

        }
    }
    //设置页面改变的监听事件
    public void initEvent(){
        mViewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageSelected(int position) {
                if(position==mListImg.size()-1){
                    Log.e("TAg","====="+position);
                    mTimer=new Timer();
                    mTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                           toActivity(LogoActivity.class);
                            finish();
                            mTimer.cancel();
                        }
                    },1000);
                }
                for (int i=0;i<iconPoints.length;i++){
                    iconPoints[i].setImageResource(i==position?R.mipmap.adware_style_selected:R.mipmap.adware_style_default);
                }

            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
//设置第一次登录
  public void FristRegist(){
      //创建参数文件对象
      mSharePreference= this.getSharedPreferences(Constant.SHAAED_PERE_FRIST,CONTEXT_INCLUDE_CODE);
      //获取值
      boolean isFrist=mSharePreference.getBoolean("isFrist",true);
      //判断
    if(!isFrist){
        toActivity(LogoActivity.class);
        finish();
    }else {
        //为ture添加数据
     mEditor=mSharePreference.edit();
        //修改为false
     mEditor.putBoolean("isFrist",false);
        //提交
        mEditor.commit();
    }
  }

}
