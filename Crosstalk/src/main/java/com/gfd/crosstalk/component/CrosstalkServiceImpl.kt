package com.gfd.home.component

import com.gfd.common.ui.fragment.BaseFragment
import com.gfd.crosstalk.ui.fragment.CrosstalkFragment
import com.gfd.provider.router.service.CrosstalkService

/**
 * Crosstalk模块提供服务的具体实现者
 */
class CrosstalkServiceImpl : CrosstalkService {

    override fun getCrosstalkFragment(): BaseFragment {
        return CrosstalkFragment()
    }

}