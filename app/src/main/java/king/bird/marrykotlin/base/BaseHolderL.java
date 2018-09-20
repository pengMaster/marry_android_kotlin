package king.bird.marrykotlin.base;

import android.view.View;
import android.widget.AbsListView;

import butterknife.ButterKnife;


/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/06/25
 *     desc   : ListView.BaseHolderL
 *     version: 1.0
 * </pre>
 */
public abstract class BaseHolderL<Data> {
    protected View mRootView;
    protected int mPosition;
    protected Data mData;
    private AbsListView listView;

    public BaseHolderL() {
        mRootView = initView();
        ButterKnife.bind(this, mRootView);
        mRootView.setTag(this);
    }

    public View getRootView() {
        return mRootView;
    }

    public void setData(Data data) {
        mData = data;
        refreshView();
    }

    public Data getData() {
        return mData;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public int getPosition() {
        return mPosition;
    }


    /**
     * 子类必须覆盖用于实现UI初始化
     */
    protected abstract View initView();

    /**
     * 子类必须覆盖用于实现UI刷新
     */
    public abstract void refreshView();

    public void setListView(AbsListView listView) {
        this.listView = listView;
    }

    public AbsListView getListView() {
        return listView;
    }
}
