package com.gfd.music.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @Author : 郭富东
 * @Date ：2018/8/15 - 15:41
 * @Email：878749089@qq.com
 * @descriptio：
 */
public class MvDetailDto {

    /**
     * loadingPic :
     * bufferPic :
     * loadingPicFS :
     * bufferPicFS :
     * subed : false
     * data : {"id":5965036,"name":"卡路里","artistId":27693474,"artistName":"火箭少女101","briefDesc":"火箭少女101献唱电影《西虹市首富》插曲","desc":"MV采用了画中画的剪辑手法，表现一位边吃着爆米花、炸鸡边看电视的\u201c胖妹\u201d，因为一档\u201c西虹人瘦\u201d电视节目洗心革面励志减肥的故事。复古的色调、超写实的歌词搭配魔性搞怪的曲风，让沉迷美食的胖妹都毅然决然地丢掉了手中的汉堡、三明治，加入减肥大军当中","cover":"http://p1.music.126.net/FuznAWoLFsX7TTBoQl4CLQ==/109951163425874499.jpg","coverId":109951163425874500,"playCount":8427762,"subCount":52163,"shareCount":19787,"likeCount":22995,"commentCount":7228,"duration":170000,"nType":0,"publishTime":"2018-07-26","brs":{"240":"http://vodkgeyttp8.vod.126.net/cloudmusic/2428/mv/f9fc/930e8886608bedac4b728fe5610f95db.mp4?wsSecret=e27893ac29eca6dcab002bc572f47e04&wsTime=1534318805","480":"http://vodkgeyttp8.vod.126.net/cloudmusic/2428/mv/f9fc/48982d8ea830f8f20d6e7a1a925d1147.mp4?wsSecret=f118b7e653365b7d96c8a7ce25d70f64&wsTime=1534318805","720":"http://vodkgeyttp8.vod.126.net/cloudmusic/2428/mv/f9fc/72f051eec3b1afbdc8efb7e08c942752.mp4?wsSecret=1935d8cd63f444e56cbfcd4d589b23d5&wsTime=1534318805","1080":"http://vodkgeyttp8.vod.126.net/cloudmusic/2428/mv/f9fc/19797ae5a5ec9849a0e48a019ec985cc.mp4?wsSecret=60350861a04b35d7c488c985c056138b&wsTime=1534318805"},"artists":[{"id":27693474,"name":"火箭少女101"}],"isReward":false,"commentThreadId":"R_MV_5_5965036"}
     * code : 200
     */

    public String loadingPic;
    public String bufferPic;
    public String loadingPicFS;
    public String bufferPicFS;
    public boolean subed;
    public DataBean data;
    public int code;

    public static class DataBean {
        /**
         * id : 5965036
         * name : 卡路里
         * artistId : 27693474
         * artistName : 火箭少女101
         * briefDesc : 火箭少女101献唱电影《西虹市首富》插曲
         * desc : MV采用了画中画的剪辑手法，表现一位边吃着爆米花、炸鸡边看电视的“胖妹”，因为一档“西虹人瘦”电视节目洗心革面励志减肥的故事。复古的色调、超写实的歌词搭配魔性搞怪的曲风，让沉迷美食的胖妹都毅然决然地丢掉了手中的汉堡、三明治，加入减肥大军当中
         * cover : http://p1.music.126.net/FuznAWoLFsX7TTBoQl4CLQ==/109951163425874499.jpg
         * coverId : 109951163425874500
         * playCount : 8427762
         * subCount : 52163
         * shareCount : 19787
         * likeCount : 22995
         * commentCount : 7228
         * duration : 170000
         * nType : 0
         * publishTime : 2018-07-26
         * brs : {"240":"http://vodkgeyttp8.vod.126.net/cloudmusic/2428/mv/f9fc/930e8886608bedac4b728fe5610f95db.mp4?wsSecret=e27893ac29eca6dcab002bc572f47e04&wsTime=1534318805","480":"http://vodkgeyttp8.vod.126.net/cloudmusic/2428/mv/f9fc/48982d8ea830f8f20d6e7a1a925d1147.mp4?wsSecret=f118b7e653365b7d96c8a7ce25d70f64&wsTime=1534318805","720":"http://vodkgeyttp8.vod.126.net/cloudmusic/2428/mv/f9fc/72f051eec3b1afbdc8efb7e08c942752.mp4?wsSecret=1935d8cd63f444e56cbfcd4d589b23d5&wsTime=1534318805","1080":"http://vodkgeyttp8.vod.126.net/cloudmusic/2428/mv/f9fc/19797ae5a5ec9849a0e48a019ec985cc.mp4?wsSecret=60350861a04b35d7c488c985c056138b&wsTime=1534318805"}
         * artists : [{"id":27693474,"name":"火箭少女101"}]
         * isReward : false
         * commentThreadId : R_MV_5_5965036
         */

        public int id;
        public String name;
        public int artistId;
        public String artistName;
        public String briefDesc;
        public String desc;
        public String cover;
        public long coverId;
        public int playCount;
        public int subCount;
        public int shareCount;
        public int likeCount;
        public int commentCount;
        public long duration;
        public int nType;
        public String publishTime;
        public BrsBean brs;
        public boolean isReward;
        public String commentThreadId;
        public List<ArtistsBean> artists;

        public static class BrsBean {
            /**
             * 240 : http://vodkgeyttp8.vod.126.net/cloudmusic/2428/mv/f9fc/930e8886608bedac4b728fe5610f95db.mp4?wsSecret=e27893ac29eca6dcab002bc572f47e04&wsTime=1534318805
             * 480 : http://vodkgeyttp8.vod.126.net/cloudmusic/2428/mv/f9fc/48982d8ea830f8f20d6e7a1a925d1147.mp4?wsSecret=f118b7e653365b7d96c8a7ce25d70f64&wsTime=1534318805
             * 720 : http://vodkgeyttp8.vod.126.net/cloudmusic/2428/mv/f9fc/72f051eec3b1afbdc8efb7e08c942752.mp4?wsSecret=1935d8cd63f444e56cbfcd4d589b23d5&wsTime=1534318805
             * 1080 : http://vodkgeyttp8.vod.126.net/cloudmusic/2428/mv/f9fc/19797ae5a5ec9849a0e48a019ec985cc.mp4?wsSecret=60350861a04b35d7c488c985c056138b&wsTime=1534318805
             */

            @SerializedName("240")
            public String _$240;
            @SerializedName("480")
            public String _$480;
            @SerializedName("720")
            public String _$720;
            @SerializedName("1080")
            public String _$1080;
        }

        public static class ArtistsBean {
            /**
             * id : 27693474
             * name : 火箭少女101
             */

            public int id;
            public String name;
        }
    }
}
