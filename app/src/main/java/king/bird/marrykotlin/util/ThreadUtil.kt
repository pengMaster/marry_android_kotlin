package king.bird.marrykotlin.util

import android.os.Handler
import android.os.Looper

/**
 * <pre>
 * author : Wp
 * e-mail : 18141924293@163.com
 * time   : 2018/08/02
 * desc   :
 * version: 1.0
</pre> *
 */
object ThreadUtil {
    //获取UI线程
    fun runOnUiThread(runnable: Runnable) {
        if (Thread.currentThread() === Looper.getMainLooper().thread) {
            runnable.run()
        } else {
            val handler = Handler(Looper.getMainLooper())
            handler.post(runnable)
        }
    }
}
