package com.jpz.basejpz.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.jpz.basejpz.R;


/**
 * Created by zhangjp on 2016/6/12.
 */
public class BaseBitmapView extends BaseView {

    // default scaleType
    private final int SCALE_DEFAULT = 0;
    // in center and clip if the bitmap is bigger
    private final int CENTER = 1;
    // scale let the short side fit the view size,the long side clip
    private final int CENTER_CROP = 2;
    //  scale the bitmap to fit the view,if the bitmap is smaller than the view,not scaled
    private final int CENTER_INSIDE = 3;
    //let the bitmap fit the view,so it's may shaped
    private final int FIT_XY = 4;

    private int scaleType = FIT_XY;

    Bitmap mBitmap;

    private int bitmapWidth;
    private int bitmapHeight;

    public BaseBitmapView(Activity activity) {
        super(activity);
    }

    public void setBitmapRes(int id) {
        mBitmap = BitmapFactory.decodeStream(mActivity.getResources().openRawResource(R.raw.d));
        bitmapWidth = mBitmap.getWidth();
        bitmapHeight = mBitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (scaleType == SCALE_DEFAULT) {
            Rect rect = new Rect(0, 0, (int) width, (int) height);
            canvas.drawBitmap(mBitmap, rect, rect, mPaint);
            return;
        }

        if (scaleType == CENTER) {
            Rect res = new Rect((int) ((bitmapWidth - width) / 2), (int) ((bitmapHeight - height) / 2), (int) ((bitmapWidth + width) / 2), (int) ((bitmapHeight + height) / 2));
            RectF dst = new RectF(0, 0, width, height);
            canvas.drawBitmap(mBitmap, res, dst, mPaint);
            return;
        }

        if (scaleType == CENTER_CROP) {
            Rect rect = new Rect(0, 0, (int) width, (int) height);
            if (width < bitmapWidth && height < bitmapHeight) {
                Rect res = new Rect((int) ((bitmapWidth - width) / 2), (int) ((bitmapHeight - height) / 2), (int) ((bitmapWidth + width) / 2), (int) ((bitmapHeight + height) / 2));
                canvas.drawBitmap(mBitmap, res, rect, mPaint);
                return;
            }
            Rect res = null;
            if (width > bitmapWidth && height > bitmapHeight) {
                res = new Rect(0, 0, bitmapWidth, bitmapHeight);
            } else if (width > bitmapWidth && height < bitmapHeight) {
                res = new Rect(0, (int) ((bitmapHeight - height) / 2), bitmapWidth, (int) ((bitmapHeight + height) / 2));
            } else if (width < bitmapWidth && height > bitmapHeight) {
                res = new Rect((int) ((bitmapWidth - width) / 2), 0, (int) ((bitmapWidth + width) / 2), bitmapHeight);
            }
            canvas.drawBitmap(mBitmap, res, rect, mPaint);
            return;
        }

        if (scaleType == CENTER_INSIDE) {
            Rect rect = new Rect(0, 0, (int) width, (int) height);
            if (width < bitmapWidth && height < bitmapHeight) {
                canvas.drawBitmap(mBitmap, null, rect, mPaint);
                return;
            }
            if (width > bitmapWidth && height > bitmapHeight) {
                Rect res = new Rect(0, 0, bitmapWidth, bitmapHeight);
                Rect dst = new Rect((int) ((width - bitmapWidth) / 2), (int) ((height - bitmapHeight) / 2), (int) ((width + bitmapWidth) / 2), (int) ((height + bitmapHeight) / 2));
                canvas.drawBitmap(mBitmap, res, dst, mPaint);
            } else if (width > bitmapWidth && height < bitmapHeight) {
                Rect res = new Rect(0, 0, bitmapWidth, bitmapHeight);
                Rect dst = new Rect((int) ((width - bitmapWidth) / 2), 0, (int) ((width + bitmapWidth) / 2), (int) height);
                canvas.drawBitmap(mBitmap, res, dst, mPaint);
            } else if (width < bitmapWidth && height > bitmapHeight) {
                Rect res = new Rect(0, 0, bitmapWidth, bitmapHeight);
                Rect dst = new Rect(0, (int) ((height - bitmapHeight) / 2), (int) width, (int) ((height + bitmapHeight) / 2));
                canvas.drawBitmap(mBitmap, res, dst, mPaint);
            }
            return;
        }

        if (scaleType == FIT_XY) {
            Rect res = new Rect(0, 0, bitmapWidth, bitmapHeight);
            RectF dst = new RectF(0, 0, width, height);
            canvas.drawBitmap(mBitmap, res, dst, mPaint);
            return;
        }

    }
}
