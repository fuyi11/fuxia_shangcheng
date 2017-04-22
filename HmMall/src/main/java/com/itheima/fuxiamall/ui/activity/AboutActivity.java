package com.itheima.fuxiamall.ui.activity;

import com.itheima.fuxiamall.R;

/**
 * Created by lxj on 2016/10/6.
 */
public class AboutActivity extends BaseActivity{
    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        showTitle(true,"关于","");
    }
}
