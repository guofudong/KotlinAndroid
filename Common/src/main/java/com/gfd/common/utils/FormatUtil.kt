package com.gfd.common.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * <br></br>
 * **进行一些转换工作**
 */
object FormatUtil {


    /**
     * 获取当前时间
     *
     * @return 时间
     */
    val time: String
        get() {
            val dfs = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return dfs.format(Date())
        }

    /**
     * 格式化时间
     *
     * @param time 时间值 (00:00 -23:59:59)
     * @return 时间
     */
    fun formatTime(time: Long): String {
        var temp = time
        // TODO Auto-generated method stub
        if (temp == 0L) {
            return "00:00"
        }
        temp /= 1000
        val s = (temp % 60) // s秒
        val m = (temp / 60 % 60) //m分
        val h = (temp / 60 / 60 % 24) //h小时s
        return if (h > 0) {
            "${if (h > 9) "$h" else "0$h"}:${if (m > 9) "$m" else "0$m"}:${if (s > 9) "$s" else "0$s"}"
        } else {
            "${if (m > 9) "$m" else "0$m"}:${if (s > 9) "$s" else "0$s"}"
        }
    }

    /**
     * 格式化时间
     *
     * @param time 时间值 (00:00 -23:59:59)
     * @return 时间
     */
    fun formatDate(time: Long): String {
        val duration = System.currentTimeMillis() - time
        return when {
            duration < 60 * 1000 -> "${duration / 1000}秒前"
            duration < 60 * 1000 * 60 -> "${duration / 1000 / 60}分钟前"
            duration < 60 * 1000 * 24 -> "${duration / 1000 / 60 / 24}小时前"
            else -> {
                val dfs = SimpleDateFormat("yyyy年MM月dd日")
                val date = dfs.format(Date(time))
                date
            }
        }
    }
}
