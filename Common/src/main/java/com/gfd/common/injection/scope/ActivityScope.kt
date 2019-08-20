package com.gfd.common.injection.scope

import javax.inject.Scope
import kotlin.annotation.MustBeDocumented
import kotlin.annotation.Retention

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 9:57
 * @Email：878749089@qq.com
 * @description：Activity级别的作用域
 */
@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope