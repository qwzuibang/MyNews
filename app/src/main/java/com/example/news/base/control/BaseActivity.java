package com.example.news.base.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.news.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void toActivity(Class cla){
        toActivity(cla,null);
    }
    public void toActivity(Class cla,Bundle bundle){
        Intent intent =new Intent(this,cla);
        if(bundle!=null){
            intent.putExtra("bundle",bundle);

        }
        startActivity(intent);
    }
}
