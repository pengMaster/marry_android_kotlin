package king.bird.marrykotlin.act

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.google.gson.Gson
import com.stx.xhb.xbanner.XBanner
import king.bird.marrykotlin.R
import king.bird.marrykotlin.base.BaseActivity
import king.bird.spiderlib.bean.ImageResult
import kotlinx.android.synthetic.main.act_image_detail.*

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/09/20
 *     desc   : 图片详情
 *     version: 1.0
 * </pre>
 */
class ImageDetailAct : BaseActivity() {

    override val getLayoutId: Int
        get() = R.layout.act_image_detail

    override fun initData() {

        val items = intent.getStringExtra("itemBean")
        val itemBean = Gson().fromJson<ImageResult>(items)
        val url = itemBean.url
        val list = getUrlListByUrl(url)
        banner.setAutoPlayAble(true)
        banner.setData(list, null)

        //加载图片
        banner.loadImage { banner, model, view, position ->
            //在此处使用图片加载框架加载图片，demo中使用glide加载，可替换成自己项目中的图片加载框架
            val imageView = view as ImageView
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            Glide.with(this@ImageDetailAct)
                    .load(GlideUrl(model as String, LazyHeaders.Builder().addHeader("Referer", "http://www.mzitu.com/").build()))
                    .placeholder(R.drawable.default_image)
                    .error(R.drawable.circle_img_error).into(imageView)
        }
    }

    /**
     * 根据首页图片截取所有图片集合
     *
     * @param url 首頁显示图片Url
     * @return 需要展示Url集合
     */
    private fun getUrlListByUrl(url: String): ArrayList<String> {

        val list: ArrayList<String> = ArrayList()
        if (url.contains("20")) {
            val split = url.split("/20")
            if (split.size > 1) {
                val s = url.split("/20")[1]
                if (s.length > 7) {
                    val substring = s.substring(0, 6)
                    val split1 = s.split("_")
                    if (split1.size > 1) {
                        val s1 = s.split("_")[1]
                        val substring1 = s1.substring(0, 3)
                        for (i in 1..8) {
                            val ivUrl = "http://i.meizitu.net/20$substring${substring1}0$i.jpg"
                            list.add(ivUrl)
                        }
                    }
                }
            }


//            if (url.contains("thumbs")) {
//                for (i in 1..8) {
//                    val ivUrl = "http://i.meizitu.net/thumbs/20$substring${substring1}0$i.jpg"
//                    list.add(ivUrl)
//                }
//            } else {
//                for (i in 1..8) {
//                    val ivUrl = "http://i.meizitu.net/20$substring${substring1}0$i.jpg"
//                    list.add(ivUrl)
//                }
//            }

        }
        return list
    }
}