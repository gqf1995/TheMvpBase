package com.fivefivelike.themvpbase.mvp.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.view.dialog.ItemChooseDialog;
import com.fivefivelike.themvpbase.R;
import com.fivefivelike.themvpbase.adapter.ImageAdapter;
import com.fivefivelike.themvpbase.mvp.delegate.ImageDelegate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liugongce on 2017/1/4.
 * 大图预览
 */

public class ImageActivity extends BaseActivity<ImageDelegate> {
    ImageAdapter adapter;
    private List<String> list;
    //多选
    public static final String BIG_IMAGE_CHOOSE_MORE = "list";
    //单选
    public static final String BIG_IMAGE_CHOOSE_SINGLE = "single";
    //是否带删除按钮
    public static final String BIG_IMAGE_IS_DELETE = "delete";
    //进来当前的条目
    public static final String BIG_IMAGE_CURRENT_POSITION = "position";
    //返回的集合
    public static final String BIG_IMAGE_BACK_PATH = "back_path";
    //传进来的集合
    public static final String BIG_IMAGE_INTO_PATH = "into_path";
    public static final int BIG_IMAGE_BACK_RESULE_CODE = 0x45613;
    private String delete = "";
    private boolean isDelete;


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        isDelete = getIntent().getBooleanExtra(BIG_IMAGE_IS_DELETE, false);
        int position = getIntent().getIntExtra(BIG_IMAGE_CURRENT_POSITION, 0);
        delete = isDelete ? "删除" : "";
        initToolbar(new ToolbarBuilder().setTitle("大图预览").setSubTitle("(0/0)" + delete));
        initData();
        //以防止第一页进来的时侯没有触发切换监听出现(0/0)
        if (list.size() > 0) {
            viewDelegate.getmToolbarSubTitle().setText("(" + 1 + "/" + list.size() + ")" + delete);
        }
        initPage(position);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        list = new ArrayList<>();
        String type = getIntent().getType();
        if (type.equals(BIG_IMAGE_CHOOSE_MORE)) {
            ArrayList<String> stringArrayListExtra = getIntent().getStringArrayListExtra(BIG_IMAGE_INTO_PATH);
            if (stringArrayListExtra != null) {
                list.addAll(stringArrayListExtra);
            }
        } else if (type.equals(BIG_IMAGE_CHOOSE_SINGLE)) {
            list.add(getIntent().getStringExtra(BIG_IMAGE_INTO_PATH));
        }
    }

    /**
     * 初始化视图
     *
     * @param position
     */
    private void initPage(int position) {
        adapter = new ImageAdapter(this, list, contentHeight);
        viewDelegate.viewPager.setAdapter(adapter);
        viewDelegate.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                viewDelegate.getmToolbarSubTitle().setText("(" + (position + 1) + "/" + list.size() + ")" + delete);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        viewDelegate.viewPager.setCurrentItem(position);
    }

    @Override
    protected void clickRightTv() {
        super.clickRightTv();
        if (isDelete) {
            new ItemChooseDialog(this).builder().addItem("删除", 0, new ItemChooseDialog.OnItemClickListener() {
                @Override
                public void onClick() {
                    int currentItem = viewDelegate.viewPager.getCurrentItem();
                    int size = list.size();
                    if (size == 1) {
                        list.clear();
                        viewDelegate.getmToolbarSubTitle().setText("(" + 0 + "/" + list.size() + ")" + delete);
                        adapter.notifyDataSetChanged();
                    } else {
                        list.remove(currentItem);
                        adapter.notifyDataSetChanged();
                        if (currentItem > 0 && size > 1) {
                            viewDelegate.viewPager.setCurrentItem(currentItem - 1);
                        } else {
                            viewDelegate.getmToolbarSubTitle().setText("(" + 1 + "/" + list.size() + ")" + delete);
                        }
                    }
                }
            }).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (isDelete) {
            Intent intent = new Intent();
            intent.putExtra(BIG_IMAGE_BACK_PATH, (Serializable) list);
            setResult(BIG_IMAGE_BACK_RESULE_CODE, intent);
        }
        super.onBackPressed();
    }

    private boolean initializeHeight;
    private int contentHeight;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && !initializeHeight) {
            initializeHeight = true;
            View view = findViewById(R.id.viewPager);
            contentHeight = view.getMeasuredHeight();
        }
    }

    @Override
    protected Class<ImageDelegate> getDelegateClass() {
        return ImageDelegate.class;
    }
}
