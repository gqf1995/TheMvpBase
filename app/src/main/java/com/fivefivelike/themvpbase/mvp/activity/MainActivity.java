package com.fivefivelike.themvpbase.mvp.activity;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.fivefivelike.mybaselibrary.base.BaseActivity;
import com.fivefivelike.mybaselibrary.entity.AreaObj;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.ToastUtil;
import com.fivefivelike.mybaselibrary.view.dialog.BirthdayDialog;
import com.fivefivelike.mybaselibrary.view.dialog.CityChooseDialog;
import com.fivefivelike.mybaselibrary.view.dialog.EditDialog;
import com.fivefivelike.mybaselibrary.view.dialog.ItemChooseDialog;
import com.fivefivelike.themvpbase.R;
import com.fivefivelike.themvpbase.adapter.MainAdapter;
import com.fivefivelike.themvpbase.mvp.delegate.MainDelegate;
import com.fivefivelike.themvpbase.util.UIHelper;
import com.fivefivelike.themvpbase.util.WebConfig;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemMoveListener;
import com.yanzhenjie.recyclerview.swipe.touch.OnItemStateChangedListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.fivefivelike.mybaselibrary.utils.ToastUtil.show;

public class MainActivity extends BaseActivity<MainDelegate> {
    private List<String> list;
    private MainAdapter adapter;

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        initToolbar(new ToolbarBuilder().setTitle("首页").setShowBack(false));
        list = new ArrayList<>();
        list.add("中间弹出框");
        list.add("下面弹出框");
        list.add("时间选择");
        list.add("地址选择");
        list.add("轮播");
        list.add("scrollview头部图片拉伸");
        list.add("数据请求页面");
        list.add("带分页的数据请求页面");
        list.add("web页面");
        list.add("图片选择.裁剪");
        list.add("没用的数据");
        list.add("没用的数据");
        list.add("没用的数据");
        list.add("没用的数据");
        list.add("没用的数据");
        list.add("没用的数据");
        list.add("没用的数据");
        viewDelegate.recycleview.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        viewDelegate.recycleview.setSwipeMenuItemClickListener(mMenuItemClickListener); // Item的Menu点击。
        viewDelegate.recycleview.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                initListener(position);
            }
        });
        viewDelegate.recycleview.setSwipeMenuCreator(mSwipeMenuCreator); // 菜单创建器。
        viewDelegate.recycleview.setLongPressDragEnabled(true); // 开启长按拖拽，默认关闭。
        viewDelegate.recycleview.setItemViewSwipeEnabled(true); // 不开启滑动删除，默认关闭。
        viewDelegate.recycleview.setOnItemMoveListener(onItemMoveListener);// 监听拖拽和侧滑删除，更新UI和数据源。
        viewDelegate.recycleview.setOnItemStateChangedListener(mOnItemStateChangedListener); // 监听Item的手指状态，拖拽、侧滑、松开。
        adapter = new MainAdapter(this, list);
        viewDelegate.recycleview.setAdapter(adapter);
    }

    private void initListener(int position) {
        String content = list.get(position);
        if (content.equals("中间弹出框")) {
            new EditDialog(this, new EditDialog.OnEditListener() {
                @Override
                public void onClickLeft() {
                    ToastUtil.show("点击了左边");
                }

                @Override
                public void onClickRight() {
                    ToastUtil.show("点击了右边");
                }
            }).setContent("这是在中间的弹出框")
                    .setTitle("这是标题")
                    .setLeft("左边按钮")
                    .setRight("右边按钮")
                    .setLeftTextColor(ContextCompat.getColor(this,R.color.green_normal))
                    .myShow();
        } else if (content.equals("下面弹出框")) {
            new ItemChooseDialog(this).builder().setTitle("这是标题")
                    .addItem("条目名字", 0, new ItemChooseDialog.OnItemClickListener() {
                        @Override
                        public void onClick() {
                            ToastUtil.show("1111");
                        }
                    }).addItem("条目名字", 0, new ItemChooseDialog.OnItemClickListener() {
                @Override
                public void onClick() {
                    ToastUtil.show("2222");
                }
            }).addItem("条目名字", 0, new ItemChooseDialog.OnItemClickListener() {
                @Override
                public void onClick() {
                    ToastUtil.show("333333");
                }
            }).show();

        } else if (content.equals("时间选择")) {
            new BirthdayDialog(this, new BirthdayDialog.OnTimeChooseListener() {
                @Override
                public void setOnTimeChooseListener(String time) {
                    ToastUtil.show(time);
                }
            }).show();
        } else if (content.equals("地址选择")) {
            new CityChooseDialog(this, new CityChooseDialog.OnChooseCityListener() {
                @Override
                public void chooseBack(AreaObj province, AreaObj city, AreaObj area) {
                    ToastUtil.show(province.getName() + city.getName() + area.getName());
                }
            }).show();
        } else if (content.equals("轮播")) {
            gotoActivty(new RollViewActivity());
        } else if (content.equals("scrollview头部图片拉伸")) {
            gotoActivty(new HeadExpandActivity());
        } else if (content.equals("数据请求页面")) {
            gotoActivty(new NormalRequestActivity());
        } else if (content.equals("带分页的数据请求页面")) {
            gotoActivty(new PageRequestActivity());
        } else if (content.equals("web页面")) {
            UIHelper.gotoWebView(this, "", "http://www.baidu.com", WebConfig.TEST_WEB);
        } else if (content.equals("图片选择.裁剪")) {
            gotoActivty(new ImageChooseActivity());
        }
    }

    /**
     * 监听拖拽和侧滑删除，更新UI和数据源。
     */
    private OnItemMoveListener onItemMoveListener = new OnItemMoveListener() {
        @Override
        public boolean onItemMove(RecyclerView.ViewHolder srcHolder, RecyclerView.ViewHolder targetHolder) {
            // 不同的ViewType不能拖拽换位置。
            if (srcHolder.getItemViewType() != targetHolder.getItemViewType())
                return false;

            int fromPosition = srcHolder.getAdapterPosition();
            int toPosition = targetHolder.getAdapterPosition();

            if (fromPosition < toPosition)
                for (int i = fromPosition; i < toPosition; i++)
                    Collections.swap(list, i, i + 1);
            else
                for (int i = fromPosition; i > toPosition; i--)
                    Collections.swap(list, i, i - 1);

            adapter.notifyItemMoved(fromPosition, toPosition);
            return true;// 返回true表示处理了，返回false表示你没有处理。
        }

        @Override
        public void onItemDismiss(int position) {
            list.remove(position);
            adapter.notifyItemRemoved(position);
            ToastUtil.show("现在的第" + position + "条被删除。");
        }
    };
    /**
     * Item的拖拽/侧滑删除时，手指状态发生变化监听。
     */
    private OnItemStateChangedListener mOnItemStateChangedListener = new OnItemStateChangedListener() {
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState == OnItemStateChangedListener.ACTION_STATE_DRAG) {

                // 拖拽的时候背景就透明了，这里我们可以添加一个特殊背景。
                viewHolder.itemView.setBackgroundColor(Color.parseColor("#FFCFCFCF"));
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_SWIPE) {
                viewDelegate.getmToolbarTitle().setText("状态：滑动删除");
            } else if (actionState == OnItemStateChangedListener.ACTION_STATE_IDLE) {
                viewDelegate.getmToolbarTitle().setText("状态：手指松开");
                // 在手松开的时候还原背景。
                ViewCompat.setBackground(viewHolder.itemView, ContextCompat.getDrawable(mContext, R.drawable.select_white));
            }
        }
    };

    /**
     * 菜单创建器。
     */
    private SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.trans_100px);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            //
            //            // 添加左侧的，如果不添加，则左侧不会出现菜单。
            //            {
            //
            //                swipeLeftMenu.addMenuItem(new SwipeMenuItem(MainActivity.this)
            //                        .setBackground(R.drawable.selector_green)
            //                        .setImage(R.drawable.ic_action_add)
            //                        .setWidth(width)
            //                        .setHeight(height)); // 添加一个按钮到左侧菜单。

            //                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
            //                        .setBackground(R.drawable.selector_red)
            //                        .setImage(R.drawable.ic_action_close)
            //                        .setWidth(width)
            //                        .setHeight(height);
            //
            //                swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
            //            }

            //            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            //            {
            //                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
            //                        .setBackground(R.drawable.selector_red)
            //                        .setImage(R.drawable.ic_action_delete)
            //                        .setText("删除")
            //                        .setTextColor(Color.WHITE)
            //                        .setWidth(width)
            //                        .setHeight(height);
            //                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
            //
            //                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
            //                        .setBackground(R.drawable.selector_purple)
            //                        .setImage(R.drawable.ic_action_close)
            //                        .setWidth(width)
            //                        .setHeight(height);
            //                swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。
            //            }
        }
    };


    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                show("list第" + adapterPosition + "; 右侧菜单第" + menuPosition);
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                show("list第" + adapterPosition + "; 左侧菜单第" + menuPosition);
            }
        }
    };


}
