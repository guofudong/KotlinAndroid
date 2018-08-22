package com.gfd.player.entity;

import java.util.List;

/**
 * @Author : 郭富东
 * @Date ：2018/8/22 - 17:16
 * @Email：878749089@qq.com
 * @descriptio：
 */
public class LiveDataDto {


    public List<LiveData> lives;

    public static class LiveData {
        /**
         * name : CCTV1综合
         * icon :
         * live : http://223.110.243.170/PLTV/3/224/3221226316/index.m3u8
         */

        public String name;
        public String icon;
        public String live;
    }
}
