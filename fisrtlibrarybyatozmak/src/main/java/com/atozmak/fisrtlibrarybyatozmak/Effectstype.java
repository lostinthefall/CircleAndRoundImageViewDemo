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
 * 基础知识在[疯狂javaP224]
 */
public enum Effectstype {

    /**
     *  step 1 : 列出所有枚举实例 ，并传入参数 。
     */
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

    /**
     *  step 2 ：定义一个实例变量 。private修饰。
     */
    private Class<? extends BaseEffects> effectsCls;

    /**
     *  step 3 ：创建构造器 。private修饰。
     */
    private Effectstype(Class<? extends BaseEffects> mClass) {
        this.effectsCls = mClass;
    }

    /**
     *  step 4 ：？
     */
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
