package com.fivefivelike.mybaselibrary.utils;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.TimeZone;

/**
 * 获取屏幕尺寸{@link #getScreenSize(Activity, int)#getScreenW(Context, boolean)}
 * 复制字符串{@link #copyChar(Activity, String)}
 * 隐藏显示软键盘{@link #HideSoftInput(Activity, boolean)#showSoftInput(Activity, EditText, boolean)}
 * onCreate()中获取组件的宽高{@link #getViewSize(View, int)}
 * 获取时区{@link #getZone()}
 * 获取版本号和版本名{@link #getVersionCode(Activity, boolean)}
 * 获取包名{@link #getPageName(Context)}
 * 获取手机型号{@link #getPhoeMODEL()}
 * 获取手机系统版本号{@link #getPhoeSystemVerson()}
 * 拨打电话{@link #goTel(Activity, String)#goTel(Activity, String, boolean)}
 * 发送短信{@link #sendMsg(Activity, String, String)#sendMsgGroup(Activity, String, String)}
 * 获取状态栏高度{@link #getStatusBarHeight(Context)}
 * 获取签名信息{@link #getAppSign(Context)}
 * 开启关闭系统震动{@link #isOpenShake(Context, boolean)}
 * 开启关闭系统声音{@link #isOpenVoice(Context, boolean)}
 * 一键设置webview{@link #webviewRegister(WebView)}
 * 通知栏是否开启{@link #isNotificationEnabled(Context)}
 * 获取状态栏高度{@link #getStatusBarHeight(Context)}
 * 克隆对象{@link #cloneTo(Object)}
 */
public class AndroidUtil {
    /**
     * 获取屏幕尺寸
     *
     * @param context
     * @param type    1为宽度 2为高度
     * @return
     */
    public static int getScreenSize(Activity context, int type) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        if (type == 1) {
            return dm.widthPixels;
        } else {
            return dm.heightPixels;
        }
    }

    /**
     * 获取屏幕像素尺寸
     *
     * @return : true:高 false:宽
     * @author yff 2013-10-31下午1:08:22
     */
    public static int getScreenW(Context context, boolean isHeight) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metrics);
        return isHeight ? metrics.heightPixels : metrics.widthPixels;
    }

    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public static void copyChar(Activity context, String content) {
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(context.CLIPBOARD_SERVICE);
        cmb.setText(content);
        ToastUtil.show(context, "复制成功");
    }

    /**
     * 隐藏软键盘(isShow 可以当做不存在)
     */
    @SuppressLint("NewApi")
    public static void HideSoftInput(Activity activity, boolean isShow) {
        if (activity == null) {
            return;
        }
        try {
            int tag = isShow ? InputMethodManager.SHOW_IMPLICIT
                    : InputMethodManager.HIDE_NOT_ALWAYS;
            ((InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(activity.getCurrentFocus()
                            .getWindowToken(), tag);
        } catch (Exception e) {
        }
    }

    /**
     * 显示软键盘 isShow ? 显示 : 隐藏
     */
    @SuppressLint("NewApi")
    public static void showSoftInput(Activity activity, EditText et,
                                     boolean isShow) {
        if (activity == null || et == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            // 显示软键盘
            imm.showSoftInputFromInputMethod(et.getWindowToken(), 0);
            // 切换软键盘的显示与隐藏
            imm.toggleSoftInputFromWindow(et.getWindowToken(), 0,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            // 显示软键盘
            imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
            // //切换软键盘的显示与隐藏
            // imm.toggleSoftInputFromWindow(et.getWindowToken(), 0,
            // InputMethodManager.SHOW_IMPLICIT);
        }

    }

    /**
     * OnCreate中获得组件大小
     * params2: 1.宽 2.高
     */
    public static int getViewSize(View view, int type) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();
        if (type == 1) {
            return width;
        } else {
            return height;
        }
    }

    /**
     * 获取时区 2015-6-8
     */
    public static String getZone() {
        TimeZone tz = TimeZone.getDefault();

        return tz.getID();
    }

    /**
     * true:为正数 false:为浮点数 获取版本号或版本名字
     *
     * @return
     */
    public static String getVersionCode(Activity activity, boolean isVersonCode) {

        if (activity == null) {
            return "0";
        }
        try {
            PackageInfo info = activity.getPackageManager().getPackageInfo(
                    getPageName(activity), 0);
            return isVersonCode ? info.versionCode + "" : info.versionName;
        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }
        return "0";
    }

    /**
     * 获取包名 2015-6-7
     */
    public static String getPageName(Context context) {

        return context != null ? context.getPackageName() : "";
    }

    /**
     * 如HUAWEI G610 2015-6-7
     */
    public static String getPhoeMODEL() {

        return Build.MODEL;
    }

    /**
     * 如HUAWEI 2015-6-7
     */
    @SuppressLint("NewApi")
    public static String getPhoeMANUFACTURER() {

        return Build.MANUFACTURER;
    }

    /**
     * 获取手机像素
     *
     * @param context
     * @return
     */
    @SuppressLint("NewApi")
    public static int getDensityDpi(Context context) {
        if (context == null) {
            return 0;
        }
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metrics);
        return metrics.densityDpi;
    }

    /**
     * 获取手机系统版本号
     *
     * @return
     */
    public static String getPhoeSystemVerson() {

        return Build.VERSION.RELEASE;
    }

    /**
     * 拨打电话
     *
     * @param context
     * @param tel
     */

    public static void goTel(Activity context, String tel) {
        goTel(context, tel, false);
    }

    /**
     * 拨打电话
     *
     * @param context
     * @param tel
     * @param isDirectly : 是否直接拨号
     */
    public static void goTel(Activity context, String tel, boolean isDirectly) {
        if (StringUtil.isBlank(tel)) {
            ToastUtil.show(context, "号码格式不对");
            return;
        }
        Intent intent = new Intent(!isDirectly ? Intent.ACTION_DIAL
                : Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + tel);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 调用系统短信页面
     *
     * @param context
     * @param tel
     * @param message
     */
    public static void sendMsg(Activity context, String tel, String message) {
        if (StringUtil.isBlank(tel)) {
            ToastUtil.show(context, "号码格式不对");
            return;
        }
        Uri uri = Uri.parse("smsto:" + tel);
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
        sendIntent.putExtra("sms_body", message);
        context.startActivity(sendIntent);
    }

    /**
     * 调用系统群发短信页面
     *
     * @param context
     * @param tel     (电话号码用;隔开)
     * @param message
     */
    public static void sendMsgGroup(final Activity context, String tel,
                                    String message) {
        if (StringUtil.isBlank(tel)) {
            ToastUtil.show(context, "号码格式不对");
            return;
        }
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setType("vnd.android-dir/mms-sms");
        sendIntent.putExtra("sms_body", message);
        sendIntent.putExtra("address", tel);
        context.startActivity(sendIntent);
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c;
        Object obj;
        Field field;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获取签名
     *
     * @param c
     * @return
     */
    public static String getAppSign(Context c) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = c.getPackageManager().getPackageInfo(getPageName(c), PackageManager.GET_SIGNATURES);
            Signature[] signs = packageInfo.signatures;
            Signature sign = signs[0];

            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(sign.toByteArray()));
            String pubKey = cert.getPublicKey().toString();

            String signNumber = cert.getSerialNumber().toString(16);
            System.out.println("signName:" + cert.getSigAlgName());
            System.out.println("pubKey:" + pubKey);
            System.out.println("signNumber:" + signNumber);
            System.out.println("subjectDN:" + cert.getSubjectDN().toString());
            return signNumber;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 开启关闭震动
     *
     * @param context
     * @param isOpen
     */
    public static void isOpenShake(Context context, boolean isOpen) {
        Vibrator vibrator = (Vibrator) context
                .getSystemService(Context.VIBRATOR_SERVICE);
        if (isOpen) {
            vibrator.vibrate(1 * 1000);
        } else {
            vibrator.cancel();
        }
    }

    /**
     * 开启关闭声音
     *
     * @param context
     * @param isOpen
     */
    public static void isOpenVoice(Context context, boolean isOpen) {
        Uri uri = RingtoneManager.getActualDefaultRingtoneUri(context,
                RingtoneManager.TYPE_NOTIFICATION);
        Ringtone ringtone = RingtoneManager.getRingtone(context, uri);// 获取名字
        if (isOpen) {
            ringtone.play();
        } else {
            ringtone.stop();
        }

    }

    /**
     * 一键设置webview
     *
     * @param webView
     */
    public static void webviewRegister(WebView webView) {
        // 设置支持JavaScript脚本
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);//否则有些视频放不出来
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 只用于缓存/不适用缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);// LOAD_CACHE_ONLY,LOAD_NO_CACHE
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 不支持放大缩小
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        // NORMAL：正常显示，没有渲染变化。
        // SINGLE_COLUMN：把所有内容放到WebView组件等宽的一列中。 //这个是强制的，把网页都挤变形了
        // NARROW_COLUMNS：可能的话，使所有列的宽度不超过屏幕宽度。 //好像是默认的
        // 自动适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.setVerticalScrollBarEnabled(false); // 取消Vertical ScrollBar显示
        webView.setHorizontalScrollBarEnabled(false); // 取消Horizontal
        // ScrollBar显示
        // webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET) ;
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
    }

    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    /**
     * 通知栏是否开启
     *
     * @param context
     * @return
     */
    @SuppressLint("NewApi")
    public static boolean isNotificationEnabled(Context context) {

        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;

        Class appOpsClass = null;
      /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE,
                    String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 克隆对象
     *
     * @param src 元对象
     * @param <T>
     * @return
     * @throws RuntimeException
     */
    @SuppressWarnings("unchecked")
    public static <T> T cloneTo(T src) throws RuntimeException {
        ByteArrayOutputStream memoryBuffer = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        T dist = null;
        try {
            out = new ObjectOutputStream(memoryBuffer);
            out.writeObject(src);
            out.flush();
            in = new ObjectInputStream(new ByteArrayInputStream(memoryBuffer.toByteArray()));
            dist = (T) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null)
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            if (in != null)
                try {
                    in.close();
                    in = null;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
        return dist;
    }

    public static String getIMEI(Activity activity) {
        TelephonyManager tm = (TelephonyManager) activity.getSystemService(Activity.TELEPHONY_SERVICE);
        return tm.getDeviceSoftwareVersion();//String
    }


}
