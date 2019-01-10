package com.gfd.home.component

import com.gfd.provider.router.component.IApplicationLoad
import com.gfd.provider.router.component.Router
import com.gfd.provider.router.service.LiveService

class PlayerApplicationLoad : IApplicationLoad {

    var router = Router.instance

    override fun registered() {
        router.addService(LiveService::class.java.simpleName, PlayerServiceImpl())
    }

    override fun unregistered() {
        router.removeService(LiveService::class.java.simpleName)
    }

}