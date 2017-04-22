package com.itheima.fuxiamall.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.itheima.fuxiamall.R;
import com.itheima.fuxiamall.util.Util2;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/11/25.
 */
public class  WXPayActivity extends Activity {
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay2);

        api = WXAPIFactory.createWXAPI(this, "wxb4ba3c02aa476ea1");

        Button appayBtn = (Button) findViewById(R.id.appay_btn);
        appayBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = "http://wxpay.weixin.qq.com/pub_v2/app/app_pay.php?plat=android";
                Button payBtn = (Button) findViewById(R.id.appay_btn);
                payBtn.setEnabled(false);
                Toast.makeText(WXPayActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();
                try{
                    byte[] buf = Util2.httpGet(url);
                    if (buf != null && buf.length > 0) {
                        String content = new String(buf);
                        Log.e("get server pay params:",content);
                        JSONObject json = new JSONObject(content);
                        if(null != json && !json.has("retcode") ){
                            PayReq req = new PayReq();
                            //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                            req.appId			= json.getString("appid");
                            req.partnerId		= json.getString("partnerid");
                            req.prepayId		= json.getString("prepayid");
                            req.nonceStr		= json.getString("noncestr");
                            req.timeStamp		= json.getString("timestamp");
                            req.packageValue	= json.getString("package");
                            req.sign			= json.getString("sign");
                            req.extData			= "app data"; // optional
                            Toast.makeText(WXPayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                            api.sendReq(req);
                        }else{
                            Log.d("PAY_GET", "返回错误"+json.getString("retmsg"));
                            Toast.makeText(WXPayActivity.this, "返回错误"+json.getString("retmsg"), Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Log.d("PAY_GET", "服务器请求错误");
                        Toast.makeText(WXPayActivity.this, "服务器请求错误", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    Log.e("PAY_GET", "异常："+e.getMessage());
                    Toast.makeText(WXPayActivity.this, "异常："+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                payBtn.setEnabled(true);
            }
        });
//        Button checkPayBtn = (Button) findViewById(R.id.check_pay_btn);
//        checkPayBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
//                Toast.makeText(WXPayActivity.this, String.valueOf(isPaySupported), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
