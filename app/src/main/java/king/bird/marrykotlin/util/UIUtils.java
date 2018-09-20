package king.bird.marrykotlin.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;

import king.bird.marrykotlin.MyApplication;

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/06/26
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class UIUtils {

    public static Context getContext() {
        return MyApplication.Companion.getContext();
    }
    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px转换dip
     */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    public static View inflate(Context context, int resId) {
        return LayoutInflater.from(context).inflate(resId, null);
    }

    public static View inflate(int resId){
        return LayoutInflater.from(getContext()).inflate(resId,null);
    }

    /**
     * getFilesDir
     */
    public static File getFilesDir() {
        return getContext().getFilesDir();
    }

    /**
     * 资产目录
     */
    public static AssetManager getAssets() {
        return getContext().getAssets();
    }

    /**
     * 获取资源
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取颜色选择器
     */
    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }



    public static String getAbsolutePath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 添加 fragment 布局
     *
     * @param fragmentManager 需要的 FragmentManager
     * @param addLayoutID     添加到那个布局
     * @param content         添加的 Fragment
     * @param TAG             标记
     */
    public static void addFragmentLayout(FragmentManager fragmentManager, int addLayoutID, android.support.v4.app.Fragment content, String TAG) {
        if (content.isAdded()) {
            fragmentManager.beginTransaction().show(content).commit();
        } else {
            fragmentManager.beginTransaction().add(addLayoutID, content, TAG).commit();
        }
    }

    /**
     * 隐藏一个布局
     *
     * @param fragmentManager 需要的 FragmentManager
     * @param content         添加的 Fragment
     */
    public static void hideFragmentLayout(FragmentManager fragmentManager, android.support.v4.app.Fragment content) {
        fragmentManager.beginTransaction().hide(content).commit();
    }

    /**
     * 动态设置ListView的高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
//		int tmp = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
//			tmp = listItem.getMeasuredHeight();
        }
        totalHeight += 10;
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}