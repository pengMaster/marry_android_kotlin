package king.bird.marrykotlin.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/06/25
 *     desc   : BaseFragment
 *     version: 1.0
 * </pre>
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment {

    protected String TAG = this.getClass().getSimpleName();
    public View view;
    public Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = initView(inflater);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initData(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 当前Fragment 退出时,取消所有网络请求
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    /**
     * 子类必须实现初始化界面
     */
    public abstract View initView(LayoutInflater inflater);

    /**
     * 子类实现初始化数据
     */
    public abstract void initData(Bundle savedInstanceState);


}
