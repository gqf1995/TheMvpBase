package com.fivefivelike.themvpbase.mvp.delegate;

import android.widget.ImageView;

import com.fivefivelike.mybaselibrary.base.BaseDelegate;
import com.fivefivelike.mybaselibrary.utils.AndroidUtil;
import com.fivefivelike.mybaselibrary.view.HeadZoomScrollView;
import com.fivefivelike.themvpbase.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by liugongce on 2017/7/28.
 */

public class HeadExpandDelegate extends BaseDelegate {
    public ImageView iv_head;
    public CircleImageView iv_icon;
    public HeadZoomScrollView scrollView;

    @Override
    public void initView() {
        iv_head = getViewById(R.id.iv_head);
        iv_icon = getViewById(R.id.iv_icon);
        scrollView = getViewById(R.id.scrollView);
        scrollView.setZoomView(iv_head);
        iv_head.getLayoutParams().height= AndroidUtil.getScreenW(this.getActivity(),false)*510/960;
        iv_head.requestLayout();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_head_expand;
    }
}
