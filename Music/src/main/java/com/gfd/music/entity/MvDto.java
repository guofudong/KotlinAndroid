package com.gfd.music.entity;

import java.util.List;

/**
 * @Author : 郭富东
 * @Date ：2018/8/14 - 17:37
 * @Email：878749089@qq.com
 * @descriptio：
 */
public class MvDto {

    /**
     * result : SUCCESS
     * code : 200
     * data : [{"id":"10785876","name":"Jony J 2017-2018 纪录片","singer":"Jony J","desc":null,"playCount":830266,"pic":"http://p1.music.126.net/2JId9NqmnKcxcIUE6jeDdQ==/109951163755935984.jpg?param=400y400","url":"https://api.bzqll.com/music/netease/mvUrl?id=10785876&key=579621905"},{"id":"10842099","name":"沙漠骆驼","singer":"展展与罗罗","desc":null,"playCount":835045,"pic":"http://p1.music.126.net/kw0dT3FWHKGL75HObSt59g==/109951163738936452.jpg?param=400y400","url":"https://api.bzqll.com/music/netease/mvUrl?id=10842099&key=579621905"},{"id":"10836120","name":"Back To You","singer":"乐华七子NEXT","desc":"乐华七子NEXT第二张音乐专辑主打歌MV  ","playCount":904816,"pic":"http://p1.music.126.net/A5cIq-jdw36o5IBNvzpHOw==/109951163731474559.jpg?param=400y400","url":"https://api.bzqll.com/music/netease/mvUrl?id=10836120&key=579621905"},{"id":"10841080","name":"起风了 Live版","singer":"买辣椒也用券","desc":null,"playCount":656743,"pic":"http://p1.music.126.net/dQ2Nb0IT-ViwffPN4_Gbsw==/109951163738076896.jpg?param=400y400","url":"https://api.bzqll.com/music/netease/mvUrl?id=10841080&key=579621905"},{"id":"10808802","name":"天亮以前说再见","singer":"何野","desc":null,"playCount":869796,"pic":"http://p1.music.126.net/Jtl0X0DIz7yhfO-w9vIGLg==/109951163670043745.jpg?param=400y400","url":"https://api.bzqll.com/music/netease/mvUrl?id=10808802&key=579621905"},{"id":"10808948","name":"倾城时光","singer":"金瀚","desc":"电视剧《你和我的倾城时光》浪漫主题曲","playCount":1380283,"pic":"http://p1.music.126.net/9oFSHhnMKVZgi2yxRR38Hw==/109951163689387898.jpg?param=400y400","url":"https://api.bzqll.com/music/netease/mvUrl?id=10808948&key=579621905"},{"id":"10836133","name":"别再闹了","singer":"毛不易","desc":null,"playCount":429829,"pic":"http://p1.music.126.net/PTi5GkSuD8SxGlkh-h5LPQ==/109951163734934732.jpg?param=400y400","url":"https://api.bzqll.com/music/netease/mvUrl?id=10836133&key=579621905"},{"id":"10808053","name":"梦不落雨林","singer":"张艺兴","desc":null,"playCount":1323949,"pic":"http://p1.music.126.net/Fg9kmhLwhGiLqQSOwgikXw==/109951163611942598.jpg?param=400y400","url":"https://api.bzqll.com/music/netease/mvUrl?id=10808053&key=579621905"},{"id":"10836154","name":"28","singer":"Jony J","desc":null,"playCount":349673,"pic":"http://p1.music.126.net/SuX-IiWwoHfcbB3kC3oi3A==/109951163749957799.jpg?param=400y400","url":"https://api.bzqll.com/music/netease/mvUrl?id=10836154&key=579621905"},{"id":"10836089","name":"Higher Brothers_16 Hours","singer":"Higher Brothers","desc":null,"playCount":492205,"pic":"http://p1.music.126.net/n2Tm0yTXwwR6YVo9ecP4zA==/109951163711987668.jpg?param=400y400","url":"https://api.bzqll.com/music/netease/mvUrl?id=10836089&key=579621905"},{"id":"10785481","name":"DON'T TOUCH","singer":"鞠婧祎","desc":null,"playCount":205615,"pic":"http://p1.music.126.net/idWEASZmpYO4J29qoYx9Kw==/109951163725585938.jpg?param=400y400","url":"https://api.bzqll.com/music/netease/mvUrl?id=10785481&key=579621905"},{"id":"10842018","name":"电影《叶问外传：张天志》宣传曲《不甘》","singer":"马良","desc":null,"playCount":58923,"pic":"http://p1.music.126.net/YOLxOu2-BUWtu1HTc1RJgA==/109951163733748467.jpg?param=400y400","url":"https://api.bzqll.com/music/netease/mvUrl?id=10842018&key=579621905"}]
     */

    private String result;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 10785876
         * name : Jony J 2017-2018 纪录片
         * singer : Jony J
         * desc : null
         * playCount : 830266
         * pic : http://p1.music.126.net/2JId9NqmnKcxcIUE6jeDdQ==/109951163755935984.jpg?param=400y400
         * url : https://api.bzqll.com/music/netease/mvUrl?id=10785876&key=579621905
         */

        private String id;
        private String name;
        private String singer;
        private String desc;
        private int playCount;
        private String pic;
        private String url;

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

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
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
    }
}
