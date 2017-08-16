package com.fivefivelike.themvpbase.mvp.activity;

import com.fivefivelike.mybaselibrary.base.BaseActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.themvpbase.mvp.delegate.HeadExpandDelegate;

/**
 * Created by liugongce on 2017/7/28.
 */

public class HeadExpandActivity extends BaseActivity<HeadExpandDelegate> {
    @Override
    protected Class<HeadExpandDelegate> getDelegateClass() {
        return HeadExpandDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("头部拉伸"));
    }
}
