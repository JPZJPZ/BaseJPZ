package com.jpz.zhangjp.basejpz;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by zhangjp on 2016/6/7.
 */
public class TestActivity extends Activity {
    TestView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        tv = new TestView(this);
//        tv.setWidthPercent(0.5f);
//        tv.setHeightPercent(0.5f);
//        tv.setXPositionPercent(0.25f);
//        tv.setYPositionPercent(0.25f);
//        tv.setCanMove(true);

        TestBitmapView tbv = new TestBitmapView(this);
        tbv.setWidthPercent(0.5f);
        tbv.setHeightPercent(0.5f);
        tbv.setXPositionPercent(0.25f);
      //  tbv.setYPositionPercent(0.25f);
        tbv.setBitmapRes(0);
        tbv.setCanMove(true);
        setContentView(tbv);

        //setContentView(R.layout.test);
    }
}
