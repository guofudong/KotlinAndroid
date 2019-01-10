package com.gfd.common.utils

import javax.script.Invocable
import javax.script.ScriptEngineManager

/**
 * @Author : 郭富东
 * @Date ：2018/9/15 - 17:29
 * @Email：878749089@qq.com
 * @descriptio：JS代码执行工具类
 */
object JSUtils {

    /**
     * 执行js代码
     * @param js String ：js代码
     * @param funName String ：方法名
     * @param param Array<out Any> ：方法参数
     * @return String ：执行结果
     */
    fun excuteJs(js: String, funName: String, vararg param: Any): String {
        val engine = ScriptEngineManager().getEngineByName("rhino")
        engine.eval(js)
        val inv = engine as Invocable
        val result = inv.invokeFunction(funName, param)
        return result.toString()
    }

    /**
     * 获取js执行引擎
     * @param js String ：js代码
     * @return Invocable ：js执行引擎
     */
    fun getJsInvocable(js: String): Invocable? {
        val engine = ScriptEngineManager().getEngineByName("rhino")
        if (engine != null) {
            engine.eval(js)
            return engine as Invocable
        }
        return null
    }
}