package king.bird.marrykotlin.util

import android.widget.Toast
import king.bird.marrykotlin.MyApplication

/**
 * Created by sunny on 2018/2/27.
 * 弹吐司工具类
 */

object ToastUtils {

    private var toast: Toast? = null

    fun showToast(tip: String) {

        if (toast == null) {
            toast = Toast.makeText(MyApplication.context, tip, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(tip)
        }
        //        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast!!.show()
    }

    fun showToastLong(tip: String) {

        if (toast == null) {
            toast = Toast.makeText(MyApplication.context, tip, Toast.LENGTH_LONG)
        } else {
            toast!!.setText(tip)
        }
        //        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast!!.show()
    }
}
