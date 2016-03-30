package com.atozmak.fisrtlibrarybyatozmak.effects;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Mak on 2016/3/24.
 */
public class FlipH extends BaseEffects {
    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(
                ObjectAnimator.ofFloat(view, "rotationY", -90, 0).setDuration(mDuration)
        );
    }
}
