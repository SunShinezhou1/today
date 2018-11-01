package com.example.my_shopping2.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.my_shopping2.R;

public class webViewActivity extends Activity {

    private ImageView jingd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
        jingd.setScaleType(ImageView.ScaleType.FIT_XY);
        //两秒钟进入主界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(webViewActivity.this,MainActivity.class));
                finish();
            }
        },2000);


    }

    private void initView() {
        jingd = (ImageView) findViewById(R.id.jingd);
    }
}
