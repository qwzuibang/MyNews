package com.example.news.model.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONObject;

/**
 * Created by 权威 on 2016/12/22.
 */

public class NewsNet {
    public void requestNetForGet(String path, Context context, Response.Listener<JSONObject>
                                 listener, Response.ErrorListener errorListener){
       RequestQueue  request= Volley.newRequestQueue(context);
        JsonObjectRequest jOR=new JsonObjectRequest(Request.Method.GET,path,null,listener,errorListener);
        request.add(jOR);
    }
}
