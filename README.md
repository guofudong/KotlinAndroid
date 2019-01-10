# KotlinAndroid
基于组件化 + MVP + Retrofit + RxKotlin + Dagger2实现的一款用Kotlin语言编写的影视类应用。

## APK下载
[点击下载](/screenshot/app-release.apk)

## 说明
#### [common.gradle](/common.gradle):三方依赖库和版本管理，统一放在该文件中。

#### [and_res_guard.gradle](/and_res_guard.gradle):微信开源工具[AndResGuard](https://github.com/shwenzhang/AndResGuard)资源文件"混淆"配置文件。

>[Android打包那些事](https://www.jianshu.com/p/5255cf853fad)

#### [libbase.gradle](/libbase.gradle):多Module build文件公共部分抽取。

#### [releaseinfo.gradle](/releaseinfo.gradle):版本发布文档自动维护脚本

>[Gradle实战](https://www.jianshu.com/p/7e1c7164976b)

### PluginSwitch：插件项目
实现了两个功能：
* 1.自动切换library和Application。
>如果gradle.properties中配置了isRunAlone=true，也就是能够独立运行，那么点击运行按钮可以选择该模块自动运行,无需在build.gradle文件中配置。如果运行的是主module，不管其依赖的module是否可以独立运行，插件自动设置为library。实现真正的自动切换。
```
//不需要再通过变量来控制了
if(isHomeModule.toBoolean()){
    apply plugin: 'com.android.library'
}else{
    apply plugin: 'com.android.application'
}
//省略资源目录的配置
```
#### 效果图：

![](/screenshot/module_run.gif)  ![](/screenshot/alone.png)

* 2.主Module在开发时不依赖业务模块，只要在运行打包时才依赖，彻底避免业务模块之间的耦合。
>通过在build.gradle配置，通过字节码插桩来实现的。
```
combuild {
    applicationName = 'com.gfd.home.app.HomeApplication'
    isRegisterAuto = false
}

//主module依赖其他模块配置在该目录下的gradle.properties中配置：
debugComponent=Home,Crosstalk,Music,Player //debug依赖的
compileComponent=Home,Crosstalk,Music,Player //release依赖的

//不需要在build.gradle中依赖了
api project(':Home')
api project(':Music')
......
```
>这样主Module在开发时不再依赖具体的业务模块，只是使用业务模块提供的服务，所以业务模块需要实现Provider模块定义的接口，提供具体的业务。

```
interface IApplicationLoad {

    /** 组件加载*/
    fun registered()

    /** 组件卸载*/
    fun unregistered()
}
```

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
