package com.fivefivelike.themvpbase.mvp.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.fivefivelike.mybaselibrary.base.BaseDataBindActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.themvpbase.adapter.NewsAdapter;
import com.fivefivelike.themvpbase.entity.NewsEntity;
import com.fivefivelike.themvpbase.mvp.datebinder.NormalRequestDataBinder;
import com.fivefivelike.themvpbase.mvp.delegate.NormalRequestDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liugongce on 2017/7/28.
 */

public class NormalRequestActivity extends BaseDataBindActivity<NormalRequestDelegate, NormalRequestDataBinder> {
    private List<NewsEntity.ListBean> list;
    private NewsAdapter adapter;

    @Override
    public NormalRequestDataBinder getDataBinder(NormalRequestDelegate viewDelegate) {
        return new NormalRequestDataBinder(viewDelegate);
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("新闻列表"));
        initRv();
        addRequest(binder.getData(this, "1", "20"));
    }

    private void initRv() {
        list = new ArrayList<>();
        adapter = new NewsAdapter(this, list);
        viewDelegate.recycleview.setLayoutManager(new LinearLayoutManager(this));
        viewDelegate.recycleview.setAdapter(adapter);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                NewsEntity newsEntity = GsonUtil.getInstance().json2Bean(data, NewsEntity.class);
                List<NewsEntity.ListBean> list = newsEntity.getList();
                if (list != null && list.size() > 0) {
                    this.list.addAll(list);
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }

    @Override
    protected Class<NormalRequestDelegate> getDelegateClass() {
        return NormalRequestDelegate.class;
    }
}
