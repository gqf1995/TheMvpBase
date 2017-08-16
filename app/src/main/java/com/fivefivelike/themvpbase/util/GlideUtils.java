package com.fivefivelike.themvpbase.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fivefivelike.mybaselibrary.utils.FileUtil;
import com.fivefivelike.mybaselibrary.utils.GlobleContext;
import com.fivefivelike.themvpbase.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.fivefivelike.themvpbase.util.Httpurl.BASE_URL;


/**
 * Created by liugongce on 2016/11/29.
 */

public class GlideUtils {
    public static void loadImage(String url, ImageView icon) {
        if (!TextUtils.isEmpty(url) && !url.startsWith("http") &&  !new File(url).exists()) {
            url = BASE_URL + File.separator+ url;
        }
        Glide.with(GlobleContext.getInstance().getApplicationContext()).load(url).centerCrop().error(R.drawable.loaderror).into(icon);
    }

    public static void loadImageSimple(String url, ImageView icon) {
        if (!TextUtils.isEmpty(url) && !url.startsWith("http") && !new File(url).exists()) {
            url = BASE_URL+ File.separator + url;
        }
        Glide.with(GlobleContext.getInstance().getApplicationContext()).load(url).error(R.drawable.loaderror).into(icon);
    }

    public static void loadImageFitCenter(String url, ImageView icon) {
        if (!TextUtils.isEmpty(url) && !url.startsWith("http") && !new File(url).exists()) {
            url = BASE_URL + File.separator+ url;
        }
        Glide.with(GlobleContext.getInstance().getApplicationContext()).load(url).fitCenter().error(R.drawable.loaderror).into(icon);
    }

    public static void loadImageWithIcon(String url, ImageView icon) {
        if (!TextUtils.isEmpty(url) && !url.startsWith("http") && !new File(url).exists()) {
            url = BASE_URL + File.separator+url;
        }
        Glide.with(GlobleContext.getInstance().getApplicationContext()).load(url).centerCrop().error(R.drawable.loaderror).into(icon);
    }

    public static File saveBitmap(Context context, String name, Bitmap bitmap) {
        // 创建一个位于SD卡上的文件
        File file = new File(FileUtil.getIamgePath(context),
                name);
        if (file.exists()) {
            return file;
        }
        FileOutputStream out = null;
        try {
            // 打开指定文件输出流
            out = new FileOutputStream(file);
            // 将位图输出到指定文件
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    out);
            MediaScannerConnection.scanFile(context, new String[]{file.getAbsolutePath()}, null, null);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
