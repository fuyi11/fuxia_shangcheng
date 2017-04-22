package com.itheima.fuxiamall.ui.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.itheima.fuxiamall.R;
import com.itheima.fuxiamall.ui.fragment.LoginFragment;
import com.itheima.fuxiamall.ui.fragment.RegisterFragment;


/**
 * Created by lxj on 2016/8/20.
 */
public class LoginActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setListener() {
    }
    @Override
    protected void initData() {

        showTitle(true, "登录", "注册");
        replaceFragment(new LoginFragment());
    }
    private boolean isShowLogin = true;
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,fragment).commitAllowingStateLoss();
    }

    @Override
    public void onRightTitleClick() {
        super.onRightTitleClick();
        if (isShowLogin) {
            showTitle(true, "注册", "登录");
            replaceFragment(new RegisterFragment());
        } else {
            showTitle(true, "登录", "注册");
            replaceFragment(new LoginFragment());
        }
        isShowLogin = !isShowLogin;
    }
}
