package king.bird.marrykotlin.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/08/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */
open abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId)
        initData()
    }

     abstract val getLayoutId: Int

     abstract fun initData()
}