package com.gfd.crosstalk.entity;

import java.util.List;

/**
 * @Author : 郭富东
 * @Date ：2018/9/15 - 16:35
 * @Email：878749089@qq.com
 * @descriptio：
 */
public class VideoPlayerData {


    /**
     * retCode : 200
     * retDesc : 成功
     * data : {"text":"郭德纲京剧大戏《秦香莲》：驸马府串门偶遇秦香莲","video":{"link":[{"url":"http://v11-tt.ixigua.com/3719e730a54ef7c2b4ed46a4972d5103/5b9cd593/video/m/220fb4b91f7bb754f81b39bfb708ce45293115b9fd60000718f0473243f/#mp4","buttonText":"高清(480p)视频地址1","type":"高清(480p)"},{"url":"http://v7.pstatp.com/fe3bc5366933c383a527f267cba53d61/5b9cd593/video/m/220fb4b91f7bb754f81b39bfb708ce45293115b9fd60000718f0473243f/#mp4","buttonText":"高清(480p)视频地址2","type":"高清(480p)"}],"download":[{"url":"http://v11-tt.ixigua.com/3719e730a54ef7c2b4ed46a4972d5103/5b9cd593/video/m/220fb4b91f7bb754f81b39bfb708ce45293115b9fd60000718f0473243f/#mp4","buttonText":"高清(480p)视频地址1","type":"高清(480p)"}]}}
     * succ : true
     */

    public int retCode;
    public String retDesc;
    public DataBean data;
    public boolean succ;

    public static class DataBean {
        /**
         * text : 郭德纲京剧大戏《秦香莲》：驸马府串门偶遇秦香莲
         * video : {"link":[{"url":"http://v11-tt.ixigua.com/3719e730a54ef7c2b4ed46a4972d5103/5b9cd593/video/m/220fb4b91f7bb754f81b39bfb708ce45293115b9fd60000718f0473243f/#mp4","buttonText":"高清(480p)视频地址1","type":"高清(480p)"},{"url":"http://v7.pstatp.com/fe3bc5366933c383a527f267cba53d61/5b9cd593/video/m/220fb4b91f7bb754f81b39bfb708ce45293115b9fd60000718f0473243f/#mp4","buttonText":"高清(480p)视频地址2","type":"高清(480p)"}],"download":[{"url":"http://v11-tt.ixigua.com/3719e730a54ef7c2b4ed46a4972d5103/5b9cd593/video/m/220fb4b91f7bb754f81b39bfb708ce45293115b9fd60000718f0473243f/#mp4","buttonText":"高清(480p)视频地址1","type":"高清(480p)"}]}
         */

        public String text;
        public VideoBean video;

        public static class VideoBean {
            public List<LinkBean> link;
            public List<DownloadBean> download;

            public static class LinkBean {
                /**
                 * url : http://v11-tt.ixigua.com/3719e730a54ef7c2b4ed46a4972d5103/5b9cd593/video/m/220fb4b91f7bb754f81b39bfb708ce45293115b9fd60000718f0473243f/#mp4
                 * buttonText : 高清(480p)视频地址1
                 * type : 高清(480p)
                 */

                public String url;
                public String buttonText;
                public String type;
            }

            public static class DownloadBean {
                /**
                 * url : http://v11-tt.ixigua.com/3719e730a54ef7c2b4ed46a4972d5103/5b9cd593/video/m/220fb4b91f7bb754f81b39bfb708ce45293115b9fd60000718f0473243f/#mp4
                 * buttonText : 高清(480p)视频地址1
                 * type : 高清(480p)
                 */

                public String url;
                public String buttonText;
                public String type;
            }
        }
    }
}
