package com.itheima.fuxiamall.global;

import android.app.Application;
import android.content.Context;

import com.itheima.fuxiamall.bean.User;
import com.itheima.fuxiamall.util.SharePrefUtil;
import com.lxj.okhttpengine.OkHttpEngine;

import java.io.File;
import java.util.concurrent.TimeUnit;


/**
 * Created by lxj on 2016/8/4.
 */
public class HeimaMallApp extends Application {

    public static Context context;
    public static User user;
    @Override
    public void onCreate() {
        super.onCreate();


        context = getApplicationContext();

        //初始化OkHttpEngine配置
        OkHttpEngine.create()
                    .connectTimeout(1, TimeUnit.SECONDS)
                    .cache(new File(getCacheDir(),"cache"),Constant.Cache.Size);

        //获取登录的user
        user = (User) SharePrefUtil.create(this).getObj(Constant.User);



    }

}
