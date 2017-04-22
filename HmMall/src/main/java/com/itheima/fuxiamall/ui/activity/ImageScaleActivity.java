package com.itheima.fuxiamall.ui.activity;

import android.support.v4.view.ViewPager;

import com.itheima.fuxiamall.R;
import com.itheima.fuxiamall.adapter.ImageScaleAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by lxj on 2016/9/5.
 */
public class ImageScaleActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_image_scale;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        ArrayList<String> urlList = getIntent().getStringArrayListExtra("urlList");
        int position = getIntent().getIntExtra("position", 0);

        ImageScaleAdapter adapter = new ImageScaleAdapter(urlList);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(position);
    }

}
