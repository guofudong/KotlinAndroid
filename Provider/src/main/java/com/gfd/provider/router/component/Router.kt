package com.gfd.provider.router.component


/**
 * 组件注册
 */
class Router private constructor() {

    private val services = hashMapOf<String, Any>()

    private object RouterHolder {
        val holder = Router()
    }

    companion object {
        val instance = RouterHolder.holder
    }

    @Synchronized
    fun addService(serviceName: String?, serviceImpl: Any?) {
        if (serviceName == null || serviceImpl == null) {
            return
        }
        services[serviceName] = serviceImpl
    }

    @Synchronized
    fun getService(serviceName: String?): Any? {
        return if (serviceName == null) {
            null
        } else services[serviceName]
    }

    @Synchronized
    fun removeService(serviceName: String?) {
        if (serviceName == null) {
            return
        }
        services.remove(serviceName)
    }
}