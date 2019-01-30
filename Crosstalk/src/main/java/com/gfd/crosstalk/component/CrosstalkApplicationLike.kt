package com.gfd.home.component

import com.gfd.provider.router.component.IApplicationLike
import com.gfd.provider.router.component.Router
import com.gfd.provider.router.service.CrosstalkService
import com.gfd.provider.router.service.HomeService

class CrosstalkApplicationLike : IApplicationLike {

    var router = Router.instance

    override fun registered() {
        router.addService(CrosstalkService::class.java.simpleName, CrosstalkServiceImpl())
    }

    override fun unregistered() {
        router.removeService(CrosstalkService::class.java.simpleName)
    }

}