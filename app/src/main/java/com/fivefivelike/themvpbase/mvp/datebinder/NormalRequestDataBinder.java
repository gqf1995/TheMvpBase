package com.fivefivelike.themvpbase.mvp.datebinder;

import com.fivefivelike.mybaselibrary.base.BaseDataBind;
import com.fivefivelike.mybaselibrary.http.HttpRequest;
import com.fivefivelike.mybaselibrary.http.RequestCallback;
import com.fivefivelike.themvpbase.mvp.delegate.NormalRequestDelegate;
import com.fivefivelike.themvpbase.util.Httpurl;

import rx.Subscription;

/**
 * Created by liugongce on 2017/7/28.
 */

public class NormalRequestDataBinder extends BaseDataBind<NormalRequestDelegate> {

    public NormalRequestDataBinder(NormalRequestDelegate viewDelegate) {
        super(viewDelegate);
    }
    public Subscription getData(RequestCallback requestCallback, String page, String pagesize) {
        getBaseMap();
        baseMap.put("pagesize", pagesize);
        baseMap.put("page", page);
        baseMap.put("cid", "136");
        return new HttpRequest.Builder()
                .setRequestCode(0x123)
                .setRequestUrl(Httpurl.NEWS_INDEX)
                .setRequestName("测试api")
                .setRequestObj(baseMap)
                .setShowDialog(true)
                .setDialog(viewDelegate.getNetConnectDialog())
                .setRequestCallback(requestCallback)
                .build()
                .RxSendRequest();
    }
}
