package com.fivefivelike.themvpbase.mvp.activity;

import android.content.Intent;

import com.fivefivelike.mybaselibrary.base.BaseActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.view.RollViewPage;
import com.fivefivelike.themvpbase.R;
import com.fivefivelike.themvpbase.mvp.delegate.RollViewDelegate;
import com.fivefivelike.themvpbase.util.UIHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liugongce on 2017/7/28.
 */

public class RollViewActivity extends BaseActivity<RollViewDelegate> {
    @Override
    protected Class<RollViewDelegate> getDelegateClass() {
        return RollViewDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("美女"));
        final List<String> list = new ArrayList<>();
        list.add("http://www.fuhaodq.com/d/file/201706/16/2uucyj1vlhyvjjr2779.jpg");
        list.add("http://pic.eastlady.cn/uploads/tp/201706/9999/b65bdc5775.jpg");
        list.add("http://dynamic-image.yesky.com/740x-/uploadImages/2016/058/45/F5GG44HP48QH.jpg");
        list.add("http://picture.youth.cn/qtdb/201611/W020161116350101705223.jpg");
        list.add("http://pic.gansudaily.com.cn/0/10/41/83/10418360_999008.jpg");
        initPage(list);
    }

    private void initPage(final List<String> list) {
        new RollViewPage.BannerBuilder(this)
                .setRl(viewDelegate.relativelayout)
                .setCirculation(true)
                .setBanList(list)
                .setShowDot(true)
                .setDotSelectorColorResouse(R.color.red)
                .setDotNormalColorResouse(R.color.white)
                .setDotSizeResouse(R.dimen.trans_10px)
                .setMode(RollViewPage.Mode.middle)
                .setBack(new RollViewPage.ImageClickBack() {
                    @Override
                    public void imageClickListener(int index) {
                        UIHelper.toBigImage(mContext, ImageActivity.BIG_IMAGE_CHOOSE_MORE, index, list, true);
                    }
                }).build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImageActivity.BIG_IMAGE_BACK_RESULE_CODE) {
            List<String> dataList = (List<String>) data.getSerializableExtra(ImageActivity.BIG_IMAGE_BACK_PATH);
            initPage(dataList);
        }
    }
}
