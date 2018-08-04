package king.bird.marrykotlin.util;

import android.os.Handler;
import android.os.Looper;

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/08/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ThreadUtil {
    //获取UI线程
    public static void runOnUiThread(Runnable runnable) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(runnable);
        }
    }
}
