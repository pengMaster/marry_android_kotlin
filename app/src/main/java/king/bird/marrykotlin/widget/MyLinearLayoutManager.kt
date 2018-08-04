package king.bird.marrykotlin.widget

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/08/01
 *     desc   :
 *     version: 1.0
 * </pre>
 */
class MyLinearLayoutManager : LinearLayoutManager {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)


    override fun onMeasure(recycler: RecyclerView.Recycler?, state: RecyclerView.State?, widthSpec: Int, heightSpec: Int) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }
}