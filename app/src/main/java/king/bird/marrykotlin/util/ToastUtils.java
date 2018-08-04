package king.bird.marrykotlin.util;

import android.widget.Toast;
import king.bird.marrykotlin.MyApplication;

/**
 * Created by sunny on 2018/2/27.
 * 弹吐司工具类
 */

public class ToastUtils {

    private static Toast toast;

    public static void showToast(String tip) {

        if(toast == null){
            toast = Toast.makeText(MyApplication.Companion.getContext(), tip, Toast.LENGTH_SHORT);
        }else{
            toast.setText(tip);
        }
//        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.show();
    }

    public static void showToastLong(String tip) {

        if(toast == null){
            toast = Toast.makeText(MyApplication.Companion.getContext(), tip, Toast.LENGTH_LONG);
        }else{
            toast.setText(tip);
        }
//        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.show();
    }
}
