package com.atozmak.fisrtlibrarybyatozmak.effects;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Mak on 2016/3/24.
 */
public class SideFall extends BaseEffects {
    @Override
    protected void setupAnimation(View view) {

        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "scaleX", 2, 1.5f, 1).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "scaleY", 2, 1.5f, 1).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "rotation", 25, 0).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "translationX", 80, 0).setDuration(mDuration),
            ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration)


        );
    }
}
