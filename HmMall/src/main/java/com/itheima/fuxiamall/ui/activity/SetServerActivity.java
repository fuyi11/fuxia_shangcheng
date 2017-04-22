package com.itheima.fuxiamall.ui.activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.widget.Button;

import com.itheima.fuxiamall.R;
import com.itheima.fuxiamall.util.SharePrefUtil;
import com.itheima.fuxiamall.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lxj on 2016/10/10.
 */

public class SetServerActivity extends BaseActivity {
    @BindView(R.id.et_server)
    TextInputEditText etServer;
    @BindView(R.id.btn)
    Button btn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_server;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        showTitle(false, "设置服务器的ip", "");

        String serverhost = SharePrefUtil.create(this).getString("serverhost", null);
        if(!TextUtils.isEmpty(serverhost)){
//            startActivity(new Intent(this,SplashActivity.class));
//            finish();
            etServer.setText(serverhost);
        }
    }


    @OnClick(R.id.btn)
    public void onClick() {
        String string = etServer.getText().toString();
        if(!TextUtils.isEmpty(string)){
            SharePrefUtil.create(this).saveString("serverhost",string);
            ToastUtil.showToast("保存成功！");
            startActivity(new Intent(this,SplashActivity.class));
            finish();
        }
    }
}
