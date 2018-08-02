package com.gfd.common.injection.scope

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 9:57
 * @Email：878749089@qq.com
 * @descriptio：Activity级别的作用域
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class ActivityScope