package king.bird.marrykotlin.iface

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/08/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */
interface OnRequestListener {
    fun onSuccess(t: String)

    fun onError(errorMsg: String)

}