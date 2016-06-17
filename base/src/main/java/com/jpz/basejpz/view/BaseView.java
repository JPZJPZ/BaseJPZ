package com.jpz.basejpz.view;

import android.app.Activity;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.jpz.basejpz.control.ViewManager;
import com.jpz.basejpz.utils.Utils;

/**
 * Created by zhangjp on 2016/6/7.
 */
public class BaseView extends View {
    // activity who added the view
    Activity mActivity;

    //view width
    public float width;
    //view height
    public float height;
    //view move deltaX
    float deltaX;
    //view move deltaY
    float deltaY;
    //view init or move stop  position(X) relative it's parent view
    private float xPosition;
    // view init or move stop  position(Y) relative it's parent view
    private float yPosition;
    //if the view move follow finger
    boolean moveFollowFinger = false;
    /**
     * whether the view is moving,when touched the view or the view is move
     * follow the another,return true
     */
    private boolean isMoving = false;
    //view's paint
    Paint mPaint;

    public BaseView(Activity activity) {
        super(activity);
        mActivity = activity;
        mPaint = new Paint();
        ViewManager.getInstance().put(mActivity, this);
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
        width = Utils.getScreenWidth(mActivity) * percentWidth;
    }

    /**
     * @param percentHeight view width percent with screen width
     */
    public void setHeightPercent(float percentHeight) {
        height = Utils.getScreenHeight(mActivity) * percentHeight;
    }

    /**
     * set x position relative it's parent
     *
     * @param xPosition
     */
    public void setXPosition(float xPosition) {
        this.xPosition = xPosition;
        setX(xPosition);
    }

    /**
     * set y position relative it's parent
     *
     * @param yPosition
     */
    public void setYPosition(float yPosition) {
        this.yPosition = yPosition;
        setY(yPosition);
    }

    /**
     * set x position relative it's parent with percent
     *
     * @param percentX
     */
    public void setXPositionPercent(float percentX) {
        setXPosition(Utils.getScreenWidth(mActivity) * percentX);
    }

    /**
     * set y position relative it's parent with percent
     *
     * @param percentY
     */
    public void setYPositionPercent(float percentY) {
        setYPosition(Utils.getScreenHeight(mActivity) * percentY);
    }

    /**
     * @return the x coordinate relative the screen (0,0)
     */
    public float getXPosition() {
        return xPosition;
    }

    /**
     * @return the y coordinate relative the screen (0,0)
     */
    public float getYPosition() {
        return yPosition;
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
    }

    // action_down x position
    float xStart;
    // action_down y position
    float yStart;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!moveFollowFinger) {
            return super.onTouchEvent(event);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xStart = event.getRawX();
                yStart = event.getRawY();
                ViewManager.getInstance().setIsMoving("One",true);
                return true;
            case MotionEvent.ACTION_MOVE:
                deltaX = event.getRawX() - xStart;
                deltaY = event.getRawY() - yStart;
                setTranslationX(xPosition + deltaX);
                setTranslationY(yPosition + deltaY);
               // LogUtils.logI("event.getX() = " + event.getX() + "  event.getRawX() = " + event.getRawX() + "view.getX()" + getX());
                ViewManager.getInstance().moveFollow(this,"One",deltaX,deltaY);
                break;
            case MotionEvent.ACTION_UP:
                ViewManager.getInstance().setIsMoving("One",false);
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
        setMeasuredDimension((int) width, (int) height);
    }

    public void setMoveFollowFingerEnable(boolean enable) {
        moveFollowFinger = enable;
    }

    public boolean isMoveFollowFinger() {
        return moveFollowFinger;
    }

    public void setIsMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isMoving() {
        return isMoving;
    }

}


