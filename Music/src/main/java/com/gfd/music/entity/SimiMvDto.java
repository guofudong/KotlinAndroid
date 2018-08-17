package com.gfd.music.entity;

import java.util.List;

/**
 * @Author : 郭富东
 * @Date ：2018/8/17 - 17:09
 * @Email：878749089@qq.com
 * @descriptio：
 */
public class SimiMvDto {


    /**
     * mvs : [{"id":10732128,"cover":"http://p3.music.126.net/G-nWoC063QDgg5699Bjanw==/109951163398869994.jpg","name":"隔壁泰山","playCount":3938459,"briefDesc":"","desc":null,"artistName":"阿里郎","artistId":11015,"duration":270000,"mark":0,"artists":[{"id":11015,"name":"阿里郎","alias":[],"transNames":null}],"alg":"itembased"},{"id":5955052,"cover":"http://p3.music.126.net/vtT1NtbXufK1FCTDGZ8MiA==/109951163368181315.jpg","name":"Wait A Minute","playCount":8262506,"briefDesc":"乐华七子NEXT首张音乐专辑主打歌","desc":null,"artistName":"乐华七子NEXT","artistId":15021290,"duration":221000,"mark":0,"artists":[{"id":15021290,"name":"乐华七子NEXT","alias":[],"transNames":null}],"alg":"itembased"},{"id":5958049,"cover":"http://p3.music.126.net/XkihyCylv229feA2aj6caA==/109951163366586966.jpg","name":"学猫叫","playCount":3949865,"briefDesc":"","desc":null,"artistName":"小潘潘","artistId":12532848,"duration":214000,"mark":0,"artists":[{"id":12532848,"name":"小潘潘","alias":[],"transNames":null},{"id":790044,"name":"小峰峰","alias":[],"transNames":null}],"alg":"itembased"},{"id":5955050,"cover":"http://p4.music.126.net/DtzaBqfdfiqSlKYx_UeUKA==/109951163367275793.jpg","name":"解码游戏","playCount":958188,"briefDesc":null,"desc":null,"artistName":"陈立农","artistId":13056440,"duration":110000,"mark":0,"artists":[{"id":13056440,"name":"陈立农","alias":[],"transNames":null}],"alg":"itembased"},{"id":10733104,"cover":"http://p4.music.126.net/76k07sQTAnDsVIn0r93BMA==/109951163397562711.jpg","name":"For You","playCount":995516,"briefDesc":"电视剧《流星花园》片头曲","desc":null,"artistName":"王鹤棣","artistId":12462214,"duration":224000,"mark":0,"artists":[{"id":12462214,"name":"王鹤棣","alias":[],"transNames":null},{"id":27889345,"name":"官鸿","alias":[],"transNames":null},{"id":27695878,"name":"梁靖康","alias":[],"transNames":null},{"id":27888364,"name":"吴希泽","alias":[],"transNames":null}],"alg":"itembased"}]
     * code : 200
     */

    public int code;
    public List<MvsBean> mvs;

    public static class MvsBean {
        /**
         * id : 10732128
         * cover : http://p3.music.126.net/G-nWoC063QDgg5699Bjanw==/109951163398869994.jpg
         * name : 隔壁泰山
         * playCount : 3938459
         * briefDesc :
         * desc : null
         * artistName : 阿里郎
         * artistId : 11015
         * duration : 270000
         * mark : 0
         * artists : [{"id":11015,"name":"阿里郎","alias":[],"transNames":null}]
         * alg : itembased
         */

        public int id;
        public String cover;
        public String name;
        public int playCount;
        public String briefDesc;
        public Object desc;
        public String artistName;
        public int artistId;
        public int duration;
        public int mark;
        public String alg;
        public List<ArtistsBean> artists;

        public static class ArtistsBean {
            /**
             * id : 11015
             * name : 阿里郎
             * alias : []
             * transNames : null
             */

            public int id;
            public String name;
            public Object transNames;
        }
    }
}
