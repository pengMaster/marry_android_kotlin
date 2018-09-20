package king.bird.marrykotlin.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/06/25
 *     desc   : ListView.BaseAppAdapter
 *     version: 1.0
 * </pre>
 */
public abstract class BaseAppAdapter<Data> extends BaseAdapter {
    private AbsListView mListView;//和该adapter关联的listView
    Unbinder unbinder;
    private List<Data> mDatas;
    public BaseAppAdapter(AbsListView listView, List<Data> datas) {
        mListView = listView;
//        if (null != datas) {
            setData(datas);
//        }
    }
    public List<Data> getData() {
        return mDatas;
    }

    public void setData(List<Data> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    /**
     * 追加数据
     * @param list
     */
    public void appendAll(List<Data> list) {
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
//        if (mDatas != null) {
            return mDatas.size();
//        }
//        return 0;
    }

    @Override
    public Object getItem(int position) {
//        if (mDatas != null && position < mDatas.size()) {
            return mDatas.get(position);
//        }
//        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolderL<Data> holder;
        unbinder = ButterKnife.bind(this, convertView);
        if (convertView != null && convertView.getTag() instanceof BaseHolderL) {
            holder = (BaseHolderL<Data>) convertView.getTag();
        } else {
            holder = getHolder();
        }
        holder.setListView(mListView);
        holder.setPosition(position);
        holder.setData(mDatas.get(position));

        return holder.getRootView();
    }
    protected abstract BaseHolderL getHolder();
}
