package com.gfd.home.component

import com.gfd.common.ui.fragment.BaseFragment
import com.gfd.home.ui.fragment.HomeFragment
import com.gfd.provider.router.service.HomeService

/**
 * @Author：郭富东
 * @Date：2019/8/19 : 16:57
 * @Email：878749089@qq.com
 * @description：home模块提供服务的具体实现者
 */
class HomeServiceImpl : HomeService {

    override fun getHomeFragment(): BaseFragment {
        return HomeFragment()
    }
}