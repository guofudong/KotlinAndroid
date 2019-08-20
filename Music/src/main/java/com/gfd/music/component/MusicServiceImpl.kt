package com.gfd.music.component

import com.gfd.common.ui.fragment.BaseFragment
import com.gfd.music.ui.fragment.MusicFragment
import com.gfd.provider.router.service.MusicService

/**
 * @Author：郭富东
 * @Date：2019/8/20 : 15:22
 * @Email：878749089@qq.com
 * @description：Crosstalk模块提供服务的具体实现者
 */
class MusicServiceImpl : MusicService {

    override fun getMusicFragment(): BaseFragment {
        return MusicFragment()
    }

}