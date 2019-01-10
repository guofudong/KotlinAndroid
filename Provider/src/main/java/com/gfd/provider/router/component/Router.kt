package com.gfd.provider.router.component

import android.text.TextUtils


/**
 * 组件注册
 */
class Router private constructor() {

    private val services = hashMapOf<String, Any>()
    //注册的组件的集合
    private val components = hashMapOf<String, IApplicationLoad>()

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

    /**
     * 注册组件
     *
     * @param classname 组件名
     */
    fun registerComponent(classname: String?) {
        if (TextUtils.isEmpty(classname)) {
            return
        }
        if (components.keys.contains(classname)) {
            return
        }
        try {
            val clazz = Class.forName(classname)
            val applicationLike = clazz.newInstance() as IApplicationLoad
            applicationLike.registered()
            components[classname!!] = applicationLike
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 反注册组件
     *
     * @param classname 组件名
     */
    fun unregisterComponent(classname: String?) {
        if (TextUtils.isEmpty(classname)) {
            return
        }
        if (components.keys.contains(classname)) {
            components[classname]?.unregistered()
            components.remove(classname)
            return
        }
        try {
            val clazz = Class.forName(classname)
            val applicationLike = clazz.newInstance() as IApplicationLoad
            applicationLike.unregistered()
            components.remove(classname)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}