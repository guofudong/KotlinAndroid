package com.gfd.music.entity;

import java.util.List;

/**
 * @Author : 郭富东
 * @Date ：2018/8/13 - 14:27
 * @Email：878749089@qq.com
 * @descriptio：
 */
public class SongItemDto {

    public String listid;
    public String title;
    public String pic_300;
    public String pic_500;
    public String pic_700;
    public String width;
    public String height;
    public String listenum;
    public String collectnum;
    public String tag;
    public String desc;
    public String url;
    public List<SongItem> content;

    public class SongItem {

        public String title;
        public String song_id;
        public String author;
        public String album_title;
        public String pic_big;
        public String ting_uid;
        public String all_artist_id;
        public String high_rate;

    }
}
