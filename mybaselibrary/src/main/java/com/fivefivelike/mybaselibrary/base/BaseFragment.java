package com.fivefivelike.mybaselibrary.base;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fivefivelike.mybaselibrary.R;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.mvp.presenter.FragmentPresenter;
import com.fivefivelike.mybaselibrary.utils.SaveUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liugongce on 2017/7/7.
 */

public abstract class BaseFragment<T extends BaseDelegate> extends FragmentPresenter<T> implements View.OnClickListener {
    protected List<Fragment> list_frl;
    private int cuurent = 0x001;
    protected int fragmentId = 0;
    public static List<String> doubleClickActList = new ArrayList<>();

    protected void initToolbar(ToolbarBuilder toolbarBuilder){
        viewDelegate.initToolBar((AppCompatActivity) getActivity(),this,toolbarBuilder);
    }

    //添加fragment
    protected void addFragment(Fragment fragment) {
        if (list_frl == null) {
            list_frl = new ArrayList<>();
        }
        list_frl.add(fragment);
    }

    //显示某一个fragment
    protected void showFrl(int index) {
        if (fragmentId == 0) {
            return;
        }
        if (cuurent != 0x001 && getCurrentFrl() == list_frl.get(index)) {
            return;
        }
        FragmentManager manage = getChildFragmentManager();
        FragmentTransaction transaction = manage.beginTransaction();
        Fragment frl = list_frl.get(index);
        if (frl.isAdded()) {
            frl.onResume();
        } else {
            transaction.add(fragmentId, frl);
        }
        for (int i = 0; i < list_frl.size(); i++) {
            Fragment fragment = list_frl.get(i);
            FragmentTransaction ft = this.getChildFragmentManager()
                    .beginTransaction();
            if (index == i) {
                ft.show(fragment);
            } else {
                ft.hide(fragment);
            }
            ft.commitAllowingStateLoss();
        }
        transaction.commitAllowingStateLoss();
        cuurent = index;
    }

    //得到当前的fragment
    protected Fragment getCurrentFrl() {
        return list_frl.get(cuurent);
    }

    /**
     * 前往页面
     *
     * @param intent 跳转的intent
     */
    public void gotoActivty(Intent intent) {
        gotoActivty(intent, false);
    }

    /**
     * @param intent 跳转参数设置
     * @param isBack 是否带返回值
     */
    public void gotoActivty(Intent intent, boolean isBack) {

        if (isBack) {
            startActivityForResult(intent, 0x123);
        } else {
            startActivity(intent);
        }
    }

    /**
     * @param activity 要跳转的页面
     */
    public void gotoActivty(BaseActivity activity) {
        gotoActivty(activity, null);
    }

    /***
     * @param activity 要跳转的页面
     * @param intent   要携带的参数
     */
    public void gotoActivty(BaseActivity activity, Intent intent) {
        gotoActivty(activity, intent, false);
    }

    /**
     * @param activity 跳转的页面
     * @param intent   携带的参数
     * @param isBack   是否带返回值
     */
    public void gotoActivty(BaseActivity activity, Intent intent,
                            boolean isBack) {
        if (intent == null) {
            intent = new Intent(getActivity(), activity.getClass());
        } else {
            intent.setClass(getActivity(), activity.getClass());
        }
        if (isBack) {
            startActivityForResult(intent, 0x123);
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.toolbar_img1) {
            clickRightIv();
        } else if (id == R.id.toolbar_img2) {
            clickRightIv2();
        } else if (id == R.id.toolbar_subtitle) {
            clickRightTv();
        }
    }

    protected void clickRightIv() {
    }

    protected void clickRightIv2() {
    }

    protected void clickRightTv() {
    }
    /**
     * 请求的map
     */
    protected Map<String, String> baseMap;
    /**
     * 获取map
     *
     * @return
     */
    protected Map getBaseMap() {
        baseMap = new HashMap<String, String>();
        return baseMap;
    }

    /**
     * 获取带uid的map
     *
     * @return
     */
    protected Map getBaseMapWithUid() {
        baseMap = new HashMap<>();
        baseMap.put("uid", SaveUtil.getInstance().getString("uid"));
        baseMap.put("token", SaveUtil.getInstance().getString("token"));
        return baseMap;
    }
}
