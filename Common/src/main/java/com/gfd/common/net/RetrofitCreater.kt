package com.gfd.common.net

import com.gfd.common.common.BaseConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 10:20
 * @Email：878749089@qq.com
 * @descriptio：Retrofit工具类
 */
class RetrofitCreater private constructor() {

    companion object {
        val instance: RetrofitCreater by lazy { RetrofitCreater() }
    }

    private val interceptor: Interceptor
    private val retrofit: Retrofit

    init {
        //通用拦截器
        interceptor = Interceptor { chain ->
            var request = chain.request()
                    .newBuilder()
                    //.addHeader("Content_Type", "application/json")
                    .addHeader("charset", "UTF-8")
                    .build()
            chain.proceed(request)

        }

        //创建Retrofit的实例
        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstant.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(initClient())
                .build()
    }

    /**
     * 创建请求客户端
     */
    private fun initClient(): OkHttpClient {
       return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(initLogInterceptor())
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build()

    }

    /**
     *日志拦截器
     */
    private fun initLogInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * 创建请求的实例
     */
    fun <T> create(service:Class<T>):T{
       return retrofit.create(service)
    }

}