package com.gfd.provider.router.component

/**
 * 组件加载，卸载接口
 */
interface IApplicationLoad {

    /** 组件加载*/
    fun registered()

    /** 组件卸载*/
    fun unregistered()
}