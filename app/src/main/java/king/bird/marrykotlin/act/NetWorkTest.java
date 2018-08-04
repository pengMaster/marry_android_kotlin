package king.bird.marrykotlin.act;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import jameson.io.library.util.LogUtils;
import king.bird.marrykotlin.R;
import king.bird.marrykotlin.base.BaseActivity;
import king.bird.marrykotlin.net.Api;
import okhttp3.Call;

/**
 * <pre>
 *     author : Wp
 *     e-mail : 18141924293@163.com
 *     time   : 2018/08/03
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class NetWorkTest extends BaseActivity{

    @Override
    public int getGetLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        OkHttpUtils.post().url("http://localhost:8080/party//mobile/mobileIn")
                .addParams("","")
                .addHeader("method",Api.Companion.getGirlImage())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        LogUtils.e(e.getMessage());
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        LogUtils.e(s);
                    }
                });

    }
}
