package com.gfd.common.net

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 11:13
 * @Email：878749089@qq.com
 * @descriptio：网络请求返回的统一实体类
 */
data class Result<T>(var status:Int,var msg:String,var verId:String,var result:T)