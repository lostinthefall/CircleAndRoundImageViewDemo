package com.atozmak.fisrtlibrarybyatozmak;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;

/**
 * Created by Mak on 2016/3/24.
 */
public class ColorUtils {

    public static ColorFilter getColorFilter(int color) {
        ColorMatrixColorFilter colorFilter;
        int red = (color & 0xff0000) / 0xffff;
        int green = (color & 0xff00) / 0xff;
        int blue = color & 0xff;

        float[] matrix = {
                0, 0, 0, 0, red,
                0, 0, 0, 0, green,
                0, 0, 0, 0, blue,
                0, 0, 0, 1, 0
        };
        colorFilter = new ColorMatrixColorFilter(matrix);
        return colorFilter;
    }
}
