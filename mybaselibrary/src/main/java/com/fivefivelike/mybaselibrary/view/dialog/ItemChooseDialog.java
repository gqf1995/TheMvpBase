package com.fivefivelike.mybaselibrary.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liugongce on 2016/11/26.
 */

public class ItemChooseDialog {
    private Dialog dialog;
    private TextView title;
    private TextView cancel;
    private LinearLayout content_layout;
    private LinearLayout title_layout;
    private List<SheetItem> sheetItemList;
    private Context context;

    public ItemChooseDialog(Context context) {
        this.context = context;
    }

    public ItemChooseDialog builder() {
        View v = LayoutInflater.from(context).inflate(R.layout.dialog_choose_dialog, null);
        title = (TextView) v.findViewById(R.id.title);
        cancel = (TextView) v.findViewById(R.id.cancel);
        content_layout = (LinearLayout) v.findViewById(R.id.content_layout);
        title_layout = (LinearLayout) v.findViewById(R.id.title_layout);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog = new Dialog(context, R.style.baseDialog);
        dialog.setContentView(v);
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        return this;
    }

    public ItemChooseDialog setTitle(String titleStr) {
        title_layout.setVisibility(View.VISIBLE);
        title.setText(titleStr);
        return this;
    }

    public ItemChooseDialog setTitle(String titleStr, int color) {
        title_layout.setVisibility(View.VISIBLE);
        title.setText(titleStr);
        title.setTextColor(color);
        return this;
    }

    public ItemChooseDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public ItemChooseDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public ItemChooseDialog addItem(String strItem, int color,
                                    OnItemClickListener listener) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<>();
        }
        sheetItemList.add(new SheetItem(strItem, color, listener));
        return this;
    }

    public void show() {
        setItems();
        dialog.show();
    }

    private void setItems() {
        if (sheetItemList == null || sheetItemList.size() <= 0) {
            return;
        }
        int size = sheetItemList.size();
        for (int i = 0; i < size; i++) {
            SheetItem sheetItem = sheetItemList.get(i);
            String strItem = sheetItem.name;
            int color = sheetItem.color;
            final OnItemClickListener listener = sheetItem.itemClickListener;
            View itemView = LayoutInflater.from(context).inflate(R.layout.item_dialog_choos, null);
            TextView textView = (TextView) itemView.findViewById(R.id.textView);
            textView.setText(strItem);
            if (color != 0) {
                textView.setTextColor(color);
            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick();
                    dialog.dismiss();
                }
            });
            content_layout.addView(itemView);
        }
    }

    public interface OnItemClickListener {
        void onClick();
    }

    public class SheetItem {
        String name;
        OnItemClickListener itemClickListener;
        int color;

        public SheetItem(String name, int color,
                         OnItemClickListener itemClickListener) {
            this.name = name;
            this.color = color;
            this.itemClickListener = itemClickListener;
        }
    }

}
