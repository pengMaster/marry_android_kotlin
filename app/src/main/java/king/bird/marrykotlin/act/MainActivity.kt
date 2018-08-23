package king.bird.marrykotlin.act

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.stx.xhb.xbanner.XBanner
import jameson.io.library.util.LogUtils
import king.bird.marrykotlin.R
import king.bird.marrykotlin.adapter.ImageAdapter
import king.bird.marrykotlin.base.BaseActivity
import king.bird.marrykotlin.bean.ImageParser
import king.bird.marrykotlin.iface.OnRequestListener
import king.bird.marrykotlin.net.Api
import king.bird.marrykotlin.net.NetWorkUtilsK
import king.bird.marrykotlin.widget.MyLinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    var resultList = ArrayList<ImageParser.ResultListBean>()

    override val getLayoutId: Int
        get() = R.layout.activity_main

    override fun initData() {

        downLoadList()
    }

    private fun downLoadList() {

        NetWorkUtilsK.doPostJson(Api.baseUrl,"",Api.girlImage,
                object : OnRequestListener {
            override fun onSuccess(t: String) {
                val fromJson = Gson().fromJson(t, ImageParser::class.java)
                 resultList = fromJson.resultList as ArrayList<ImageParser.ResultListBean>
                LogUtils.e(t)
                initBanner()
                initRv()
            }

            override fun onError(errorMsg: String) {
                LogUtils.e(errorMsg)
            }
        })
    }

    private fun initItemClick() {

    }

    private fun initRv() {
        rv_image.layoutManager = MyLinearLayoutManager(this)
        rv_image.adapter = ImageAdapter(this, resultList)
        rv_image.setHasFixedSize(true)
        rv_image.setNestedScrollingEnabled(false)
    }

    private fun initBanner() {
        //刷新数据之后，需要重新设置是否支持自动轮播
        banner.setAutoPlayAble(true)
        banner.setData(resultList, null)
        //加载广告图片
        banner.loadImage(XBanner.XBannerAdapter { banner, model, view, position ->
            //在此处使用图片加载框架加载图片，demo中使用glide加载，可替换成自己项目中的图片加载框架
            val imageView = view as ImageView
            val bean = model as ImageParser.ResultListBean
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            Glide.with(this@MainActivity).load(bean.imageUrl).placeholder(R.drawable.default_image).error(R.drawable.default_image).into(imageView)
        })
    }
}
