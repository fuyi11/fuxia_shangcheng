package com.itheima.fuxiamall.ui.fragment;

import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.itheima.fuxiamall.R;
import com.itheima.fuxiamall.bean.UserResult;
import com.itheima.fuxiamall.global.Constant;
import com.itheima.fuxiamall.global.HeimaMallApp;
import com.itheima.fuxiamall.global.Url;
import com.itheima.fuxiamall.util.DialogUtil;
import com.itheima.fuxiamall.util.Logger;
import com.itheima.fuxiamall.util.MD5Util;
import com.itheima.fuxiamall.util.SharePrefUtil;
import com.itheima.fuxiamall.util.ToastUtil;
import com.lxj.okhttpengine.OkHttpEngine;
import com.lxj.okhttpengine.PostParams;
import com.lxj.okhttpengine.callback.OkHttpCallback;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lxj on 2016/10/13.
 */

public class LoginFragment extends BaseFragment {
    @BindView(R.id.et_username)
    TextInputEditText etUsername;
    @BindView(R.id.et_password)
    TextInputEditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @Override
    public int getLayoutId() {
        return R.layout.layout_login;
    }

    @Override
    protected void setListener() {

    }

    @OnClick({R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (checkUsernameAndPassword(etUsername, etPassword)) {
                    executeLogin();
                }
                break;
        }
    }

    @Override
    public void loadData() {
        stateLayout.showContentView();
    }

    /**
     * 执行登录请求
     */
    private void executeLogin() {
        DialogUtil.showProgressDialog(getActivity(), "register", "正在登录");
        PostParams params = new PostParams();
        params.add("username", etUsername.getText().toString());
        params.add("password", MD5Util.md5(etPassword.getText().toString()));
        OkHttpEngine.create().post(Url.login, params, new OkHttpCallback<UserResult>(){
            @Override
            public void onSuccess(UserResult userBaseBean) {
                DialogUtil.dismissDialog("register");
                if(userBaseBean.isSuccess()){
                    Logger.e("tag", userBaseBean.data.toString());
                    //1.保存最新的用户数据
                    SharePrefUtil.create(getActivity())
                            .saveObj(Constant.User, userBaseBean.data);
                    HeimaMallApp.user = userBaseBean.data;

                    //通知界面更新
                    EventBus.getDefault().post(userBaseBean.data);
                    ToastUtil.showToast("登录成功");
                    getActivity().finish();
                }else {
                    ToastUtil.showToast(userBaseBean.msg);
                    DialogUtil.dismissDialog("register");
                }
            }
            @Override
            public void onFail(IOException e) {
                ToastUtil.showToast("请求失败");
                DialogUtil.dismissDialog("register");
            }
        });
    }
    /**
     * 检查用户名和密码
     *
     * @return
     */
    private boolean checkUsernameAndPassword(TextInputEditText etUser, TextInputEditText etPass) {
        final String username = etUser.getText().toString().trim();
        String password = etPass.getText().toString().trim();
        //1.检查用户名是否为空
        if (TextUtils.isEmpty(username)) {
            etUser.setError("用户名不能为空！");
            return false;
        }
        //2.检查用户名是否合法
        if (!isRightInput(username)) {
            etUser.setError("用户名必须是5-15位数字和字母组合");
            return false;
        }
        //3.检查密码是否为空
        if (TextUtils.isEmpty(password)) {
            etPass.setError("密码不能为空！");
            return false;
        }
        //4.检查密码是否合法
        if (!isRightInput(password)) {
            etPass.setError("密码必须是5-15位数字和字母组合");
            return false;
        }
        return true;
    }
    /**
     * 是否是合法的用户名
     *
     * @param username
     * @return
     */
    private boolean isRightInput(String username) {
        String rg = "^[0-9a-zA-Z]{5,15}$";
        return username.matches(rg);
    }
}
