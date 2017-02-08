package com.example.news.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.news.common.Constant;
import com.example.news.model.entity.NewsDatail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 权威 on 2017/1/3.
 * 数据库的操作类
 */

public class DBWrapper {
    protected DBOpenHelp mDBOpenHelper;
    protected SQLiteDatabase mDBDataBase;
    public DBWrapper(Context context) {
        mDBOpenHelper=new DBOpenHelp(context);
    }
   //插入数据
    public void inset(NewsDatail newsDatail){
        //获取数据库文件
        mDBDataBase=mDBOpenHelper.getWritableDatabase();
        //插入
        ContentValues value=new ContentValues();
        value.put(Constant.TABLE_ICON,newsDatail.icon);
        value.put(Constant.TABLE_CONTENT,newsDatail.summary);
        value.put(Constant.TABLE_TITLE,newsDatail.title);
        value.put(Constant.TABLE_STAMP,newsDatail.stamp);
        value.put(Constant.TABLE_LINK,newsDatail.link);
        mDBDataBase.insert(Constant.TABLE_NAME,null,value);
        //关闭
        mDBDataBase.close();
    }
//查询所有数据
    public List<NewsDatail> select(){
        mDBDataBase=mDBOpenHelper.getReadableDatabase();
        List<NewsDatail> newsDatails=new ArrayList<NewsDatail>();
        Cursor cursor = mDBDataBase.query(Constant.TABLE_NAME, null, null, null, null,null,null);
       while(cursor.moveToNext()){
           NewsDatail newsDatail=new NewsDatail(cursor.getString(cursor.getColumnIndex(Constant.TABLE_CONTENT)),
                   cursor.getString(cursor.getColumnIndex(Constant.TABLE_ICON)),
                   cursor.getString(cursor.getColumnIndex(Constant.TABLE_TITLE)),
                   cursor.getString(cursor.getColumnIndex(Constant.TABLE_STAMP)),
                   cursor.getString(cursor.getColumnIndex(Constant.TABLE_LINK)));
           newsDatails.add(newsDatail);

       }
        mDBDataBase.close();
        return newsDatails;
    }

}
