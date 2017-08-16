package com.fivefivelike.mybaselibrary.view.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.R;


/**
 * Created by liugongce on 2016/9/19.
 */
public class UpdataDialog extends BaseDialog implements  View.OnClickListener {
    private TextView message,left,right;
    private ClickListener onClickListener;
    public UpdataDialog(Activity context, ClickListener onClickListener) {
        super(context);
        this.onClickListener=onClickListener;
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_update;
    }

    @Override
    protected void startInit() {
        message=getView(R.id.message);
        left=getView(R.id.left);
        right=getView(R.id.right);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        setShowLoaction(Loction.CENTER);
        setWindow();
    }
    public void setContent(String content){
     message.setText(content);

    }
    public void setRightGone(){
        getView(R.id.rightLayout).setVisibility(View.GONE);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.left){
            onClickListener.onLeftClick();
        }else
        if(v.getId()==R.id.right){
            onClickListener.onRightClick();
        }
    }
    public interface ClickListener{
        void onLeftClick();
        void onRightClick();
    }

}
