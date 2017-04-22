package com.itheima.fuxiamall.bean;

import java.io.Serializable;

/**
 * Created by lxj on 2016/10/30.
 */

public class BaseBean<T> implements Serializable {
    public int code;//0是逻辑成功，-1是逻辑错误
    public String msg;
    public T data;//返回的真实的数据，类型不确定


    public boolean isSuccess(){
        return code==0;
    }


}
