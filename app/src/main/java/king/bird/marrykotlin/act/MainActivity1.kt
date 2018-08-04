package king.bird.marrykotlin.act

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView


import com.view.jameson.library.CardScaleHelper

import java.util.ArrayList

import king.bird.marrykotlin.R
import king.bird.marrykotlin.adapter.CardAdapter
import king.bird.marrykotlin.image.util.BlurBitmapUtils
import king.bird.marrykotlin.image.util.ViewSwitchUtils
import king.bird.marrykotlin.util.ThreadUtil
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MainActivity1 : Activity() {

    private var mRecyclerView: RecyclerView? = null
    private var mBlurView: ImageView? = null
    private var mCardScaleHelper: CardScaleHelper? = null
    private var mBlurRunnable: Runnable? = null
    private var mLastPos = -1
    val mImgList = listOf<String>(
            "https://pengmaster.com/party/wechat/marry/tai_miao_zip/HY2A0764.jpg",
            "https://pengmaster.com/party/wechat/marry/ozfq_zip/HY2A1165.jpg",
            "https://pengmaster.com/party/wechat/marry/fhjr_zip/HY2A1065.jpg",
            "https://pengmaster.com/party/wechat/marry/dqdy_zip/HY2A1159.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val decorView = window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            window.statusBarColor = Color.TRANSPARENT
        }
        setContentView(R.layout.activity_image)
        init()
    }

    private fun init() {
        mRecyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mRecyclerView!!.layoutManager = linearLayoutManager
        mRecyclerView!!.adapter = CardAdapter(mImgList, applicationContext)
        // mRecyclerView绑定scale效果
        mCardScaleHelper = CardScaleHelper()
        mCardScaleHelper!!.currentItemPos = 2
        mCardScaleHelper!!.attachToRecyclerView(mRecyclerView)

        initBlurBackground()
    }

    private fun initBlurBackground() {
        mBlurView = findViewById<View>(R.id.blurView) as ImageView
        mRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    notifyBackgroundChange()
                }
            }
        })

        notifyBackgroundChange()
    }

    fun notifyBackgroundChange() {
        if (mLastPos == mCardScaleHelper!!.currentItemPos) return
        mLastPos = mCardScaleHelper!!.currentItemPos
        val resId = mImgList[mCardScaleHelper!!.currentItemPos]
        mBlurView!!.removeCallbacks(mBlurRunnable)
        mBlurRunnable = Runnable {
            Thread(Runnable {
                var imageurl: URL? = null

                try {
                    imageurl = URL(resId)
                } catch (e: MalformedURLException) {
                    e.printStackTrace()
                }

                try {
                    val conn = imageurl!!.openConnection() as HttpURLConnection
                    conn.doInput = true
                    conn.connect()
                    val `is` = conn.inputStream
                    var bitmap = BitmapFactory.decodeStream(`is`)
                    `is`.close()
                    ThreadUtil.runOnUiThread {
                        ViewSwitchUtils.startSwitchBackgroundAnim(mBlurView!!, BlurBitmapUtils.getBlurBitmap(mBlurView!!.context, bitmap, 15))
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }).start()

        }
        mBlurView!!.postDelayed(mBlurRunnable, 500)
    }

}
