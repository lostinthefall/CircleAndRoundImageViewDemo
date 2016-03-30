package com.atozmak.fisrtlibrarybyatozmak.effects;

import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;

import com.atozmak.fisrtlibrarybyatozmak.util.LogUtil;

/**
 * Created by Mak on 2016/3/24.
 */
public class RotateBottom extends BaseEffects {
    public static final String TAG = LogUtil.makeLogTag("RotateBottom");

    @Override
    protected void setupAnimation(View view) {
        Log.v(TAG, ">>>>");
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "rotationX", 90, 0).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "translationY", 300, 0).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration)
        );
    }
}
