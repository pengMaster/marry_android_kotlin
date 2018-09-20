package king.bird.marrykotlin.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/08/02
 *     desc   : Base可扩展
 *     version: 1.0
 * </pre>
 */
open abstract class BaseActivity : AppCompatActivity() {

    open var TAG = this.javaClass.simpleName!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId)
        initData()
    }

     abstract val getLayoutId: Int

     abstract fun initData()

    inline fun <reified T : Activity> Activity.startActivity() {
        startActivity(Intent(this, T::class.java))
    }

    inline fun <reified T : Activity> Activity.startActivity(bundle: Bundle) {
        val intent = Intent()
        intent.setClass(this, T::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    inline fun <reified T : Any> Gson.fromJson(json: String): T {
        //封装了`Gson.fromJson(String json , Class<T> classOf)`方法
        return fromJson(json, T::class.java)
    }

}