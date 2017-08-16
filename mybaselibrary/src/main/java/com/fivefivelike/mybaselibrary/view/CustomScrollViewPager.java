package com.fivefivelike.mybaselibrary.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @ author      Qsy
 * @ date        17/2/11 上午11:42
 * @ description 自定义是否可以横向滑动的ViewPager, 默认不能横向滑动
 */
public class CustomScrollViewPager extends ViewPager {
    private boolean canScrollHorizontal = false;

    public CustomScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollViewPager(Context context) {
        super(context);
    }

    public void setCanScrollHorizontal(boolean canScrollHorizontal) {
        this.canScrollHorizontal = canScrollHorizontal;
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return canScrollHorizontal && super.onTouchEvent(arg0);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return canScrollHorizontal && super.onInterceptTouchEvent(arg0);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }


}
