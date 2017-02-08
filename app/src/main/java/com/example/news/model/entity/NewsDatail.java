package com.example.news.model.entity;

/**
 * Created by 权威 on 2016/12/22.
 */

public class NewsDatail {
    public  String summary;
    public String icon;
    public String title;
    public String link;
    public String stamp;
    public String nid;

    public NewsDatail(String summary, String icon, String title, String stamp,String link) {
        this.summary = summary;
        this.icon = icon;
        this.title = title;
        this.stamp = stamp;
        this.link=link;
    }

    @Override
    public String toString() {
        return "NewsDatail{" +
                "summary='" + summary + '\'' +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", stamp='" + stamp + '\'' +
                ", nid='" + nid + '\'' +
                '}';
    }
}
