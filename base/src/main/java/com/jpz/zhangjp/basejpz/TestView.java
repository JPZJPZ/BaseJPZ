package com.jpz.zhangjp.basejpz;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by zhangjp on 2016/6/7.
 */
public class TestView extends  BaseView{
    private final String TAG = "TestView";

    Paint paint ;


    public TestView(Activity activity) {
        super(activity);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        //canvas.drawLine(deltaX,deltaY,width+deltaY,height+deltaY,paint);
        paint.setStyle(Paint.Style.FILL);
        //canvas.drawRect(0+deltaX,0+deltaY,width+deltaX,height+deltaY,paint);

        canvas.drawRect(0,0,width,height,paint);

        paint.setColor(Color.WHITE);
        Random r = new Random();
        float gg = r.nextFloat();
        canvas.drawLine(0,0,width*gg,height*gg,paint);
    }

}
