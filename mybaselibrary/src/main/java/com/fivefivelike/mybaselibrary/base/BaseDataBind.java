package com.fivefivelike.mybaselibrary.base;


import com.fivefivelike.mybaselibrary.mvp.databind.IDataBind;
import com.fivefivelike.mybaselibrary.mvp.view.IDelegate;
import com.fivefivelike.mybaselibrary.utils.SaveUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liugongce on 2017/7/3.
 */

public abstract class BaseDataBind<T extends IDelegate> implements IDataBind<T> {
    protected T viewDelegate;
    protected Map<String, String> baseMap;

    public BaseDataBind(T viewDelegate) {
        this.viewDelegate = viewDelegate;
    }

    protected Map<String, String> getBaseMap() {
        baseMap = new HashMap<>();
        return baseMap;
    }

    protected Map<String, String> getBaseMapWithUid() {
        getBaseMap();
        baseMap.put("uid", SaveUtil.getInstance().getString("uid"));
        baseMap.put("token", SaveUtil.getInstance().getString("token"));
        return baseMap;
    }

}
