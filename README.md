# KotlinAndroid
基于组件化 + MVP + Retrofit + RxKotlin + Dagger2实现的一款用Kotlin语言编写的影视类应用。

## APK下载
[点击下载](/screenshot/app-release.apk)

## 说明
[common.gradle](/common.gradle):三方依赖库和版本管理，统一放在该文件中。

[and_res_guard.gradle](/and_res_guard.gradle):微信开源工具[AndResGuard](https://github.com/shwenzhang/AndResGuard)资源文件"混淆"配置文件。

[Android打包那些事](https://www.jianshu.com/p/5255cf853fad)

[libbase.gradle](/libbase.gradle):多Module build文件公共部分抽取。

[releaseinfo.gradle](/releaseinfo.gradle):版本发布文档自动维护脚本

[Gradle实战](https://www.jianshu.com/p/7e1c7164976b)

### API说明
>项目中用到的数据都是通过解析网站数据而来，所以没有固定的接口格式。因此没有封装统一的网络工具类，而使用三方库[okhttp-OkGo](https://github.com/jeasonlzy/okhttp-OkGo)来请求，便于解析数据。

[Jsoup](http://www.open-open.com/jsoup/)

### 屏幕适配
采用smallestWidth适配

>smallestWidth限定符适配和宽高限定符适配最大的区别在于，前者有很好的容错机制，如果没有对应dp文件夹，系统会向下寻找，比如离360dp最近的只有value-sw350dp，那么Android就会选择value-sw350dp文件夹下面的资源文件。这个特性就完美的解决了上文提到的宽高限定符的容错问题。

[自动生成适配文件工具](https://github.com/ladingwu/dimens_sw)

[AndroidUI屏幕适配](https://www.jianshu.com/p/667cc6e0261a)

## 模块化结构图

![](/screenshot/模块化.png)

>Common为公共库，主要包含一些基类和常用的工具类，Provider依赖于Common，是业务模块真正依赖的库。业务模块包括：Home，Music，User，Player。每个业务模块对应一个Module。

## 首页模块效果图
![](/screenshot/home.png)  ![](/screenshot/player.png)

![](/screenshot/search2.png)  ![](/screenshot/search1.png)

## 音乐模块效果图

![](/screenshot/music1.png)  ![](/screenshot/music2.png)

![](/screenshot/music4.png)  ![](/screenshot/music5.png)  

![](/screenshot/music_search2.png)  ![](/screenshot/music_search1.png)  

 ![](/screenshot/music3.png)  
 
 ## 电视直播，相声模块
 
![](/screenshot/play01.png)  ![](/screenshot/crosstalk01.png)  
