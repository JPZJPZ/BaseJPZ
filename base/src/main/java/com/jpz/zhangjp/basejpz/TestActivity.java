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
        tv = new TestView(this);
        setContentView(tv);
    }
}