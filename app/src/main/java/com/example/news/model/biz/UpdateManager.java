package com.example.news.model.biz;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.news.common.Constant;
import com.example.news.control.MainActivity;
import com.example.news.model.entity.NewVersition;

import com.example.news.presenter.NewsPresenter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

/**
 * Created by 权威 on 2017/1/5.
 * 软件更新的管理类
 */

public class UpdateManager {

    String  versition;
    int  presentVersion;
    String link;
    Context context;
public UpdateManager(Context context){
    this.context=context;
}

    /**
     *判断是否最新版本
     * @return
     */

    public  void isUpdate(){
        Log.e("path---------",Constant.NEWVERSITION);
        Log.e("context===========",""+context);
        //获取最新的版本号
        NewsPresenter pre=new NewsPresenter(Constant.NEWVERSITION,context,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        //成功响应
                    Gson gson=new Gson();
           NewVersition newVersition=gson.fromJson(jsonObject.toString(),new TypeToken<NewVersition>(){}.getType());
                        Log.e("newVersition---------","==="+jsonObject);
           versition= newVersition.getVersion();
                        Log.e("versition---------","====="+versition);
           link=newVersition.getLink();
             Log.e("link---------",link);

                        //获取当前的版本

                        String apkName=link.substring(link.lastIndexOf("/")+1);
                        try {
                            PackageInfo packageInfo= context.getPackageManager().getPackageInfo(context.getPackageName(),0);
                            presentVersion=packageInfo.versionCode;

                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }

                        //比较
                        if(Integer.parseInt(versition)>presentVersion){

                            showUpdateDialog();
                        }
                        else{

                            Toast.makeText(context, "已经是最新版本", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(context, "Net Error", Toast.LENGTH_SHORT).show();
                Log.e("volleyError---------",volleyError+"");
            }
        });
        pre.requestHttp();
    }
//下载apk
    public void downLoadApk(){

            DownloadManager downLoadmanagr= (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
     DownloadManager.Request request=new DownloadManager.Request(Uri.parse(link));
            //设置apk的存放位置

        String apkname=link.substring(link.lastIndexOf("/")+1);

        request.setDestinationInExternalFilesDir(context,null,"storage/sdcard/"+apkname);
     downLoadmanagr.enqueue(request);

        }


    /**
     *
     * 弹出对话框
     */

    public void showUpdateDialog(){
        final AlertDialog.Builder bulider=new AlertDialog.Builder(context);
        bulider.setTitle("发现新的版本");
        bulider.setMessage("是否更新");
        bulider.setItems(new String[]{"更新", "暂不更新"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                    //下载
                        downLoadApk();
                        break;
                    case 1:
                        AlertDialog dialog = bulider.show();
                        dialog.dismiss();
                        break;
                }
            }
        });
    }
}
