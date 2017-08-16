package com.fivefivelike.themvpbase.adapter;

import android.content.Context;
import android.widget.TextView;

import com.fivefivelike.themvpbase.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by liugongce on 2017/7/28.
 */

public class MainAdapter extends CommonAdapter<String> {
    private TextView tv_name;

    public MainAdapter(Context context, List<String> datas) {
        super(context, R.layout.adapter_main, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        tv_name = holder.getView(R.id.tv_name);
        tv_name.setText(s);
    }
}
