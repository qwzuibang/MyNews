package com.example.news.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 权威 on 2016/12/29.
 * 用户登录成功的共享参数类
 */

public class SharedPreferenceUser {
    String mSaveKey;
    protected SharedPreferences mSpf;
//设计单例模式
    private static SharedPreferenceUser mSharedPreferenceUser;
    public SharedPreferenceUser(Context context,String saveFile) {

        mSpf=context.getSharedPreferences(saveFile,Context.CONTEXT_INCLUDE_CODE);
    }
    public static SharedPreferenceUser getSharedPreference(Context context,String saveFile){
        if(mSharedPreferenceUser==null){
            synchronized (SharedPreferenceUser.class){
                if(mSharedPreferenceUser==null){
                    mSharedPreferenceUser=new SharedPreferenceUser(context,saveFile);
                }
            }
        }
        return  mSharedPreferenceUser;
    }

    public void saveLogoSharedpreference(String saveKey,String token){
            this.mSaveKey=saveKey;

        mSpf.edit().putString(mSaveKey,token).commit();
    }
    public String  getLogoSharedpreference(){

        return mSpf.getString(mSaveKey,null);
    }
    public void cleanLogoSharedpreference(){
        mSpf.edit().putString(mSaveKey,null).commit();
    }
}
