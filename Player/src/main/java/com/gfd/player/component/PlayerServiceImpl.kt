package com.gfd.player.component

import com.gfd.common.ui.fragment.BaseFragment
import com.gfd.player.ui.fragment.LiveFragment
import com.gfd.provider.router.service.LiveService

/**
 * Player模块提供服务的具体实现者
 */
class PlayerServiceImpl : LiveService {

    override fun getLiveFragment(): BaseFragment {
        return LiveFragment()
    }

}