package king.bird.marrykotlin.net

import com.google.gson.Gson
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import king.bird.marrykotlin.iface.OnRequestListener
import king.bird.marrykotlin.util.LogUtils
import king.bird.marrykotlin.util.ToastUtils
import okhttp3.Call
import okhttp3.MediaType

import java.util.HashMap


/**
 * Created by sunny on 2017/8/17.
 */

open class NetWorkUtilsK {
    companion object {

        private val TAG = "NetWorkUtils"


        open fun doPostJson(url: String, params: Any,method:String,
                            listener: OnRequestListener?) {
            LogUtils.e("\n"+"-----------NetWork.doPostJson---------"+"\n"+url)
            OkHttpUtils
                    .postString()
                    .url(url)
                    .addHeader("method",method)
                    .content(Gson().toJson(params))
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(object : StringCallback() {
                        override fun onResponse(s: String, i: Int) {
                            try {
                                LogUtils.e("\n"+"-----------NetWork.onResponse---------"+"\n"+s)
                                listener?.onSuccess(s)
                            } catch (e: Exception) {
                                ToastUtils.showToast(e.message!!)
                            }

                        }

                        override fun onError(call: Call, e: Exception, i: Int) {
                            LogUtils.e("\n"+"-----------NetWork.onError---------"+"\n"+e.message)
                            listener?.onError(e.message!!)
                        }
                    })

        }

        /**
         * 获取查询字符串
         *
         * @param params
         * @return
         */
        private fun formatQueryString(params: HashMap<String, String>): String {
            val keys = params.keys
            val iterator = keys.iterator()
            val queryStringBuilder = StringBuilder()
            queryStringBuilder.append("?")
            while (iterator.hasNext()) {
                val key = iterator.next()
                queryStringBuilder.append(String.format("%s=%s&", key, params[key]))
            }

            var queryString = queryStringBuilder.toString()
            queryString = queryString.substring(0, queryString.length - 1)
            return queryString

        }

        /**
         * 将json结果解析成bean对象
         *
         * @param jsonResult
         * @param clz
         * @param <T>
         * @return
        </T> */
        fun <T> jsonToBean(jsonResult: String, clz: Class<T>): T {
            val gson = Gson()

            return gson.fromJson(jsonResult, clz)
        }

        fun cancelRequest(tag: Any) {

            OkHttpUtils.getInstance().cancelTag(tag)
        }
    }
}
