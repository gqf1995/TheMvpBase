/*
 * AUTHOR：Yan Zhenjie
 *
 * DESCRIPTION：create the File, and add the content.
 *
 * Copyright © ZhiMore. All Rights Reserved
 *
 */
package com.fivefivelike.themvpbase.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.fivefivelike.mybaselibrary.utils.AndroidUtil;
import com.fivefivelike.mybaselibrary.utils.GlobleContext;
import com.fivefivelike.mybaselibrary.view.widget.PhotoViewAttacher;
import com.fivefivelike.themvpbase.util.Httpurl;

import java.io.File;
import java.util.List;

public class ImageAdapter extends PagerAdapter {

    private List<String> list;
    private Context mContext;
    private int contentHeight;
    public ImageAdapter(Context mContext, List<String> list, int contentHeight) {
        this.list = list;
        this.mContext=mContext;
        this.contentHeight=contentHeight;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
//        return list!=null&&list.size()==0?POSITION_NONE:super.getItemPosition(object);
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final ImageView imageView = new ImageView(container.getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        container.addView(imageView);
        final PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);
        String url = list.get(position);
        if (!TextUtils.isEmpty(url) && !url.startsWith("http://") && url.contains("upload")) {
            url = Httpurl.BASE_URL+ File.separator + url;
        }
        Glide.with(GlobleContext.getInstance().getApplicationContext())
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        imageView.setImageBitmap(resource);
                        attacher.update();
                        int height = resource.getHeight();
                        int width = resource.getWidth();
                        int bitmapSize = height / width;
                        int contentSize = contentHeight / AndroidUtil.getScreenW(mContext,false);
                        if (height > width && bitmapSize >= contentSize) {
                            attacher.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        } else {
                            attacher.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        }
                    }
                });
        return imageView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(((View) object));

    }
}
