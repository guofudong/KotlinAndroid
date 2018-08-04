package com.gfd.common.injection.scope

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * @Author : 郭富东
 * @Date ：2018/8/3 - 10:49
 * @Email：878749089@qq.com
 * @descriptio：组件级别作用域
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
annotation class PerComponentScope