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
     * result : SUCCESS
     * code : 200
     * data : [{"id":2614841256,"title":"给那些回不去又遥远的乡愁","creator":"AirRadio空气赫兹","description":"很多年前看过一部关于乡愁的记录式电影，分别拍摄了几个不同的人各自的生活轨迹。其中一个人在异乡生活一辈子都没有再回到家乡，他在一个新的城市里落地生根，他的故乡是内陆城市，离开故乡的原因很简单，他喜欢大海所以去了有海的城市，就一直在哪里呆着，从年少一晃到了老年，摄制组跟拍了他的生活，他像个向导一样，带着他们去他年轻时工作的地方转转，他经常走的那条海边小路，边走边回忆，\u201c看，那是我第一次约会的地方\u201d。他指着一间破旧的咖啡馆说，\u201c我太太其实几年前就去世了，但是我每次路过这里，都会觉得她在里面等着我。\u201d 他的故事最后，摄制组邀请他回到他的家乡，也就是年少时执意离开的地方，这么多年没有回去，其实那个城市早就变了样子，这一段画面的语言很少，一行人走进这个城市，仿佛是陌生人一样，后来他们来到他年少时的社区，有些许变化但基本还是以前的样子，他在一条小路前停了好长时间，这个长镜头是影片的结尾，之前他们有说话，可是这一刻，整段影片静默到几乎令人窒息，尽头从远景慢慢的移动到近景，定格在他的那有些感情波动的表情上，我看见一行眼泪\u2026电影结束。 这么多年过去了，每每我们说起\u201c乡愁\u201d两个字的时候，我总会想起那部电影结束时候，那张脸庞的表情，它太真实了，在没有语言的情节下，却胜过千言万语，尽管我已经忘了那部电影的名字。 乡愁在每个人的生活轨迹里，是不同的印记，也许是故乡的味道，就像我离开故乡这么多年，依旧念念不忘家乡小吃的味道，每次看到我生活的城市里出现新的家乡口味的小吃店都想去试试（每次都失望而归），心里明白虽然味道不差，可能是乡愁作祟；身边的小伙伴早已全部离家在各自的城市里生活，聚会的次数少的可怜，你知道的，人只要不在一起习惯了，似乎也就没有什么可以讲的了\u2026 故乡在现实里越来越远，我却想在记忆慢慢靠近它，人和事是基本不可能了，除了饮食的味道，还有音乐，那些青春期一起听过的歌，到现在听起来想到从前的歌，也许就是那么几句歌词 少年十八离家，匆匆数年，往后故事的发生和故乡的名字也许再没有关联，你重新和一个陌生的城市相处，把理想和热血往那儿一丢，冷暖自知，成功与失败一直交错，受过挫折打败过他人，爱过错或对的人，多年以后，也许满载荣誉，也许依旧孑然一身，不知道为什么，每次想到这些，我总会有些淡淡的乡愁。 封面图片来自：阮义忠","coverImgUrl":"http://p1.music.126.net/A6I-QOF_h2AkBA6eN7hbGw==/109951163781590817.jpg?param=400y400","songNum":26,"playCount":133415},{"id":2575922902,"title":"新锐之声|| 来日已可期的欧美零零后","creator":"冰糖炖雪梨Er","description":"随着2018年12月31日的过去 意味着00年份最小的一批也已踏入成年人的行列 封面｜Kristian Kostov (2000.03.15) 17年欧歌赛亚军 来自保加利亚 & 俄罗斯双国籍 \u203b新锐之星\u203b 来日方长 未来已可期\u203b ta们有些已完成变声or正处在变声期or还保持着少年的稚嫩嗓音 但不可否认一点：零零后乐坛新声代宣布ta们已经悄然杀入欧美圈 (流行、R&B、电子、说唱、乡村、另类/独立系都有十分令人期待的新声代) 相信未来的欧美圈一定会拿下属于ta们的一片天空 按歌手出生年份列表分享（很多歌手页资料待补充） 2000年份推荐歌手 Bülow Bars & Melody Carlie Hanson Craaon Lunders Charlotte Lawrence Connie Talbot Daniel Skye Isac Elliot Jada Facer Jasmine Thompson Kriatian Kostov Maisie Peters New District Willion Smith 2001年份推荐歌手 Billie Eilish Isabela Moner Luna Blaise Noah Cyrus Selina Mour Toby Randall Zhavia Ward 2002年份推荐歌手 Henry Gallahger Jacob Satoruis Lil Mosey Marcus & Martinus 2003年份推荐歌手 Johnny Orlando Matty B 2004年份推荐歌手 Grace Vanderwaal Mackenzie Ziegler 2005年份推荐歌手 Cruz Beckham 2006年份推荐歌手 Mason Ramsey","coverImgUrl":"http://p1.music.126.net/ywfmojI47lp82_Ydoe3xUA==/109951163771871623.jpg?param=400y400","songNum":34,"playCount":84712}]
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
         * id : 2614841256
         * title : 给那些回不去又遥远的乡愁
         * creator : AirRadio空气赫兹
         * description : 很多年前看过一部关于乡愁的记录式电影，分别拍摄了几个不同的人各自的生活轨迹。其中一个人在异乡生活一辈子都没有再回到家乡，他在一个新的城市里落地生根，他的故乡是内陆城市，离开故乡的原因很简单，他喜欢大海所以去了有海的城市，就一直在哪里呆着，从年少一晃到了老年，摄制组跟拍了他的生活，他像个向导一样，带着他们去他年轻时工作的地方转转，他经常走的那条海边小路，边走边回忆，“看，那是我第一次约会的地方”。他指着一间破旧的咖啡馆说，“我太太其实几年前就去世了，但是我每次路过这里，都会觉得她在里面等着我。” 他的故事最后，摄制组邀请他回到他的家乡，也就是年少时执意离开的地方，这么多年没有回去，其实那个城市早就变了样子，这一段画面的语言很少，一行人走进这个城市，仿佛是陌生人一样，后来他们来到他年少时的社区，有些许变化但基本还是以前的样子，他在一条小路前停了好长时间，这个长镜头是影片的结尾，之前他们有说话，可是这一刻，整段影片静默到几乎令人窒息，尽头从远景慢慢的移动到近景，定格在他的那有些感情波动的表情上，我看见一行眼泪…电影结束。 这么多年过去了，每每我们说起“乡愁”两个字的时候，我总会想起那部电影结束时候，那张脸庞的表情，它太真实了，在没有语言的情节下，却胜过千言万语，尽管我已经忘了那部电影的名字。 乡愁在每个人的生活轨迹里，是不同的印记，也许是故乡的味道，就像我离开故乡这么多年，依旧念念不忘家乡小吃的味道，每次看到我生活的城市里出现新的家乡口味的小吃店都想去试试（每次都失望而归），心里明白虽然味道不差，可能是乡愁作祟；身边的小伙伴早已全部离家在各自的城市里生活，聚会的次数少的可怜，你知道的，人只要不在一起习惯了，似乎也就没有什么可以讲的了… 故乡在现实里越来越远，我却想在记忆慢慢靠近它，人和事是基本不可能了，除了饮食的味道，还有音乐，那些青春期一起听过的歌，到现在听起来想到从前的歌，也许就是那么几句歌词 少年十八离家，匆匆数年，往后故事的发生和故乡的名字也许再没有关联，你重新和一个陌生的城市相处，把理想和热血往那儿一丢，冷暖自知，成功与失败一直交错，受过挫折打败过他人，爱过错或对的人，多年以后，也许满载荣誉，也许依旧孑然一身，不知道为什么，每次想到这些，我总会有些淡淡的乡愁。 封面图片来自：阮义忠
         * coverImgUrl : http://p1.music.126.net/A6I-QOF_h2AkBA6eN7hbGw==/109951163781590817.jpg?param=400y400
         * songNum : 26
         * playCount : 133415
         */

        private long id;
        private String title;
        private String creator;
        private String description;
        private String coverImgUrl;
        private int songNum;
        private int playCount;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreator() {
            return creator;
        }

        public void setCreator(String creator) {
            this.creator = creator;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public int getSongNum() {
            return songNum;
        }

        public void setSongNum(int songNum) {
            this.songNum = songNum;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }
    }
}
