package com.fivefivelike.themvpbase.mvp.delegate;

import android.widget.RelativeLayout;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.themvpbase.R;

/**
 * Created by liugongce on 2017/7/28.
 */

public class RollViewDelegate extends BaseDelegate {
    public RelativeLayout relativelayout;

    @Override
    public void initView() {
        relativelayout = getViewById(R.id.relativelayout);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_roll_view;
    }
}
