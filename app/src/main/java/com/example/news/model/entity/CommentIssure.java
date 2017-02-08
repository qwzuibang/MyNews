package com.example.news.model.entity;

/**
 * Created by 权威 on 2017/1/4.
 */

public class CommentIssure {

    /**
     * message : OK
     * status : 0
     * data : {"result":0,"explain":"发布成功！"}
     */

    private String message;
    private int status;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * result : 0
         * explain : 发布成功！
         */

        private int result;
        private String explain;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }
    }
}
