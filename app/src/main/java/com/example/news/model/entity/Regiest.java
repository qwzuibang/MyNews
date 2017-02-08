package com.example.news.model.entity;

/**
 * Created by 权威 on 2016/12/28.
 */

public class Regiest {

    /**
     * message : OK
     * status : 0
     * data : {"result":0,"token":"ea44b40c69503754f5fb9b0a0354a76e","explain":"登录成功"}
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
         * token : ea44b40c69503754f5fb9b0a0354a76e
         * explain : 登录成功
         */

        private int result;
        private String token;
        private String explain;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }
    }
}
