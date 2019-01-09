package com.gfd.music.entity;

import java.util.List;

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 17:19
 * @Email：878749089@qq.com
 * @descriptio：
 */
public class Song {


    /**
     * error_code : 22000
     * content : [{"title":"歌曲推荐","song_list":[{"artist_id":"130","pic_big":"http://qukufile2.qianqian.com/data2/music/1805DC21398BAF2A5723BCC7956D8D15/252492173/252492173.jpg@s_0,w_150","pic_small":"http://qukufile2.qianqian.com/data2/music/1805DC21398BAF2A5723BCC7956D8D15/252492173/252492173.jpg@s_0,w_90","pic_premium":"http://qukufile2.qianqian.com/data2/music/1805DC21398BAF2A5723BCC7956D8D15/252492173/252492173.jpg@s_0,w_500","pic_huge":"http://qukufile2.qianqian.com/data2/music/1805DC21398BAF2A5723BCC7956D8D15/252492173/252492173.jpg","pic_singer":"","all_artist_ting_uid":"1100","file_duration":"273","del_status":"0","resource_type":"0","all_rate":"96,128,224,320,flac","toneid":"600902000005286912","copy_type":"0","has_mv_mobile":1,"song_id":"7313983","title":"喜欢你","ting_uid":"1100","author":"Beyond","album_id":"7311104","album_title":"传奇再续","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":1,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"1100000000","desc":"","url":"http://music.baidu.com/song/7313983","recommend_reason":"以往片刻欢笑仍挂在脸上"},{"artist_id":"90","pic_big":"http://qukufile2.qianqian.com/data2/pic/0748b2fb73c0be76039beb88869fa936/61643/61643.jpg@s_1,w_150,h_150","pic_small":"http://qukufile2.qianqian.com/data2/pic/0748b2fb73c0be76039beb88869fa936/61643/61643.jpg@s_1,w_90,h_90","pic_premium":"http://qukufile2.qianqian.com/data2/pic/0748b2fb73c0be76039beb88869fa936/61643/61643.jpg@s_1,w_500,h_500","pic_huge":"http://qukufile2.qianqian.com/data2/pic/0748b2fb73c0be76039beb88869fa936/61643/61643.jpg","pic_singer":"","all_artist_ting_uid":"1079","file_duration":"210","del_status":"0","resource_type":"0","all_rate":"96,128,224,320,flac","toneid":"600902000005510324","copy_type":"1","has_mv_mobile":1,"song_id":"305963","title":"停电","ting_uid":"1079","author":"金莎","album_id":"61643","album_title":"空气","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":1,"learn":1,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"2","mv_provider":"0100000000","desc":"","url":"http://music.baidu.com/song/305963","recommend_reason":"我害怕一个人累 一个人睡"},{"artist_id":"1413","pic_big":"http://qukufile2.qianqian.com/data2/music/028AB2B6109AC56CFC4AFE3A9FF2FB2B/255084406/255084406.jpg@s_1,w_150,h_150","pic_small":"http://qukufile2.qianqian.com/data2/music/028AB2B6109AC56CFC4AFE3A9FF2FB2B/255084406/255084406.jpg@s_1,w_90,h_90","pic_premium":"http://qukufile2.qianqian.com/data2/music/028AB2B6109AC56CFC4AFE3A9FF2FB2B/255084406/255084406.jpg@s_1,w_500,h_500","pic_huge":"http://qukufile2.qianqian.com/data2/music/028AB2B6109AC56CFC4AFE3A9FF2FB2B/255084406/255084406.jpg","pic_singer":"","all_artist_ting_uid":"1541","file_duration":"271","del_status":"0","resource_type":"0","all_rate":"96,128,224,320,flac","toneid":"0","copy_type":"1","has_mv_mobile":0,"song_id":"242427416","title":"从前的我","ting_uid":"1541","author":"陈洁仪","album_id":"242427415","album_title":"从前的我","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","desc":"","url":"http://music.baidu.com/song/242427416","recommend_reason":"你说你要离开 明天还会回来"},{"artist_id":"2237987","pic_big":"http://qukufile2.qianqian.com/data2/pic/e18b5d0931908d181b1c66bcdd794d6d/261812620/261812620.jpg@s_1,w_150,h_150","pic_small":"http://qukufile2.qianqian.com/data2/pic/e18b5d0931908d181b1c66bcdd794d6d/261812620/261812620.jpg@s_1,w_90,h_90","pic_premium":"http://qukufile2.qianqian.com/data2/pic/e18b5d0931908d181b1c66bcdd794d6d/261812620/261812620.jpg@s_1,w_500,h_500","pic_huge":"","pic_singer":"","all_artist_ting_uid":"218966","file_duration":"234","del_status":"0","resource_type":"0","all_rate":"96,128,224,320","toneid":"0","copy_type":"0","has_mv_mobile":0,"song_id":"261813046","title":"Ship To Wreck","ting_uid":"218966","author":"Florence + The Machine","album_id":"261812620","album_title":"2016 GRAMMY Nominees","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","desc":"","url":"http://music.baidu.com/song/261813046","recommend_reason":"How Big, How Blue, How Beautiful"}]}]
     */

    public int error_code;
    public List<ContentBean> content;

    public static class ContentBean {
        /**
         * title : 歌曲推荐
         * song_list : [{"artist_id":"130","pic_big":"http://qukufile2.qianqian.com/data2/music/1805DC21398BAF2A5723BCC7956D8D15/252492173/252492173.jpg@s_0,w_150","pic_small":"http://qukufile2.qianqian.com/data2/music/1805DC21398BAF2A5723BCC7956D8D15/252492173/252492173.jpg@s_0,w_90","pic_premium":"http://qukufile2.qianqian.com/data2/music/1805DC21398BAF2A5723BCC7956D8D15/252492173/252492173.jpg@s_0,w_500","pic_huge":"http://qukufile2.qianqian.com/data2/music/1805DC21398BAF2A5723BCC7956D8D15/252492173/252492173.jpg","pic_singer":"","all_artist_ting_uid":"1100","file_duration":"273","del_status":"0","resource_type":"0","all_rate":"96,128,224,320,flac","toneid":"600902000005286912","copy_type":"0","has_mv_mobile":1,"song_id":"7313983","title":"喜欢你","ting_uid":"1100","author":"Beyond","album_id":"7311104","album_title":"传奇再续","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":1,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"1100000000","desc":"","url":"http://music.baidu.com/song/7313983","recommend_reason":"以往片刻欢笑仍挂在脸上"},{"artist_id":"90","pic_big":"http://qukufile2.qianqian.com/data2/pic/0748b2fb73c0be76039beb88869fa936/61643/61643.jpg@s_1,w_150,h_150","pic_small":"http://qukufile2.qianqian.com/data2/pic/0748b2fb73c0be76039beb88869fa936/61643/61643.jpg@s_1,w_90,h_90","pic_premium":"http://qukufile2.qianqian.com/data2/pic/0748b2fb73c0be76039beb88869fa936/61643/61643.jpg@s_1,w_500,h_500","pic_huge":"http://qukufile2.qianqian.com/data2/pic/0748b2fb73c0be76039beb88869fa936/61643/61643.jpg","pic_singer":"","all_artist_ting_uid":"1079","file_duration":"210","del_status":"0","resource_type":"0","all_rate":"96,128,224,320,flac","toneid":"600902000005510324","copy_type":"1","has_mv_mobile":1,"song_id":"305963","title":"停电","ting_uid":"1079","author":"金莎","album_id":"61643","album_title":"空气","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":1,"learn":1,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"2","mv_provider":"0100000000","desc":"","url":"http://music.baidu.com/song/305963","recommend_reason":"我害怕一个人累 一个人睡"},{"artist_id":"1413","pic_big":"http://qukufile2.qianqian.com/data2/music/028AB2B6109AC56CFC4AFE3A9FF2FB2B/255084406/255084406.jpg@s_1,w_150,h_150","pic_small":"http://qukufile2.qianqian.com/data2/music/028AB2B6109AC56CFC4AFE3A9FF2FB2B/255084406/255084406.jpg@s_1,w_90,h_90","pic_premium":"http://qukufile2.qianqian.com/data2/music/028AB2B6109AC56CFC4AFE3A9FF2FB2B/255084406/255084406.jpg@s_1,w_500,h_500","pic_huge":"http://qukufile2.qianqian.com/data2/music/028AB2B6109AC56CFC4AFE3A9FF2FB2B/255084406/255084406.jpg","pic_singer":"","all_artist_ting_uid":"1541","file_duration":"271","del_status":"0","resource_type":"0","all_rate":"96,128,224,320,flac","toneid":"0","copy_type":"1","has_mv_mobile":0,"song_id":"242427416","title":"从前的我","ting_uid":"1541","author":"陈洁仪","album_id":"242427415","album_title":"从前的我","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","desc":"","url":"http://music.baidu.com/song/242427416","recommend_reason":"你说你要离开 明天还会回来"},{"artist_id":"2237987","pic_big":"http://qukufile2.qianqian.com/data2/pic/e18b5d0931908d181b1c66bcdd794d6d/261812620/261812620.jpg@s_1,w_150,h_150","pic_small":"http://qukufile2.qianqian.com/data2/pic/e18b5d0931908d181b1c66bcdd794d6d/261812620/261812620.jpg@s_1,w_90,h_90","pic_premium":"http://qukufile2.qianqian.com/data2/pic/e18b5d0931908d181b1c66bcdd794d6d/261812620/261812620.jpg@s_1,w_500,h_500","pic_huge":"","pic_singer":"","all_artist_ting_uid":"218966","file_duration":"234","del_status":"0","resource_type":"0","all_rate":"96,128,224,320","toneid":"0","copy_type":"0","has_mv_mobile":0,"song_id":"261813046","title":"Ship To Wreck","ting_uid":"218966","author":"Florence + The Machine","album_id":"261812620","album_title":"2016 GRAMMY Nominees","is_first_publish":0,"havehigh":2,"charge":0,"has_mv":0,"learn":0,"song_source":"web","piao_id":"0","korean_bb_song":"0","resource_type_ext":"0","mv_provider":"0000000000","desc":"","url":"http://music.baidu.com/song/261813046","recommend_reason":"How Big, How Blue, How Beautiful"}]
         */

        public String title;
        public List<SongListBean> song_list;

        public static class SongListBean {
            /**
             * artist_id : 130
             * pic_big : http://qukufile2.qianqian.com/data2/music/1805DC21398BAF2A5723BCC7956D8D15/252492173/252492173.jpg@s_0,w_150
             * pic_small : http://qukufile2.qianqian.com/data2/music/1805DC21398BAF2A5723BCC7956D8D15/252492173/252492173.jpg@s_0,w_90
             * pic_premium : http://qukufile2.qianqian.com/data2/music/1805DC21398BAF2A5723BCC7956D8D15/252492173/252492173.jpg@s_0,w_500
             * pic_huge : http://qukufile2.qianqian.com/data2/music/1805DC21398BAF2A5723BCC7956D8D15/252492173/252492173.jpg
             * pic_singer :
             * all_artist_ting_uid : 1100
             * file_duration : 273
             * del_status : 0
             * resource_type : 0
             * all_rate : 96,128,224,320,flac
             * toneid : 600902000005286912
             * copy_type : 0
             * has_mv_mobile : 1
             * song_id : 7313983
             * title : 喜欢你
             * ting_uid : 1100
             * author : Beyond
             * album_id : 7311104
             * album_title : 传奇再续
             * is_first_publish : 0
             * havehigh : 2
             * charge : 0
             * has_mv : 1
             * learn : 0
             * song_source : web
             * piao_id : 0
             * korean_bb_song : 0
             * resource_type_ext : 0
             * mv_provider : 1100000000
             * desc :
             * url : http://music.baidu.com/song/7313983
             * recommend_reason : 以往片刻欢笑仍挂在脸上
             */

            public String artist_id;
            public String pic_big;
            public String pic_small;
            public String pic_premium;
            public String pic_huge;
            public String pic_singer;
            public String all_artist_ting_uid;
            public String file_duration;
            public String del_status;
            public String resource_type;
            public String all_rate;
            public String toneid;
            public String copy_type;
            public int has_mv_mobile;
            public String song_id;
            public String title;
            public String ting_uid;
            public String author;
            public String album_id;
            public String album_title;
            public int is_first_publish;
            public int havehigh;
            public int charge;
            public int has_mv;
            public int learn;
            public String song_source;
            public String piao_id;
            public String korean_bb_song;
            public String resource_type_ext;
            public String mv_provider;
            public String desc;
            public String url;
            public String recommend_reason;
        }
    }
}
