package com.gfd.home.component

import com.gfd.provider.router.component.IApplicationLike
import com.gfd.provider.router.component.Router
import com.gfd.provider.router.service.HomeService

/**
 * @Author：郭富东
 * @Date：2019/8/19 : 16:57
 * @Email：878749089@qq.com
 * @description：插件自动加载该类，实现服务注册
 */
class HomeApplicationLike : IApplicationLike {

    var router = Router.instance

    override fun registered() {
        router.addService(HomeService::class.java.simpleName, HomeServiceImpl())
    }

    override fun unregistered() {
        router.removeService(HomeService::class.java.simpleName)
    }

}