package com.atozmak.fisrtlibrarybyatozmak;

import com.atozmak.fisrtlibrarybyatozmak.effects.BaseEffects;
import com.atozmak.fisrtlibrarybyatozmak.effects.FadeIn;
import com.atozmak.fisrtlibrarybyatozmak.effects.Fall;
import com.atozmak.fisrtlibrarybyatozmak.effects.FlipH;
import com.atozmak.fisrtlibrarybyatozmak.effects.FlipV;
import com.atozmak.fisrtlibrarybyatozmak.effects.NewsPaper;
import com.atozmak.fisrtlibrarybyatozmak.effects.RotateBottom;
import com.atozmak.fisrtlibrarybyatozmak.effects.RotateLeft;
import com.atozmak.fisrtlibrarybyatozmak.effects.Shake;
import com.atozmak.fisrtlibrarybyatozmak.effects.SideFall;
import com.atozmak.fisrtlibrarybyatozmak.effects.SlideBottom;
import com.atozmak.fisrtlibrarybyatozmak.effects.SlideLeft;
import com.atozmak.fisrtlibrarybyatozmak.effects.SlideRight;
import com.atozmak.fisrtlibrarybyatozmak.effects.SlideTop;
import com.atozmak.fisrtlibrarybyatozmak.effects.Slit;

/**
 * Created by Mak on 2016/3/24.
 */
public enum Effectstype {

    Fadein(FadeIn.class),
    Slideleft(SlideLeft.class),
    Slidetop(SlideTop.class),
    Slidebottom(SlideBottom.class),
    Slideright(SlideRight.class),
    Fall(Fall.class),
    Newspaper(NewsPaper.class),
    Fliph(FlipH.class),
    Flipv(FlipV.class),
    RotateBottom(RotateBottom.class),
    RotateLeft(RotateLeft.class),
    Slit(Slit.class),
    Shake(Shake.class),
    Sidefill(SideFall.class);

    private Class<? extends BaseEffects> effectsCls;

    private Effectstype(Class<? extends BaseEffects> mClass) {
        effectsCls = mClass;
    }

    public BaseEffects getAnimator() {
        BaseEffects baseEffects = null;

        try {
            baseEffects = effectsCls.newInstance();
        } catch (InstantiationException e) {
            throw new Error("can not init animatorClass instance");
        } catch (IllegalAccessException e) {
            throw new Error("can not init animatorClass instance");
        }
        return baseEffects;
    }


}
