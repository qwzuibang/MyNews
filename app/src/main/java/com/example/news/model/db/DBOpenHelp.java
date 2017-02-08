package com.example.news.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.news.common.Constant;

/**
 * Created by 权威 on 2017/1/3.
 * 创建数据库
 */

public class DBOpenHelp extends SQLiteOpenHelper
{
    public DBOpenHelp(Context context) {
        super(context, Constant.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL("create table "+Constant.TABLE_NAME+"("+Constant.TABLE_ID+
             " integer primary key autoincrement not null,"+Constant.TABLE_TITLE+
             " text,"+Constant.TABLE_CONTENT+" text,"+Constant.TABLE_ICON+
             " text,"+Constant.TABLE_STAMP+" text,"+Constant.TABLE_LINK+" text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
