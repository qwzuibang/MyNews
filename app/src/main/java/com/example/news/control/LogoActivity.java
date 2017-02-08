package com.example.news.control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.news.R;
import com.example.news.base.control.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;


public class LogoActivity extends BaseActivity {
    protected ImageView mImageView;
    protected Timer mTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        mImageView= (ImageView) this.findViewById(R.id.img_logo);
        mImageView.setImageResource(R.mipmap.logo_img);
        mTimer=new Timer();

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                toActivity(MainActivity.class);
                mTimer.cancel();
            }
        }, 2000);
    }
}
