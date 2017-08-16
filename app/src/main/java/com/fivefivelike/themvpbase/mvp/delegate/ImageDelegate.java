package com.fivefivelike.themvpbase.mvp.delegate;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.view.ViewPagerFixed;
import com.fivefivelike.themvpbase.R;

/**
 * Created by liugongce on 2017/7/19.
 */

public class ImageDelegate extends BaseDelegate {
    public ViewPagerFixed viewPager;

    @Override
    public void initView() {
        viewPager=getViewById(R.id.viewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bigimage;
    }
}
