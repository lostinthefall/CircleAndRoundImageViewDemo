package com.atozmak.fisrtlibrarybyatozmak.effects;

import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.View;

import com.atozmak.fisrtlibrarybyatozmak.util.LogUtil;

/**
 * Created by Mak on 2016/3/24.
 */
public class RotateLeft extends BaseEffects {
    // public static final String TAG = LogUtil.makeLogTag("RotateLeft");

    @Override
    protected void setupAnimation(View view) {
        // Log.v(TAG, ">>>>>>>1");
        getAnimatorSet().playTogether(


                ObjectAnimator.ofFloat(view, "rotationY", 300, 0).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "translationX", -300, 0).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration)


        );
        // Log.v(TAG, ">>>>>>>2");
    }
}
