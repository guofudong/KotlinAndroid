package com.gfd.common.net

import android.net.ParseException
import com.gfd.common.R
import com.gfd.common.utils.ToastUtils
import com.google.gson.JsonParseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * @Author : 郭富东
 * @Date ：2018/8/2 - 10:42
 * @Email：878749089@qq.com
 * @descriptio：
 */
abstract class HttpResultSubscriber<T> : Observer<Result<T>> {

    private val RESULT_OK = 1

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        onStart()
    }

    override fun onNext(t: Result<T>) {
        if(t.status == RESULT_OK){
            onSuccess(t.result)
        }else{
            onFail(t.status,t.msg)
        }
    }


    override fun onError(e: Throwable) {
        onFail(0,"请求错误")
        if (e is HttpException) {
            onException(ExceptionReason.BAD_NETWORK)
        } else if (e is ConnectException
                || e is UnknownHostException) {
            onException(ExceptionReason.CONNECT_ERROR)
        } else if (e is InterruptedIOException) {
            onException(ExceptionReason.CONNECT_TIMEOUT)
        } else if (e is JsonParseException
                || e is JSONException
                || e is ParseException) {
            onException(ExceptionReason.PARSE_ERROR)
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR)
        }
    }

    fun onException(reason: ExceptionReason) {
        when (reason) {
            ExceptionReason.CONNECT_ERROR -> ToastUtils.instance.showToast(R.string.connect_error)
            ExceptionReason.CONNECT_TIMEOUT -> ToastUtils.instance.showToast(R.string.connect_timeout)
            ExceptionReason.BAD_NETWORK -> ToastUtils.instance.showToast(R.string.bad_network)
            ExceptionReason.PARSE_ERROR -> ToastUtils.instance.showToast(R.string.parse_error)
            ExceptionReason.UNKNOWN_ERROR -> ToastUtils.instance.showToast(R.string.unknown_error)
            else -> ToastUtils.instance.showToast(R.string.unknown_error)
        }
    }

    /**
     * 访问成功
     */
    abstract fun onSuccess(response: T)

    /**
     * 访问失败
     */
    abstract fun onFail(status:Int,msg:String)

    /**
     * 开始访问
     */
    fun onStart() {}
}