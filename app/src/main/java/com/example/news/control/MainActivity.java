package com.example.news.control;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;


import com.example.news.R;
import com.example.news.fragment.CollectFragment;
import com.example.news.fragment.EnrallFargment;
import com.example.news.fragment.FoundPwdFragment;
import com.example.news.fragment.LeftFragment;
import com.example.news.fragment.NewsFragment;
import com.example.news.fragment.RegiestFragment;
import com.example.news.fragment.RightFargment;

import edu.zx.slidingmenu.SlidingMenu;


public class MainActivity extends FragmentActivity {

    public SlidingMenu slidMenu;
    protected  NewsFragment newsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //添加新闻列表
         newsFragment=new NewsFragment();
        this.getSupportFragmentManager().beginTransaction().add(R.id.activity_main, newsFragment).commit();

        initSlidingMenu();
    }

    //侧滑
    public void initSlidingMenu(){
         slidMenu=new SlidingMenu(this);
        slidMenu.attachToActivity(MainActivity.this,SlidingMenu.SLIDING_CONTENT);
        //设置左边菜单
        slidMenu.setMenu(R.layout.sliading_left);
        FragmentManager fra=this.getSupportFragmentManager();
        fra.beginTransaction().add(R.id.sliding_left,new LeftFragment()).commit();
       slidMenu.setBehindOffset(150);
       slidMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置右边菜单
        slidMenu.setSecondaryMenu(R.layout.sliding_right);

        fra.beginTransaction().add(R.id.sliding_right,new RightFargment()).commit();
        slidMenu.setMode(SlidingMenu.LEFT_RIGHT);
    }
    //设置返回
    @Override
    public void onBackPressed() {
        if(slidMenu.isMenuShowing()){
                slidMenu.showContent();

    }
        else{
            super.onBackPressed();
        }
    }
    //登录页面
    public void regesit(){
        this.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main,
                new RegiestFragment()).commit();
                  slidMenu.showContent();
    }
     //注册界面
    public void enroll(){
        this.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main,
                new EnrallFargment()).commit();

    }
     //找回密码界面
    public void foundPwd(){
        this.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main,new
                FoundPwdFragment()).commit();

    }

    //添加新闻列表
    public void addNews(){
        this.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main,newsFragment).commit();
        slidMenu.showContent();
    }
    //添加收藏页面
  public void collectNews(){
      this.getSupportFragmentManager().beginTransaction().
              replace(R.id.activity_main,new CollectFragment()).commit();
               slidMenu.showContent();
  }
}
