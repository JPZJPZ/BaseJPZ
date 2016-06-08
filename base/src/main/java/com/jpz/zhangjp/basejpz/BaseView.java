package com.jpz.zhangjp.basejpz;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

import com.jpz.zhangjp.basejpz.com.jpz.zhangjp.basejpz.utils.LogUtils;
import com.jpz.zhangjp.basejpz.com.jpz.zhangjp.basejpz.utils.Utils;

/**
 * Created by zhangjp on 2016/6/7.
 */
public class BaseView extends View{
    /**
     * activity who added the view
     */
    private Activity mActivity;

    /**
     * view width
     */
    float width;
    /**
     * view height
     */
    float height;
    /**
     * view move deltaX
     */
    float deltaX;
    /**
     * view move deltaY
     */
    float deltaY;
    /**
     *  view init or move stop  position(X) relative it's parent view
     */
    float xPosition;
    /**
     * view init or move stop  position(Y) relative it's parent view
     */
    float yPosition;
    /**
     * can move or not
     */
    boolean canMove = false;

    public BaseView(Activity activity) {
        super(activity);
        mActivity = activity;
    }

    /**
     * @param width
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * @param height
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * @param percentWidth view width percent with screen width
     */
    public void setWidthPercent(float percentWidth) {
        width = Utils.getScreenWidth(mActivity)*percentWidth;
    }

    /**
     * @param percentHeight view width percent with screen width
     */
    public void setHeightPercent(float percentHeight) {
        height = Utils.getScreenHeight(mActivity)*percentHeight;
    }

    /**
     *  set x position relative it's parent
     * @param xPosition
     */
    public void setXPosition(float xPosition) {
        this.xPosition = xPosition;
        setX(xPosition);
    }

    /**
     * set y position relative it's parent
     * @param yPosition
     */
    public  void setYPosition(float yPosition) {
        this.yPosition = yPosition;
        setY(yPosition);
    }

    /**
     * set x position relative it's parent with percent
     * @param percentX
     */
    public void setXPositionPercent(float percentX) {
        setXPosition(Utils.getScreenWidth(mActivity) * percentX);
    }

    /**
     * set y position relative it's parent with percent
     * @param percentY
     */
    public void setYPositionPercent(float percentY) {
        setYPosition(Utils.getScreenHeight(mActivity) * percentY);
    }

    /**
     * action_down x position
     */
    float xStart;
    /**
     * action_down y position
     */
    float yStart;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(!canMove) {
            return super.onTouchEvent(event);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xStart = event.getRawX();
                yStart = event.getRawY();
                LogUtils.logI("event.getX() = " + event.getX() + "  event.getRawX() = " + event.getRawX());
                return true;
            case MotionEvent.ACTION_MOVE:
                deltaX = event.getRawX() - xStart;
                deltaY = event.getRawY()-yStart;
                setTranslationX(xPosition+deltaX);
                setTranslationY(yPosition+deltaY);
                break;
            case MotionEvent.ACTION_UP:
                xPosition = getX();
                yPosition = getY();
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * refresh view
     */
    public void reFresh() {
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension((int)width,(int)height);
    }

    public void setCanMove(boolean move) {
        canMove = move;
    }

    public boolean getCanMove() {
        return canMove;
    }

}


