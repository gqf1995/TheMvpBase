package com.fivefivelike.themvpbase.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fivefivelike.mybaselibrary.utils.AndroidUtil;
import com.fivefivelike.mybaselibrary.utils.paginate.ViewHolder;
import com.fivefivelike.themvpbase.R;
import com.fivefivelike.themvpbase.util.GlideUtils;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liugongce on 2017/3/14.
 */

public class AddPicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> list;
    private Context mContext;
    private LayoutInflater inflater;
    public static final int TYPE_ADD = 0x6545;
    public static final int TYPE_PIC = 0x54236;
    private OnItemClickListener onItemClickListener;
    private  int size;
    private List<String>pathList;

    public List<String> getPathList() {
        return pathList;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public AddPicAdapter(List<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        pathList=new ArrayList<>();
        inflater = LayoutInflater.from(mContext);
        size= (AndroidUtil.getScreenW(mContext,false)-mContext.getResources().getDimensionPixelSize(R.dimen.trans_20px)*2-
                mContext.getResources().getDimensionPixelSize(R.dimen.trans_8px)*3)/4;
    }
    public void refreshDataWithList(){
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_ADD:
                view = inflater.inflate(R.layout.item_add_pic, parent, false);
                ViewHolder viewHolder = new ViewHolder(view);
                setListener(parent,viewHolder,viewType);
                return viewHolder;
            case TYPE_PIC:
                view = inflater.inflate(R.layout.item_pic, parent, false);
                ShowPicViewHolder showPicViewHolder = new ShowPicViewHolder(view);
                setListener(parent,showPicViewHolder,viewType);
                return  showPicViewHolder;
        }

        return null;
    }
    protected boolean isEnabled(int viewType) {
        return true;
    }
    protected void setListener(final ViewGroup parent, final RecyclerView.ViewHolder viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    onItemClickListener.onItemClick(v, viewHolder , position);
                }
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener != null) {
                    int position = viewHolder.getAdapterPosition();
                    return onItemClickListener.onItemLongClick(v, viewHolder, position);
                }
                return false;
            }
        });
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ShowPicViewHolder) {
            ShowPicViewHolder showPicViewHolder = (ShowPicViewHolder) holder;
            GlideUtils.loadImage(list.get(position),showPicViewHolder.iv_pic);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == list.size()) {
            return TYPE_ADD;
        } else {
            return TYPE_PIC;
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }
    class ShowPicViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_pic;

        public ShowPicViewHolder(View itemView) {
            super(itemView);
            itemView.getLayoutParams().width = size;
            itemView.getLayoutParams().height = size;
            itemView.requestLayout();
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
        }
    }
    class AddPicViewHolder extends RecyclerView.ViewHolder{
        public AddPicViewHolder(View itemView) {
            super(itemView);
            itemView.getLayoutParams().width = size;
            itemView.getLayoutParams().height = size;
            itemView.requestLayout();
        }
    }

}
