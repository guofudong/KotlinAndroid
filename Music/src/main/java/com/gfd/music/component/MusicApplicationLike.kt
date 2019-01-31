package com.gfd.music.component

import com.gfd.provider.router.component.IApplicationLike
import com.gfd.provider.router.component.Router
import com.gfd.provider.router.service.MusicService

class MusicApplicationLoad : IApplicationLike {

    var router = Router.instance

    override fun registered() {
        router.addService(MusicService::class.java.simpleName, MusicServiceImpl())
    }

    override fun unregistered() {
        router.removeService(MusicService::class.java.simpleName)
    }

}