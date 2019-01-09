package com.gfd.music.entity;

import java.util.List;

/**
 * @Author : 郭富东
 * @Date ：2018/8/17 - 17:09
 * @Email：878749089@qq.com
 * @descriptio：
 */
public class SimiMv {

    /**
     * result : SUCCESS
     * code : 200
     * data : {"videoCount":600,"videos":[{"coverUrl":"http://p1.music.126.net/Qt-ZsA6Yhli6jZb2ijyaUQ==/109951163574200462.jpg","title":"《80000》全网最酷炫最走心remix版本回归！实力碾压！","durationms":95804,"playTime":2003353,"type":1,"creator":[{"userId":364241728,"userName":"拼口汉踢你"}],"aliaName":null,"transName":null,"vid":"DB13660458401253468CD0F469F4CD31","markTypes":[],"alg":"alg_video_a"},{"coverUrl":"http://p1.music.126.net/VUBaIqBIi37jC1Y0A8N82w==/109951163573939322.jpg","title":"まじ娘_『心做し』超燃现场版LIVE","durationms":257730,"playTime":1445829,"type":1,"creator":[{"userId":259837595,"userName":"希喽呀"}],"aliaName":null,"transName":null,"vid":"9DA7CD02FDBA00F08A5992BC63833086","markTypes":[109],"alg":"alg_video_a"},{"coverUrl":"http://p1.music.126.net/XudNEdafPhJ7nd0KkIPujQ==/109951163655242619.jpg","title":"陈立农《女孩》现场版，17岁的少年就这样走进女孩们的心...","durationms":238144,"playTime":625395,"type":1,"creator":[{"userId":1664436531,"userName":"音乐点"}],"aliaName":null,"transName":null,"vid":"841A22E9A1F4BA6CF7FF90DF527F77AD","markTypes":[],"alg":"alg_video_a"},{"coverUrl":"http://p1.music.126.net/7zdGpT4bChdUUKMbDXEFHA==/109951163635365277.jpg","title":"韩国高中生舞蹈翻跳串烧，男同学们都看疯了","durationms":654966,"playTime":5653270,"type":1,"creator":[{"userId":427599707,"userName":"李开心心心心心哼"}],"aliaName":null,"transName":null,"vid":"8B3E68D8FB6E3F01872DF7D6C9F5C72C","markTypes":[110],"alg":"alg_video_a"},{"coverUrl":"http://p1.music.126.net/4v6EQkPiaBIWw0loPJ_d8Q==/109951163573664745.jpg","title":"毕业季最戳心单曲，听着听着就泪目了，回不去的青春！","durationms":348787,"playTime":764828,"type":1,"creator":[{"userId":449979212,"userName":"全球潮音乐"}],"aliaName":null,"transName":null,"vid":"9E27168505CDA89981609497AB7C2A46","markTypes":[],"alg":"alg_video_a"},{"coverUrl":"http://p1.music.126.net/SAJDiGVOONpC-56BLwm0Yw==/109951163572656209.jpg","title":"冯提莫翻唱花泽香菜神曲《恋爱循环》，甜炸了老夫的少女心","durationms":316093,"playTime":3227242,"type":1,"creator":[{"userId":479954154,"userName":"音乐诊疗室"}],"aliaName":null,"transName":null,"vid":"D818EF4FF0DEEC092BFA704494E30721","markTypes":[],"alg":"alg_video_a"},{"coverUrl":"http://p1.music.126.net/QV_DakTabySCr-iuzGhIhg==/109951163574305489.jpg","title":"火遍全网的日本歌曲《心做し》现场版","durationms":205150,"playTime":450189,"type":1,"creator":[{"userId":278675941,"userName":"Instagram美图集"}],"aliaName":null,"transName":null,"vid":"9A16A33259D8B32ED7C09E6737B44A60","markTypes":[],"alg":"alg_video_a"},{"coverUrl":"http://p1.music.126.net/RXpBOWaQfd2UgvYN2EZWgg==/109951163573191170.jpg","title":"网易云音乐最扎心评论第一期-你离开的事实","durationms":223560,"playTime":1128510,"type":1,"creator":[{"userId":426450560,"userName":"阿快剪辑"}],"aliaName":null,"transName":null,"vid":"557E6A473D3AD7A5FCB30BD3F034AC45","markTypes":[109],"alg":"alg_video_a"}]}
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
         * videoCount : 600
         * videos : [{"coverUrl":"http://p1.music.126.net/Qt-ZsA6Yhli6jZb2ijyaUQ==/109951163574200462.jpg","title":"《80000》全网最酷炫最走心remix版本回归！实力碾压！","durationms":95804,"playTime":2003353,"type":1,"creator":[{"userId":364241728,"userName":"拼口汉踢你"}],"aliaName":null,"transName":null,"vid":"DB13660458401253468CD0F469F4CD31","markTypes":[],"alg":"alg_video_a"},{"coverUrl":"http://p1.music.126.net/VUBaIqBIi37jC1Y0A8N82w==/109951163573939322.jpg","title":"まじ娘_『心做し』超燃现场版LIVE","durationms":257730,"playTime":1445829,"type":1,"creator":[{"userId":259837595,"userName":"希喽呀"}],"aliaName":null,"transName":null,"vid":"9DA7CD02FDBA00F08A5992BC63833086","markTypes":[109],"alg":"alg_video_a"},{"coverUrl":"http://p1.music.126.net/XudNEdafPhJ7nd0KkIPujQ==/109951163655242619.jpg","title":"陈立农《女孩》现场版，17岁的少年就这样走进女孩们的心...","durationms":238144,"playTime":625395,"type":1,"creator":[{"userId":1664436531,"userName":"音乐点"}],"aliaName":null,"transName":null,"vid":"841A22E9A1F4BA6CF7FF90DF527F77AD","markTypes":[],"alg":"alg_video_a"},{"coverUrl":"http://p1.music.126.net/7zdGpT4bChdUUKMbDXEFHA==/109951163635365277.jpg","title":"韩国高中生舞蹈翻跳串烧，男同学们都看疯了","durationms":654966,"playTime":5653270,"type":1,"creator":[{"userId":427599707,"userName":"李开心心心心心哼"}],"aliaName":null,"transName":null,"vid":"8B3E68D8FB6E3F01872DF7D6C9F5C72C","markTypes":[110],"alg":"alg_video_a"},{"coverUrl":"http://p1.music.126.net/4v6EQkPiaBIWw0loPJ_d8Q==/109951163573664745.jpg","title":"毕业季最戳心单曲，听着听着就泪目了，回不去的青春！","durationms":348787,"playTime":764828,"type":1,"creator":[{"userId":449979212,"userName":"全球潮音乐"}],"aliaName":null,"transName":null,"vid":"9E27168505CDA89981609497AB7C2A46","markTypes":[],"alg":"alg_video_a"},{"coverUrl":"http://p1.music.126.net/SAJDiGVOONpC-56BLwm0Yw==/109951163572656209.jpg","title":"冯提莫翻唱花泽香菜神曲《恋爱循环》，甜炸了老夫的少女心","durationms":316093,"playTime":3227242,"type":1,"creator":[{"userId":479954154,"userName":"音乐诊疗室"}],"aliaName":null,"transName":null,"vid":"D818EF4FF0DEEC092BFA704494E30721","markTypes":[],"alg":"alg_video_a"},{"coverUrl":"http://p1.music.126.net/QV_DakTabySCr-iuzGhIhg==/109951163574305489.jpg","title":"火遍全网的日本歌曲《心做し》现场版","durationms":205150,"playTime":450189,"type":1,"creator":[{"userId":278675941,"userName":"Instagram美图集"}],"aliaName":null,"transName":null,"vid":"9A16A33259D8B32ED7C09E6737B44A60","markTypes":[],"alg":"alg_video_a"},{"coverUrl":"http://p1.music.126.net/RXpBOWaQfd2UgvYN2EZWgg==/109951163573191170.jpg","title":"网易云音乐最扎心评论第一期-你离开的事实","durationms":223560,"playTime":1128510,"type":1,"creator":[{"userId":426450560,"userName":"阿快剪辑"}],"aliaName":null,"transName":null,"vid":"557E6A473D3AD7A5FCB30BD3F034AC45","markTypes":[109],"alg":"alg_video_a"}]
         */

        private int videoCount;
        private List<VideosBean> videos;

        public int getVideoCount() {
            return videoCount;
        }

        public void setVideoCount(int videoCount) {
            this.videoCount = videoCount;
        }

        public List<VideosBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBean> videos) {
            this.videos = videos;
        }

        public static class VideosBean {
            /**
             * coverUrl : http://p1.music.126.net/Qt-ZsA6Yhli6jZb2ijyaUQ==/109951163574200462.jpg
             * title : 《80000》全网最酷炫最走心remix版本回归！实力碾压！
             * durationms : 95804
             * playTime : 2003353
             * type : 1
             * creator : [{"userId":364241728,"userName":"拼口汉踢你"}]
             * aliaName : null
             * transName : null
             * vid : DB13660458401253468CD0F469F4CD31
             * markTypes : []
             * alg : alg_video_a
             */

            private String coverUrl;
            private String title;
            private int durationms;
            private int playTime;
            private int type;
            private Object aliaName;
            private Object transName;
            private String vid;
            private String alg;
            private List<CreatorBean> creator;
            private List<?> markTypes;

            public String getCoverUrl() {
                return coverUrl;
            }

            public void setCoverUrl(String coverUrl) {
                this.coverUrl = coverUrl;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getDurationms() {
                return durationms;
            }

            public void setDurationms(int durationms) {
                this.durationms = durationms;
            }

            public int getPlayTime() {
                return playTime;
            }

            public void setPlayTime(int playTime) {
                this.playTime = playTime;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public Object getAliaName() {
                return aliaName;
            }

            public void setAliaName(Object aliaName) {
                this.aliaName = aliaName;
            }

            public Object getTransName() {
                return transName;
            }

            public void setTransName(Object transName) {
                this.transName = transName;
            }

            public String getVid() {
                return vid;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }

            public String getAlg() {
                return alg;
            }

            public void setAlg(String alg) {
                this.alg = alg;
            }

            public List<CreatorBean> getCreator() {
                return creator;
            }

            public void setCreator(List<CreatorBean> creator) {
                this.creator = creator;
            }

            public List<?> getMarkTypes() {
                return markTypes;
            }

            public void setMarkTypes(List<?> markTypes) {
                this.markTypes = markTypes;
            }

            public static class CreatorBean {
                /**
                 * userId : 364241728
                 * userName : 拼口汉踢你
                 */

                private int userId;
                private String userName;

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public String getUserName() {
                    return userName;
                }

                public void setUserName(String userName) {
                    this.userName = userName;
                }
            }
        }
    }
}
