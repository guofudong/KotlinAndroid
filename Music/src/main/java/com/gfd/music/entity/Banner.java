package com.gfd.music.entity;

import java.util.List;

/**
 * @Author : 郭富东
 * @Date ：2018/8/10 - 14:20
 * @Email：878749089@qq.com
 * @descriptio：
 */
public class Banner {

    /**
     * pic : [{"type":6,"mo_type":4,"code":"http://music.taihe.com/h5pc/spec_detail?id=1290&columnid=86","randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1533870073fb593cea2f51be6b5c3ea2d179efe5a3.jpg","randpic_ios5":"","randpic_desc":"","randpic_ipad":"","randpic_qq":"","randpic_2":"","randpic_iphone6":"","special_type":0,"ipad_desc":"","is_publish":"111111"},{"type":6,"mo_type":4,"code":"http://music.taihe.com/h5pc/spec_detail?id=1286&columnid=92","randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_1533800429fb270972d62cecb177c1834ff17756c0.jpg","randpic_ios5":"","randpic_desc":"","randpic_ipad":"","randpic_qq":"","randpic_2":"","randpic_iphone6":"","special_type":0,"ipad_desc":"","is_publish":"111111"},{"type":6,"mo_type":4,"code":"http://music.taihe.com/h5pc/spec_detail?id=1284&columnid=87","randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_15336130839fdf9251a9a5649ded85e702563db012.jpg","randpic_ios5":"","randpic_desc":"","randpic_ipad":"","randpic_qq":"","randpic_2":"","randpic_iphone6":"","special_type":0,"ipad_desc":"","is_publish":"111111"},{"type":2,"mo_type":2,"code":"601921467","randpic":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_15338105095af98693be88a71b0df65a95ee1cc55d.jpg","randpic_ios5":"","randpic_desc":"","randpic_ipad":"http://business.cdn.qianqian.com/qianqian/pic/bos_client_153381051358234b157fac652c906dfc9f69e322de.jpg","randpic_qq":"","randpic_2":"","randpic_iphone6":"","special_type":0,"ipad_desc":"","is_publish":"111111"}]
     * error_code : 22000
     */
    public int error_code;
    public List<PicBean> pic;

    public static class PicBean {
        /**
         * type : 6
         * mo_type : 4
         * code : http://music.taihe.com/h5pc/spec_detail?id=1290&columnid=86
         * randpic : http://business.cdn.qianqian.com/qianqian/pic/bos_client_1533870073fb593cea2f51be6b5c3ea2d179efe5a3.jpg
         * randpic_ios5 :
         * randpic_desc :
         * randpic_ipad :
         * randpic_qq :
         * randpic_2 :
         * randpic_iphone6 :
         * special_type : 0
         * ipad_desc :
         * is_publish : 111111
         */

        public int type;
        public int mo_type;
        public String code;
        public String randpic;
        public String randpic_ios5;
        public String randpic_desc;
        public String randpic_ipad;
        public String randpic_qq;
        public String randpic_2;
        public String randpic_iphone6;
        public int special_type;
        public String ipad_desc;
        public String is_publish;
    }
}
