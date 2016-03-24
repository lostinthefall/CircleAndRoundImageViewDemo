package com.atozmak.circleandroundimageviewdemo.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import com.atozmak.circleandroundimageviewdemo.utils.LogUtil;

/**
 * Created by Mak on 2016/3/23.
 */
public class ScrollLayoutView extends View {

    private static final String TAG = LogUtil.makeLogTag("ScrollLayoutView");

    private int lastX;
    private int lastY;
    private Scroller mScroll;

    public ScrollLayoutView(Context context) {
        super(context);
        init(context);

    }

    public ScrollLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    private void init(Context context) {
        mScroll = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
            /*    layout(
                        getLeft() + offsetX,
                        getTop() + offsetY,
                        getRight() + offsetX,
                        getBottom() + offsetY
                );*/
          /*      //和layout用法一样
                offsetLeftAndRight(offsetX);
                offsetTopAndBottom(offsetY);*/
                //和上面用法一样
                //   ((View) getParent()).scrollBy(-offsetX, -offsetY);
                break;

            case MotionEvent.ACTION_UP:
                View viewGroup = (View) getParent();
                mScroll.startScroll(
                        //getScrollX() : @return The left edge of the displayed part of your view, in pixels.
                        viewGroup.getScrollX(),
                        viewGroup.getScrollY(),
//                        -viewGroup.getScrollX(),
//                        -viewGroup.getScrollY()
                        -100, 0,2000
                );
                Log.v(TAG,".....>>>");
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroll.computeScrollOffset()) {
            ((View) getParent()).scrollTo(
                    //Returns the current X offset in the scroll.
                    mScroll.getCurrX(),
                    mScroll.getCurrY()
            );
            invalidate();
            Log.v(TAG, ">>>>>>"+mScroll.getCurrX()+">>>>"+mScroll.getCurrY());
        } else {

        }
    }
}
