package com.fivefivelike.themvpbase.mvp.delegate;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.themvpbase.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

/**
 * Created by liugongce on 2017/7/28.
 */

public class MainDelegate extends BaseDelegate {

    public SwipeMenuRecyclerView recycleview;

    @Override
    public void initView() {
        recycleview = getViewById(R.id.recycleview);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
