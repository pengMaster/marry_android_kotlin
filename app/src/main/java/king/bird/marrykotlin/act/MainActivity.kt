package king.bird.marrykotlin.act

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import king.bird.marrykotlin.R
import king.bird.marrykotlin.base.BaseActivity
import king.bird.marrykotlin.iface.OnRequestListener
import king.bird.marrykotlin.net.NetWorkUtilsK
import king.bird.spiderlib.SpiderUtil
import king.bird.spiderlib.bean.ImageResult
import king.bird.spiderlib.listener.ImageResultListener
import kotlinx.android.synthetic.main.activity_main.*
import com.bumptech.glide.load.model.LazyHeaders
import com.google.gson.Gson
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import king.bird.marrykotlin.adapter.GridAdapter
import king.bird.marrykotlin.util.ToastUtils
import king.bird.spiderlib.SpiderUtil.Companion.getImageByUrl


/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/09/03
 *     desc   : 主页面
 *     version: 1.0
 * </pre>
 */
class MainActivity : BaseActivity(), OnRefreshListener, OnLoadmoreListener {

    private var url : String= "http://www.mzitu.com/tag/ugirls/"
    private var currentPage : Int = 2
    private var isRefresh : Boolean = true
    private var adapter : GridAdapter? = null
    private var mExitTime: Long? = 0L

    override val getLayoutId: Int
        get() = R.layout.activity_main

    override fun initData() {

        refreshLayout.isEnableAutoLoadmore = true
        refreshLayout.isEnableRefresh = true
        refreshLayout.setOnRefreshListener(this)
        refreshLayout.setOnLoadmoreListener(this)

        downLoadImage(url)

        mGridView.onItemClickListener = AdapterView.OnItemClickListener { p0, p1, p2, p3 ->
            val itemAtPosition = mGridView.getItemAtPosition(p2) as ImageResult
            val bundle = Bundle()
            bundle.putString("itemBean",Gson().toJson(itemAtPosition))
            startActivity<ImageDetailAct>(bundle)
        }

    }

    override fun onRefresh(refreshlayout: RefreshLayout?) {

        url = "http://www.mzitu.com/tag/ugirls/"
        isRefresh = true
        currentPage= 2
        downLoadImage(url)

    }

    override fun onLoadmore(refreshlayout: RefreshLayout?) {

        isRefresh = false
        url = "http://www.mzitu.com/tag/ugirls/page/$currentPage/"
        downLoadImage(url)
        currentPage++
    }


    private fun downLoadImage(url : String) {

        getImageByUrl(url, object : ImageResultListener {

            override fun result(imageList: HashSet<ImageResult>) {
                val list: ArrayList<ImageResult> = ArrayList()
                imageList.forEach {
                    list.add(it)
                }
                if (isRefresh) {
                    adapter = GridAdapter(mGridView, list, this@MainActivity)
                    mGridView.adapter = adapter
                    refreshLayout.finishRefresh()
                } else {
                    adapter!!.appendAll(list)
                    refreshLayout.finishLoadmore()
                }
            }
        })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mExitTime!! > 2000) {
                ToastUtils.showToast("再按一次退出程序")
                mExitTime = System.currentTimeMillis()
            } else {
                System.exit(0)
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}