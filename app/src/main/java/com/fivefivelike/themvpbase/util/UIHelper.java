package com.fivefivelike.themvpbase.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;

import com.fivefivelike.mybaselibrary.base.BaseActivity;
import com.fivefivelike.mybaselibrary.base.BaseFragment;
import com.fivefivelike.mybaselibrary.base.BaseWebViewActivity;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.view.TabPageIndicator;
import com.fivefivelike.themvpbase.R;
import com.fivefivelike.themvpbase.mvp.activity.ImageActivity;
import com.fivefivelike.themvpbase.mvp.activity.NormalWebviewActivity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liugongce on 2017/7/17.
 */

public class UIHelper {

    /**
     * 去webView
     *
     * @param title 标题
     * @param url   网址
     */
    public static void gotoWebView(BaseActivity activity, String title, String url, String type) {
        Intent intent = new Intent();
        intent.putExtra(BaseWebViewActivity.WEBTTITLE, title);
        intent.putExtra(BaseWebViewActivity.WEBURL, url);
        intent.putExtra(WebConfig.TYPE, type);
        activity.gotoActivty(new NormalWebviewActivity(), intent, true);

    }

    /**
     * 去webView
     *
     * @param title 标题
     * @param url   网址
     */
    public static void gotoWebView(BaseFragment activity, String title, String url, String type) {
        Intent intent = new Intent();
        intent.putExtra(BaseWebViewActivity.WEBTTITLE, title);
        intent.putExtra(BaseWebViewActivity.WEBURL, url);
        intent.putExtra(WebConfig.TYPE, type);
        activity.gotoActivty(new NormalWebviewActivity(), intent, true);
    }

    /**
     * 判断提交必填参数是否为空
     *
     * @param content
     * @return
     */
    public static boolean judgeRequestContentIsNull(String content, String toastString) {
        if (TextUtils.isEmpty(content)) {
            ToastUtil.show(toastString);
            return true;
        }
        return false;
    }

    /**
     * @param context
     * @param type
     * @param position
     * @param pathList
     */
    public static void toBigImage(Activity context, String type, int position, List<String> pathList, boolean isDelete) {
        Intent intent = new Intent(context, ImageActivity.class);
        intent.putExtra(ImageActivity.BIG_IMAGE_CURRENT_POSITION, position);
        intent.putExtra(ImageActivity.BIG_IMAGE_INTO_PATH, (Serializable) pathList);
        intent.putExtra(ImageActivity.BIG_IMAGE_IS_DELETE, isDelete);
        intent.setType(type);
        if (isDelete)
            context.startActivityForResult(intent, 0x123);
        else
            context.startActivity(intent);
    }

    /**
     * 通过一些set方法，设置控件的属性
     */
    public static void setTabPagerIndicator(TabPageIndicator tabPageIndicator, TabPageIndicator.IndicatorMode mode) {
        tabPageIndicator.setIndicatorMode(mode);// 设置模式，一定要先设置模式
        tabPageIndicator.setDividerColorResource(R.color.white);// 设置分割线的颜色
        tabPageIndicator.setDividerPadding(10);//设置
        tabPageIndicator.setIndicatorColorResource(R.color.red);// 设置底部导航线的颜色
        tabPageIndicator.setTextColorSelectedResource(R.color.red);// 设置tab标题选中的颜色
        tabPageIndicator.setTextColorResource(R.color.trans_333333);// 设置tab标题未被选中的颜色
        tabPageIndicator.setTextSize(18);// 设置字体大小
        tabPageIndicator.setUnderlineColorResource(R.color.basic_line);
        tabPageIndicator.setBackgroundColor(Color.WHITE);
        //        tabPageIndicator.setIndicatorHeight(3);
    }
}
