package com.atozmak.fisrtlibrarybyatozmak.effects;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by Mak on 2016/3/24.
 */
public class Shake extends BaseEffects {
    @Override
    protected void setupAnimation(View view) {

        getAnimatorSet().playTogether(

                //不用管这里，这里的参数设置就是让效果看起来没有这么抖而已。
                ObjectAnimator.ofFloat(view, "translationX",
                        0, 0.10f, -25, 0.2f, 25, 0.42f,
                        -25, 0.58f, 25, 0.74f, -25, 0.90f, 1, 0).setDuration(mDuration)
             /*   ObjectAnimator.ofFloat(view, "translationX",
                        0, -250, 250,
                        -250, 250, -25,-25, 25,
                        -25, 25, -25, 25,
                        -25, 25, -25 ,0).setDuration(mDuration)*/


        );
    }
}
