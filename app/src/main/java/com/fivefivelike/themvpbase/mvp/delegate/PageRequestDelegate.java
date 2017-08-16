package com.fivefivelike.themvpbase.mvp.delegate;

import com.fivefivelike.mybaselibrary.base.BasePullDelegate;
import com.fivefivelike.themvpbase.R;

/**
 * Created by liugongce on 2017/7/28.
 */

public class PageRequestDelegate extends BasePullDelegate {

    @Override
    public void initView() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_page_request;
    }
}
