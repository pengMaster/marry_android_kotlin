package king.bird.marrykotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import king.bird.marrykotlin.R
import king.bird.marrykotlin.bean.ImageBean
import king.bird.marrykotlin.bean.ImageParser
import java.util.*

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/08/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private var context: Context? = null
    private var newsList: ArrayList<ImageParser.ResultListBean>? = null

    //这是构造方法
    constructor(context: Context, newsList: ArrayList<ImageParser.ResultListBean>) {
        this.context = context
        this.newsList = newsList
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (newsList?.size as Int > position) {
            val news = newsList?.get(position)
            //使用Glide加载图片
            Glide.with(context).load(news?.imageUrl).into(holder?.ivDes)
            //设置标题
            holder?.tvTitle?.text = news?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return newsList?.size as Int
    }


    /**
     * 添加数据
     */
    fun addData(dataList: ArrayList<ImageParser.ResultListBean>): Unit {
        //这里不用像java一样判断空了,这里肯定是非空的
        if (dataList.size == 0) {
            return
        }
        newsList?.addAll(dataList)
        notifyDataSetChanged()
    }

    /**
     * 更新数据
     */
    fun updateData(dataList: ArrayList<ImageParser.ResultListBean>): Unit {
        if (dataList.size == 0) {
            return
        }
        newsList?.clear()
        newsList?.addAll(dataList)
        notifyDataSetChanged()
    }


    class ViewHolder : RecyclerView.ViewHolder {

        var ivDes: ImageView
        var tvTitle: TextView

        constructor(itemView: View) : super(itemView) {
            ivDes = itemView.findViewById(R.id.iv_image)
            tvTitle = itemView.findViewById(R.id.tv_hint)
        }
    }

}