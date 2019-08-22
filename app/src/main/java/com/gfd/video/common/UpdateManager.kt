package com.gfd.video.common

import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.gfd.video.entity.VersionData
import com.google.gson.Gson
import com.lzy.okgo.OkGo
import com.lzy.okgo.callback.StringCallback
import com.lzy.okgo.model.Response
import com.orhanobut.logger.Logger

/**
 * @Author：郭富东
 * @Date：2019/8/19 : 15:36
 * @Email：878749089@qq.com
 * @description：版本更新工具类
 */
@Suppress("DEPRECATION")
class UpdateManager private constructor() {

    companion object {
        val instance: UpdateManager by lazy { UpdateManager() }
    }

    fun checkVersion(context: Context) {
        OkGo.get<String>(Api.URL_VERSION_CHECK)
                .execute(object : StringCallback() {
                    override fun onSuccess(response: Response<String>) {
                        val json = response.body()
                        Logger.e("版本更新数据：$json")
                        val versionData = Gson().fromJson(json, VersionData::class.java)
                        val currentCode = getVersionCode(context)
                        val serverCode = versionData.data.versionCode
                        if (serverCode > currentCode) {
                            //有新版本
                            showDownLoadDialog(context)
                        }
                    }
                })

    }

    /**
     * 获取当前应用的版本号
     * @param context Context
     * @return Int
     */
    private fun getVersionCode(context: Context): Int {
        var versionCode = 0
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                context.packageManager.getPackageInfo(context.packageName, 0).longVersionCode.toInt()
            } else {
                context.packageManager.getPackageInfo(context.packageName, 0).versionCode
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return versionCode
    }


    /**
     * 显示更新提示对话框
     * @param context Context
     */
    private fun showDownLoadDialog(context: Context) {
        val dialog = AlertDialog.Builder(context)
                .setTitle("发现新版本")
                .setMessage("github:https://github.com/guofudong/KotlinAndroid")
                .setNegativeButton("取消") { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton("确认") { dialog, _ ->
                    dialog.dismiss()
                }.create()
        dialog.show()

    }

}