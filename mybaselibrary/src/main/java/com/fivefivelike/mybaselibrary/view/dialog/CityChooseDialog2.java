package com.fivefivelike.mybaselibrary.view.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.fivefivelike.mybaselibrary.R;
import com.fivefivelike.mybaselibrary.entity.AreaObj;
import com.fivefivelike.mybaselibrary.utils.AndroidUtil;
import com.fivefivelike.mybaselibrary.utils.AreaUtils;
import com.fivefivelike.mybaselibrary.view.wheelView.CityChooseAdapter;
import com.fivefivelike.mybaselibrary.view.wheelView.OnWheelChangedListener;
import com.fivefivelike.mybaselibrary.view.wheelView.WheelView;

import java.util.List;


public class CityChooseDialog2 extends BaseDialog implements OnWheelChangedListener {
	private OnChooseCityListener chooseListner;
	public CityChooseDialog2(Activity context, OnChooseCityListener chooseListener) {
		super(context);
		this.chooseListner=chooseListener;
	}
	private WheelView province;
	private WheelView city;
	private List<AreaObj> proList;
	private List<AreaObj> cityList;
	private TextView cancel;
	private TextView commit;
	private AreaObj proObj,cityObj;
	@Override
	protected int getLayout() {
		return R.layout.dialog_citychoose2;
	}
	@Override
	protected void startInit() {
		setShowLoaction(Loction.BUTTOM);
		setWindow();
		province=getView(R.id.wheel_province);
		city=getView(R.id.wheel_city);
		province.TEXT_SIZE=(AndroidUtil.getScreenSize(mContext, 2) / 80) *2;
		city.TEXT_SIZE=(AndroidUtil.getScreenSize(mContext, 2) / 80) * 2;
		cancel=getView(R.id.cancel);
		commit=getView(R.id.commit);
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		commit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
				chooseListner.chooseBack(proObj, cityObj);
			}
		});
		province.addChangingListener(this);
		city.addChangingListener(this);
		proList= AreaUtils.getInstance().getProList();
		if(proList!=null&&proList.size()>0){
			proObj=proList.get(0);
		String[]proStr=new String[proList.size()];
		for (int i = 0; i < proList.size(); i++) {
			proStr[i]=proList.get(i).getName();
		}
		province.setAdapter(new CityChooseAdapter(proList));
		province.setVisibleItems(7);
		city.setVisibleItems(7);
		province.setCurrentItem(0);
		updateCities();
		}
	}
	private void updateCities() {
		String[]cityStr;
		int current=province.getCurrentItem();
		cityList=AreaUtils.getInstance().getCityList(proList.get(current).getAreaid());
		if(cityList!=null&&cityList.size()>0){
			cityObj=cityList.get(0);
			cityStr=new String[cityList.size()];
		for (int i = 0; i < cityList.size(); i++) {
			cityStr[i]=cityList.get(i).getName();
		}
		}else {
			cityStr = new String[] { "" };
		}
		city.setAdapter(new CityChooseAdapter(cityList));
		city.setCurrentItem(0);
	}
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		if (wheel == province) {
			updateCities();
			proObj=proList.get(newValue);
		} else if (wheel == city) {
			cityObj=cityList.get(newValue);
		}
	}
	public interface OnChooseCityListener{
		public void chooseBack(AreaObj province, AreaObj city);
	}
}
