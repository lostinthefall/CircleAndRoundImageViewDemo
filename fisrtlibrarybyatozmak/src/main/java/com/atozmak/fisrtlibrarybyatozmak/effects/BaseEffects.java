package com.atozmak.fisrtlibrarybyatozmak.effects;

import android.animation.AnimatorSet;
import android.view.View;

/**
 * 效果抽象基类
 * 作用：1.初始化AnimatorSet 2.
 */
public abstract class BaseEffects {

    protected long mDuration = DURATION;
    private static final int DURATION = 700;
    private AnimatorSet mAnimatorSet;

    {
        //用来把动画效果组合起来
        mAnimatorSet = new AnimatorSet();
    }

    protected abstract void setupAnimation(View view);

    public void start(View view) {
        reset(view);
        //１．在setupAnimation中把set组合起来。
        setupAnimation(view);
        //２．播放动画。
        mAnimatorSet.start();
    }

    public void reset(View view) {
        /**
         *   啊，原来JakeWharton的nineoldandroids的
         *   ViewHelper.setPivotX(view, view.getMeasuredWidth() / 2.0f);
         *   对应的是下面这个啊
         */
        //设置中心点
        view.setPivotX(view.getMeasuredWidth() / 2.0f);
        view.setPivotY(view.getMeasuredHeight() / 2.0f);
    }

    public AnimatorSet getAnimatorSet() {
        return mAnimatorSet;
    }

    public void setDuration(long duration) {
        this.mDuration = duration;
    }
}
