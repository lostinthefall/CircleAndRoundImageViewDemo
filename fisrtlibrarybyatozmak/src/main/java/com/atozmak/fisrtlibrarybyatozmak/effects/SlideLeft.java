package com.atozmak.fisrtlibrarybyatozmak.effects;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Mak on 2016/3/24.
 */
public class SlideLeft extends BaseEffects {
    @Override
    protected void setupAnimation(View view) {

        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "translationX", -300, 0).setDuration(mDuration),
                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration)


        );
    }
}
