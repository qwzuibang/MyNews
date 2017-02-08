package com.example.news.common;

/**
 * Created by 权威 on 2016/12/22.
 */

public class Constant {
    public static  final  String SHAAED_PERE_FRIST="frist";
    public static  final String BASE_PATH="http://118.244.212.82:9092/newsClient/";
    public  static  final String NEWS_LIST_PATH=BASE_PATH+"news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
    public static final String ENROLL_PATH=BASE_PATH+"user_register?ver=1&";
    public static final String REGEIST_PATH=BASE_PATH+"user_login?ver=1&";
    public static final String USER_CENTER_PATH=BASE_PATH+"user_home?ver=1&imei=111&";
    //用户中心的共享参数手机令牌保存文件名
    public static final  String LOGO_tOKEN="logon";
    //共享参数手机令牌对应的键
    public static final  String LOGO_tOKEN_KEY="token";
    //头像和用户名保存文件
    public static  final String USER_LOGO_MESSAGE_NAME="message_name";
    public static  final String USER_LOGO_MESSAGE_PHOTO="message_photo";
    //头像和用户名对应的键
    public static  final String USER_NAME_KEY="name";
    public static  final String USER_PHOTO_KEY="portrait";
    /** 数据库名*/
    public static  final String DB_NAME="collect.db";
    /**表名*/
    public static  final String TABLE_NAME="collect";
    /**表的ID*/
    public static  final String TABLE_ID="_id";

    /**图标列*/
    public static  final String TABLE_ICON="icon";
    /**标题列*/
    public static  final String TABLE_TITLE="title";
    /**内容列*/
    public static  final String TABLE_CONTENT="content";
    /**时间列*/
    public static  final String TABLE_STAMP="stamp";
   //网页列
    public static  final String TABLE_LINK="link";
    //发布评论请求路径
    public static  final String COMMENT_ISSUE=BASE_PATH+"cmt_commit?ver=1&nid=";
    //显示评论路径
    public static  final String COMMENT_SHOW=BASE_PATH+"cmt_list?ver=1&nid=";
    //评论数量路径
    public static  final String COMMENT_COUNT=BASE_PATH+"cmt_num?ver=1&nid=";
//最新版本的路径
public static final  String  NEWVERSITION=BASE_PATH+"update?imei=1&pkg=1&ver=1";
}