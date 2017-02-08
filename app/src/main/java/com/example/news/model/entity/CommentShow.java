package com.example.news.model.entity;

import java.util.List;

/**
 * Created by 权威 on 2017/1/4.
 */

public class CommentShow {

    /**
     * message : OK
     * status : 0
     * data : [{"uid":"123","content":"sdf","stamp":"2017-01-04 09:22:02","cid":8481,"portrait":"http://118.244.212.82:9092/Images/20161126103809.jpg"},{"uid":"123","content":"??","stamp":"2017-01-04 09:21:37","cid":8480,"portrait":"http://118.244.212.82:9092/Images/20161126103809.jpg"},{"uid":"123","content":"??","stamp":"2017-01-04 09:20:57","cid":8479,"portrait":"http://118.244.212.82:9092/Images/20161126103809.jpg"},{"uid":"123","content":"??","stamp":"2017-01-04 09:20:56","cid":8478,"portrait":"http://118.244.212.82:9092/Images/20161126103809.jpg"},{"uid":"lyz","content":"???","stamp":"2017-01-04 09:16:52","cid":8473,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"lyz","content":"2","stamp":"2017-01-04 09:16:18","cid":8472,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"123","content":"111","stamp":"2017-01-04 09:15:17","cid":8471,"portrait":"http://118.244.212.82:9092/Images/20161126103809.jpg"},{"uid":"123","content":"???","stamp":"2017-01-04 09:15:03","cid":8470,"portrait":"http://118.244.212.82:9092/Images/20161126103809.jpg"},{"uid":"123","content":"2134","stamp":"2017-01-04 09:14:03","cid":8469,"portrait":"http://118.244.212.82:9092/Images/20161126103809.jpg"},{"uid":"123","content":"44","stamp":"2017-01-04 09:10:57","cid":8468,"portrait":"http://118.244.212.82:9092/Images/20161126103809.jpg"},{"uid":"123","content":"22","stamp":"2017-01-04 09:10:51","cid":8467,"portrait":"http://118.244.212.82:9092/Images/20161126103809.jpg"},{"uid":"123","content":"22","stamp":"2017-01-04 09:06:02","cid":8466,"portrait":"http://118.244.212.82:9092/Images/20161126103809.jpg"},{"uid":"wdd","content":"ha","stamp":"2017-01-03 17:35:08","cid":8450,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"wdd","content":"ha","stamp":"2017-01-03 17:35:05","cid":8449,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"wdd","content":"hei","stamp":"2017-01-03 17:35:01","cid":8448,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"1804866","content":"1","stamp":"2017-01-03 16:05:55","cid":8445,"portrait":"http://118.244.212.82:9092/Images/image.png"},{"uid":"123","content":"123","stamp":"2017-01-03 10:50:01","cid":8437,"portrait":"http://118.244.212.82:9092/Images/20161126103809.jpg"},{"uid":"123","content":"15","stamp":"2017-01-03 10:26:07","cid":8436,"portrait":"http://118.244.212.82:9092/Images/20161126103809.jpg"},{"uid":"123","content":"123","stamp":"2017-01-03 10:25:48","cid":8435,"portrait":"http://118.244.212.82:9092/Images/20161126103809.jpg"},{"uid":"123","content":"12","stamp":"2017-01-03 10:20:05","cid":8434,"portrait":"http://118.244.212.82:9092/Images/20161126103809.jpg"}]
     */

    private String message;
    private int status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 123
         * content : sdf
         * stamp : 2017-01-04 09:22:02
         * cid : 8481
         * portrait : http://118.244.212.82:9092/Images/20161126103809.jpg
         */

        private String uid;
        private String content;
        private String stamp;
        private int cid;
        private String portrait;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStamp() {
            return stamp;
        }

        public void setStamp(String stamp) {
            this.stamp = stamp;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }
    }
}
