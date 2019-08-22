package com.gfd.common.dsl

/**
 * @Author ：郭富东
 * @Date：2019/8/6:14:59
 * @Email：878749089@qq.com
 * @description：键盘行为DSL
 */
class EditorActionDSL {

    /** 搜索按键*/
    internal var onSearch: ((text: String) -> Unit)? = null
        private set

    /**
     * 设置搜索回调
     * @param block (text: String) -> Unit
     */
    fun onSearch(block: (text: String) -> Unit) {
        this.onSearch = block
    }
}