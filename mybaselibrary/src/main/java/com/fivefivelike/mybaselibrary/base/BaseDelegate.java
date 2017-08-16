package com.fivefivelike.mybaselibrary.base;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.R;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.mvp.view.IDelegateImpl;
import com.fivefivelike.mybaselibrary.view.dialog.NetConnectDialog;

/**
 * Created by liugongce on 2017/7/7.
 */

public abstract class BaseDelegate extends IDelegateImpl {
    private TextView mToolbarTitle;
    private TextView mToolbarSubTitle;
    private Toolbar mToolbar;
    private ImageView mToolbarRightImg1;
    private ImageView mToolbarRightImg2;
    private NetConnectDialog netConnectDialog;
    public NetConnectDialog getNetConnectDialog() {
        if (netConnectDialog==null){
            netConnectDialog=new NetConnectDialog(this.getActivity());
        }
        return netConnectDialog;
    }
    /**
     * 初始化标题栏
     *
     * @param builder 设置标题栏参数的对象
     */
    protected void initToolBar(AppCompatActivity activity, View.OnClickListener listener, ToolbarBuilder builder) {
        mToolbar = getViewById(R.id.toolbar);
        mToolbarTitle = getViewById(R.id.toolbar_title);
        mToolbarSubTitle = getViewById(R.id.toolbar_subtitle);
        if (mToolbar != null) {
            //将Toolbar显示到界面
            activity.setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null) {
            //getTitle()的值是activity的android:lable属性值
            mToolbarTitle.setText(activity.getTitle());
            //设置默认的标题不显示
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        //设置标题
        if (!TextUtils.isEmpty(builder.getTitle())) {
            mToolbarTitle.setText(builder.getTitle());
        }
        //设置右边的文字并显示
        if (!TextUtils.isEmpty(builder.getSubTitle())) {
            mToolbarSubTitle.setVisibility(View.VISIBLE);
            mToolbarSubTitle.setText(builder.getSubTitle());
            mToolbarSubTitle.setOnClickListener(listener);
        }
        //设置右边第一个按钮并显示
        if (builder.getmRightImg1() != 0) {
            mToolbarRightImg1 = getViewById(R.id.toolbar_img1);
            mToolbarRightImg1.setVisibility(View.VISIBLE);
            mToolbarRightImg1.setImageResource(builder.getmRightImg1());
            mToolbarRightImg1.setOnClickListener(listener);
        }
        //设置右边第二个按钮并显示
        if (builder.getmRightImg2() != 0) {
            mToolbarRightImg2 = getViewById(R.id.toolbar_img2);
            mToolbarRightImg2.setVisibility(View.VISIBLE);
            mToolbarRightImg2.setImageResource(builder.getmRightImg2());
            mToolbarRightImg2.setOnClickListener(listener);
        }
        //设置显示返回箭头
        if (builder.isShowBack()) {
            showBack(activity);
        }
        //设置标题栏的背景颜色
        if (builder.getmToolbarBackColor() != 0) {
            mToolbar.setBackgroundColor(builder.getmToolbarBackColor());
        }
        //设置标题是否显示
        if (!builder.isTitleShow()) {
            mToolbarTitle.setVisibility(View.GONE);
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    private void showBack(final AppCompatActivity activity) {
        //        setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        getmToolbar().setNavigationIcon(R.drawable.toolbar_icon_back);
        getmToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    public TextView getmToolbarTitle() {
        return mToolbarTitle;
    }

    public TextView getmToolbarSubTitle() {
        return mToolbarSubTitle;
    }

    public Toolbar getmToolbar() {
        return mToolbar;
    }

    public ImageView getmToolbarRightImg1() {
        return mToolbarRightImg1;
    }

    public ImageView getmToolbarRightImg2() {
        return mToolbarRightImg2;
    }
}
