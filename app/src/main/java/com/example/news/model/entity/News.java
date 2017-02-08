package com.example.news.model.entity;

import java.util.List;

/**
 * Created by 权威 on 2016/12/22.
 */

public class News <T>{
   public  String message;
    public int  status;
    public T data;

    @Override
    public String toString() {
        return "News{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}
