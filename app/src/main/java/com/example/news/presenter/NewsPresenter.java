package com.example.news.presenter;

import android.content.Context;

import com.android.volley.Response;
import com.example.news.common.Constant;
import com.example.news.model.net.NewsNet;

import org.json.JSONObject;

/**
 * Created by 权威 on 2016/12/22.
 */

public class NewsPresenter {
    NewsNet mNewsNet;
    Context mContext;
    String mPath;
    Response.Listener<JSONObject>  mListener;
    Response.ErrorListener mErrorListener;
    public NewsPresenter(String path, Context context,Response.Listener<JSONObject>  listener, Response.ErrorListener errorListener) {
        mNewsNet=new NewsNet();
        this.mContext=context;
        this.mListener=listener;
        this.mPath=path;
        this.mErrorListener=errorListener;
    }
    public void requestHttp(){
        mNewsNet.requestNetForGet(mPath,mContext,mListener,mErrorListener);

    }
}
