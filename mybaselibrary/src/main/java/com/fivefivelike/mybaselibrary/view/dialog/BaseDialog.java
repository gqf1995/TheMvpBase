package com.fivefivelike.mybaselibrary.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.fivefivelike.mybaselibrary.R;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

public abstract class BaseDialog extends Dialog implements View.OnClickListener{
	protected Activity mContext;
    protected Map<String,String> baseMap;
    public BaseDialog(Activity context) {
        super(context, R.style.baseDialog);
//        v = LayoutInflater.from(mContext).inflate(getLayout(), null);
        this.mContext =context;
        setContentView(getLayout());
        ButterKnife.bind(this);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        startInit();
    }
    public BaseDialog(Activity context, int style) {
        super(context, style);
        //        v = LayoutInflater.from(mContext).inflate(getLayout(), null);
        this.mContext =context;
        setContentView(getLayout());
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        startInit();
    }
    protected abstract int getLayout();
    protected abstract void startInit();
    //显示位置
    public void setShowLoaction(Loction loc){
        if(loc == Loction.LEFT){
            getWindow().setGravity(Gravity.LEFT);
        }else if(loc == Loction.TOP){
            getWindow().setGravity(Gravity.TOP);
        }else if(loc == Loction.RIGHT){
            getWindow().setGravity(Gravity.RIGHT);
        }else if(loc == Loction.BUTTOM){
            getWindow().setGravity(Gravity.BOTTOM);
        }else {
            getWindow().setGravity(Gravity.CENTER);
        }
    }
    public  int getScreemWidth(){

        return getWindow().getWindowManager().getDefaultDisplay().getWidth();
    }

    public  int getScreemHeight(){

        return getWindow().getWindowManager().getDefaultDisplay().getHeight();
    }
    @SuppressWarnings("unchecked")
	protected <E extends View> E getView(int viewId) {
		return (E) (findViewById(viewId));
	}
    public enum Loction {
        LEFT, RIGHT, BUTTOM, CENTER , TOP
    }
    protected void setWindow(){
    	Window win =getWindow();
		win.getDecorView().setPadding(0, 0, 0, 0);
		WindowManager.LayoutParams lp = win.getAttributes();
		        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
		        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
		        win.setAttributes(lp);
    }
    public void setWindow(int left,int top,int right ,int bottom ){
        Window win =getWindow();
        win.getDecorView().setPadding(left, top, right, bottom);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
    }
    /**
	 * 弹出的toast
	 */
	public void toast(String content) {
		ToastUtil.show(getContext(),content);
	}

    /**
     * 获取map
     * @return
     */
    protected Map getBaseMap(){
        baseMap=new HashMap<String,String>();
        return  baseMap;
    }

    @Override
    public void onClick(View v) {

    }
}
