package com.atozmak.circleandroundimageviewdemo.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.atozmak.circleandroundimageviewdemo.utils.LogUtil;

import java.security.spec.MGF1ParameterSpec;

/**
 * Created by Mak on 2016/3/22.
 */
public class ClockView extends View {

    public static final String TAG = LogUtil.makeLogTag("ClockView");

    private Paint paintCircle, paintDegree, paintPoint, paintHour, paintMin;
    private int mHeight, mWidth;

    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        /**
         * 外圆
         */
        paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(5);
        /**
         * 刻度
         */
        paintDegree = new Paint();
        paintDegree.setStrokeWidth(3);
        /**
         * 圆心
         */
        paintPoint = new Paint();
        paintPoint.setStrokeWidth(30);
        /**
         * 时针，分针
         */
        paintHour = new Paint();
        paintHour.setStrokeWidth(20);
        paintMin = new Paint();
        paintMin.setStrokeWidth(10);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mWidth = getWidth();
        mHeight = getHeight();

        Log.v(TAG, "mWidth:" + mWidth);
        Log.v(TAG, "mHeight:" + mHeight);

        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2 - 10, paintCircle);

        canvas.drawPoint(mWidth / 2, mHeight / 2, paintPoint);

        canvas.rotate(180, mWidth / 2, mHeight / 2);

        for (int i = 0; i < 24; i++) {
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                paintDegree.setStrokeWidth(10);
                paintDegree.setTextSize(60);
                canvas.drawLine(
                        mWidth / 2,
                        10,
                        mWidth / 2,
                        90,
                        paintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(
                        degree,
                        mWidth / 2 - paintDegree.measureText(degree) / 2,
                        160,
                        paintDegree
                );
            } else {
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextSize(40);
                canvas.drawLine(
                        mWidth / 2,
                        10,
                        mWidth / 2,
                        60,
                        paintDegree
                );
                String degree = String.valueOf(i);
                canvas.drawText(
                        degree,
                        mWidth / 2 - paintDegree.measureText(degree) / 2,
                        100,
                        paintDegree
                );
            }
            canvas.rotate(360 / 24, mWidth / 2, mHeight / 2);
        }
        canvas.save();
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(0, 0, 100, 100, paintHour);
        canvas.drawLine(0, 0, 100, 200, paintMin);
    }
}
