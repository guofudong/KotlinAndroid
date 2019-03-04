package com.gfd.common.impl

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.DegradeService
import com.gfd.common.utils.ToastUtils

/**
 * @Author ：郭富东
 * @Date：2019/3/1:14:47
 * @Email：878749089@qq.com
 * @descriptio：全局降级处理
 */
@Route(path = "/xxx/xxx")
class DegradeServiceImpl : DegradeService {
    override fun onLost(context: Context?, postcard: Postcard?) {
        ToastUtils.instance.showToast("页面走丢了。")
    }
    override fun init(context: Context?) {
    }
}