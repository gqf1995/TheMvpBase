package com.fivefivelike.themvpbase.mvp.delegate;

import android.support.v7.widget.RecyclerView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.themvpbase.R;

/**
 * Created by liugongce on 2017/7/28.
 */

public class ImageChooseDelegate extends BaseDelegate {
    public RecyclerView recycleview;

    @Override
    public void initView() {
        recycleview = getViewById(R.id.recycleview);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_choose;
    }
}
