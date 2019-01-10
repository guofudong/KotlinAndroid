package com.gfd.home.component

import com.gfd.provider.router.component.IApplicationLoad
import com.gfd.provider.router.component.Router
import com.gfd.provider.router.service.HomeService

class HomeApplicationLoad : IApplicationLoad {

    var router = Router.instance

    override fun registered() {
        router.addService(HomeService::class.java.simpleName, HomeServiceImpl())
    }

    override fun unregistered() {
        router.removeService(HomeService::class.java.simpleName)
    }

}