package king.bird.marrykotlin

import android.app.Application
import android.content.Context

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/08/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class MyApplication : Application() {

    companion object {
         var context: Context? = null
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}