package com.itheima.fuxiamall.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.fuxiamall.R;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import butterknife.BindView;

/**
 * Created by Administrator on 2016/11/24.
 */
public class ZxingActivity extends BaseActivity{

    @BindView(R.id.tv_result)
    TextView mResult;
    @BindView(R.id.et_text)
    EditText mEtText;
    @BindView(R.id.iv_image)
    ImageView mImage;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){
            // 获取返回结果
            String result = data.getStringExtra("result");
            // 将结果设置给TextView
            mResult.setText(result);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_zxing;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {
        showTitle(true,"扫描二维码","");
    }


    /**
     * 扫描二维码
     *
     * */
    public void scan(View view){
        // 开启ZXing库中可以扫描的二维码的Activity
        Intent intent = new Intent(this, CaptureActivity.class);
        // 要有返回结果
        startActivityForResult(intent,1);

    }



    /**生产二维码*/
    public void create(View view){
        String result = mEtText.getText().toString();
        if (!TextUtils.isEmpty(result)){
            Bitmap bitmap = EncodingUtils.createQRCode(result,500,500,
                    BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
            mImage.setImageBitmap(bitmap);
        }else{
            // 提示
            Toast.makeText(this,"输入不能为空",Toast.LENGTH_LONG).show();

        }

    }
}
