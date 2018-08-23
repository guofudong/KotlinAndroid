package com.gfd.music.entity;

import java.util.List;

/**
 * @Author : 郭富东
 * @Date ：2018/8/23 - 17:47
 * @Email：878749089@qq.com
 * @descriptio：
 */
public class HotSearchDto {

    /**
     * error_code : 22000
     * result : [{"strong":1,"word":"爸爸去哪儿","linktype":3,"linkurl":"http://music.taihe.com/h5pc/spec_detail?id=1299&columnid=87"},{"strong":0,"word":"怀旧翻唱","linktype":3,"linkurl":"http://music.taihe.com/h5pc/spec_detail?id=1296&columnid=96"},{"strong":0,"word":"黄渤","linktype":3,"linkurl":"http://music.taihe.com/h5pc/spec_detail?id=1291&columnid=87"},{"strong":0,"word":"经典老歌","linktype":3,"linkurl":"http://music.taihe.com/h5pc/spec_detail?id=1292&columnid=134"},{"strong":0,"word":"戴荃","linktype":3,"linkurl":"http://music.taihe.com/h5pc/spec_detail?id=1286&columnid=92"},{"strong":0,"word":"海岛风情","linktype":3,"linkurl":"http://music.taihe.com/h5pc/spec_detail?id=1287&columnid=96"},{"strong":0,"word":"西虹市首富","linktype":3,"linkurl":"http://music.taihe.com/h5pc/spec_detail?id=1275&columnid=87"},{"strong":0,"word":"夏日消暑","linktype":3,"linkurl":"http://music.taihe.com/h5pc/spec_detail?id=1139&columnid=96"},{"strong":0,"word":"薛之谦","linktype":5,"linkurl":"601422007"},{"strong":0,"word":"一周音乐热","linktype":3,"linkurl":"http://music.taihe.com/h5pc/spec_detail?id=1252&columnid=87"},{"strong":0,"word":"许嵩","linktype":8,"linkurl":"1557"},{"strong":0,"word":"车载音乐","linktype":3,"linkurl":"http://music.taihe.com/h5pc/spec_detail?id=1278&columnid=96"}]
     */

    public int error_code;
    public List<ResultBean> result;

    public static class ResultBean {
        /**
         * strong : 1
         * word : 爸爸去哪儿
         * linktype : 3
         * linkurl : http://music.taihe.com/h5pc/spec_detail?id=1299&columnid=87
         */

        public int strong;
        public String word;
        public int linktype;
        public String linkurl;
    }
}
