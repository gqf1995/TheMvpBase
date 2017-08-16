package com.fivefivelike.themvpbase.mvp.activity;

import android.support.v7.widget.LinearLayoutManager;

import com.fivefivelike.mybaselibrary.base.BasePullActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.fivefivelike.themvpbase.adapter.NewsAdapter;
import com.fivefivelike.themvpbase.entity.NewsEntity;
import com.fivefivelike.themvpbase.mvp.datebinder.PageRequestDataBinder;
import com.fivefivelike.themvpbase.mvp.delegate.PageRequestDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liugongce on 2017/7/28.
 */

public class PageRequestActivity extends BasePullActivity<PageRequestDelegate, PageRequestDataBinder> {
    private List<NewsEntity.ListBean> list;
    private NewsAdapter adapter;

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("新闻列表"));
        initRv();
        refreshData();
    }

    @Override
    protected Class<PageRequestDelegate> getDelegateClass() {
        return PageRequestDelegate.class;
    }

    private void initRv() {
        list = new ArrayList<>();
        adapter = new NewsAdapter(this, list);
        initRecycleViewPull(adapter, new LinearLayoutManager(this));
    }

    @Override
    public PageRequestDataBinder getDataBinder(PageRequestDelegate viewDelegate) {
        return new PageRequestDataBinder(viewDelegate);
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                NewsEntity newsEntity = GsonUtil.getInstance().json2Bean(data, NewsEntity.class);
                getDataBack(list, newsEntity.getList(), adapter);//调用封装好的
                break;
        }
    }

    @Override
    protected void refreshData() {
        addRequest(binder.getData(this, String.valueOf(viewDelegate.page), String.valueOf(viewDelegate.pagesize)));
    }
}
