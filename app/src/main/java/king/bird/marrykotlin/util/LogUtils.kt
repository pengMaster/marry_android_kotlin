package king.bird.marrykotlin.util

import android.util.Log

import java.io.BufferedWriter
import java.io.FileNotFoundException
import java.io.FileWriter
import java.io.IOException


/**
 * @author
 * @return Log工具类
 */
object LogUtils {

    /**
     *
     * @param tag
     * @param msg
     */
    fun e(tag: String, msg: String) {
        Log.e(tag, msg)
    }

    /**
     *
     * @param msg
     */
    fun e(msg: String) {
        Log.e("LogUtils", msg)
    }

    /**
     * 打印log到文件中
     *
     * @param logPath
     * 日志文件路径
     * @param logData
     * 日志内容
     * @param override
     * 是否覆盖文件内容
     */
    fun f(logPath: String, logData: String, override: Boolean) {
        var writer: BufferedWriter? = null
        try {
            writer = BufferedWriter(FileWriter(logPath, !override))
            if (!override) {
                writer.append(logData)
            } else {
                writer.write(logData)
            }
            writer.newLine()
        } catch (e: FileNotFoundException) {
            //			e(new LogUtil(), e.toString());
        } catch (e: IOException) {
            //			e(new LogUtil(), e.toString());
        } finally {
            try {
                if (writer != null) {
                    writer.flush()
                    writer.close()
                }
            } catch (e2: Exception) {
                //				e(new LogUtil(), e2.toString());
            }

        }
    }
}// 混淆打包 置为false
