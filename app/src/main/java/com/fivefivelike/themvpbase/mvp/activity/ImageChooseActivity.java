package com.fivefivelike.themvpbase.mvp.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BaseActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.FileUtil;
import com.fivefivelike.themvpbase.R;
import com.fivefivelike.themvpbase.adapter.AddPicAdapter;
import com.fivefivelike.themvpbase.mvp.delegate.ImageChooseDelegate;
import com.fivefivelike.themvpbase.util.UIHelper;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.durban.Controller;
import com.yanzhenjie.durban.Durban;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liugongce on 2017/7/28.
 */

public class ImageChooseActivity extends BaseActivity<ImageChooseDelegate> {
    AddPicAdapter addPicAdapter;
    ArrayList<String> list;

    @Override
    protected Class<ImageChooseDelegate> getDelegateClass() {
        return ImageChooseDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("图片选择"));
        initRecycleView();
    }

    private void initRecycleView() {
        list = new ArrayList<>();
        addPicAdapter = new AddPicAdapter(list, this);
        GridLayoutManager manager = new GridLayoutManager(this, 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        viewDelegate.recycleview.setLayoutManager(manager);
        final int dimension = getResources().getDimensionPixelSize(R.dimen.trans_8px);
        viewDelegate.recycleview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                if (position < 4) {
                    if (position == 3) {
                        outRect.set(0, 0, 0, 0);
                    } else {
                        outRect.set(0, 0, dimension, 0);
                    }
                } else {
                    if (position % 4 == 3) {
                        outRect.set(0, dimension, 0, 0);
                    } else {
                        outRect.set(0, dimension, dimension, 0);
                    }
                }
            }
        });
        viewDelegate.recycleview.setAdapter(addPicAdapter);
        addPicAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                int itemViewType = addPicAdapter.getItemViewType(position);
                switch (itemViewType) {
                    case AddPicAdapter.TYPE_ADD:
                        Album.album(ImageChooseActivity.this)
                                .toolBarColor(ContextCompat.getColor(mContext, R.color.blue)) // Toolbar color.
                                .statusBarColor(ContextCompat.getColor(mContext, R.color.blue)) // StatusBar color.
                                .title("相册")
                                .selectCount(9)
                                .columnCount(3)
                                .camera(true)
                                .checkedList(list)
                                .start(100);
                        break;
                    case AddPicAdapter.TYPE_PIC:
                        UIHelper.toBigImage(mContext, ImageActivity.BIG_IMAGE_CHOOSE_MORE, position, list, true);
                        break;
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {//选择图
            if (requestCode == 100) {
                ArrayList<String> pathList = Album.parseResult(data);
                Durban.with(this)
                        // Che title of the UI.
                        .title("裁剪")
                        .toolBarColor(ContextCompat.getColor(mContext, R.color.blue)) // Toolbar color.
                        .statusBarColor(ContextCompat.getColor(mContext, R.color.blue)) // StatusBar color.
                        // Image path list/array.
                        .inputImagePaths(pathList)
                        // Image output directory.
                        .outputDirectory(FileUtil.getCropPath(this))
                        // Image size limit.
                        .maxWidthHeight(500, 500)
                        // Aspect ratio.
                        .aspectRatio(1, 1)
                        // Output format: JPEG, PNG.
                        .compressFormat(Durban.COMPRESS_JPEG)
                        // Compress quality, see Bitmap#compress(Bitmap.CompressFormat, int, OutputStream)
                        .compressQuality(90)
                        // Gesture: ROTATE, SCALE, ALL, NONE.
                        .gesture(Durban.GESTURE_ALL)
                        .controller(
                                Controller.newBuilder() // Create Builder of Controller.
                                        .enable(false) // Enable the control panel.
                                        .rotation(true) // Rotation button.
                                        .rotationTitle(true) // Rotation button title.
                                        .scale(true) // Scale button.
                                        .scaleTitle(true) // Scale button title.
                                        .build()) // Create Controller Config.
                        .requestCode(200)
                        .start();
            } else if (requestCode == 200) {
                ArrayList<String> mImageList = Durban.parseResult(data);
                list.clear();
                if (mImageList != null) {
                    list.addAll(mImageList);
                }
                addPicAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 0x123 && resultCode == ImageActivity.BIG_IMAGE_BACK_RESULE_CODE) {//图片预览操作回来
            List<String> dList = (List<String>) data.getSerializableExtra(ImageActivity.BIG_IMAGE_BACK_PATH);
            list.clear();
            if (dList != null) {
                list.addAll(dList);
            }
            addPicAdapter.notifyDataSetChanged();
        }
    }
}
