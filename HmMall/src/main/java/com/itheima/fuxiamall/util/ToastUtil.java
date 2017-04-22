package com.itheima.fuxiamall.util;

import android.os.Handler;
import android.widget.Toast;

import com.itheima.fuxiamall.global.HeimaMallApp;

/**
 * Created by lxj on 2016/8/23.
 */
public class ToastUtil {
    private static Toast toast;
    public static void showToast(String msg){
        if(toast==null){
            toast = Toast.makeText(HeimaMallApp.context,msg,Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }

    /**
     * 在主线程显示吐司
     * @param msg
     */
    public static void showToastOnUIThread(final String msg){
        Handler handler = new Handler(HeimaMallApp.context.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                showToast(msg);
            }
        });
    }
}
