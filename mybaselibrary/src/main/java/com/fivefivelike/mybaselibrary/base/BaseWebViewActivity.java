package com.fivefivelike.mybaselibrary.base;

import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.AndroidUtil;


public abstract class BaseWebViewActivity<T extends BaseDelegate> extends BaseActivity<T> {

    public static final String WEBTTITLE = "webtitle";
    public static final String WEBURL = "weburl";
    protected WebView webView;
    protected String url;
    protected String title;

    /**
     * 传入webiew的id
     *
     * @param webViewId
     */
    protected void initWebView(int webViewId) {
        title = getIntent().getStringExtra(WEBTTITLE);
        url = getIntent().getStringExtra(WEBURL);
        title = TextUtils.isEmpty(title) ? "" : title;
        webView = viewDelegate.getViewById(webViewId);
        AndroidUtil.webviewRegister(webView);//注册webview

    }

    /**
     * 获取网络标题
     */
    protected void setWebTitle() {
        if (!TextUtils.isEmpty(title)) {
            initToolbar(new ToolbarBuilder().setTitle(title));
        } else {
            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);
                    initToolbar(new ToolbarBuilder().setTitle(title));
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        webView.onPause();
        super.onPause();
    }
}
