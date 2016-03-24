package com.atozmak.fisrtlibrarybyatozmak.effects;

import android.animation.AnimatorSet;
import android.support.v4.widget.ViewDragHelper;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewManager;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by Mak on 2016/3/24.
 */
public abstract class BaseEffects {

    protected long mDuration = DURATION;
    private static final int DURATION = 700;
    private AnimatorSet mAnimatorSet;

    {
        mAnimatorSet = new AnimatorSet();
    }

    protected abstract void setupAnimation(View view);

    public void start(View view) {
        reset(view);
        setupAnimation(view);
        mAnimatorSet.start();
    }

    public void reset(View view) {
        /**
         *   啊，原来JakeWharton的nineoldandroids的
         *   ViewHelper.setPivotX(view, view.getMeasuredWidth() / 2.0f);
         *   对应的是下面这个啊
         */
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
