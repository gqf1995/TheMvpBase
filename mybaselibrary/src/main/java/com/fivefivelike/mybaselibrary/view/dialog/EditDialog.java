package com.fivefivelike.mybaselibrary.view.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.R;


/**
 * Created by liugongce on 2016/12/28.
 */

public class EditDialog extends BaseDialog {
    TextView tvTitle;
    TextView tvContent;
    TextView tvLeft;
    TextView tvRight;
    OnEditListener onEditListener;
    boolean clickDismiss = true;

    public EditDialog(Activity context, OnEditListener onEditListener) {
        super(context);
        this.onEditListener = onEditListener;
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_edit;
    }

    @Override
    protected void startInit() {
        tvTitle = getView(R.id.title);
        tvContent = getView(R.id.content);
        tvLeft = getView(R.id.left);
        tvRight = getView(R.id.right);
        tvLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        setWindow(40, 0, 40, 0);
        setShowLoaction(Loction.CENTER);
    }

    public EditDialog setTitle(String title) {
        tvTitle.setText(title);
        return this;
    }

    public EditDialog setIsCancel(boolean isCancelAble) {
        setCancelable(isCancelAble);
        setCanceledOnTouchOutside(isCancelAble);
        return this;
    }

    public EditDialog setContent(String content) {
        tvContent.setText(content);
        return this;
    }

    public EditDialog setLeft(String left) {
        tvLeft.setText(left);
        return this;
    }

    public EditDialog setRight(String right) {
        tvRight.setText(right);
        return this;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.left) {
            if (clickDismiss) {
                dismiss();
            }
            onEditListener.onClickLeft();
        } else if (view.getId() == R.id.right) {
            if (clickDismiss) {
                dismiss();
            }
            onEditListener.onClickRight();
        }
    }

    public EditDialog setLeftTextColor(int color) {
        tvLeft.setTextColor(color);
        return this;
    }

    public EditDialog setRightTextColor(int color) {
        tvRight.setTextColor(color);
        return this;
    }

    public EditDialog setTitleTextColor(int color) {
        tvTitle.setTextColor(color);
        return this;
    }

    public EditDialog setContentTextColor(int color) {
        tvContent.setTextColor(color);
        return this;
    }

    public EditDialog myShow() {
        show();
        return this;
    }

    public EditDialog setClickDismiss(boolean clickDismiss) {
        this.clickDismiss = clickDismiss;
        return this;
    }

    public interface OnEditListener {
        void onClickLeft();

        void onClickRight();
    }
}
