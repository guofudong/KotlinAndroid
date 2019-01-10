package com.gfd.home.component

import com.gfd.provider.router.component.IApplicationLoad
import com.gfd.provider.router.component.Router
import com.gfd.provider.router.service.MusicService

class MusicApplicationLoad : IApplicationLoad {

    var router = Router.instance

    override fun registered() {
        router.addService(MusicService::class.java.simpleName, MusicServiceImpl())
    }

    override fun unregistered() {
        router.removeService(MusicService::class.java.simpleName)
    }

}