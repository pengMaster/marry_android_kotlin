package king.bird.marrykotlin.adapter;

import android.app.Activity;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.List;

import butterknife.BindView;
import king.bird.marrykotlin.R;
import king.bird.marrykotlin.base.BaseAppAdapter;
import king.bird.marrykotlin.base.BaseHolderL;
import king.bird.marrykotlin.util.UIUtils;
import king.bird.spiderlib.bean.ImageResult;

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/09/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class GridAdapter extends BaseAppAdapter {

    Activity mActivity;

    public GridAdapter(AbsListView listView, List datas, Activity activity) {
        super(listView, datas);
        mActivity = activity;
    }

    @Override
    protected BaseHolderL getHolder() {
        return new GridHolder();
    }

    class GridHolder extends BaseHolderL {

        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_hint)
        TextView tvHint;
        @BindView(R.id.mLayout)
        CardView mLayout;

        @Override
        protected View initView() {
            return UIUtils.inflate(R.layout.item_image);
        }

        @Override
        public void refreshView() {
            ImageResult data = (ImageResult) getData();
            String url = data.getUrl();
            if (!"".equals(url)) {
                Glide.with(mActivity)
                        .load(new GlideUrl(url, new LazyHeaders.Builder().addHeader("Referer", "http://www.mzitu.com/").build()))
                        .placeholder(R.drawable.default_image)
                        .error(R.drawable.circle_img_error)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(ivImage);
            }
            String desc = data.getDesc();
            if (!"".equals(desc)) {
                tvHint.setText(desc);
            }

            //动画
            Animation animation = AnimationUtils.loadAnimation(mActivity, R.anim.translate_0_1);
            mLayout.startAnimation(animation);
        }
    }
}
