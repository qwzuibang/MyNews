package com.example.news.model.entity;

/**
 * Created by 权威 on 2016/12/22.
 */

public class LeftMenu {
    public String chineseName;
    public String englishName;
    public int leftMenuIcon;

    public LeftMenu(String chineseName, String englishName, int leftMenuIcon) {
        this.chineseName = chineseName;
        this.englishName = englishName;
        this.leftMenuIcon = leftMenuIcon;
    }

    @Override
    public String toString() {
        return "LeftMenu{" +
                "chineseName='" + chineseName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", leftMenuIcon=" + leftMenuIcon +
                '}';
    }
}
