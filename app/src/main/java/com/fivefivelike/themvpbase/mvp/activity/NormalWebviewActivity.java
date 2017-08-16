package com.fivefivelike.themvpbase.mvp.activity;


import com.fivefivelike.mybaselibrary.base.BaseWebViewActivity;
import com.fivefivelike.themvpbase.R;
import com.fivefivelike.themvpbase.mvp.delegate.NormalWebviewDelegate;
import com.fivefivelike.themvpbase.util.Httpurl;
import com.fivefivelike.themvpbase.util.WebConfig;

import java.io.File;

/**
 * Created by liugongce on 2017/7/17.
 */

public class NormalWebviewActivity extends BaseWebViewActivity<NormalWebviewDelegate> {
    private String type;

    @Override
    protected Class<NormalWebviewDelegate> getDelegateClass() {
        return NormalWebviewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initWebView(R.id.webView);
        type = getIntent().getStringExtra(WebConfig.TYPE);
        if (type != null) {
            init();
        }
        setWebTitle();
        if (!url.startsWith("http")) {
            url = Httpurl.BASE_URL + File.separator + url;
        }
        webView.loadUrl(url);
    }

    private void init() {
        if (type.equals(WebConfig.TEST_WEB)) {//测试

        }
    }


}
