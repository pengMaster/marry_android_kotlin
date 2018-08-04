package king.bird.marrykotlin.act

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import king.bird.marrykotlin.R
import king.bird.marrykotlin.base.BaseActivity
import kotlinx.android.synthetic.main.activity_test.*

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/08/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */
open class ImageDetail : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        initData()
    }
     fun initData() {
        btn_test.setOnClickListener{
            var intent = Intent()
            intent.setClass(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
    }

}