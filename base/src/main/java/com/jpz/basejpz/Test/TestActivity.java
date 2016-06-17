package com.jpz.basejpz.Test;

import android.app.Activity;
import android.os.Bundle;

import com.jpz.basejpz.control.ViewManager;
import com.jpz.basejpz.view.JPZViewGroup;

/**
 * Created by zhangjp on 2016/6/7.
 */
public class TestActivity extends Activity {
    TestView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv = new TestView(this);
        tv.setWidthPercent(0.1f);
        tv.setHeightPercent(0.1f);
        tv.setXPositionPercent(0.1f);
        tv.setYPositionPercent(0.1f);
        tv.setMoveFollowFingerEnable(true);

        TestBitmapView tbv = new TestBitmapView(this);
        tbv.setWidthPercent(0.5f);
        tbv.setHeightPercent(0.5f);
        tbv.setXPositionPercent(0.25f);
        //  tbv.setYPositionPercent(0.25f);
        tbv.setBitmapRes(0);
        tbv.setMoveFollowFingerEnable(true);

        ViewManager.getInstance().putViewToGroup("One",tv);
        ViewManager.getInstance().putViewToGroup("One",tbv);

        JPZViewGroup group = new JPZViewGroup(this);
        group.addView(tbv);
        group.addView(tv);
        setContentView(group);
        //  setContentView(tbv);

        //setContentView(R.layout.test);
    }
}
