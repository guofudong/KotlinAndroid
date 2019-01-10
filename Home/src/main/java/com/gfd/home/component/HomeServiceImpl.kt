package com.gfd.home.component

import com.gfd.common.ui.fragment.BaseFragment
import com.gfd.home.ui.fragment.HomeFragment
import com.gfd.provider.router.service.HomeService

/**
 * home模块提供服务的具体实现者
 */
class HomeServiceImpl : HomeService {

    override fun getHomeFragment(): BaseFragment {
        return HomeFragment()
    }
}