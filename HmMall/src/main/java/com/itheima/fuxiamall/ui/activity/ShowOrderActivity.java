package com.itheima.fuxiamall.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.itheima.fuxiamall.R;
import com.itheima.fuxiamall.adapter.ViewPagerIndicatorAdapter;
import com.itheima.fuxiamall.global.Constant;
import com.itheima.fuxiamall.ui.fragment.OrderListFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by lxj on 2016/8/21.
 */
public class ShowOrderActivity extends BaseActivity {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    ArrayList<ViewPagerIndicatorAdapter.PageInfo> pageInfos = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_order;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        showTitle(true,"我的订单","");

        preparePageInfo();

        viewPager.setAdapter(new ViewPagerIndicatorAdapter(getSupportFragmentManager(), pageInfos));

        tabLayout.setupWithViewPager(viewPager);


    }
    private void preparePageInfo() {
        String[] titles = getResources().getStringArray(R.array.order_state);
        pageInfos.add(new ViewPagerIndicatorAdapter.PageInfo(createFragment(Constant.OrderState.StateUnpay), titles[0]));
        pageInfos.add(new ViewPagerIndicatorAdapter.PageInfo(createFragment(Constant.OrderState.StateUnsend), titles[1]));
        pageInfos.add(new ViewPagerIndicatorAdapter.PageInfo(createFragment(Constant.OrderState.StateUnreceived), titles[2]));
    }

    private Fragment createFragment(int state) {
        OrderListFragment goodsListFragment = new OrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(OrderListFragment.STATE, state);
        goodsListFragment.setArguments(bundle);
        return goodsListFragment;
    }

}
