# KotlinAndroid
基于组件化 + MVP + Retrofit + RxKotlin + Dagger2实现的一款用Kotlin语言编写的影视类应用。

## APK下载
[点击下载](/screenshot/app-release.apk)

## 说明
[common.gradle](/common.gradle):三方依赖库和版本管理，统一放在该文件中。

## 模块化结构图

![](/screenshot/模块化.png)

>Common为公共库，主要包含一些基类和常用的工具类，Provider依赖于Common，是业务模块真正依赖的库。业务模块包括：Home，Music，User，Player。

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
