package com.gfd.music.entity;

/**
 * @Author : 郭富东
 * @Date ：2018/8/15 - 15:41
 * @Email：878749089@qq.com
 * @descriptio：
 */
public class MvDetail {

    /**
     * result : SUCCESS
     * code : 200
     * data : {"id":"5965802","name":"年少有为","singer":"李荣浩","pic":"http://p1.music.126.net/uOFdkh9GkJkYu6wiI5EWDg==/109951163456577100.jpg?param=400y400","url":"https://api.bzqll.com/music/netease/mvUrl?id=5965802&key=579621905","desc":"年少有为描写一段平凡爱情里的深刻印记，男人面对情感的压抑和撕心裂肺 像是每个男人都有过的曾经　每个世代都有的情感经历，最伟大的爱情不是永远　 而是曾经在平凡生活里有你有我的那些深刻 ","playCount":"10478875","publishTime":"2018-08-13"}
     */

    private String result;
    private int code;
    private DataBean data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5965802
         * name : 年少有为
         * singer : 李荣浩
         * pic : http://p1.music.126.net/uOFdkh9GkJkYu6wiI5EWDg==/109951163456577100.jpg?param=400y400
         * url : https://api.bzqll.com/music/netease/mvUrl?id=5965802&key=579621905
         * desc : 年少有为描写一段平凡爱情里的深刻印记，男人面对情感的压抑和撕心裂肺 像是每个男人都有过的曾经　每个世代都有的情感经历，最伟大的爱情不是永远　 而是曾经在平凡生活里有你有我的那些深刻
         * playCount : 10478875
         * publishTime : 2018-08-13
         */

        private String id;
        private String name;
        private String singer;
        private String pic;
        private String url;
        private String desc;
        private String playCount;
        private String publishTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSinger() {
            return singer;
        }

        public void setSinger(String singer) {
            this.singer = singer;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPlayCount() {
            return playCount;
        }

        public void setPlayCount(String playCount) {
            this.playCount = playCount;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }
    }
}
