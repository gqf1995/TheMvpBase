package com.fivefivelike.themvpbase.adapter;

import android.content.Context;
import android.widget.TextView;

import com.fivefivelike.themvpbase.R;
import com.fivefivelike.themvpbase.entity.NewsEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by liugongce on 2017/7/28.
 */

public class NewsAdapter extends CommonAdapter<NewsEntity.ListBean> {
    private TextView tv_name;

    public NewsAdapter(Context context, List<NewsEntity.ListBean> datas) {
        super(context, R.layout.adapter_main, datas);
    }

    @Override
    protected void convert(ViewHolder holder, NewsEntity.ListBean listBean, int position) {
        tv_name=holder.getView(R.id.tv_name);
        tv_name.setText(listBean.getName());
    }
}
